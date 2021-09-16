package B04_목_최단거리_다익스트라;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//https://www.acmicpc.net/problem/1753
public class 기본_No_24_1753_01번_반복중_Node {
    static int V, E, startPoint;
    static ArrayList<Node>[] al;
    static int[] dist;
    public static void main(String[] agrs) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("No_1753.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        startPoint = Integer.parseInt(br.readLine());

        al = new ArrayList[V+1];
        dist = new int[V+1];

        for (int i = 0; i <=V ; i++) {
            al[i] = new ArrayList<>();
        }

        Arrays.fill(dist, Integer.MAX_VALUE);

        for (int i = 1; i <=E ; i++) {
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            al[start].add(new Node(end, weight));
        }

        dist[startPoint] = 0;
        daik(startPoint);

        for (int i = 1; i <=V; i++) {
            System.out.println(dist[i]);
        }

    }

    static void daik(int startPoint){
        PriorityQueue<Node> pq = new PriorityQueue<>();

        pq.offer(new Node(startPoint, 0));

        while(!pq.isEmpty()){
            Node hereNode = pq.poll();

            for (int i = 0; i <al[hereNode.end].size() ; i++) {
                Node nextNode = al[hereNode.end].get(i);

                if(dist[nextNode.end] > dist[hereNode.end] + nextNode.weight){
                    dist[nextNode.end] = dist[hereNode.end] + nextNode.weight;
                    pq.offer(new Node(nextNode.end, dist[nextNode.end]));
                }

            }
        }

    }

    static class Node implements Comparable<Node>{
        int end;
        int weight;

        public Node(int end, int weight){
            this.end = end;
            this.weight = weight;
        }

        public int compareTo(Node o){
            return Integer.compare(this.weight, o.weight);
        }
    }



}
