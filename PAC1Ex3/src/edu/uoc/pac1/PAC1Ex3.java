package edu.uoc.pac1;


public class PAC1Ex3 {

    public static int MAX_ROWS = 6;
    public static int MAX_COLUMNS = 7;

    public static boolean isValidColumn(char[][] board, int column) {
        if (!(0 <= column && column < MAX_COLUMNS)) {
            return false;
        }
        return board[0][column] == ' ';
    }

    public static void printBoard(char[][] board) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < MAX_ROWS; i++) {
            for (int j = 0; j < MAX_COLUMNS; j++) {
                output.append("|");
                output.append(board[i][j]);
            }
            output.append("|");
            output.append(System.lineSeparator());
        }
        System.out.print(output);
    }

    public static boolean checkWinHorizontal(char[][] board, char color) {

        for (int i = 0; i < MAX_ROWS; i++) {
            int counter = 0;

            for (int j = 0; j < MAX_COLUMNS; j++) {
                if(board[i][j] == color){
                    counter ++;
                }
                }
            }
        }
        return false;
    }

    public static boolean checkWinVertical(char[][] board, char color) {
        for (int i = 0; i <= (MAX_ROWS - 4); i++) {
            for (int j = 0; j < MAX_COLUMNS; j++) {
                if (board[i][j] == color &&
                        board[i + 1][j] == color &&
                        board[i + 2][j] == color &&
                        board[i + 3][j] == color) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean checkWinDiagonal(char[][] board, char color) {
        for (int i = 0; i <= MAX_ROWS - 4; i++) {
            for (int j = 0; j <= MAX_COLUMNS - 4; j++) {
                if (board[i][j] == color &&
                        board[i + 1][j + 1] == color &&
                        board[i + 2][j + 2] == color &&
                        board[i + 3][j + 3] == color) {
                    return true;
                }
            }
        }

        for (int i = 0; i <= MAX_ROWS - 4; i++) {
            for (int j = 3; j < MAX_COLUMNS; j++) {
                if (board[i][j] == color &&
                        board[i + 1][j - 1] == color &&
                        board[i + 2][j - 2] == color &&
                        board[i + 3][j - 3] == color) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean checkWin(char[][] board, char color) {
        return checkWinHorizontal(board, color) ||
                checkWinVertical(board, color) ||
                checkWinDiagonal(board, color);
    }

    public static char[][] dropPiece(char[][] board, int column, char color) {

        if (!isValidColumn(board, column)) {
            System.out.println("This column is invalid or full.");
            return board;
        }

        for (int row = MAX_ROWS - 1; row >= 0; row--) {
            if (board[row][column] == ' ') {
                board[row][column] = color;
                break;
            }
        }

        printBoard(board);

        if (checkWin(board, color)) {
            System.out.println("Color " + color + " wins!");
        }

        return board;
    }
}
