package B10_MergeSort;

import java.io.*;

//단순히 정렬이 아니라 MergeSort를 구현할 수 있는가에 대한 문제다
//https://www.youtube.com/watch?v=ctkuGoJPmAE
//https://do-rang.tistory.com/61
//https://www.acmicpc.net/problem/2751
public class No_17_2751 {
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
            sb.append(arr[i] + "\n");
        }
        System.out.println(sb);
    }

    public static void mergeSort(int[] arr, int start, int end) {    // 숫자열을 가져와서 1개 단위로 쪼개는 행위
        if(start<end) {
            int mid = (start+end)/2;
            mergeSort(arr, start, mid);    // 총 8개의 원소를 갖는 배열이 있다면, 맨 앞의 0번째 1번째 인덱스가 하나로 쪼개질 때까지 쪼갠다.
            mergeSort(arr, mid+1, end);

            //2개 단위로 쪼개졌으면 합친다.
            merge(arr, start, mid, end);    // 다 쪼개면, 하나로 합친다.
        }
    }

    public static void merge(int[] arr, int start, int mid, int end) {
        int l = start;    // 쪼개진 첫 번째 배열의 첫 인덱스
        int r = mid+1;   // 쪼개진 두 번째 배열의 첫 인덱스
        int k = start;    // 새로 삽입될 배열의 첫 인덱스

//        l, r을 기준으로 생각한다.
        while(l <= mid || r <= end) {    // 쪼개진 두 배열의 원소를 비교해가면서 새로운 배열에다가 삽입한다.
            if(l<=mid && r<=end) {    // 두 배열 모두 비교가 필요한 원소가 남아있다면
                if(arr[l]<=arr[r]){
                    result[k] = arr[l];    // 첫 번째 배열과 두 번째 배열의 현재 인덱스를 비교, 더 작은 부분을 삽입한다.
                    l++;
                } else {
                    result[k] = arr[r];
                    r++;
                }
            } else if(l > mid && r <= end){    // 첫 번째 배열은 비교할 원소가 남아 있지 않은 경우
                result[k] = arr[r];
                r++;
            } else if(l <= mid && r > end) {     // 두 번째 배열은 비교할 원소가 남아 있지 않은경우
                result[k] = arr[l];
                l++;
            }

            k++;
        }

        //start ~ end까지 원래 배열로 복사해 둔다.
        //정렬된 원래 배열이 다시 사용되니까
        for(int i=start; i<=end; i++) {
            arr[i] = result[i];
        }
    }
}