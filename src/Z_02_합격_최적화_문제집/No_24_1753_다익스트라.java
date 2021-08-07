package Z_02_합격_최적화_문제집;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class No_24_1753_다익스트라 {
    static int V, E, startPoint;
    static ArrayList<Node>[] al;
    static int[] di;
    public static void main(String[] agrs) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("No_1753.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        startPoint = Integer.parseInt(br.readLine());
        al = new ArrayList[V+1];
        di = new int[V+1];
        Arrays.fill(di, Integer.MAX_VALUE);

        for(int i = 0; i<V+1; i++){
            al[i] = new ArrayList<Node>();
        }

        for(int i=0; i<E; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
//            System.out.println(start+"   "+end+"   "+weight);
            al[start].add(new Node(end, weight));
//            al[end].add(new Node(start, weight));
        }

        di[startPoint] = 0;
        dij(startPoint);

        for(int i=1; i<di.length; i++){
            System.out.println(di[i]);
        }

    }

    static void dij(int startPoint){

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.offer(startPoint);

        while(!pq.isEmpty()){
            int que = pq.poll();
            for(int i = 0; i< al[que].size(); i++){
                Node node = al[que].get(i);
                System.out.println(que+"   "+node.end +"    "+ node.weight);

                if(di[node.end] > di[que] + node.weight){
                    di[node.end] = di[que] + node.weight;
                    pq.offer(node.end);
                }
            }
        }

    }

    static class Node implements Comparable<Node>{
        int end;
        int weight;

        Node(int end, int weight){
            this.end = end;
            this.weight = weight;
        }

        public int compareTo(Node o){

//            return this.weight - o.weight;
            return Integer.compare(this.weight, o.weight);
        }
    }

}
