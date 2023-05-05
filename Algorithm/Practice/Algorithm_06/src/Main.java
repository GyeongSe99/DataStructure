// 알고리즘 - 투 포인터
// for-loop vs two pointers

import java.util.Arrays;

public class Main {
    public static int[] forLoop(int[] arr, int target) {
        // 구간을 기록
        int[] result = {-1, -1};

        for (int i = 0; i < arr.length; i++) {
            int sum = arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                // target값을 찾으면 구간 리턴
                if (sum == target) {
                    result[0] = i;
                    result[1] = j - 1;
                    break;
                }
                // 못찾으면 계속 더하기
                sum += arr[j];
            }

            // 이중 for문이라서 나갈 수 있게 두번 처리한것.
            if (sum == target) {
                break;
            }
        }

        return result;
    }

    public static int[] twoPointers(int[] arr, int target) {
        int p1 = 0;
        int p2 = 0;
        int sum = 0;
        int[]  result = {-1, -1};

        while (true) {
            // sum이 target보다 많으면 sum에서 첫번째 포인터 값 뺀 후 한칸 앞으로 이동.
            if (sum > target) {
                sum -= arr[p1++];
            } else if (p2 == arr.length) {  // 값을 못찾으면 p2가 끝까지 감.
                break;
            } else {    // 그외 계속 p2의 값을 옮겨주면서 구간 합 찾기.
                sum += arr[p2++];
            }

            if (sum == target) {
                result[0] = p1;
                result[1] = p2 - 1;
                break;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 5, 3, 7, 2, 4, 3, 2};
        System.out.println(Arrays.toString(forLoop(arr, 9)));
        System.out.println(Arrays.toString(forLoop(arr, 14)));
        System.out.println();

        System.out.println(Arrays.toString(twoPointers(arr, 9)));
        System.out.println(Arrays.toString(twoPointers(arr, 14)));
    }
}
