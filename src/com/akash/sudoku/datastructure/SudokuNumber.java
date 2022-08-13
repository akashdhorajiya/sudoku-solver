package com.akash.sudoku.datastructure;

public class SudokuNumber {
	
	private int value;
	
	private double probability;

	private double subSquareProbability;
	
	private double rowProbability;
	
	private double columnProbability;
	
	public SudokuNumber(int value) {
		this.value = value;
		this.probability = 0.0;
		this.columnProbability = 0.0;
		this.rowProbability = 0.0;
		this.subSquareProbability = 0.0;
	}
		
	public int getValue() {
		return value;
	}


	public void setValue(int value) {
		this.value = value;
	}


	public void setProbability(double probability) {
		this.probability = probability;
	}

	public double getSubSquareProbability() {
		return subSquareProbability;
	}


	public void setSubSquareProbability(double subSquareProbability) {
		this.subSquareProbability = subSquareProbability;
	}


	public double getRowProbability() {
		return rowProbability;
	}


	public void setRowProbability(double rowProbability) {
		this.rowProbability = rowProbability;
	}


	public double getColumnProbability() {
		return columnProbability;
	}


	public void setColumnProbability(double columnProbability) {
		this.columnProbability = columnProbability;
	}


	public double getProbability() {
		return Math.max(subSquareProbability, Math.max(rowProbability, Math.max(probability, columnProbability)));
	}
}
