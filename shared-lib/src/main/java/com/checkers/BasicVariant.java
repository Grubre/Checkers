package com.checkers;

import java.util.HashMap;
import java.util.Optional;

import javax.lang.model.util.ElementScanner14;

import com.checkers.Board.Color;

public class BasicVariant extends Board {

    BasicVariant(int x_dim, int y_dim)
    {
        super(x_dim, y_dim);
    }

    @Override
    public void setup_board() {
        for (int j = 0; j < x_dim; j++) {
            for (int i = 0; i < y_dim; i++) {
                if(j < 3 && (i + j) % 2 == 1)
                    board[i][j] = new RegularPawn(Color.WHITE);
                else if(j > 4 && (i + j) % 2 == 1)
                    board[i][j] = new RegularPawn(Color.BLACK);
                else
                    board[i][j] = null;
            }
        }
    }

    @Override
    public Optional<Color> game_over() {
        HashMap<Color,Integer> color_cnt = new HashMap<Board.Color,Integer>();
        for (int i = 0; i < x_dim; i++) {
            for (int j = 0; j < y_dim; j++) {
                AbstractPawn pawn = board[i][j];
                if(pawn == null)
                    continue;
                Color key = pawn.get_color();
                color_cnt.putIfAbsent(key, 0);
                color_cnt.put(key, color_cnt.get(key) + 1);
            }
        }
        if(color_cnt.get(Color.BLACK) == 0)
            return Optional.of(Color.WHITE);
        else if(color_cnt.get(Color.WHITE) == 0)
            return Optional.of(Color.BLACK);
        return Optional.empty();
    }
    
}
