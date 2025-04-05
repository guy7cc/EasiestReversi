package com.guy7cc.reversi.core;

import java.util.*;

public class Reversi {
    private final Map<Color, PlayStrategy> playStrategies = new HashMap<>();

    private Board board = new Board();

    public Reversi(List<PlayStrategy> playStrategies) {
        for(PlayStrategy playStrategy : playStrategies){
            this.playStrategies.put(playStrategy.getColor(), playStrategy);
        }
        for(Color color : Color.values()) {
            this.playStrategies.computeIfAbsent(color, AIStrategy::new);
        }
    }

    public void run(){
        int turn = 0;
        while(!board.isGameOver()){
            Color color = Color.values()[turn % Color.values().length];
            PlayStrategy playStrategy = playStrategies.get(color);

            board.render();
            System.out.println("Turn" + turn + ": " + color.toString());
            if(!board.getValidCells(color).isEmpty()){
                System.out.println("No cells available. Skipping turn.");
            } else {
                int cell = playStrategy.play(board);
                board = board.put(color, cell);
            }
            turn++;
        }

        board.render();
        System.out.println("Game Finished!");
        int max = 0;
        Set<Color> winners = new HashSet<>();
        for(Color color : Color.values()){
            int count = board.getColoredCells(color);
            if(count > max){
                max = count;
                winners.clear();
                winners.add(color);
            } else if(count == max){
                winners.add(color);
            }
        }

        System.out.print("Winner(s): ");
        for(Color color : winners){
            System.out.print(color.toString() + " ");
        }
    }
}
