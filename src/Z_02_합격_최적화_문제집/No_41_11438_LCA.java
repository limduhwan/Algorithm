package Z_02_합격_최적화_문제집;

//문제
//N(2 ≤ N ≤ 100,000)개의 정점으로 이루어진 트리가 주어진다.
//트리의 각 정점은 1번부터 N번까지 번호가 매겨져 있으며, 루트는 1번이다.
//
//두 노드의 쌍 M(1 ≤ M ≤ 100,000)개가 주어졌을 때,
//두 노드의 가장 가까운 공통 조상이 몇 번인지 출력한다.
//
//입력
//첫째 줄에 노드의 개수 N이 주어지고,
//다음 N-1개 줄에는 트리 상에서 연결된 두 정점이 주어진다.
//그 다음 줄에는 가장 가까운 공통 조상을 알고싶은 쌍의 개수 M이 주어지고,
//다음 M개 줄에는 정점 쌍이 주어진다.
//
//출력
//M개의 줄에 차례대로 입력받은 두 정점의 가장 가까운 공통 조상을 출력한다.

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

//https://subbak2.tistory.com/60
//https://www.acmicpc.net/problem/11438
public class No_41_11438_LCA {
    static int N, K, M;
    static int[] depth;
    static int[][] parent;
    static ArrayList<Integer>[] tree;
    static boolean[] checked;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("No_11438.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        K = 0;
        int temp = 1;
        while(temp<=N){
            temp<<=1;
            K++;
        }

        depth = new int[N+1];
        parent = new int[K][N+1];
        tree = new ArrayList[N+1];
        checked = new boolean[N+1];

        for(int i =0; i<=N; i++){
            tree[i] = new ArrayList();
        }

        for(int i=1; i<=N-1; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            //양방향 왜????
            tree[a].add(b);
            tree[b].add(a);
        }

        //***depth가 1부터 시작!
        dfs(1, 0);
        fillparent();

        M = Integer.parseInt(br.readLine());
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            System.out.println(lca(a, b));
        }

    }

    static int lca(int x, int y){
//        1. 깊이 있는 걸 y로 바꾸기
        if(depth[x] > depth[y]){
            int tmp = x;
            x = y;
            y = tmp;
        }
//        2. y를 끌어올리기
        for(int i = K-1; i>=0; i--){
            if(depth[y]-depth[x] >= (1<<i)){
                y = parent[i][y];
            }
        }

//        3. x == y인지 체크
        if(x == y) return x;

//        4. 내리기
        for(int i= K-1; i>=0; i--){
            if(parent[i][x] != parent[i][y]){
                x = parent[i][x];
                y = parent[i][y];
            }
        }

        return parent[0][x];
    }

    static void fillparent(){
        for(int k=1; k<K; k++){
            for(int v=1; v<=N; v++){
                parent[k][v] = parent[k-1][parent[k-1][v]];
            }
        }
    }

    static void dfs(int node, int cnt){
        checked[node] = true;
        depth[node] = cnt;

        for(int i = 0; i<tree[node].size(); i++){
            int child = tree[node].get(i);
            if(!checked[child]) {
                dfs(child, cnt + 1);
                //표의 0번째 로우를 채운다.
                parent[0][child] = node;
            }
        }
    }

//    static void dfs(int node, int cnt){
//        depth[node] = cnt;
//
//        for(int i = 0; i<tree[node].size(); i++){
//            int child = tree[node].get(i);
//            if(depth[child] == 0) {
//                dfs(child, cnt + 1);
//                //표의 0번째 로우를 채운다.
//                parent[0][child] = node;
//            }
//        }
//    }
}
