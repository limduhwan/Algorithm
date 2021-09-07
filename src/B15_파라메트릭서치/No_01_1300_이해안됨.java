package B15_파라메트릭서치;

//문제
//세준이는 크기가 N×N인 배열 A를 만들었다.
// 배열에 들어있는 수 A[i][j] = i×j 이다.
// 이 수를 일차원 배열 B에 넣으면 B의 크기는 N×N이 된다.
// B를 오름차순 정렬했을 때, B[k]를 구해보자.
//
//배열 A와 B의 인덱스는 1부터 시작한다.
//
//입력
//첫째 줄에 배열의 크기 N이 주어진다.
// N은 105보다 작거나 같은 자연수이다.
// 둘째 줄에 k가 주어진다.
// k는 min(109, N2)보다 작거나 같은 자연수이다.
//
//출력
//B[k]를 출력한다.


import java.io.*;
import java.util.StringTokenizer;

//https://steady-coding.tistory.com/20
public class No_01_1300_이해안됨 {
    static int N, K;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("No_1300.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());

        long left = 1, right = K;
        long ans = 0;

        while(left <= right){
            long mid = (left+right)/2;
            long cnt = 0;

            for(int i=1; i<= N; i++){
                cnt = cnt + Math.min(mid/i, N);
            }

            if(cnt < K){
                left = mid+1;
            }else{
                ans = mid;
                right = mid -1;
            }
        }

        bw.write(ans+"\n");
        bw.flush();
        bw.close();
        br.close();

    }

    static int binarySearch(int left, int right){

        return 0;

    }
}
