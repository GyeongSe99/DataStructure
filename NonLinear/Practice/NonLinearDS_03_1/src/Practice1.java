// Practice1
// 앞의 BST 삽입, 삭제 코드를 재귀 형태로 구현

import java.util.LinkedList;
import java.util.Queue;

class BinarySearchTree2 {
    Node head;

    BinarySearchTree2(int key) {
        this.head = new Node(key, null, null);
    }

    public Node addNodeRecursive(Node cur, int key) {
        if (cur == null) {  // 마지막까지 가면 새로운 노드를 만들어 리턴한다.
            return new Node(key, null, null);
        }

        // 넣을려는 데이터의 값이 현재 위치한 노드의 값보다 작으면
        if (key < cur.key) {
            // 왼쪽으로 가본다. 왼쪽끝으로 갔다면(기저조건인 cur == null) 새로운 노드를 만들어 리턴할 것이다.
            // 리턴된 새로운 노드가 왼쪽 자식이 된다
            cur.left = addNodeRecursive(cur.left, key);
        } else {
            cur.right = addNodeRecursive(cur.right, key);
        }

        return cur;
    }

    public Node removeNodeRecursive(Node cur, int key) {
        if (cur == null) {  // 값을 못찾았다면 null반환
            return null;
        }
        
        if (key < cur.key) {
            cur.left = removeNodeRecursive(cur.left, key);
        } else if (key > cur.key) {
            cur.right = removeNodeRecursive(cur.right, key);
        } else {    // cur.key = key : 값을 찾으면 else로 넘어옴.
            // 자식 노드가 한개이거나 아얘 없거나
            if (cur.left == null) { // 찾은 값의 왼쪽이 null이면
                // 오른쪽 자식이 있는지 없는지 모르겠지만
                // 자식이 있다면 그 값을 리턴하고
                // 없다면 null 리턴하겠지?
                return cur.right;
            } else if (cur.right == null) {
                return cur.left;
            }
            // 자식노드가 두개인 경우
            else {
                Node predecessor = cur;
                Node successor = cur.left;

                // 오른쪽 맨 끝으로 가서 후계자 찾기
                while (successor.right != null) {
                    predecessor = successor;
                    successor = successor.right;
                }
                // 찾고나서 원래 후계자가 있던 자리에 후계자의 자식(없다면 null)을 넣고
                predecessor.right = successor.left;
                cur.key = successor.key;    // 현재 key값에 후계자의 값을 넣기
            }
        }

        return cur;
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
        // 노드 삽입
        BinarySearchTree2 bst = new BinarySearchTree2(20);
        bst.head = bst.addNodeRecursive(bst.head, 10);
        bst.head = bst.addNodeRecursive(bst.head, 30);
        bst.head = bst.addNodeRecursive(bst.head, 1);
        bst.head = bst.addNodeRecursive(bst.head, 15);
        bst.head = bst.addNodeRecursive(bst.head, 25);
        bst.head = bst.addNodeRecursive(bst.head, 13);
        bst.head = bst.addNodeRecursive(bst.head, 35);
        bst.head = bst.addNodeRecursive(bst.head, 27);
        bst.head = bst.addNodeRecursive(bst.head, 40);
        bst.levelOrder(bst.head);

        // 노드 삭제
        bst.head = bst.removeNodeRecursive(bst.head,40);
        bst.levelOrder(bst.head);
        bst.head = bst.removeNodeRecursive(bst.head, 25);
        bst.levelOrder(bst.head);
        bst.head = bst.removeNodeRecursive(bst.head, 20);
        bst.levelOrder(bst.head);
    }
}
