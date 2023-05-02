// Practice1
// 종이 접기
// 종이를 반으로 접었을 때, 안으로 파인 부분은 0, 볼록 튀어나온 부분은 1이라고 하자.
// 종이를 접을 때는 오른쪽에서 왼쪽으로 접는다.
// 종이를 N번 접었을 때의 접힌 상태를 출력하는 문제를 작성하세요.

// 입출력 예시
// 입력: 1
// 출력: 0

// 입력: 2
// 출력: 0, 0, 1

// 입력: 3
// 출력: 0, 0, 1, 0, 0, 1, 1


public class Practice1 {
    public static void solution(int n) {
        int[] binaryTree = new int[(int)Math.pow(2, n)];

        // 맨 처음 접었을땐 그냥 0
        binaryTree[0] = 0;
        // leaf노드를 제외한 횟수만큼 자식노드를 만든다.
        // 맨 처음은 그냥 넣었으므로 n이 1이면 범위는 i < 0. 이때 이 반복문은 돌지 않는다.
        // 내가 만들고 싶은 level의 이전 level노드까지만 자식을 만들어야함.
        for (int i = 0; i < (int)Math.pow(2, n - 1) - 1; i++) {
            binaryTree[i * 2 + 1] = 0;  // 왼쪽 자식은 0
            binaryTree[i * 2 + 2] = 1;  // 오른쪽 자식은 1
        }

        inOrder(binaryTree, 0);
        System.out.println();

    }

    public static void inOrder(int[] arr, int idx) {
        int left = 2 * idx + 1;
        int right = 2 * idx + 2;

        // arr의 크기를 Math.pow로 만들어서 마지막 한칸이 남아 -1을 해준다
        if (left < arr.length - 1) {
            inOrder(arr, left);
        }

        System.out.print(arr[idx] + " ");

        if (right < arr.length -1) {
            inOrder(arr, right);
        }
    }

    public static void main(String[] args) {
        // Test code
        solution(1);
        solution(2);
        solution(3);
    }
}
