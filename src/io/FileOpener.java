package io;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;

public class FileOpener {
	//static String[] f = {"C:\\Program Files (x86)\\VideoLAN\\VLC\\vlc.exe", "C:\\Movies\\Hot Fuzz.mp4", "C:\\Movies\\Alien.mp4"};
	public static void main(String[] args) throws IOException {
		File f = new File("C:\\Movies\\Hot Fuzz.mp4");
		BasicFileAttributes bfa = Files.readAttributes(f.toPath(), BasicFileAttributes.class);
		System.out.println(bfa.creationTime());
		System.out.println(bfa.size());
		System.out.println(bfa.lastAccessTime());
		System.out.println(bfa.lastModifiedTime());
	}
	public FileOpener() {
		
	}
}
