// Practice
// 정수형 nums 배열이 주어졌다.
// 각 원소의 값은 해당 위치에서 오른쪽으로 이동할 수 있는 최대 값이다.
// 첫 번째 위치에서 시작해서 가장 끝까지 이동이 가능한지 판별하는 프로그램을 작성하세요.
// 이동이 가능하면 true, 불가능하면 false 를 반환하세요.

// 입출력 예시
// nums: 2, 3, 0, 1, 4
// 출력: true

// nums: 3, 0, 0, 1, 1
// 출력: true

// nums: 3, 2, 1, 0, 4
// 출력: false

public class Practice1 {
    public static boolean solution(int[] nums) {
        int pos = 0;    // 현재 위치

        for (int i = 0; i < nums.length; i++) {
            // i보다 작으면 i까지 가지 못했다는 뜻.
            // i보다 크면 i를 넘을 수 있다는 뜻.
            if (pos < i) {
                return false;
            } else if (pos >= nums.length - 1) {
                return true;
            }

            // 현재 기준 도달할 수 있는 최대 pos 계산
            // 현재 위치 = pos
            // 현재 위치에서 갈 수 있는 곳 = i + nums[i]
            pos = Math.max(pos, i + nums[i]);
        }
        return false;
    }

    public static void main(String[] args) {
        // Test code
        int[] nums = {2, 3, 0, 1, 4};
        System.out.println(solution(nums));

        nums = new int[]{3, 0, 0, 1, 1};
        System.out.println(solution(nums));

        nums = new int[]{3, 2, 1, 0, 4};
        System.out.println(solution(nums));
    }
}
