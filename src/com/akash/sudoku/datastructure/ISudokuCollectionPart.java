package com.akash.sudoku.datastructure;

import java.util.List;
import java.util.Set;

public interface ISudokuCollectionPart extends ISudokuPart{

	Set<Integer> missingNumbers();
	
	double getProbabilityForNumber(int number);
	
	void addCell(Cell cell);
	
	Cell getCell(int index);
	
	List<Cell> getCells();
}
