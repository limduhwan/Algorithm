package B00_재귀;

import java.io.*;
import java.util.StringTokenizer;

//문제
//0보다 크거나 같은 정수 N이 주어진다.
// 이때, N!을 출력하는 프로그램을 작성하시오.
//
//입력
//첫째 줄에 정수 N(0 ≤ N ≤ 12)가 주어진다.
//
//출력
//첫째 줄에 N!을 출력한다.
public class No_01_10872 {
    static int N;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("No_10872.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
//        System.out.println(N);

        System.out.println(factorial(N));
    }

    static int factorial(int n){
        if(n == 1){
            return 1;
        }
        return n * factorial(n-1);
    }

}
