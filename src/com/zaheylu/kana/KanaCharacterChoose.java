package com.zaheylu.kana;

import static com.zaheylu.CodeLibary.*;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import com.zaheylu.log.Log;

public class KanaCharacterChoose extends JDialog {
	public KanaCharacterChoose() {
		// choose(2);
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

	private static final int HIRAGANA = 1;
	private static final int KATAKANA = 2;
	private static final int KANJI = 3;
	private static final int TYPE = 1;
	private static final int CHOOSE = 2;
	private static final int CANCEL = -1;
	private static final int JPTOEN = 1;
	private static final int ENTOJP = 2;


	private int[] choice = {
			-1, -1 };
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	private final JRadioButton rdbtnJpToEn = new JRadioButton("JP to EN");
	private final JRadioButton rdbtnEnToJp = new JRadioButton("EN to JP");
	private final ButtonGroup buttonGroup_2 = new ButtonGroup();
	private final JButton btnHelp = new JButton("Info on 'Vocabulary'");


	public int[] choose(int mode) {

		Log.event("CharacterChoose");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 220, 165);
		setModal(true);
		getContentPane().setLayout(null);

		panelVoc = new JPanel();
		panelVoc.setBounds(0, 0, 204, 94);
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
		panelChar1.setBounds(0, 0, 105, 94);
		panelChar1.setBorder(new EmptyBorder(5, 5, 5, 5));
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
			panelChar2.setBounds(108, 0, 96, 94);
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
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 94, 204, 33);
			getContentPane().add(buttonPane);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new OkButtonActionListener(mode));
				buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
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
		setLocationRelativeTo(null);

		if (mode == 1) {
			panelVoc.setVisible(false);


		} else if (mode == 2) {
			panelChar1.setVisible(false);
			panelChar2.setVisible(false);

		}

		setVisible(true);
		return choice;
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
			}
			JButton button = (JButton) e.getSource();
			SwingUtilities.getWindowAncestor(button).dispose();
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
			showmessage("The Vocabulary is loaded into the program by xml files containing the vocabulary"
					+ "\nThese xml files are located in '" + Log.getLog("Path.Vocabulary") + "'" 
					+ "\nYou can edit these files if you wish to make changes to the vocabulary."
					+ "\n\nImportant note for editing files: The Vocabulary.xml in 'UTF-8 w/o BOM encoding'."
					+ "\nBe sure to maintain this encoding type because else it won't work."
					+ "\nNotepad++ is recommended for editing.");
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
