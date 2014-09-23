package com.zaheylu.kana.gui;

import static com.zaheylu.snippets.CodeLibary.*;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JDialog;
import javax.swing.JLabel;

import com.zaheylu.kana.KanaLib;
import com.zaheylu.kana.mp3.Mp3Play;
import com.zaheylu.kana.words.TWord;
import com.zaheylu.log.Log;

public class VocHelp extends JDialog {

	private final int width = 700;

	private JLabel newJLabel(String txt, int y, int height, Font font) {
		JLabel result = new JLabel(txt);
		result.setBounds(5, y, width - 5, height);
		result.setFont(font);
		strechFont(result);
		return result;
	}

	public VocHelp(Component frame, TWord word, String group) {
		int cY = 0;
		getContentPane().setLayout(null);
		// kanji
		if (word.hasKanji()) {
			JLabel lblKanji = newJLabel(word.getKanji(), cY, 100, new Font("MS Gothic", Font.BOLD, 20));
			cY += lblKanji.getHeight();
			getContentPane().add(lblKanji);
		}
		// kana
		{
			JLabel lblKana = newJLabel(word.getKana(), cY, 100, new Font("MS Gothic", Font.BOLD, 20));
			cY += lblKana.getHeight();
			getContentPane().add(lblKana);
		}
		if (word.hasPresent()) {
			JLabel lblPresent = newJLabel("Present: " + word.getPresent(), cY, 100, new Font("MS Gothic", Font.BOLD, 20));
			cY += lblPresent.getHeight();
			getContentPane().add(lblPresent);
		}
		// English
		{
			for (int n = 0; n < word.getEngl().size(); n++) {
				JLabel lblEngl = newJLabel(word.getEngl().get(n), cY, 50, new Font("Tahoma", Font.BOLD, 20));
				cY += lblEngl.getHeight();
				getContentPane().add(lblEngl);
			}
		}
		// Romaji
		{
			JLabel lblRomaji;
			String txt = "Romaji: ";
			if (word.hasRomaji()) txt += word.getRomaji();
			else txt += KanaLib.convertPlus(word.getKana(), KanaLib.findType(String.valueOf(word.getKana().toCharArray()[0])), 0);
			lblRomaji = newJLabel(txt, cY, 30, new Font("MS Gothic", Font.PLAIN, 20));
			cY += lblRomaji.getHeight() + 10;
			getContentPane().add(lblRomaji);
		}
		// Comment
		if (word.hasComment()) {
			JLabel lblComment = newJLabel("(" + word.getComment() + ")", cY, 50, new Font("MS Gothic", Font.PLAIN, 20));
			cY += lblComment.getHeight();
			getContentPane().add(lblComment);
		}


		if (group != null) this.setTitle("Group: " + group);
		this.setSize(width, cY + 40);
		this.setLocationRelativeTo(frame);
		this.setModal(true);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.addMouseListener(new MouseListener() {

			public void mouseClicked(MouseEvent arg0) {

			}

			public void mouseEntered(MouseEvent arg0) {

			}

			public void mouseExited(MouseEvent arg0) {

			}

			public void mousePressed(MouseEvent arg0) {
				dispose();
			}

			public void mouseReleased(MouseEvent arg0) {
			}
		});
		this.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent arg0) {
				dispose();
			}

			public void keyReleased(KeyEvent arg0) {

			}

			public void keyTyped(KeyEvent arg0) {
			}
		});
		try {
			String sounds = Log.getLog("Sounds.Enabled");
			if (sounds != null) {
				if (Boolean.valueOf(sounds)) {
					Mp3Play.play(word);
				}
			}
		} catch (Throwable t) {
			t.printStackTrace();
		}
		this.setVisible(true);

	}

	public void dispose() {
		super.dispose();
	}

}
