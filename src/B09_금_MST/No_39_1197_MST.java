package B09_금_MST;

//문제
//그래프가 주어졌을 때, 그 그래프의 최소 스패닝 트리를 구하는 프로그램을 작성하시오.
//최소 스패닝 트리는, 주어진 그래프의 모든 정점들을 연결하는 부분 그래프 중에서
// 그 가중치의 합이 최소인 트리를 말한다.
//
//입력
//첫째 줄에 정점의 개수 V(1 ≤ V ≤ 10,000)와 간선의 개수 E(1 ≤ E ≤ 100,000)가 주어진다.
//다음 E개의 줄에는 각 간선에 대한 정보를 나타내는 세 정수 A, B, C가 주어진다.
//이는 A번 정점과 B번 정점이 가중치 C인 간선으로 연결되어 있다는 의미이다.
//C는 음수일 수도 있으며, 절댓값이 1,000,000을 넘지 않는다.
//
//그래프의 정점은 1번부터 V번까지 번호가 매겨져 있고, 임의의 두 정점 사이에 경로가 있다.
//최소 스패닝 트리의 가중치가 -2,147,483,648보다 크거나 같고, 2,147,483,647보다 작거나 같은 데이터만
//입력으로 주어진다.
//
//출력
//첫째 줄에 최소 스패닝 트리의 가중치를 출력한다.

import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//https://jellyinghead.tistory.com/86
//https://www.acmicpc.net/problem/1197
public class No_39_1197_MST {
    static int V, E;
    static int[] parent;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("No_1197.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        parent = new int[V+1];

        for(int i=1; i<=V; i++){
            parent[i] = i;
        }

        PriorityQueue<Edge> pq = new PriorityQueue<Edge>();

        for(int i=0; i<E; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            pq.offer(new Edge(a, b, w));
        }

        int weight = 0;
        while(!pq.isEmpty()){
            Edge eg = pq.poll();

            if(find(eg.V1) != find(eg.V2)){
                union(eg.V1, eg.V2);
                weight = weight + eg.weight;
            }
        }

        System.out.println(weight);
    }

    static class Edge implements Comparable<Edge>{
        int V1, V2, weight;

        Edge(int V1, int V2, int weight){
            this.V1 = V1;
            this.V2 = V2;
            this.weight = weight;
        }

        public int compareTo(Edge o){
            return this.weight - o.weight;
        }
    }

    static int find(int x){
        if(parent[x] == x){
            return x;
        }else{
            return parent[x] = find(parent[x]);
        }
    }

    static void union(int x, int y){
        x = find(x);
        y = find(y);

        if(x != y){
            if(x <y){
                parent[y] = x;
            }else{
                parent[x] = y;
            }
        }
    }

    static boolean isSameParent(int x, int y){
        if(find(x) == find(y)){
            return true;
        }else{
            return false;
        }

    }
}
