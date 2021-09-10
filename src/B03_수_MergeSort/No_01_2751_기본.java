package B03_수_MergeSort;

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
//https://www.youtube.com/watch?v=ctkuGoJPmAE
//https://do-rang.tistory.com/61
//https://www.acmicpc.net/problem/2751
public class No_01_2751_기본 {
    static int result[];

    public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("No_2751.txt"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine().trim());

        result = new int[N];
        int[] arr = new int[N];

        for(int i=0;i<N;i++) {
            arr[i] = Integer.parseInt(br.readLine().trim());
        }

        mergeSort(arr, 0,N-1);

        for(int i=0;i<N;i++) {
            sb.append(result[i] + "\n");
        }
        System.out.println(sb);
    }

    public static void mergeSort(int[] arr, int start, int end) {    // 숫자열을 가져와서 1개 단위로 쪼개는 행위
        int mid;

        if(start<end) {
            mid = (start+end)/2;
            mergeSort(arr, start,mid);    // 총 8개의 원소를 갖는 배열이 있다면, 맨 앞의 0번째 1번째 인덱스가 하나로 쪼개질 때까지 쪼갠다.
            mergeSort(arr, mid+1, end);
            merge(arr, start, mid, end);    // 다 쪼개면, 하나로 합친다.
        }
    }

    public static void merge(int[] arr, int start, int mid, int end) {
        int l = start;    // 쪼개진 첫 번째 배열의 첫 인덱스
        int r = mid+1;   // 쪼개진 두 번째 배열의 첫 인덱스
        int k = start;    // 새로 삽입될 배열의 첫 인덱스

        while(l<=mid || r<=end) {    // 쪼개진 두 배열의 원소를 비교해가면서 새로운 배열에다가 삽입한다.
            if(l<=mid && r<=end) {    // 두 배열 모두 비교가 필요한 원소가 남아있다면
                if(arr[l]<=arr[r]){
                    result[k] = arr[l];    // 첫 번째 배열과 두 번째 배열의 현재 인덱스를 비교, 더 작은 부분을 삽입한다.
                    l++;
                } else {
                    result[k] = arr[r];
                    r++;
                }
            } else if(l<=mid && r > end) {     // 두 번째 배열은 비교할 원소가 남아 있지 않은경우
                result[k] = arr[l++];
            } else {
                result[k] = arr[r++];        // 첫 번째 배열은 비교할 원소가 남아 있지 않은 경우
            }

            k++;
        }

        for(int i=start;i<=end;i++) {    // 쪼갠
            arr[i] = result[i];
        }
    }
}