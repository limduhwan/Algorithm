package B02_화_SegmentTree;

//문제
//어떤 N개의 수가 주어져 있다.
// 그런데 중간에 수의 변경이 빈번히 일어나고 그 중간에 어떤 부분의 곱을 구하려 한다.
// 만약에 1, 2, 3, 4, 5 라는 수가 있고,
// 3번째 수를 6으로 바꾸고 2번째부터 5번째까지 곱을 구하라고 한다면 240을 출력하면 되는 것이다.
// 그리고 그 상태에서 다섯 번째 수를 2로 바꾸고
// 3번째부터 5번째까지 곱을 구하라고 한다면 48이 될 것이다.
//
//입력
//첫째 줄에 수의 개수 N(1 ≤ N ≤ 1,000,000)과 M(1 ≤ M ≤ 10,000), K(1 ≤ K ≤ 10,000) 가 주어진다.
// M은 수의 변경이 일어나는 횟수이고, K는 구간의 곱을 구하는 횟수이다.
// 그리고 둘째 줄부터 N+1번째 줄까지 N개의 수가 주어진다.
// 그리고 N+2번째 줄부터 N+M+K+1 번째 줄까지 세 개의 정수 a,b,c가 주어지는데,
// a가 1인 경우 b번째 수를 c로 바꾸고
// a가 2인 경우에는 b부터 c까지의 곱을 구하여 출력하면 된다.
//
//입력으로 주어지는 모든 수는 0보다 크거나 같고, 1,000,000보다 작거나 같은 정수이다.
//
//출력
//첫째 줄부터 K줄에 걸쳐 구한 구간의 곱을 1,000,000,007로 나눈 나머지를 출력한다.

import java.io.*;
import java.util.StringTokenizer;

//https://www.acmicpc.net/problem/11505
//https://wellbell.tistory.com/57
public class 기본_TopDown_No_03_11505_update_01번_반복중 {
    static int N, K, M;
    static int[] arr;
    static long[] tree;
    static int d = 1000000007;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("No_11505.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

//        System.out.println(N +" "+ M +" "+ K);

        arr = new int[N+1];
        tree = new long[N*4];

        for (int i = 1; i <=N ; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        initTree(1, 1, N);

//        for (int j = 1; j <=N ; j++) {
//            System.out.println(tree[j]);
//        }

//        System.out.println("============================");

        for (int i = 0; i <M+K ; i++) {
//        for (int i = 0; i <1 ; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if(a == 1){
//                System.out.println("b, c " + b +" " + c);
                //update
                updateTree(1, 1, N, b, c);

//                for (int j = 1; j <=N ; j++) {
//                    System.out.println(tree[j]);
//                }

            }else{
                //구간곱
                long aaa = query(1, 1, N, b, c);

                System.out.println(aaa);
            }
        }

    }

    static long query(int node, int start, int end, int left, int right){
        if( right < start || end <left){
            return 1;
        }

        if( left <= start && end <= right){
            return tree[node];
        }

        int mid = (start + end)/2;

        return (query(node*2, start, mid, left, right)
                *query(node*2+1, mid+1, end, left, right))%d;
    }

    static long updateTree(int node, int start, int end, int index, int newValule){
        if( index < start || end < index){
            return tree[node];
        }

        if(start == end){
            return tree[node] = newValule;
        }

        int mid = (start+end)/2;
        return tree[node] = (updateTree(node*2, start, mid, index, newValule)
                *updateTree(node*2+1, mid+1, end, index, newValule)
        )%d;
    }

    static long initTree(int node, int start, int end){
        if(start == end){
            return tree[node] = arr[start];
        }

        int mid = (start+end)/2;

        return tree[node] = (initTree(node*2, start, mid)
                             *initTree(node*2+1, mid+1, end)
        )%d;
    }

}


