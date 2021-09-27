package B02_화_SegmentTree;

import java.io.*;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

//10 3
//10
//20
//30
//40
//50
//60
//70
//80
//90
//100

public class 기본_No_K번째수 {

    static long[] segTree;
    static int MAX = 65535;
    static int N, K;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("No_9426_1.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[N+1];
        segTree = new long[MAX*4];
        ArrayDeque<Integer> que = new ArrayDeque<>();

        long result = 0;
        for (int i = 1; i <=N ; i++) {
            int now = Integer.parseInt(br.readLine());
            update(1, 1, MAX, now, 1);
            arr[i] = now;
        }

//        initSegTree(1, 1, N);
        System.out.println("1번째 수 " + search(1, 1, MAX, 1));
        System.out.println("2번째 수 " + search(1, 1, MAX, 2));
        System.out.println("3번째 수 " + search(1, 1, MAX, 3));
        System.out.println("4번째 수 " + search(1, 1, MAX, 4));
        System.out.println("5번째 수 " + search(1, 1, MAX, 5));
        System.out.println("6번째 수 " + search(1, 1, MAX, 6));
        System.out.println("7번째 수 " + search(1, 1, MAX, 7));
        System.out.println("8번째 수 " + search(1, 1, MAX, 8));
        System.out.println("9번째 수 " + search(1, 1, MAX, 9));
        System.out.println("10번째 수 " + search(1, 1, MAX, 10));

//        System.out.println(result);
    }

    static long search(int node, int start, int end, long k){
        if(start == end){
            return start;
        }

        int mid = (start+end)/2;

        if(k<=segTree[node*2]){
            return search(node*2, start, mid, k);
        }else{
            return search(node*2+1, mid+1, end, k-segTree[node*2]);
        }
    }

    static long update(int node, int start, int end, int index, int val){
        if( index < start || end < index){
            return segTree[node];
        }

        if( start == end ){
            return segTree[node] = segTree[node] + val;
        }

        int mid = (start+end)/2;

        return segTree[node] = update(node*2, start, mid, index, val)
                +update(node*2+1, mid+1, end, index, val);
    }
}

