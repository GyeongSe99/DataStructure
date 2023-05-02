// 비선형 자료구조 - 이진 탐색 트리_2
// AVL 트리 - 삽입

import java.util.LinkedList;
import java.util.Queue;

class Node {
    int key;
    int height;
    Node left;
    Node right;

    public Node(int key, Node left, Node right) {
        this.key = key;
        this.height = 0;
        this.left = left;
        this.right = right;
    }
}

class AVLTree {
    Node head;

    // 노드의 높이 구하기
    public int height(Node node) {
        if (node == null) {
            return -1;
        }
        return node.height;
    }

    // 회전 하고 나서의 root노드를 리턴

    // LL일때 오른쪽으로 회전
    public Node rightRotate(Node node) {
        Node lNode = node.left;

        // 자리 바꿔주기
        node.left = lNode.right;    // lNode의 right에 값이 있다면 그 값을 연결 없다면 null연결
        lNode.right = node;

        // 높이 새로 해주기
        // node.left = lNode.right인 상태(바뀐 높이)-왼쪽 애들 중 가장 높이 있는 애,
        // node.right는 노드에 딸려있던 원래 오른쪽 애들중 가장 높이 있는 애
        // 얘네보다 한칸 더 위에 있으므로 + 1


        // lNode.left는 원래 왼쪽에 붙어있던 애들
        // lNode.right는 현재 node가 붙어있음.
        // 그래서 node의 높이를 먼저 구하고 더 높아진 lNode의 높이를 구한다.
        lNode.height = Math.max(height(lNode.left), height(lNode.right)) + 1;

        return lNode;
    }

    // RR일때 왼쪽으로 회전
    public Node leftRotate(Node node) {
        Node rNode = node.right;

        node.right = rNode.left;
        rNode.left = node;

        // 높이 새로 정해주기는 LL과 같다
        node.height = Math.max(height(node.left), height(node.right)) + 1;
        rNode.height = Math.max(height(rNode.left), height(rNode.right)) + 1;

        return rNode;
    }

    public Node lrRotate(Node node) {
        // 노드.왼쪽을 기준으로 왼쪽 회전시킨 결과의 루트가(노드의 왼쪽의 오른쪽 애)
        // 노드의 왼쪽이 됨.
        node.left = leftRotate(node.left);
        return rightRotate(node);
    }

    public Node rlRotate(Node node) {
        // 노드의 오른쪽을 기준으로 오른쪽 회전시킨 결과의 루트가(노드.오른쪽의 왼쪽애)
        // node의 오른쪽이 됨.
        node.right = rightRotate(node.right);
        return leftRotate(node);
    }

    public int getBalance(Node node) {
        if (node == null) {
            return 0;
        }

       return height(node.left) - height(node.right);
    }

    public void insert(int key) {
        this.head = insert(this.head, key);
    }

    public Node insert(Node node, int key) {
        if (node == null) {
            // 값을 넣어 준 뒤 key의 부모로 돌아가게 됨.
            return new Node(key,null,null);
        }

        // 값의 크기에 따라 노드에 넣어준 뒤
        // 이때 키의 부모될 노드의 왼쪽에 들어갈 지 오른쪽에 들어갈지 결정
        if (key < node.key) {
            node.left = insert(node.left, key);
        } else {
            node.right = insert(node.right, key);
        }

        // 들어간 이후 key의 부모노드 높이 갱신
        node.height = Math.max(height(node.left), height(node.right)) + 1;

        // 밸런스 체크를 한 후
        int balance = getBalance(node);
        // 키의 부모의 높이는 1로 밸런스가 맞을 것임.
        // 회전 안한채로 키 부모의 부모로 넘어간다.
        // 키 부모의 부모의 높이를 갱신하고 밸런스 체크를 하면 이땐 밸런스가 맞을수도 있고 안맞을 수 있음.
        // 계속 반복.

        // 밸런스가 맞지 않으면 각 상황에 맞게 회전시켜주기
        // LL
        if (balance > 1 && key < node.left.key) {
            return rightRotate(node);
        }

        // RR
        if (balance < -1 && key > node.right.key) {
            return leftRotate(node);
        }

        // LR
        if (balance > 1 && key > node.right.key) {
            return lrRotate(node);
        }

        // RL
        if (balance < -1 && key < node.right.key) {
            return rlRotate(node);
        }

        return node;
    }

    public void levelOrder(Node node) {
        Queue<Node> queue = new LinkedList();
        queue.add(node);
        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            System.out.print(cur.key + " ");
            if (cur.left != null) {
                queue.offer(cur.left);
            }

            if (cur.right != null) {
                queue.offer(cur.right);
            }
        }
        System.out.println();
    }
}

public class Practice1 {
    public static void main(String[] args) {
        // Test code
        AVLTree avl = new AVLTree();

        // Insert
        avl.insert(30);
        avl.insert(20);
        avl.insert(10);     // LL
        avl.levelOrder(avl.head);

        avl.insert(40);
        avl.insert(50);     // RR
        avl.levelOrder(avl.head);

        avl.insert(5);
        avl.insert(7);      // LR
        avl.levelOrder(avl.head);

        avl.insert(60);
        avl.insert(55);     // RL
        avl.levelOrder(avl.head);
    }
}
