package B12_DFS;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.OutputStreamWriter;
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


public class No_프로젝트_내거 {
    static int T, InwonSu, FirstPower;
    static int[][] arr;
    static int[] powerArr;
    static int[] powerSumArr;
    static int[] depth;
    static boolean[] isVisited;
    static int Result;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("No_프로젝트.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());
        InwonSu = Integer.parseInt(br.readLine());
        FirstPower = Integer.parseInt(br.readLine());

        arr = new int[InwonSu+1][InwonSu+1];
        powerArr = new int[InwonSu+1];
        depth = new int[InwonSu+1];
        isVisited = new boolean[InwonSu+1];
        powerSumArr = new int[InwonSu+1];
        Result = 0;

        powerArr[1] = FirstPower;

        for (int i = 2; i <=InwonSu ; i++) {
            st = new StringTokenizer(br.readLine());
            int parentNode = Integer.parseInt(st.nextToken());
            int power = Integer.parseInt(st.nextToken());

            arr[i][parentNode] = arr[parentNode][i] = 1;
            powerArr[i] = power;
        }

        bfs(1, 0);

    }

    static void bfs(int start, int dep){
        depth[start] = dep;
        isVisited[start] = true;

        int first = Integer.MIN_VALUE;
        int second = Integer.MIN_VALUE;

        for (int i = 1; i <=InwonSu ; i++) {
            if(arr[start][i] == 1 && !isVisited[i]){
                bfs(i, dep++);

                if(first == Integer.MIN_VALUE){
                    first = powerArr[i];
                }else if(first > Integer.MIN_VALUE){
                    second = powerArr[i];
                }

                if(second > Integer.MIN_VALUE){
                    Result = Math.max(Result, first + second + powerArr[i]);
                }
//                System.out.println(i + " " + powerArr[i]);
            }
        }
    }
}
