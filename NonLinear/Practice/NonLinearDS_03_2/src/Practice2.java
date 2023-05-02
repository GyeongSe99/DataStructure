// AVL 트리 - 삭제

class AVLTree2 extends AVLTree{

    public void delete(int key) {
        this.head = delete(this.head, key);
    }

    public Node delete(Node node, int key) {
        // 계속 타고 들어갔는데 해당 key와 같은 값을 갖는 노드를 찾지 못한 경우
        if (node == null) {
            return null;
        }

        if (key < node.key) {
            node.left = delete(node.left, key);
        } else if (key > node.key) {
            node.right = delete(node.right, key);
        } else {    // node.key == key 삭제할 노드의 위치를 찾은 경우
            if (node.left == null) {    // 자식이 왼쪽만 있거나 둘다 없는경우
                return node.right;  // 삭제노드의 오른쪽을 리턴시켜 삭제노드 부모에게 붙여준다.
            } else if (node.right == null) {    // 자식이 오른쪽만 있거나 둘다 없는경우
                return node.left;   // 삭제노드 왼쪽을 리턴시켜 삭제노드 부모에게 붙여준다
            } else {    // 자식노드가 둘인 경우
                // 왼쪽에서 가장 큰 자식을 찾아 맨 위로(삭제된 노드의 자리)올리고
                // 그 밑과 비교하면서 올바른 자리 찾기

                // 왼쪽에서 제일 큰 애 찾기(후계자)
                Node predecessor = node;    // 후계자의 부모
                Node successor =  node.left;    // 후계자

                // 제일 큰 애 찾기 위해 오른쪽으로 쭉 간다.
                while (successor.right != null) {
                    predecessor = successor;
                    successor = successor.right;
                }

                // 후계자 부모의 오른쪽에 후계의 왼쪽 자식을 넣고
                predecessor.right = successor.left;
                // 삭제할 노드 데이터에 후계자의 값을 넣는다.
                node.key = successor.key;
            }
        }

        node.height = Math.max(height(node.left), height(node.right)) + 1;

        int balance = getBalance(node);

        // LL
        if (balance > 1 && getBalance(node.left) > 0) {
            return rightRotate(node);
        }

        // RR
        if (balance < -1 && getBalance(node.right) < 0) {
            return leftRotate(node);
        }

        // LR
        if (balance > 1 && getBalance(node.left) < 0) {
            return lrRotate(node);
        }

        // RL
        if (balance < -1 && getBalance(node.right) > 0) {
            return rlRotate(node);
        }

        return node;
    }
}

public class Practice2 {
    public static void main(String[] args) {
        // Test code
        AVLTree2 avl = new AVLTree2();

        avl.insert(30);
        avl.insert(20);
        avl.insert(40);
        avl.insert(10);
        avl.levelOrder(avl.head);
        avl.delete(40);     // LL
        avl.levelOrder(avl.head);

        avl.insert(40);
        avl.delete(10);     // RR
        avl.levelOrder(avl.head);

        avl.insert(25);
        avl.delete(40);     // LR
        avl.levelOrder(avl.head);

        avl.insert(27);
        avl.delete(20);     // RL
        avl.levelOrder(avl.head);
    }
}
