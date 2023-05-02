
public class Practice4 {
    // 결과는 10^9 + 7로 나머지 연산을 한 결과로 출력하시오
    final static int mod = (int) 1e9 + 7;

    public static int solution(long n) {
        // 짝수는 5C1, 홀수는 4C1
        return (int) (recursion(5, (n + 1) /2) * recursion(4, n / 2) % mod);
    }

    // 조합 함수. nC1 계산기 x = n / y = nC1을 몇번 곱할지에대한 횟수 = 자릿수
    public static long recursion(long x, long y) {
        if (y == 0) {
            return 1;
        }

        long p = recursion(x, y / 2);
        return p * p * (y % 2 > 0 ? x : 1) % mod;
    }

    public static void main(String[] args) {
        // Test code
        System.out.println(solution(1));
        System.out.println(solution(2));
        System.out.println(solution(3));
        System.out.println(solution(4));
        System.out.println(solution(50));
    }
}
