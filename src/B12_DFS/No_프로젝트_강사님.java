package B12_DFS;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
중요!!! : 주석 삭제 금지 / 동일 코드 SW검정 사이트 제출 금지

김희성 프로(SW역량강화TF)
알고리즘 유형 : DFS

풀이
1. DFS 탐색을 하면서 하위 트리들 중에서
능력치의 최대값을 가지는 트리를 구한다.

2. DFS 탐색을 하면서 모든 정점에서 1에서 구한 능력치의 최대값에 따라
가장 큰 능력치와 두번째로 큰 능력치의 합이 MAX 인 값을 찾는다.

죠르디는 사랑입니다♥
 */

//2
//7
//0
//1 1
//1 -3
//2 1
//2 1
//3 7
//3 -3
//7
//0
//1 1
//1 3
//2 1
//2 1
//3 7
//3 -3
//
//
//
//
//(출력)
//#1 10
//#2 10


public class No_프로젝트_강사님 {
    private static ArrayList<Integer>[] adjList;
    private static int[] stat; // 능력치
    private static long[] sum; // 각 트리와 그 하위 트리 중에서 능력치가 가장 큰 값 저장
    private static long result;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("No_프로젝트.txt"));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(br.readLine());

            result = Long.MIN_VALUE;
            stat = new int[N + 1];
            sum = new long[N + 1];
            adjList = new ArrayList[N + 1];

            for (int i = 1; i <= N; i++) {
                adjList[i] = new ArrayList<>();
            }

            stat[1] = Integer.parseInt(br.readLine());

            for (int i = 2; i <= N; i++) {
                st = new StringTokenizer(br.readLine());
                int p = Integer.parseInt(st.nextToken());
                adjList[p].add(i);
                stat[i] = Integer.parseInt(st.nextToken());
            }

            dfs(1); // 루트는 항상 1번 (1번 직원은 선임이 없다)

            System.out.println("#" + tc + " " + result);
        }
    }

    private static long dfs(int now) {
        // now_sum = 전체합을 저장
        long now_sum = stat[now];
        // 하위 트리 중에서 가장 능력치가 큰 값을 구하기 위한 변수
        long max = Long.MIN_VALUE;
        // 하위 트리에서 능력치가 가장 큰 트리의 값, 두번째로 큰 트리의 값
        long first = Long.MIN_VALUE, second = Long.MIN_VALUE;

        for (int i : adjList[now]) {
            now_sum += dfs(i);
            max = Math.max(max, sum[i]);

            if (sum[i] >= first) {
                second = first;
                first = sum[i];
            } else if (sum[i] > second) {
                second = sum[i];
            }

            // 겹치지 않게 2개의 팀을 구성해야한다. 따라서 second 가 Long.MIN_VALUE 보다 커야한다.
            if (second > Long.MIN_VALUE)
                result = Math.max(result, first + second);
        }

        // 현재 트리, 하위 트리들 중에서 능력치의 합이 가장 큰 값을 저장
        sum[now] = Math.max(now_sum, max);
        // 하위 노드의 전체 합을 리턴
        return now_sum;
    }
}