package challenge.othello;

import java.util.Optional;
import java.util.stream.Stream;

import static challenge.othello.Square.Blank;
import static java.util.Arrays.stream;
import static java.util.Optional.empty;
import static java.util.Optional.of;
import static java.util.stream.IntStream.rangeClosed;

enum Direction {

    N(0, 1),
    NE(1, 1),
    E(1, 0),
    SE(1, -1),
    S(0, -1),
    SW(-1, -1),
    W(-1, 0),
    NW(-1, 1);

    final int row;
    final int col;

    Direction(int row, int col) {
        this.row = row;
        this.col = col;
    }

    static Stream<Direction> all() {
        return stream(Direction.values());
    }

    public Optional<Square> getPiece(Board board, Move move, int magnitude) {
        if (move.getRow() + this.row * magnitude < 0 || move.getCol() + this.col * magnitude < 0 || move.getRow() + this.row * magnitude >= board.getSize() || move.getCol() + this.col * magnitude >= board.getSize()) {
            return empty();
        }
        return of(board.getSquares()[move.getRow() + this.row * magnitude][move.getCol() + this.col * magnitude]).filter(p -> p != Blank);
    }

    boolean capture(Board board, Move move) {
        Optional<Integer> capturableAmount = capturableAmount(board, move);
        capturableAmount.ifPresent(num -> rangeClosed(1, num).forEach(magnitude -> board.getSquares()[move.getRow() + this.row * magnitude][move.getCol() + this.col * magnitude] = board.getCurrentMove()));
        return capturableAmount.isPresent();
    }


    Optional<Integer> capturableAmount(Board board, Move move) {
        return getPiece(board, move, 1).flatMap(piece -> {
            int magnitude = 1;   //java 8 stream doesn't has 'takeWhile'
            while (true) {
                Optional<Square> pieceAtMagnitude = getPiece(board, move, magnitude);
                if (!pieceAtMagnitude.isPresent()) {
                    return empty();
                }
                if (pieceAtMagnitude.filter(board.getCurrentMove()::equals).isPresent()) {
                    int lastMagnitude = magnitude - 1;
                    return lastMagnitude >= 1 ? of(lastMagnitude) : empty();
                }
                magnitude++;
            }
        });
    }
}
