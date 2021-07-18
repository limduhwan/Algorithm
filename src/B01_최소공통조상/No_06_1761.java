package B01_최소공통조상;

import java.io.*;
import java.util.StringTokenizer;

//https://www.acmicpc.net/problem/1761
//https://velog.io/@imfksh/%EB%B0%B1%EC%A4%80-1470-Java
public class No_06_1761 {

    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("No_1761.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        System.out.println(N);


    }
}
