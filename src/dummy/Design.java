package dummy;

import javax.swing.border.EtchedBorder;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;
import java.awt.event.ActionEvent;

public class Design {

	private JFrame frmLauncher;
	private JFileChooser jfc;
	private String srcDir = "C:\\Users\\vv3383my\\Documents\\GitHub\\Project\\test_env\\bin\\";
	protected Properties prop = new Properties();
	
	protected JMenu mnShows = new JMenu("Shows");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					Design window = new Design();
					window.frmLauncher.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Design() {
		initialize();
		loadFiles();
	}

	protected void loadFiles() {
		System.out.println(srcDir + "\\prop.ini");
		//Loading properties for window
		try {
			FileInputStream in = new FileInputStream(new File(srcDir + "prop.ini"));
			prop.load(in);
			in.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println(prop.toString());
	}
	protected void saveProp(Properties p) {
		try {
			FileOutputStream out = new FileOutputStream(new File(srcDir + "prop.ini"));
			p.store(out, "--No Comment--");
			out.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLauncher = new JFrame();
		frmLauncher.setFont(new Font("Verdana", Font.PLAIN, 12));
		frmLauncher.setTitle("Launcher\r\n");
		frmLauncher.setBounds(100, 100, 706, 494);
		frmLauncher.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frmLauncher.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmOpen = new JMenuItem("Open");
		mnFile.add(mntmOpen);
		
		JMenuItem mntmSave = new JMenuItem("Save");
		mnFile.add(mntmSave);
		
		JMenuItem mntmSaveAs = new JMenuItem("Save as...");
		mnFile.add(mntmSaveAs);
		
		JSeparator separator_1 = new JSeparator();
		mnFile.add(separator_1);
		
		JMenuItem mntmProperties = new JMenuItem("Properties");
		mntmProperties.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								PropertiesEditor pe = new PropertiesEditor(Design.this, prop);
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
		});
		mnFile.add(mntmProperties);
		
		JSeparator separator = new JSeparator();
		mnFile.add(separator);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mnFile.add(mntmExit);
		
		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);
		
		JMenu mnViewList = new JMenu("View List");
		mnEdit.add(mnViewList);
		
		mnViewList.add(mnShows);
		
		loadShowButtons(mnShows);
		
		JMenuItem mntmMovies = new JMenuItem("Movies");
		mnViewList.add(mntmMovies);
		
		JMenu mnSeries = new JMenu("Series");
		mnViewList.add(mnSeries);
		
		JMenu mnQueue = new JMenu("Queue");
		menuBar.add(mnQueue);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		frmLauncher.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(10, 11, 194, 350);
		frmLauncher.getContentPane().add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_1.setBounds(486, 11, 194, 350);
		frmLauncher.getContentPane().add(panel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_2.setBounds(214, 36, 262, 131);
		frmLauncher.getContentPane().add(panel_2);
		panel_2.setLayout(new GridLayout(3, 1, 2, 2));
		
		JLabel lblLength = new JLabel("  Length: ");
		lblLength.setFont(new Font("Verdana", Font.PLAIN, 11));
		panel_2.add(lblLength);
		
		JLabel lblLength_1 = new JLabel("LENGTH  ");
		lblLength_1.setFont(new Font("Verdana", Font.PLAIN, 11));
		lblLength_1.setHorizontalAlignment(SwingConstants.TRAILING);
		panel_2.add(lblLength_1);
		
		JLabel lblSize = new JLabel("  Size: ");
		lblSize.setFont(new Font("Verdana", Font.PLAIN, 11));
		panel_2.add(lblSize);
		
		JLabel lblSize_1 = new JLabel("SIZE  ");
		lblSize_1.setFont(new Font("Verdana", Font.PLAIN, 11));
		lblSize_1.setHorizontalAlignment(SwingConstants.TRAILING);
		panel_2.add(lblSize_1);
		
		JLabel lblSystemTimeAfter = new JLabel("  Time after: ");
		lblSystemTimeAfter.setFont(new Font("Verdana", Font.PLAIN, 11));
		panel_2.add(lblSystemTimeAfter);
		
		JLabel lblTimeAfter = new JLabel("TIME AFTER  ");
		lblTimeAfter.setFont(new Font("Verdana", Font.PLAIN, 11));
		lblTimeAfter.setHorizontalAlignment(SwingConstants.TRAILING);
		panel_2.add(lblTimeAfter);
		
		JLabel lblQueueData = new JLabel("Queue Data");
		lblQueueData.setFont(new Font("Verdana", Font.PLAIN, 11));
		lblQueueData.setHorizontalAlignment(SwingConstants.CENTER);
		lblQueueData.setBounds(214, 11, 262, 14);
		frmLauncher.getContentPane().add(lblQueueData);
		
		JLabel label = new JLabel("Selected Data");
		label.setFont(new Font("Verdana", Font.PLAIN, 11));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(214, 178, 262, 14);
		frmLauncher.getContentPane().add(label);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_3.setBounds(214, 203, 262, 131);
		frmLauncher.getContentPane().add(panel_3);
		panel_3.setLayout(new GridLayout(3, 2, 0, 0));
		
		JLabel label_1 = new JLabel("  Length: ");
		label_1.setFont(new Font("Verdana", Font.PLAIN, 11));
		panel_3.add(label_1);
		
		JLabel label_4 = new JLabel("LENGTH  ");
		label_4.setFont(new Font("Verdana", Font.PLAIN, 11));
		label_4.setHorizontalAlignment(SwingConstants.TRAILING);
		panel_3.add(label_4);
		
		JLabel label_5 = new JLabel("  Size: ");
		label_5.setFont(new Font("Verdana", Font.PLAIN, 11));
		panel_3.add(label_5);
		
		JLabel label_3 = new JLabel("SIZE  ");
		label_3.setFont(new Font("Verdana", Font.PLAIN, 11));
		label_3.setHorizontalAlignment(SwingConstants.TRAILING);
		panel_3.add(label_3);
		
		JLabel lblTimeAfter_1 = new JLabel("  Time after: ");
		lblTimeAfter_1.setFont(new Font("Verdana", Font.PLAIN, 11));
		panel_3.add(lblTimeAfter_1);
		
		JLabel label_2 = new JLabel("TIME AFTER  ");
		label_2.setFont(new Font("Verdana", Font.PLAIN, 11));
		label_2.setHorizontalAlignment(SwingConstants.TRAILING);
		panel_3.add(label_2);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(10, 372, 670, 45);
		frmLauncher.getContentPane().add(panel_4);
		panel_4.setLayout(null);
		
		JButton btnPlayQueue = new JButton("Play Queue");
		btnPlayQueue.setBounds(10, 11, 89, 23);
		panel_4.add(btnPlayQueue);
	}

	private void loadShowButtons(JMenu parent) {
		//Read CSV files
		//Parse show name from path
		//create JMenuItem from show name
		//add action listener
			//set JList and src folder string
		//add to parent
		//do while CSV files exist
	}
}
