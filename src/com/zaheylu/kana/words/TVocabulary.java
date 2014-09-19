package com.zaheylu.kana.words;

import java.util.ArrayList;

public class TVocabulary {

	public ArrayList<TWord> words;

	public TVocabulary() {
		words = new ArrayList<TWord>();
	}

	public void add(TWord word) {
		words.add(word);
	}

	public ArrayList<TWord> getFiltered(ArrayList<Integer> filter) {
		ArrayList<TWord> result = new ArrayList<TWord>();
		for (int i = 0; i < words.size(); i++) {
			int iGroup = words.get(i).getGroup();
			for (int n = 0; n < filter.size(); n++) {
				if (iGroup == filter.get(n)) result.add(words.get(i));
			}
		}
		return result;
	}

	public ArrayList<TWord> getFiltered(int filter) {
		ArrayList<TWord> result = new ArrayList<TWord>();
		for (int i = 0; i < words.size(); i++) {
			int iGroup = words.get(i).getGroup();
			if (iGroup == filter) result.add(words.get(i));
		}
		return result;
	}



	public TWord get(int i) {
		return words.get(i);
	}

	public int size() {
		return words.size();
	}

	public int size(int group) {
		int result = 0;
		for (int i = 0; i < words.size(); i++) {
			int iGroup = words.get(i).getGroup();
			if (iGroup == group) result++;
		}
		return result;
	}
}