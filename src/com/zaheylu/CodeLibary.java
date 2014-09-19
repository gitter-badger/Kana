package com.zaheylu;

import java.awt.Canvas;
import java.awt.Font;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class CodeLibary {

	private static Random rand = new Random();

	public static int getStringWidth(Font font, String txt) {
		Canvas c = new Canvas();
		return c.getFontMetrics(font).stringWidth(txt);
	}

	public static void showmessage(String msg) {
		JOptionPane.showMessageDialog(null, msg, null, JOptionPane.PLAIN_MESSAGE);
	}

	public static int randInt(int min, int max) {
		return rand.nextInt((max - min) + 1) + min;
	}

	public static int randInt(int max) {
		return rand.nextInt((max) + 1);
	}

	public static void strechLbl(JLabel lbl) {
		Font font = lbl.getFont();
		String text = lbl.getText();

		int stringWidth = lbl.getFontMetrics(font).stringWidth(text);
		int componentWidth = lbl.getWidth();

		double widthRatio = (double) componentWidth / (double) stringWidth;

		int newFontSize = (int) (font.getSize() * widthRatio) - 1;
		int componentHeight = lbl.getHeight();

		// Pick a new font size so it will not be larger than the height of label.
		int fontSizeToUse = Math.min(newFontSize, componentHeight);

		// Set the label's font size to the newly determined size.
		lbl.setFont(new Font(font.getName(), font.getStyle(), fontSizeToUse));
	}



}
