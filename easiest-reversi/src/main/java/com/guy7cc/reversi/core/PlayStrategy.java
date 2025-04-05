package com.guy7cc.reversi.core;

public interface PlayStrategy {
    Color getColor();

    int play(Board board);
}
