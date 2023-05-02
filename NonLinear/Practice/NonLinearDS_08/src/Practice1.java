// Practice 1
// 최소 힙에서 특정 값을 변경하는 코드를 작성하세요.
// 특정 값이 여러개라면 모두 바꿔주세요.

import java.util.ArrayList;

class MinHeap{
    ArrayList<Integer> heap;

    public MinHeap() {
        this.heap = new ArrayList<>();
        this.heap.add(0);
    }

    public void insert(int data) {
        heap.add(data);

        int cur = heap.size() - 1;
        while (cur > 1 && heap.get(cur / 2) > heap.get(cur)) {
            int parentVal = heap.get(cur / 2);
            heap.set(cur / 2, data);
            heap.set(cur, parentVal);

            cur /= 2;
        }
    }

    public Integer delete() {
        if (heap.size() == 1) {
            System.out.println("Heap is empty!");
            return null;
        }

        int target = heap.get(1);

        heap.set(1, heap.get(heap.size() - 1));
        heap.remove(heap.size() - 1);

        int cur = 1;
        while (true) {
            int leftIdx = cur * 2;
            int rightIdx = cur * 2 + 1;
            int targetIdx = -1;

            if (rightIdx < heap.size()) {
                targetIdx = heap.get(leftIdx) < heap.get(rightIdx) ? leftIdx : rightIdx;
            } else if (leftIdx < heap.size()) {
                targetIdx = cur * 2;
            } else {
                break;
            }

            if (heap.get(cur) < heap.get(targetIdx)) {
                break;
            } else {
                int parentVal = heap.get(cur);
                heap.set(cur, heap.get(targetIdx));
                heap.set(targetIdx, parentVal);
                cur = targetIdx;
            }
        }

        return target;
    }

    public void printTree() {
        for (int i = 1; i < this.heap.size(); i++) {
            System.out.print(this.heap.get(i) + " ");
        }
        System.out.println();
    }
}

public class Practice1 {
    public static void solution(MinHeap minHeap, int from, int to) {
        // 힙을 쭉 돌면서 from과 같은 값을 찾아 to로 바꿔준다
        for (int i = 0; i < minHeap.heap.size(); i++) {
            if (minHeap.heap.get(i) == from) {
                minHeap.heap.set(i, to);

                // 바꾸고 나서 heap의 조건에 맞게 정렬
                moveUp(minHeap, i);
                moveDown(minHeap, i);
            }
        }
    }

    // 아래쪽이 더 작을때 위로 올려주는 메소드
    public static void moveUp(MinHeap minHeap, int idx) {
        int cur = idx;

        while (cur > 1 && minHeap.heap.get(cur / 2) > minHeap.heap.get(cur)) {
            int parentVal = minHeap.heap.get(cur / 2);
            minHeap.heap.set(cur / 2, minHeap.heap.get(cur));
            minHeap.heap.set(cur, parentVal);

            cur /= 2;
        }
    }


    public static void moveDown(MinHeap minHeap, int idx) {
        int cur = idx;
        while (true) {
            int leftIdx = cur * 2;
            int rightIdx = cur * 2 + 1;
            int targetIdx = -1;

            if (rightIdx < minHeap.heap.size()) {
                targetIdx = minHeap.heap.get(leftIdx) < minHeap.heap.get(rightIdx) ? leftIdx : rightIdx;
            } else if (leftIdx < minHeap.heap.size()) {
                targetIdx = leftIdx;
            } else {
                break;
            }

            if (minHeap.heap.get(cur) < minHeap.heap.get(targetIdx)) {
                break;
            } else {
                int parentVal = minHeap.heap.get(cur);
                minHeap.heap.set(cur, minHeap.heap.get(targetIdx));
                minHeap.heap.set(targetIdx, parentVal);
                cur = targetIdx;
            }

        }
    }

    public static void main(String[] args) {
        // Test code
        MinHeap minHeap = new MinHeap();
        minHeap.insert(30);
        minHeap.insert(40);
        minHeap.insert(10);
        minHeap.insert(50);
        minHeap.insert(60);
        minHeap.insert(70);
        minHeap.insert(20);
        minHeap.insert(30);
        System.out.println("== 데이터 변경 전 ==");
        minHeap.printTree();

        System.out.println("== 데이터 변경 후 ==");
        solution(minHeap, 30, 100);
        minHeap.printTree();

        solution(minHeap, 60, 1);
        minHeap.printTree();
    }
}
