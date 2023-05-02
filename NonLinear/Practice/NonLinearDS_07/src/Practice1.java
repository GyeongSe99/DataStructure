// Practice 1
// ArrayList 로 최대 힙 구현

import java.util.ArrayList;

class MaxHeap {
    ArrayList<Integer> heap;

    public MaxHeap() {
        this.heap = new ArrayList<>();
        this.heap.add(0);
    }

    public void insert(int data) {
        // 데이터 맨 끝에 넣기
        heap.add(data);

        // 부모와 비교하여 트리클링 해주기
        int cur = heap.size() - 1;
        // cur이 1보다 작다 : heap의 사이즈가 1이다.
        // cur이 마지막 데이터의 인덱스를 뜻하므로 cur / 2는 cur의 부모인덱스를 뜻함.
        while (cur > 1 && heap.get(cur / 2) < heap.get(cur)) {
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

        // 첫번째에 가장 마지막 데이터를 옮겨준다.
        heap.set(1, heap.get(heap.size() - 1));
        heap.remove(heap.size() - 1);

        int cur = 1;
        while (true) {
            int leftIdx = cur * 2;
            int rightIdx = cur * 2 + 1;
            int targetIdx = -1;

            // heap사이즈보다 rightIdx가 작다면 오른쪽 자식이 있다는 뜻.
            // 오른쪽 자식이 있다는 것은 왼쪽부터 채우므로 자식이 둘다 있다는 뜻.
            if (rightIdx < heap.size()) {
                // 자식 노드 중 큰 값이 타겟.
                targetIdx = heap.get(leftIdx) > heap.get(rightIdx) ? leftIdx : rightIdx;
            } else if (leftIdx < heap.size()) {
                targetIdx = leftIdx;
            } else {
                break;
            }

            // 현재 값이 타겟인덱스의 값보다 크면 더이상 밑으로 내려갈 필요가 없으므로 끝.
            if (heap.get(cur) > heap.get(targetIdx)) {
                break;
            } else {    // 현재값이 타겟인덱스의 값보다 더 작다면 자리를 바꿔준다.
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
    public static void main(String[] args) {
        // Test code
        MaxHeap maxHeap = new MaxHeap();
        System.out.println("== 데이터 삽입 ==");
        maxHeap.insert(30);
        maxHeap.insert(40);
        maxHeap.insert(10);
        maxHeap.printTree();
        maxHeap.insert(50);
        maxHeap.insert(60);
        maxHeap.insert(70);
        maxHeap.printTree();
        maxHeap.insert(20);
        maxHeap.printTree();
        maxHeap.insert(30);
        maxHeap.printTree();

        System.out.println();
        System.out.println("== 데이터 삭제 ==");
        System.out.println("삭제: " + maxHeap.delete());
        maxHeap.printTree();
        System.out.println("삭제: " + maxHeap.delete());
        maxHeap.printTree();
        System.out.println("삭제: " + maxHeap.delete());
        maxHeap.printTree();
    }
}
