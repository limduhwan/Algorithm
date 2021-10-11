package B01_월_LCA;

//문제
//N개의 도시와 그 도시를 연결하는 N-1개의 도로로 이루어진 도로 네트워크가 있다.
//
//모든 도시의 쌍에는 그 도시를 연결하는 유일한 경로가 있고,
// 각 도로의 길이는 입력으로 주어진다.
//
//총 K개의 도시 쌍이 주어진다.
// 이때, 두 도시를 연결하는 경로 상에서 가장 짧은 도로의 길이와
// 가장 긴 도로의 길이를 구하는 프로그램을 작성하시오.
//
//입력
//첫째 줄에 N이 주어진다. (2 ≤ N ≤ 100,000)
//
//다음 N-1개 줄에는 도로를 나타내는 세 정수 A, B, C가 주어진다.
// A와 B사이에 길이가 C인 도로가 있다는 뜻이다.
// 도로의 길이는 1,000,000보다 작거나 같은 양의 정수이다.
//
//다음 줄에는 K가 주어진다. (1 ≤ K ≤ 100,000)
//
//다음 K개 줄에는 서로 다른 두 자연수 D와 E가 주어진다.
// D와 E를 연결하는 경로에서 가장 짧은 도로의 길이와 가장 긴 도로의 길이를 구해서 출력하면 된다.
//
//출력
//총 K개 줄에 D와 E를 연결하는 경로에서
// 가장 짧은 도로의 길이와 가장 긴 도로의 길이를 출력한다.

//5
//2 3 100
//4 3 200
//1 5 150
//1 3 50
//3
//2 4
//3 5
//1 2

//100 200
//50 150
//50 100

//https://www.acmicpc.net/problem/3176
//https://subbak2.tistory.com/61?category=824830

import java.io.*;
import java.util.*;

// 3176 도로 네트워크
public class 기본_No_07_3176_인터넷 {

    static int N, K;    // N : 정점수, K : 2의 지수
    static int M;        // M : 쿼리 수 (문제에서 K)

    // LCA 관련 변수
    static int[] depth;
    static int[][] parent; // parent[K][V] 정점 V의 2^K번째 조상 정점 번호
    // parent[K][V] = parent[K-1][parent[K-1][V]];
    // TREE 변수
    static ArrayList[] tree;

    // 도로 네트워크 추가변수
    static int[][] minDist;    // minDist[K][V]  정점 V의 2^K번째 조상까지의 최소거리 도로
    static int[][] maxDist; // maxDist[K][V]  정점 V의 2^K번째 조상까지의 최대거리 도로

    static int min, max;

    static class edge {
        int target, cost;

        public edge(int target, int cost) {
            this.target = target;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("No_3176.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        // 1. 입력 & 변수 준비
        N = Integer.parseInt(br.readLine());

        // 2^K > N인 K 찾기
        K = 0;
        for (int i = 1; i <= N; i *= 2) {
            K++;
        }

        // LCA 관련 변수 초기화
        depth = new int[N + 1];
        parent = new int[K][N + 1];

        // 도로 네트워크 변수 초기화
        minDist = new int[K][N + 1];
        maxDist = new int[K][N + 1];

        // TREE 변수 초기화
        tree = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            tree[i] = new ArrayList<edge>();
        }

        int a, b, c;
        for (int i = 1; i <= N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            // 양방향 간선
            tree[a].add(new edge(b, c));
            tree[b].add(new edge(a, c));
        }

        // 2. DEPTH 확인
        dfs(1, 1);

        // 3. 2^N 까지 parent 채우기
        fillParent();

        // 4. LCA 진행
        M = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());

            lca(a, b);
            sb.append(min + " " + max + "\n");
        }

        bw.write(sb.toString());

        bw.flush();
        bw.close();
        br.close();
    }

    // 최소공통조상
    static void lca(int a, int b) {
        // 1. depth[a] >= depth[b] 이도록 조정하기
        if (depth[a] < depth[b]) {
            int tmp = a;
            a = b;
            b = tmp;
        }

        min = Integer.MAX_VALUE;
        max = -1;

        // 2. 더 깊은 a를 2^K승 점프하여 depth를 맞추기
        for (int i = K - 1; i >= 0; i--) {
            if (Math.pow(2, i) <= depth[a] - depth[b]) {
                min = Math.min(min, minDist[i][a]);
                max = Math.max(max, maxDist[i][a]);

                a = parent[i][a];
            }
        }

        // 3. depth를 맞췄는데 같다면 종료
        if (a == b) return;

        // 4. a-b는 같은 depth이므로 2^K승 점프하며 공통부모 바로 아래까지 올리기
        for (int i = K - 1; i >= 0; i--) {
            if (parent[i][a] != parent[i][b]) {
                min = Math.min(min, Math.min(minDist[i][a], minDist[i][b]));
                max = Math.max(max, Math.max(maxDist[i][a], maxDist[i][b]));

                a = parent[i][a];
                b = parent[i][b];
            }
        }

        min = Math.min(min, Math.min(minDist[0][a], minDist[0][b]));
        max = Math.max(max, Math.max(maxDist[0][a], maxDist[0][b]));

        return;
    }

    // 부모 채우기
    static void fillParent() {
        for (int i = 1; i < K; i++) {
            for (int j = 1; j <= N; j++) {
                parent[i][j] = parent[i - 1][parent[i - 1][j]];

                // 도로네트워크 추가
                minDist[i][j] = Math.min(minDist[i - 1][j], minDist[i - 1][parent[i - 1][j]]);
                maxDist[i][j] = Math.max(maxDist[i - 1][j], maxDist[i - 1][parent[i - 1][j]]);
            }
        }
    }

    // DEPTH 확인 DFS
    static void dfs(int id, int cnt) {
        // 1. depth를 기록
        depth[id] = cnt;

        // 2. 자식들의 depth를 기록
        int len = tree[id].size();
        for (int i = 0; i < len; i++) {
            edge next = (edge) tree[id].get(i);
            // 아직 깊이를 모르면 → 미방문 노드
            if (depth[next.target] == 0) {
                dfs(next.target, cnt + 1);
                parent[0][next.target] = id;        // 첫번째 부모를 기록

                minDist[0][next.target] = next.cost; // 현재 cost로 갱신
                maxDist[0][next.target] = next.cost; // 현재 cost로 갱신

            }
        }
        return;
    }
}
