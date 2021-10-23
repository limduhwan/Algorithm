package B05_월_최단거리_Fluid_Washal;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
김희성 프로(SW역량강화TF)
알고리즘 유형 : 플로이드-워셜 + 경우의 수 DP

풀이

1. 플로이드-워셜 알고리즘으로 모든 정점 사이의 최단거리를 구한다.
2. 간선 리스트에서 [입력 받은 거리]와 [플로이드-워셜로 구한 최단거리] 가
동일한 간선들을 제거한다.
3. 누적합 DP 로 경우의 수를 구한다.

죠르디는 사랑입니다♥
 */

//(입력)
//2
//4 5
//1 2 4
//4 2 5
//2 3 1
//4 3 3
//1 3 2
//3 3
//1 2 1
//2 3 2
//3 1 3
//
//(출력)
//#1 3
//#2 0

public class No_01_운동하자_플로이드워셜_강사님 {

    private static class Node {
        int start;
        int end;
        int cost;

        public Node(int start, int end, int cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }
    }

    private static final int MOD = 1000000007;
    private static final long INF = 500 * 1000000000L;
    private static long[][] dist;
    private static int[][] dp;
    private static ArrayList<Node> list, resList;
    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("No_운동하자.txt"));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()); // 교차로의 수
            int M = Integer.parseInt(st.nextToken()); // 도로의 수

            dist = new long[N + 1][N + 1];
            dp = new int[N][N + 1];
            list = new ArrayList<>();
            resList = new ArrayList<>();

            for (int i = 1; i <= N; i++) {
                // Long.MAX_VALUE 로 초기화 시 Overflow 발생 유의
                Arrays.fill(dist[i], INF);
            }

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());
                dist[from][to] = dist[to][from] = cost;
                list.add(new Node(to, from, cost));
            }

            // 플로이드-워셜로 최단거리 구하기
            floyd();

            // 최단경로가 아닌 간선만 resList 에 저장
            for (Node node : list) {
                if (dist[node.start][node.end] != node.cost) {
                    resList.add(new Node(node.start, node.end, 0));
                    resList.add(new Node(node.end, node.start, 0));
                }
            }

            int result = 0;

            // 1번 정점에 0번에 도착하는 경우의 수를 1개로 세팅
            dp[0][1] = 1;

            for (int i = 1; i < N; i++) {
                for (Node node : resList) {
                    dp[i][node.end] += dp[i - 1][node.start];
                    dp[i][node.end] %= MOD;
                }
                result += dp[i][2];
                result %= MOD;
            }

            System.out.println("#" + tc + " " + result);
        }
    }

    private static void floyd() {
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }
    }
}