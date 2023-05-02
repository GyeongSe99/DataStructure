// 비선형 자료구조 - 트라이 (Trie)

import java.util.HashMap;

class Node {
    HashMap<Character, Node> child;
    boolean isTerminal;

    public Node() {
        this.child = new HashMap<>();
        this.isTerminal = false;
    }
}

class Trie {
    Node root;
    public Trie() {
        this.root = new Node();
    }

    public void insert(String str) {
        Node cur = root;

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);

            // c라는 키값이 있으면 넘어가고 없으면 추가 하는 메소드
            cur.child.putIfAbsent(c, new Node());
            cur = cur.child.get(c);

            // 끝부분은 끝이라고 mark해놓기
            if (i == str.length() - 1) {
                cur.isTerminal = true;
                return;
            }
        }
    }

    public boolean search(String str) {
        Node cur = this.root;

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);

            if (cur.child.containsKey(c)) {
                cur = cur.child.get(c);
            } else {
                return false;
            }

            // 끝에 도달했을때 terminal이 false라면 해당 단어는 이 트라이에 없다는 뜻.
            // 그래서 false를 리턴해주어야함.
            if (i == str.length() - 1) {
                if (!cur.isTerminal) {
                    return false;
                }
            }
        }
        return true;
    }

    public void delete(String str) {
        boolean ret = this.delete(this.root, str, 0);
        if (ret) {
            System.out.println(str + " 삭제완료");
        } else {
            System.out.println("단어가 없습니다.");
        }
    }

    public boolean delete(Node node, String str, int idx) {
        char c = str.charAt(idx);

        if (!node.child.containsKey(c)) {
            return false;
        }

        // 그 다음으로 넘어감
        Node cur = node.child.get(c);
        idx++;

        // 끝에 도달했다면
        if (idx == str.length()) {
            // 현재 위치가 끝이 맞는지 확인
            if (!cur.isTerminal) {  // 끝이 아니라면
                return false;
            }

            // 끝이라면 끝이었던 부분을 false로 만들어주고
            cur.isTerminal = false;

            // cur = node.child의 Node이므로
            // cur의 child는 node의 child의 child.
            // 자식이 있다면 다른 더 긴 단어가 있다는 뜻이므로 terminal표시만 없애주고 끝
            if (cur.child.isEmpty()) {
                node.child.remove(c);
            }
        } else {    // 끝이 아니라면
            // 재귀적으로 호출한 부분이 false라는 뜻은
            // 끝까지 갔는데 해당 char이 없었거나, 끝부분에 도달했는데 그 부분이 끝표시가 아니었다는 뜻
            // 똑같이 false리턴
            if (!this.delete(cur, str, idx)) {
                return false;
            }

            // this.delete가 true로 리턴된 상태에서
            // 끝이 아니고, 자식이 없으면 지워주기
            if (!cur.isTerminal && cur.child.isEmpty()) {
                node.child.remove(c);
            }
        }
        return true;
    }

}

public class Main {
    public static void main(String[] args) {
        // Test code
        Trie trie = new Trie();
        trie.insert("apple");
        trie.insert("april");
        trie.insert("app");
        trie.insert("ace");
        trie.insert("bear");
        trie.insert("best");
        System.out.println(trie.search("apple"));   // true
        System.out.println(trie.search("april"));   // true
        System.out.println(trie.search("app"));     // true
        System.out.println(trie.search("ace"));     // true
        System.out.println(trie.search("bear"));    // true
        System.out.println(trie.search("best"));    // true
        System.out.println(trie.search("abc"));     // false

        System.out.println();
        trie.delete("apple");
        System.out.println(trie.search("apple"));   // false
        System.out.println(trie.search("april"));   // true
        System.out.println(trie.search("appl"));    // false
        trie.delete("apple");
    }
}
