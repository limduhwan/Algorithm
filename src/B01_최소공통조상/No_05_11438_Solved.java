package B01_최소공통조상;

import java.io.*;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

//https://www.acmicpc.net/problem/11438
//https://pangtrue.tistory.com/262
public class No_05_11438_Solved {
    static int N, K, M;
    static ArrayList<ArrayList<Integer>> tree;
    static int[] depth;
    static int[][] parent;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("No_11438.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        System.out.println(N);

        tree = new ArrayList<>();

        for(int i = 0; i<N+1; i++){
            tree.add(new ArrayList<>());
        }

        for(int i = 0; i<N-1; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            tree.get(a).add(b);
            tree.get(b).add(a);
        }

        int tmp = 1;
        K = 0;

        while(tmp <= N){
            tmp <<= 1;
            K++;
        }

        System.out.println(K);

        depth = new int[N+1];
        parent = new int[N+1][K];

        dfs(1, 1);
        fillParent();

        M = Integer.parseInt(br.readLine());
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            int lca = lca(a, b);
            bw.write(lca + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    static void dfs(int node, int cnt){
        depth[node] = cnt;

        ArrayList<Integer>  al = tree.get(node);

        for(int i = 0; i<al.size(); i++){
            int a = al.get(i);
            if(depth[a] ==0){
                dfs(a, cnt+1);
                parent[a][0] = node;
            }
        }
    }

    static void fillParent(){
        for(int i = 1; i<K; i++){
            for(int j = 1; j<=N; j++){
                parent[j][i] = parent[ parent[j][i-1] ][i-1];
            }
        }
    }

    static int lca(int a, int b){
        return 0;
    }
}
