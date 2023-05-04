// 힙 정렬

import java.util.Arrays;

public class Main2 {
    
    public static void heapSort(int[] arr) {
        // 힙으로 재구성 (maxHeap 기준, 마지막 부모 노드부터)
        // 보통 자식이 있는 노드는 배열을 반으로 잘랐을 때 그 앞쪽까지만 자식이 있고
        // 그 이후 인덱스들은 자식이 없음
        // 자식이 있는 마지막 노드는 길이/2-1
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            heapify(arr, i, arr.length);
        }

        // 힙 구조는 트리 구조상으로는 정렬이 되어있지만
        // 배열 구조상으로는 정렬이 되어있지 않음.
        // 힙정렬만 하고 난 후 -> 7, 6, 4, 5, 1, 3, 2

        // 배열 구조상으로 정렬
        for (int i = arr.length - 1; i > 0; i--) {
            // 스왑을 통해 가장 큰 수를 맨 뒤로 보낸다.
            // for문의 i가 점점 값이 작아지기때문에 맨 뒤로 보낸 값은 맨 처음 빼고는 건들이지 않는다.
            // heapify에서 가장 큰 수가 맨 앞에오는데 다음 인덱스에서 그걸 맨 뒤로 보내기때문에 정렬이 된다.
            swap(arr, 0, i);
            heapify(arr, 0, i);
        }
    }

    // 최대 힙으로 정렬
    public static void heapify(int[] arr, int parentIdx, int size) {
        // 자식 노드 인덱스
        int leftIdx = 2 * parentIdx + 1;
        int rightIdx = 2 * parentIdx + 2;
        // 최대 힙이므로 parent의 자리는 값이 가장 커야함
        int maxIdx = parentIdx;

        // 왼쪽 자식 노드가 max보다 크면 maxIdx 를 해당 인덱스로 교체
        if (leftIdx < size && arr[maxIdx] < arr[leftIdx]) {
            maxIdx = leftIdx;
        }

        // 오른쪽 자식 노드가 max보다 크면 maxIdx 를 해당 인덱스로 교체
        if (rightIdx < size && arr[maxIdx] < arr[rightIdx]) {
            maxIdx = rightIdx;
        }

        // 최댓값이 원래 부모값이 아니라 자식인 상태라면
        // 자식과 부모 자리를 바꿔 부모값이 더 크게 만들어 주어야함.
        if (parentIdx != maxIdx) {
            swap(arr, maxIdx, parentIdx);
            // 스왑을 하고 나면 또 자리를 정리해주어야 할 경우가 생길 수 있기 때문에
            // (부모는 제대로 정렬이 되었지만 자식이 잘 되어있는지는 모름)
            // maxIdx의 밑에있는(자식들의) 트리도 쭉 검사 하도록 재귀 호출
            heapify(arr, maxIdx, size);
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        // Test code
        int[] arr = {3, 5, 2, 7, 1, 4, 6};
        heapSort(arr);
        System.out.println("힙 정렬: " + Arrays.toString(arr));
    }
}
