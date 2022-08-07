package com.akash.sudoku.datastructure;

import java.util.ArrayList;
import java.util.List;

public class Column implements ISudokuCollectionPart {
	
	private List<Cell> columnCells;
	
	private int size;
	
	public Column(int size) {
		this.columnCells = new ArrayList<>();
		this.size = size;
	}
	
	public void addCell(Cell cell) {
		this.columnCells.add(cell);
	}

	@Override
	public int getEntropy() {
		return columnCells.stream().mapToInt(Cell::getEntropy).sum();
	}

	@Override
	public List<Integer> missingNumbers(int size) {
		// TODO Auto-generated method stub
		return null;
	}

}
