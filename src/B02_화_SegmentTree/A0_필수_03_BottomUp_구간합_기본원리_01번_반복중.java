package B02_화_SegmentTree;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class A0_필수_03_BottomUp_구간합_기본원리_01번_반복중 {
    static int N;
    static int[] arr, tree;
    static int K, startIdx, treeLength;
    static int start, end, sum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("No_BottomUp_01.txt"));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new int[N+1];
        sum = 0;

        for (int i = 1; i <=N ; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

//        KSLT

        K = (int) Math.ceil(Math.log(N)/Math.log(2));

        startIdx = (int) Math.pow(2, K);

        treeLength = (int) Math.pow(2, K+1);

        tree = new int[treeLength+1];

        for (int i = 1; i <=N ; i++) {
            updateTree(i, arr[i]);
        }

//        query(start, end);
        query(2, 4);
        System.out.println(sum);
    }

    static void query(int start, int end){
        start = startIdx + start - 1;
        end = startIdx + end -1;

        while(start <= end){
            if(start%2 == 1){
                sum = sum + tree[start];
            }

            if(end%2 == 0){
                sum = sum + tree[end];
            }

            start = (start+1)/2;
            end = (end-1)/2;
        }
    }

    static void updateTree(int index, int value){
        index = startIdx + index -1;
        tree[index] = value;
        index = index / 2;

        while(index > 0){
            tree[index] = tree[index*2] + tree[index*2+1];
            index = index / 2;
        }
    }
}