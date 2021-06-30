package B01_최소공통조상;

import java.io.*;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

//https://www.acmicpc.net/problem/11438
//https://blog.naver.com/kks227/220820773477
//트리에서 너무 지나칠 정도로 최단경로를 빨리 찾기를 원한다면 LCA를 먼저 생각해 봐야 합니다.
//https://velog.io/@imfksh/%EB%B0%B1%EC%A4%80-11438-Java
//https://www.crocus.co.kr/660
//https://blog.naver.com/kks227/220793361738
// 작은 j부터 배열을 채워가면서 전체 배열을 채울 수 있다.
// i에서 2^(j+1)번 이동한 후의 정점은 i에서 2^j번*2번 이동하는 것
//    for(int j = 1; j < MAX_D; ++j)
//        for(int i = 1; i <= M; ++i)
//        next[i][j] = next[ next[i][j-1] ][j-1];
//https://pangtrue.tistory.com/262
public class No_05_11438_Internet {
    static int N, K, M;
    static ArrayList<ArrayList<Integer>> tree;
    static int[] depth;
    static int[][] parent;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("No_11438.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

//        System.out.println(N);

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

//        System.out.println(K);

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
//                부모의 부모값을 저장해 둔다.
                parent[j][i] = parent[ parent[j][i-1] ][i-1];
            }
        }
    }

    static int lca(int a, int b){
        if(depth[a] < depth[b]){
            int temp = a;
            a = b;
            b = temp;
        }

        for(int i = K-1; i>=0; i--){
            if(Math.pow(2, i) <= depth[a] - depth[b]){
                a = parent[a][i];
            }
        }

        if(a==b) return a;

        for(int i = K-1; i>=0; i--){
            if(parent[a][i] != parent[b][i]){
                a = parent[a][i];
                b = parent[b][i];
            }
        }

        return parent[a][0];
    }
}
