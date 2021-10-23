package B02_화_파라메트릭서치;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

// 파라메트릭 서치 & BFS

public class No_03_등산_강사님 {

    private static class Pos {
        int x;
        int y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static final int[] rx = {1, -1, 0, 0}, ry = {0, 0, 1, -1};
    // 산 고도 저장
    private static int[][] map;
    // 방문 여부 저장
    private static boolean[][] visit;
    // 크기
    private static int N;
    // map 에서 가장 작은 값과 큰 값
    private static int init_min, init_max;
    // 입력 값의 MAX
    private static final int INPUT_MAX = 200;

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("No_등산.txt"));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            visit = new boolean[N][N];

            // map 전체에서 가장 작은 값과 큰 값 저장을 위함
            int all_min = INPUT_MAX;
            int all_max = 0;

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    all_min = Math.min(all_min, map[i][j]);
                    all_max = Math.max(all_max, map[i][j]);
                }
            }

            // 시작점과 도착점 중 큰 값과 작은 값 저장
            init_max = Math.max(map[0][0], map[N - 1][N - 1]);
            init_min = Math.min(map[0][0], map[N - 1][N - 1]);

            // left = 시작점과 도착점 차이보다는 답이 무조건 크거나 같음
            int left = Math.abs(map[0][0] - map[N - 1][N - 1]);

            // right = map 전체에서 가장 작은 값과 큰 값의 차이보다는 답이 무조건 작거나 같음
            int right = all_max - all_min;

            // 파라메트릭 서치
            while (left < right) {
                int mid = (left + right) / 2;
                if (isPossible(mid))
                    right = mid;
                else
                    left = mid + 1;
            }

            System.out.println("#" + tc + " " + left);
        }
    }

    private static boolean isPossible(int answer) {
        // 시작점 = 1, 도착점 = 1 일때, 답이 5라면 이동 가능한 범위
        // (min, max) = (-4, 1), (-3, 2), (-2, 3), (-1, 4), (0, 5), (1, 6)
        // 해당 조합들로 도착 가능한지 탐색함

        for (int i = answer - (init_max - init_min); i >= 0; i--) {
            int min = init_min - i;
            int max = init_max + ((answer - (init_max - init_min)) - i);
            // map 에는 0 미만 200 초과인 값이 없음
            if (min < 0 || max > INPUT_MAX)
                continue;

            if (bfs(min, max))
                return true;
        }

        return false;
    }

    private static boolean bfs(int min, int max) {
        ArrayDeque<Pos> queue = new ArrayDeque<>();

        for (int i = 0; i < N; i++) {
            Arrays.fill(visit[i], false);
        }

        visit[0][0] = true;
        queue.offer(new Pos(0, 0));

        while (!queue.isEmpty()) {
            Pos now = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nextX = now.x + rx[i];
                int nextY = now.y + ry[i];

                // map 밖으로 못 나가도록 예외처리

                if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= N)
                    continue;

                // 찾고자하는 범위(min, max)내에서만 탐색
                if (map[nextX][nextY] < min || map[nextX][nextY] > max)
                    continue;

                // 이미 방문한 정점은 skip
                if (visit[nextX][nextY])
                    continue;

                visit[nextX][nextY] = true; // 방문 표시

                queue.offer(new Pos(nextX, nextY));
            }
        }

        return visit[N - 1][N - 1];
    }
}