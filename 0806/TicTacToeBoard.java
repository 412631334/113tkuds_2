
public class TicTacToeBoard {

    private char[][] board;
    private static final int SIZE = 3;

    public TicTacToeBoard() {
        board = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = ' ';
            }
        }
    }

    // 放置棋子 ('X' 或 'O')
    public boolean placePiece(int row, int col, char player) {
        if (row < 0 || row >= SIZE || col < 0 || col >= SIZE) {
            return false;
        }
        if (board[row][col] != ' ') {
            return false;
        }
        board[row][col] = player;
        return true;
    }

    // 判斷是否有人獲勝
    public boolean checkWin(char player) {
        // 檢查列
        for (int i = 0; i < SIZE; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) {
                return true;
            }
        }
        // 檢查行
        for (int j = 0; j < SIZE; j++) {
            if (board[0][j] == player && board[1][j] == player && board[2][j] == player) {
                return true;
            }
        }
        // 對角線
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
            return true;
        }
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player) {
            return true;
        }

        return false;
    }

    // 判斷遊戲是否結束 (有人贏或平手)
    public boolean isGameOver() {
        if (checkWin('X') || checkWin('O')) {
            return true;
        }
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true; // 平手
    }

    // 印出棋盤
    public void printBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(board[i][j]);
                if (j < SIZE - 1) {
                    System.out.print("|");
                }
            }
            System.out.println();
            if (i < SIZE - 1) {
                System.out.println("-----");
            }
        }
    }

    // 測試
    public static void main(String[] args) {
        TicTacToeBoard game = new TicTacToeBoard();
        game.placePiece(0, 0, 'X');
        game.placePiece(1, 1, 'O');
        game.placePiece(0, 1, 'X');
        game.placePiece(2, 2, 'O');
        game.placePiece(0, 2, 'X');

        game.printBoard();

        System.out.println("X 獲勝？ " + game.checkWin('X'));
        System.out.println("遊戲結束？ " + game.isGameOver());
    }
}
