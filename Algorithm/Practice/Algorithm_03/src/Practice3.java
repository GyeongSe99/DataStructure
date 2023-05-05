// Practice3
// intervals 라는 구간으로 이루어진 배열이 주어졌을 때,
// 오버랩 되는 구간을 합치는 프로그램을 작성하세요.

// 입출력 예시
// 입력: [2, 6], [1, 3], [15, 18], [8, 10]
// 출력: [1, 6] [8, 10] [15, 18]

import java.util.ArrayList;
import java.util.Arrays;

public class Practice3 {

    public static ArrayList<int[]> solution(int[][] intervals) {
        if (intervals == null || intervals.length < 2) {
            return new ArrayList<>();
        }

        sort(intervals);
//        Arrays.sort(intervals, (x, y) -> Integer.compare(x[0], y[0]));

        ArrayList<int[]> result = new ArrayList<>();
        // 첫 구간 우선 넣고 시작
        int[] curInterval = intervals[0];
        result.add(curInterval);

        for (int i = 1; i < intervals.length; i++) {
            // 구간 오버랩 도는 경우 구간 합치기
            if (curInterval[1] >= intervals[i][0]) {
                curInterval[1] = intervals[i][1];
            } else {
                // 오버랩 구간 아닌 경우 curInterval 업데이트 후 추가
                curInterval = intervals[i];
                result.add(curInterval);
            }
        }

        return result;
    }

    public static void sort(int[][] intervals) {
        quickSort(intervals, 0, intervals.length - 1);
    }

    public static void quickSort(int[][] arr, int left, int right) {
        // 왼쪽 인덱스과 오른쪽 인덱스가 같아지면 리턴
        if (left >= right) {
            return;
        }

        // 분할
        int pivot = partition(arr, left, right);

        quickSort(arr, left, pivot - 1);
        quickSort(arr, pivot + 1, right);
    }

    // 배열의 0번 인덱스를 기준으로 정렬하기 위한 분할
    public static int partition(int[][] arr, int left, int right) {
        int pivot = arr[left][0];
        int i = left;
        int j = right;

        while (i < j) {
            // 우측 : 작은 값을 만날때까지 진행
            while (arr[j][0] > pivot && i < j) {
                j--;
            }

            // 좌측 : 큰 값을 만날때까지 진행
            while (arr[i][0] <= pivot && i < j) {
                i++;
            }

            swap(arr, i, j);
        }
        swap(arr, left, i);

        return i;
    }

    public static void swap(int[][] arr, int i, int j) {
        int[] tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }


    public static void main(String[] args) {
        // Test code
        int[][] intervals = {{2, 6}, {1, 3}, {15, 18}, {8, 10}};

        for (int[] item: solution(intervals)) {
            System.out.print(Arrays.toString(item) + " ");
        }
        System.out.println();

    }
}
