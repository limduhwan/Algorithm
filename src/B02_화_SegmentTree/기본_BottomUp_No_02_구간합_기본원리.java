package B02_화_SegmentTree;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class 기본_BottomUp_No_02_구간합_기본원리 {
    static int N;
    static int[] arr, tree;
    static int K, startIdx, treeN;
    static int start, end, max;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("No_BottomUp_01.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        arr = new int[N + 1];
        max = -1;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        // 트리의 Depth
        K = (int) Math.ceil(Math.log(N) / Math.log(2));

        // startIndex
        startIdx = (int) Math.pow(2, K);

        // 트리 배열의 Length
        treeN = (int) Math.pow(2, K + 1) - 1;

        // 트리 배열 생성
        tree = new int[treeN + 1];

        // 트리배열 초기화
        for (int i = 1; i <= N; i++) {
            updateTree(i, arr[i]);
        }

        System.out.println(query(1, 3));

//        System.out.println(search(75));

        System.out.println(search_(1, 1, N, 105));
    }

    static long search_(int node, int start, int end, long k){
        if(start == end){
            return start;
        }

        int mid = (start+end)/2;

        if(k<=tree[node*2]){
            return search_(node*2, start, mid, k);
        }else{
            return search_(node*2+1, mid+1, end, k-tree[node*2]);
        }
    }

    public static void updateTree(int index, int num) {
        index = startIdx + index - 1;
        tree[index] = num;

        index = index / 2;

        while (index > 0) {
            tree[index] = tree[index * 2] + tree[index * 2 + 1];
            index = index / 2;
        }
    }


    static long query(int start, int end) {
        long sum = 0;
        start = startIdx + start - 1;
        end = startIdx + end - 1;

        while (start <= end) {
            if (start % 2 == 1) {
                sum = sum + tree[start];
            }

            if (end % 2 == 0) {
                sum = sum + tree[end];
            }

            start = (start + 1) / 2;
            end = (end - 1) / 2;
        }

        return sum;
    }

    static int search(int index){
        int current = 1;
        while(current < startIdx){

            if(tree[current*2] >= index){
                current = current*2;
            }else{
                index = index - tree[current*2];
                current = current*2+1;
            }
        }

        return current - startIdx + 1;
    }
}