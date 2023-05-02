// Practice3
// 트리 구조 복습 / 구현

import java.util.LinkedList;
import java.util.Queue;

class Node2 {
    char data;
    Node2 left;
    Node2 right;
    Node2 parent;

    public Node2(char data, Node2 left, Node2 right, Node2 parent) {
        this.data = data;
        this.left = left;
        this.right = right;
        this.parent = parent;
    }
}

class BinaryTree3 {
    Node2 head;

    BinaryTree3(char[] arr) {
        Node2[] nodes = new Node2[arr.length];
        for (int i = 0; i < arr.length; i++) {
            nodes[i] = new Node2(arr[i], null, null, null);
        }

        for (int i = 0; i < arr.length; i++) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;

            if (left < arr.length) {
                nodes[i].left = nodes[left];
                nodes[left].parent = nodes[i];
            }

            if (right < arr.length) {
                nodes[i].right = nodes[right];
                nodes[right].parent = nodes[i];
            }
        }

        this.head = nodes[0];
    }

    public Node2 searchNode(char data) {
        Queue<Node2> queue = new LinkedList<>();

        // levelOrder로 돌면서 찾기
        queue.add(this.head);
        while (!queue.isEmpty()) {
            // 큐에서 꺼내본 값이 찾고자 하는 데이터와 일치하면 리턴
            Node2 cur = queue.poll();
            if (cur.data == data) {
                return cur;
            }

            // 일치하는 값이 없다면 계속
            if (cur.left != null) {
                queue.offer(cur.left);
            }

            if (cur.right != null) {
                queue.offer(cur.right);
            }
        }

        // 다 돌고 나서도 없으면 null 리턴
        return null;
    }

    public Integer checkSize(char data) {
        Node2 node = this.searchNode(data);

        Queue<Node2> queue = new LinkedList<>();
        queue.add(node);
        int size = 0;
        while (!queue.isEmpty()) {
            Node2 cur = queue.poll();

            if (cur.left != null) {
                queue.offer(cur.left);
                size++;
            }

            if (cur.right != null) {
                queue.offer(cur.right);
                size++;
            }
        }
        return size + 1;


    }
}

public class Practice3 {

    public static void main(String[] args) {
        char[] arr = new char[10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (char)('A' + i);
        }

        BinaryTree3 bt = new BinaryTree3(arr);

        // Root node 루트노드 출력
        System.out.println("Root: " + bt.head.data);


        // B's child node
        // B노드를 먼저 찾고 그 자식 출력
        Node2 B= bt.searchNode('B');
        if (B.left != null) {
            System.out.println("B -> left child: " + B.left.data);
        }
        if (B.right != null) {
            System.out.println("B -> right child: " + B.right.data);
        }

        // F's parent node
        Node2 F = bt.searchNode('F');
        System.out.println("F -> parent: " + F.parent.data);


        // Leaf node
        System.out.print("Leaf node: ");
        for (int i = 0; i < arr.length; i++) {
            Node2 cur = bt.searchNode((char)('A' + i));

            if (cur.left == null && cur.right == null) {
                System.out.print(cur.data + " ");
            }
        }
        System.out.println();


        // E's Depth
        // E의 위치를 찾고 나서 부모를 몇개나 건너야하는지 숫자를 센다.
        Node2 E = bt.searchNode('E');
        Node2 cur = E;
        int cnt = 0;
        while (cur.parent != null) {
            cur = cur.parent;
            cnt++;
        }
        System.out.println("E depth: " + cnt);


        // Level2 Node
        System.out.print("Level2 node: ");
        for (int i = 0; i < arr.length; i++) {
            Node2 target = bt.searchNode((char)('A' + i));
            cur = target;
            cnt = 0;
            while (cur.parent != null) {
                cur = cur.parent;
                cnt++;
            }

            // depth가 2인 모든 노드 출력
            if (cnt == 2) {
                System.out.print(target.data + " ");
            }
        }
        System.out.println();


        // Height
        int maxCnt = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            Node2 target = bt.searchNode((char)('A' + i));
            cur = target;
            cnt = 0;
            while (cur.parent != null) {
                cur = cur.parent;
                cnt++;
            }

            if (maxCnt < cnt) {
                maxCnt = cnt;
            }
        }
        System.out.println();


        // B's Size
        int size = bt.checkSize('B');
        System.out.println("B size = " + size);

    }
}
