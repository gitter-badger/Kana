package com.zaheylu.kana.gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.zaheylu.kana.version.Version;
import com.zaheylu.snippets.CodeLibary;

public class KanaAbout extends JDialog {

	private final JPanel panel2 = new JPanel();

	public KanaAbout() {
		ImageIcon icon = new ImageIcon(KanaWindow.class.getResource("/res/kana_small.png"));
		setIconImage(icon.getImage());
		setBounds(100, 100, 525, 294);
		getContentPane().setLayout(null);
		{
			JPanel panel1 = new JPanel();
			panel1.setBounds(0, 0, 335, 222);
			getContentPane().add(panel1);
			setLocationRelativeTo(null);
			setResizable(false);
			panel1.setBorder(new EmptyBorder(5, 5, 5, 5));
			panel1.setLayout(new GridLayout(10, 1, 0, 3));
			setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			setModal(true);
			{
				JPanel panel = new JPanel();
				panel1.add(panel);
			}
			{
				JLabel lblProgram = new JLabel("Kana Trainer " + Version.getShortVersion() + " by Zaheylu ");
				panel1.add(lblProgram);
			}

			JLabel lblHttpGit = new JLabel("http://zaheylu.github.io/Kana/");
			lblHttpGit.setForeground(Color.blue);
			lblHttpGit.addMouseListener(new OpenHTTPMouseListener());
			panel1.add(lblHttpGit);

			JLabel lblHttpSetup = new JLabel("http://hafnehau.square7.ch/kana/");
			lblHttpSetup.setForeground(Color.blue);
			lblHttpSetup.addMouseListener(new OpenHTTPMouseListener());
			panel1.add(lblHttpSetup);
			{
				JPanel panel = new JPanel();
				panel1.add(panel);
			}
			{
				JLabel lblCopyright = new JLabel("Copyright (C) 2014 Simon Lange");
				panel1.add(lblCopyright);
			}
			{
				JPanel panel = new JPanel();
				panel1.add(panel);
			}
			{
				JLabel lblLicence1 = new JLabel("This is free software.");
				panel1.add(lblLicence1);
			}
			{
				JLabel lblLicence2 = new JLabel("Licenced under GNU GENERAL PUBLIC LICENSE Version 2");
				panel1.add(lblLicence2);
			}
			{
				JLabel lblLicence3 = new JLabel("http://www.gnu.org/licenses/old-licenses/gpl-2.0.txt");
				lblLicence3.setForeground(Color.blue);
				lblLicence3.addMouseListener(new OpenHTTPMouseListener());
				panel1.add(lblLicence3);
			}
			
		}
		panel2.setBounds(258, 0, 300, 222);
		panel2.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(panel2);
		panel2.setLayout(new GridLayout(0, 1, 0, 0));

		JLabel picLabel = new JLabel(new ImageIcon(KanaAbout.class.getResource("/res/{z}_small.png")));
		panel2.add(picLabel);

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 223, 509, 33);
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
			CodeLibary.openHTTP("http://www.hafnehau.square7.ch/kana/contact.html");
		}
	}
	private class OpenHTTPMouseListener extends MouseAdapter {
		@Override
		public void mousePressed(MouseEvent arg0) {
			CodeLibary.openHTTP(((JLabel)arg0.getSource()).getText());
		}
	}
}
