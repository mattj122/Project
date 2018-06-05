package io;

import java.io.*;
import java.util.*;

public class CSVReader {
	public final String USER = "vv3383my";
	public final String SHOW = "Archer";
	public final String FILE_NAME = "archer.csv";
	public final String PATH = "C:\\Users\\" + USER + "\\Documents\\GitHub\\Project\\test_env\\bin\\sh\\";
	public static ArrayList<ArrayList<String>> matrix;
	public static ArrayList<String> epNames = new ArrayList<String>();
	public final String LIMITER = ",";
	public static void main(String[] args) {
		String[][] temp = null;
		CSVReader c = null;
		try {
			c = new CSVReader();
			c.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
		temp = getCSVMatrix();
		for(int i = 0; i < temp.length; i++) {
			System.out.print("||");
			for(int j = 0; j < temp[0].length; j++) {
				if(temp[i][j].length() > 14) {
					System.out.print(temp[i][j].substring(0, 14) + "\t|");
				}
				else {
					System.out.print(temp[i][j] + "\t|");
				}
			}
			System.out.println("|");
		}
		for(int i = 0; i < getEpNames().length; i++) {
			//System.out.println(getEpNames()[i]);
		}
	}
	public void read() throws IOException {
		String line = null;
		String[] param = null;
		String[] data = null;
		String[] lineSplit = null;
		
		BufferedReader br = null; 
		br = new BufferedReader(new java.io.FileReader(PATH + FILE_NAME));
		param = br.readLine().split(LIMITER);
		data = br.readLine().split(LIMITER);
		for(String s : data) {
			System.out.print("|" + s + "\t");
		}
		System.out.println();
		int count = 0;
		while((line = br.readLine()) != null) {
			lineSplit = line.split(LIMITER);
			count++;
			matrix.add(new ArrayList<String>());
			for(int i = 0; i < lineSplit.length; i++) {
				matrix.get(count-1).add(lineSplit[i]);
			}
			epNames.add(lineSplit[1]);
		}
		
		br.close();
	}
	public CSVReader() throws FileNotFoundException {
		matrix = new ArrayList<ArrayList<String>>();
		
	}
	public static String[] getEpNames() {
		String[] temp = new String[epNames.size()]; 
		epNames.toArray(temp);
		return temp;
	}
	public static String[][] getCSVMatrix(){
		String[][] temp = new String[matrix.size()][matrix.get(0).size()]; 
		for(int i = 0; i < matrix.size(); i++) {
			for(int j = 0; j < matrix.get(0).size(); j++) {
				temp[i][j] = matrix.get(i).get(j);
			}
		}
		return temp;
	}
}
