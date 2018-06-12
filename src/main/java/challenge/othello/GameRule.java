package challenge.othello;

import static challenge.othello.Square.Blank;
import static java.util.stream.IntStream.range;

class GameRule {

    boolean canContinue(Board board) {
        Square[][] squares = board.getSquares();
        return range(0, board.getSize()).anyMatch(row ->
                range(0, board.getSize()).anyMatch(col -> {
                    if (squares[row][col] != Blank) {
                        return false;
                    }
                    return Direction.all()
                            .anyMatch(direction -> direction.capturableAmount(board, new Move(row, col)).isPresent());
                })
        );
    }

    void move(Board board, Move move) throws IllegalMoveException {
        if (board.getSquares()[move.getRow()][move.getCol()] != Blank) {
            throw new PlaceOccupiedException();
        }
        long updatedDirections = Direction.all()
                .filter(direction -> direction.capture(board, move))
                .count();

        if (updatedDirections == 0) {
            throw new NonCapturableMoveException();
        }

        board.getSquares()[move.getRow()][move.getCol()] = board.popPiece();
    }
}
