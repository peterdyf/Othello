package challenge.othello;

enum Square {
    O("O"),
    X("X"),
    Blank("-") {
        @Override
        public Square getNext() {
            throw new UnsupportedOperationException("Impossible to get next from Blank");
        }
    };

    static {
        O.next = X;
        X.next = O;
    }

    Square(String display) {
        this.display = display;
    }

    private final String display;

    private Square next;

    public String getDisplay() {
        return display;
    }

    public Square getNext() {
        return next;
    }
}