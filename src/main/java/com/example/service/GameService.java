package com.example.service;

import com.example.model.Board;
import com.example.model.Cell;
import com.example.model.Player;
import com.example.model.State;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

/**
 * @author Yash Chaturvedi
 */
public class GameService {

    private final Board board;
    private final Queue<Player> players;

    public GameService(Board board, List<Player> playerList) {
        this.board = board;
        this.players = new LinkedList<>();
        players.addAll(playerList);
    }

    public void startGame() {
        Scanner sc = new Scanner(System.in);
        while(!board.isBoardFilled()) {
            board.print();
            Player player = players.peek();
            System.out.println("Player " + player.getName() + ", It's your turn");
            String input = sc.nextLine();
            String[] s = input.split(" ");
            if("EXIT".equalsIgnoreCase(s[0])) {
                break;
            }
            int i = Integer.parseInt(s[0]);
            int j = Integer.parseInt(s[1]);
            Cell cell = new Cell(i-1, j-1);
            if(board.isCellValid(cell)) {
                players.remove();
                board.addCell(cell, player.getMove());
                boolean playerWon = checkIfPlayerWon(player.getMove());
                if(playerWon) {
                    System.out.println(player.getName() + " won the game");
                    break;
                }
                else {
                    players.add(player);
                }
            }
            else {
                System.out.println("Invalid Move");
            }
        }
        if(board.isBoardFilled()) {
            System.out.println("Game Over");
        }
    }

    private boolean checkIfPlayerWon(State move) {
        int size = board.getSize();
        boolean  isMajorDiagonalFilled = true, isMinorDiagonalFilled = true;
        for(int i=0; i<size; i++) {
            boolean isRowFilled = true, isColFilled = true;
            for(int j=0; j<size; j++) {
                if(!board.checkCellState(new Cell(i, j), move)) {
                    isRowFilled = false;
                }
                if(!board.checkCellState(new Cell(j, i), move)) {
                    isColFilled = false;
                }
            }
            if(isRowFilled || isColFilled) {
                return true;
            }
            if(!board.checkCellState(new Cell(i, i), move)) {
                isMajorDiagonalFilled = false;
            }
            if(!board.checkCellState(new Cell(i, size - 1 - i), move)) {
                isMinorDiagonalFilled = false;
            }
        }
        return isMajorDiagonalFilled || isMinorDiagonalFilled;
    }

}
