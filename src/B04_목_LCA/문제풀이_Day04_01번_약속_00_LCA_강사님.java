package B04_목_LCA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 문제풀이_Day04_01번_약속_00_LCA_강사님 {

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
            N = Integer.parseInt(st.nextToken());   // 전체 도시의 수
            int D = Integer.parseInt(st.nextToken());   // 방문 도시의 수

            adjList = new ArrayList[N + 1];

            for (int i = 1; i <= N; i++) {
                adjList[i] = new ArrayList<>();
            }

            parent = new int[LGN + 1][N + 1];
            depth = new int[N + 1];
            visit = new boolean[N + 1];

            st = new StringTokenizer(br.readLine());

            for (int i = 1; i <= N; i++) {
                int C = Integer.parseInt(st.nextToken());
                // C == 0 이면 간선이 없음
                if (C > 0) {
                    adjList[C].add(i);
                    // 양방향 이동 가능함(무향간선)
                    adjList[i].add(C);
                }
            }
            // dfs 로 구현시 Stack Overflow 의 위험이 있으나
            // 문제의 Stack 사이즈가 25MB 로 여유롭다.
            // 따라서 dfs, bfs 중 어떤 것을 사용해도 괜찮다.
//            dfs(1, 0);
            bfs(1, 0);

            aces_find();

            // 이동 거리는 최대 값으로 초기화
            int ansDist = MAX;
            int ansCity = 0;

            for (int i = 0; i < D; i++) {
                st = new StringTokenizer(br.readLine());
                int A = Integer.parseInt(st.nextToken());
                int B = Integer.parseInt(st.nextToken());

                // 두 지점 사이의 거리가 짝수일 때만 만난다.
                if ((depth[A] + depth[B]) % 2 == 0) {
                    // 두 점 사이의 LCA
                    int LCA = lca(A, B);

                    // LCA 부터 A 까지 거리
                    int distA = depth[A] - depth[LCA];

                    // LCA 부터 B 까지 거리
                    int distB = depth[B] - depth[LCA];

                    // A, B 중간 지점까지 거리
                    int distMID = (distA + distB) / 2;

                    // A 의 깊이와 B 의 깊이가 같다면 LCA 가 중간 지점
                    if (depth[A] == depth[B]) {
                        // 사실 distA == distB == distMID 라 어느 것을 비교해도 괜찮음
                        if (ansDist > distMID) {
                            ansDist = distMID;
                            ansCity = LCA;
                        }
                    }
                    else {
                        // 깊이가 깊은 쪽을 A 로 만들고, A 의 조상을 찾아 올라간다.
                        if (depth[A] < depth[B]) {
                            A = B;
                        }

                        // A 부터 중간 지점까지의 거리
                        int move = distMID;

                        // A 의 조상을 따라 올라가면서 중간 지점으로 이동시킴
                        // LCA 에서 희소 테이블을 이용하여 2^k 번째 조상을 찾았던 것 처럼
                        // move 번째 조상을 2^k 을 이용하여 찾는다.
                        for (int k = LGN; k >= 0; k--) {
                            // 1 << i 값은 아래와 같다.
                            // [i, 1 << i] = [17, 131072], [16, 65536], [15, 32768] ... [3, 8], [2, 4], [1, 2], [0, 1]
                            if (move >= (1 << k)) {
                                A = parent[k][A];
                                move -= (1 << k);
                            }
                        }

                        if (ansDist > distMID) {
                            ansDist = distMID;
                            // A 가 중간 지점
                            ansCity = A;
                        }
                    }
                }
            }

            if (ansDist == MAX)
                bw.write("#" + tc + " -1\n");
            else
                bw.write("#" + tc + " " + ansCity + " " + ansDist + "\n");

            bw.flush();
        }
    }

    // 깊이 우선 탐색
    private static void dfs(int here, int dep) {
        depth[here] = dep;
        visit[here] = true;

        // 현재(here)의 자식들 만큼 iteration 을 돈다.
        for (int next : adjList[here]) {
            if (visit[next])
                continue;

            parent[0][next] = here; // 현재(here)를 자식들의 1번째 조상(parent)으로 표시
            dfs(next, dep + 1); // 자식들의 depth 는 현재의 depth + 1 이다.
        }
    }

    // 너비 우선 탐색
    private static void bfs(int start, int dep) {
        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.offer(start);
        depth[start] = dep;
        visit[start] = true;

        while (!q.isEmpty()) {
            int here = q.poll();
            // 현재(here)의 자식들 만큼 iteration 을 돈다.
            for (int next : adjList[here]) {
                if (visit[next])
                    continue;

                visit[next] = true;
                parent[0][next] = here; // 현재(here)를 자식들의 1번째 조상(parent)으로 표시
                depth[next] = depth[here] + 1; // 자식들의 depth 는 현재의 depth + 1 이다.
                q.offer(next);
            }
        }
    }

    // 2^k 번째 조상들을 다 저장해둔다.
    private static void aces_find() {
        // K = 0 은 bfs(dfs) 를 통해 다 저장해두었음
        for (int K = 1; K <= LGN; K++) {
            for (int V = 1; V <= N; V++) {
                parent[K][V] = parent[K - 1][parent[K - 1][V]];
            }
        }
    }

    private static int lca(int x, int y) {
        // 루트 기준에서 더 아래있는 정점을 y, 더 위에 있는 정점을 x 로 맞춘다.
        // depth 가 더 작다는건 루트에 더 가깝다는 뜻
        if (depth[x] > depth[y]) {
            int temp = x;
            x = y;
            y = temp;
        }

        // "y 의 depth" 가 "x 의 depth" 와 같아질 때까지 y 를 끌어올림
        for (int i = LGN; i >= 0; i--) {
            // 1 << i 값은 아래와 같다.
            // [i, 1 << i] = [17, 131072], [16, 65536], [15, 32768] ... [3, 8], [2, 4], [1, 2], [0, 1]
            if (depth[y] - depth[x] >= (1 << i)) {
                y = parent[i][y];
            }
        }

        // 동일한 높이까지 끌어올렸을 때, x 와 y 가 같다면 둘의 조상이 같다는 뜻이니
        // 그것이 최저 공통 조상이다.
        if (x == y)
            return x;

        // x 와 y 가 같지 않다면, LGN 에서부터 처음으로 조상이 같지 않은 지점을 만날 때까지 탐색
        // 처음으로 달라진 위치에서 그들의 부모가 공통 조상이 됨
        for (int i = LGN; i >= 0; i--) {
            if (parent[i][x] != parent[i][y]) {
                x = parent[i][x];
                y = parent[i][y];
            }
        }

        return parent[0][x];
    }
}