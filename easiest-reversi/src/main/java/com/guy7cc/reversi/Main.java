package com.guy7cc.reversi;

import com.guy7cc.reversi.core.AIStrategy;
import com.guy7cc.reversi.core.Color;
import com.guy7cc.reversi.core.InputStrategy;
import com.guy7cc.reversi.core.Reversi;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Main entry point for the Reversi game
        Scanner scanner = new Scanner(System.in);

        Reversi reversi = new Reversi(List.of(
                new InputStrategy(Color.BLACK, scanner),
                new AIStrategy(Color.WHITE)
        ));
        reversi.run();

        // Close the scanner to prevent resource leaks
        scanner.close();
    }
}
