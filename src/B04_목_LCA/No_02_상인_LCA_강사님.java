package B04_목_LCA;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
김희성 프로(SW역량강화TF)
알고리즘 유형 : LCA (최저 공통 조상)

풀이
문제로 주어진 그래프는 트리이다.
트리에서 A -> B 로 이동할 때 최단경로는 반드시 LCA(A, B) 를 지나며,
LCA(A, B) 가 최단경로 상에서 Root 에 가장 가까운 정점이다.
따라서, 최단거리를 아래와 같이 구할 수 있다.

트리에서 A, B 두 정점의 최단거리는 LCA(A, B) 와 두 정점의 깊이 차이가 된다.
A 에서 LCA 까지 거리 : Depth[A] - Depth[LCA(A, B)]
B 에서 LCA 까지 거리 : Depth[B] - Depth[LCA(A, B)]
A -> B 의 거리 : Depth[A] - Depth[LCA(A, B)] + Depth[B] - Depth[LCA(A, B)]

죠르디는 사랑입니다♥
 */

//(입력)
//1
//5
//3 1
//2 4
//3 5
//1 4
//
//
//
//
//
//(출력)
//#1 10

public class No_02_상인_LCA_강사님 {

    private static final int LGN = 16; // 2^16 = 65,536 < 100,000 < 2^17 = 131,072
    private static int[] depth;
    private static int[][] parent;
    private static ArrayList<Integer>[] adjList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("No_상인.txt"));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken()); // 정점의 수

            depth = new int[N + 1];
            parent = new int[LGN + 1][N + 1];
            adjList = new ArrayList[N + 1];

            for (int i = 1; i <= N; i++) {
                adjList[i] = new ArrayList<>();
            }

            for (int i = 1; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                adjList[from].add(to);
                adjList[to].add(from);
            }

            bfs(1);

            aces_find(N);

            long result = 0;

            for (int i = 1; i < N; i++) {
                int lca = lca(i, i + 1);
                result += depth[i] - depth[lca] + depth[i + 1] - depth[lca];
            }

            System.out.println("#" + tc + " " + result);
        }
    }

    private static void bfs(int start) {
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        queue.addLast(start);
        depth[start] = 1; // 시작 정점의 depth 는 1로 저장

        while (!queue.isEmpty()) {
            int now = queue.pollFirst();

            for (int next : adjList[now]) {
                if (depth[next] == 0) {
                    depth[next] = depth[now] + 1;
                    parent[0][next] = now;
                    queue.addLast(next);
                }
            }
        }
    }

    private static void aces_find(int N) {
        for (int k = 0; k < LGN; k++) {
            for (int i = 1; i <= N; i++) {
                parent[k + 1][i] = parent[k][parent[k][i]];
            }
        }
    }

    private static int lca(int x, int y) {
        if (depth[x] > depth[y]) {
            int tmp = x;
            x = y;
            y = tmp;
        }

        for (int i = LGN; i >= 0; i--) {
            if (depth[y] - depth[x] >= 1 << i) {
                y = parent[i][y];
            }
        }

        if (x == y)
            return x;

        for (int i = LGN; i >= 0; i--) {
            if (parent[i][x] != parent[i][y]) {
                x = parent[i][x];
                y = parent[i][y];
            }
        }

        return parent[0][x];
    }
}