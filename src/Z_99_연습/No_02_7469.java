package Z_99_연습;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class No_02_7469 {
    static int N, K;
    static int[][] data;
    static int[][] temp;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("No_7469.txt"));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        data = new int[N][2];
        temp = new int[N][2];

        st = new StringTokenizer(br.readLine());

        for(int i = 1; i <=N; i++){
            data[i-1][0] = Integer.parseInt(st.nextToken());
            data[i-1][1] = i;
        }

    }

    static void mergeSort(int start, int end) {
        if(start < end){
            int mid = start + (end - start) / 2;

            mergeSort(start, mid);
            mergeSort(mid+1, end);

            merge(start, mid, end);
        }

    }

    static void merge(int start, int mid, int end){
        for(int i = start; i<=end; i++){
            temp[i][0] = data[i][0];
            temp[i][1] = data[i][1];
        }

        int l = start;
        int r = mid +1;
        int k = start;




    }
}
