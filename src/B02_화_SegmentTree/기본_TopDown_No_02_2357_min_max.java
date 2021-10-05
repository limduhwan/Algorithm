package B02_화_SegmentTree;

import java.io.*;
import java.util.StringTokenizer;

//문제
//N(1 ≤ N ≤ 100,000)개의 정수들이 있을 때,
//a번째 정수부터 b번째 정수까지 중에서 제일 작은 정수, 또는 제일 큰 정수를 찾는 것은 어려운 일이 아니다.
//하지만 이와 같은 a, b의 쌍이 M(1 ≤ M ≤ 100,000)개 주어졌을 때는 어려운 문제가 된다.
//이 문제를 해결해 보자.
//
//여기서 a번째라는 것은 입력되는 순서로 a번째라는 이야기이다.
//예를 들어 a=1, b=3이라면 입력된 순서대로 1번, 2번, 3번 정수 중에서
//최소, 최댓값을 찾아야 한다. 각각의 정수들은 1이상 1,000,000,000이하의 값을 갖는다.
//
//입력
//첫째 줄에 N, M이 주어진다. 다음 N개의 줄에는 N개의 정수가 주어진다.
//다음 M개의 줄에는 a, b의 쌍이 주어진다.
//
//출력
//M개의 줄에 입력받은 순서대로 각 a, b에 대한 답을 최솟값, 최댓값 순서로 출력한다.

//https://www.acmicpc.net/problem/2357
public class 기본_TopDown_No_02_2357_min_max {
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
