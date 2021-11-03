package B10_MergeSort;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

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
public class 기본_No_01_2751_03번_반복중 {

    static int N;
    static int[] arr, result;
    public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("No_2751.txt"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        arr = new int[N+1];
        result = new int[N+1];

        for (int i = 1; i <=N ; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        sortMerge(1, N);

        for (int i = 1; i <=N ; i++) {
            System.out.println(arr[i]);
        }
    }

    static void sortMerge(int start, int end){
        //*여기 주의 start == end 자기 자신(1개)면 더이상 쪼갤 게 없으니까
        if(start < end){
            int mid = (start+end)/2;

            sortMerge(start, mid);
            sortMerge(mid+1, end);

            merge(start, mid, end);
        }
    }

    static void merge(int start, int mid, int end){
        int l = start;
        int r = mid+1;
        int k = start;

        //*mid, end 기준으로 생각한다.
        while( l<= mid || end >= r ){
            if( l <= mid && end >= r){
                if(arr[l] <= arr[r]){
                    result[k] = arr[l];
                    l++;
                }else{
                    result[k] = arr[r];
                    r++;
                }

//                위에거나 아래거나 관계없다.
//                if(arr[l] >= arr[r]){
//                    result[k] = arr[r];
//                    r++;
//                }else{
//                    result[k] = arr[l];
//                    l++;
//                }

            }else if(l <= mid && end < r){
                result[k] = arr[l];
                l++;
            }else if(l > mid && end >= r){
                result[k] = arr[r];
                r++;
            }

            k++;
        }

        // i<=end 기존 배열을 새롭게 정렬된 값으로 변경
        for (int i = start; i <= end ; i++) {
            arr[i] = result[i];
        }

    }

}