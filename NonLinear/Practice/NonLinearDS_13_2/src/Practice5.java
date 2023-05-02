import java.util.ArrayList;
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
    // 대문자 기록용
    ArrayList<Character> capitals;

    public Trie() {
        this.root = new Node();
        capitals = new ArrayList<>();
    }

    public void insert(String str) {
        Node cur = this.root;

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);

            // 대문자인 경우 넣어주기
            if (c < 'a') {
                capitals.add(c);
            }

            cur.child.putIfAbsent(c, new Node());
            cur = cur.child.get(c);

            if (i == str.length() - 1) {
                cur.isTerminal = true;
                return;
            }
        }
    }

    public boolean search(String str) {
        Node cur = this.root;
        // 대문자 목록을 복사해서 해당 대문자가 나왔을때 지워보기.
        ArrayList<Character> cap = new ArrayList<>(capitals);

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);

            if (cur.child.containsKey(c)) {
                // 해당 문자 포함한 경우 대문자에서 삭제 후 다음으로
                // 만약 해당 알파벳이 또 들어온다면 이미 삭제된 대문자이므로 삭제가 안되고 false.
                cap.remove(new Character(c));
                cur = cur.child.get(c);
            } else {
                // 트라이에 없는데 대문자인 경우 바로 false
                if (c < 'a') {
                    return false;
                } else {
                    // 그 외 패턴에는 없는 문자인 경우 우선 continue
                    continue;
                }
            }

            if (i == str.length() - 1) {
                if (!cur.isTerminal) {
                    return false;
                }
            }
        }
        return cap.size() == 0;
    }
}

public class Practice5 {
    public static ArrayList<Boolean> solution(String[] queries, String pattern) {
        Trie trie = new Trie();
        trie.insert(pattern);

        ArrayList<Boolean> result = new ArrayList<>();

        for (String query : queries) {
            result.add(trie.search(query));
        }
        return null;
    }

    public static void main(String[] args) {
        // Test code
        String[] queries = {"FooBar", "FooBarTest", "FootBall", "FrameBuffer", "ForceFeedBack"};
        String pattern = "FB";
        System.out.println(solution(queries, pattern));

        pattern = "FoBa";
        System.out.println(solution(queries, pattern));

        pattern = "FoBaT";
        System.out.println(solution(queries, pattern));
    }
}
