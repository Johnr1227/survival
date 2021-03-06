package com.cool.lib;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class IO {
	public static void write(String filePath, Object object) {
		try {
			FileOutputStream f = new FileOutputStream(new File(filePath));
			ObjectOutputStream o = new ObjectOutputStream(f);

			o.writeObject(object);

			o.close();
			f.close();

		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("Error initializing output stream");
		}
	}

	public static void write(String filePath, Object... objects) {
		try {
			FileOutputStream f = new FileOutputStream(new File(filePath));
			ObjectOutputStream o = new ObjectOutputStream(f);

			for (Object obj : objects) {
				o.writeObject(obj);
			}

			o.close();
			f.close();

		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("Error initializing output stream");
		}
	}

	public static Object read(String filePath) {
		Object ret;
		try {
			FileInputStream fi = new FileInputStream(new File(filePath));
			ObjectInputStream oi = new ObjectInputStream(fi);

			ret = oi.readObject();
			oi.close();
			fi.close();

			return ret;

		} catch (FileNotFoundException e) {
			System.out.println("File not found");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error initializing input stream");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Object[] read(String filePath, int count) {
		Object[] ret = new Object[count];
		try {
			FileInputStream fi = new FileInputStream(new File(filePath));
			ObjectInputStream oi = new ObjectInputStream(fi);

			for (int i = 0; i < count; i++) {
				ret[i] = oi.readObject();
			}
			oi.close();
			fi.close();

			return ret;

		} catch (FileNotFoundException e) {
			System.out.println("File not found");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error initializing input stream");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
}
