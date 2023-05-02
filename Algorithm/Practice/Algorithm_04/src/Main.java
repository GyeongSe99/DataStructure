// 알고리즘 - 이진 탐색

public class Main {
    // 반복문 구조
    public static int binarySearch(int arr[], int target) {

        return -1;
    }
    
    // 재귀 호출 구조
    public static int binarySearch2(int[] arr, int target, int left, int right) {
        
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 5, 10, 20, 30, 40, 50, 60};

        System.out.println("Index: " + binarySearch(arr, 30));
        System.out.println();

        System.out.println("Index: " + binarySearch2(arr, 30, 0, arr.length - 1));
    }
}
