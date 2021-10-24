package B04_목_LCA;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

//정점들의 거리
//시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
//2 초	128 MB	8105	3306	2069	39.016%

//문제
//N(2 ≤ N ≤ 40,000)개의 정점으로 이루어진 트리가 주어지고
// M(1 ≤ M ≤ 10,000)개의 두 노드 쌍을 입력받을 때 두 노드 사이의 거리를 출력하라.
//
//입력
//첫째 줄에 노드의 개수 N이 입력되고 다음 N-1개의 줄에 트리 상에 연결된 두 점과 거리를 입력받는다.
// 그 다음 줄에 M이 주어지고, 다음 M개의 줄에 거리를 알고 싶은 노드 쌍이 한 줄에 한 쌍씩 입력된다.
// 두 점 사이의 거리는 10,000보다 작거나 같은 자연수이다.
//
//정점은 1번부터 N번까지 번호가 매겨져 있다.
//
//출력
//M개의 줄에 차례대로 입력받은 두 노드 사이의 거리를 출력한다.

//7
//1 6 13
//6 3 9
//3 5 7
//4 1 3
//2 4 20
//4 7 2
//3
//1 6
//1 4
//2 6

//13
//3
//36

//https://www.acmicpc.net/problem/1761
//https://velog.io/@imfksh/%EB%B0%B1%EC%A4%80-1470-Java

public class 기본_No_06_1761_01번_반복중 {

    static int N;
    static ArrayList<Integer> [] list;
    static ArrayList<Integer> [] dlist;
    static int[] depth;
    static int[][] parent;
    static int[] distance;
    static boolean[] visited;
    static int K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("No_1761.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

//        System.out.println(N);

        list = new ArrayList[N+1];
        dlist = new ArrayList[N+1];

        for(int i=1; i<=N; i++){
            list[i] = new ArrayList<Integer>();
            dlist[i] = new ArrayList<Integer>();
        }

        for(int i=1; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            list[a].add(b);
            list[b].add(a);
            dlist[a].add(c);
            dlist[b].add(c);
        }

        depth = new int[N+1];
        distance = new int[N+1];
        visited = new boolean[N+1];

        K = 0;
        int temp = 1;
        while(temp<=N){
            temp<<=1;
            K++;
        }

        parent = new int[K][N+1];

        dfs(1, 0, 0 );

        print();

        fillParent();

        int Q = Integer.parseInt(br.readLine());

        for(int i=1; i<=Q; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            long answer = getLCA(a, b);
            System.out.println(answer);
        }
    }

    static long getLCA(int x, int y){
        if(depth[x] > depth[y]){
            int temp = x;
            x = y;
            y = temp;
        }

        for(int i = K-1; i>=0; i--){
            if(depth[y] - depth[x] >= (1<<i)){
                y = parent[i][y];
            }
        }

        if(x == y){
            return distance[x] - distance[y];
        }

        for(int i = K-1; i>=0; i--){
            if(parent[i][x] != parent[i][y]){
                x = parent[i][x];
                y = parent[i][y];
            }
        }

        int lca = parent[0][x];

        long answer = distance[x]+distance[y]-(distance[lca]*2);

        return answer;
    }

    static void fillParent(){
        for(int k=1; k<K; k++){
            for(int v=1; v<=N; v++){
                parent[k][v] = parent[k-1][parent[k-1][v]];
            }
        }
    }

    static void dfs(int node, int dep, int dis){
        visited[node] = true;
        depth[node] = dep;
        distance[node] = dis;

        for(int i=0; i<list[node].size(); i++){
            if(!visited[list[node].get(i)]){
                parent[0][list[node].get(i)] = node;
                dfs(list[node].get(i), dep+1, dis+dlist[node].get(i));
            }
        }
    }

    private static void print() {
        // TODO Auto-generated method stub
        for (int i = 1; i<=N; i++) {
            System.out.print(depth[i]+ " ");
        }System.out.println();
        for (int i = 1; i<=N; i++) {
            System.out.print(parent[0][i]+ " ");
        }System.out.println();
        for (int i = 1; i<=N; i++) {
            System.out.print(distance[i]+ " ");
        }System.out.println();
    }
}
