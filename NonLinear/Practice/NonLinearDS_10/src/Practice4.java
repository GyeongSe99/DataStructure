// Practice4
// 문자열 s 가 주어졌을 때, 문자열 내에 동일한 알파벳이 연속적으로 배치되지 않도록 재배치 하세요.
// 재배치가 가능한 경우 재정렬한 문자열을 반환하고 불가능한 경우 null 을 반환하세요.

// 입출력 예시
// 입력: "aabb"
// 출력: "abab"

// 입력: "aaca"
// 출력: null

// 문자의 빈도수를 센 뒤 우선순위 순으로 넣은다음 빈도수가 큰 알파벳에서 하나를 꺼낸 후 출력하고
// 숫자가 남아있으면 다시 우선순위에 넣어준다.

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Practice4 {
    public static String solution(String s) {
        HashMap<String, Integer> map = new HashMap<>();
        for (String item : s.split("")) {
            map.put(item, map.getOrDefault(item, 0) + 1);
        }

        // 빈도수를 기준으로 우선순위 큐에 넣어주기
        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>((x, y) -> y.getValue() - x.getValue());
        for (Map.Entry<String, Integer> item: map.entrySet()) {
            pq.offer(item);
        }

        StringBuffer sb = new StringBuffer();
        Map.Entry<String, Integer> prev = null;
        while (!pq.isEmpty()) {
            Map.Entry<String, Integer> cur = pq.poll(); // pq에서 하나 빼서

            if (prev != null && prev.getValue() > 0) {  // 이전 값이 아직 남아있다면
                pq.offer(prev); // pq에 다시 넣어주기
            }

            sb.append(cur.getKey());  // 출력할 sb에 넣어놓기
            cur.setValue(cur.getValue() - 1);   // 빈도값 하나 줄여주기

            prev = cur; // prev에 빈도값하나 줄여진 데이터 저장

            // pq에 더 이상 남아있지 않은데(다른 알파벳이 더 이상 없는데)
            // 방금 출력한 알파벳이 아직 남아있다면
            // 번갈아가면서 출력을 못하므로 null 반환.
            if (pq.isEmpty() && prev.getValue() > 0) {
                return null;
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        // Test code
        System.out.println(solution("aabb"));
        System.out.println(solution("aaaaabccd"));
        System.out.println(solution("aaca"));
    }
}
