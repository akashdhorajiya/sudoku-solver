package com.akash.sudoku.datastructure;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SudokuSquare implements ISudokuPart{
	
	private List<SudokuPart> rows;
	
	private List<SudokuPart> columns;
	
	private List<SudokuPart> subSquares;
	
	private int size;
	
	private static SudokuSquare sudoku;
	
	public static SudokuSquare getInstance(int size) {
		if(sudoku == null) {
			sudoku = new SudokuSquare(size);
		}
		return sudoku;
	}
	
	private SudokuSquare(int size) {
		rows = new ArrayList<>();
		columns = new ArrayList<>();
		subSquares = new ArrayList<>();
		this.size = size;
	}
	
	public void addRow(SudokuPart row) {
		if(rows.size() < size) {
			this.rows.add(row);
		}
	}
//	
//	public List<SudokuPart> getRows() {
//		return rows;
//	}
	
	public int getSize() {
		return size;
	}
	
	public SudokuPart getRow(int index) {
		return rows.get(index);
	}
	
	public SudokuPart getColumn(int index) {
		return columns.get(index);
	}
	
	public SudokuPart getSubSquare(int index) {
		return subSquares.get(index);
	}
	
	public Cell getCell(int row, int column) {
		return rows.get(row).getCell(column);
	}
	
	public void setCellValue(int value, int row, int column) {
		Cell cell = rows.get(row).getCell(column);
		cell.setValue(value);
	}
	
	public void setCellMissingNumbers(Set<SudokuNumber> numbers, int row, int column) {
		Cell cell = rows.get(row).getCell(column);
		cell.setPossibleValues(numbers);
	}
	
	public void addColumn(SudokuPart column) {
		if(columns.size() < size) {
			this.columns.add(column);
		}
	}
	
	public void addSubSquare(SudokuPart subSquare) {
		if(subSquares.size() < size) {
			this.subSquares.add(subSquare);
		}
	}
	
	public void addCell(int value, int x, int y) {
		Cell cell = new Cell(value, size);
		int sudokuSize = (int) Math.sqrt(size);
		int rowIndex = x;
		int columnIndex = y;
		int subSquareIndex = ((x/sudokuSize)*sudokuSize) + (y/sudokuSize);
		if(rows.size() <= rowIndex) {
			rows.add(new SudokuPart(size));
		}
		if(columns.size() <= columnIndex) {
			columns.add(new SudokuPart(size));
		}
		if(subSquares.size() <= subSquareIndex) {
			subSquares.add(new SudokuPart(size));
		}
		rows.get(rowIndex).addCell(cell);
		columns.get(columnIndex).addCell(cell);
		subSquares.get(subSquareIndex).addCell(cell);
	}
	
	@Override
	public void print() {
		System.out.println();
		System.out.println("Sudoku:");
		rows.forEach(row -> {
			row.getCells().forEach(cell -> {
				System.out.print("----");
			});
			System.out.print("-\n");
			row.getCells().forEach(cell -> {
				System.out.print("| " + cell.getValue() + " ");
			});
			System.out.print("|\n");
		});
		rows.forEach(row -> {
			System.out.print("----");
		});
		System.out.println();
	}

	@Override
	public int getEntropy() {
		return rows.stream().mapToInt(SudokuPart::getEntropy).sum();
	}
	
	public void printRow(int index) {
		System.out.println();
		System.out.println("Row: " + index);
		rows.get(index).print();
		System.out.println();
	}
	
	public void printColumn(int index) {
		System.out.println();
		System.out.println("Column: " + index);
		columns.get(index).print();
		System.out.println();
	}
	
	public void printSubSquare(int index) {
		System.out.println();
		System.out.println("SubSquare: " + index);
		subSquares.get(index).print();
		System.out.println();
	}
	
	public void printCell(int x, int y) {
		System.out.println();
		System.out.println("Cell: (" + x + "," + y + ")");
		rows.get(x).getCell(y).print();
		System.out.println();
	}
	
	public void printUnsolvedCells() {
		for(int x=0; x<size; x++) {
			for(int y=0; y<size; y++) {
				if(!rows.get(x).getCell(y).isFilled()) {
					printCell(x, y);
				}
			}
		}
	}

}
