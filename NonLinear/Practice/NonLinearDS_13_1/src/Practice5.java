import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class Practice5 {
    public static int solution(int[] forbidden, int a, int b, int x) {
        int cnt = 0;
        // 기본 상한선 설정
        int limit = x + a + b;

        Queue<int[]> queue = new LinkedList<>();
        // 초기 위치 설정 : 0, 0부터 시작한다는 뜻.
        queue.offer(new int[] {0, 0});

        // 가면 안되는 곳(forbidden) 정보를 visited에 넣어 방문하지 않도록 하기
        HashSet<int[]> visited = new HashSet<>(queue);
        for (int pos : forbidden) {
            // 배열[0]은 방향 정보 : 0은 앞으로 1은 뒤로
            // 배열[1]은 위치 정보
            visited.add(new int[] {0, pos});
            visited.add(new int[] {1, pos});
            // a >= b 인 경우, 목적지 x 를 넘어가면 되돌아올 수 없음
            // a < b 인 경우, 되돌아 오는게 가능한데 forbidden 에 걸리면 안됨
            // 따라서 기본 limit 을 forbidden 중 max + a + b 로 설정
            limit = Math.max(limit, pos + a + b);
        }

        while (!queue.isEmpty()) {
            for (int i = 0; i < queue.size(); i++) {
                int[] cur = queue.poll();
                int dir = cur[0];
                int pos = cur[1];

                if (pos == x) {
                    return cnt;
                }

                // 앞으로 가는 경우 keep
                int[] forward = new int[] {0, pos + a};
                if (pos + a <= limit && visited.add(forward)) {
                    queue.offer(forward);
                }

                // 뒤로 가는 경우 keep
                int[] backward = new int[] {1, pos - b};
                // 뒤로 두번 가면 안되므로 원래 dir이 0이어야하고
                // 현재 위치에서 b만큼 뒤로 간 값이 0보다 커야하고,
                // visited.add(backward)가 true이어야함. = 한번도 방문한적이 없어야함.
                if (dir == 0 && pos - b >= 0 && visited.add(backward)) {
                    queue.offer(backward);
                }
            }
            cnt++;
        }
        return -1;
    }

    public static void main(String[] args) {
        // Test code
        int[] forbidden = {14, 4, 18, 1, 15};
        System.out.println(solution(forbidden, 3, 15, 9));

        forbidden = new int[]{8, 3, 16, 6, 12, 20};
        System.out.println(solution(forbidden, 15, 13, 11));

        forbidden = new int[]{1, 6, 2, 14, 5, 17, 4};
        System.out.println(solution(forbidden, 16, 9, 7));
    }
}
