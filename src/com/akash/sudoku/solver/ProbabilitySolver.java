package com.akash.sudoku.solver;

import java.util.HashSet;
import java.util.Set;

import com.akash.sudoku.datastructure.SudokuNumber;
import com.akash.sudoku.datastructure.SudokuSquare;

public class ProbabilitySolver implements ISudokuSolver {
	
	private static final int NO_CHANGE_THRESHOLD = 5;

	@Override
	public void solve(SudokuSquare sudoku) {
		
		int prevEntropy = sudoku.getEntropy();
		int noChangeIterationCount = 0;

		sudoku.print();
		System.out.println();
		System.out.println("Intial Entropy - " + sudoku.getEntropy());
		
		while(sudoku.getEntropy() != 0 && noChangeIterationCount < NO_CHANGE_THRESHOLD) {
			
			solutionIteration(sudoku);
			
			if(prevEntropy == sudoku.getEntropy()) {
				noChangeIterationCount++;
			} else {
				prevEntropy = sudoku.getEntropy();
				noChangeIterationCount = 0;
			}
		}
		
		System.out.println();
		System.out.println("Result:");
		if(noChangeIterationCount >= NO_CHANGE_THRESHOLD) {
			System.out.println("Sudoku remains unsolved: Entropy - " + sudoku.getEntropy());
		}
		if(sudoku.getEntropy() == 0) {
			System.out.println("Sudoku solved");
		}
		
		sudoku.print();
		
		if(noChangeIterationCount >= NO_CHANGE_THRESHOLD) {
			sudoku.printUnsolvedCells();
		}
		
	}

	private void solutionIteration(SudokuSquare sudoku) {
		
		for(int x=0; x<sudoku.getSize(); x++) {
			for(int y=0; y<sudoku.getSize(); y++) {
				if(!sudoku.getRow(x).getCell(y).isFilled()) {
					int sudokuSize = (int) Math.sqrt(sudoku.getSize());
					int rowIndex = x;
					int columnIndex = y;
					int subSquareIndex = ((x/sudokuSize)*sudokuSize) + (y/sudokuSize);
					Set<Integer> missingNumbersInRow = sudoku.getRow(rowIndex).missingNumbers();
					Set<Integer> missingNumbersInColumn = sudoku.getColumn(columnIndex).missingNumbers();
					Set<Integer> missingNumbersInSubSquare = sudoku.getSubSquare(subSquareIndex).missingNumbers();
					missingNumbersInRow.retainAll(missingNumbersInColumn);
					missingNumbersInRow.retainAll(missingNumbersInSubSquare);
					if(missingNumbersInRow.size() == 1) {
						sudoku.setCellValue(missingNumbersInRow.stream().findFirst().get(), rowIndex, columnIndex);
					} else {
						Set<SudokuNumber> missingNumbers = new HashSet<>();
						missingNumbersInRow.stream().forEach(value -> {
							SudokuNumber number = new SudokuNumber(value);
							number.setProbability(1.0/missingNumbersInRow.size());
							number.setRowProbability(sudoku.getRow(rowIndex).getProbabilityForNumber(value));
							number.setColumnProbability(sudoku.getColumn(columnIndex).getProbabilityForNumber(value));
							number.setSubSquareProbability(sudoku.getSubSquare(subSquareIndex).getProbabilityForNumber(value));
							missingNumbers.add(number);
						});
						sudoku.setCellMissingNumbers(missingNumbers, rowIndex, columnIndex);
					}
//					sudoku.printUnsolvedCells();
				}
			}
		}
		
	}

}
