package com.zaheylu.kana.gui;

import static com.zaheylu.kana.KanaLibV2.*;
import static com.zaheylu.kana.settings.KanaSettings.*;
import static com.zaheylu.snippets.CodeLibary.*;
import static java.lang.Math.*;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.SAXException;

import com.zaheylu.gui.JCheckDialog;
import com.zaheylu.kana.users.Profile;
import com.zaheylu.kana.version.Version;
import com.zaheylu.kana.words.TGroups;
import com.zaheylu.kana.words.TWord;
import com.zaheylu.kana.xml.ReadXMLEntryNames;
import com.zaheylu.kana.xml.handler.ProfileHandler;
import com.zaheylu.kana.xml.handler.VocHandler;
import com.zaheylu.log.Log;
import com.zaheylu.snippets.CodeLibary;


public class KanaWindow extends JFrame {
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
	private JPanel panelProgress;
	private JLabel lblProgess;
	private JPanel panelHints;


	private KanaWindow thisFrame = this;

	public KanaWindow(String[] args) {
		if (args != null) {
			for (String arg : args) {
				if (arg.equalsIgnoreCase("debug")) Log.enableLogOutput();
				if (arg.equalsIgnoreCase("touch")) Log.put("mode.touch", true);
			}
		}
		ImageIcon icon = new ImageIcon(KanaWindow.class.getResource("/res/kana_small.png"));
		setIconImage(icon.getImage());
		this.setMinimumSize(new Dimension(300, 200));
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Kana");

		getContentPane().setLayout(null);

		panelVocabulary = new JPanel();
		panelVocabulary.setLayout(null);
		getContentPane().add(panelVocabulary);

		panelChoose = new JPanel();
		panelChoose.setLayout(null);
		getContentPane().add(panelChoose);

		panelType = new JPanel();
		panelType.setLayout(null);
		getContentPane().add(panelType);

		initComponents();

		showPanel(null);

		loadXMLs();
		vocabulary.assignProfile(profile);

		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			public void run() {
				onShutdown();
			}
		}));

		this.setVisible(true);
	}

	public void onShutdown() {
		Log.event("hook.shutdown");
		try {
			profile.save();
		} catch (UnsupportedEncodingException | FileNotFoundException | XMLStreamException e) {
			e.printStackTrace();
		}
	}

	public void setVisible(boolean b) {
		Log.event("showWindow");
		super.setVisible(b);
	}

	private void initComponents() {
		Log.event("initComponents");
		tfType = new JTextField();
		tfTypeTmp = new JTextField();
		TfTypeKeyListener tmp = new TfTypeKeyListener(tfType.getDocument(), tfTypeTmp.getDocument());
		tfTypeTmp.getDocument().addDocumentListener(tmp);
		tfType.addKeyListener(tmp);
		tfType.getDocument().addDocumentListener(tmp);
		tfType.setBounds(10, 221, 194, 20);
		tfType.setColumns(10);
		lblTypeHelp = new JLabel("");
		lblTypeHelp.setForeground(Color.RED);
		lblTypeHelp.setHorizontalAlignment(SwingConstants.CENTER);
		lblTypeHelp.setFont(new Font("MS Gothic", Font.BOLD, 120));
		lblTypeHelp.setVisible(false);
		lblType = new JLabel("");
		lblType.setHorizontalAlignment(SwingConstants.CENTER);
		lblType.setFont(new Font("MS Gothic", Font.BOLD, 120));

		panelHints = new JPanel();
		panelHints.setLayout(new GridLayout(2, 1, 0, 0));
		JLabel lblConfirm = new JLabel("Press Enter to Confirm");
		JLabel lblSkip = new JLabel("Press CTRL+S to Skip");
		panelProgress = new JPanel();
		panelProgress.setLayout(new GridLayout(0, 1, 0, 0));
		lblProgess = new JLabel("Progess: 0 / 0");
		lblProgess.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblProgess.setHorizontalAlignment(SwingConstants.CENTER);

		tfVoc = new JTextField();
		tfVoc.addKeyListener(new TfVocKeyListener());
		tfVoc.setColumns(10);
		lblVoc = new JLabel("B");
		lblVoc.setHorizontalAlignment(SwingConstants.CENTER);
		lblVoc.setFont(new Font("MS Gothic", Font.BOLD, 120));
		lblVocComment = new JLabel("");
		lblVocComment.setFont(new Font("MS Gothic", Font.PLAIN, 22));
		lblVocComment.setHorizontalAlignment(SwingConstants.CENTER);
		lblVocHira = new JLabel("");
		lblVocHira.setFont(new Font("MS Gothic", Font.PLAIN, 25));
		lblVocKata = new JLabel("");
		lblVocKata.setFont(new Font("MS Gothic", Font.PLAIN, 25));


		panelType.add(tfType);
		panelType.add(lblType);
		panelType.add(lblTypeHelp);

		panelHints.add(lblConfirm);
		panelHints.add(lblSkip);
		panelProgress.add(lblProgess);

		panelVocabulary.add(panelHints);
		panelVocabulary.add(panelProgress);
		panelVocabulary.add(tfVoc);
		panelVocabulary.add(lblVoc);
		panelVocabulary.add(lblVocComment);
		panelVocabulary.add(lblVocHira);
		panelVocabulary.add(lblVocKata);

		// MENU

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnProgram = new JMenu("Program");
		JMenu mnProfile = new JMenu("Profile");
		JMenu mnTraining = new JMenu("Training");
		JMenu mnSettings = new JMenu("Settings");
		JMenu mnFilter = new JMenu("Filter");
		JMenu mnInfo = new JMenu("Info");
		JMenu mnDebug = new JMenu("Debug");

		JMenuItem mnLoadAllSounds = new JMenuItem("Reload all sounds");
		mnLoadAllSounds.addActionListener(new ChckbxmntmLoadAllSoundsActionListener());

		JMenuItem mntmReloadSound = new JMenuItem("Reload this sound");
		mntmReloadSound.addActionListener(new MntmReloadSoundActionListener());

		JMenuItem mntmSave = new JMenuItem("Save Profile");
		mntmSave.addActionListener(new MntmSaveActionListener());

		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new MntmExitActionListener());

		JMenuItem mntmLoadGrp = new JMenuItem("Reload Groups.xml");
		mntmLoadGrp.addActionListener(new MntmLoadGrpActionListener());

		JMenuItem mntmLoadVoc = new JMenuItem("Reload Vocabulary.xml");
		mntmLoadVoc.addActionListener(new MntmLoadVocActionListener());

		JMenuItem mntmLoadPro = new JMenuItem("Reload Profile");
		mntmLoadPro.addActionListener(new MntmLoadProActionListener());

		JMenuItem mntmLinkPro = new JMenuItem("Link Vocabulary/Profile");
		mntmLinkPro.addActionListener(new MntmLinkProActionListener());

		JMenuItem mntmUnLinkPro = new JMenuItem("Unlink Vocabulary/Profile");
		mntmUnLinkPro.addActionListener(new MntmUnLinkProActionListener());

		JMenuItem mntmCharacters = new JMenuItem("Characters");
		mntmCharacters.addActionListener(new MntmCharactersActionListener());

		JMenuItem mntmVocabulary = new JMenuItem("Vocabulary");
		mntmVocabulary.addActionListener(new MntmVocabularyActionListener());

		JMenuItem mntmAbout = new JMenuItem("About");
		mntmAbout.addActionListener(new MntmAboutActionListener());

		JMenuItem mntmCheckForUpdates = new JMenuItem("Check for Updates");
		mntmCheckForUpdates.addActionListener(new MntmCheckForUpdatesActionListener());

		JCheckBoxMenuItem chckbxmntmEnableSounds = new JCheckBoxMenuItem("Enable Sounds");
		chckbxmntmEnableSounds.addActionListener(new ChckbxmntmEnableSoundsActionListener());

		JCheckBoxMenuItem chckbxmntmSoundOnNew = new JCheckBoxMenuItem("Sound on new Vocabulary");
		chckbxmntmSoundOnNew.addActionListener(new ChckbxmntmSoundOnNewActionListener());

		JCheckBoxMenuItem chckbxmntmRemoveSkippedWords = new JCheckBoxMenuItem("Remove Skipped Words");
		chckbxmntmRemoveSkippedWords.addActionListener(new ChckbxmntmRemoveSkippedWordsActionListener());

		JCheckBoxMenuItem chckbxmntmChronologicalOrder = new JCheckBoxMenuItem("Chronological Order");
		chckbxmntmChronologicalOrder.addActionListener(new ChckbxmntmChronologicalOrderActionListener());

		JCheckBoxMenuItem chckbxmntmTouchMode = new JCheckBoxMenuItem("Bigger Window");
		chckbxmntmTouchMode.addActionListener(new ChckbxmntmTouchModeListener());

		JMenuItem mntmPoolNewWords = new JMenuItem("Word Pool: New Words");
		mntmPoolNewWords.addActionListener(new MntmNewWordsActionListener());

		JMenuItem mntmPoolWrongWords = new JMenuItem("Word Pool: Wrong Words");
		mntmPoolWrongWords.addActionListener(new MntmWrongWordsActionListener());

		JMenuItem mntmPoolHasKanji = new JMenuItem("Word Pool: Has Kanji");
		mntmPoolHasKanji.addActionListener(new MntmHasKanjiActionListener());

		JMenuItem mntmPoolOldWords = new JMenuItem("Word Pool: Repeat Old Words");
		mntmPoolOldWords.addActionListener(new MntmPoolOldWordsActionListener());

		menuBar.add(mnProgram);
		menuBar.add(mnProfile);
		menuBar.add(mnTraining);
		menuBar.add(mnSettings);
		menuBar.add(mnFilter);
		menuBar.add(mnInfo);
		menuBar.add(mnDebug);

		mnProgram.add(mntmExit);

		mnProfile.add(mntmSave);

		mnTraining.add(mntmCharacters);
		mnTraining.add(mntmVocabulary);

		mnSettings.add(chckbxmntmEnableSounds);
		mnSettings.add(chckbxmntmSoundOnNew);
		mnSettings.add(chckbxmntmRemoveSkippedWords);
		mnSettings.add(new JPanel());
		mnSettings.add(chckbxmntmChronologicalOrder);
		mnSettings.add(new JPanel());
		mnSettings.add(chckbxmntmTouchMode);
		mnSettings.add(new JPanel());

		mnFilter.add(mntmPoolNewWords);
		mnFilter.add(mntmPoolWrongWords);
		mnFilter.add(mntmPoolHasKanji);
		mnFilter.add(mntmPoolOldWords);

		mnInfo.add(mntmAbout);
		mnInfo.add(mntmCheckForUpdates);

		mnDebug.add(mnLoadAllSounds);
		mnDebug.add(mntmReloadSound);
		mnDebug.add(mntmLoadGrp);
		mnDebug.add(mntmLoadVoc);
		mnDebug.add(mntmLoadPro);
		mnDebug.add(mntmLinkPro);
		mnDebug.add(mntmUnLinkPro);

		placeComponents();
		setLocationRelativeTo(null);
	}

	private void placeComponents() {
		Log.event("placeComponents");
		Container pan = getContentPane();

		if (Log.getBool("mode.touch")) pan.setPreferredSize(new Dimension(1280, 640));
		else pan.setPreferredSize(new Dimension(700, 350));

		pan.setSize(pan.getPreferredSize());
		pack();
		int width = pan.getWidth();
		int height = pan.getHeight();

		panelVocabulary.setBounds(0, 0, width, height);
		panelChoose.setBounds(0, 0, width, height);
		panelType.setBounds(0, 0, width, height);

		tfType.setBounds(10, 221, 194, 20);
		lblTypeHelp.setBounds(0, 11, 214, 199);
		lblType.setBounds(0, 11, 214, 199);

		tfVoc.setBounds(5, height - 27, width - 140, 20);
		panelHints.setBounds(width - 130, height - 30, 130, 30);
		panelProgress.setBounds(panelHints.getBounds());
		lblVocComment.setBounds(0, (int) round((height - 30) * 0.85), width, (int) round((height - 30) * 0.15));
		lblVocHira.setBounds(0, (int) round((height - 30) * 0.725), width / 2, (int) round((height - 30) * 0.125));
		lblVocKata.setBounds(width / 2, lblVocHira.getY(), lblVocHira.getWidth(), lblVocHira.getHeight());
		lblVoc.setBounds(0, 0, width, (int) round((height - 30) * 0.725));
	}

	private void loadXMLs() {
		Log.event("loadXML");
		loadGroups("Groups.xml");
		loadVocabulary("Vocabulary.xml");
		loadProfiles();
	}

	private void loadGroups(String fileName) {
		Log.event("loadGroups");
		try {
			ReadXMLEntryNames reader = new ReadXMLEntryNames();
			if (new File(Log.getString("path.user") + fileName).exists()) {
				String path = Log.getString("path.user") + fileName;
				Log.put("path.group", path);
				groups = new TGroups(reader.load(path));
			} else {
				URL path = (KanaWindow.class.getResource("/lib/" + fileName));
				Log.put("path.group", path);
				groups = new TGroups(reader.load(path));
				Log.put("loaded.group", groups);
			}

		} catch (ParserConfigurationException | SAXException | IOException err) {
			Log.event("err.kanaWindow.loadGroups");
			err.printStackTrace();
		}
	}

	private void loadVocabulary(String fileName) {
		Log.event("loadVocabulary");
		try {
			VocHandler reader = VocHandler.newVocHandler();
			if (new File(Log.getString("path.user") + fileName).exists()) {
				String path = Log.getString("path.user") + fileName;
				Log.put("path.vocabulary", path);
				reader.load(path);
				vocabulary = reader.getReturn();
			} else {
				URL path = (KanaWindow.class.getResource("/lib/" + fileName));
				Log.put("path.vocabulary", path);
				reader.load(path);
				vocabulary = reader.getReturn();
			}
			Log.put("loaded.vocabulary", vocabulary);

		} catch (ParserConfigurationException | SAXException | IOException e) {
			Log.event("err.kanaWindow.loadVocabulary");
			e.printStackTrace();
		}
	}

	private void loadProfiles() {
		Log.event("loadProfiles");
		try {
			profile = loadProfile("default");
			Log.put("loaded.profile", profile);
			if (profile == null) profile = new Profile();
			loadProfile();
		} catch (ParserConfigurationException | SAXException | IOException e) {
			Log.event("err.kanaWindow.loadProfiles");
			e.printStackTrace();
		}
	}

	private Profile loadProfile(String name) {
		File f = new File(Log.getString("path.user") + "profiles\\" + name + ".xml");
		if (f.exists() && name.compareToIgnoreCase("profiles") != 0) {
			Log.event("loadProfile: " + name);
			try {
				ProfileHandler reader = ProfileHandler.newProfileHandler();
				reader.load(f);
				return reader.getReturn();
			} catch (UnsupportedEncodingException | FileNotFoundException | ParserConfigurationException | SAXException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	private Profile[] loadProfile() throws ParserConfigurationException, SAXException, IOException {
		ReadXMLEntryNames reader = new ReadXMLEntryNames();
		ArrayList<String> list = reader.load(Log.getString("path.user") + "profiles.xml");

		if (list == null) return null;
		Profile[] profiles = new Profile[list.size()];
		for (int n = 0; n < list.size(); n++) {
			profiles[n] = loadProfile(list.get(n));
		}
		return profiles;
	}

	private class MntmExitActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			System.exit(0);
		}
	}

	private class MntmCharactersActionListener implements ActionListener {


		public void actionPerformed(ActionEvent arg0) {

			options = new KanaCharacterChoose(thisFrame).choose(1);
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
				lblTypeHelp.setText(convertChr(lblType.getText(), options[0], 0));
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
				options = new KanaCharacterChoose(thisFrame).choose(2);
				if (options[0] != KanaCharacterChoose.CANCEL) {
					ArrayList<Integer> choices = new JCheckDialog(thisFrame, groups.toStringArrayCount(vocabulary), 2).choose();
					if (choices != null && choices.size() > 0 && choices.get(0) >= 0) {
						currentWordPool = vocabulary.getFiltered(choices);
						showPanel(panelVocabulary);
						Log.put("vocabulary.index", -1);
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
			new KanaAbout(thisFrame);
		}
	}

	private class MntmCheckForUpdatesActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			openHTTP("http://www.hafnehau.square7.ch/kana/versioncheck.php?v=" + Version.getShortVersion());
		}
	}

	private class TfVocKeyListener extends KeyAdapter {


		public void keyReleased(KeyEvent arg0) {
			if (options[0] == 2) {
				lblVocHira.setText(convertStr(tfVoc.getText(), 0, 1));
				lblVocKata.setText(convertStr(tfVoc.getText(), 0, 2));
				strechFont(lblVocHira);
				strechFont(lblVocKata);
			}
			if (arg0.getKeyCode() == KeyEvent.VK_S && arg0.isControlDown() && (!(arg0.isShiftDown())) && (!(arg0.isAltDown()))) {
				tfVoc.setText("");
				lblVocHira.setText("");
				lblVocKata.setText("");
				if (options[1] == KanaCharacterChoose.EXAM) {
					currentWord.update(false);
					newExamAnswer(false);
				} else {
					if (Log.getBool("vocabulary.removeSkipped")) currentWordPool.remove(currentWord);
					else currentWord.update(false);
					newVocabulary();
				}
			} else if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {

				for (String answer : possibleAnswers) {
					if (!(tfVoc.getText().isEmpty())) {
						if (equalsIgnoreCase(tfVoc.getText(), answer) || equalsIgnoreCase(lblVocHira.getText(), answer)
								|| equalsIgnoreCase(lblVocKata.getText(), answer)) {
							tfVoc.setText("");
							lblVocHira.setText("");
							lblVocKata.setText("");
							currentWord.update(true);
							if (options[1] == KanaCharacterChoose.EXAM) newExamAnswer(true);
							else newVocabulary();
						}
					}
				}
			}
		}

		public void keyPressed(KeyEvent arg0) {
			if (arg0.getKeyCode() == KeyEvent.VK_F1) {
				new VocHelp(thisFrame, currentWord, groups.get(currentWord.getGroup()));
			} else if (arg0.getKeyCode() == KeyEvent.VK_ESCAPE && options[1] != KanaCharacterChoose.EXAM) {
				Log.put("vocabulary.Index", -1);
			} else if (arg0.getKeyCode() == KeyEvent.VK_F3) {
				new KanaResult(thisFrame, currentWordPool, profile);
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
			Log.put("sounds.enabled", check.isSelected());
			if (check.isSelected()) showmessage("Enabling this will read out the word you're translating in Vocabulary mode when pressing F1."
					+ "\nIt is using Google's Text-To-Speech Service and JLayer for MP3-output."
					+ "\nIt may or may not work. If it doesn't, just disable this feature by clicking 'Enable Sounds' again.");
		}
	}

	private class ChckbxmntmSoundOnNewActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			Log.put("sounds.onVocabulary", ((JCheckBoxMenuItem) arg0.getSource()).isSelected());
		}
	}

	private class MntmReloadSoundActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			if (Log.getBool("sounds.enabled")) {
				vocabulary.loadSound(currentWord, false, true);
			}
		}
	}

	private class ChckbxmntmRemoveSkippedWordsActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			JCheckBoxMenuItem ch = (JCheckBoxMenuItem) arg0.getSource();
			Log.put("vocabulary.removeSkipped", ch.isSelected());
		}
	}

	private class ChckbxmntmChronologicalOrderActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			JCheckBoxMenuItem ch = (JCheckBoxMenuItem) arg0.getSource();
			Log.put("vocabulary.order.chronological", ch.isSelected());
		}
	}

	private class MntmNewWordsActionListener implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			ArrayList<TWord> words = new ArrayList<TWord>();
			int threshold = Integer.valueOf(JOptionPane.showInputDialog(thisFrame, "Threshold number", "0"));
			for (TWord word : currentWordPool) {
				if (word.getSuccess().getNumber() <= threshold) words.add(word);
			}
			if (words.size() > 0) {
				currentWordPool = words;
				showPanel(panelVocabulary);
				newVocabulary();
			} else showmessage("There are no new words.");
		}
	}

	private class MntmWrongWordsActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			ArrayList<TWord> words = new ArrayList<TWord>();
			double threshold = Double.valueOf(JOptionPane.showInputDialog(thisFrame, "Threshold in percent", "50")) / 100.0;
			for (TWord word : currentWordPool) {
				if (word.getSuccess().getNumber() > 0 && word.getSuccess().getRatio() <= threshold) words.add(word);
			}
			if (words.size() > 0) {
				currentWordPool = words;
				showPanel(panelVocabulary);
				newVocabulary();
			} else showmessage("There are no new words.");
		}
	}

	private class MntmHasKanjiActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			ArrayList<TWord> words = new ArrayList<TWord>();
			for (TWord word : currentWordPool) {
				if (word.hasKanji()) words.add(word);
			}
			if (words.size() > 0) {
				currentWordPool = words;
				showPanel(panelVocabulary);
				newVocabulary();
			} else showmessage("There are no new words.");
		}
	}

	private class MntmPoolOldWordsActionListener implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			ArrayList<TWord> words = new ArrayList<TWord>();
			int threshold = Integer.valueOf(JOptionPane.showInputDialog(thisFrame, "Last time in days", "1"));
			for (TWord word : currentWordPool) {
				if (CodeLibary.daysPast(word.getSuccess().getTimestamp()) >= threshold) {
					words.add(word);
				}
			}
			if (words.size() > 0) {
				currentWordPool = words;
				showPanel(panelVocabulary);
				newVocabulary();
			} else showmessage("There are no new words.");

		}
	}

	private class ChckbxmntmTouchModeListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			JCheckBoxMenuItem ch = (JCheckBoxMenuItem) arg0.getSource();
			Log.put("mode.touch", ch.isSelected());
			// Thread t = new Thread(new RefreshThread());
			// t.start();
			placeComponents();
		}
	}

	private class MntmSaveActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			try {
				profile.save();
			} catch (UnsupportedEncodingException | FileNotFoundException | XMLStreamException e) {
				e.printStackTrace();
			}
		}
	}

	private class MntmLoadGrpActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			loadGroups("Groups.xml");
		}
	}

	private class MntmLoadVocActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			loadVocabulary("Vocabulary.xml");
		}
	}

	private class MntmLoadProActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			loadProfiles();
		}
	}

	private class MntmLinkProActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			vocabulary.assignProfile(profile);
		}
	}

	private class MntmUnLinkProActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			vocabulary.unassignProfile();
		}
	}

	/*class RefreshThread implements Runnable {
		public void run() {
			while (Log.getBool("Mode.Touch")) {
				try {
					placeComponents();
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}*/

	private void newType() {
		int val;
		if (options[0] != KanaCharacterChoose.KANJI) {
			do {
				val = randInt(getAlphabet().length - 1);
			} while (convertChr(getAlphabet()[val], 0, options[0]) == lblType.getText());
			lblType.setText(convertChr(getAlphabet()[val], 0, options[0]));
			tfType.requestFocus();
		} else {}
	}

	private void newExam() {
		Log.event("exam.started");
		examIndex = -1;
		examCorrect = new ArrayList<TWord>();
		examWrong = new ArrayList<TWord>();
		if (Log.getBool("vocabulary.order.chronological")) {
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
			new KanaResult(thisFrame, examWrong, examCorrect, profile);
		}
	}

	private void showVoc(TWord word) {
		Log.event("showVoc");
		panelProgress.setVisible(options[1] == KanaCharacterChoose.EXAM);

		if (Log.getBool("sounds.enabled")) {
			vocabulary.loadSound(word, Log.getBool("sounds.onVocabulary"), false);
		}
		String from;
		// CALCULATE TRANSLATION AND POSSIBLE ANSWERS
		if (options[0] == 1) {
			if (word.hasKanji()) from = currentWord.getKanji();
			else from = word.getKana();
			if (word.hasPresent()) from += " / " + word.getPresent();
			lblVocHira.setText("");
			lblVocKata.setText("");
			possibleAnswers = vocabulary.getPossibleEngl(word);
		} else {
			from = word.getEngl().get(randInt(word.getEngl().size() - 1));
			possibleAnswers = vocabulary.getPossibleKana(from);
		}
		// SET TEXT
		lblVoc.setText(from);
		strechFont(lblVoc);
		if (word.hasComment()) lblVocComment.setText("(" + word.getComment() + ")");
		else lblVocComment.setText("");
		strechFont(lblVocComment);

		// FOCUS TEXTFIELD
		tfVoc.requestFocus();
		// profile.debug();
	}

	private int getNext() {
		Integer index = (Integer) Log.get("vocabulary.index").getValue();
		if (index < currentWordPool.size() - 1) return index + 1;
		else return 0;

	}

	private void newVocabulary() {
		int val = 0;
		if (currentWordPool.size() > 0) {
			if (Log.getBool("vocabulary.order.chronological")) val = getNext();
			else do {
				val = randInt(currentWordPool.size() - 1);
			} while (currentWordPool.get(val) == currentWord && currentWordPool.size() > 1);
			Log.put("vocabulary.index", val);
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
		Log.event("repaint");
		this.repaint();
	}
}