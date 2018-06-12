package challenge.othello;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

public class OthelloTest {

    @Test
    public void playGame() {
        String board= Othello.playGame("f5,6f,f7,4f,f3,3e,d3,c5");
        assertThat(board, equalTo(
                "1 --------\n" +
                        "2 --------\n" +
                        "3 ---XXX--\n" +
                        "4 ---XXX--\n" +
                        "5 --OOOX--\n" +
                        "6 -----X--\n" +
                        "7 -----X--\n" +
                        "8 --------\n" +
                        "  abcdefgh"
        ));
    }
}