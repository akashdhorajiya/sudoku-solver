package com.akash.sudoku.datastructure;

import java.util.ArrayList;
import java.util.List;

public class SudokuSquare implements ISudokuPart{
	
	private List<Row> square;
	
	private int size;
	
	private static SudokuSquare sudoku;
	
	public static SudokuSquare getInstance(int size) {
		if(sudoku == null) {
			sudoku = new SudokuSquare(size);
		}
		return sudoku;
	}
	
	private SudokuSquare(int size) {
		square = new ArrayList<>();
		this.size = size;
	}
	
	public void addRow(Row row) {
		if(square.size() < size) {
			this.square.add(row);
		}
	}

	@Override
	public int getEntropy() {
		return square.stream().mapToInt(Row::getEntropy).sum();
	}

}
