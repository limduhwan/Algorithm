package B04_목_최단거리_다익스트라;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 문제풀이_Day01_01번_위대한항로_00_다익스트라_완성 {

    static class Node implements Comparable<Node>{
        int dest;
        int cost;

        public Node(int dest, int cost) {
            this.dest = dest;
            this.cost = cost;
        }

        public int compareTo(Node o) {
            return Integer.compare(this.cost, o.cost);
        }
    }

    static ArrayList<Node>[] adjList;
    static int[] food, dist;
    static int N, M, K, T;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("No_위대한항로.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());

        for(int tc = 1; tc<=T; tc++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            adjList = new ArrayList[N+1];

            for(int i=1; i<=N; i++) {
                adjList[i] = new ArrayList<>();
            }

            for(int i=0; i<M; i++) {
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());

                adjList[end].add(new Node(start, cost));
            }

            food = new int[N+1];
            dist = new int[N+1];

            for(int i=0; i<K; i++) {
                st = new StringTokenizer(br.readLine());
                int idx = Integer.parseInt(st.nextToken());
                int value = Integer.parseInt(st.nextToken());

                food[idx] = value;
            }

            dijkstra(N, 1);

            bw.write("#" +  tc + " "+dist[1] + "\n");
            bw.flush();
        }



    }

    static void dijkstra(int start, int dest) {
        PriorityQueue<Node> PQ = new PriorityQueue<>();

        Arrays.fill(dist, Integer.MAX_VALUE);

        PQ.add(new Node(start, 0));

        while(!PQ.isEmpty()) {
            Node now = PQ.poll();

            if(now.cost > dist[now.dest]) {
                continue;
            }

            for(Node next: adjList[now.dest]) {
                if(dist[next.dest] > Math.max(now.cost+next.cost - food[next.dest], 0)) {
                    dist[next.dest] = Math.max(now.cost + next.cost - food[next.dest], 0);
                    PQ.add(new Node(next.dest, dist[next.dest]));
                }
            }
        }

    }

}
