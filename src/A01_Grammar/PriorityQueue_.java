package A01_Grammar;

import java.util.Collections;
import java.util.PriorityQueue;

//https://coding-factory.tistory.com/603
public class PriorityQueue_ {
    public static void main(String[] args) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.offer(1);
        pq.offer(2);
        pq.offer(3);

        while(!pq.isEmpty()){
            System.out.print(pq.poll()+" ");
        }
        System.out.println();

        PriorityQueue<Integer> pq1 = new PriorityQueue<>();
        pq1.offer(3);
        pq1.offer(1);
        pq1.offer(2);

        while(!pq1.isEmpty()){
            System.out.print(pq1.poll()+" ");
        }
        System.out.println();

        PriorityQueue<Integer> pq2 = new PriorityQueue<>(Collections.reverseOrder());
        pq2.offer(3);
        pq2.offer(1);
        pq2.offer(2);

        while(!pq2.isEmpty()){
            System.out.print(pq2.poll()+" ");
        }


    }
}
