package B02_화_SegmentTree;

import java.io.*;
import java.util.StringTokenizer;

//문제
//N(1 ≤ N ≤ 100,000)개의 정수들이 있을 때,
//a번째 정수부터 b번째 정수까지 중에서 제일 작은 정수를 찾는 것은 어려운 일이 아니다.
//하지만 이와 같은 a, b의 쌍이 M(1 ≤ M ≤ 100,000)개 주어졌을 때는 어려운 문제가 된다.
//이 문제를 해결해 보자.
//
//여기서 a번째라는 것은 입력되는 순서로 a번째라는 이야기이다.
//예를 들어 a=1, b=3이라면 입력된 순서대로 1번, 2번, 3번 정수 중에서 최솟값을 찾아야 한다.
//각각의 정수들은 1이상 1,000,000,000이하의 값을 갖는다.
//
//입력
//첫째 줄에 N, M이 주어진다. 다음 N개의 줄에는 N개의 정수가 주어진다.
//다음 M개의 줄에는 a, b의 쌍이 주어진다.
//
//출력
//M개의 줄에 입력받은 순서대로 각 a, b에 대한 답을 출력한다.

//세그먼트 트리
//https://www.youtube.com/watch?v=075fcq7oCC8
//https://www.youtube.com/watch?v=ahFB9eCnI6c
//https://steady-coding.tistory.com/127
//https://www.acmicpc.net/problem/10868
public class 기본_No_01_10868 {
    static int N, M;
    static int[] arr, minTree;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("No_10868.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        arr = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        minTree = new int[N * 4];

        minInit(1, N, 1);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());

            sb.append(minFind(1, N, 1, left, right) + "\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    // 각 구간 별로 최솟값을 저장.
    public static int minInit(int start, int end, int node) {
        if (start == end) {
            return minTree[node] = arr[start];
        }

        int mid = (start + end) / 2;
        return minTree[node] = Math.min(minInit(start, mid, node * 2), minInit(mid + 1, end, node * 2 + 1));
    }

    // left ~ right 범위 내에 최솟값을 찾음.
    public static int minFind(int start, int end, int node, int left, int right) {
        // 범위를 벗어난 경우
        if (right < start || end < left) {
            return Integer.MAX_VALUE;
        }

        // 범위 안에 있는 경우
        if (left <= start && end <= right) {
            return minTree[node];
        }

        int mid = (start + end) / 2;

        return Math.min(minFind(start, mid, node * 2, left, right), minFind(mid + 1, end, node * 2 + 1, left, right));
    }

}