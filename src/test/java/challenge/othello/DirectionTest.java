package challenge.othello;

import org.junit.Test;

import static challenge.othello.BoardPrinter.*;
import static challenge.othello.Direction.NE;
import static challenge.othello.Move.fromInput;
import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static challenge.othello.Square.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DirectionTest {

    @Test
    public void getPiece() throws IllegalMoveException {

        Board board = mock(Board.class);
        when(board.getSquares()).thenReturn(new Square[][]{
                new Square[]{Blank, O, O},
                new Square[]{O, O, O},
                new Square[]{Blank, O, X}
        });
        when(board.getSize()).thenReturn(3);

        assertThat("1a Go 2 step to NE should be X on \n" + print(board), NE.getPiece(board, fromInput("1a"), 2), equalTo(of(X)));
    }

    @Test
    public void getPieceInEdgeVertical() throws IllegalMoveException {

        Board board = mock(Board.class);
        when(board.getSquares()).thenReturn(new Square[][]{
                new Square[]{Blank, O},
                new Square[]{Blank, O}
        });
        when(board.getSize()).thenReturn(2);

        assertThat("2a Go 1 step to NE should go out of board vertical edge\n" + print(board), NE.getPiece(board, fromInput("2a"), 1), equalTo(empty()));
    }

    @Test
    public void getPieceInEdgeHorizontal() throws IllegalMoveException {
        Board board = mock(Board.class);
        when(board.getSquares()).thenReturn(new Square[][]{
                new Square[]{Blank, O},
                new Square[]{Blank, O}
        });
        when(board.getSize()).thenReturn(2);

        assertThat("1b Go 1 step to NE should go out of board horizontal edge\n" + print(board), NE.getPiece(board, fromInput("1b"), 1), equalTo(empty()));
    }

    @Test
    public void capturableAmount() throws IllegalMoveException {
        Board board = mock(Board.class);
        when(board.getSquares()).thenReturn(new Square[][]{
                new Square[]{Blank, O, O},
                new Square[]{O, O, O},
                new Square[]{Blank, O, X}
        });
        when(board.getSize()).thenReturn(3);
        when(board.getCurrentMove()).thenReturn(X);
        assertThat("Place X on 1a can capture 1 'O' in NE \n" + print(board), NE.capturableAmount(board, fromInput("1a")), equalTo(of(1)));
    }

    @Test
    public void capturableAmount_Blank() throws IllegalMoveException {

        Board board = mock(Board.class);
        when(board.getSquares()).thenReturn(new Square[][]{
                new Square[]{Blank, O, O},
                new Square[]{O, Blank, O},
                new Square[]{Blank, O, X}
        });
        when(board.getSize()).thenReturn(3);
        when(board.getCurrentMove()).thenReturn(X);
        assertThat("Place X on 1a cannot capture any 'O' on NE since 2b is blank \n" + print(board), NE.capturableAmount(board, fromInput("1a")), equalTo(empty()));
    }

    @Test
    public void capturableAmount_SelfPiece() throws IllegalMoveException {

        Board board = mock(Board.class);
        when(board.getSquares()).thenReturn(new Square[][]{
                new Square[]{Blank, O, O},
                new Square[]{O, X, O},
                new Square[]{Blank, O, X}
        });
        when(board.getSize()).thenReturn(3);
        when(board.getCurrentMove()).thenReturn(X);
        assertThat("Place X on 1a cannot capture any 'O' on NE since 2b is also X \n" + print(board), NE.capturableAmount(board, fromInput("1a")), equalTo(empty()));
    }

    @Test
    public void capturableAmountNoSelfPiece() throws IllegalMoveException {

        Board board = mock(Board.class);
        when(board.getSquares()).thenReturn(new Square[][]{
                new Square[]{Blank, O, O},
                new Square[]{O, O, O},
                new Square[]{Blank, O, Blank}
        });
        when(board.getSize()).thenReturn(3);
        when(board.getCurrentMove()).thenReturn(X);
        assertThat("Place X on 1a cannot capture any 'O' on NE since no X on this direction \n" + print(board), NE.capturableAmount(board, fromInput("1a")), equalTo(empty()));
    }

    @Test
    public void capturableAmountDisconnectedSelfPiece() throws IllegalMoveException {

        Board board = mock(Board.class);
        when(board.getSquares()).thenReturn(new Square[][]{
                new Square[]{Blank, O, O, O},
                new Square[]{O, O, O, O},
                new Square[]{Blank, O, Blank, O},
                new Square[]{Blank, O, Blank, X}
        });
        when(board.getSize()).thenReturn(4);
        when(board.getCurrentMove()).thenReturn(X);
        assertThat("Place X on 1a cannot capture any 'O' on NE since 3c is blank on this direction \n" + print(board), NE.capturableAmount(board, fromInput("1a")), equalTo(empty()));

    }

    @Test
    public void capture() throws IllegalMoveException {

        Board board = mock(Board.class);
        Square[][] squares = {
                new Square[]{Blank, O, O},
                new Square[]{O, O, O},
                new Square[]{Blank, O, X}
        };
        when(board.getSquares()).thenReturn(squares);
        when(board.getSize()).thenReturn(3);
        when(board.getCurrentMove()).thenReturn(X);

        NE.capture(board, fromInput("1a"));
        assertThat("Place X on 1a can capture 'O' on 2b on direction NE \n", squares[1][1], equalTo(X));
    }

    @Test
    public void invalidCapture() throws IllegalMoveException {

        Board board = mock(Board.class);
        Square[][] squares = {
                new Square[]{Blank, O, O},
                new Square[]{O, O, O},
                new Square[]{Blank, O, Blank}
        };
        when(board.getSquares()).thenReturn(squares);
        when(board.getSize()).thenReturn(3);
        when(board.getCurrentMove()).thenReturn(X);

        NE.capture(board, fromInput("1a"));
        assertThat("Place X on 1a cannot capture 'O' on 2b on direction NE \n", squares[1][1], equalTo(O));
    }
}