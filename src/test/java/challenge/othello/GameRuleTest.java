package challenge.othello;

import org.hamcrest.MatcherAssert;
import org.junit.Test;

import static challenge.othello.BoardPrinter.print;
import static challenge.othello.Square.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GameRuleTest {

    @Test
    public void canContinue_Full() {

        Board board = mock(Board.class);
        when(board.getSquares()).thenReturn(new Square[][]{
                new Square[]{O, X, O},
                new Square[]{X, O, X},
                new Square[]{X, O, X},
        });
        when(board.getSize()).thenReturn(3);
        when(board.getCurrentMove()).thenReturn(X);


        boolean canContinue = new GameRule().canContinue(board);
        assertThat("Full Board cannot move", canContinue, equalTo(false));
    }

    @Test
    public void capturable_noValidMove() {

        Board board = mock(Board.class);
        when(board.getSquares()).thenReturn(new Square[][]{
                new Square[]{Blank, Blank, O, Blank},
                new Square[]{X, X, X, X},
                new Square[]{X, X, X, X},
                new Square[]{X, X, X, X},
        });
        when(board.getSize()).thenReturn(4);
        when(board.getCurrentMove()).thenReturn(X);

        boolean canContinue = new GameRule().canContinue(board);

        assertThat("Cannot move X on \n" + BoardPrinter.print(board), canContinue, equalTo(false));
    }


    @Test(expected = PlaceOccupiedException.class)
    public void placeOccupiedException() throws IllegalMoveException {
        Board board = mock(Board.class);
        when(board.getSquares()).thenReturn(new Square[][]{
                new Square[]{O, X},
                new Square[]{X, O},
        });
        when(board.getSize()).thenReturn(2);

        new GameRule().move(board, Move.fromInput("a1"));
    }


    @Test(expected = NonCapturableMoveException.class)
    public void nonCapturableMoveException() throws IllegalMoveException {
        Board board = mock(Board.class);
        when(board.getSquares()).thenReturn(new Square[][]{
                new Square[]{Blank, Blank, O, Blank},
                new Square[]{X, X, X, X},
                new Square[]{X, X, X, X},
                new Square[]{X, X, X, X},
        });
        when(board.getSize()).thenReturn(4);
        when(board.getCurrentMove()).thenReturn(X);
        new GameRule().move(board, Move.fromInput("a1"));
    }


    @Test
    public void move0() throws IllegalMoveException {
        Board board = mock(Board.class);
        Square[][] squares = {
                new Square[]{Blank, Blank, Blank, Blank},
                new Square[]{Blank, O, X, Blank},
                new Square[]{Blank, X, O, Blank},
                new Square[]{Blank, Blank, Blank, Blank},
        };
        when(board.getSquares()).thenReturn(squares);
        when(board.getSize()).thenReturn(4);
        when(board.getCurrentMove()).thenReturn(X);
        when(board.popPiece()).thenReturn(X);
        String boardString = print(board);

        new GameRule().move(board, Move.fromInput("2a"));

        MatcherAssert.assertThat("Place X on 2a on\n"+ boardString, squares, equalTo(
                new Square[][]{
                        new Square[]{Blank, Blank, Blank, Blank},
                        new Square[]{X, X, X, Blank},
                        new Square[]{Blank, X, O, Blank},
                        new Square[]{Blank, Blank, Blank, Blank},
                }
        ));
    }

    @Test
    public void move1() throws IllegalMoveException {

        Board board = mock(Board.class);
        Square[][] squares = {
                new Square[]{Blank, Blank, Blank, Blank},
                new Square[]{Blank, O, X, Blank},
                new Square[]{Blank, X, O, Blank},
                new Square[]{Blank, Blank, Blank, Blank},
        };
        when(board.getSquares()).thenReturn(squares);
        when(board.getSize()).thenReturn(4);
        when(board.getCurrentMove()).thenReturn(X);
        when(board.popPiece()).thenReturn(X);
        String boardString = print(board);

        new GameRule().move(board, Move.fromInput("1b"));

        MatcherAssert.assertThat("Place X on 1b on\n"+ boardString, squares, equalTo(
                new Square[][]{
                        new Square[]{Blank, X, Blank, Blank},
                        new Square[]{Blank, X, X, Blank},
                        new Square[]{Blank, X, O, Blank},
                        new Square[]{Blank, Blank, Blank, Blank},
                }
        ));
    }

    @Test
    public void move2() throws IllegalMoveException {

        Board board = mock(Board.class);
        Square[][] squares = {
                new Square[]{Blank, X, Blank, Blank},
                new Square[]{Blank, X, X, Blank},
                new Square[]{Blank, X, O, Blank},
                new Square[]{Blank, Blank, Blank, Blank},
        };
        when(board.getSquares()).thenReturn(squares);
        when(board.getSize()).thenReturn(4);
        when(board.getCurrentMove()).thenReturn(O);
        when(board.popPiece()).thenReturn(O);
        String boardString = print(board);

        new GameRule().move(board, Move.fromInput("3a"));

        MatcherAssert.assertThat("Place O on 3a on\n"+ boardString, squares, equalTo(
                new Square[][]{
                        new Square[]{Blank, X, Blank, Blank},
                        new Square[]{Blank, X, X, Blank},
                        new Square[]{O, O, O, Blank},
                        new Square[]{Blank, Blank, Blank, Blank},
                }
        ));

    }


}