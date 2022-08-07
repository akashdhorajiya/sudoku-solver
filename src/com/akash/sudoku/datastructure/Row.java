package com.akash.sudoku.datastructure;

import java.util.ArrayList;
import java.util.List;

public class Row implements ISudokuCollectionPart{
	
	private List<Cell> rowCells;
	
	private int size;
	
	public Row(int size) {
		this.rowCells = new ArrayList<>();
		this.size = size;
	}
	
	public void addCell(Cell cell) {
		this.rowCells.add(cell);
	}

	@Override
	public int getEntropy() {
		return rowCells.stream().mapToInt(Cell::getEntropy).sum();
	}

	@Override
	public List<Integer> missingNumbers() {
		// TODO Auto-generated method stub
		return null;
	}

}
