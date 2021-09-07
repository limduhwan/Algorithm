package Z_99_연습;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class No_04_11438 {
    static int N, M, K;
    static ArrayList<Integer>[] tree;
    static boolean[] checked;
    static int[][] parent;
    static int[] depth;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("No_11438.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        checked = new boolean[N+1];
        depth = new int[N+1];

        tree = new ArrayList[N+1];
        for(int i=0; i<=N; i++){
            tree[i] = new ArrayList<>();
        }

        for(int i=1; i<=N-1; i++){
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());

//            System.out.println(v1 +"  "+ v2);
            tree[v1].add(v2);
            tree[v2].add(v1);
        }

        //1. K구하기
        K = 0;
        int temp = 1;
        while(temp<=N){
            temp<<=1;
            K++;
        }

//        System.out.println(K);
        parent = new int[K][N+1];

        //2. dfs
        dfs(1, 0);

        //3. fillparent
        fillparent();

        //4. lca
        M = Integer.parseInt(br.readLine());
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            System.out.println(lca(a, b));
        }
    }

    static int lca(int x, int y){
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

        if(x == y) return x;

        for(int i = K-1; i>=0; i--){
            if(parent[i][x] != parent[i][y]){
                x = parent[i][x];
                y = parent[i][y];
            }
        }

        return parent[0][x];
    }

    static void fillparent() {
        for(int k=1; k<K; k++){
            for(int v=1; v<=N; v++){
                parent[k][v] = parent[k-1][parent[k-1][v]];
            }
        }
    }

    static void dfs(int node, int dep) {
        checked[node] = true;
        depth[node] = dep;

        for(int i=0; i<tree[node].size(); i++) {
            int child = tree[node].get(i);

            if(!checked[child]){
                parent[0][child] = node;
                dfs(child, dep+1);
            }
        }
    }
}
