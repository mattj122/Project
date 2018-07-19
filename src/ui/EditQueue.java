package ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.AbstractListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;

public class EditQueue {

	protected JFrame frame;
	JButton btnClear = new JButton("Clear");
	//Dummy Queue
	//ArrayList<String> queueList = new ArrayList<String>();

	/**
	 * Create the application.
	 */
	public EditQueue(ArrayList<String> queueList, MovieLauncher mL) {
		initialize(queueList, mL);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(ArrayList<String> queueList, MovieLauncher mL) {
		frame = new JFrame();
		frame.setBounds(100, 100, 591, 374);
		frame.addWindowListener(new WindowAdapter(){
				public void windowClosing(WindowEvent e) {
					closeWindow(mL);
				}
			});
		
		JPanel panel = new JPanel();
		
		JList<String> movL = new JList<String>();
		
		updateQueue(movL, queueList);
		
		JScrollPane jspM = new JScrollPane();
		jspM.setViewportView(movL);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));
		panel_1.add(jspM);
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
						.addComponent(panel, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 414, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		panel.setLayout(new GridLayout(1, 5, 0, 0));
		
		JButton movUp = new JButton("");
		movUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!movL.isSelectionEmpty()) {
					if(movL.getSelectedIndex() > 0 && queueList.size() > 1) {
						int index = movL.getSelectedIndex();
						String temp = queueList.get(index);
						queueList.remove(index);
						queueList.add(index-1, temp);
						updateQueue(movL, queueList);
						movL.setSelectedIndex(index-1);;
					}
					else {
						System.out.println("Nope. ");
					}
				}
			}
		});
		movUp.setIcon(new ImageIcon(EditQueue.class.getResource("/javax/swing/plaf/metal/icons/sortUp.png")));
		panel.add(movUp);
		
		JButton movDwn = new JButton("");
		movDwn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!movL.isSelectionEmpty()) {
					if(movL.getSelectedIndex() < queueList.size()-1 && queueList.size() > 1) {
						int index = movL.getSelectedIndex();
						String temp = queueList.get(index);
						queueList.remove(index);
						queueList.add(index+1, temp);
						updateQueue(movL, queueList);
						movL.setSelectedIndex(index+1);;
					}
					else {
						System.out.println("Nope. ");
					}
				}
			}
		});
		movDwn.setIcon(new ImageIcon(EditQueue.class.getResource("/javax/swing/plaf/metal/icons/sortDown.png")));
		panel.add(movDwn);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!movL.isSelectionEmpty()) {
					if(queueList.size() > 1) {
						queueList.remove(movL.getSelectedIndex());
						updateQueue(movL, queueList);
					}
					else {
						queueList.clear();
						frame.dispose();
					}
				}
			}
		});
		panel.add(btnRemove);
		
		JButton btnRandomize = new JButton("Randomize");
		btnRandomize.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Collections.shuffle(queueList);
				updateQueue(movL, queueList);
			}
		});
		panel.add(btnRandomize);
		
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!queueList.isEmpty()) {
					queueList.clear();
					updateQueue(movL, queueList);
					closeWindow(mL);
				}
				updateQueue(movL, queueList);
			}
		});
		panel.add(btnClear);
		frame.getContentPane().setLayout(groupLayout);
	}
	@SuppressWarnings("serial")
	public void updateQueue(JList<String> queue, ArrayList<String> queueList) {
		if(queueList.isEmpty()) {
			btnClear.setEnabled(false);
		}
		else {
			btnClear.setEnabled(true);
		}
		String []temp = new String[queueList.size()];
		for(int i = 0; i < temp.length; i++) {
			temp[i] = queueList.get(i);
		}
		//queueList.toArray(temp);
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
	public void closeWindow(MovieLauncher mL) {
		mL.updateQueue();
		frame.dispose();
	}
}
