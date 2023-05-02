import java.util.PriorityQueue;

public class Practice3 {
    public static boolean solution(int[] target) {
        if (target == null || target.length == 0) {
            return false;
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>((x, y) -> y - x);

        int sum = 0;
        // 일단 전체 sum을 구한다.
        for (int num : target) {
            sum += num;
            pq.add(num);
        }

        while (pq.peek() != 1) {
            // 큰 값부터 꺼내서 sum 에서 차감
            int num = pq.poll();
            // sum은 큰값을 제외한 나머지 숫자들의 합이 됨.
            sum -= num;

            // 차감한 후의 sum 보다 num 이 작거나 sum 이 1보다 작으면 false
            if (num <= sum || sum < 1) {
                return false;
            }

            // 다음 값 비교를 위해 업데이트
            num -= sum;
            sum += num;

            pq.add(num);
        }
        return true;
    }

    public static void main(String[] args) {
        // Test code
        int[] target = {9, 3, 5};
        System.out.println(solution(target));

        target = new int[]{8, 5};
        System.out.println(solution(target));

        target = new int[]{1, 1, 1, 2};
        System.out.println(solution(target));
    }
}
