package com.akash.sudoku.datastructure;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SudokuPart implements ISudokuCollectionPart{
	
	private List<Cell> cells;
	
	private int size;
	
	public SudokuPart(int size) {
		this.cells = new ArrayList<>();
		this.size = size;
	}
	
	@Override
	public void addCell(Cell cell) {
		this.cells.add(cell);
	}
	
	@Override
	public Cell getCell(int index) {
		return this.cells.get(index);
	}

	@Override
	public int getEntropy() {
		return cells.stream().mapToInt(Cell::getEntropy).sum();
	}

	@Override
	public Set<Integer> missingNumbers() {
		Set<Integer> missingNumbers = new HashSet<>();
		for(int i=1; i<=size; i++) {
			missingNumbers.add(i);
		}
		cells.forEach(cell -> {
			if(cell.getValue() != 0) {
				missingNumbers.remove(cell.getValue());
			}
		});
		return missingNumbers;
	}

	@Override
	public double getProbabilityForNumber(int number) {
		boolean hasNumber = cells.stream().anyMatch(cell -> cell.getValue()==number);
		if(!hasNumber) {
			double emptyPlaces = cells.stream()
					.filter(cell -> cell.getValue() == 0 && cell.isNumberInPossibleValues(number)).count();
			return 1 / emptyPlaces;
		}
		return 0;
	}

	@Override
	public List<Cell> getCells() {
		return cells;
	}
	

	@Override
	public void print() {
		cells.forEach(cell -> {
			System.out.print("----");
		});
		System.out.print("-\n");
		cells.forEach(cell -> {
			System.out.print("| " + cell.getValue() + " ");
		});
		System.out.print("|\n");
		cells.forEach(cell -> {
			System.out.print("----");
		});
		
	}
}
