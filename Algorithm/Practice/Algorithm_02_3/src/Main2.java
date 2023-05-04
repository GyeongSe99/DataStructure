// 계수 정렬

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class Main2 {
    public static void countingSort(int[] arr) {
        int max = Arrays.stream(arr).max().getAsInt();
        // 카운트 테이블 만들기 (배열)
        // 원소 중 가장 큰 값을 찾아 배열의 사이즈를 정해준다.
        int[] cntArr = new int[max + 1];

        // 카운트 테이블에 배열원소 집어넣기
        for (int i = 0; i < arr.length; i++) {
            cntArr[arr[i]]++;
        }

        int idx = 0;
        // 카운트 테이블을 쭉 돌면서
        // 카운트 테이블의 값이 0보다 크면 = 숫자가 등장한 적이 있다는 뜻.
        for (int i = 0; i < cntArr.length; i++) {
            while (cntArr[i] > 0) {
                // 원래 배열에 넣어준 뒤
                arr[idx++] = i;
                // 하나 출력할 때마다 개수 하나씩 줄이기
                cntArr[i] -= 1;
            }
        }

        // 해시맵 이용
        // 배열을 이용했을때 최대값 하나만 너무 크면 그 크기만큼의 배열을 만들어야 하기 때문에
        // 공간복잡도 면에서 효율이 떨어진다.
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int item : arr) {
            map.put(item, map.getOrDefault(item, 0) + 1);
        }

        int idx2 = 0;
        ArrayList<Integer> list = new ArrayList<>(map.keySet());
        // hash맵은 순서가 보장되어있지 않으므로 정렬을 해준다.
        Collections.sort(list);

        for (int i = 0; i < list.size(); i++) {
            int cnt = map.get(list.get(i));
            while (cnt > 0) {
                arr[idx2++] = list.get(i);
                cnt--;
            }
        }
    }

    public static void main(String[] args) {
        // Test code
        int[] arr = {10, 32, 10, 27, 32, 17, 99, 56};
        countingSort(arr);
        System.out.println("계수 정렬: " + Arrays.toString(arr));
    }
}
