package B02_SegmentTree;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

//주어진 구간의 최대, 최소값을 동시에 찾는 문제
//https://www.acmicpc.net/problem/2357
public class No_02_2357_min_max {
    static int N, M;
    static int[] minTree, maxTree, arr;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("No_2357.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N+1];
        minTree = new int[N*4];
        maxTree = new int[N*4];

//        **여기서 주의! 반드시 1부터
        for(int i=1; i<=N; i++){
//            System.out.println(Integer.parseInt(br.readLine()));
            arr[i] = Integer.parseInt(br.readLine());
        }

        initMinTree(1, 1, N);
        initMaxTree(1, 1, N);

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
//            System.out.println(a+"   "+b);

            System.out.println(getMin(1, 1, N, a, b) +" "+getMax(1, 1, N, a, b));
        }
    }

    static int getMin(int node, int start, int end, int left, int right){
        if( right < start || end < left ){
            return Integer.MAX_VALUE;
        }

//        여기주의 &&
        if( left <= start && end <= right){
            return minTree[node];
        }

        int mid = (start+end)/2;
        return Math.min(getMin(node*2, start, mid, left, right), getMin(node*2+1, mid+1, end, left, right));
    }

    static int getMax(int node, int start, int end, int left, int right){
        if( right < start || end < left ){
            return Integer.MIN_VALUE;
        }

//        여기주의 &&
        if( left <= start && end <= right){
            return maxTree[node];
        }

        int mid = (start+end)/2;
        return Math.max(getMax(node*2, start, mid, left, right), getMax(node*2+1, mid+1, end, left, right));
    }

    static int initMinTree(int node, int start, int end){
        if(start == end){
            return minTree[node] = arr[start];
        }

        int mid = (start+end)/2;
        return minTree[node] = Math.min(initMinTree(node*2, start, mid), initMinTree(node*2+1, mid+1, end));
    }

    static int initMaxTree(int node, int start, int end){
        if(start == end){
            return maxTree[node] = arr[start];
        }

        int mid = (start+end)/2;
        return maxTree[node] = Math.max(initMaxTree(node*2, start, mid), initMaxTree(node*2+1, mid+1, end));
    }


}
