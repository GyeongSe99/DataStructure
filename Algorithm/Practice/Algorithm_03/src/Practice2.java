// Practice2
// 문자열 배열 strs 가 주어졌을 때 anagram 으로 묶어서 출력하는 프로그램을 작성하세요.
// anagram 은 철자 순서를 바꾸면 같아지는 문자를 의미한다.
// 예) elvis <-> lives
// anagram 그룹 내에서 출력 순서는 상관 없다.

// 입출력 예시
// 입력: "eat", "tea", "tan", "ate", "nat", "bat"
// 출력: [[eat, tea, ate], [bat], [tan, nat]]


import java.util.ArrayList;

public class Practice2 {
    public static ArrayList<ArrayList<String>> solution(String[] strs) {

        return null;
    }

    public static void sort(char[] arr) {

    }

    public static void main(String[] args) {
        // Test code
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println(solution(strs));

        strs = new String[]{"abc", "bac", "bca", "xyz", "zyx", "aaa"};
        System.out.println(solution(strs));
    }
}
