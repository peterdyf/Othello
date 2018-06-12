package challenge.othello;

import java.util.Arrays;

import static challenge.othello.Square.Blank;
import static challenge.othello.Square.O;
import static challenge.othello.Square.X;
import static java.util.stream.IntStream.range;

class Board {
    private static final int size = 8;
    private final Square[][] squares = new Square[size][size];
    private Square currentMove = X;
    private boolean finished = false;
    private final GameRule rule;

    Board() {
        this(new GameRule());
    }

    Board(GameRule rule) {
        this.rule = rule;
        fillAllWithBlank();
        initialPieces();
    }

    private void fillAllWithBlank() {
        range(0, size).forEach(row -> Arrays.fill(squares[row], Blank));
    }

    private void initialPieces() {
        squares[3][3] = O;
        squares[3][4] = X;
        squares[4][3] = X;
        squares[4][4] = O;
    }

    Square popPiece() {
        Square piece = currentMove;
        currentMove = currentMove.getNext();
        return piece;
    }


    void move(String moveString) throws IllegalMoveException {
        if (finished) {
            throw new CannotMoveOnFinishedBoard();
        }
        rule.move(this, Move.fromInput(moveString));
        if (!rule.canContinue(this)) {
            popPiece();
        }
        if (!rule.canContinue(this)) {
            finished = true;
        }
    }

    boolean isFinished() {
        return finished;
    }

    long count(Square piece) {
        return range(0, squares.length).flatMap(row -> range(0, squares[0].length).filter(col -> squares[row][col] == piece)).count();
    }

    public Square[][] getSquares() {
        return squares;
    }

    public int getSize() {
        return size;
    }

    public Square getCurrentMove() {
        return currentMove;
    }

}
