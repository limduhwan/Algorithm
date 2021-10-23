package B05_월_최단거리_Fluid_Washal;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 플로이드-워셜

//3
//5 6 5 5
//1 2 2
//3 2 2
//3 4 3
//1 3 6
//5 4 4
//5 1 4
//1 4
//5 3
//3 1
//4 2
//2 5
//5 6 5 -5
//1 2 2
//3 2 2
//3 4 3
//1 3 6
//5 4 4
//5 1 4
//1 4
//5 3
//3 1
//4 2
//2 5
//5 4 3 1
//1 2 1
//2 3 2
//3 4 1
//4 5 2
//1 2
//1 3
//3 5

//#1 29
//#2 -15
//#3 5
public class 문제풀이_Day01_02번_외환관리_00_플루이드와샬_강사님 {
    // [0][X][Y] = K 안쓴거, [1][X][Y] = K 쓴거
    private static long[][][] dist = new long[2][201][201];
    private static int N;

    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("sample_input.txt"));
        BufferedReader br = new BufferedReader(new FileReader("No_외환관리.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()); // 통화종류의 수 : 3 <= N <= 200
            int M = Integer.parseInt(st.nextToken()); // 교환비용 정보 개수 : N-1 <= M <= (N-1) * N / 2
            int D = Integer.parseInt(st.nextToken()); // 업무 일 수 : 1 <= D <= 100,000
            int K = Integer.parseInt(st.nextToken()); // 협약 비용 // 매일 1회 사용 : -1,000 <= K <= 1,000

            // dist 배열 max 값으로 초기화
            for (int i = 0; i <= N; i++) {
                // dist 배열은 long 이지만
                // 자료형 Overflow 방지를 위해 int max 로 초기화
                Arrays.fill(dist[0][i], Integer.MAX_VALUE);
                Arrays.fill(dist[1][i], Integer.MAX_VALUE);
            }

            for (int i = 1; i <= M; i++) {
                st = new StringTokenizer(br.readLine());
                // X 통화와 Y 통화를 직접 교환시 비용 = Z
                int X = Integer.parseInt(st.nextToken());
                int Y = Integer.parseInt(st.nextToken());
                int Z = Integer.parseInt(st.nextToken());
                // 0 = K 안쓴거
                dist[0][X][Y] = dist[0][Y][X] = Z; // 양방향 이동 가능하니까 X, Y 바꾼 것도 저장
                // 1 = K 쓴거
                dist[1][X][Y] = dist[1][Y][X] = Math.min(Z, K);
            }

            // 플로이드-워셜
            // main 함수 내에서 돌리면 시간초과. 꼭 클래스 메소드로 분리할 것.
            floyd();

            long result = 0;

            for (int i = 0; i < D; i++) {
                st = new StringTokenizer(br.readLine());
                int X = Integer.parseInt(st.nextToken());
                int Y = Integer.parseInt(st.nextToken());
                // K 를 한 번이라도 쓴 값이 안 쓴 값보다 저렴하거나 같다.
                // dist[1][X][Y] <= dist[0][X][Y]
                result += dist[1][X][Y];
            }

            bw.write("#" + t + " " + result + "\n");
            bw.flush();
        }
    }

    // 플로이드-워셜
    private static void floyd() {
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    // 0 : K 안쓴거
                    dist[0][i][j] = Math.min(dist[0][i][j], dist[0][i][k] + dist[0][k][j]);
                    // 1 : K 쓴거
                    dist[1][i][j] = Math.min(dist[1][i][j],
                            // i-k 에서 K를 썻으면, k-j 에서는 못씀. 그 반대도 동일함
                            Math.min(dist[1][i][k] + dist[0][k][j], dist[0][i][k] + dist[1][k][j]));
                }
            }
        }
    }
}
