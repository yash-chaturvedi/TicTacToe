package com.example.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yash Chaturvedi
 */
public class Board {

    private static final int DEFAULT_SIZE = 3;

    private final int size;
    private List<List<State>> matrix;

    public Board(int size) {
        this.size = size;
        this.matrix = new ArrayList<>();
        for(int i=0; i<size; i++) {
            List<State> row = new ArrayList<>();
            for(int j=0; j<size; j++) {
                row.add(State.EMPTY);
            }
            this.matrix.add(row);
        }
    }

    public Board() {
        this(DEFAULT_SIZE);
    }

    public int getSize() {
        return size;
    }

    private boolean isCellEmpty(Cell cell) {
        return State.EMPTY.equals(matrix.get(cell.getI()).get(cell.getJ()));
    }

    public boolean isCellValid(Cell cell) {
        return cell.getI() >= 0 && cell.getJ() >= 0 && cell.getI() < size && cell.getJ() < size && isCellEmpty(cell);
    }

    public boolean isBoardFilled() {
        for(int i=0; i<size; i++) {
            for(int j=0; j<size; j++) {
                if(State.EMPTY.equals(matrix.get(i).get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    public void addCell(Cell cell, State move) {
        matrix.get(cell.getI()).set(cell.getJ(), move);
    }

    public boolean checkCellState(Cell cell, State state) {
        return state.equals(matrix.get(cell.getI()).get(cell.getJ()));
    }

    public void print() {
        System.out.println();
        for(int i=0; i<size; i++) {
            for(int j=0; j<size; j++) {
                String cellValue = State.EMPTY.equals(matrix.get(i).get(j)) ? "_" : matrix.get(i).get(j).name();
                System.out.print(cellValue + " ");
            }
            System.out.println();
        }
    }

}
