package A01_Grammar;

import java.util.ArrayDeque;
import java.util.Deque;

//대기열에서 사용될 때는 LinkedList보다 빠를 수 있습니다
//https://crazykim2.tistory.com/581 [잡다한 프로그래밍]
public class ArrayDeque_ {
    public static void main(String[] args) {
//        Deque<String> deque = new ArrayDeque<String>(); // Deque 선언
        ArrayDeque<String> deque = new ArrayDeque<>();
        // 값 추가
        deque.addFirst("Hello");
        deque.offerFirst("Hello");
        deque.addLast("World");
        deque.offerLast("World");
        deque.add("Hello");

        System.out.print(deque);
        // 결과 출력
    }
}
