package io;

import java.io.File;
import java.util.ArrayList;

public class FileReader {
	final static String VLC_SRC = "C:\\Program Files (x86)\\VideoLAN\\VLC\\vlc.exe";
	public static File dirMovie = new File("C:\\Movies\\");
	public static File dirShow = new File("C:\\Shows\\");
	static ArrayList<File> list = new ArrayList<File>();
	public static void main(String[] args) throws Exception {
		loadMovies(dirMovie);
		for(int i = 0; i < getList().length; i++) {
			//System.out.println(i + ": " + getList()[i]);
		}
	}
	public FileReader() {
		
	}
	public static String getDir() {
		return VLC_SRC;
	}
	public static File[] getList() {
		File[] listTemp = new File[list.size()];
		list.toArray(listTemp);
		return listTemp;
	}
	public static void loadMovies(File f) throws Exception {
		System.out.println("File is " + f.getAbsolutePath());
		if(f.isDirectory()) {
			System.out.println("true");
			for(int i = 0; i < f.listFiles().length; i++) {
				try {
					String exten = f.listFiles()[i].getName().substring(f.listFiles()[i].getName().lastIndexOf('.'), f.listFiles()[i].getName().length());
					if(exten.equalsIgnoreCase(".mp4") || exten.equalsIgnoreCase(".mkv") || exten.equalsIgnoreCase(".avi") || exten.equalsIgnoreCase(".m4v")) {
						System.out.println("Loading " + f.listFiles()[i].getName() + "... ");
						list.add(f.listFiles()[i]);
					}
					else {
						System.out.println("Invalid file: " + f.listFiles()[i].getName());
					}
				}
				catch(Exception e) {
					System.out.println("Invalid file: " + f.listFiles()[i].getName());
					if((f.listFiles()[i]).isDirectory()) {
						String temp = f.listFiles()[i].getAbsolutePath() + "\\";
						System.out.println("Loading from " + temp);
						loadMovies(new File(temp));
					}
				}
			}
		}
		else {
			throw new Exception("Not a directory. ");
		}
	}
	public static void loadShows() throws Exception {
		if(dirShow.isDirectory()) {
			for(int i = 0; i < dirShow.listFiles().length; i++) {
				String exten = dirShow.listFiles()[i].getName().substring(dirShow.listFiles()[i].getName().lastIndexOf('.'), dirShow.listFiles()[i].getName().length());
				if(exten.equalsIgnoreCase(".mp4") || exten.equalsIgnoreCase(".mkv") || exten.equalsIgnoreCase(".avi")) {
					System.out.println("Loading " + dirShow.listFiles()[i].getName() + "... ");
				}
			}
		}
		else {
			throw new Exception("Not a directory. ");
		}
	}
	public static String getMovDir() {
		return dirMovie.getAbsolutePath();
	}
}
