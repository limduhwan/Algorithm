package B01_월_LCA;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayDeque;
        import java.util.ArrayList;
import java.util.StringTokenizer;

// LCA : 최저 공통 조상
public class No_05_11438_Ontact {
    private static final int LGN = 17; // 2^16 (65,536) < N = 100,000 < 2^17 (131,072)
    //    static final int LGN = 5; // 2^4 (16) < N = 18 < 2^5 (32)
    private static int[][] parent;
    private static int[] depth;
    private static ArrayList<Integer>[] adjList;
    private static int N;

    public static void main(String[] args) throws Exception {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("No_11438.txt"));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            int Q = Integer.parseInt(st.nextToken());

            adjList = new ArrayList[N + 1];

            for (int i = 1; i <= N; i++) {
                adjList[i] = new ArrayList<>();
            }

            // 중요!!!!!
            parent = new int[LGN + 1][N + 1];
            depth = new int[N + 1];

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i < N; i++) {
                int a = Integer.parseInt(st.nextToken());
                adjList[a].add(i + 1);
            }

            // DFS 로 돌리면 Stack Overflow 발생가 발생함
            // Depth 의 시작은 꼭 0 으로하고, 방문 체크는 필요시 visit 배열을 쓰자
            // 시작정점은 1, 0
//            dfs(1, 0); *Stack Overflow 가 발생할 수 있다.
            // Stack Overflow 가 발생한다면 BFS 로 변경해야함
            bfs(1, 0);

            aces_find();

            StringBuilder sb = new StringBuilder();

            for (int i = 1; i <= Q; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                sb.append(" ").append(lca(a, b));
            }

            System.out.println("#" + tc + sb);

        }
    }

    // dfs 로 돌리면 Stack Overflow 가 발생할 수 있다.
    // 시작 : here = 1, dep = 0
    private static void dfs(int here, int dep) {
        depth[here] = dep;

        // root 에서 연결된 간선들
        for (int next : adjList[here]) {
            // parent[i][j] -> j 의 2^i 번째 부모 저장
            parent[0][next] = here;
            // 깊이를 1증가 시킨다.
            dfs(next, dep + 1);
        }
    }

    // 시작 : here = 1, dep = 0
    // 모든 정점의 깊이 저장 + 2의 0승번째 부모 저장 -> Parent 배열에
    private static void bfs(int start, int dep) {
        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.add(start);
        depth[start] = dep;

        while (!q.isEmpty()) {
            int here = q.poll();
            // 현재(here)의 자식들 만큼 iteration 을 돈다.
            for (int next : adjList[here]) {
                parent[0][next] = here; // 현재(here)를 자식들의 1번째 조상(parent)으로 표시
                depth[next] = depth[here] + 1; // 자식들의 depth은 현재의 depth + 1 이다.
                q.add(next);
            }
        }
    }

    // 2^n 번째 조상들을 다 저장해둔다.
    private static void aces_find() {
        // K = 0 은 BFS(DFS) 를 통해 다 저장해두었음
        for (int K = 1; K < LGN; K++) {
            for (int V = 1; V <= N; V++) {
                // K = 2, V = 17 parent[2][17] = parent[1][11]
                // 17의 2번 위 부모 = 17의 부모의 부모다
                // K = 1, V = 17 parent[1][17] = parent[0][14]
                parent[K][V] = parent[K - 1][parent[K - 1][V]];
            }
        }
    }

    // x 와 y 의 공통 조상 찾기
    private static int lca(int x, int y) {
        // 루트 기준에서 더 아래있는 정점을 y, 더 높이있는 정점을 x 로 맞춘다.
        // depth 가 더 작다는건 루트에 더 가깝다는 뜻
        // depth 가 큰 정점을 y 로 만든다.
        if (depth[x] > depth[y]) {
            int temp = x;
            x = y;
            y = temp;
        }

        // "y 의 depth" 가 "x 의 depth" 와 같아질 때까지 y 를 끌어올림 = 높이 맞추기
        for (int i = LGN; i >= 0; i--) {
            // 1 << i 값은 아래와 같다.
            // [i, 1 << i] = [17, 131072], [16, 65536], [15, 32768] ...[4, 16] [3, 8], [2, 4], [1, 2], [0, 1]
            // (1 << i) = 2 의 i 승 구하기
            if (depth[y] - depth[x] >= (1 << i)) {
                y = parent[i][y];
            }
        }

        // 동일한 높이까지 끌어올렸을 때, x 와 y 가 같다면 둘의 조상이 같다는 뜻이니
        // 그것이 최저 공통 조상이다.
        if (x == y)
            return x;

        // x 와 y 가 같지 않다면, 루트에서부터 처음으로 조상이 같지 않은 지점을 만날 때까지 탐색
        // 처음으로 달라진 위치에서 그들의 부모 중 공통 조상을 다시 찾음
        for (int i = LGN; i >= 0; i--) {
            if (parent[i][x] != parent[i][y]) {
                x = parent[i][x];
                y = parent[i][y];
            }
        }

        return parent[0][x];
    }

}