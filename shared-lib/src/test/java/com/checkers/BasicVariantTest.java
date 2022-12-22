package com.checkers;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.checkers.Board.Color;

public class BasicVariantTest {
    @Test
    public void ConstructorTest()
    {
        int x_dim = 8;
        int y_dim = 8;
        BasicVariant basicVariant = new BasicVariant(x_dim, y_dim);
        assertTrue(basicVariant.x_dim == x_dim);
        assertTrue(basicVariant.y_dim == y_dim);
    }
    @Test
    public void StartingBoardCntTest()
    {
        BasicVariant starting_board = new BasicVariant(8, 8);
        starting_board.setup_board();
        int pawn_cnt = 0;
        for (int j = 0; j < 8; j++) {
            for (int i = 0; i < 8; i++) {
                if(starting_board.get_board()[i][j] != null)
                    pawn_cnt++;
            }
        }
        assertTrue(pawn_cnt == 24);
    }
    @Test
    public void StartingBoardNoPawnsOnWhiteFieldsTest()
    {
        BasicVariant starting_board = new BasicVariant(8, 8);
        starting_board.setup_board();
        int white_field_pawn_cnt = 0;
        for (int j = 0; j < 8; j++) {
            for (int i = 0; i < 8; i++) {
                if((i + j) % 2 == 0 && starting_board.get_board()[i][j] != null)
                white_field_pawn_cnt++;
            }
        }
        assertTrue(white_field_pawn_cnt == 0);
    }
    @Test
    public void StartingBoardWhitePlacementTest()
    {
        BasicVariant starting_board = new BasicVariant(8, 8);
        starting_board.setup_board();
        for (int j = 0; j < 8; j++) {
            for (int i = 0; i < 8; i++) {
                if(j < 3 && (i + j) % 2 == 1 && starting_board.get_board()[i][j].get_color() != Color.WHITE)
                    assert(false);
            }
        }
        assertTrue(true);
    }
    @Test
    public void StartingBoardBlackPlacementTest()
    {
        BasicVariant starting_board = new BasicVariant(8, 8);
        starting_board.setup_board();
        for (int j = 0; j < 8; j++) {
            for (int i = 0; i < 8; i++) {
                if(j > 4 && (i + j) % 2 == 1 && starting_board.get_board()[i][j].get_color() != Color.BLACK)
                    assert(false);
            }
        }
        assertTrue(true);
    }
}