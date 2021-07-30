package Z_99_연습;

import java.io.*;

public class No_01_MergeSort_01 {
    static int[] result, arr;
    static int N;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("No_2751.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());

        result = new int[N];
        arr = new int[N];

        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        mergeSort(arr, 0, N-1);

        for(int i= 0; i<N; i++){
            sb.append(arr[i] + "\n");
        }

        System.out.println(sb);
    }

    static void mergeSort(int[] arr, int start, int end){
        if(start < end){
            int mid = (start+end)/2;
            mergeSort(arr, start, mid);
            mergeSort(arr, mid+1, end);

            merge(arr, start, mid, end);
        }
    }

    static void merge(int[] arr, int start, int mid, int end){
        int l = start;
        int r = mid+1;
        int k = start;

        while(l <= mid || r <= end){
            if(l <= mid && r <= end) {
                if(arr[l] <= arr[r]){
                    result[k] = arr[l];
                    l++;
                }else{
                    result[k] = arr[r];
                    r++;
                }
            } else if( l > mid && r <= end){
                result[k] = arr[r];
                r++;
            } else if(l <=mid && r > end) {
                result[k] = arr[l];
                l++;
            }

            k++;
        }

        System.out.println(start +"  " +end);

        for(int i=start;i<=end;i++) {
            arr[i] = result[i];
        }
    }

}
