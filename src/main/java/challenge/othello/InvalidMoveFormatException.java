package challenge.othello;

class InvalidMoveFormatException extends IllegalMoveException {

    private final String move;

    public InvalidMoveFormatException(String move) {
        this.move = move;
    }

    public String getMove() {
        return move;
    }
}
