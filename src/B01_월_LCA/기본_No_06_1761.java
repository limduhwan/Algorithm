package B01_월_LCA;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

//https://www.acmicpc.net/problem/1761
//https://velog.io/@imfksh/%EB%B0%B1%EC%A4%80-1470-Java
public class 기본_No_06_1761 {

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
