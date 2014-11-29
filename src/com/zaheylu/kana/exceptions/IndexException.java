package com.zaheylu.kana.exceptions;

import com.zaheylu.kana.users.Profile;
import com.zaheylu.kana.users.SuccessEntry;
import com.zaheylu.kana.words.TVocabulary;
import com.zaheylu.kana.words.TWord;

public class IndexException extends Exception {

	public IndexException() {
		super();
	}

	public IndexException(String message) {
		super(message);
	}

	public IndexException(String message, Throwable cause) {
		super(message, cause);
	}

	public IndexException(Throwable cause) {
		super(cause);
	}

	public IndexException(TWord word, SuccessEntry se) {
		super("Ex on " + word.toString() + " and " + se.toString() + "; " + word.getIndex() + "=!" + se.getIndex());
	}

	public IndexException(int i, SuccessEntry se) {
		super("Ex on " + se.toString() + "; Index :" + i + "=!" + se.getIndex());
	}

	public IndexException(int i, TWord word) {
		super("Ex on " + word.toString() + "; Index :" + i + "=!" + word.getIndex());
	}

	public IndexException(Profile p, TVocabulary voc) {
		super("Cannot load Profile because there was an error loading the vocabulary:" + p.toString() + "; " + voc.toString());
	}
}
