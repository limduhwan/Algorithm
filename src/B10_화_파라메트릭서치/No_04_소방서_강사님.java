package B10_화_파라메트릭서치;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
김희성 프로(SW역량강화TF)
알고리즘 유형 : 파라메트릭 서치

제한조건 1. 마을이 있는 좌표의 범위는 0 이상 1,000,000,000 이하의 정수이다.

풀이
0. N <= P 이면 답은 0 이다.
1. 조건 1에 의해 답의 범위는 0 ~ 1,000,000,000 이다. (0 ~ 1,000,000,000) / 2 = 500,000,000
2. 최소거리가 500,000,000 / 2 (mid) 가 되도록 소방서를 지을 수 있는지 확인한다.
    2-1. 불가능하다면, 250,000,000 + 1 (mid) ~ 500,000,000 (right) 사이에서 또 확인한다.
    2-2. 가능하다면, 0 (left) ~ 250,000,000 (mid) 사이에서 또 확인한다.
3. 2번 과정을 left >= right 가 될 때까지 계속 반복한다.

죠르디는 사랑입니다♥
 */

public class No_04_소방서_강사님 {
    private static int[] point; // 마을의 좌표를 입력 받음
    private static int N, P;

    public static void main(String[] args) throws IOException {
//        System.setIn(new FileInputStream("sample/소방서.txt"));
        BufferedReader br = new BufferedReader(new FileReader("No_소방서.txt"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()); // 마을의 수
            P = Integer.parseInt(st.nextToken()); // 소방서의 수

            point = new int[N];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                point[i] = Integer.parseInt(st.nextToken());
            }

            int result = 0;

            // P 가 N 보다 크거나 같으면 답은 0 임
            if (N > P) {
                // 파라메트릭 서치 시작
                // 답이 될 수 있는 값의 범위 left ~ right 사이에 답이 있음
                int left = 0;
                int right = point[N - 1] - point[0];

                while (left < right) {
                    int mid = (right + left) / 2;
                    if (isPossible(mid))
                        right = mid;
                    else
                        left = mid + 1;
                }
                result = left;
            }
            System.out.println("#" + tc + " " + result);
        }
    }

    // 내가 생각한 답(answer) 만큼의 거리 내로 소방서를 지을 때 모든 마을이 커버 가능한지 확인
    private static boolean isPossible(int answer) {
        int now_p = P;
        int idx = 0;

        while (now_p-- > 0) {
            int now = point[idx++] + answer;
            while (idx < N && Math.abs(now - point[idx]) <= answer)
                idx++;
            if (idx == N) return true;
        }

        return false;
    }
}