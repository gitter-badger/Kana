package com.zaheylu;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class JCheckDialog extends JDialog {

	private JCheckBox[] boxes;
	private ArrayList<Integer> choices;
	private JButton okBtn;

	public JCheckDialog(JFrame parent, String[] strs, int columns) {
		int index = 0;
		int rows = (strs.length / columns) + 3;
		if ((double) strs.length / (double) columns != strs.length / columns) rows++;
		if (columns == 1)
		rows += 2;
		this.getContentPane().setLayout(new GridLayout(rows, columns));
		this.setSize(columns * 200, rows * 30 + 30);
		this.setLocationRelativeTo(parent);
		this.setModal(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		boxes = new JCheckBox[strs.length];

		for (int n = 0; n < strs.length; n++) {
			boxes[n] = new JCheckBox(strs[n]);
			getContentPane().add(boxes[n]);
			index++;
		}
		while (index % columns != 0) {
			getContentPane().add(new JPanel());
			index++;
		}


		// SELECT BUTTONS
		JButton selAllBtn = new JButton("Select All");
		selAllBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int n = 0; n < boxes.length; n++) {
					boxes[n].setSelected(true);
				}
			}
		});
		getContentPane().add(selAllBtn);

		JButton SelNonBtn = new JButton("Select None");
		SelNonBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int n = 0; n < boxes.length; n++) {
					boxes[n].setSelected(false);
				}
			}
		});
		getContentPane().add(SelNonBtn);

		index += 2;

		// padding
		while (index % columns != 0) {
			getContentPane().add(new JPanel());
			index++;
		}
		for (int n = 0; n < columns; n++) {
			getContentPane().add(new JPanel());
		}

		// COMFRM BTNS
		okBtn = new JButton("Ok");
		okBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				choices = new ArrayList<Integer>();
				for (int n = 0; n < boxes.length; n++) {
					if (boxes[n].isSelected()) choices.add(n);
				}
				dispose();
			}
		});
		getContentPane().add(okBtn);

		JButton cancelBtn = new JButton("Cancel");
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				choices = new ArrayList<Integer>();
				choices.add(-1);
				dispose();
			}
		});
		getContentPane().add(cancelBtn);
		pack();
	}

	public ArrayList<Integer> choose() {
		this.setVisible(true);
		return choices;
	}

	public void dispose() {
		super.dispose();
	}




}
