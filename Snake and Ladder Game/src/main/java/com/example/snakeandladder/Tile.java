package com.example.snakeandladder;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tile extends Rectangle{

    public Tile(int x, int y) {
        setWidth(DiceRoleSnake.Tile_Size);
        setHeight(DiceRoleSnake.Tile_Size);

        setFill(Color.PINK);
        setStroke(Color.BLACK);
    }

}
