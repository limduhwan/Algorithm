package B15_파라메트릭서치;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

//https://namhandong.tistory.com/199
//https://www.crocus.co.kr/1000
public class No_02_1477_이해안됨 {
    static int N; //현재 고속도로 휴게소 개수
    static int M; //더 세우려는 휴게소 개수
    static int L; //고속도로 길이
    static int left, right;

    static int[] arr;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("No_1477.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        arr = new int[N+2];
        st = new StringTokenizer(br.readLine());

        for(int i=1; i<=N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        arr[0] = 0;
        arr[N+1] = L;

        Arrays.sort(arr);

        binarySearch();
        System.out.println(left);
    }

    static void binarySearch(){
        left = 0;
        right = 0;

        for(int i=1; i<=N+1; i++) {
            right = Math.max(right, arr[i]-arr[i-1]+1);
        }

        while(left <=right){
            int mid = (left+right)/2;
            int sum = 0;

            for(int i=1; i<N+2; i++){
                sum = sum + (arr[i]-arr[i-1]-1)/mid;
            }

            if(sum > M){
                left = mid +1;
            }else{
                right = mid -1;
            }

        }
    }
}
