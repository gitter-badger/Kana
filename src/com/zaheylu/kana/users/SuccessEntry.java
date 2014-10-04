package com.zaheylu.kana.users;

public class SuccessEntry {
	private int success;
	private int number;
	private int index;

	public double getRatio() {
		if (number == 0) return 0.5;
		return ((double) success) / ((double) number);
	}

	public SuccessEntry() {
		number = 0;
		success = 0;
	}

	public void update(boolean result) {
		if (result) success++;
		number++;
	}

	public SuccessEntry(int index) {
		this.index = index;
		number = 0;
		success = 0;
	}

	public SuccessEntry(int success, int number) {
		this.success = success;
		this.number = number;
	}

	public int getSuccess() {
		return success;
	}

	public void setSuccess(int success) {
		this.success = success;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
}