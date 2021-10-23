package B02_화_SegmentTree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.util.*;

//[2일차] [SW검정] Professional 실전 문제풀이 과정(7/7)
//12:20
//17:00 원리설명
//구간합
//트리에 넣었던 걸 다시 뺀다
/*
김희성 프로(SW역량강화TF)
알고리즘 유형 : 구간트리(인덱스 트리, 세그먼트 트리 등)

풀이
1. A를 기준으로 정렬한다.
2. A 값이 차가 X 이하인 값들만 트리에 들어가 있도록
 X 이하면 트리에 1로 업데이트하고,
 X 이상 차이났던 기존에 들어간 값은 0으로 업데이트한다.
 여기서 기존에 들어갔던 값들을 저장해두기 위해 Queue 를 사용한다.

죠르디는 사랑입니다♥
 */

public class 문제풀이_Day02_04번_부품공장_00_세그먼트트리_강사님 {
    private static long[] segTree; // 세그먼트 트리 데이터 저장

    private static class Comp {
        int a;
        int b;
        public Comp(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("No_부품공장.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        StringTokenizer st1;

        int T = Integer.parseInt(st.nextToken());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken()); // 부품의 개수
            int X = Integer.parseInt(st.nextToken()); // A 부품의 값 최소 차이
            int Y = Integer.parseInt(st.nextToken()); // B 부품의 값 최소 차이

            // 세그먼트 트리 사이즈 = N * 4
            segTree = new long[100000 * 4];
            Comp[] comps = new Comp[N];

            st = new StringTokenizer(br.readLine());
            st1 = new StringTokenizer(br.readLine());

            for (int i = 0; i < N; i++) {
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st1.nextToken());
                comps[i] = new Comp(a, b);
            }

            // A 를 기준으로 오름차순 정렬
            Arrays.sort(comps, (o1, o2) -> o1.a - o2.a);

            // Tree 에 넣었던 값을 다시 빼야하기 때문에 넣었던 값들 큐에 저장
            ArrayDeque<Comp> q = new ArrayDeque<>();

            long result = 0;

            for (int i = 0; i < N; i++) {
                Comp now = comps[i];
                if (!q.isEmpty()) {
                    // a 의 차가 X 보다 크면 Tree 에 0 으로 업데이트
                    while (now.a - q.peek().a > X) {
                        update(1, 1, 100000, q.poll().b, 0);
                    }
                }

                // b 의 차가 Y 보다 큰 값만 더해줘야함
                // 1 ~ (현재.b - Y) 구간 쿼리
                result += query(1, 1, 100000, 1, now.b - Y);

                // (현재.b + Y) ~ 100000 구간 쿼리
                result += query(1, 1, 100000, now.b + Y, 100000);

                // 트리에 넣은 값은 큐에도 넣어준다.
                q.offer(now);
                update(1, 1, 100000, now.b, 1);
            }

            bw.write("#" + tc + " " + result + "\n");
            bw.flush();
        }
    }

    // 세그먼트 트리
    private static long update(int node, int left, int right, int index, long val) {
        if (index < left || index > right)
            return segTree[node];

        if (left == right)
            return segTree[node] = val;

        int mid = (left + right) / 2;

        return segTree[node] = update(node * 2, left, mid, index, val)
                + update(node * 2 + 1, mid + 1, right, index, val);
    }

    private static long query(int node, int left, int right, int start, int end) {
        if (end < left || start > right)
            return 0;

        if (start <= left && right <= end)
            return segTree[node];

        int mid = (left + right) / 2;

        return query(node * 2, left, mid, start, end)
                + query(node * 2 + 1, mid + 1, right, start, end);
    }
}