package com.guy7cc.reversi.core;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Board implements Cloneable {
    private int[][] board;

    public Board(){
        this.board = new int[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = -1;
            }
        }
        board[3][3] = 0;
        board[3][4] = 1;
        board[4][3] = 1;
        board[4][4] = 0;
    }

    public int[][] getBoard(){
        return deepCopy(board);
    }

    public Set<Integer> getValidCells(Color color){
        Set<Integer> validCells = new HashSet<>();
        List<int[]> directions = List.of(
                new int[]{-1, -1}, new int[]{-1, 0}, new int[]{-1, 1},
                new int[]{0, -1}, new int[]{0, 1},
                new int[]{1, -1}, new int[]{1, 0}, new int[]{1, 1}
        );

        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if(board[i][j] != -1) continue;
                for(int[] dir : directions){
                    int x = i + dir[0];
                    int y = j + dir[1];
                    boolean foundOpponent = false;
                    while(0 <= x && x < 8 && 0 <= y && y < 8){
                        if(board[x][y] == -1) break;
                        if(board[x][y] == color.value){
                            if(foundOpponent) {
                                validCells.add(i * 10 + j);
                            }
                            break;
                        }
                        foundOpponent = true;
                        x += dir[0];
                        y += dir[1];
                    }
                }
            }
        }

        return validCells;
    }

    public int getColoredCells(Color color){
        int count = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == color.value) {
                    count++;
                }
            }
        }
        return count;
    }

    public Board put(Color color, int cell){
        Board newBoard = this.clone();
        int i = cell / 10;
        int j = cell % 10;
        newBoard.board[i][j] = color.value;

        List<int[]> directions = List.of(
                new int[]{-1, -1}, new int[]{-1, 0}, new int[]{-1, 1},
                new int[]{0, -1}, new int[]{0, 1},
                new int[]{1, -1}, new int[]{1, 0}, new int[]{1, 1}
        );

        for(int[] dir : directions){
            int x = i + dir[0];
            int y = j + dir[1];
            boolean foundOpponent = false;
            while(0 <= x && x < 8 && 0 <= y && y < 8){
                if(newBoard.board[x][y] == -1) break;
                if(newBoard.board[x][y] == color.value){
                    if(foundOpponent) {
                        int k = i + dir[0];
                        int l = j + dir[1];
                        while(k != x || l != y){
                            newBoard.board[k][l] = color.value;
                            k += dir[0];
                            l += dir[1];
                        }
                    }
                    break;
                }
                foundOpponent = true;
                x += dir[0];
                y += dir[1];
            }
        }
        return newBoard;
    }

    public void render(){
        System.out.println("   0 1 2 3 4 5 6 7  ");
        System.out.println(" +-----------------+");
        for (int i = 0; i < 8; i++) {
            System.out.print(i + "| ");
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == -1) {
                    System.out.print("  ");
                } else {
                    Color color = Color.fromValue(board[i][j]);
                    System.out.print(color.icon + " ");
                }
            }
            System.out.println("|");
        }
        System.out.println(" +-----------------+");
    }

    public boolean isGameOver(){
        for(Color color : Color.values()){
            if(!getValidCells(color).isEmpty()){
                return false;
            }
        }
        return true;
    }

    private int[][] deepCopy(int[][] original) {
        if (original == null) return null;
        int[][] copy = new int[original.length][];
        for (int i = 0; i < original.length; i++) {
            copy[i] = original[i] != null ? original[i].clone() : null;
        }
        return copy;
    }

    @Override
    public Board clone() {
        try {
            Board cloned = (Board) super.clone();
            cloned.board = deepCopy(this.board);
            return cloned;
        } catch (CloneNotSupportedException e) {
            System.out.println("Clone not supported for Board");
            return null;
        }
    }
}
