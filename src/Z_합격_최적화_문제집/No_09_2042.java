package Z_합격_최적화_문제집;

import java.io.*;
import java.util.StringTokenizer;

//세그먼트 트리 업데이트를 구현할 수 있는가?
//https://steady-coding.tistory.com/124
public class No_09_2042 {
    static int N, K, M;
//    **여기서 주의
    static long[] tree, segTree;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("No_2042.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st;
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

//        System.out.println(N +" "+ K +" " + M);
        tree = new long[N+1];
        segTree = new long[N*4];

        for(int i=1; i<=N; i++){
            tree[i] = Integer.parseInt(br.readLine());
        }

        initSegTree(1, 1, N);

        for(int i = 0; i<K+M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Integer.parseInt(st.nextToken());

//            System.out.println(a +" "+ b +" " + c);

            if(a == 1){
                long diff =  c - tree[b];
                tree[b] = c;
                updateSegTree(1, 1,  N,  b,  diff);
            } else if(a==2){
                System.out.println(sumSegTree(1, 1, N, b, (int) c));
            }
        }



    }

    static long initSegTree(int node, int start, int end){
        if(start == end){
            //**여기서 주의 tree start
            return segTree[node] = tree[start];
        }

        int mid = (start+end)/2;
        return segTree[node] = initSegTree(node*2, start, mid) + initSegTree(node*2+1, mid+1, end);
    }

    static void updateSegTree(int node, int start, int end, int index, long diff){
        if( index < start || end < index) {
            return;
        }

        segTree[node] = segTree[node] + diff;
        if(start == end){
            return;
        }

        int mid = (start+end)/2;
        updateSegTree(node*2, start, mid, index, diff);
        updateSegTree(node*2+1, mid+1, end, index, diff);
    }

    static long sumSegTree(int node, int start, int end, int left, int right){
        if( right < start || end < left){
            return 0;
        }

        if( left<=start && end <=right){
            return segTree[node];
        }

        int mid = (start+end)/2;
        return sumSegTree(node*2, start, mid, left, right) + sumSegTree(node*2+1, mid+1, end, left, right);
    }
}
