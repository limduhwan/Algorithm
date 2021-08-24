package B02_SegmentTree;

import java.io.*;
import java.util.StringTokenizer;

//(a, b)간이 주어졌을 때 최소값을 구하라
//세그먼트 트리
//https://www.youtube.com/watch?v=075fcq7oCC8
//https://www.youtube.com/watch?v=ahFB9eCnI6c
//https://steady-coding.tistory.com/127
//https://www.acmicpc.net/problem/10868
public class No_01_10868_기본 {
    static int N, M;
    static int[] arr, minTree;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("No_10868.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N+1];
        for(int i = 1; i<=N; i++){
            st = new StringTokenizer(br.readLine());
            arr[i] = Integer.parseInt(st.nextToken());
        }

//        for(int i = 1; i<=N; i++){
//         System.out.println(arr[i]);
//        }

        minTree = new int[N*4];

        minInit(1, 1, N);

        for(int i = 0; i<M; i++){
            st = new StringTokenizer(br.readLine());

            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());

            bw.write(minFind(1, 1, N, left, right) + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    static int minInit(int node, int start, int end){
        if(start == end){
            minTree[node] = arr[start];
        }

        int mid = (start+end) / 2;
        return minTree[node] = Math.min(minInit(node*2, start, mid), minInit(node*2+1, mid+1, end));
    }

    //    ***** Start, End를 중심으로 생각하자
    static int minFind(int node, int start, int end, int left, int right){
        if( right < start || end < left ){
            return Integer.MAX_VALUE;
        }

        if(left <= start && end <= right) {
            return minTree[node];
        }

        int mid = (start+end) / 2;
        return Math.min(minFind(node*2, start, mid, left, right), minFind(node*2+1, mid+1, end, left, right));
    }

}
