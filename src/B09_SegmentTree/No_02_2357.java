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
            update(1, 1, N, i, input[i]);
        }

        init(input, 1, 1, N);

        for(int i = 0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            long max = query(1, 1, N, a, b);
            long min = queryMin(1, 1, N, a, b);
            bw.write(min+" "+max+"\n");
        }
        bw.flush();
    }

    static void init(long[] arr, int node, int start, int end){
        if(start == end) {
            segTreeMin[node] = segTree[node] = arr[start];
            return;
        }

        int mid = (start+end)/2;
        init(arr, node*2, start, mid);
        init(arr, node*2+1, mid+1, end);
        segTree[node] = Math.max(segTree[node*2], segTree[node*2+1]);
        segTreeMin[node] = Math.min(segTreeMin[node*2], segTreeMin[node*2+1]);
    }

    static void update(int node, int start, int end, int index, long diff){
        if(index < start || index > end){
            return;
        }

        if(start == end){
            segTreeMin[node] = segTree[node] = diff;
            return;
        }

        int mid = (start+end) / 2;

        update(node*2, start, mid, index, diff);
        update(node*2+1, mid+1, end, index, diff);

        segTree[node] = Math.max(segTree[node*2], segTree[node*2+1] );
        segTreeMin[node] = Math.min(segTreeMin[node*2+1], segTreeMin[node*2+1] );
    }

    static long query(int node, int start, int end, int left, int right){
        if(right < start || left > end) {
            return 0;
        }

        if(left <= start && end <= right){
            return segTree[node];
        }

        int mid = (start + end) /2;

        return Math.max(query(node*2, start, mid, left, right), query(node*2+1, mid+1, end, left, right));
    }

    static long queryMin(int node, int start, int end, int left, int right){
        if(right < start || left > end){
            return Integer.MAX_VALUE;
        }

        if(left <= start && end <= right){
            return segTreeMin[node];
        }

        int mid = (start+end) / 2;

        return Math.min(queryMin(node*2, start, mid, left, right), queryMin(node*2+1, mid+1, end, left, right));
    }

}
