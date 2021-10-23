package B01_월_LCA;

import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

//LCA

//아래 코드 LCA 비트연산
//for (int k = LGN; k >= 0; k--) {
//        // 1 << i 값은 아래와 같다.
//        // [i, 1 << i] = [17, 131072], [16, 65536], [15, 32768] ... [3, 8], [2, 4], [1, 2], [0, 1]
//        if (move >= (1 << k)) {
//        A = parent[k][A];
//        move -= (1 << k);
//        }
//}

//BFS
//ArrayDeque<Integer> q = new ArrayDeque<>();
public class 문제풀이_Day04_01번_약속_00_LCA_나에게맞게 {

    static final int MAX = 300000 / 2 + 1;
    static final int LGN = 19;
    static int[][] parent;
    static int[] depth;
    static boolean[] visit;
    static ArrayList<Integer>[] adjList;
    static int T, N, D, K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("문제풀이_Day04_01번_약속_00_LCA.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            D = Integer.parseInt(st.nextToken());

            adjList = new ArrayList[N+1];

            for (int i = 1; i <=N ; i++) {
                adjList[i] = new ArrayList<>();
            }

            parent = new int[LGN + 1][N+1];
            depth = new int[N+1];
            visit = new boolean[N+1];

            st = new StringTokenizer(br.readLine());

            for (int i = 1; i <= N ; i++) {
                int C = Integer.parseInt(st.nextToken());

                if(C>0){
                    adjList[C].add(i);
                    adjList[i].add(C);
                }
            }

            bfs(1, 0);

            aces_find();

            int ansDist = MAX;
            int ansCity = 0;

            for (int i = 0; i < D; i++) {
                st = new StringTokenizer(br.readLine());
                int A = Integer.parseInt(st.nextToken());
                int B = Integer.parseInt(st.nextToken());

                if((depth[A] + depth[B]) %2 ==0){
                    int LCA = lca(A, B);

                    int distA = depth[A] - depth[LCA];

                    int distB = depth[B] - depth[LCA];

                    int distMID = (distA + distB) / 2;

                    if(depth[A] == depth[B]){
                        if(ansDist > distMID){
                            ansDist = distMID;
                            ansCity = LCA;
                        }
                    } else {
                        if(depth[A] < depth[B]){
                            A = B;
                        }

                        int move = distMID;

                        for (int k = LGN; k >= 0 ; k--) {
                            if(move >= (1 << k)){
                                A = parent[k][A];
                                move = move - (1<<k);
                            }
                        }

                        if(ansDist > distMID){
                            ansDist = distMID;

                            ansCity = A;
                        }
                    }
                }
            }

            if(ansDist == MAX){
                bw.write("#" + tc + " -1\n");
            } else {
                bw.write("#"+ tc + " " + ansCity + " " + ansDist + "\n");
            }

            bw.flush();
        }
    }

    static void bfs(int start, int dep){
        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.offer(start);

        depth[start] = dep;
        visit[start] = true;

        while(!q.isEmpty()){
            int here = q.poll();

            for(int next : adjList[here]) {
                if(visit[next]){
                    continue;
                }

                visit[next] = true;
                parent[0][next] = here;
                depth[next] = depth[here] + 1;
                q.offer(next);
            }
        }
    }

    static void aces_find(){
        for(int K=1; K<=LGN; K++){
            for(int V=1; V<=N; V++){
                parent[K][V] = parent[K-1][parent[K-1][V]];
            }
        }
    }

    static int lca(int x, int y){
        if(depth[x] > depth[y]) {
            int temp = x;
            x = y;
            y = temp;
        }

        for(int i = LGN; i>=0; i--){
            if(depth[y] - depth[x] >= (1<<i)){
                y = parent[i][y];
            }
        }

        if(x == y){
            return x;
        }

        for(int i = LGN; i >=0; i--){
            if(parent[i][x] != parent[i][y]){
                x = parent[i][x];
                y = parent[i][y];
            }
        }

        return parent[0][x];
    }
}