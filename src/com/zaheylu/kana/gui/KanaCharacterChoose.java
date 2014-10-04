package com.zaheylu.kana.gui;

import static com.zaheylu.snippets.CodeLibary.*;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

import com.zaheylu.log.Log;
import com.zaheylu.snippets.CodeLibary;

public class KanaCharacterChoose extends JDialog {
	public KanaCharacterChoose(JFrame parent) {
		this.parent = parent;
		//choose(2);
	}



	private ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton rdbtnHiragana = new JRadioButton("Hiragana");
	private JRadioButton rdbtnKatakana = new JRadioButton("Katakana");
	private JRadioButton rdbtnKanji = new JRadioButton("Kanji");
	private JRadioButton rdbtnType = new JRadioButton("Type");
	private JRadioButton rdbtnChoose = new JRadioButton("Choose");

	private JPanel panelVoc;
	private JPanel panelChar2;
	private JPanel panelChar1;
	private JFrame parent;

	public static final int HIRAGANA = 1;
	public static final int KATAKANA = 2;
	public static final int KANJI = 3;
	public static final int TYPE = 1;
	public static final int CHOOSE = 2;
	public static final int CANCEL = -1;
	public static final int JPTOEN = 1;
	public static final int ENTOJP = 2;
	public static final int EXAM = 1;


	private int[] choice = {
			-1, -1 };
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	private final JRadioButton rdbtnJpToEn = new JRadioButton("JP to EN");
	private final JRadioButton rdbtnEnToJp = new JRadioButton("EN to JP");
	private final ButtonGroup buttonGroup_2 = new ButtonGroup();
	private final JButton btnHelp = new JButton("How to use");
	private final JCheckBox chckbxExam = new JCheckBox("exam");
	private JPanel buttonPane;

	private void placeComponents() {
		Container pan = getContentPane();

		pan.setPreferredSize(new Dimension(209, 154));
		pan.setSize(pan.getPreferredSize());
		pack();
		int width = pan.getWidth();
		int height = pan.getHeight();

		panelVoc.setBounds(0, 0, width, (int) Math.round(height * 0.75));
		panelChar1.setBounds(0, 0, width / 2, (int) Math.round(height * 0.75));
		panelChar2.setBounds(width / 2, 0, width / 2, (int) Math.round(height * 0.75));
		buttonPane.setBounds(0, (int) Math.round(height * 0.75), width, (int) Math.round(height * 0.25));
	}

	public int[] choose(int mode) {
		Log.event("CharacterChoose");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setResizable(false);
		getContentPane().setLayout(null);

		panelVoc = new JPanel();
		getContentPane().add(panelVoc);
		panelVoc.setLayout(new GridLayout(4, 2, 5, 5));
		panelVoc.add(new JPanel());
		buttonGroup_2.add(rdbtnJpToEn);
		rdbtnJpToEn.setSelected(true);
		panelVoc.add(rdbtnJpToEn);
		{
			buttonGroup_2.add(rdbtnEnToJp);
			panelVoc.add(rdbtnEnToJp);
		}
		{
			btnHelp.addActionListener(new BtnHelpActionListener());
			panelVoc.add(btnHelp);
		}
		panelChar1 = new JPanel();
		//panelChar1.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(panelChar1);
		panelChar1.setLayout(new GridLayout(0, 1, 0, 0));
		{

			buttonGroup.add(rdbtnHiragana);
			rdbtnHiragana.setSelected(true);
			panelChar1.add(rdbtnHiragana);
		}
		{
			buttonGroup.add(rdbtnKatakana);
			panelChar1.add(rdbtnKatakana);
		}
		{
			buttonGroup.add(rdbtnKanji);
			rdbtnKanji.setEnabled(false);
			panelChar1.add(rdbtnKanji);
		}
		{
			panelChar2 = new JPanel();
			getContentPane().add(panelChar2);
			panelChar2.setLayout(new GridLayout(0, 1, 0, 0));
			{
				rdbtnType.setSelected(true);
				buttonGroup_1.add(rdbtnType);
				rdbtnType.setHorizontalAlignment(SwingConstants.LEFT);
				panelChar2.add(rdbtnType);
			}
			{
				buttonGroup_1.add(rdbtnChoose);
				rdbtnChoose.setHorizontalAlignment(SwingConstants.LEFT);
				panelChar2.add(rdbtnChoose);
			}
		}
		{
			buttonPane = new JPanel();
			getContentPane().add(buttonPane);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new OkButtonActionListener(mode));
				buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
				{
					if (mode == 2) buttonPane.add(chckbxExam);
				}
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new CancelButtonActionListener());
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		setLocationRelativeTo(parent);

		if (mode == 1) {
			panelVoc.setVisible(false);


		} else if (mode == 2) {
			panelChar1.setVisible(false);
			panelChar2.setVisible(false);

		}
		placeComponents();
		setVisible(true);
		return choice;
	}

	public void dispose() {
		super.dispose();
	}

	private class OkButtonActionListener implements ActionListener {
		int mode;

		public OkButtonActionListener(int mode) {
			this.mode = mode;
		}

		public void actionPerformed(ActionEvent e) {
			if (mode == 1) {
				if (rdbtnHiragana.isSelected()) choice[0] = HIRAGANA;
				else if (rdbtnKatakana.isSelected()) choice[0] = KATAKANA;
				else choice[0] = KANJI;
				if (rdbtnType.isSelected()) choice[1] = TYPE;
				else choice[1] = CHOOSE;

			} else if (mode == 2) {
				if (rdbtnEnToJp.isSelected()) choice[0] = ENTOJP;
				else if (rdbtnJpToEn.isSelected()) choice[0] = JPTOEN;
				choice[1] = CodeLibary.boolToInt(chckbxExam.isSelected());
			}
			dispose();
		}
	}

	private class CancelButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			choice[0] = CANCEL;
			dispose();
		}
	}

	private class BtnHelpActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			showmessage("\nInstructions:" + "\nEnter the correct translation and hit enter to confirm."
					+ "\nPress <F1> for help and use <CTRL+S> to skip a word or if you didn't know the translation in exam mode."
					+ "\nPress <F2> to see all words in the current word pool."
					+ "\nIn EXAM MODE you have to translate all words in the selected groups in random order." + "\n\nAdding Vocabulary:"
					+ "\nThe Vocabulary is loaded into the program by xml files containing the vocabulary." + "\nThese xml files are located in '"
					+ Log.getString("Path.Vocabulary") + "'\nYou can edit these files if you wish to make changes to the vocabulary."
					+ "\n\nEditing XML Files:" + "\nThe Vocabulary.xml file is in 'UTF-8 w/o BOM' encoded."
					+ "\nBe sure to maintain this encoding type because else it won't work.\nNotepad++ is recommended for editing."
					+ "\nOne software to support this encoding is Notepad++. Windows' notepad does not support it.");
		}
	}



	class ThisKeyListener extends KeyAdapter {
		public void keyTyped(KeyEvent arg) {
			if (arg.getKeyCode() == KeyEvent.VK_ESCAPE) {
				choice[0] = CANCEL;
				dispose();
			}
		}
	}


}
