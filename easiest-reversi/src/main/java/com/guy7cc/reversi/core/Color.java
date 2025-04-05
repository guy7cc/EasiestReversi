package com.guy7cc.reversi.core;

public enum Color {
    BLACK(0, '@'),
    WHITE(1, 'O'),;

    public final int value;
    public final char icon;

    Color(int value, char icon){
        this.value = value;
        this.icon = icon;
    }

    public static Color fromValue(int value) {
        for (Color color : Color.values()) {
            if (color.value == value) {
                return color;
            }
        }
        return null;
    }
}
