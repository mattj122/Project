package dummy;

import java.awt.EventQueue;
import java.util.*;
import javax.swing.*;

import ui.MovieLauncher;
import java.awt.BorderLayout;
import javax.swing.border.EtchedBorder;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PropertiesEditor {
	protected JFrame frame;
	
	JRadioButton rdbtnVlc = new JRadioButton("VLC");
	JRadioButton rdbtnWindowsMediaPlayer = new JRadioButton("Windows Media");
	JRadioButton rdbtnQuicktime = new JRadioButton("Quicktime");
	JRadioButton rdbtnOther = new JRadioButton("Other...");
	
	JCheckBox chckbxAmazonPrime = new JCheckBox("Amazon Prime");
	JCheckBox chckbxHulu = new JCheckBox("Hulu");
	JCheckBox chckbxNetflix = new JCheckBox("Netflix");
	private final ButtonGroup playerBtnGrp = new ButtonGroup();
	private final JPanel dirPanel = new JPanel();
	private JTextField dirMovies = new JTextField();
	private JTextField dirShows = new JTextField();
	private JTextField dirData = new JTextField();
	private final JButton btnCancel = new JButton("Cancel");
	private final JButton btnOk = new JButton("Ok");
	private final JButton btnApply = new JButton("Apply");
	
	private Design d;
	private Properties p;
	
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						PropertiesEditor pe = new PropertiesEditor(new Design(), new Properties());
						pe.frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
		catch(Exception e) {
			
		}
	}
	public PropertiesEditor(Design d, Properties p) {
		this.d = d;
		this.p = p;
		initValues(p);
		initialize();
	}
	private void initialize() {
		frame = new JFrame("Properies Editor");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(439, 397);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
		
		JPanel playerPanel = new JPanel();
		playerPanel.setBorder(BorderFactory.createTitledBorder(new EtchedBorder(), "Select Player Type"));
		playerPanel.setBounds(10, 11, 413, 71);
		frame.getContentPane().add(playerPanel);
		playerPanel.setLayout(new GridLayout(1, 0, 0, 0));
		
		playerBtnGrp.add(rdbtnVlc);
		playerBtnGrp.add(rdbtnWindowsMediaPlayer);
		playerBtnGrp.add(rdbtnQuicktime);
		playerBtnGrp.add(rdbtnOther);
		
		playerPanel.add(rdbtnVlc);
		playerPanel.add(rdbtnWindowsMediaPlayer);
		playerPanel.add(rdbtnQuicktime);
		playerPanel.add(rdbtnOther);
		
		JPanel streamPanel = new JPanel();
		streamPanel.setBorder(BorderFactory.createTitledBorder(new EtchedBorder(), "Streaming Services"));
		streamPanel.setBounds(10, 93, 413, 71);
		frame.getContentPane().add(streamPanel);
		streamPanel.setLayout(new GridLayout(1, 0, 0, 0));
		
		streamPanel.add(chckbxAmazonPrime);
		streamPanel.add(chckbxHulu);
		streamPanel.add(chckbxNetflix);
		dirPanel.setBorder(BorderFactory.createTitledBorder(new EtchedBorder(), "Directories"));
		dirPanel.setBounds(10, 172, 413, 111);
		
		frame.getContentPane().add(dirPanel);
		dirPanel.setLayout(null);
		
		JLabel lblMovieDirectory = new JLabel("Movie Directory: ");
		lblMovieDirectory.setBounds(10, 29, 82, 14);
		dirPanel.add(lblMovieDirectory);
		
		JLabel lblShowDirectory = new JLabel("Show Directory: ");
		lblShowDirectory.setBounds(10, 54, 80, 14);
		dirPanel.add(lblShowDirectory);
		
		JLabel lblDataDirectory = new JLabel("Data Directory: ");
		lblDataDirectory.setBounds(10, 79, 77, 14);
		dirPanel.add(lblDataDirectory);
		
		JButton findDirMov = new JButton("...");
		findDirMov.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//File Chooser
			}
		});
		findDirMov.setBounds(375, 26, 28, 20);
		dirPanel.add(findDirMov);
		
		JButton findDirShow = new JButton("...");
		findDirShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//File Chooser
			}
		});
		findDirShow.setBounds(375, 51, 28, 20);
		dirPanel.add(findDirShow);
		
		JButton findDirData = new JButton("...");
		findDirData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//File Chooser
			}
		});
		findDirData.setBounds(375, 76, 28, 20);
		dirPanel.add(findDirData);
		
		dirMovies.setBounds(102, 26, 263, 20);
		dirPanel.add(dirMovies);
		dirMovies.setColumns(10);
		
		dirShows.setBounds(102, 51, 263, 20);
		dirPanel.add(dirShows);
		dirShows.setColumns(10);
		
		dirData.setBounds(102, 76, 263, 20);
		dirPanel.add(dirData);
		dirData.setColumns(10);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnCancel.setBounds(334, 334, 89, 23);
		
		frame.getContentPane().add(btnCancel);
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				save();
				d.loadFiles();
				frame.dispose();
			}
		});
		btnOk.setBounds(235, 334, 89, 23);
		
		frame.getContentPane().add(btnOk);
		btnApply.setEnabled(false);
		btnApply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				save();
				btnApply.setEnabled(false);
			}
		});
		btnApply.setBounds(136, 334, 89, 23);
		
		frame.getContentPane().add(btnApply);
	}
	private void initValues(Properties p) {
		int setter = 0;
		for(int i = 0; i < playerBtnGrp.getButtonCount()-1; i++) {
			if(p.getProperty("plyr" + i).equals("true")) {
				setter = i;
			}
		}
		playerBtnGrp.clearSelection();
		switch(setter) {
		case 0: rdbtnVlc.setSelected(true);
		
		break;
		case 1: rdbtnWindowsMediaPlayer.setSelected(true);
		this.p.setProperty("plyr1", "false");
		this.p.setProperty("plyr2", "true");
		this.p.setProperty("plyr3", "false");
		this.p.setProperty("plyr4", "false");
		this.p.setProperty("exec_player", "wmp");
		break;
		case 2: rdbtnQuicktime.setSelected(true);
		this.p.setProperty("plyr1", "false");
		this.p.setProperty("plyr2", "false");
		this.p.setProperty("plyr3", "true");
		this.p.setProperty("plyr4", "false");
		this.p.setProperty("exec_player", "quicktime");
		break;
		case 3: rdbtnOther.setSelected(true);
		this.p.setProperty("plyr1", "false");
		this.p.setProperty("plyr2", "false");
		this.p.setProperty("plyr3", "false");
		this.p.setProperty("plyr4", "true");
		this.p.setProperty("exec_player", "other");
		break;
		default: playerBtnGrp.clearSelection();
		}
		//Streaming service options
		chckbxAmazonPrime.setSelected(Boolean.parseBoolean(p.getProperty("prime", "false")));
		chckbxHulu.setSelected(Boolean.parseBoolean(p.getProperty("hulu", "false")));
		chckbxNetflix.setSelected(Boolean.parseBoolean(p.getProperty("netflix", "false")));
		//Directory defaults
		dirMovies.setText(p.getProperty("movie_dir"));
		dirShows.setText(p.getProperty("show_dir"));
		dirData.setText(p.getProperty("data_dir"));
	}
	private void save() {
		this.p.setProperty("plyr1", Boolean.toString(playerBtnGrp.isSelected(rdbtnVlc.getModel())));
		this.p.setProperty("plyr2", Boolean.toString(playerBtnGrp.isSelected(rdbtnWindowsMediaPlayer.getModel())));
		this.p.setProperty("plyr3", Boolean.toString(playerBtnGrp.isSelected(rdbtnQuicktime.getModel())));
		this.p.setProperty("plyr4", Boolean.toString(playerBtnGrp.isSelected(rdbtnOther.getModel())));
		p.setProperty("hulu", Boolean.toString(chckbxHulu.isSelected()));
		p.setProperty("netflix", Boolean.toString(chckbxNetflix.isSelected()));
		p.setProperty("prime", Boolean.toString(chckbxAmazonPrime.isSelected()));
		p.setProperty("movie_dir", dirMovies.getText());
		p.setProperty("data_dir", dirData.getText());
		p.setProperty("show_dir", dirShows.getText());
		d.saveProp(p);
		System.out.println("Saved!");
	}
}
class Handler implements ActionListener{
	public void actionPerformed(ActionEvent arg0) {
		
	}
}
