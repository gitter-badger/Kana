package com.zaheylu.kana.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.zaheylu.kana.version.Version;
import com.zaheylu.snippets.CodeLibary;

public class KanaAbout extends JDialog {

	private  JPanel panel2;
	private  JPanel panel1;
	private  JPanel buttonPane;

	public KanaAbout(JFrame parent) {
		ImageIcon icon = new ImageIcon(KanaWindow.class.getResource("/res/kana_small.png"));
		setIconImage(icon.getImage());
		getContentPane().setLayout(null);

		getContentPane().setPreferredSize(new Dimension(525, 294));
		pack();
		setLocationRelativeTo(parent);
		setResizable(false);
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		int width = getContentPane().getWidth();
		int height = getContentPane().getHeight();

		panel1 = new JPanel();
		panel1.setBounds(0, 0, (int) Math.round(width * 0.65), height - 30);

		panel2 = new JPanel();
		panel2.setBounds((int) Math.round(width * 0.65), 0, (int) Math.round(width * 0.35), height - 30);
		
		buttonPane = new JPanel();
		buttonPane.setBounds(0, height - 30, width , 30);

		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		panel2.setLayout(new GridLayout(0, 1, 0, 0));
		panel1.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel1.setLayout(new GridLayout(10, 1, 0, 3));
		
		panel1.add(new JPanel());
		JLabel lblProgram = new JLabel("Kana Trainer " + Version.getShortVersion() + " by Zaheylu ");
		panel1.add(lblProgram);
		JLabel lblHttpGit = new JLabel("http://zaheylu.github.io/Kana/");
		lblHttpGit.setForeground(Color.blue);
		lblHttpGit.addMouseListener(new OpenHTTPMouseListener());
		panel1.add(lblHttpGit);
		JLabel lblHttpSetup = new JLabel("http://hafnehau.square7.ch/kana/");
		lblHttpSetup.setForeground(Color.blue);
		lblHttpSetup.addMouseListener(new OpenHTTPMouseListener());
		panel1.add(lblHttpSetup);
		panel1.add(new JPanel());
		JLabel lblCopyright = new JLabel("Copyright (C) 2014 Simon Lange");
		panel1.add(lblCopyright);
		panel1.add(new JPanel());
		JLabel lblLicence1 = new JLabel("This is free software.");
		panel1.add(lblLicence1);
		JLabel lblLicence2 = new JLabel("Licenced under GNU GENERAL PUBLIC LICENSE Version 2");
		panel1.add(lblLicence2);
		JLabel lblLicence3 = new JLabel("http://www.gnu.org/licenses/old-licenses/gpl-2.0.txt");
		lblLicence3.setForeground(Color.blue);
		lblLicence3.addMouseListener(new OpenHTTPMouseListener());
		panel1.add(lblLicence3);

		JLabel picLabel = new JLabel(new ImageIcon(KanaAbout.class.getResource("/res/{z}_small.png")));
		panel2.add(picLabel);

		JButton okButton = new JButton("OK");
		okButton.addActionListener(new OkButtonActionListener());
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);

		JButton contactButton = new JButton("Contact");
		contactButton.addActionListener(new ContactButtonActionListener());
		buttonPane.add(contactButton);

		getContentPane().add(panel1);
		getContentPane().add(panel2);
		getContentPane().add(buttonPane);
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
			CodeLibary.openHTTP(((JLabel) arg0.getSource()).getText());
		}
	}
}
