package com.zaheylu.snippets;

import java.awt.Dimension;

public class Bounds {
	private int x;
	private int y;
	private int width;
	private int height;

	public Bounds() {
		x = 0;
		y = 0;
		width = 0;
		height = 0;
	}

	public Bounds(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}



	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Dimension getPosition() {
		return new Dimension(x, y);
	}

	public void setPosition(Dimension d) {
		this.x = d.width;
		this.y = d.height;
	}

	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Dimension getSize() {
		return new Dimension(width, height);
	}

	public void setSize(Dimension d) {
		this.width = d.width;
		this.width = d.height;
	}

	public void setSize(int width, int height) {
		this.width = width;
		this.height = height;
	}




}
