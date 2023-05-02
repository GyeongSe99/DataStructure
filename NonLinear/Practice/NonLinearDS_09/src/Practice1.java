// Practice 1
// 자바 기본 PriorityQueue 응용
// 나이 순으로 오름차순 또는 내림차순 출력


import java.util.PriorityQueue;

class Person implements Comparable<Person> {
    String name;
    int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

// Person은 우선순위에 대한 설정이 안되어있기때문에 comparator로 돌릴 수 없음
    @Override
    public int compareTo(Person o) {
        // 1: 변경 안함
        // -1: 변경

        // 새롭게 추가하는 데이터가 더 적은 값일때 변경
        // (적은 값이 더 앞으로, 더 위로 올라감. 오름차순)
        return this.age >= o.age ? 1 : -1;

        // 내림차순
//        return this.age >= o.age ? -1 : 1;
    }
}


public class Practice1 {
    public static void solution(String[] name, int[] age) {
        PriorityQueue<Person> pq = new PriorityQueue<>();

        for (int i = 0; i < name.length; i++) {
            pq.offer(new Person(name[i], age[i]));
        }

        System.out.println("== 실제 출력 순서==");
        while (!pq.isEmpty()) {
            Person p = pq.poll();
            System.out.println(p.name + " " + p.age);
        }
    }

    public static void main(String[] args) {
        String[] name = {"A", "B", "C", "D", "E"};
        int[] age = {30, 20, 45, 62, 35};

        solution(name, age);

        // 두번째 방법은 람다식으로 비교 조건을 넣어준다.
        // compareTo메소드를 오버라이딩 하지 않아도 됨.
        PriorityQueue<Person> pq2 = new PriorityQueue<>((Person p1, Person p2) -> p1.age >= p2.age ? 1 : -1);
    }
}
