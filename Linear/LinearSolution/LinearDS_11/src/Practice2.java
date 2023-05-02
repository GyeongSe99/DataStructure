// Practice2
// Palindrome 찾기
// Palindrome 이면 true, 아니면 false 를 반환하세요.
// Palindrome: 앞으로 읽어도 거꾸로 읽어도 같은 문자열

// 입출력 예시)
// 입력: a
// 결과: true

// 입력: madam
// 결과: true

// 입력: abab
// 결과: false

import java.util.ArrayDeque;
import java.util.Deque;

public class Practice2 {
    public static boolean checkPalindrome(String str) {
        Deque deque = new ArrayDeque();
        boolean isFront = true;
        boolean isPalindrome = true;

        for (String s: str.split("")) {
            deque.addFirst(s);
        }

        // poll은 데크가 비어있을때 null반환
        // 앞에서 하나빼서 저장, 뒤에서 하나빼고 저장
        // equals를 이용하여 같으면 true, 다르면 false
        while (!deque.isEmpty()) {
            String s1 = (String)deque.pollFirst();
            String s2 = (String)deque.pollLast();

            // s1이 null이 아니고, s2가 null이 아닌데 s1과 s2가 같지 않은 경우
            // 홀수인 경우에도 잘 돌다가 마지막에 s1이나 s2가 null인 경우가 되면 어떤 글자가 오더라도 상관없음.
            if (s1 != null && s2 != null && !s1.equals(s2)) {
                isPalindrome = false;
                break;
            }
        }

        return isPalindrome;
    }

    public static void main(String[] args) {
        // Test code
        System.out.println(checkPalindrome("a"));       // true
        System.out.println(checkPalindrome("aba"));     // true
        System.out.println(checkPalindrome("abba"));    // true
        System.out.println(checkPalindrome("abab"));    // false
        System.out.println(checkPalindrome("abcba"));   // true
        System.out.println(checkPalindrome("abccba"));  // true
        System.out.println(checkPalindrome("madam"));  // true
    }
}
