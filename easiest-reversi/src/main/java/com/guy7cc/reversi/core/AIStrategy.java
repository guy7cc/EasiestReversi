package com.guy7cc.reversi.core;

import java.util.Random;
import java.util.Set;

public class AIStrategy implements PlayStrategy {
    private final Color color;

    public AIStrategy(Color color){
        this.color = color;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public int play(Board board) {
        Set<Integer> validCells = board.getValidCells(color);
        if(validCells.isEmpty()){
            return -1;
        }
        Random random = new Random();
        int randomIndex = random.nextInt(validCells.size());
        int i = 0;
        for(int cell : validCells){
            if(i == randomIndex){
                return cell;
            }
            i++;
        }
        return -1; // This line should never be reached
    }
}
