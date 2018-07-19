package io;

import java.io.*;
import java.util.*;

public class CSVReader {
	public final String USER = "vv3383my";
	public final String SHOW = "Archer";
	public String fileName;
	public final String PATH = "C:\\Users\\" + USER + "\\Documents\\GitHub\\Project\\test_env\\bin\\sh\\";
	public static ArrayList<ArrayList<String>> matrix;
	public static ArrayList<String> epNames = new ArrayList<String>();
	public final String LIMITER = ",";
	public void read() throws IOException {
		String line = null;
		String[] param = null;
		String[] data = null;
		String[] lineSplit = null;
		
		BufferedReader br = null; 
		br = new BufferedReader(new java.io.FileReader(PATH + fileName));
		param = br.readLine().split(LIMITER);
		data = br.readLine().split(LIMITER);
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
	public CSVReader(File f) throws FileNotFoundException {
		matrix = new ArrayList<ArrayList<String>>();
		fileName = f.getName();
	}
	public String[] getEpNames() {
		String[] temp = new String[epNames.size()]; 
		epNames.toArray(temp);
		return temp;
	}
	public String[][] getCSVMatrix(){
		String[][] temp = new String[matrix.get(0).size()][matrix.size()]; 
		for(int i = 0; i < matrix.size(); i++) {
			for(int j = 0; j < matrix.get(0).size(); j++) {
				temp[j][i] = matrix.get(i).get(j);
			}
		}
		return temp;
	}
	@SuppressWarnings("unused")
	private String [] getYearArray() {
		return getCSVMatrix()[5];
	}
	public String [] getExtensArray() {
		return getCSVMatrix()[2];
	}
	public String [] getPathArray() {
		return getCSVMatrix()[8];
	}
	public File [] getFileArray() {
		File[] out = new File[getCSVMatrix()[8].length];
		int i = 0;
		for(String s : getCSVMatrix()[8]) {
			out[i] = new File(s);
			i++;
		}
		return out;
	}
	public String [] getNameArray() {
		return getCSVMatrix()[1];
	}
	public String [] getSizeArray() {
		return getCSVMatrix()[7];
	}
	public String [] getLengthArray() {
		return getCSVMatrix()[6];
	}
}
