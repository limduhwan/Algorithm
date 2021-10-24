package B01_월_최단거리_다익스트라;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//문제
//방향그래프가 주어지면 주어진 시작점에서 다른 모든 정점으로의 최단 경로를 구하는 프로그램을 작성하시오.
// 단, 모든 간선의 가중치는 10 이하의 자연수이다.
//
//입력
//첫째 줄에 정점의 개수 V와 간선의 개수 E가 주어진다. (1≤V≤20,000, 1≤E≤300,000)
// 모든 정점에는 1부터 V까지 번호가 매겨져 있다고 가정한다.
// 둘째 줄에는 시작 정점의 번호 K(1≤K≤V)가 주어진다.
// 셋째 줄부터 E개의 줄에 걸쳐 각 간선을 나타내는 세 개의 정수 (u, v, w)가 순서대로 주어진다.
// 이는 u에서 v로 가는 가중치 w인 간선이 존재한다는 뜻이다.
// u와 v는 서로 다르며 w는 10 이하의 자연수이다.
// 서로 다른 두 정점 사이에 여러 개의 간선이 존재할 수도 있음에 유의한다.
//
//출력
//첫째 줄부터 V개의 줄에 걸쳐, i번째 줄에 i번 정점으로의 최단 경로의 경로값을 출력한다.
// 시작점 자신은 0으로 출력하고, 경로가 존재하지 않는 경우에는 INF를 출력하면 된다.

//https://www.acmicpc.net/problem/1753
public class No_02_1753_01번_반복중_Node {
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
