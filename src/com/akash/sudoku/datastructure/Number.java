package com.akash.sudoku.datastructure;

public class Number {
	
	private int value;

	private double subSquareProbability;
	
	private double rowProbability;
	
	private double columnProbability;
	
		
	public int getValue() {
		return value;
	}


	public void setValue(int value) {
		this.value = value;
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
		return Math.max(subSquareProbability, Math.max(rowProbability, columnProbability));
	}
}
