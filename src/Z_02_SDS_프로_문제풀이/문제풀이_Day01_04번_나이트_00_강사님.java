package Z_02_SDS_프로_문제풀이;

import java.io.*;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//4
//3 17
//..R...R...R...R..
//S...............E
//..R...R...R...R..
//10 10
//.#........
//......R...
//.....#....
//.......S..
//........#.
//..........
//..........
//......R...
//.....E....
//R..R#.....
//5 5
//S.R.R
//..RR.
//RR..R
//.R.R.
//R.R.E
//5 5
//.R..R
//..E..
//....#
//SR.R#
//.....

//#1 4 16
//#2 3 6
//#3 -1
//#4 3 10

public class 문제풀이_Day01_04번_나이트_00_강사님 {
    private static final int[] rx = {-2, -2, 2, 2, -1, 1, -1, 1};
    private static final int[] ry = {-1, 1, -1, 1, -2, -2, 2, 2};
    private static final int BLOCK = '#', START = 'S', END = 'E', BLANK = '.', STAY = 'R';
    private static final int MOD = 1000000007;

    private static class Point implements Comparable<Point> {
        int x;
        int y;
        int cost;
        int isStay; // 휴게소 여부, 휴게소인 경우 0, 아니면 1 로 저장

        public Point(int x, int y, int cost, int isStay) {
            this.x = x;
            this.y = y;
            this.cost = cost;
            this.isStay = isStay;
        }

        @Override
        public int compareTo(Point o) {
            // 가중치가 같은 경우 휴게소를 가장 늦게 방문해야한다.
            // 휴게소 여부, 휴게소인 경우 0, 아니면 1 로 저장
            if (this.cost == o.cost)
                return Integer.compare(o.isStay, this.isStay);
            else
                return Integer.compare(this.cost, o.cost);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("No_나이트.txt"));
        StringTokenizer st;
        int TC = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= TC; tc++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            char[][] map = new char[N][M]; // 입력 값 저장
            int[][] cost = new int[N][M]; // 거리(피로도) 저장
            int[][] cnt = new int[N][M]; // 경우의 수 저장

            int endX = 0, endY = 0;

            PriorityQueue<Point> PQ = new PriorityQueue<>();

            // cost 배열 (무한대 값으로) 초기화
            for (int[] i : cost) {
                Arrays.fill(i, Integer.MAX_VALUE);
            }

            for (int i = 0; i < N; i++) {
                map[i] = br.readLine().toCharArray();
                for (int j = 0; j < M; j++) {
                    // 시작점인 경우 다익스트라 시작점을 위한 세팅
                    if (map[i][j] == START) {
                        PQ.add(new Point(i, j, 0, 1));
                        // 시작점의 경우의 수 1 로 세팅
                        cnt[i][j] = 1;
                        cost[i][j] = 0;
                    } else if (map[i][j] == END) {
                        endX = i;
                        endY = j;
                    }
                }
            }

            // 다익스트라 탐색 시작

            while (!PQ.isEmpty()) {
                Point now = PQ.poll();
                // 더 큰 가중치로 도착한 경우 패스
                if (cost[now.x][now.y] < now.cost)
                    continue;

                // 연결된 간선 탐색
                for (int i = 0; i < 8; i++) {
                    int nextX = now.x + rx[i];
                    int nextY = now.y + ry[i];

                    // MAP 범위를 벗어남
                    if (nextX < 0 || nextX >= N || nextY < 0 || nextY >= M)
                        continue;

                    // 이동 불가능한 지점
                    if (map[nextX][nextY] == BLOCK)
                        continue;

                    // 휴게소에서 다른 휴게소로 바로 이동할 수 없다.
                    if (map[now.x][now.y] == STAY && map[nextX][nextY] == STAY)
                        continue;

                    // 휴게소인 경우 cost 를 0 으로 세팅한다.
                    int stay = 1;
                    if (map[nextX][nextY] == STAY)
                        stay = 0;

                    // cost 가 더 작을 때만 갱신하고 PQ에 넣음
                    if (cost[nextX][nextY] > now.cost + stay) {
                        cost[nextX][nextY] = now.cost + stay;
                        // isStay 는 PQ 정렬을 위해서 stay 값 세팅 필요
                        PQ.add(new Point(nextX, nextY, cost[nextX][nextY], stay));
                    }

                    // 현 위치에 오는 경우의 수를 다음 지점에 도달하는 경우의 수에 더해준다.
                    if (cost[nextX][nextY] == now.cost + stay) {
                        cnt[nextX][nextY] += cnt[now.x][now.y];
                        cnt[nextX][nextY] %= MOD;
                    }
                }
            }

            if (cost[endX][endY] == Integer.MAX_VALUE)
                System.out.println("#" + tc + " -1");
            else
                System.out.println("#" + tc + " " + cost[endX][endY] + " " + cnt[endX][endY]);
        }
    }
}