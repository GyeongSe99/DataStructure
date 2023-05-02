// 비선형 자료구조 - 이진 탐색 트리

import java.util.LinkedList;
import java.util.Queue;

class Node {
    int key;
    Node left;
    Node right;

    Node(int key, Node left, Node right) {
        this.key = key;
        this.left = left;
        this.right = right;
    }
}

class BinarySearchTree {
    Node head;

    BinarySearchTree(int key) {
        this.head = new Node(key, null, null);
    }

    public void addNode(int key) {
        // 트리에 아무것도 없을 때
        if (this.head == null) {
            this.head = new Node(key, null, null);
        } else {    // 하나라도 값이 있을 때
            Node cur = this.head;   // 헤드에서부터 알맞은 자리를 탐색

            while (true) {
                Node pre = cur;

                // cur의 데이터보다 추가할 데이터의 값이 작으면 왼쪽자식
                if (key < cur.key) {
                    cur = cur.left;

                    // cur == null 이란 것은 더 이상 왼쪽 자식이 없다는 뜻.
                    // 새로운 노드를 만들어 pre와 연결 작업을 해준다.
                    if (cur == null) {
                        pre.left = new Node(key, null, null);
                        break;
                    }
                } else {    // 값이 보다 크면 오른쪽 자식
                    cur = cur.right;

                    if (cur == null) {
                        pre.right = new Node(key, null, null);
                        break;
                    }
                }
            }
        }
    }

    public void removeNode(int key) {
        Node parent = null;
        Node successor = null;  // 지울려는 대상의 후계자(왼쪽에서 가장 큰 값 or 오른쪽에서 가장 작은 값)
        Node predecessor = null;    // 후계자의 부모
        Node child = null;  // successor에 자식이 있는지 없는지 확인하는 변수

        Node cur = this.head;
        while (cur != null) {
            if (key == cur.key) {   // 지우려는 값과 같은 값을 찾으면 끝
                break;
            }

            // 값 찾기
            parent = cur;
            if (key < cur.key) {
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }

        // cur == null : 값을 찾지 못하여 끝까지 간 상태
        if (cur == null) {
            System.out.println("Key에 해당하는 노드가 없습니다.");
        }

        if (cur.left == null && cur.right == null) {    // leaf노드인 경우
            if (parent == null) {   // 부모가 없으면 cur이 헤드
                this.head = null;
            } else {
                if (parent.left == cur) {
                    parent.left = null;
                } else {
                    parent.right = null;
                }
            }
        }
        // 자식이 하나일 때
        else if (cur.left != null && cur.right == null || cur.left == null && cur.right != null) {
            if (cur.left != null) { // 왼쪽 자식이 있다면
                child = cur.left;   // 왼쪽 자식으로 위치를 옮겨본다.
            } else {    // 오른쪽 자식이 있다면
                child = cur.right;  // 오른쪽 자식으로 위치를 옮겨본다.
            }

            // 삭제하려는 노드가 root노드였는데 자식이 하나인 경우
            if (parent == null) {
                this.head = child;
            } else {    // 삭제하려는 노드가 root가 아닌 경우
                if (parent.left == cur) {   // 삭제하려는 노드의 부모의 왼쪽자식이 삭제하려는 노드라면
                    parent.left = child;    // 부모의 왼쪽에 삭제하려는 노드의 자식을 넣는다.
                } else {
                    parent.right = child;
                }
            }
        }
        // 자식 노드가 둘인 경우
        // 왼족 자식들 중에 가장 큰값을 찾을 것임(방법 1)
        else {
            predecessor = cur;  // 후계자의 부모 cur에서부터 시작
            successor = cur.left;   // 후계자의 왼쪽에서 가장 큰 자식 찾기

            // 왼쪽 중 가장 큰 값(오른쪽 값)찾기
            // 가장 큰 값을 찾아야하므로 반복문 돌면서 right의 끝까지 간다.
            while (successor.right != null) {
                predecessor = successor;
                successor = successor.right;
            }

            // successor의 오른쪽자식 노드는 없고(오른쪽 맨 끝인애니깐)
            // 만약에 왼쪽자식이 있다면 왼쪽 자식을 넣고
            // 왼쪽이 null이라면 predecessor의 오른쪽은 null이 된다.
            predecessor.right = successor.left;
            // cur의 자식들과 연결되어있던 것을 successor에 연결
            successor.left = cur.left;
            successor.right = cur.right;
            // cur의 부모와 successor를 연결
            if (parent == null) {
                this.head = successor;
            } else {
                if (parent.left == cur) {
                    parent.left = successor;
                } else {
                    parent.right = successor;
                }
            }
        }
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


public class Main {
    public static void main(String[] args) {
        // Test code
        // 노드 삽입
        BinarySearchTree bst = new BinarySearchTree(20);
        bst.addNode(10);
        bst.addNode(30);
        bst.addNode(1);
        bst.addNode(15);
        bst.addNode(25);
        bst.addNode(13);
        bst.addNode(35);
        bst.addNode(27);
        bst.addNode(40);
        bst.levelOrder(bst.head);

        // 노드 삭제
        bst.removeNode(40);
        bst.levelOrder(bst.head);
        bst.removeNode(25);
        bst.levelOrder(bst.head);
        bst.removeNode(20);
        bst.levelOrder(bst.head);

    }
}
