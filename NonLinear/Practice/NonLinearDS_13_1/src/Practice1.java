
public class Practice1 {
    final static int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};


    public static boolean solution(char[][] board, String word) {
        // 입력값이 이상할때.
        if (board == null || board.length == 0 || board[0].length == 0 || word == null || word.length() == 0) {
            return false;
        }

        // 방문했는지 아닌지를 체크해놓을 배열 만들기
        int row = board.length;
        int col = board[0].length;
        boolean[][] visited = new boolean[row][col];

        // 배열 전체를 돌면서 dfs로 확인하기.
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (dfs(board, visited, i, j, 0, word)) {
                    return true;
                }
            }
        }

        return false;
    }

    // i는 word의 문자 인덱스
    public static boolean dfs(char[][] board, boolean[][] visited, int x, int y, int i, String word) {
        int row = board.length;
        int col = board[0].length;

        // 계속 true가 나와서 끝까지 갔을 때
        if (i == word.length()) {
            return true;
        }

        // board의 칸을 넘어갔을때
        if (x < 0 || x >= row || y < 0 || y >= col) {
            return false;
        }

        // 이미 방문한 칸일 경우
        if (visited[x][y]) {
            return false;
        }

        // 찾고있는 문자와 같지 않을때
        if (board[x][y] != word.charAt(i)) {
            return false;
        }

        visited[x][y] = true;
        // 4방향으로 가봤을 때 값을 찾으면 true반환.
        for (int[] dir: dirs) {
            int xNext = x + dir[0];
            int yNext = y + dir[1];

            if (dfs(board, visited, xNext, yNext, i + 1, word)) {
                return true;
            }
        }
        visited[x][y] = false;

        return false;
    }

    public static void main(String[] args) {
        // Test code
        char[][] board = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        System.out.println(solution(board, "ABCCED"));
        System.out.println(solution(board, "SEE"));
        System.out.println(solution(board, "ABCB"));
    }
}
