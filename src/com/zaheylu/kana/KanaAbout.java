package com.zaheylu.kana;

import static com.zaheylu.CodeLibary.*;

import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class KanaAbout extends JDialog {

	private final JPanel panel2 = new JPanel();



	public KanaAbout() {
		ImageIcon icon = new ImageIcon(KanaWindow.class.getResource("/res/kana_small.png"));
		setIconImage(icon.getImage());
		setBounds(100, 100, 372, 300);
		getContentPane().setLayout(null);
		{
			JPanel panel1 = new JPanel();
			panel1.setBounds(0, 0, 131, 229);
			getContentPane().add(panel1);
			setLocationRelativeTo(null);
			panel1.setBorder(new EmptyBorder(5, 5, 5, 5));
			panel1.setLayout(new GridLayout(9, 1, 0, 10));
			setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			setModal(true);
			JLabel label = new JLabel("");
			panel1.add(label);
			{
				JLabel lblKanaTrainer = new JLabel("Kana Trainer");
				panel1.add(lblKanaTrainer);
			}
			{
				JLabel lblVersion = new JLabel("Version " + Version.getShortVersion());
				panel1.add(lblVersion);
			}
			{
				JLabel lblKanaTrainerBy = new JLabel("Written By Zaheylu");
				panel1.add(lblKanaTrainerBy);
			}

			JLabel lblSnailsBySkype = new JLabel("Snails By Skype");
			panel1.add(lblSnailsBySkype);

			JLabel lblBeatsByDre = new JLabel("Beats By Dre");
			panel1.add(lblBeatsByDre);
		}
		panel2.setBounds(135, 0, 221, 229);
		panel2.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(panel2);
		panel2.setLayout(new GridLayout(0, 1, 0, 0));

		JLabel picLabel = new JLabel(new ImageIcon(KanaAbout.class.getResource("/res/{z}_small.png")));
		panel2.add(picLabel);

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 229, 356, 33);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new OkButtonActionListener());
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton contactButton = new JButton("Contact");
				contactButton.addActionListener(new ContactButtonActionListener());
				contactButton.setActionCommand("Cancel");
				buttonPane.add(contactButton);
			}
		}
		setVisible(true);
	}

	private class OkButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			dispose();
		}
	}

	private class ContactButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent evt) {
			if (Desktop.isDesktopSupported()) {
				try {
					Desktop.getDesktop().browse(new URI("http://www.hafnehau.square7.ch/kana/contact.html"));
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else showmessage("Please visit http://www.hafnehau.square7.ch/kana/contact.html");
		}
	}
}
