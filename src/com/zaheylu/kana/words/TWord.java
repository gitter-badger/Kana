package com.zaheylu.kana.words;

import java.util.ArrayList;

public class TWord {

	private ArrayList<String> engl;
	private String kana;
	private String romaji;
	private String comment;
	private String kanji;
	private String present;
	private int group;

	public static TWord getDummyTWord(int i) {
		TWord result = new TWord();
		result.setComment(String.valueOf(i));
		result.setGroup(String.valueOf(i));
		result.setKana(String.valueOf(i));
		result.setKanji(String.valueOf(i));
		result.setPresent(String.valueOf(i));
		result.setRomaji(String.valueOf(i));
		result.addEngl(String.valueOf(i));
		result.addEngl(String.valueOf(i));
		
		return result;
	}
	
	public TWord() {
		engl = new ArrayList<String>();
		group = 0;
	}

	public ArrayList<String> getEngl() {
		return engl;
	}

	public void setEngl(ArrayList<String> engl) {
		this.engl = engl;
	}

	public String getKana() {
		return kana;
	}

	public void setKana(String kana) {
		this.kana = kana;
	}

	public String getRomaji() {
		return romaji;
	}

	public void setRomaji(String romaji) {
		this.romaji = romaji;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void addEngl(String added) {
		if (engl == null) engl = new ArrayList<String>();
		engl.add(added);
	}

	public String getKanji() {
		return kanji;
	}

	public void setKanji(String kanji) {
		this.kanji = kanji;
	}

	public int getGroup() {
		return group;
	}

	public void setGroup(int group) {
		this.group = group;
	}

	public void setGroup(String group) {
		this.group = Integer.valueOf(group);
	}

	public String getPresent() {
		return present;
	}

	public void setPresent(String present) {
		this.present = present;
	}
	
	public boolean hasComment() {
		return (comment != null);
	}
	
	public boolean hasRomaji() {
		return (romaji != null);
	}

	public boolean hasKanji() {
		return (kanji != null);
	}
	
	public boolean hasPresent() {
		return (present != null);
	}
	
	private boolean hasKana() {
		return (kana != null);
	}
	
	private boolean hasEngl() {
		return (engl.size() > 0);
	}

	public boolean isValid() {
		return ((hasKana() || hasKanji()) && hasEngl());
	}


}
