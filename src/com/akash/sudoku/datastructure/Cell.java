package com.akash.sudoku.datastructure;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class Cell implements ISudokuPart{
	
	private int value;
	
	private boolean filled;
	
	private int maxPossibleNumber;
	
	private Set<SudokuNumber> possibleValues;
	
	public Cell(int value, int maxPossibleNumber) {
		this.value = value;
		this.filled = value != 0;
		this.maxPossibleNumber = maxPossibleNumber;
		possibleValues = new HashSet<>();
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
		this.filled = true;
		this.possibleValues = new HashSet<>();
	}

	public boolean isFilled() {
		return filled;
	}

	public Set<SudokuNumber> getPossibleValues() {
		return possibleValues;
	}

	public void setPossibleValues(Set<SudokuNumber> possibleValues) {
		this.possibleValues = possibleValues;
		Optional<SudokuNumber> value = possibleValues.stream().filter(number -> number.getProbability() == 1.0).findFirst();
		if(value.isPresent()) {
			setValue(value.get().getValue());
		}
	}
	
	public boolean isNumberInPossibleValues(int number) {
		if(possibleValues.isEmpty()) {
			return true;
		}
		return possibleValues.stream().anyMatch(value -> value.getValue()==number);
	}
	
	@Override
	public int getEntropy() {
		if(filled) {
			return 0;
		} 
		return possibleValues.isEmpty() ? maxPossibleNumber : possibleValues.size();
	}

	@Override
	public void print() {
		System.out.println("Possible numbers :");
		possibleValues.forEach(possibleValue -> {
			System.out.println(possibleValue.getValue() + " : R-" + possibleValue.getRowProbability() 
			+ " ; C-" + possibleValue.getColumnProbability() + " ; S-" + possibleValue.getSubSquareProbability()
			+ " ; T-" + possibleValue.getProbability());
		});
	}

}
