package ui;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;

import io.CSVReader;
import io.FileReader;

public class MovieLauncher {

	private JFrame frame;
	private ArrayList<String> queueList = new ArrayList<String>();
	private JList<String> queue = new JList<String>();
	JButton btnPlayQueue = new JButton("Play Queue");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MovieLauncher window = new MovieLauncher();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws Exception 
	 */
	public MovieLauncher() throws Exception {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws Exception 
	 */
	@SuppressWarnings("serial")
	private void initialize() throws Exception {
		frame = new JFrame();
		frame.setBounds(100, 100, 750, 433);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JList<String> movList = new JList<String>();
		CSVReader csvr = new CSVReader(new File("C:\\Users\\vv3383my\\Documents\\GitHub\\Project\\test_env\\bin\\sh\\archer.csv"));
		
		csvr.read();
		String []temp = csvr.getEpNames();
		movList.setModel(new AbstractListModel<String>() {
			String[] values = temp;
			public int getSize() {
				return values.length;
			}
			public String getElementAt(int index) {
				return values[index];
			}
		});
		
		JScrollPane jspQ = new JScrollPane();
		jspQ.setViewportView(queue);
		
		JScrollPane jspM = new JScrollPane();
		jspM.setViewportView(movList);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmSaveQueue = new JMenuItem("Save Queue...");
		mnFile.add(mntmSaveQueue);
		
		JMenuItem mntmLoadQueue = new JMenuItem("Load Queue...");
		mnFile.add(mntmLoadQueue);
		
		JMenuItem mntmQuit = new JMenuItem("Quit");
		mnFile.add(mntmQuit);
		
		JMenu mnQueue = new JMenu("Queue");
		menuBar.add(mnQueue);
		
		JMenuItem mntmEdit = new JMenuItem("Edit...");
		mnQueue.add(mntmEdit);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {400, 400};
		gridBagLayout.rowHeights = new int[] {328, 33};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0};
		gridBagLayout.rowWeights = new double[]{1.0, 1.0};
		frame.getContentPane().setLayout(gridBagLayout);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		panel.add(jspQ);
		frame.getContentPane().add(panel, gbc_panel);
		
		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 0);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 0;
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));
		panel_1.add(jspM);
		frame.getContentPane().add(panel_1, gbc_panel_1);
		
		JPanel panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_2.gridwidth = 2;
		gbc_panel_2.insets = new Insets(0, 0, 0, 5);
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 1;
		frame.getContentPane().add(panel_2, gbc_panel_2);
		panel_2.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		JButton btnAddToQueue = new JButton("Add to Queue");
		btnAddToQueue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				queueList.add(temp[movList.getSelectedIndex()]);
				updateQueue();
			}
		});
		
		JButton btnAddAll = new JButton("Add All");
		btnAddAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				queueList = new ArrayList<String>();
				for(String s : temp) {
					queueList.add(s);
				}
				updateQueue();
			}
		});
		panel_2.add(btnAddAll);
		panel_2.add(btnAddToQueue);
		
		JButton btnPlaySelected = new JButton("Play Selected");
		btnPlaySelected.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] f = new String[2];
				f[0] = FileReader.getDir();
				f[1] = csvr.getPathArray()[movList.getSelectedIndex()];
				ProcessBuilder pb = new ProcessBuilder(f);
				try {
					@SuppressWarnings("unused")
					Process start = pb.start();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		panel_2.add(btnPlaySelected);
		
		JButton btnEditQueue = new JButton("Edit Queue");
		btnEditQueue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					EditQueue window = new EditQueue(queueList, MovieLauncher.this);
					window.frame.setVisible(true);
					
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		panel_2.add(btnEditQueue);
		
		btnPlayQueue.setEnabled(false);
		btnPlayQueue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] f = new String[queueList.size()+1];
				f[0] = FileReader.getDir();
				int temp = 0;
				int index = 0;
				for(int i = 1; i < queueList.size()+1; i++) {
					for(int j = 0; j < csvr.getCSVMatrix()[0].length-1; j++) {
						if(csvr.getCSVMatrix()[1][j].equals(queueList.get(index))) {
							temp = j;
							index++;
						}
					}
					index = 0;
					f[i] = csvr.getCSVMatrix()[8][temp];
				}
				ProcessBuilder pb = new ProcessBuilder(f);
				try {
					@SuppressWarnings("unused")
					Process start = pb.start();
				} catch (Exception e1) {
					System.out.println("Nope. Sry.");
				}
			}
		});
		panel_2.add(btnPlayQueue);
	}
	
	@SuppressWarnings("serial")
	public void updateQueue() {
		if(queueList.isEmpty()) {
			btnPlayQueue.setEnabled(false);
		}
		else {
			btnPlayQueue.setEnabled(true);
		}
		String []temp = new String[queueList.size()];
		for(int i = 0; i < temp.length; i++) {
			temp[i] = queueList.get(i);
		}
		queue.setModel(new AbstractListModel<String>() {
			String[] values = temp;
			public int getSize() {
				return values.length;
			}
			public String getElementAt(int index) {
				return values[index];
			}
		});
	}
}
