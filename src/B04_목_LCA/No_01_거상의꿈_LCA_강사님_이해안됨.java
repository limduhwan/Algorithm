package B04_목_LCA;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*

김희성 프로(SW역량강화TF)
알고리즘 유형 : LCA (Lowest Common Ancestor : 최저 공통 조상)
풀이
1. LCA 알고리즘에서 모든 정점의 깊이를 구하는 BFS/DFS 탐색시에
Root (1번 정점) 부터의 거리의 누적합(cost 배열)도 구한다.
2. A -> B 정점의 최단거리는 cost[A] - cost[LCA(A,B)] + cost[B] - cost[LCA(A,B)] 가 된다.
3. 최단거리의 누적합을 구한다. -> 살 수 있는 특산품의 수가 된다.
4. 동일한 정점에서 시간 차를 두고 특산품을 두 번 사는 경우에는
마지막에 들렸던 누적합(거리)을 저장해두면 된다.
즉, A라는 정점에 5일 걸려 도착해서 특산품을 한 번 사고,
그 이후에 동일한 A 정점에 7일 뒤에 들려서 특산품을 샀다면,
총 이동 시간은 12일이니까 그냥 기존 5를 12로 갱신해버리고
마지막에 답을 구할 때만 12를 더해주면 된다.

죠르디는 사랑입니다♥

 */

public class No_01_거상의꿈_LCA_강사님_이해안됨 {
    private static class Node {
        int dest;
        int cost;

        public Node(int dest, int cost) {
            this.dest = dest;
            this.cost = cost;
        }
    }

    private static final int LGN = 17; // 2^16 = 65,536 < 100,000 < 2^17 = 131,072
    private static int[][] parent;
    private static int[] depth;
    private static ArrayList<Node>[] adjList;
    private static long[] cost; // 루트(1번 정점)부터의 거리를 저장
    private static long[] last; // 해당 정점에 마지막에 들렸을 때의 거리

    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("No_거상의꿈.txt"));
        BufferedReader br = new BufferedReader(new FileReader("No_거상의꿈.txt"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken()); // 마을의 수
            int K = Integer.parseInt(st.nextToken()); // 방문할 마을의 수

            adjList = new ArrayList[N + 1];

            for (int i = 0; i <= N; i++) {
                adjList[i] = new ArrayList<>();
            }
            parent = new int[LGN + 1][N + 1];
            depth = new int[N + 1];
            cost = new long[N + 1];
            last = new long[N + 1];

            // 간선은 N - 1 개
            for (int i = 1; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());
                adjList[start].add(new Node(end, cost));
                adjList[end].add(new Node(start, cost));
            }

            bfs(1, 1);
            aces_find(N);

            long result = 0;
            long sum_cost = 0; // 걸린 시간의 누적 값

            int now = 1; // 출발 정점

            st = new StringTokenizer(br.readLine());

            for (int i = 0; i < K; i++) {
                int next = Integer.parseInt(st.nextToken()); // 이번에 방문할 정점
                int lca = lca(now, next);
                // 현재 정점에서 LCA 까지 거리 + 다음 정점에서 LCA 까지 거리 = 총 이동거리
                long dist = (cost[now] - cost[lca]) + (cost[next] - cost[lca]);
                sum_cost += dist; // 이동 시간의 누적합
                // next 정점의 마지막 방문 시간을 이동 시간의 누적합으로 갱신
                last[next] = sum_cost;
                now = next; // 지금의 next(다음 위치)가 다음에는 now(현재 위치)가 됨
            }

            // i 번 정점에 마지막에 들렸던 이동 시간의 누적합을 답에 다 더한다.
            for (int i = 1; i <= N; i++) {
                result += last[i];
            }

            System.out.println("#" + tc + " " + result);
        }
    }

    private static void bfs(int start, int dep) {
        ArrayDeque<Node> queue = new ArrayDeque<>();
        queue.add(new Node(start, 0));
        cost[start] = 0;
        depth[start] = dep;

        while (!queue.isEmpty()) {
            Node now = queue.poll();
            // 현재(here)의 자식들 만큼 iteration 을 돈다.
            for (Node next : adjList[now.dest]) {
                if (depth[next.dest] == 0) {
                    parent[0][next.dest] = now.dest; // 현재(here)를 자식들의 0번째 부모(parent)로 표시
                    depth[next.dest] = depth[now.dest] + 1; // 자식들의 depth은 현재의 depth + 1 이다.
                    cost[next.dest] = cost[now.dest] + next.cost; // Root 부터 자신까지 거리를 저장
                    queue.add(next);
                }
            }
        }
    }

    // 2^n 번째 조상들을 다 저장해둔다.

    private static void aces_find(int N) {
        // K = 0 은 BFS(DFS) 를 통해 다 저장해두었음
        for (int K = 1; K <= LGN; K++) {
            for (int V = 1; V <= N; V++) {
                parent[K][V] = parent[K - 1][parent[K - 1][V]];
            }
        }
    }

    private static int lca(int x, int y) {
        // 루트 기준에서 더 아래있는 정점을 y, 더 높이있는 정점을 x 로 맞춘다.
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
            if (depth[y] - depth[x] >= 1 << i) {
                y = parent[i][y];
            }
        }

        // 동일한 높이까지 끌어올렸을 때, x 와 y 가 같다면 둘의 조상이 같다는 뜻이니
        // 그것이 최저 공통 조상이다.
        if (x == y)
            return x;

        // x 와 y 가 같지 않다면, 루트에서부터 처음으로 조상이 같지 않은 지점을 만날 때까지 탐색
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