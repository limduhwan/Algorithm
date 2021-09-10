package B01_월_LCA;

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
import java.util.StringTokenizer;

//https://subbak2.tistory.com/60
//https://www.acmicpc.net/problem/11438
public class 기본_No_05_11438_LCA_01번_반복중 {
    static int N, M, K;
//    static ArrayList<Integer>[] tree;
    static int[][] tree;
    static boolean[] isVisited;
    static int[] depth;
    static int[][] parent;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("No_11438.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        tree = new int[N+1][N+1];
        isVisited = new boolean[N+1];
        depth = new int[N+1];

        for (int i = 0; i <N-1 ; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

//            System.out.println(a+" "+b);
            tree[a][b] = 1;
            tree[b][a] = 1;
        }

        //        k d f l
        int temp = 1;
        while(temp<N){
            temp <<= 1;
            K++;
        }

        //       * K , N+1
        // *parent 배열이 선언되는 위치도 K를 구한 다음
        parent = new int[K][N+1];

        dfs(1, 0);

//        for (int i = 0; i <=N ; i++) {
//            System.out.println(depth[i]);
//        }

        fillparent();

        M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
//            System.out.println(a+" "+b);
            System.out.println(a+" "+b +"   "+ lca(a, b));
        }
    }

    static int lca(int x, int y){
//        x, y 순서바꾸기
//        System.out.println("before "+x +"  "+ y +" "+ depth[x] +" "+ depth[y]);
        if(depth[x] > depth[y]){
            int temp = 0;
            temp = x;
            x = y;
            y = temp;
        }
//        System.out.println("after "+x +"  "+ y +" "+ depth[x] +" "+ depth[y]);

//        y 끌어올리기
        for (int i = K-1; i >= 0 ; i--) {
            if(depth[y] - depth[x] >= (1<<i)){
                y = parent[i][y];
            }
        }

//        x = y 이면 x 리턴하기
        if(x == y) return x;

//        위에서부터 내려오기
        for (int i = K-1; i >= 0 ; i--) {
            if(parent[i][x] != parent[i][y]){
                x = parent[i][x];
                y = parent[i][y];
            }
        }

//        바로 위 리턴하기
        return parent[0][x];
    }

    static void fillparent(){
        for(int k = 1; k<K; k++){
            for(int v=1; v<=N; v++){
                parent[k][v] = parent[k-1][parent[k-1][v]];
            }
        }
    }

    static void dfs(int start, int dep){
        isVisited[start] = true;
        depth[start] = dep;

        for (int i = 1; i <=N ; i++) {
            if(tree[start][i] != 0 && isVisited[i] == false){
//                System.out.println("hoi "+start +"  "+ i + " " + dep);
                parent[0][i] = start;
//                *dep+1로 해야함.. 왜???
                dfs(i, dep+1);
            }
        }
    }
}

