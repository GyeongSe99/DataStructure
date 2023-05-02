// Practice2
// 배열을 이용한 기본 데크 직접 구현

// 데크도 스택처럼 front를 비운 상태로 유지하는 방식으로 구현.

class MyDeque2 {
    int[] arr;
    int front = 0;  // 첫번째 원소의 앞(빈공간)의 인덱스.
    int rear = 0;   // 마지막 원소의 인덱스

    // front는 비워져있으므로 실제 사이즈는 (입력받은 사이즈 + 1)
    MyDeque2(int size) {
        this.arr = new int[size + 1];
    }

    // 비어있을땐 rear와 front가 같다.
    public boolean isEmpty() {
        return this.rear == this.front;
    }

    // 전부 채워져있을땐 rear + 1 % 배열의 길이 == front
    // 원형이기 때문에 배열의 길이로 나눈 나머지를 인덱스로 다시 앞쪽으로 돌 수 있음.
    public boolean isFull() {
        return (this.rear + 1) % this.arr.length == this.front;
    }

    // full일땐 더 넣을 수 없으므로 리턴
    // addFirst는 맨 앞에 원소를 넣어주므로 front에 data를 넣어주고
    // front는 -1(앞으로 하나 당김)
    // 음수값이 나올 수 있으므로 길이만큼 더해주고
    // 양수 값에 길이를 더했을 경우를 생각해 길이로 나누어주면 인덱스를 구할 수 있다.
    public void addFirst(int data) {
        if (this.isFull()) {
            System.out.println("Deque is full!");
            return;
        }

        this.arr[front] = data;
        this.front = (this.front - 1 + this.arr.length) % this.arr.length;
    }

    // addLast는 맨 뒤에 값을 넣어주므로
    // rear는 front와 다르게 이미 값이 있는 상태이기때문에
    // 일단 rear를 뒤로 옮겨주고 그 다음 데이터를 넣어준다.
    // addFirst는 -연산을 하기때문에 음수가 나올 수 있었지만
    // addLast는 음수가 나오지 않아서 코드가 다르다.
    public void addLast(int data) {
        if (this.isFull()) {
            System.out.println("Deque is full!");
            return;
        }

        this.rear = (this.rear + 1) % this.arr.length;
        this.arr[this.rear] = data;
    }

    public Integer removeFirst() {
        if (this.isEmpty()) {
            System.out.println("Deque is empty!");
            return null;
        }

        // 뒤로 옮겨준 후 리턴.
        // 값을 따로 지우지 않아도 다른 연산은 front에서 rear만큼 하기때문에
        // 결과에 상관이 없어 따로 구현하지 않음.
        this.front = (this.front + 1) % this.arr.length;
        return this.arr[this.front];
    }

    public Integer removeLast() {
        if (this.isEmpty()) {
            System.out.println("Deque is empty!");
            return null;
        }

        int data = this.arr[this.rear];
        this.rear = (this.rear - 1 + this.arr.length) % this.arr.length;
        return data;
    }

    public void printDeque() {
        int start = (this.front + 1) % this.arr.length;
        int end = (this.rear + 1) % this.arr.length;

        for (int i = start; i != end; i = (i + 1) % this.arr.length) {
            System.out.print(this.arr[i] + " ");
        }
        System.out.println();
    }

}

public class Practice2 {
    public static void main(String[] args) {
        // Test code
        MyDeque2 myDeque = new MyDeque2(5);
        // Front 부분 입력
        myDeque.addFirst(1);
        myDeque.addFirst(2);
        myDeque.addFirst(3);
        myDeque.printDeque();   // 3 2 1

        // Rear 부분 입력
        myDeque.addLast(10);
        myDeque.addLast(20);
        myDeque.addLast(30);    // Deque is full!
        myDeque.printDeque();        // 3 2 1 10 20

        // Front 부분 출력
        System.out.println(myDeque.removeFirst());  // 3
        myDeque.printDeque();   // 2 1 10 20

        // Rear 부분 출력
        System.out.println(myDeque.removeLast());   // 20
        myDeque.printDeque();    // 2 1 10

        System.out.println(myDeque.removeLast());   // 10
        System.out.println(myDeque.removeLast());   // 1
        System.out.println(myDeque.removeLast());   // 2
        System.out.println(myDeque.removeLast());   // Deque is empty!
    }
}
