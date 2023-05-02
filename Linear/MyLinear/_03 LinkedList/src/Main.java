// 선형 자료구조 - 연결 리스트
// 단순 연결 리스트 (simple ver.) 기본 구조 구현

// 노드
class Node {
    // 값
    int data;
    // 링크 - 다음노드
    Node next;

    // 생성자
    Node() {}
    Node(int data, Node next) {
        this.data = data;
        this.next = next;
    }
}

// 연결리스트
class LinkedList {
    Node head;

    // 생성자
    LinkedList() {}
    LinkedList(Node node) {
        this.head = node;
    }

    //  연결 리스트 비어있는지 확인
    public boolean isEmpty() {
        if (this.head == null) {
            return true;
        }
        return false;
    }

    //  연결 리스트의 맨 뒤에 데이터 추가
    public void addData(int data) {
        if (this.head == null) {    // 연결리스트에 값이 없으면
            this.head = new Node(data, null);   // head에 새로운 노드를 만들어 할당해줌.
        } else {    // 값이 있다면
            Node cur = this.head;
            // next의 값이 없을때까지(맨끝에 도달할때까지) cur의 값을 옮겨줌.
            while (cur.next != null) {
                cur = cur.next;
            }
            // 맨 뒤까지 간다면 새로운 노드를 만들어서 이전 노드의 next에 할당해주기
            cur.next = new Node(data, null);
        }
    }


    //  연결 리스트의 맨 뒤의 데이터 삭제
    public void removeData() {
        if (this.isEmpty()) {
            System.out.println("List is empty");
            return;
        }

        // 삭제할 값의 이전 링크를 null로 만들어야 삭제가 되므로
        // prev를 만들어 이전의 노드가 따라갈 수 있도록 한다.
        Node cur = this.head;
        Node prev = cur;

        // 맨끝을 삭제할 것이기 때문에 next데이터가 없는 노드까지 이동한다.
        while (cur.next != null) {
            prev = cur;
            cur = cur.next;
        }

        // 이동했는데 만약 그 노드가 head라면 head를 null로 바꾸어주고
        if (cur == this.head) {
            this.head = null;
        }
        // 아니라면 이전의 노드 next를 null로 만들어 연결을 끊어준다.
        else {
            prev.next = null;
        }
    }

    //  연결 리스트에서 데이터 찾기
    public void findData(int data) {
        if (this.isEmpty()) {
            System.out.println("List is Empty!");
            return;
        }

        Node cur = this.head;
        while (cur != null) {
            if (cur.data == data) {
                System.out.println("Data exist!");
                return;
            }
            cur = cur.next;
        }
        System.out.println("Data not found!");
    }


    //  연결 리스트의 모든 데이터 출력
    public void showData() {
        if (this.isEmpty()) {
            System.out.println("List is Empty!");
            return;
        }

        Node cur = this.head;
        while (cur != null) {
            System.out.print(cur.data + " ");
            cur = cur.next;
        }
        System.out.println();
    }

}


public class Main {

    public static void main(String[] args) {

//      Test Code
        LinkedList myList = new LinkedList(new Node(1, null));
        myList.showData();      // 1

        myList.addData(2);
        myList.addData(3);
        myList.addData(4);
        myList.addData(5);
        myList.showData();      // 1 2 3 4 5

        myList.findData(3);     // Data exist!
        myList.findData(100);   // Data not found!

        myList.removeData();
        myList.removeData();
        myList.removeData();
        myList.showData();      // 1 2

        myList.removeData();
        myList.removeData();
        myList.removeData();    // List is empty

    }
}
