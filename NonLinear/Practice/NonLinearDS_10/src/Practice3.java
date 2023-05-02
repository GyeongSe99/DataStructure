// Practice3
// nums 배열에 주어진 정수들 중에서 가장 많이 발생한 숫자들 순으로 k 번째 까지 출력하세요.
// 빈도가 같은 경우에는 값이 작은 숫자가 먼저 출력되도록 구현하세요.

// 입출력 예시
// 입력: 1, 1, 1, 2, 2, 3
// k: 2
// 출력: 1, 2

// 입력: 3, 1, 5, 5, 3, 3, 1, 2, 2, 1, 3
// k: 3
// 출력: 3, 1, 2


import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Practice3 {
    public static void solution1(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 1; i <= 9; i++) {
            map.put(i, 0);
        }
        System.out.println(map);
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>((x, y) -> y.getValue() == x.getValue() ?
                x.getKey() - y.getKey() : y.getValue() - x.getValue());

        for (Map.Entry<Integer, Integer> item: map.entrySet()) {
            pq.add(item);
        }
        System.out.println(pq);

        for (int i = 0; i < k; i++) {
            Map.Entry<Integer, Integer> cur = pq.poll();
            System.out.print(cur.getKey() + " ");
        }
        System.out.println();
    }

    class Num implements Comparable<Num> {
        int key;
        int freq;

        public Num(int key, int freq) {
            this.key = key;
            this.freq = freq;
        }

        @Override
        public int compareTo(Num o) {
            if (this.freq == o.freq) {
                return this.key - o.key;
            } else {
                return o.freq - this.freq;
            }
        }
    }

    public static void solution2(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        PriorityQueue<Num> pq = new PriorityQueue();
        for (Map.Entry<Integer, Integer> item: map.entrySet()) {
            pq.add(new Practice3().new Num(item.getKey(), item.getValue()));
        }

        for (int i = 0; i < k; i++) {
            Num cur = pq.poll();
            System.out.print(cur.key + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // Test code
        int[] nums = {2, 2, 1, 1, 2, 3};
        solution1(nums, 3);
        solution2(nums, 3);
        System.out.println();

        nums = new int[]{3, 1, 4, 4, 3, 3, 1, 2, 2, 1, 3};
        solution1(nums, 4);
        solution2(nums, 4);
    }
}
