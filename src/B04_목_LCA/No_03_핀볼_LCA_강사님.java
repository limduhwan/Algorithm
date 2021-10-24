package B04_목_LCA;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*

김희성 프로(SW역량강화TF)
알고리즘 유형 : LCA(최저 공통 조상)

풀이
1. LCA 알고리즘에서 Depth 와 첫번째 조상을 구하는 BFS(혹은 DFS)를 수행할 때
Root(1) 부터 자기 자신까지 점수의 누적합을 구한다. (이 때 자기 자신의 점수를 포함한다.)
2. Root(1) 부터의 거리가 짧은 쪽(Depth 가 낮은 쪽)은 [자신까지의 누적합] 점수를 얻는다.
Depth 가 큰 쪽은 [자신까지의 누적합 - LCA 까지의 누적합] 점수를 얻는다.
3. 둘의 Depth 가 동일하다면, LCA 에서 부딪치기 때문에
둘 다 [자신까지의 누적합 - LCA 까지의 누적합] 점수를 얻는다.

죠르디는 사랑입니다♥
 */

//(입력)
//3
//7 5
//3 4 2 1 5 7 3
//0 1 1 3 3 2 4
//4 5
//6 7
//5 3
//2 4
//7 5
//5 4
//10 4 8 7 5
//0 3 4 1 4
//5 4
//3 2
//3 1
//2 5
//10 6
//4 10 9 6 5 9 7 10 2 5
//0 1 6 6 3 1 2 7 4 7
//3 9
//10 8
//6 5
//6 3
//3 1
//7 10
//
//
//(출력)
//
//
//#1 31 29
//#2 57 53
//#3 92 50

public class No_03_핀볼_LCA_강사님 {

    private static final int LGN = 17; // 2^16 (65,536) < N = 100,000 < 2^17 (131,072)
    private static int[][] parent;
    private static int[] depth;
    private static ArrayList<Integer>[] adjList;
    private static int N;
    private static long[] sum_score; // 해당 지점까지 점수의 누적합 (최대 100,000 * 100,000)

    public static void main(String[] args) throws Exception {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("No_핀볼.txt"));
        StringTokenizer st, st1;
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            int Q = Integer.parseInt(st.nextToken());

            adjList = new ArrayList[N + 1];

            for (int i = 0; i <= N; i++) {
                adjList[i] = new ArrayList<>();
            }

            parent = new int[LGN + 1][N + 1];
            depth = new int[N + 1];
            sum_score = new long[N + 1];

            st = new StringTokenizer(br.readLine());
            st1 = new StringTokenizer(br.readLine());

            for (int i = 1; i <= N; i++) {
                sum_score[i] = Integer.parseInt(st.nextToken()); // 자신의 점수
                int parent = Integer.parseInt(st1.nextToken()); // 자신의 부모
                adjList[parent].add(i);
            }

            bfs(1, 1);
            aces_find();

            long sum_A = 0; // 내 점수의 합
            long sum_B = 0; // 리아 점수의 합

            for (int i = 0; i < Q; i++) {
                st = new StringTokenizer(br.readLine());
                int A = Integer.parseInt(st.nextToken());   // 나
                int B = Integer.parseInt(st.nextToken());   // 리아

                // A, B 의 최저 공통 조상을 구한다.
                int LCA = lca(A, B);

                // 코드 간소화
                // 자기 자신까지 누적합 - LCA 까지 누적합 점수를 얻는다.
                sum_A += sum_score[A] - sum_score[LCA];
                sum_B += sum_score[B] - sum_score[LCA];

                // 둘의 깊이가 같지 않으면서 깊이가 더 낮은 쪽은 모든 점수를 얻을 수 있다.
                // 그런 경우 LCA 까지 누적합을 다시 더해주자.
                if (depth[A] != depth[B]) {
                    if (depth[A] < depth[B])
                        sum_A += sum_score[LCA];
                    else
                        sum_B += sum_score[LCA];
                }

                // 아래 코드랑 위 간소화 코드는 같다.
                // 둘의 깊이가 같으면 LCA 에서 부딪치니까 점수를 LCA 까지 점수의 합만 더한다.
//                if (depth[A] == depth[B]) {
//                    sum_A += sum_score[A] - sum_score[LCA];
//                    sum_B += sum_score[B] - sum_score[LCA];
//                }
//                else {
//                    // 깊이가 깊은 쪽은 LCA 까지만 점수를 얻을 수 있다. 이후 점수는 얻을 수 없음
//                    if (depth[A] < depth[B]) {
//                        sum_A += sum_score[A];
//                        sum_B += sum_score[B] - sum_score[LCA];
//                    }
//                    else {
//                        sum_A += sum_score[A] - sum_score[LCA];
//                        sum_B += sum_score[B];
//                    }
//                }
            }

            System.out.println("#" + tc + " " + sum_A + " " + sum_B);
        }
    }

    private static void bfs(int start, int dep) {
        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.add(start);
        depth[start] = dep;

        while (!q.isEmpty()) {
            int here = q.poll();
            // 현재(here)의 자식들 만큼 iteration 을 돈다.
            for (int next : adjList[here]) {
                parent[0][next] = here; // 현재(here)를 자식들의 1번째 부모(parent)로 표시
                depth[next] = depth[here] + 1; // 자식들의 depth은 현재의 depth + 1 이다.
                sum_score[next] += sum_score[here]; // 조상부터 자신까지 점수의 합 저장
                q.add(next);
            }
        }
    }

    // 2^n 번째 조상들을 다 저장해둔다.
    private static void aces_find() {
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
            if (depth[y] - depth[x] >= (1 << i)) {
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