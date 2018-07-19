package io;

import java.io.*;

public class Tester {
	static String [] epNames;
	static String [] extensions;
	static String [] year;
	static File [] files;
	static String filePath = "C:\\Users\\vv3383my\\Documents\\GitHub\\Project\\test_env\\bin\\sh\\archer.csv";
	static CSVReader csvr;
	public static void main(String[] args) {
		try {
			csvr = new CSVReader(new File(filePath));
			csvr.read();
		}
		catch (IOException e) {
			System.out.println(e.getMessage());
		}
		epNames = csvr.getEpNames();
		for(String s : epNames) {
			System.out.println(s);
		}
		files = csvr.getFileArray();
		for(int i = 0; i < files.length; i++) {
			System.out.println(files[i].getName());
		}
	}

}
