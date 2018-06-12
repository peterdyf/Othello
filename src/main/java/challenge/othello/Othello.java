package challenge.othello;

import java.util.Arrays;
import java.util.Scanner;

import static challenge.othello.Square.O;
import static challenge.othello.Square.X;

public class Othello {

    public static void main(String[] args) {
        Board board = new Board();
        Scanner cin = new Scanner(System.in);
        System.out.println(BoardPrinter.print(board));
        while (!board.isFinished()) {
            System.out.format("\nPlayer '%s' move: ", board.getCurrentMove());
            try {
                board.move(cin.next());
                System.out.println(BoardPrinter.print(board));
            } catch (IllegalMoveException e) {
                System.out.println("Invalid move. Please try again.");
            }
        }
        printResult(board);
    }

    public static String playGame(String moves) {
        Board board = new Board();
        Arrays.stream(moves.split(",")).forEach(move -> {
            try {
                board.move(move);
            } catch (IllegalMoveException e) {
                System.out.println(e);
                System.out.println("Invalid move. Please try again.");
            }
        });
        if (board.isFinished()) {
            printResult(board);
        }
        return BoardPrinter.print(board);
    }

    private static void printResult(Board board) {
        System.out.println("No Further moves available");
        String format = "Player '%s' wins (%d vs %d)\n";
        long countX = board.count(X);
        long countO = board.count(O);
        if (countO > countX) {
            System.out.format(format, O, countO, countX);
        } else {
            System.out.format(format, X, countX, countO);
        }
    }
}
