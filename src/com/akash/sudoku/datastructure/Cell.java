package com.akash.sudoku.datastructure;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Cell implements ISudokuPart{
	
	private int value;
	
	private boolean filled;
	
	private List<Number> possibleValues;

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

	public List<Number> getPossibleValues() {
		return possibleValues;
	}

	public void setPossibleValues(List<Number> possibleValues) {
		this.possibleValues = possibleValues;
		Optional<Number> value = possibleValues.stream().filter(number -> number.getProbability() == 100).findFirst();
		if(value.isPresent()) {
			this.value = value.get().getValue();
			this.filled = true;
			this.possibleValues = new ArrayList<>();
		}
	}
	
	@Override
	public int getEntropy() {
		return possibleValues.size();
	}

}
