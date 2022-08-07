package com.akash.sudoku.datastructure;

import java.util.List;

public class Cell implements ISudokuPart{
	
	private int value;
	
	private boolean filled;
	
	private List<Integer> possibleValues;

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public boolean isFilled() {
		return filled;
	}

	public void setFilled(boolean filled) {
		this.filled = filled;
	}

	public List<Integer> getPossibleValues() {
		return possibleValues;
	}

	public void setPossibleValues(List<Integer> possibleValues) {
		this.possibleValues = possibleValues;
	}
	
	@Override
	public int getEntropy() {
		return possibleValues.size();
	}

}
