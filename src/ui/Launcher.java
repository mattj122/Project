package ui;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.net.URI;
import java.net.URL;
import java.awt.event.ActionEvent;
import javax.swing.ButtonGroup;

public class Launcher extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	public static void main(String[] args) {
		try {
			Launcher dialog = new Launcher();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Launcher() {
		JRadioButton showsButton = new JRadioButton("Shows");
		buttonGroup.add(showsButton);
		JRadioButton moviesButton = new JRadioButton("Movies");
		buttonGroup.add(moviesButton);
		JRadioButton netflixButton = new JRadioButton("Netflix");
		buttonGroup.add(netflixButton);
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(1, 0, 0, 0));
		{
			showsButton.setSelected(true);
			contentPanel.add(showsButton);
		}
		{
			contentPanel.add(moviesButton);
		}
		{
			contentPanel.add(netflixButton);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if(showsButton.isSelected()) {
							
							dispose();
						}
						else if(moviesButton.isSelected()) {
							
							dispose();
						}
						else if(netflixButton.isSelected()) {
							try {
								Desktop desktop = Desktop.getDesktop();
						        desktop.browse(new URI("http://www.netflix.com"));
						    } catch (Exception e) {
						        e.printStackTrace();
						    }
							dispose();
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
