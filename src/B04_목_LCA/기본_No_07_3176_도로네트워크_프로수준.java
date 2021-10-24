package B04_목_LCA;

//문제
//N개의 도시와 그 도시를 연결하는 N-1개의 도로로 이루어진 도로 네트워크가 있다.
//
//모든 도시의 쌍에는 그 도시를 연결하는 유일한 경로가 있고,
// 각 도로의 길이는 입력으로 주어진다.
//
//총 K개의 도시 쌍이 주어진다.
// 이때, 두 도시를 연결하는 경로 상에서 가장 짧은 도로의 길이와
// 가장 긴 도로의 길이를 구하는 프로그램을 작성하시오.
//
//입력
//첫째 줄에 N이 주어진다. (2 ≤ N ≤ 100,000)
//
//다음 N-1개 줄에는 도로를 나타내는 세 정수 A, B, C가 주어진다.
// A와 B사이에 길이가 C인 도로가 있다는 뜻이다.
// 도로의 길이는 1,000,000보다 작거나 같은 양의 정수이다.
//
//다음 줄에는 K가 주어진다. (1 ≤ K ≤ 100,000)
//
//다음 K개 줄에는 서로 다른 두 자연수 D와 E가 주어진다.
// D와 E를 연결하는 경로에서 가장 짧은 도로의 길이와 가장 긴 도로의 길이를 구해서 출력하면 된다.
//
//출력
//총 K개 줄에 D와 E를 연결하는 경로에서
// 가장 짧은 도로의 길이와 가장 긴 도로의 길이를 출력한다.

//5 -> 도시 개수
//2 3 100 -> A, B, Cost
//4 3 200
//1 5 150
//1 3 50
//3
//2 4 -> 두 도시 사이의 가장 짧은 도로의 길이와 가장 긴 도로의 길이를 구하시오
//3 5
//1 2

//100 200
//50 150
//50 100

//https://www.acmicpc.net/problem/3176
//https://loosie.tistory.com/468  (자세한 해설)

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

// 3176 도로 네트워크
public class 기본_No_07_3176_도로네트워크_프로수준 {

    static int N, K, M, min, max;
    static int[] depth;
    static int[][] parent, minDist, maxDist;
    static ArrayList<edge>[] tree;
    static boolean[] visited;

    static class edge{
        int tartget;
        int cost;

        public edge(int target, int cost){
            this.tartget = target;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("No_3176.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        K=0;
        int temp = 1;
        while(temp<=N){
            temp <<= 1;
            K++;
        }

        depth = new int[N+1];
        parent = new int[K][N+1];
        visited = new boolean[N+1];
        Arrays.fill(visited, false);

        minDist = new int[K][N+1];
        maxDist = new int[K][N+1];

        tree = new ArrayList[N+1];

        for (int i = 1; i <=N ; i++) {
            tree[i] = new ArrayList<edge>();
        }

        int a, b, c;
        for (int i = 1; i <=N -1; i++) {
            st = new StringTokenizer(br.readLine());

            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            tree[a].add(new edge(b, c));
            tree[b].add(new edge(a, c));
        }

//        dfs(1, 1);
        bfs(1, 0);
        fillParent();

        M = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= M ; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            lca(a, b);
            System.out.println(min + " " +max);
        }
    }

    static void bfs(int start, int dep){
        depth[start] = dep;
        visited[start] = true;

        ArrayDeque<Integer> que = new ArrayDeque<>();

        que.add(start);
        while(!que.isEmpty()){
            int now = que.poll();

            for (int i = 0; i < tree[now].size() ; i++) {
                if(depth[tree[now].get(i).tartget] == 0 ){
//                if(visited[tree[now].get(i).tartget] == false ){
//                    System.out.println(visited[tree[now].get(i).tartget]);
                    edge next = (edge) tree[now].get(i);

                    depth[next.tartget] = depth[now] + 1;
                    parent[0][next.tartget] = now;
                    minDist[0][next.tartget] = next.cost;
                    maxDist[0][next.tartget] = next.cost;

                    que.add(next.tartget);
                }

            }


        }
    }

    static void lca(int x, int y){
        if(depth[x] > depth[y]){
            int temp = x;
            x = y;
            y = temp;
        }

        min = Integer.MAX_VALUE;
        max = Integer.MIN_VALUE;

        for (int i = K-1; i >=0 ; i--) {
            if(depth[y] - depth[x] >= 1<<i){
                min = Math.min(min, minDist[i][y]);
                max = Math.max(max, maxDist[i][y]);
                y = parent[i][y];
            }
        }

        if(x == y) return;

        for (int i = K-1; i >=0 ; i--) {
            if(parent[i][x] !=  parent[i][y]){
//                min = Math.min(min, Math.min(minDist[i][x], minDist[i][y]));
//                max = Math.max(max, Math.max(maxDist[i][x], maxDist[i][y]));

                x = parent[i][x];
                y = parent[i][y];
            }
        }

        min = Math.min(min, Math.min(minDist[0][x], minDist[0][y]));
        max = Math.max(max, Math.max(maxDist[0][x], maxDist[0][y]));

        return;
    }

    static void fillParent(){
        for (int i = 1; i <K ; i++) {
            for (int j = 1; j <=N ; j++) {
                parent[i][j] = parent[i-1][parent[i-1][j]];

                minDist[i][j] = Math.min(minDist[i-1][j], minDist[i-1][parent[i-1][j]]);
                maxDist[i][j] = Math.max(maxDist[i-1][j], maxDist[i-1][parent[i-1][j]]);
            }
        }
    }

    static void dfs(int id, int cnt){
        depth[id] = cnt;

        int len = tree[id].size();
        for (int i = 0; i < len; i++) {
            edge next = (edge) tree[id].get(i);

            if(depth[next.tartget] == 0){
                dfs(next.tartget, cnt+1);

                parent[0][next.tartget] = id;
                minDist[0][next.tartget] = next.cost;
                maxDist[0][next.tartget] = next.cost;
            }
        }
        return;
    }

}
