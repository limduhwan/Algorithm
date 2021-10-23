package B03_수_MergeSort;
//문제
//길이가 N인 수열 A1, A2, ..., AN이 주어진다.
// 이때, 다음 쿼리를 수행하는 프로그램을 작성하시오.
//
//i j k: Ai, Ai+1, ..., Aj로 이루어진 부분 수열 중에서
//k보다 큰 원소의 개수를 출력한다.

//입력
//첫째 줄에 수열의 크기 N (1 ≤ N ≤ 100,000)이 주어진다.
//
//둘째 줄에는 A1, A2, ..., AN이 주어진다. (1 ≤ Ai ≤ 109)
//
//셋째 줄에는 쿼리의 개수 M (1 ≤ M ≤ 100,000)이 주어진다.
//
//넷째 줄부터 M개의 줄에는 a, b, c가 주어진다. a, b, c를 이용해 쿼리를 만들어야 한다.
//
//i = a xor last_ans
//j = b xor last_ans
//k = c xor last_ans
//last_ans는 이전 쿼리의 정답이며, 가장 처음에는 0이다.
//xor한 결과는 1 ≤ i ≤ j ≤ n, 1 ≤ k ≤ 109 을 만족한다.
//
//출력
//각각의 쿼리마다 정답을 한 줄에 하나씩 출력한다.


import java.io.*;
import java.util.StringTokenizer;

//https://www.acmicpc.net/problem/13544
public class No_16_13544_안품 {
    static int last_ans, n;
    static int[] arr;
    static int[][] tree;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("No_13544.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        arr = new int[n+1];

        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int h = (int)Math.ceil(Math.log(n)/Math.log(2))+1;

    }

}
