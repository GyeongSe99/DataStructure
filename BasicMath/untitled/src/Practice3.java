
public class Practice3 {
    public static String solution(String equation) {
        String[] parts = equation.split("=");
        int[] leftSide = evaluate(parts[0]);
        int[] rightSide = evaluate(parts[1]);

        // 양쪽이 같으면 해가 무한히 많으므로 "Infinite solution
        // x의 계수가 같고 상수가 다르면 해가 없으므로 "No solution"
        if (leftSide[0] == rightSide[0] && leftSide[1] == rightSide[1]) {
            return "Infinite solutions";
        } else if (leftSide[0] == rightSide[0]) {
            return "No solution";
        } else {
            return "x=" + (rightSide[1] - leftSide[1]) / (leftSide[0] - rightSide[0]);
        }
    }

    public static int[] evaluate(String str) {
        int[] result = new int[2];

        boolean isMinus = false;
        int idx = 0;
        while (idx != str.length()) {
            char c = str.charAt(idx++);

            if (c == '+') {
                continue;
            }

            if (c == '-') {
                isMinus = true;
                continue;
            }

            if (c == 'x') {
                result[0] += isMinus ? -1 : 1;
            } else {    // 숫자가 들어왔는데 그 다음이 'x'인 경우는 x의 계수. x의 계수는 result[0]에 넣는다.
                if (idx < str.length() && str.charAt(idx) == 'x') {
                    result[0] += isMinus ? -(c - '0') : (c - '0');
                } else {
                    result[1] += isMinus ? -(c - '0') : (c - '0');
                }
            }

            isMinus = false;
        }

        return result;
    }

    // # 2 정규표현식 사용
    public static int[] evaluate2(String str) {
        int[] result = new int[2];

        for (String s : str.split("(?=[+-])")) {
            // 계수가 1인 경우
            if (s.equals("+x") || s.equals("x")) {
                result[0]++;
            }
            // 계수가 -1인 경우
            else if (s.equals("-x")) {
                result[0]--;
            }
            // 계수가 써있는 x인 경우
            else if (s.contains("x")) {
                result[0] += Integer.parseInt(s.substring(0, s.length() - 1));
            }
            // 상수인 경우
            else {
                result[1] += Integer.parseInt(s);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        // Test code
        String equation = "x+5-3+x=6+x-2";
        System.out.println(solution(equation));

        equation = "x=x";
        System.out.println(solution(equation));

        equation = "2x=x";
        System.out.println(solution(equation));
    }
}
