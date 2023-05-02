import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class Practice4 {
    public static int solution(String[] deadends, String target) {
        if (target == null || target.length() == 0) {
            return -1;
        }

        // visited에 deadends를 사전에 넣어 놓으면 이 부분은 그냥 지나감.
        HashSet<String> visited = new HashSet<>(Arrays.asList(deadends));

        Queue<String> queue = new LinkedList<>();

        // 처음 시작 위치
        queue.offer("0000");
        // 이동 횟수
        int cnt = 0;

        // bfs
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String curNum = queue.poll();

                // 이미 방문했던 숫자면 그냥 넘어가기
                if (!visited.add(curNum)) {
                    continue;
                }

                // 기저조건 : target과 숫자가 같다면 이동횟수 반환.
                if (curNum.equals(target)) {
                    return cnt;
                }

                // queue에 들어있던 String을 char배열로 만든 뒤
                // getExtnums(그 다음으로 넘어갈 수 있는 숫자들)메소드를 통해 리턴된 값을 돌기
                for (String nextNum : getNextNums(curNum.toCharArray())) {
                    // visited에 들어있다는 뜻은 방문하면 안되는 곳이라는 뜻
                    // visited에 들어있지 않은 숫자인 경우 queue에 넣기
                    if (!visited.contains(nextNum)) {
                        queue.offer(nextNum);
                    }
                }
            }
            cnt++;
        }

        return -1;
    }

    // 다음으로 넘어갈 수 있는 숫자들을 리턴해주는 메소드
    public static LinkedList<String> getNextNums(char[] cArr) {
        LinkedList<String> nums = new LinkedList<>();
        for (int i = 0; i < cArr.length; i++) {
            char c = cArr[i];
            // 한부분만 값을 1증가시키기
            // 9일때에는 1증가시키면 0이된다.
            cArr[i] = c == '9' ? '0' : (char)(c + ((char) 1));

            nums.add(String.valueOf(cArr));

            // 한부분만 값을 1 감소 시키기.
            cArr[i] = c == '0' ? '9' : (char)(c - ((char) 1));
            nums.add(String.valueOf(cArr));
            // 값을 저장한 후 다음 숫자를 위해 원래대로 돌려놓기
            cArr[i] = c;
        }
        return nums;
    }

    public static void main(String[] args) {
        // Test code
        String[] deadends = {"0201", "0101", "0102", "1212", "2002"};
        System.out.println(solution(deadends, "0202"));

        deadends = new String[] {"8888"};
        System.out.println(solution(deadends, "0009"));

    }
}
