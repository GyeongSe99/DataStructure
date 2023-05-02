// Practice2
// 문자열 배열 dictionary 와 문자열 sentence 가 주어졌을 때
// sentence 내의 단어 중 dictionary 의 단어로 시작하는 경우
// 해당 단어로 변경하여 출력하는 프로그램을 작성하세요.

// 입출력 예시
// 입력 dictionary: "cat", "bat", "rat"
// 입력 sentence = "the cattle was rattled by the battery"
// 출력: "the cat was rat by the bat"

// 입력 dictionary: "a", "b", "c"
// 입력 sentence = "apple banana carrot water"
// 출력: "a b c water"


public class Practice2 {
    public static void solution(String[] dictionary, String sentence) {
        Trie trie = new Trie();
        for (String str: dictionary) {
            trie.insert(str);
        }

        StringBuffer sbResult = new StringBuffer();
        for (String word : sentence.split(" ")) {
            Node cur = trie.root;
            StringBuffer sbCur = new StringBuffer();

            for (char c : word.toCharArray()) {
                sbCur.append(c);

                // 자식이 null이 아니라면 자식이 끝인지 아닌지 확인
                if (cur.child.get(c) != null) {
                    // 끝이라면 break
                    if (cur.child.get(c).isTerminal) {
                        break;
                    }
                    // 끝이 아니라면
                    cur = cur.child.get(c);
                } else { // 자식이 null이라면 단어가 없다는 뜻이므로 그냥 원래 단어 넣어준뒤 break
                    sbCur = new StringBuffer(word);
                    break;
                }
            }

            sbResult.append(sbCur);
            sbResult.append(" ");
        }
        System.out.println(sbResult);
    }


    public static void main(String[] args) {
        // Test code
        String[] dictionary = {"cat", "bat", "rat"};
        String sentence = "the cattle was rattled by the battery";
        solution(dictionary, sentence);

        dictionary = new String[]{"a", "b", "c"};
        sentence = "apple banana carrot water";
        solution(dictionary, sentence);
    }
}
