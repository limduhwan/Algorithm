package Z_02_SDS_프로_문제풀이.세그먼트트리;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

//3                                               ← 3 test cases in total
//10 3                                            ← 1st case
//175 182 178 179 170 179 171 185 185 181
//3 7 175
//1 10 180
//1 10 179
//7 5                                            ← 2nd case
//183 176 175 183 174 182 186
//1 4 176
//2 6 177
//1 7 180
//1 7 160
//5 7 180
//2 2                                            ← 3rd case
//161 168
//1 2 175
//1 2 188
//
//
//
//
//
//(출력)
//
//#1 3 4 4
//#2 2 2 4 7 2
//#3 0 0


// 구간트리 (인덱스 트리, 세그먼트 트리)
public class No_03_키컸으면_세그먼트트리_강사님 {
    // Input (키)를 저장하기 위한 클래스
    // 키 순으로 정렬이 필요하며, 정렬된 데이터의 순서(idx)가 Pair 로 움직여야함
    private static class Input implements Comparable<Input> {
        int idx; // 입력받은 순서
        int height; // 키
        public Input(int idx, int height) {
            this.idx = idx;
            this.height = height;
        }

        // 내림차순 정렬
        @Override
        public int compareTo(Input o) {
            return Integer.compare(o.height, this.height);
        }
    }

    // Query 를 저장하기 위한 클래스
    // 키 순으로 정렬이 필요하며, 정렬된 데이터의 순서(idx)가 Pair 로 움직여야함
    private static class Query implements Comparable<Query> {
        int idx; // 입력받은 순서
        int start; // 찾고자하는 범위 Start
        int end; // 찾고자하는 범위 end
        int height; // 키

        public Query(int idx, int start, int end, int height) {
            this.idx = idx;
            this.start = start;
            this.end = end;
            this.height = height;
        }

        // 내림차순 정렬
        @Override
        public int compareTo(Query o) {
            return Integer.compare(o.height, this.height);
        }
    }

    private static int[] segTree; //세그먼트 트리 데이터 저장
    private static int[] result; // 쿼리 결과 값을 index 를 통해 저장
    private static Input[] inputs; // 키 입력
    private static Query[] querys; // 질문 입력

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("No_키컸으면.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int Q = Integer.parseInt(st.nextToken());

            inputs = new Input[N];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                // i 를 인덱스로 저장한다. 순서를 저장함
                inputs[i] = new Input(i + 1, Integer.parseInt(st.nextToken()));
            }

            // 키 순으로 정렬
            Arrays.sort(inputs);
            querys = new Query[Q];

            for (int i = 0; i < Q; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int h = Integer.parseInt(st.nextToken());

                // i 를 인덱스로 저장한다. 순서를 저장함
                querys[i] = new Query(i, a, b, h);
            }

            // 쿼리 키 순으로 정렬
            Arrays.sort(querys);

            // 세그먼트 트리 구현을 위한 필요 크기는 대략 N * 4
            segTree = new int[N * 4];

            // 질의 결과 값 저장을 위한 배열 Q 의 크기만큼
            result = new int[Q];

            // 정렬된 키를 iteration 할 index
            int idx = 0;

            // 질의 수 만큼 반복문 수행
            for (int i = 0; i < Q; i++) {
                // 키가 큰 순서로 질의의 키보다 input 의 키가 크면 해당 세그먼트 트리에 1을 더함
                // -> 질의보다 키가 큰 사람이 1명 있다는 뜻
                // i 번째 질의보다 input 값의 키가 큰 사람 수 만큼 update 해준다.
                while (idx < N && querys[i].height < inputs[idx].height) {
                    update(0, N, 1, inputs[idx].idx, 1);
                    idx++;
                }

                // 질의 수행 while 문에서 질의보다 키가 큰 사람의 수가 모두 세그먼트 트리에 저장되었기 때문에
                // 질의 구간에 저장된 데이터가 질의의 답이 된다.
                // 질의 순으로 출력을 위해 질의의 idx 를 result 배열의 첨자로 사용하여 저장한다.

                result[querys[i].idx] = query(querys[i].start, querys[i].end, 1, 0, N);
            }

            bw.write("#" + tc);

            for (int i = 0; i < Q; i++) {
                bw.write(" " + result[i]);
            }
            bw.newLine();
            bw.flush();
        }
    }

    // 세그먼트 트리
    private static void update(int start, int end, int node, int index, int dif) {
        if (index < start || index > end) {
            return;
        }

        segTree[node] += dif;

        if (start == end) {
            return;
        }

        int mid = (start + end) / 2;
        update(start, mid, node * 2, index, dif);
        update(mid + 1, end, node * 2 + 1, index, dif);
    }

    private static int query(int start, int end, int node, int left, int right) {
        if (start > right || end < left) {
            return 0;
        }

        if (start <= left && right <= end) {
            return segTree[node];
        }

        int mid = (left + right) / 2;
        return query(start, end, node * 2, left, mid) +
                query(start, end, node * 2 + 1, mid + 1, right);
    }
}