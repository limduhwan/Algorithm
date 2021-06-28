package B09_SegmentTree;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

//최소값과 최대값
public class No_02_2357 {

    static long[] segTree;
    static long[] segTreeMin;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("No_2357.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        segTree = new long[N*4];
        segTreeMin = new long[N*4];

        Arrays.fill(segTreeMin, Integer.MAX_VALUE);

        long[] input = new long[N+1];

        for(int i = 1; i<=N; i++){
            input[i]  = Integer.parseInt(br.readLine());
        }


    }

    static void init(long[] arr, int node, int start, int end){
        if(start == end) {
            segTreeMin[node] = segTree[node] = arr[start];
        }

        int mid = (start+end)/2;
        init(arr, node*2, start, mid);
        init(arr, node*2+1, mid+1, end);
        segTree[node] = Math.max(segTree[node*2], segTree[node*2+1]);
        segTreeMin[node] = Math.min(segTreeMin[node*2], segTreeMin[node*2+1]);
    }


}
