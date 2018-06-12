package challenge.othello;

import java.util.Objects;

class Move {

    private final int row;
    private final int col;

    static Move fromInput(String moveString) throws IllegalMoveException {
        Objects.requireNonNull(moveString);
        if (moveString.matches("[a-h][1-8]")) {
            int row = moveString.charAt(1) - '1';
            int col = moveString.charAt(0) - 'a';
            return new Move(row, col);
        } else if (moveString.matches("[1-8][a-h]")) {
            int row = moveString.charAt(0) - '1';
            int col = moveString.charAt(1) - 'a';
            return new Move(row, col);
        } else {
            throw new InvalidMoveFormatException(moveString);
        }
    }

    Move(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
