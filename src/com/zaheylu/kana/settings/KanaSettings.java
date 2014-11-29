package com.zaheylu.kana.settings;

import java.util.ArrayList;

import com.zaheylu.kana.users.Profile;
import com.zaheylu.kana.words.TGroups;
import com.zaheylu.kana.words.TVocabulary;
import com.zaheylu.kana.words.TWord;

public class KanaSettings {

	private KanaSettings() {}

	static {
		options = new int[2];
		profile = new Profile();
	}
	public static int[] options;
	public static Profile profile;
	public static TVocabulary vocabulary;
	public static TGroups groups;
	public static TWord currentWord;
	public static ArrayList<TWord> currentWordPool;
	public static ArrayList<String> possibleAnswers;
	public static int examIndex;
	public static ArrayList<TWord> examCorrect;
	public static ArrayList<TWord> examWrong;
	public static int[] examOrder;
}
