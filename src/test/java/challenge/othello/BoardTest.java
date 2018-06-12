package challenge.othello;

import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static challenge.othello.Square.O;
import static challenge.othello.Square.X;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class BoardTest {


    @Test
    public void pass() throws IllegalMoveException {
        GameRule gameRule = mock(GameRule.class);
        when(gameRule.canContinue(any())).thenAnswer(new Answer() {
            private int count = 0;

            public Object answer(InvocationOnMock invocation) {
                if (count++ == 0)
                    return false;   //first time

                return true;       //second time
            }
        });

        Board board = new Board(gameRule);
        doAnswer(invocation -> {
            board.popPiece();
            return null;
        }).when(gameRule).move(any(), any());

        board.move("1a");


        assertThat("O should be skipped since 1st canContinue return false", board.getCurrentMove(), equalTo(X));
        assertThat("Game should not finish since 2nd canContinue return true", board.isFinished(), equalTo(false));
    }


    @Test
    public void finish() throws IllegalMoveException {
        GameRule gameRule = mock(GameRule.class);
        when(gameRule.canContinue(any())).thenAnswer(new Answer() {
            private int count = 0;

            public Object answer(InvocationOnMock invocation) {
                if (count++ == 0)
                    return false;   //first time

                return false;       //second time
            }
        });

        Board board = new Board(gameRule);
        doAnswer(invocation -> {
            board.popPiece();
            return null;
        }).when(gameRule).move(any(), any());

        board.move("1a");

        assertThat("Game should not finish since both of 1st an d 2nd canContinue return false", board.isFinished(), equalTo(true));
    }

}
