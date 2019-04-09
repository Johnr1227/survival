package com.cool.audio;

import static org.lwjgl.openal.AL10.*;
import static org.lwjgl.openal.ALC10.*;
import static org.lwjgl.stb.STBVorbis.*;
import static org.lwjgl.system.MemoryStack.*;

import org.lwjgl.openal.*;

import java.nio.*;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class Audio {
	private static ALCCapabilities alcCapabilities;
	private static ALCapabilities alCapabilities;

	private static String defaultDeviceName;
	private static int[] attributes = { 0 };
	private static long context;
	private static long device;

	public static Queue<Integer> shortSources = new ArrayBlockingQueue<>(10);
	public static Queue<Integer> mediumSources = new ArrayBlockingQueue<>(5);
	public static Queue<Integer> longSources = new ArrayBlockingQueue<>(3);

	public static void init() {
		defaultDeviceName = alcGetString(0, ALC_DEFAULT_DEVICE_SPECIFIER);
		device = alcOpenDevice(defaultDeviceName);
		context = alcCreateContext(device, attributes);
		alcMakeContextCurrent(context);
		alcCapabilities = ALC.createCapabilities(device);
		alCapabilities = AL.createCapabilities(alcCapabilities);
		if (alCapabilities.OpenAL10) {
			// OpenAL 1.0 is supported
		}
	}

	public static Sound loadSound(String filename) {
		// Allocate space to store return information from the function
		stackPush();
		IntBuffer channelsBuffer = stackMallocInt(1);
		stackPush();
		IntBuffer sampleRateBuffer = stackMallocInt(1);

		ShortBuffer rawAudioBuffer = stb_vorbis_decode_filename(filename, channelsBuffer, sampleRateBuffer);

		// Retreive the extra information that was stored in the buffers by the function
		int channels = channelsBuffer.get();
		int sampleRate = sampleRateBuffer.get();

		// Free the space we allocated earlier
		stackPop();
		stackPop();
		// Find the correct OpenAL format
		int format = -1;
		if (channels == 1) {
			format = AL_FORMAT_MONO16;
		} else if (channels == 2) {
			format = AL_FORMAT_STEREO16;
		}

		// Request space for the buffer
		int bufferPointer = alGenBuffers();

		// Send the data to OpenAL
		alBufferData(bufferPointer, format, rawAudioBuffer, sampleRate);
		return new Sound(bufferPointer, format, rawAudioBuffer, sampleRate, SoundLength.SHORT);
	}

	public static void destroy() {
		alcDestroyContext(context);
		alcCloseDevice(device);
	}

	public static class Sound {
		public int bufferPointer;
		public int format;
		public ShortBuffer rawAudioBuffer;
		public int sampleRate;

		public SoundLength length;

		public Sound(int bufferPointer, int format, ShortBuffer rawAudioBuffer, int sampleRate, SoundLength length) {
			this.bufferPointer = bufferPointer;
			this.format = format;
			this.rawAudioBuffer = rawAudioBuffer;
			this.sampleRate = sampleRate;
			this.length = length;
		}

		public void play() {
//			alBufferData(bufferPointer, format, rawAudioBuffer, sampleRate);
//			alDeleteBuffers(bufferPointer);	
			int sourcePointer = alGenSources();
			alSourcei(sourcePointer, AL_BUFFER, bufferPointer);
			alSourcePlay(sourcePointer);
			switch (length) {
			case SHORT:
				if (shortSources.size() >= 10)
					alDeleteSources(shortSources.poll());
				shortSources.add(sourcePointer);
				break;
			case MEDIUM:
				if (mediumSources.size() >= 5)
					alDeleteSources(mediumSources.poll());
				mediumSources.add(sourcePointer);
				break;
			case LONG:
				if (mediumSources.size() >= 3)
					alDeleteSources(longSources.poll());
				longSources.add(sourcePointer);
				break;
			}
		}

		public Sound setLength(SoundLength length) {
			this.length = length;
			return this;
		}

		public void loop() {
			int sourcePointer = alGenSources();
			alSourcei(sourcePointer, AL_BUFFER, bufferPointer);
			alSourcei(sourcePointer, AL_LOOPING, 1);
			alSourcePlay(sourcePointer);
			switch (length) {
			case SHORT:
				if (shortSources.size() >= 10)
					alDeleteSources(shortSources.poll());
				shortSources.add(sourcePointer);
				break;
			case MEDIUM:
				if (mediumSources.size() >= 5)
					alDeleteSources(mediumSources.poll());
				mediumSources.add(sourcePointer);
				break;
			case LONG:
				if (mediumSources.size() >= 3)
					alDeleteSources(longSources.poll());
				longSources.add(sourcePointer);
				break;
			}
		}
	}

	public static enum SoundLength {
		SHORT, MEDIUM, LONG;
	}
}
