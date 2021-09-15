package Z_02_SDS_프로_문제풀이;

import java.io.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
김희성 프로(SW역량강화TF)
알고리즘 유형 : 세그먼트 트리

풀이
1. 높이 순으로 내림 차순 정렬 (높이가 같으면 index 순)
2. 높이가 높은 순서부터
    2-1 Tree 에서 K 번째 수 찾기 수행
    2-2 수행 후 해당 건물 Tree 에 업데이트
3. 2번 과정 반복
죠르디는 사랑입니다♥
 */
public class 문제풀이_Day02_02번_화살_00_세그먼트트리_강사님 {
    private static class Point implements Comparable<Point> {
        int idx;
        int power;
        int height;
        public Point(int idx, int power, int height) {
            this.idx = idx;
            this.power = power;
            this.height = height;
        }
        // 높이 기준 오름차순 정렬
        // * Merge Sort 로 정렬되기 때문에 index 순서 보장됨
        @Override
        public int compareTo(Point o) {
            return o.height - this.height;
        }
    }

    private static final int[] segTree = new int[131072 * 2]; // 세그먼트 트리 데이터 저장
    private static final Point[] points = new Point[100000];

    public static void main(String[] args) throws IOException {
//        System.setIn(new FileInputStream("sample_input.txt"));
        BufferedReader br = new BufferedReader(new FileReader("No_화살.txt"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st, st1;

        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < 100000; i++) {
            points[i] = new Point(0, 0, 0);
        }

        for (int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            st1 = new StringTokenizer(br.readLine());

            for (int i = 0; i < N; i++) {
                points[i].idx = i + 1;
                points[i].power = Integer.parseInt(st1.nextToken());
                points[i].height = Integer.parseInt(st.nextToken());
            }
            // 높이 기준 오름차순 정렬
            // * Merge Sort 로 정렬되기 때문에 index 순서 보장됨
            Arrays.sort(points, 0, N);

            long result = 0;
            for (int i = 0; i < N; i++) {
                int idx = points[i].idx;
                int P = points[i].power + 1;
                // 현재 건물보다 index 가 높은 건물이 몇 개 있는지
                int now = query(idx + 1, N, 1, 1, N);
                // P 보다 now 가 크거나 같아야 건물에 화살이 꽂힘
                if (now >= P) {
                    // i - now = 전체 트리에서 현재 건물의 순서
                    // P + i - now = K 일 때, 전체 트리에서 K번째 수 찾기 수행
                    result += search(1, N, 1, P + i - now);
                }
                // 자기 자신은 쿼리 수행 후에 업데이트 해야함
                update(1, N, 1, idx, 1);
            }
            Arrays.fill(segTree, 0);
            System.out.println("#" + tc + " " + result);
        }
    }

    // 세그먼트 트리
    private static void update(int left, int right, int node, int index, int dif) {
        if (index < left || index > right)
            return;

        segTree[node] += dif;

        if (left == right)
            return;

        int mid = (left + right) / 2;

        update(left, mid, node * 2, index, dif);
        update(mid + 1, right, node * 2 + 1, index, dif);
    }


    private static int query(int start, int end, int node, int left, int right) {
        if (start > right || end < left)
            return 0;

        if (start <= left && right <= end)
            return segTree[node];

        int mid = (left + right) / 2;
        return query(start, end, node * 2, left, mid)
                + query(start, end, node * 2 + 1, mid + 1, right);
    }


    // 세그먼트 트리에서 K 번째 수 찾기
    private static int search(int start, int end, int node, int k) {
        if (start == end)
            return start;
        int mid = (start + end) / 2;
        if (k <= segTree[node * 2])
            return search(start, mid, node * 2, k);
        else
            return search(mid + 1, end, node * 2 + 1, k - segTree[node * 2]);
    }
}