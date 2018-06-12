package challenge.othello;

import java.util.stream.Stream;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.joining;
import static java.util.stream.IntStream.range;

class BoardPrinter {

    static String print(Board board) {

        Square[][] squares = board.getSquares();

        return Stream.concat(
                range(0, board.getSize()).mapToObj(rowNumber ->
                        rowMark(rowNumber) + " " + stream(squares[rowNumber]).map(Square::getDisplay).collect(joining())
                ),
                Stream.of("  " + range(0, board.getSize()).mapToObj(BoardPrinter::colMark).collect(joining()))
        ).collect(joining("\n"));
    }

    static String rowMark(int rowNumber) {
        return String.valueOf(rowNumber + 1);
    }

    static String colMark(int colNumber) {
        return String.valueOf((char) ('a' + colNumber));
    }
}
