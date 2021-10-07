package B02_화_SegmentTree;

import java.io.*;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

//시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
//1 초	256 MB	8360	1441	978	24.024%
//문제
//기상학에서 주요 사용하는 대표값은 중앙값이다.
//(중앙값의 정의는 힌트에 나와있다)
//
//상근이는 1초에 한 번씩 온도를 재는 기계를 가지고 있고,
// 이 기계에 들어갈 소프트웨어를 작성하려고 한다.
// 기계에는 작은 디지털 디스플레이가 하나 달려있다.
// 매 초마다 디스플레이는 지난 K초동안 측정한 온도의 중앙값을 화면에 보여준다.
//
//상근이는 소프트웨어를 기계에 올리기 전에 컴퓨터에서 테스트해보려고 한다.
//
//총 N초 동안 측정한 온도가 주어졌을 때,
// 디스플레이에 표시된 중앙값의 합을 구하는 프로그램을 작성하시오.
// 즉, N개의 수가 주어졌을 때,
// 길이가 K인 연속 부분 수열 N-K+1개의
// 중앙값의 합을 구하는 프로그램을 작성하시오.
//
//입력
//첫째 줄에 N과 K가 주어진다.
// (1 ≤ N ≤ 250,000, 1 ≤ K ≤ 5,000, K ≤ N)
//
//둘째 줄부터 N개 줄에 측정한 온도가 순서대로 주어진다.
// 온도는 0보다 크거나 같고, 65535보다 작거나 같은 정수이다.
//
//출력
//길이가 K인 모든 연속 부분 수열의 중앙값의 합을 출력한다.

//10 3
//3
//4
//5
//6
//7
//8
//9
//10
//11
//12

//60

// 백준 9426번 중앙값 측정
// https://www.acmicpc.net/problem/9426
public class 기본_BottomUp_No_03_K번째수_기본원리 {
    private static final int MAX = 65535;
    private static int[] indexTree; // 인덱스 트리 데이터 저장
    private static int s_idx;
    static int K, startIdx, treeN;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("No_9426.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int Kx = Integer.parseInt(st.nextToken());

        // 트리의 Depth K
        K = (int) Math.ceil(Math.log(N) / Math.log(2));

        // startIndex
        startIdx = (int) Math.pow(2, K);

        // 트리 배열의 Length
        treeN = (int) Math.pow(2, K + 1) - 1;

        // 트리 배열 생성
        indexTree = new int[treeN + 1];

        ArrayDeque<Integer> q = new ArrayDeque<>();

        long result = 0;

        for (int i = 1; i <= N; i++) {
            int now = Integer.parseInt(br.readLine());
            update(now, 1);
            q.offer(now);
            if (i >= Kx) {
                int temp = search((Kx+1) / 2);
                System.out.println(temp);
                result += search((Kx + 1) / 2);
                int poll = q.poll();
                update(poll, -1);
            }
        }

//        bw.write(result + "\n");
        bw.flush();
    }

    // 인덱스 트리
    private static void update(int idx, int diff) {
        idx = startIdx + idx-1;

        indexTree[idx] += diff;

        idx = idx/2;

        while (idx != 0) {
            indexTree[idx] += diff;
            idx = idx / 2;
        }
    }

    static int search(int k){
        int idx = 1;
        while(idx < startIdx){

            if(k <= indexTree[idx*2]){
                idx = idx*2;
            }else{
                k = k - indexTree[idx*2];
                idx = idx*2+1;
            }
        }

        return idx - startIdx + 1;
    }
}