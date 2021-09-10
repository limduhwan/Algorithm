package B07_수_BinarySearch;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

//https://www.acmicpc.net/problem/2805
//https://log-laboratory.tistory.com/68
//나무 자르기
public class No_02_2805 {
    static int N, M;
    static int[] arr;

     public static void main(String[] args) throws IOException{
//         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         BufferedReader br = new BufferedReader(new FileReader("No_2805.txt"));
         BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
         StringTokenizer st = new StringTokenizer(br.readLine());

         N = Integer.parseInt(st.nextToken());
         M = Integer.parseInt(st.nextToken());

         arr = new int[N];

         st = new StringTokenizer(br.readLine());

         //Array에 넣
         for(int i = 0; i<N; i++){
             arr[i] = Integer.parseInt(st.nextToken());
         }

        //오름차순 정렬
         Arrays.sort(arr);

//         for(int i = 0; i<arr.length; i++){
//             System.out.print(arr[i]+" ");
//         }

         int left =1;
         int right =arr[N-1];
         long height =0;
         int mid =0;

         while(left <= right){
             height =0;
             mid =(left+right)/2;

             for (int i = 0; i <N ; i++) {
                 if(arr[i]>=mid) {
                     height += (arr[i] -mid);
                 }
             }

             if(height >= M){
                 left = mid + 1;
             }else  if(height < M){
                 right = mid-1;
             }
         }
         System.out.println(right);
     }
}
