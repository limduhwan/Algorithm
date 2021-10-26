package B02_화_SegmentTree;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

//[2일차] [SW검정] Professional 실전 문제풀이 과정(7/7)
//12:20
//17:00 원리설명
//구간합
//트리에 넣었던 걸 다시 뺀다

/*

김희성 프로(SW역량강화TF)

알고리즘 유형 : 구간 트리(인덱스 트리, 세그먼트 트리 등)



풀이

1. A를 기준으로 정렬한다.

2. A 값이 차가 X 이하인 값들만 트리에 들어가 있도록

 X 이하면 트리에 1 을 더하고,

 X 이상 차이났던 기존에 들어간 값은 -1 을 더한다.

 여기서 기존에 들어갔던 값들을 저장해두기 위해 Queue 를 사용한다.



죠르디는 사랑입니다♥

 */

public class 문제풀이_Day02_04번_부품공장_00_인덱스트리_강사님 {
    private static class Comp {
        int a;
        int b;
        public Comp(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }

    private static int[] indexTree; // 인덱스 트리 데이터 저장
    private static int s_idx; // 인덱스 트리 leaf 노드의 시작 위치

    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("sample/부품공장.txt"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
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

            // 인덱스 트리 시작 위치 계산
            s_idx = 1;
            while (s_idx < N)
                s_idx <<= 1;

            indexTree = new int[s_idx * 2];

            s_idx--;

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
                    // a 의 차가 X 보다 크면 Tree 에 -1 을 더한다.
                    while (now.a - q.peek().a > X) {
                        update(q.poll().b, -1);
                    }
                }

                // b 의 차가 Y 보다 큰 값만 더해줘야함
                // 1 ~ (현재.b - Y) 구간 쿼리
                result += query(1, now.b - Y);

                // (현재.b + Y) ~ 100000 구간 쿼리
                result += query(now.b + Y, N);

                // 트리에 넣은 값은 큐에도 넣어준다.
                q.offer(now);
                update(now.b, 1);
            }

            bw.write("#" + tc + " " + result + "\n");
            bw.flush();
        }
    }

    // 인덱스 트리
    private static int query(int left, int right) {
        left += s_idx;
        right += s_idx;
        int sum = 0;

        while (left <= right) {
            // 시작하는 부분이 홀수 일 때
            if (left % 2 == 1)
                sum += indexTree[left];
            // 끝나는 부분이 짝수 일 때
            if (right % 2 == 0)
                sum += indexTree[right];

            left = (left + 1) >> 1; // left = (left + 1) / 2;
            right = (right - 1) >> 1; // right = (right - 1) / 2;
        }

        return sum;
    }

    private static void update(int idx, int diff) {
        idx += s_idx;

        while (idx != 0) {
            indexTree[idx] += diff;
            idx >>= 1;
        }
    }
}