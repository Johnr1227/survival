package com.cool;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;

import com.cool.audio.Audio;
import com.cool.audio.Audio.Sound;
import com.cool.audio.Audio.SoundLength;

public class Sounds {
	public static Sound MENU_SELECT;
	public static Sound MENU_CLICK;
	
	public static void init() throws IOException {
		MENU_SELECT = Audio.loadSound(unpackResource("ui/button_select.ogg")).setLength(SoundLength.SHORT);
		MENU_CLICK= Audio.loadSound(unpackResource("ui/button_click.ogg")).setLength(SoundLength.SHORT);
	}
	public static String unpackResource(String path) throws IOException {
		path = "audio/" + path;
		File tempDir = new File(System.getProperty("java.io.tmpdir"));
		File file = new File(tempDir,path);
		file.getParentFile().mkdirs();
		FileOutputStream fileOutputSteam = new FileOutputStream(file);
		IOUtils.copy(ClassLoader.getSystemResourceAsStream(path),fileOutputSteam);
		return file.getAbsolutePath();
	}
}
