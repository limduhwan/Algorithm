package B10_MergeSort;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

//문제
//N개의 수가 주어졌을 때, 이를 오름차순으로 정렬하는 프로그램을 작성하시오.
//
//입력
//첫째 줄에 수의 개수 N(1 ≤ N ≤ 1,000,000)이 주어진다. 둘째 줄부터 N개의 줄에는 수가 주어진다.
//이 수는 절댓값이 1,000,000보다 작거나 같은 정수이다. 수는 중복되지 않는다.
//
//출력
//첫째 줄부터 N개의 줄에 오름차순으로 정렬한 결과를 한 줄에 하나씩 출력한다.

//단순히 정렬이 아니라 MergeSort를 구현할 수 있는가에 대한 문제다

//5 => N 수의 개수
//5
//4
//3
//2
//1
//https://www.youtube.com/watch?v=ctkuGoJPmAE
//https://do-rang.tistory.com/61
//https://www.acmicpc.net/problem/2751
public class 기본_No_01_2751_02번_반복중 {

    static int[] arr, result;
    public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("No_2751.txt"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        arr = new int[N+1];
        result = new int[N+1];

        for (int i = 1; i <=N ; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        mergeSort(1, N);

        for (int i = 1; i <=N ; i++) {
            sb.append(result[i] + "\n");
        }

        System.out.println(sb);

    }

    static void mergeSort(int start, int end){
        int mid;

        if(start < end){
            mid = (start+end)/2;
            mergeSort(start, mid);
            mergeSort(mid+1, end);

            merge(start, mid, end);
        }

    }

    static void merge(int start, int mid, int end){
        //lrk
        //while
        //for
        int l = start;
        int r = mid+1;
        //*여기 주의
        int k = start;

        while (l <=mid || r<=end){
            if(l <=mid && r<=end){
                //*여기 주의
                if(arr[l] <= arr[r]){
                    result[k] = arr[l];
                    l++;
                }else{
                    result[k] = arr[r];
                    r++;
                }
            } else if(l <=mid && r > end){
                result[k] = arr[l];
                l++;
            } else {
                result[k] = arr[r];
                r++;
            }

            k++;
        }

        //*여기 주의
        for (int i = start; i <=end ; i++) {
            arr[i] = result[i];
        }


    }



}