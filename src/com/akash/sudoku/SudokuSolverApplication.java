package com.akash.sudoku;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.akash.sudoku.datastructure.SudokuSquare;
import com.akash.sudoku.solver.ISudokuSolver;
import com.akash.sudoku.solver.ProbabilitySolver;

public class SudokuSolverApplication {
	
	private static final String INPUT_FILENAME = "input.txt"; 
		

	public static void main(String[] args) {
		
		List<List<Integer>> inputNumbers = readInput(INPUT_FILENAME);
		
		SudokuSquare sudoku = processInput(inputNumbers);
		
		ISudokuSolver sudokuSolver = new ProbabilitySolver();
		
		sudokuSolver.solve(sudoku);

	}

	private static SudokuSquare processInput(List<List<Integer>> inputNumbers) {
		int size = inputNumbers.get(0).size();
		SudokuSquare sudoku = SudokuSquare.getInstance(size);
		for(int x=0; x<size; x++) {
			for(int y=0; y<size; y++) {
				sudoku.addCell(inputNumbers.get(x).get(y), x, y);
			}
		}
		return sudoku;
		
	}

	private static List<List<Integer>> readInput(String inputFilename) {
		List<List<Integer>> inputNumbers = new ArrayList<>();
		try(BufferedReader br = new BufferedReader(new FileReader(INPUT_FILENAME))) {
			br.lines().forEach(line -> {
				inputNumbers.add(Arrays.asList(line.split(",")).stream().map(Integer::valueOf).toList());
				
			});
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return inputNumbers;
	}

}
