package Z_02_SDS_프로_문제풀이.다익스트라;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
김희성 프로(SW역량강화TF)
알고리즘 유형 : 다익스트라 (또는 DP)

풀이
1. 다익스트라 알고리즘으로 풀이하면서 미사일의 사용 여부에 따라
각각 사용했을 때, 사용하지 않았을 때 최단거리를 따로 저장한다.
2. 미사일 사용여부 역시 우선순위 큐에 넣어서 함께 들고 다녀야한다.

*TIP : 디버깅의 편의를 위해 X, Y 를 Y, X 로 바꾸면 편하다.

죠르디는 사랑입니다♥
 */

//(입력)
//3
//8 3
//...
//...
//...
//**.
//*.*
//**.
//*.*
//.*.
//20 4
//....
//....
//....
//....
//*...
//...*
//..*.
//....
//..**
//...*
//**..
//..*.
//*..*
//.*..
//*...
//.***
//..*.
//..*.
//.**.
//....
//8 3
//...
//...
//...
//***
//**.
//.*.
//.*.
//.*.
//
//
//(출력)
//#1 2
//#2 3
//#3 IMPOSSIBLE


public class No_02_스크롤게임_다익스트라_강사님 {

    private static class Node {
        int y;
        int x;
        int cost;
        int missile; // 미사일 사용 여부 : 미사용 = 0 / 사용 = 1

        public Node(int y, int x, int cost, int missile) {
            this.y = y;
            this.x = x;
            this.cost = cost;
            this.missile = missile;
        }
    }

    private static final char BLANK = '.', BLOCK = '*';
    private static final int[] rx = {-1, 0, 1}; // x 좌표를 이동하기 위한 배열
    private static final int[] cx = {1, 0, 1}; // x 좌표에 따른 이동 횟수
    private static final int INF = 3001; // 거리의 최대 값
    private static int[][][] cost; // [폭탄사용여부][Y][X]
    private static char[][] map;
    private static int N, M;

    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("sample/스크롤게임.txt"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("No_스크롤게임.txt"));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()); // 맵의 세로 칸 수
            M = Integer.parseInt(st.nextToken()); // 맵의 가로 칸 수

            cost = new int[2][N][M]; // [폭탄사용여부][Y][X]
            map = new char[N][M];

            for (int i = 0; i < N; i++) {
                map[i] = br.readLine().toCharArray();
            }

            int result = INF;
            dijkstra(N - 1, 0); // 시작 위치는 왼쪽 맨 아래

            // 마지막 줄 중에서 가장 작은 값을 찾는다.
            for (int i = 0; i < M; i++) {
                // 폭탄을 무조건 쓴 것이 안쓴것 보다 cost 가 작거나 같음
                result = Math.min(result, cost[1][0][i]);
            }

            if (result == INF)
                System.out.println("#" + tc + " IMPOSSIBLE");
            else
                System.out.println("#" + tc + " " + result);
        }
    }

    private static void dijkstra(int sy, int sx) {
        // 거리 기준 오름 차순 정렬
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.cost, o2.cost));

        // cost 배열 무한대로 초기화
        for (int i = 0; i < N; i++) {
            Arrays.fill(cost[0][i], INF);
            Arrays.fill(cost[1][i], INF);
        }

        // 시작점은 비용(거리)을 0으로 설정
        cost[0][sy][sx] = 0;
        pq.offer(new Node(sy, sx, 0, 0));

        while (!pq.isEmpty()) {
            Node now = pq.poll();
            // 더 큰 가중치로 도착한 경우 패스
            if (cost[now.missile][now.y][now.x] < now.cost)
                continue;

            // 3번 [왼쪽, 중앙, 오른쪽] 탐색
            for (int i = 0; i < 3; i++) {
                // cost 가 더 작을 때만 갱신하고 PQ에 넣음
                int ny = now.y - 1; // y 는 무조건 1칸 씩 움직인다.
                int nx = now.x + rx[i]; // rx = [-1, 0, 1]
                int next_cost = now.cost + cx[i]; // cx = [1, 0, 1]

                // 맵 범위를 벗어나는 경우 예외 처리
                if (nx < 0 || ny < 0 || nx >= M || ny >= N)
                    continue;

                // 미사일은 쓰는 경우는 무조건 갈 수 있음
                if (now.missile == 0) {
                    if (cost[now.missile + 1][ny][nx] > next_cost) {
                        cost[now.missile + 1][ny][nx] = next_cost;
                        pq.offer(new Node(ny, nx, cost[now.missile + 1][ny][nx], now.missile + 1));
                    }
                }

                // 미사일을 안쓰는 경우는 장애물이 있으면 못 감
                if (map[ny][nx] == BLOCK)
                    continue;

                if (cost[now.missile][ny][nx] > next_cost) {
                    cost[now.missile][ny][nx] = next_cost;
                    pq.offer(new Node(ny, nx, cost[now.missile][ny][nx], now.missile));
                }
            }
        }
    }
}