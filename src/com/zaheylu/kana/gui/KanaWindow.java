package com.zaheylu.kana.gui;

import static com.zaheylu.kana.KanaLib.*;
import static com.zaheylu.snippets.CodeLibary.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.zaheylu.gui.JCheckDialog;
import com.zaheylu.kana.version.Version;
import com.zaheylu.kana.words.TGroups;
import com.zaheylu.kana.words.TVocabulary;
import com.zaheylu.kana.words.TWord;
import com.zaheylu.kana.xml.ReadXMLEntryNames;
import com.zaheylu.kana.xml.ReadXMLVocabulary;
import com.zaheylu.log.Log;
import com.zaheylu.snippets.CodeLibary;
import java.awt.GridLayout;


public class KanaWindow extends JFrame {

	private String path;
	private int[] options;

	private JPanel panelType;
	private JPanel panelChoose;
	private JPanel panelVocabulary;

	private JLabel lblType;
	private JLabel lblTypeHelp;
	private JTextField tfType;
	private JTextField tfTypeTmp;

	private JTextField tfVoc;
	private JLabel lblVocHira;
	private JLabel lblVocKata;
	private JLabel lblVoc;
	private JLabel lblVocComment;

	private TVocabulary vocabulary;
	private TGroups groups;
	private TWord currentWord;
	private ArrayList<TWord> currentWordPool;
	private ArrayList<String> possibleAnswers;

	private KanaWindow thisFrame = this;

	private int examIndex;
	private ArrayList<TWord> examCorrect;
	private ArrayList<TWord> examWrong;
	private int[] examOrder;
	private JCheckBoxMenuItem chckbxmntmSoundOnNew;
	private JMenuItem mnLoadAllSounds;
	private JMenuItem mntmReloadSound;
	private JPanel panelProgress;
	private JLabel lblProgess;
	private JPanel panel_1;
	private JPanel panel_2;
	private JCheckBoxMenuItem chckbxmntmChronologicalOrder;


	public KanaWindow() {
		this.path = System.getProperty("user.home") + "\\Documents\\kana\\";
		Log.setLog("Path.User", path);

		ImageIcon icon = new ImageIcon(KanaWindow.class.getResource("/res/kana_small.png"));
		setIconImage(icon.getImage());
		this.setSize(634, 317);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Kana");

		getContentPane().setLayout(null);

		panelVocabulary = new JPanel();
		panelVocabulary.setBounds(10, 11, 608, 246);
		getContentPane().add(panelVocabulary);

		panelChoose = new JPanel();
		panelChoose.setBounds(10, 11, 608, 246);
		getContentPane().add(panelChoose);

		panelType = new JPanel();
		panelType.setBounds(10, 11, 214, 246);
		getContentPane().add(panelType);
		panelType.setLayout(null);

		Log.event("initComponents");
		initComponents();

		showPanel(null);

		loadXMLs();

		Log.event("showWindow");
		this.setVisible(true);
	}

	private void initComponents() {

		tfType = new JTextField();
		tfTypeTmp = new JTextField();
		TfTypeKeyListener tmp = new TfTypeKeyListener(tfType.getDocument(), tfTypeTmp.getDocument());
		tfTypeTmp.getDocument().addDocumentListener(tmp);
		tfType.addKeyListener(tmp);
		tfType.getDocument().addDocumentListener(tmp);
		tfType.setBounds(10, 221, 194, 20);
		tfType.setColumns(10);
		panelType.add(tfType);

		lblTypeHelp = new JLabel("");
		lblTypeHelp.setForeground(Color.RED);
		lblTypeHelp.setHorizontalAlignment(SwingConstants.CENTER);
		lblTypeHelp.setFont(new Font("MS Gothic", Font.BOLD, 120));
		lblTypeHelp.setBounds(0, 11, 214, 199);
		lblTypeHelp.setVisible(false);
		panelType.add(lblTypeHelp);

		lblType = new JLabel("");
		lblType.setBounds(0, 11, 214, 199);
		panelType.add(lblType);
		lblType.setHorizontalAlignment(SwingConstants.CENTER);
		lblType.setFont(new Font("MS Gothic", Font.BOLD, 120));
		panelVocabulary.setLayout(null);

		tfVoc = new JTextField();
		tfVoc.addKeyListener(new TfVocKeyListener());

		panelProgress = new JPanel();
		panelProgress.setBounds(477, 213, 130, 30);
		panelVocabulary.add(panelProgress);
		panelProgress.setLayout(new GridLayout(0, 1, 0, 0));

		lblProgess = new JLabel("Progess: 0 / 0");
		lblProgess.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblProgess.setHorizontalAlignment(SwingConstants.CENTER);
		panelProgress.add(lblProgess);
		tfVoc.setBounds(0, 213, 476, 20);
		tfVoc.setColumns(10);
		panelVocabulary.add(tfVoc);

		lblVoc = new JLabel("B");
		lblVoc.setBounds(0, 11, 608, 126);
		panelVocabulary.add(lblVoc);
		lblVoc.setHorizontalAlignment(SwingConstants.CENTER);
		lblVoc.setFont(new Font("MS Gothic", Font.BOLD, 120));

		lblVocComment = new JLabel("");
		lblVocComment.setFont(new Font("MS Gothic", Font.PLAIN, 22));
		lblVocComment.setHorizontalAlignment(SwingConstants.CENTER);
		lblVocComment.setBounds(0, 180, 608, 30);
		panelVocabulary.add(lblVocComment);

		lblVocHira = new JLabel("");
		lblVocHira.setFont(new Font("MS Gothic", Font.PLAIN, 25));
		lblVocHira.setBounds(0, 130, 303, 39);
		panelVocabulary.add(lblVocHira);

		lblVocKata = new JLabel("");
		lblVocKata.setFont(new Font("MS Gothic", Font.PLAIN, 25));
		lblVocKata.setBounds(315, 130, 293, 39);
		panelVocabulary.add(lblVocKata);

		JLabel lblConfirm = new JLabel("Press Enter to Confirm");
		lblConfirm.setBounds(477, 212, 132, 14);
		panelVocabulary.add(lblConfirm);

		JLabel lblSkip = new JLabel("Press CTRL+S to Skip");
		lblSkip.setBounds(477, 226, 132, 14);
		panelVocabulary.add(lblSkip);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnProgram = new JMenu("Settings");
		menuBar.add(mnProgram);

		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new MntmExitActionListener());

		JCheckBoxMenuItem chckbxmntmEnableSounds = new JCheckBoxMenuItem("Enable Sounds");
		chckbxmntmEnableSounds.addActionListener(new ChckbxmntmEnableSoundsActionListener());
		mnProgram.add(chckbxmntmEnableSounds);

		mnLoadAllSounds = new JMenuItem("Reload all sounds");
		mnLoadAllSounds.setEnabled(false);
		mnLoadAllSounds.addActionListener(new ChckbxmntmLoadAllSoundsActionListener());

		chckbxmntmSoundOnNew = new JCheckBoxMenuItem("Sound on new Vocabulary");
		chckbxmntmSoundOnNew.setEnabled(false);
		chckbxmntmSoundOnNew.addActionListener(new ChckbxmntmSoundOnNewActionListener());
		mnProgram.add(chckbxmntmSoundOnNew);
		mnProgram.add(mnLoadAllSounds);

		mntmReloadSound = new JMenuItem("Reload this sound");
		mntmReloadSound.addActionListener(new MntmReloadSoundActionListener());
		mntmReloadSound.setEnabled(false);
		mnProgram.add(mntmReloadSound);

		JCheckBoxMenuItem chckbxmntmRemoveSkippedWords = new JCheckBoxMenuItem("Remove Skipped Words");
		chckbxmntmRemoveSkippedWords.addActionListener(new ChckbxmntmRemoveSkippedWordsActionListener());
		mnProgram.add(chckbxmntmRemoveSkippedWords);

		panel_1 = new JPanel();
		panel_1.setBackground(Color.BLACK);
		panel_1.setForeground(new Color(0, 0, 0));
		mnProgram.add(panel_1);

		chckbxmntmChronologicalOrder = new JCheckBoxMenuItem("Chronological Order");
		chckbxmntmChronologicalOrder.addActionListener(new ChckbxmntmChronologicalOrderActionListener());
		mnProgram.add(chckbxmntmChronologicalOrder);

		panel_2 = new JPanel();
		panel_2.setForeground(Color.BLACK);
		panel_2.setBackground(Color.BLACK);
		mnProgram.add(panel_2);

		mnProgram.add(mntmExit);

		JMenu mnProfile = new JMenu("Profile");
		mnProfile.setEnabled(false);
		menuBar.add(mnProfile);

		JMenuItem mntmLoad = new JMenuItem("Load");
		mntmLoad.addActionListener(new MntmLoadActionListener());
		mnProfile.add(mntmLoad);

		JMenu mnTraining = new JMenu("Training");
		menuBar.add(mnTraining);

		JMenuItem mntmCharacters = new JMenuItem("Characters");
		mntmCharacters.addActionListener(new MntmCharactersActionListener());
		mnTraining.add(mntmCharacters);

		JMenuItem mntmVocabulary = new JMenuItem("Vocabulary");
		mntmVocabulary.addActionListener(new MntmVocabularyActionListener());
		mnTraining.add(mntmVocabulary);

		JMenu mnInfo = new JMenu("Info");
		menuBar.add(mnInfo);

		JMenuItem mntmAbout = new JMenuItem("About");
		mntmAbout.addActionListener(new MntmAboutActionListener());
		mnInfo.add(mntmAbout);

		JMenuItem mntmCheckForUpdates = new JMenuItem("Check for Updates");
		mntmCheckForUpdates.addActionListener(new MntmCheckForUpdatesActionListener());
		mnInfo.add(mntmCheckForUpdates);
	}

	private void loadXMLs() {
		Log.event("loadXML");

		loadGroups("Groups.xml");
		loadVocabulary("Vocabulary.xml");
	}

	private void loadGroups(String fileName) {
		Log.event("Load Groups");
		try {
			ReadXMLEntryNames reader = new ReadXMLEntryNames();
			if (new File(path + fileName).exists()) {
				String path = this.path + fileName;
				Log.setLog("Path.Group", path);
				groups = new TGroups(reader.load(path));
			} else {
				URL path = (KanaWindow.class.getResource("/lib/" + fileName));
				Log.setLog("Path.Group", path);
				groups = new TGroups(reader.load(path));
				Log.setLog("Loaded.Group", groups);
			}

		} catch (ParserConfigurationException | SAXException | IOException err) {
			Log.event("Err.KanaWindow.loadGroups");
			err.printStackTrace();
		}
	}

	private void loadVocabulary(String fileName) {
		Log.event("Load Vocabulary");
		try {
			ReadXMLVocabulary vocReader = new ReadXMLVocabulary();
			if (new File(path + fileName).exists()) {
				String path = this.path + fileName;
				Log.setLog("Path.Vocabulary", path);
				// TODO: I should really not be using SAX for this... might as well write a seperate xml loader for this...
				vocabulary = vocReader.loadVocabulary(path);
			} else {
				URL path = (KanaWindow.class.getResource("/lib/" + fileName));
				Log.setLog("Path.Vocabulary", path);
				vocabulary = vocReader.loadVocabulary(path);
				Log.setLog("Loaded.Vocabulary", vocabulary);
			}

		} catch (ParserConfigurationException | SAXException | IOException e) {
			Log.event("Err.KanaWindow.loadVocabulary");
			e.printStackTrace();
		}
	}

	private class MntmExitActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			System.exit(0);
		}
	}

	private class MntmLoadActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {

		}
	}

	private class MntmCharactersActionListener implements ActionListener {


		public void actionPerformed(ActionEvent arg0) {

			options = new KanaCharacterChoose().choose(1);
			if (options[0] != -1) {
				if (options[1] == 1) {
					showPanel(panelType);
					newType();
				} else {
					showPanel(panelChoose);
					newChoose();
				}
			}
		}
	}

	private class TfTypeKeyListener extends KeyAdapter implements DocumentListener {
		private Document d1, d2;

		public TfTypeKeyListener(Document d1, Document d2) {
			this.d1 = d1;
			this.d2 = d2;
		}

		public void keyReleased(KeyEvent arg0) {

			if (arg0.getKeyCode() == KeyEvent.VK_F1) {
				lblTypeHelp.setVisible(false);
			}
		}

		public void keyPressed(KeyEvent arg0) {
			if (arg0.getKeyCode() == KeyEvent.VK_F1) {
				lblTypeHelp.setText(convert(lblType.getText(), options[0], 0));
				lblTypeHelp.setVisible(true);
			}
		}

		public void changedUpdate(DocumentEvent e) {
			update(e);
		}

		public void insertUpdate(DocumentEvent e) {
			update(e);
		}

		public void removeUpdate(DocumentEvent e) {
			update(e);
		}

		private void update(DocumentEvent e) {
			{
				Document doc = null, doc2 = null;
				if (e.getDocument().equals(d1)) {
					doc = d1;
					doc2 = d2;
				} else if (e.getDocument().equals(d2)) {
					doc = d2;
					doc2 = d1;
				}
				if (doc != null) {
					tfType.setDocument(doc);
					if (check(tfType)) {
						newType();
						tfTypeTmp.setDocument(doc2);
						tfTypeTmp.setText("");
						tfType.setDocument(doc2);
					}
				}
			}
		}

		private boolean check(JTextField tf) {
			return matches(tf.getText(), 0, lblType.getText(), options[0]);
		}
	}

	private class MntmVocabularyActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			if (vocabulary != null) if (vocabulary.size() > 0) {
				options = new KanaCharacterChoose().choose(2);
				if (options[0] != KanaCharacterChoose.CANCEL) {
					ArrayList<Integer> choices = new JCheckDialog(thisFrame, groups.toStringArray(vocabulary), 2).choose();
					if (choices != null && choices.size() > 0 && choices.get(0) >= 0) {
						currentWordPool = vocabulary.getFiltered(choices);
						showPanel(panelVocabulary);
						Log.setLog("Vocabulary.Index", -1);
						if (options[1] == KanaCharacterChoose.EXAM) {
							newExam();
						} else {
							newVocabulary();
						}
					}
				}
			} else {
				showmessage("Error loading Vocabulary Database.");
			}
		}
	}

	private class MntmAboutActionListener implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			new KanaAbout();
		}
	}

	private class MntmCheckForUpdatesActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			openHTTP("http://www.hafnehau.square7.ch/kana/versioncheck.php?=" + Version.getShortVersion());
		}
	}

	private class TfVocKeyListener extends KeyAdapter {


		public void keyReleased(KeyEvent arg0) {
			if (arg0.getKeyCode() == KeyEvent.VK_S && arg0.isControlDown() && (!(arg0.isShiftDown())) && (!(arg0.isAltDown()))) {
				tfVoc.setText("");
				lblVocHira.setText("");
				lblVocKata.setText("");
				if (options[1] == KanaCharacterChoose.EXAM) {
					newExamAnswer(false);
				} else {
					if (Log.getBool("Vocabulary.RemoveSkipped")) currentWordPool.remove(currentWord);
					newVocabulary();
				}
			}
			if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
				if (options[0] == 1) {
					for (int n = 0; n < currentWord.getEngl().size(); n++) {
						if (equalsIgnoreCase(currentWord.getEngl().get(n), tfVoc.getText())) {
							tfVoc.setText("");
							if (options[1] == KanaCharacterChoose.EXAM) newExamAnswer(true);
							else newVocabulary();
						}
					}
				} else {
					for (int n = 0; n < possibleAnswers.size(); n++) {
						if (!(tfVoc.getText().isEmpty())) {
							if (equalsIgnoreCase(tfVoc.getText(), possibleAnswers.get(n)) || equalsIgnoreCase(lblVocHira.getText(), possibleAnswers.get(n))
									|| equalsIgnoreCase(lblVocKata.getText(), possibleAnswers.get(n))) {
								tfVoc.setText("");
								lblVocHira.setText("");
								lblVocKata.setText("");
								if (options[1] == KanaCharacterChoose.EXAM) newExamAnswer(true);
								else newVocabulary();
							}
						}
					}
				}
			}
			if (options[0] == 2) {
				lblVocHira.setText(convertPlus(tfVoc.getText(), 0, 1));
				lblVocKata.setText(convertPlus(tfVoc.getText(), 0, 2));
			}
		}

		public void keyPressed(KeyEvent arg0) {
			if (arg0.getKeyCode() == KeyEvent.VK_F1) {
				new VocHelp(thisFrame, currentWord, groups.get(currentWord.getGroup()));
			} else if (arg0.getKeyCode() == KeyEvent.VK_ESCAPE && options[1] != KanaCharacterChoose.EXAM) {
				Log.setLog("Vocabulary.Index", -1);
			}
		}
	}

	private class ChckbxmntmLoadAllSoundsActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			vocabulary.loadAllSounds();
		}
	}

	private class ChckbxmntmEnableSoundsActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			JCheckBoxMenuItem check = (JCheckBoxMenuItem) arg0.getSource();
			Log.setLog("Sounds.Enabled", check.isSelected());
			chckbxmntmSoundOnNew.setEnabled(check.isSelected());
			mnLoadAllSounds.setEnabled(check.isSelected());
			if (check.isSelected()) showmessage("Enabling this will read out the word you're translating in Vocabulary mode when pressing F1."
					+ "\nIt is using Google's Text-To-Speech Service and JLayer for MP3-output."
					+ "\nIt may or may not work. If it doesn't, just disable this feature by clicking 'Enable Sounds' again.");
		}
	}

	private class ChckbxmntmSoundOnNewActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			Log.setLog("Sounds.OnVocabulary",((JCheckBoxMenuItem) arg0.getSource()).isSelected());
		}
	}

	private class MntmReloadSoundActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			if (Log.getBool("Sounds.Enabled")) {
				vocabulary.loadSound(currentWord, false, true);
			}
		}
	}

	private class ChckbxmntmRemoveSkippedWordsActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			JCheckBoxMenuItem ch = (JCheckBoxMenuItem) arg0.getSource();
			Log.setLog("Vocabulary.RemoveSkipped", ch.isSelected());
		}
	}

	private class ChckbxmntmChronologicalOrderActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			JCheckBoxMenuItem ch = (JCheckBoxMenuItem) arg0.getSource();
			Log.setLog("Vocabulary.Order.Chronological", ch.isSelected());
		}
	}

	private void newType() {
		int val;
		if (options[0] != KanaCharacterChoose.KANJI) {
			do {
				val = randInt(getAlphabet().length - 1);
			} while (convert(getAlphabet()[val], 0, options[0]) == lblType.getText());
			lblType.setText(convert(getAlphabet()[val], 0, options[0]));
			tfType.requestFocus();
		} else {
			// TODO: typeKanji
		}
	}

	private void newExam() {
		Log.event("Exam.Started");
		examIndex = -1;
		examCorrect = new ArrayList<TWord>();
		examWrong = new ArrayList<TWord>();
		if (Log.getBool("Vocabulary.Order.Chronological")) {
			examOrder = new int[currentWordPool.size()];
			for (int n = 0; n < examOrder.length; n++) {
				examOrder[n] = n;
			}
		} else examOrder = CodeLibary.randomOrder(currentWordPool.size());
		newExamWord();
	}

	private void newExamAnswer(boolean pass) {
		if (pass) examCorrect.add(currentWord);
		else examWrong.add(currentWord);
		newExamWord();
	}

	private void newExamWord() {
		examIndex++;
		if (examIndex != currentWordPool.size()) {
			lblProgess.setText(String.format("Progress: %d / %d", examIndex + 1, currentWordPool.size()));
			currentWord = currentWordPool.get(examOrder[examIndex]);
			showVoc(currentWord);
		} else {
			showPanel(null);
			new KanaResult(thisFrame, examWrong, examCorrect);
		}
	}

	private void showVoc(TWord word) {
		panelProgress.setVisible(options[1] == KanaCharacterChoose.EXAM);

		mntmReloadSound.setEnabled(Log.getBool("Sounds.Enabled"));
		if (Log.getBool("Sounds.Enabled")) {
			vocabulary.loadSound(word, Log.getBool("Sounds.OnVocabulary"), false);
		}
		Log.event("showVoc");
		String from;
		// CALCULATE TRANSLATION AND POSSIBLE ANSWERS
		if (options[0] == 1) {
			if (word.hasKanji()) from = currentWord.getKanji();
			else from = word.getKana();
			if (word.hasPresent()) from += " / " + word.getPresent();
			lblVocHira.setText("");
			lblVocKata.setText("");
		} else {
			from = word.getEngl().get(randInt(word.getEngl().size() - 1));
			possibleAnswers = vocabulary.getPossibleAnswers(from);
		}
		// SET TEXT
		lblVoc.setText(from);
		strechFont(lblVoc);
		if (word.hasComment()) lblVocComment.setText("(" + word.getComment() + ")");
		else lblVocComment.setText("");
		strechFont(lblVocComment);

		// FOCUS TEXTFIELD
		tfVoc.requestFocus();

	}

	private int getNext() {
		Integer index = (Integer) Log.getEntry("Vocabulary.Index").getLogObj();
		if (index < currentWordPool.size() - 1) return index + 1;
		else return 0;

	}

	private void newVocabulary() {
		int val = 0;
		if (currentWordPool.size() > 0) {
			if (Log.getBool("Vocabulary.Order.Chronological")) val = getNext();
			else do {
				val = randInt(currentWordPool.size() - 1);
			} while (currentWordPool.get(val) == currentWord && currentWordPool.size() > 1);
			Log.setLog("Vocabulary.Index", val);
			currentWord = currentWordPool.get(val);
			showVoc(currentWord);
		} else {
			showPanel(null);
			showmessage("There are no word in the word pool left.");
		}
	}

	private void newChoose() {
		Log.event("newChoose");
		showmessage("This feature is not implemented yet.\nI would kindly like to apologize for the inconvenience this may cause.");
	}

	private void showPanel(JPanel panel) {
		Log.event("showPanel");
		panelVocabulary.setVisible(false);
		panelType.setVisible(false);
		panelChoose.setVisible(false);
		if (panel != null) panel.setVisible(true);
		f5();
	}

	private void f5() {
		Log.event("Repaint");
		this.repaint();
	}
}