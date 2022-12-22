package com.checkers;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.checkers.Board.BoardPos;
import com.checkers.Board.Color;

public class RegularPawnTest {
    @Test
    public void ConstructorTest()
    {
        Color color = Color.BLACK;
        RegularPawn regularPawn = new RegularPawn(color);

        assertTrue(color == regularPawn.get_color());
    }

    @Test
    public void WhiteAscensionRowTest()
    {
        int x_dim = 8, y_dim = 8;
        RegularPawn regularPawn = new RegularPawn(Color.WHITE);
        BasicVariant basicVariant = new BasicVariant(x_dim, y_dim);

        for (int j = 0; j < y_dim; j++) {
            for (int i = 0; i < x_dim; i++) {
                if(j == y_dim - 1)
                    assertTrue(regularPawn.can_ascend(basicVariant, basicVariant.new BoardPos(i,j)));
                else
                    assertTrue(!regularPawn.can_ascend(basicVariant, basicVariant.new BoardPos(i,j)));
            }
        }
    }

    @Test
    public void BlackAscensionRowTest()
    {
        int x_dim = 8, y_dim = 8;
        RegularPawn regularPawn = new RegularPawn(Color.BLACK);
        BasicVariant basicVariant = new BasicVariant(x_dim, y_dim);

        for (int j = 0; j < y_dim; j++) {
            for (int i = 0; i < x_dim; i++) {
                if(j == 0)
                    assertTrue(regularPawn.can_ascend(basicVariant, basicVariant.new BoardPos(i,j)));
                else
                    assertTrue(!regularPawn.can_ascend(basicVariant, basicVariant.new BoardPos(i,j)));
            }
        }
    }

    @Test
    public void WhiteAscensionCntTest()
    {
        int x_dim = 8, y_dim = 8;
        RegularPawn regularPawn = new RegularPawn(Color.WHITE);
        BasicVariant basicVariant = new BasicVariant(x_dim, y_dim);

        int white_cnt = 0;

        for (int j = 0; j < y_dim; j++) {
            for (int i = 0; i < x_dim; i++) {
                if(regularPawn.can_ascend(basicVariant, basicVariant.new BoardPos(i,j)))
                    white_cnt++;
            }
        }

        assertTrue(white_cnt == x_dim);
    }

    @Test
    public void BlackAscensionCntTest()
    {
        int x_dim = 8, y_dim = 8;
        RegularPawn regularPawn = new RegularPawn(Color.BLACK);
        BasicVariant basicVariant = new BasicVariant(x_dim, y_dim);

        int black_cnt = 0;

        for (int j = 0; j < y_dim; j++) {
            for (int i = 0; i < x_dim; i++) {
                if(regularPawn.can_ascend(basicVariant, basicVariant.new BoardPos(i,j)))
                    black_cnt++;
            }
        }

        assertTrue(black_cnt == x_dim);
    }
}
