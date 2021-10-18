package B01_월_LCA;

import java.io.*;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

//문제
//각 노드가 자식을 최대 K개 가질 수 있는 트리를 K진 트리라고 한다.
// 총 N개의 노드로 이루어져 있는 K진 트리가 주어진다.
//
//트리는 "적은 에너지" 방법을 이용해서 만든다.
// "적은 에너지" 방법이란, 이전 깊이를 모두 채운 경우에만,
// 새로운 깊이를 만드는 것이고,
// 이 새로운 깊이의 노드는 가장 왼쪽부터 차례대로 추가 한다.
//
//아래 그림은 노드 9개로 이루어져 있는 3진 트리이다.
//
//노드의 개수 N과 K가 주어졌을 때,
// 두 노드 x와 y 사이의 거리를 구하는 프로그램을 작성하시오.
//
//입력
//첫째 줄에 N (1 ≤ N ≤ 1015)과 K (1 ≤ K ≤ 1 000),
// 그리고 거리를 구해야 하는 노드 쌍의 개수 Q (1 ≤ Q ≤ 100 000)가 주어진다.
//
//다음 Q개 줄에는 거리를 구해야 하는 두 노드 x와 y가 주어진다.
// (1 ≤ x, y ≤ N, x ≠ y)
//
//출력
//총 Q개의 줄을 출력한다. 각 줄에는 입력으로 주어진 두 노드 사이의 거리를 출력한다.

//9 3 3
//8 9
//5 7
//8 4
//
//2
//2
//3
public class 기본_No_08_11812_K진트리_혼자풀었다하하 {
    static int N, Ksu, Q, K;
    static int lcaNode;
    static int[] depth;
    static boolean[] isVisit;
    static int[][] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("No_11812.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Ksu = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        isVisit = new boolean[N+1];
        depth = new int[N+1];
        parent = new int[N+1][N+1];

        //K
        int temp=1;

        while(K<=N){
            temp<<=1;
            K++;
        }

        //B
        bfs(1, 0);

//        for (int i = 1; i <= N ; i++) {
//            System.out.println(i +" " +parent[0][i]);
//        }


        //F
        fillParent();

        //L

        for (int i = 0; i <Q ; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            lcaNode = lca(x, y);

//            System.out.println(x +" "+ y +" "+ lcaNode);

            int result  = (depth[x] - depth[lcaNode]) + (depth[y] - depth[lcaNode]);

            System.out.println(result);
        }



    }

    static int lca(int x, int y){
        //depth 맞추주고
        if(depth[y] < depth[x]){
            int temp = x;
            x = y;
            y = temp;
        }

        //y끌올
        for (int i = K-1; i>=0 ; i--) {
            if(depth[y]-depth[x] >= (1<<i)){
                y = parent[i][y];
            }
        }

        //같으면 return x
        if(x == y) return x;

        //위에서부터 내려오면서
        for (int i = K-1; i >=0 ; i--) {
            if(parent[i][x] != parent[i][y]){
                x = parent[i][x];
                y = parent[i][y];
            }
        }

        return parent[0][x];
    }


    static void fillParent(){
        for (int k = 1; k < K ; k++) {
            for (int v = 1; v <=N ; v++) {
                parent[k][v] = parent[k-1][parent[k-1][v]];
            }
        }
    }

    static void bfs(int start, int dep){
        ArrayDeque<Integer> que = new ArrayDeque<>();
        depth[start] = 0;
        isVisit[start] = true;
        que.add(start);

        int cnt = 0;
        while(!que.isEmpty()){
            int now = que.poll();
            cnt = 0;

            for (int i = 2; i <=N ; i++) {
//                System.out.println("cnt " + cnt + " Ksu "+ Ksu);
                if(cnt == Ksu){
                    break;
                }

                if(cnt != Ksu && isVisit[i] == false){
                    depth[i] = depth[now] + 1;
                    isVisit[i] = true;
                    parent[0][i] = now;
                    que.add(i);
                    cnt++;
                }


            }
        }
    }


}
