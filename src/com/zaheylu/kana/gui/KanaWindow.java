package com.zaheylu.kana.gui;

import static com.zaheylu.kana.KanaLib.*;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.ImageIcon;
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
import com.zaheylu.kana.KanaCharacterChoose;
import com.zaheylu.kana.Version;
import com.zaheylu.kana.words.TGroups;
import com.zaheylu.kana.words.TVocabulary;
import com.zaheylu.kana.words.TWord;
import com.zaheylu.kana.xml.ReadXMLEntryNames;
import com.zaheylu.kana.xml.ReadXMLVocabulary;
import com.zaheylu.log.Log;

import static com.zaheylu.snippets.CodeLibary.*;


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
	private JLabel lblVocHelp;
	private JLabel lblVocHira;
	private JLabel lblVocKata;
	private JLabel lblVoc;
	private JLabel lblVocComment;

	private TVocabulary vocabulary;
	private TGroups groups;
	private TWord currentVoc;
	private ArrayList<TWord> currentWordPool;
	private ArrayList<String> possibleAnswers;
	private KanaWindow thisFrame = this;


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
		tfVoc.addKeyListener(new TfVocKeyListener(this));
		tfVoc.setBounds(10, 215, 476, 20);
		tfVoc.setColumns(10);
		panelVocabulary.add(tfVoc);

		lblVocHelp = new JLabel("A");
		lblVocHelp.setForeground(Color.RED);
		lblVocHelp.setHorizontalAlignment(SwingConstants.CENTER);
		lblVocHelp.setFont(new Font("MS Gothic", Font.BOLD, 120));
		lblVocHelp.setBounds(0, 11, 608, 126);
		lblVocHelp.setVisible(false);
		panelVocabulary.add(lblVocHelp);

		lblVoc = new JLabel("B");
		lblVoc.setBounds(0, 11, 608, 126);
		panelVocabulary.add(lblVoc);
		lblVoc.setHorizontalAlignment(SwingConstants.CENTER);
		lblVoc.setFont(new Font("MS Gothic", Font.BOLD, 120));

		lblVocComment = new JLabel("");
		lblVocComment.setFont(new Font("MS Gothic", Font.PLAIN, 22));
		lblVocComment.setHorizontalAlignment(SwingConstants.CENTER);
		lblVocComment.setBounds(0, 174, 608, 30);
		panelVocabulary.add(lblVocComment);

		lblVocHira = new JLabel("");
		lblVocHira.setFont(new Font("MS Gothic", Font.PLAIN, 25));
		lblVocHira.setBounds(0, 130, 303, 39);
		panelVocabulary.add(lblVocHira);

		lblVocKata = new JLabel("");
		lblVocKata.setFont(new Font("MS Gothic", Font.PLAIN, 25));
		lblVocKata.setBounds(315, 130, 293, 39);
		panelVocabulary.add(lblVocKata);

		JLabel lblPressEnter = new JLabel("Press Enter");
		lblPressEnter.setBounds(496, 218, 68, 14);
		panelVocabulary.add(lblPressEnter);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnProgram = new JMenu("Program");
		menuBar.add(mnProgram);

		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new MntmExitActionListener());

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
				vocabulary = vocReader.loadVocabulary(path);
			} else {
				URL path = (KanaWindow.class.getResource("/lib/" + fileName));
				Log.setLog("Path.Vocabulary", path);
				vocabulary = vocReader.loadVocabulary(path);
				Log.setLog("Loaded.Vocabulary", vocabulary);
			}

		} catch (ParserConfigurationException | SAXException | IOException e) {
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
				if (options[0] != -1) {
					ArrayList<Integer> choices = new JCheckDialog(thisFrame, groups.toStringArray(vocabulary), 2).choose();
					if (choices != null && choices.size() > 0 && choices.get(0) >= 0) {
						currentWordPool = vocabulary.getFiltered(choices);
						showPanel(panelVocabulary);
						newVocabulary();
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
			if (Desktop.isDesktopSupported()) {
				try {
					Desktop.getDesktop().browse(new URI("http://www.hafnehau.square7.ch/kana/versioncheck.php?v=" + Version.getShortVersion()));
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				showmessage("Please visit http://www.hafnehau.square7.ch/kana/versioncheck.php?v=" + Version.getShortVersion());
			}
		}
	}

	private class TfVocKeyListener extends KeyAdapter {
		private JFrame frame;

		public TfVocKeyListener(JFrame frame) {
			this.frame = frame;
		}

		public void keyReleased(KeyEvent arg0) {
			if (arg0.getKeyCode() == KeyEvent.VK_F1) {
				lblVocHelp.setVisible(false);
			}
			if (arg0.getKeyCode() == KeyEvent.VK_S && arg0.isControlDown() && (!(arg0.isShiftDown())) && (!(arg0.isAltDown()))) {
				tfVoc.setText("");
				lblVocHira.setText("");
				lblVocKata.setText("");
				newVocabulary();
			}
			if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
				if (options[0] == 1) {
					for (int n = 0; n < currentVoc.getEngl().size(); n++) {
						if (equalsIgnoreCase(currentVoc.getEngl().get(n), tfVoc.getText())) {
							tfVoc.setText("");
							newVocabulary();
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
								newVocabulary();
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
				// lblVocHelp.setVisible(true);
				new VocHelp(frame, currentVoc, groups.get(currentVoc.getGroup()));
			}
		}

	}

	private void newType() {
		Log.event("newType");
		int val;
		if (options[0] != 3) {
			do {
				val = randInt(getAlphabet().length - 1);
			} while (convert(getAlphabet()[val], 0, options[0]) == lblType.getText());
			lblType.setText(convert(getAlphabet()[val], 0, options[0]));
			tfType.requestFocus();
		} else {
			// TODO: typeKanji
		}
	}

	public void newVocabulary() {
		Log.event("newVocabulary");
		// SELECT A NEW WORD
		int val;
		do {
			val = randInt(currentWordPool.size() - 1);
		} while (currentWordPool.get(val) == currentVoc && currentWordPool.size() > 1);
		currentVoc = currentWordPool.get(val);
		String from, to;

		// CALCULATE TRANSLATION AND POSSIBLE ANSWERS
		if (options[0] == 1) {
			if (currentVoc.hasKanji()) from = currentVoc.getKanji();
			else from = currentVoc.getKana();
			if (currentVoc.hasPresent()) from += " / " + currentVoc.getPresent();
			to = currentVoc.getEngl().get(randInt(currentVoc.getEngl().size() - 1));
			lblVocHira.setText("");
			lblVocKata.setText("");

		} else {
			from = currentVoc.getEngl().get(randInt(currentVoc.getEngl().size() - 1));
			to = currentVoc.getKana();
			possibleAnswers = getPossibleAnswers(from);
		}
		// SET TEXT
		lblVoc.setText(from);
		strechLbl(lblVoc);
		lblVocHelp.setText(to);
		strechLbl(lblVocHelp);
		if (currentVoc.hasComment()) lblVocComment.setText("(" + currentVoc.getComment() + ")");
		else lblVocComment.setText("");
		strechLbl(lblVocComment);

		// FOCUS TEXTFIELD
		tfVoc.requestFocus();

	}

	public void newChoose() {
		Log.event("newChoose");
		showmessage("This feature is not implemented yet.\nI would kindly like to apologize for the inconvenience this may cause.");
	}

	public void showPanel(JPanel panel) {
		Log.event("showPanel");
		panelVocabulary.setVisible(false);
		panelType.setVisible(false);
		panelChoose.setVisible(false);
		if (panel != null) panel.setVisible(true);
		f5();
	}

	public void f5() {
		Log.event("Repaint");
		this.repaint();
	}

	public ArrayList<String> getPossibleAnswers(String arg) {
		Log.event("getPossibleAnswers");
		ArrayList<String> result = new ArrayList<String>();
		for (int n = 0; n < vocabulary.size(); n++)
			for (int m = 0; m < vocabulary.get(n).getEngl().size(); m++) {
				if (equalsIgnoreCase(vocabulary.get(n).getEngl().get(m), arg)) {
					result.add(vocabulary.get(n).getKana());
					if (vocabulary.get(n).hasRomaji()) result.add(vocabulary.get(n).getRomaji());
					if (vocabulary.get(n).hasPresent()) result.add(vocabulary.get(n).getPresent());
				}
			}
		return result;
	}
}