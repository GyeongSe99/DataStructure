
public class Practice2 {
    public static void solution(char[][] board) {
        // 2칸인 경우에는 X가 둘러쌀수가 없으므로 그냥 끝내기
        if (board == null || board.length < 3 || board[0].length < 3) {
            return;
        }

        int row = board.length;
        int col = board[0].length;

        // 외곽에서 'O'로 시작하는 값이 있는지 확인 후
        // 얘네랑 연결되어있으면 둘러쌓여있지 않다는 뜻
        for (int i = 0; i < row; i++) { // 좌우 끝
            if (board[i][0] == 'O') {
                dfs(board, i, 0);
            }

            if (board[i][col - 1] == 'O') {
                dfs(board, i, col - 1);
            }
        }

        for (int i = 0; i < col - 1; i++) { // 상하 끝
            if (board[0][i] == 'O') {
                dfs(board, 0, i);
            }

            if (board[row - 1][i] == 'O') {
                dfs(board, row - 1, i);
            }
        }

        // 외곽의 'O'와 맞닿아있는 'O'들을 전부 *로 바꾸어주었으므로
        // 그냥 'O'인 애들은 외곽과 맞닿아있지 않음을 뜻함 (둘러쌓여 있다는 뜻)
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                // 'O' 인 애들은 X로 바꾸어주고
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }

                // '*'인 애들은 다시 'O로 바꾸어 주기
                if (board[i][j] == '*') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    public static void dfs(char[][] board, int x, int y) {
        if (x < 0 || y < 0 || x > board.length - 1 || y > board[0].length - 1) {
            return;
        }

        if (board[x][y] != 'O') {
            return;
        }

        // O를 만나면 별표 표시한 후 4방향으로 가보기
        board[x][y] = '*';
        dfs(board, x + 1, y);
        dfs(board, x - 1, y);
        dfs(board, x, y + 1);
        dfs(board, x, y - 1);
    }

    public static void main(String[] args) {
        // Test code
        char[][] board = {{'X', 'X', 'X', 'X'}, {'X', 'O', 'O', 'X'}, {'X', 'X', 'O', 'X'}, {'X', 'O', 'X', 'X'}};
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
        solution(board);

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();

        board = new char[][]{{'X', 'X', 'X', 'X'}, {'X', 'O', 'O', 'X'}, {'X', 'X', 'O', 'X'}, {'X', 'O', 'O', 'X'}};
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
        solution(board);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}
