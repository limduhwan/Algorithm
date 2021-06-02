package B01_BinarySearch;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

//https://www.acmicpc.net/problem/10815
//숫자카드
public class No_01_10815 {
    static int n, m;
    static int arr[];

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("No_10815.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        arr = new int[n];

        StringTokenizer st = new StringTokenizer((br.readLine()));

        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        m = Integer.parseInt((br.readLine()));
        st = new StringTokenizer(br.readLine());

        for(int i = 0; i < m; i++){
            int num = Integer.parseInt(st.nextToken());

            if(binarySearch(num)) {
                bw.write("1 ");
            }else{
                bw.write("0 " );
            }
        }

        bw.close();
        br.close();

    }

    private static boolean binarySearch(int num) {
        int leftIndex = 0;
        int rightIndex = n-1;

        while(leftIndex <= rightIndex) {
            int midIndex = (leftIndex + rightIndex) / 2;
            int mid = arr[midIndex];

            if(num < mid){
                rightIndex = midIndex - 1;
            }else if(num > mid) {
                leftIndex = midIndex + 1;
            }else {
                return true;
            }
        }
        return false;
    }
}
