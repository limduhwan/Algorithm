package Z_합격_최적화_문제집;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

//정렬문제. 단순하게 생각하면 시간초과
//https://www.acmicpc.net/problem/2751
public class No_Z0_2751 {

    static int[] arr;
    static int N;
    static ArrayList<Integer> al;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("No_2751.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        arr = new int[N];
        al = new ArrayList<>();

        for(int i=0; i<N; i++){
//            arr[i] = Integer.parseInt(br.readLine());
            al.add(Integer.parseInt(br.readLine()));
        }

        Collections.sort(al);

        for(int value: al){
            sb.append(value).append('\n');
        }

        System.out.println(sb);

    }
}
