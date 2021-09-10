package B02_화_SegmentTree;

//문제
//히스토그램은 직사각형 여러 개가 아래쪽으로 정렬되어 있는 도형이다.
//각 직사각형은 같은 너비를 가지고 있지만, 높이는 서로 다를 수도 있다.
//예를 들어, 왼쪽 그림은 높이가 2, 1, 4, 5, 1, 3, 3이고
//너비가 1인 직사각형으로 이루어진 히스토그램이다.
//히스토그램에서 가장 넓이가 큰 직사각형을 구하는 프로그램을 작성하시오.
//
//입력
//입력은 테스트 케이스 여러 개로 이루어져 있다.
//각 테스트 케이스는 한 줄로 이루어져 있고, 직사각형의 수 n이 가장 처음으로 주어진다.
//(1 ≤ n ≤ 100,000) 그 다음 n개의 정수 h1, ..., hn
//(0 ≤ hi ≤ 1,000,000,000)가 주어진다.
//이 숫자들은 히스토그램에 있는 직사각형의 높이이며,
//왼쪽부터 오른쪽까지 순서대로 주어진다.
//모든 직사각형의 너비는 1이고,
//입력의 마지막 줄에는 0이 하나 주어진다.
//
//출력
//각 테스트 케이스에 대해서,
//히스토그램에서 가장 넓이가 큰 직사각형의 넓이를 출력한다.

import java.io.*;
import java.util.StringTokenizer;

//https://www.acmicpc.net/problem/6549
//https://steady-coding.tistory.com/129
public class No_05_6549_이해안됨 {

    static int[] arr, tree;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("No_6549.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        StringBuilder sb = new StringBuilder();

        while(true){
            st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());

            if(N == 0){
                break;
            }

            arr = new int[N+1];

            for(int i = 1; i<=N; i++){
                arr[i] = Integer.parseInt(st.nextToken());
            }

            tree = new int[N*4];
            init(1, 1, N);

            sb.append(getMax(1, N, N) + "\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    static int init(int node, int start, int end){
        if(start == end){
            return tree[node] = start;
        }else{
            int mid = (start+end)/2;

            init(node*2, start, mid);
            init(node*2+1, mid+1, end);

            if(arr[tree[node*2]] <= arr[tree[node*2+1]]){
                return tree[node] = tree[node*2];
            } else {
                return tree[node] = tree[node*2+1];
            }
        }
    }

    static int query(int node, int start, int end, int left, int right){
        if( right < start || end < left){
            return -1;
        }

        if(left <= start && end <= right){
            return tree[node];
        }

        int mid = (start+end)/2;
        int m1 = query(node*2, start, mid, left, right);
        int m2 = query(node*2+1, mid+1, end, left, right);

        if(m1 == -1){
            return m2;
        } else if (m2 == -1){
            return m1;
        } else {
            if (arr[m1] <= arr[m2]){
                return m1;
            } else {
                return m2;
            }
        }
    }

    static long getMax(int start, int end, int N){
        int m = query(1, 1, N, start, end);

        long area = (end - start +1) * (long)arr[m];

        if(start <= m-1){
            long tmp = getMax(start, m-1, N);

            area = Math.max(area, tmp);
        }

        if(m+1 <= end){
            long tmp = getMax(m+1, end, N);

            area = Math.max(area, tmp);
        }

        return area;
    }



}
