package com.akash.sudoku.datastructure;

import java.util.ArrayList;
import java.util.List;

public class SubSquare implements ISudokuCollectionPart {
	
	private List<Cell> subSquareCells;
	
	private int size;
	
	public SubSquare(int size) {
		this.subSquareCells = new ArrayList<>();
		this.size = size;
	}
	
	public void addCell(Cell cell) {
		this.subSquareCells.add(cell);
	}

	@Override
	public int getEntropy() {
		return subSquareCells.stream().mapToInt(Cell::getEntropy).sum();
	}

	@Override
	public List<Integer> missingNumbers() {
		// TODO Auto-generated method stub
		return null;
	}

}
