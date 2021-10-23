package B01_월_LCA;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

//https://platform.samsungcic.com/#/connect/LCB20210329100061572
//5일차 2/4

//(출력)
//#1 42
//#2 101
//#3 140
public class No_01_거상의꿈_LCA_내코드에재맞춤 {
    static class Node{
        int dest;
        int cost;

        public Node(int dest, int cost){
            this.dest = dest;
            this.cost = cost;
        }
    }

    static final int LGN = 17;
    static int[][] parent;
    static int[] depth;
    static ArrayList<Node>[] adjList;
    static long[] cost;
    static long[] last;
    static int K;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("No_거상의꿈.txt"));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());


        for (int tc = 1; tc <=T ; tc++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int KK = Integer.parseInt(st.nextToken());

            adjList = new ArrayList[N+1];

            for (int i = 0; i <= N ; i++) {
                adjList[i] = new ArrayList<>();
            }

            int temp = 1;
            while(temp<=N){
                temp<<=1;
                K++;
            }

            parent = new int[K][N+1];
            depth = new int[N+1];
            cost = new long[N+1];
            last = new long[N+1];

            for (int i = 1; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());

                adjList[start].add(new Node(end, cost));
                adjList[end].add(new Node(start, cost));
            }

            bfs(1, 1);
            aces_find(N);

            long result = 0;
            long sum_cost = 0;

            int now = 1;

            st = new StringTokenizer(br.readLine());

//            for (int j = 1; j <= N ; j++) {
//                System.out.println("depth :" + depth[j]);
//            }

            for (int i = 0; i < KK; i++) {
                int next = Integer.parseInt(st.nextToken());
//                System.out.println("now next:" + now+ " "+next);

                int lca = lca(now, next);

                long dist = (cost[now] - cost[lca]) + (cost[next] - cost[lca]);
                sum_cost = sum_cost + dist;

                last[next] = sum_cost;
                now = next;
            }

            for (int i = 1; i <= N ; i++) {
                result = result + last[i];
            }

            System.out.println("#"+tc+" "+result);

        }
    }

    static void bfs(int start, int dep){
        ArrayDeque<Node> queue = new ArrayDeque<>();
        queue.add(new Node(start, 0));
        cost[start] = 0;
        depth[start] = dep;

        while(!queue.isEmpty()){
            Node now = queue.poll();


            for (int i = 0; i <adjList[now.dest].size() ; i++) {
                Node next = adjList[now.dest].get(i);

                if(depth[next.dest] == 0){
                    parent[0][next.dest] = now.dest;
                    depth[next.dest] = depth[now.dest] + 1;
                    cost[next.dest] = cost[now.dest] + next.cost;
                    queue.add(next);
                }
            }

//            for(Node next : adjList[now.dest]) {
//                if(depth[next.dest] == 0){
//                    parent[0][next.dest] = now.dest;
////                    System.out.println("depth[now.dest]  " +  depth[now.dest]);
//                    depth[next.dest] = depth[now.dest] + 1;
//                    cost[next.dest] = cost[now.dest] + next.cost;
//                    queue.add(next);
//                }
//            }
        }
    }

    static void aces_find(int N){
        for(int k=1; k< K; k++){
            for(int V=1; V<=N; V++){
                parent[k][V] = parent[k-1][parent[k-1][V]];
            }
        }
    }

    static int lca(int x, int y){
//        System.out.println("x y:" + x+ " "+y);
//        System.out.println("depth[x] depth[y]:" + depth[x]+ " "+depth[y]);
        if(depth[x] > depth[y]){
            int temp = x;
            x = y;
            y = temp;
        }

        for (int i = K-1; i >=0 ; i--) {
            if(depth[y] - depth[x] >= (1<<i)){
                y = parent[i][y];
            }
        }

        if(x==y) return x;

        for (int i = K-1; i >= 0 ; i--) {
            if(parent[i][x] != parent[i][y]) {
                x = parent[i][x];
                y = parent[i][y];
            }
        }

        return parent[0][x];
    }
}
