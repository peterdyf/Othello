package challenge.othello;

import org.junit.Test;

import static challenge.othello.Square.Blank;
import static challenge.othello.Square.O;
import static challenge.othello.Square.X;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BoardPrinterTest {

    @Test
    public void colMark0() {
        assertThat("Col 0 should display as a", BoardPrinter.colMark(0), equalTo("a"));
    }

    @Test
    public void colMark1() {
        assertThat("Col 1 should display as b", BoardPrinter.colMark(1), equalTo("b"));
    }

    @Test
    public void output2() {

        Board board = mock(Board.class);
        when(board.getSquares()).thenReturn(new Square[][]{
                new Square[]{Blank, X, O},
                new Square[]{X, O, X},
                new Square[]{X, O, X},
        });
        when(board.getSize()).thenReturn(3);


        assertThat("Print Board", BoardPrinter.print(board), equalTo(
                "1 -XO\n" +
                        "2 XOX\n" +
                        "3 XOX\n" +
                        "  abc"
        ));
    }
}