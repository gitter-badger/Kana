package com.zaheylu.kana.words;

import java.util.ArrayList;

import com.zaheylu.kana.exceptions.IndexException;
import com.zaheylu.kana.users.SuccessEntry;
import com.zaheylu.snippets.CodeLibary;

public class TWord {

	private ArrayList<String> engl;
	private String kana;
	private String romaji;
	private String comment;
	private String kanji;
	private String present;
	private int group;
	private int index;
	private SuccessEntry success;

	/**
	 * Returns a dummy word
	 * 
	 * @param i
	 *            dummy index
	 * @return a dummy word
	 */
	public static TWord DummyTWord(int i) {
		TWord result = new TWord();
		result.setComment(String.valueOf(i));
		result.setGroup(String.valueOf(i));
		result.setKana(String.valueOf(i));
		result.setKanji(String.valueOf(i));
		result.setPresent(String.valueOf(i));
		result.setRomaji(String.valueOf(i));
		result.addEngl(String.valueOf(i));
		result.addEngl(String.valueOf(i));
		result.setSuccess(new SuccessEntry(i));
		return result;
	}

	public TWord() {
		engl = new ArrayList<String>();
		group = 0;
	}

	/**
	 * 
	 * @param index
	 *            initializes the index of the word and the SuccessEntry
	 */
	public TWord(int index) {
		this.index = index;
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

	/**
	 * Adds a String to Engl
	 * 
	 * @param added
	 *            new Engl String
	 */
	public void addEngl(String added) {
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

	/**
	 * Included casting
	 * 
	 * @param group
	 *            new group
	 */
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

	/**
	 * 
	 * @return engl.size() > 0
	 */
	private boolean hasEngl() {
		return (engl.size() > 0);
	}

	/**
	 * 
	 * @return group >= 0
	 */
	private boolean hasGroup() {
		return (group >= 0);
	}

	/**
	 * Is valid if the word (hasKana() || hasKanji()) && hasEngl() && hasGroup()
	 * 
	 * @return validity
	 */
	public boolean isValid() {
		return ((hasKana() || hasKanji()) && hasEngl() && hasGroup());
	}

	public int getIndex() {
		return index;
	}

	/**
	 * Sets the index and the index of the SuccessEntry
	 * 
	 * @param index
	 *            index
	 */
	public void setIndex(int index) {
		this.index = index;
		if (success != null) success.setIndex(index);
	}

	public SuccessEntry getSuccess() {
		return success;
	}

	/**
	 * sets the success, will throw an exception if there's an index mismatch
	 * 
	 * @param success
	 *            new SE
	 */
	public void setSuccess(SuccessEntry success) {
		this.success = success;
		try {
			if (success.getIndex() != index) throw new IndexException(this, success);
		} catch (IndexException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setNullSuccess() {
		success = null;
	}

	/**
	 * SE method
	 * 
	 * @return success.getRatio();
	 */
	public double getRatio() {
		if (success != null) return success.getRatio();
		return 0;
	}

	/**
	 * SE method
	 * 
	 * @param number
	 *            additive number
	 * @param success
	 *            additive success
	 */
	public void update(int number, int success) {
		if (this.success != null) this.success.update(number, success);
	}

	/**
	 * SE method
	 * 
	 * @param result
	 *            updatebool
	 */
	public void update(boolean result) {
		if (success != null) success.update(result);
	}

	/**
	 * toString()
	 * 
	 * @return the first english String and the index
	 */
	public String toString() {
		if (hasEngl()) return "[" + index + "]:" + getEngl().get(0);
		else return "[" + index + "]:TWord";
	}

	public boolean hasSuccess() {
		if (success == null) return false;
		if (success.getTimestamp() == 0) return false;
		return true;
	}
	
	public String getTimePastString() {
		if (!hasSuccess()) return "-";
		return CodeLibary.timePast(success.getTimestamp());
	}

}
