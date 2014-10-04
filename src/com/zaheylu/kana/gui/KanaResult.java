package com.zaheylu.kana.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import com.zaheylu.kana.users.Profile;
import com.zaheylu.kana.words.TWord;
import com.zaheylu.snippets.CodeLibary;

public class KanaResult extends JDialog {
	private JDialog thisFrame = this;

	private final int width = (int) Math.round(Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.9);

	private JPanel newResultPanel(ArrayList<TWord> words, String title, Profile profile) {
		JPanel result = new JPanel();
		int columns = 8;
		int rows = (words.size() / columns) + 1;
		if ((words.size() % columns) == 0) rows--;
		int height = rows * 50 + 50;
		result.setLayout(null);
		result.setBounds(0, 0, width, height);
		JLabel lblTitle = new JLabel(title + ": ");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 45));
		lblTitle.setBounds(0, 0, width, 49);
		CodeLibary.strechFont(lblTitle);
		result.add(lblTitle);

		JPanel panelContent = new JPanel();
		panelContent.setLayout(new GridLayout(rows, columns));
		panelContent.setBorder(new LineBorder(Color.BLACK));
		panelContent.setBackground(new Color(100, 100, 100));
		panelContent.setBounds(0, 50, width, rows * 50);

		for (TWord word : words) {
			JLabel lbl = new JLabel(word.getKana());
			if (word.hasKanji()) lbl.setText(word.getKanji());
			lbl.setSize((panelContent.getWidth()) / (columns), (panelContent.getHeight()) / rows);
			lbl.setFont(new Font("MS Gothic", Font.BOLD, 20));
			lbl.setBackground(Color.black);
			if (profile != null) {
				double ratio;
				int r = 0, g = 0, b = 0, number = 0;
				if (profile.get(word.getIndex()) == null) ratio = 0.5;
				else {
					ratio = profile.get(word.getIndex()).getRatio();
					number = profile.get(word.getIndex()).getNumber();
				}


				// COLOR
				if (number != 0) if (ratio >= 0.9 && number > 9) {
					r = (int) (ratio * 255.0);
					g = (int) (ratio * 255.0);
					b = (int) (ratio * 255.0);
				} else if (ratio >= 0.5) {
					r = (int) (510.0 + ratio * (-510.0));
					g = 255;
					b = 0;
				} else if (ratio >= 0.1) {
					r = 255;
					g = (int) (ratio * 510.0);
					b = 0;
				} else {
					r = (int) (ratio * 510.0);
					g = (int) (ratio * 255.0);
					b = (int) (255 - ratio * 255.0);
				}
				Color c = new Color(r, g, b);

				lbl.setBorder(new LineBorder(c));
				lbl.setForeground(c);
			} else lbl.setBorder(new LineBorder(Color.BLACK));
			CodeLibary.strechFont(lbl);
			lbl.addMouseListener(new KanaMouseListener(word));
			panelContent.add(lbl);
		}
		result.add(panelContent);
		return result;
	}

	private void init() {
		getContentPane().setLayout(null);
	}

	private void showFrame(JFrame frame) {
		this.setLocationRelativeTo(frame);
		this.setModal(true);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
	}

	public KanaResult(JFrame frame, ArrayList<TWord> words, Profile profile) {
		init();
		JPanel panelWords = newResultPanel(words, "Word pool", profile);
		panelWords.setLocation(0, 0);
		getContentPane().add(panelWords);
		getContentPane().setPreferredSize(new Dimension(panelWords.getWidth(), panelWords.getHeight()));
		pack();
		showFrame(frame);
	}

	public KanaResult(JFrame frame, ArrayList<TWord> words) {
		init();
		JPanel panelWords = newResultPanel(words, "Word pool", null);
		panelWords.setLocation(0, 0);
		getContentPane().add(panelWords);
		getContentPane().setPreferredSize(new Dimension(panelWords.getWidth(), panelWords.getHeight()));
		pack();
		showFrame(frame);
	}

	public KanaResult(JFrame frame, ArrayList<TWord> wrongWords, ArrayList<TWord> correctWords) {
		init();
		JPanel panelWrong = newResultPanel(wrongWords, "Wrong", null);
		panelWrong.setLocation(0, 0);
		getContentPane().add(panelWrong);

		JPanel panelCorrect = newResultPanel(correctWords, "Correct", null);
		panelCorrect.setLocation(0, panelWrong.getHeight());
		getContentPane().add(panelCorrect);

		getContentPane().setPreferredSize(new Dimension(panelCorrect.getWidth(), panelCorrect.getHeight() + panelCorrect.getY()));
		pack();
		showFrame(frame);
	}

	public KanaResult(JFrame frame, ArrayList<TWord> wrongWords, ArrayList<TWord> correctWords, Profile profile) {
		init();
		JPanel panelWrong = newResultPanel(wrongWords, "Wrong", profile);
		panelWrong.setLocation(0, 0);
		getContentPane().add(panelWrong);

		JPanel panelCorrect = newResultPanel(correctWords, "Correct", profile);
		panelCorrect.setLocation(0, panelWrong.getHeight());
		getContentPane().add(panelCorrect);

		getContentPane().setPreferredSize(new Dimension(panelCorrect.getWidth(), panelCorrect.getHeight() + panelCorrect.getY()));
		pack();
		showFrame(frame);
	}

	public void dispose() {
		super.dispose();
	}

	private class KanaMouseListener implements MouseListener {
		private TWord word;

		public KanaMouseListener(TWord word) {
			this.word = word;
		}

		public void mouseClicked(MouseEvent e) {

		}

		public void mouseEntered(MouseEvent e) {
		}

		public void mouseExited(MouseEvent e) {
		}

		public void mousePressed(MouseEvent e) {
			new VocHelp(thisFrame, word, null);
		}

		public void mouseReleased(MouseEvent e) {
		}
	}
}
