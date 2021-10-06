package B02_화_SegmentTree;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class 기본_BottomUp_No_01_min_max_기본원리_01번_반복중 {
    static int N;
    static int[] arr, tree;
    static int K, startIdx, treeN;
    static int start, end, max;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("No_BottomUp_01.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        arr = new int[N+1];
        max = -1;

        for (int i = 1; i <=N ; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        K = (int) Math.ceil(Math.log(N) / Math.log(2));

        startIdx = (int) Math.pow(2, K);

        treeN = (int) Math.pow(2, K+1)-1;

        tree = new int[treeN+1];

        for (int i = 1; i <=N ; i++) {
            updateTree(i, arr[i]);
        }

        query(start, end);

    }

    static void updateTree(int index, int num){
        index = startIdx + index -1;
        tree[index] = num;

        index = index/2;

        while(index > 0){
            tree[index] = Math.max(tree[index*2], tree[index*2+1]);
            index = index / 2;
        }
    }

    static void query(int start, int end){
        start = startIdx + start -1;
        end = startIdx + end -1;

        while(start<=end){
            if(start%2 == 1) {
                max = Math.max(tree[start], max);
            }

            if(end%2 == 0){
                max = Math.max(tree[end], max);
            }

            start = (start+1)/2;
            end = (end-1)/2;
        }
    }



}