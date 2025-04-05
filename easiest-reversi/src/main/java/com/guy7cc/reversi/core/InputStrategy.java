package com.guy7cc.reversi.core;

import java.util.Scanner;
import java.util.Set;

public class InputStrategy implements PlayStrategy {
    private final Scanner scanner;
    private final Color color;

    public InputStrategy(Color color, Scanner scanner){
        this.color = color;
        this.scanner = scanner;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public int play(Board board) {
        Set<Integer> validCells = board.getValidCells(color);

        while(true){
            System.out.print("Input here: ");
            String input = scanner.nextLine();
            try{
                int num = Integer.parseInt(input);
                int i = num / 10;
                int j = num % 10;
                if(i < 0 || i > 7 || j < 0 || j > 7){
                    System.out.println("Invalid input. Please enter a number between 00 and 77.");
                    continue;
                }
                if(!validCells.contains(num)){
                    System.out.println("Invalid input. Please enter a valid cell.");
                    continue;
                }
                return num;
            } catch(NumberFormatException e){
                System.out.println("Invalid input. Please enter a number between 00 and 77.");
                continue;
            }
        }
    }
}
