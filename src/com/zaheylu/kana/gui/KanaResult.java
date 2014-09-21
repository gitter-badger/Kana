package com.zaheylu.kana.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import com.zaheylu.kana.words.TWord;
import com.zaheylu.snippets.CodeLibary;

public class KanaResult extends JDialog {
	private JDialog thisFrame = this;

	private final int width = 786;

	private JPanel newResultPanel(ArrayList<TWord> words, String title) {
		JPanel result = new JPanel();
		int columns = 4;
		int rows = (words.size() / 4) + 1;
		if ((words.size() % 4) == 0) rows--;
		int height = rows * 50 + 50;
		result.setLayout(null);
		result.setBounds(0, 0, width - 6, height);
		JLabel lblTitle = new JLabel(title + ": ");
		lblTitle.setBounds(10, 0, width - 6, 49);
		result.add(lblTitle);
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 45));
		JPanel panelContent = new JPanel();
		panelContent.setLayout(new GridLayout(rows, columns));
		panelContent.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelContent.setBounds(10, 50, width - 60, rows * 50);

		for (int n = 0; n < words.size(); n++) {
			JLabel lbl = new JLabel(words.get(n).getKana());
			if (words.get(n).hasKanji()) lbl.setText(words.get(n).getKanji());
			lbl.setSize((panelContent.getWidth() - 6) / (columns), (panelContent.getHeight() - 6) / rows);
			lbl.setBorder(new LineBorder(new Color(0, 0, 0)));
			lbl.setFont(new Font("MS Gothic", Font.BOLD, 20));
			CodeLibary.strechFont(lbl);
			lbl.addMouseListener(new KanaMouseListener(words.get(n)));
			panelContent.add(lbl);
		}
		result.add(panelContent);
		return result;
	}
	
	

	public KanaResult(JFrame frame, ArrayList<TWord> wrongWords, ArrayList<TWord> correctWords) {
		
		getContentPane().setLayout(null);

		JPanel panelWrong = newResultPanel(wrongWords, "Wrong");
		panelWrong.setLocation(0, 0);
		getContentPane().add(panelWrong);
		
		JPanel panelCorrect = newResultPanel(correctWords, "Correct");
		panelCorrect.setLocation(0, panelWrong.getHeight());
		getContentPane().add(panelCorrect);

		this.setSize(width, panelCorrect.getY() + panelCorrect.getHeight() + 30);
		this.setLocationRelativeTo(frame);
		this.setModal(true);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
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
