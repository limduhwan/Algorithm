package B06_금_LIS;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 기본_LIS_강사님 {
    private static int[] arr;
    private static int[] dp;
    private static int[] trace; // 실제 LIS 의 구성요소를 트래킹하기 위한 배열

    // 음수가 들어갈 수 있는 경우는 초기값을 Integer.MIN_VALUE
    private static final int MIN = -1000000001;

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("No_LIS2.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        arr = new int[N];
        dp = new int[N];
        trace = new int[N];
        Arrays.fill(dp, MIN);

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int cnt = findLIS();

        ArrayDeque<Integer> stack = new ArrayDeque<>();

        for (int i = N - 1; i >= 0; i--) {
            if (trace[i] == cnt - 1) {
                stack.push(arr[i]);
                cnt--;
            }
        }

        bw.write(stack.size() + "\n");

        while (!stack.isEmpty()) {
            bw.write(stack.pop() + " ");
        }

        bw.write("\n");
        bw.flush();
    }

    private static int findLIS() {
        dp[0] = arr[0];
        trace[0] = 0;
        int max_idx = 0; // LIS 의 마지막 위치 저장
        for (int i = 1; i < arr.length; i++) {
            // dp 배열의 가장 큰 수보다 더 큰 수가 들어오면 뒤에 이어서 붙인다.
            if (dp[max_idx] < arr[i]) {
                dp[++max_idx] = arr[i];
                trace[i] = max_idx; // 위치 저장
            } else {
                // dp 배열의 가장 큰 수보다 작을 경우 배열 내에서 Lower Bound 와 교체
                int target;
                // Binary Search API 활용
                int tmp = Arrays.binarySearch(dp, 0, max_idx, arr[i]);
                target = tmp < 0 ? (tmp * -1) - 1 : tmp;
                // lower_bound 직접 구현
//              target = lower_bound(max_idx, arr[i]);
                dp[target] = arr[i];
                trace[i] = target; // 위치 저장
            }
        }
        return max_idx + 1;
    }

    // 배열 내에서 target 보다 큰 수 중에서 가장 작은 수
    private static int lower_bound(int size, int target) {
        int mid, start, end;
        start = 0;
        end = size - 1;

        // 이분탐색 : end 가 start 보다 같거나 작아지면, 그 값이 Lower Bound
        while (end > start) {
            mid = (start + end) / 2;
            if (dp[mid] >= target) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }

        return end;
    }
}