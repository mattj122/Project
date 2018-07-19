package io;

import java.io.*;
import java.nio.file.Files;
import java.text.*;
import java.util.*;

public class CSVWriter {
	public String[] extens = { "m4v", "avi", "mp4", "mkv" };
	//public final String USER = "Matt";
	public final String USER = "vv3383my";
	public final String LIMITER = ",";
	public final static String SHOW = "Archer";
	public final static String OUT_FILE_NAME = "archer.csv";
	//Test environment
	//public final String SHOW_PATH = "C:\\Shows\\DUMMY\\";
	//Real path
	public final String SHOW_PATH = "C:\\Shows\\";
	public final String OUTPATH_SHOWS = "C:\\Users\\" + USER + "\\Documents\\GitHub\\Project\\test_env\\bin\\sh\\";
	public final String OUTPATH_MOVIES = "C:\\Movies\\bin\\";
	int totEp = 0, totSeas = 0;
	double totSize = 0.0;
	//Format of file size
	DecimalFormat df = new DecimalFormat("##.#");
	PrintWriter pw = null;
	
	StringBuilder sb = new StringBuilder();

	public static void main(String[] args) {
		try {
			CSVWriter csvw = new CSVWriter();
			csvw.read();
			csvw.write();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	private void write() {
		pw.write(sb.toString());
		pw.close();
	}

	private void read() throws IOException {
		String [][] arr = null;
		arr = getShowList();
		
		String colNames = "name,filename,filetype,season,seas_ep_num,tot_ep_num,length,size, path";
		String totSizeStr = "";
		if(totSize > 1024) {
			totSizeStr = df.format(totSize/1024.0) + "GB";
		}
		else {
			totSizeStr = df.format(totSize) + "MB";
		}
		String data = "tot_ep=" + totEp + ",tot_seas=" + totSeas + ",tot_length=" + "LENGTH" + ",tot_size=" + totSizeStr;
		sb.append(colNames + "\n");
		sb.append(data + "\n");
		for(int i = 0; i < arr.length; i++) {
			for(int j = 0; j < arr[0].length; j++) {
				sb.append(arr[i][j]);
				if(j+1 != arr[0].length) {
					sb.append(LIMITER);
				}
			}
			sb.append("\n");
		}
	}

	public CSVWriter() throws IOException {
		pw = new PrintWriter(new File(OUTPATH_SHOWS + OUT_FILE_NAME));
		
		
	}

	private String[][] getShowList() throws IOException {
		//selfexplanatory
		int seasNum = 0;
		//master array
		String[][] arr = null;
		//String for file size
		String sizeStr = "";
		//Episode number in the season
		int epNum;
		//Directory of show, duh
		File showDir = new File(SHOW_PATH + SHOW);
		//Directory of season, initialized to null in case of exceptions
		File seasDir = null;
		//used for directories to seasons
		String [] files = null;
		boolean seasonAdded = false;
		//Unnecessary but easier
		ArrayList<String[]> arrList = new ArrayList<String[]>();
		//episodes within a season
		String[] eps = null;
		//extension of file being read
		String extension = "";
		//Checks if show leads to directory
		if(showDir.isDirectory()) {
			//List all files in the directory of the show
			files = showDir.list();
			//Puts seasons in particular order and ethnically cleanses
			files = sortSeas(files);
			for(int i = 0; i < files.length; i++) {
				//Checks if directory contains the word season, and sets path
				if(files[i].contains("Season") && (seasDir = new File(SHOW_PATH + SHOW + "\\" 
						+ files[i])).isDirectory()) {
					seasonAdded = false;
					//Get current season based on the directory name, not dynamic but makes the naming uniform
					seasNum = Integer.parseInt(files[i].substring(files[i].length()-1));
					epNum = 0;
					//List the names of all files in current season
					eps = seasDir.list();
					//Sorts episodes in the proper order based on the naming scheme
					eps = sortEps(eps);
					for(int j = 0; j < eps.length; j++) {
						if(eps[j] != null) {
							if(!seasonAdded) {
								totSeas++;
								seasonAdded = true;
							}
							//Get filetype extension
							extension = eps[j].substring(eps[j].lastIndexOf(".") + 1);
							//Determines if the episodes are files that can be played by the selected player
							if(Arrays.toString(extens).contains(extension)) {
								totEp++;
								epNum++;
								//Get file size and convert to MB
								double size = (Files.size((new File(seasDir + "\\" + eps[j])).toPath()))/1024.0/1024.0;
								totSize += size;
								//Determines if in GB or MB
								if(size > 1024) {
									sizeStr = df.format(size/1024.0) + "GB";
								}
								else {
									sizeStr = df.format(size) + "MB";
								}
								//Name of the episode/video, name of the file after numbering (may remove later), Season the episode is in, 
										//File extension, episode number in the season, overall episode number length of the video, string for file size
								String[] temp = {"VIDEO NAME", eps[j].substring(eps[j].indexOf(" ") + 1, 
										eps[j].lastIndexOf(".")), extension, Integer.toString(seasNum), Integer.toString(epNum), 
										Integer.toString(totEp), "LENGTH", sizeStr, seasDir.getAbsolutePath() + "\\" + eps[j]};
								//Add to arraylist
								arrList.add(temp);
							}
						}
					}
				}
			}
			//Sets size for array, y value just needs to not be too short, 15 is good unless you need to get ridiculous
			arr = new String [totEp][15];
			//sets arr to the arraylist, arraylists are just easier to work with
			arrList.toArray(arr);
		}
		else {
			//Returns exception for show name not being directory
			throw new IOException("Not a directory. ");
		}
		return arr;
	}
	private String[] sortSeas(String[] seasons) throws IOException {
		boolean changed = true;
		int removed = 0;
		int num1 = 0, num2 = 0;
		while(changed) {
			changed = false;
			for(int i = 0; i < seasons.length; i++) {
				if(!seasons[i].contains("Season")) {
					for(int j = i; j < seasons.length-1; j++) {
						seasons[j] = seasons[j+1];
					}
					//used to short circuit and avoid comparing duplicates
					removed++;
				}
			}
			for(int i = 0; i < seasons.length - (1 + removed); i++) {
				//check for proper naming scheme
				try {
					num1 = Integer.parseInt(seasons[i].substring(seasons[i].length()-1));
				}
				catch(Exception e) {
					throw new IOException(seasons[i] + " does not follow naming procedure");
				}
				try {
					num2 = Integer.parseInt(seasons[i + 1].substring(seasons[i+1].length()-1));
				}
				catch(Exception e) {
					throw new IOException(seasons[i + 1] + " does not follow naming procedure");
				}
				if(num1 > num2) {
					//swap
					String temp = seasons[i];
					seasons[i] = seasons[i+1];
					seasons[i+1] = temp;
					//go through loop again
					changed = true;
				}
			}
		}
		return seasons;
	}
	private String[] sortEps(String[] eps) throws IOException {
		boolean changed = true;
		int epNum1 = 0, epNum2 = 0;
		while(changed) {
			changed = false;
			for(int i = 0; i < eps.length - 1; i++) {
				//Check that episode follows naming schema
				try {
					if(eps[i] != null) {
						epNum1 = Integer.parseInt(eps[i + 1].substring(2,4));
					}
				}
				catch(NumberFormatException | NullPointerException e) {
					System.out.println(eps[i] + " does not follow proper naming schema (index = "+ i +")");
					eps[i] = null;
				}
				try {
					if(eps[i+1] != null) {
						epNum2 = Integer.parseInt(eps[i + 1].substring(2,4));
					}
				}
				catch(NumberFormatException | NullPointerException e) {
					System.out.println(eps[i + 1] + " does not follow proper naming schema (index = "+ (i+1) +")");
					eps[i+1] = null;
				}
				if(epNum1 > epNum2) {
					//swap
					String temp = eps[i];
					eps[i] = eps[i+1];
					eps[i+1] = temp;
					changed = true;
				}
			}
		}
		return eps;
	}
}
