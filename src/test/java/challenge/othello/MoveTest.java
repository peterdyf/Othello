package challenge.othello;

import org.junit.Test;

import static challenge.othello.Square.X;
import static org.junit.Assert.*;

public class MoveTest {

    @Test(expected = InvalidMoveFormatException.class)
    public void illegalMove_12() throws IllegalMoveException {
        Move.fromInput("12");
    }

    @Test(expected = InvalidMoveFormatException.class)
    public void illegalMove_A2() throws IllegalMoveException {
        Move.fromInput("A2");
    }

}