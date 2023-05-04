// 알고리즘 - 정렬_3
// 기수 정렬

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void radixSort(int[] arr) {
        // 기수 테이블 만들기
        ArrayList<Queue<Integer>> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new LinkedList<>());
        }

        int idx = 0;
        int div = 1; // 자릿수
        int maxLen = getMaxLen(arr);

        // 자리수 만큼 기수 정렬 반복
        for (int i = 0; i < maxLen; i++) {
            // 각 자리에 맞는 큐 위치에 넣어주기
            for (int j = 0; j < arr.length; j++) {
                list.get((arr[j] / div) % 10).offer(arr[j]);
            }

            // 다시 순서대로 가져와서 배치
            for (int j = 0; j < 10; j++) {
                Queue<Integer> queue = list.get(j);

                while (!queue.isEmpty()) {
                    arr[idx++] = queue.poll();
                }
            }

            // 그다음 자릿수를 정렬하기 위한 단계
            idx = 0;
            div *= 10;
        }
    }

    // 가장 긴 자릿수 구하는 메소드
    public static int getMaxLen(int[] arr) {
        int maxLen = 0;
        for (int i = 0; i < arr.length; i++) {
            // 딱 나누어 떨어지지 않더라고 int로 형변환 하면서 정리됨.
            int len = (int)Math.log10(arr[i]) + 1;
            if (maxLen < len) {
                maxLen = len;
            }
        }
        return maxLen;
    }


    public static void main(String[] args) {
        // Test code
        int[] arr = {10, 32, 52, 27, 48, 17, 99, 56};
        radixSort(arr);
        System.out.println("기수 정렬: " + Arrays.toString(arr));
    }
}
