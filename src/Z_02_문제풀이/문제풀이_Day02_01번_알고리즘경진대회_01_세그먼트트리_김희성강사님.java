package Z_02_문제풀이;

import java.io.*;
import java.util.StringTokenizer;

//https://platform.samsungcic.com/#/connect/LCB20210329100061572
//2일차 (2/7) 11:11분
//3/7 11:43

public class 문제풀이_Day02_01번_알고리즘경진대회_01_세그먼트트리_김희성강사님 {
    static int[] segTreeGCD;
    static long[] segTreeSum;
    static long[] sumArr;
    static int[] A = new int[100000];

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("문제풀이_Day02_01번_알고리즘경진대회_00_세그먼트트리.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(br.readLine());

            segTreeGCD = new int[N*4];

            segTreeSum = new long[N*4];
            sumArr = new long[N+1];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i <N; i++) {
                A[i] = Integer.parseInt(st.nextToken());
                sumArr[i+1] = sumArr[i] + A[i]; //누적합
            }

            initGCD(A, 0, N-1, 1);
            initSum(A, 0, N-1, 1);

            st = new StringTokenizer(br.readLine());
            long result=0;
            for(int i=0; i<N; i++){
                int G = Integer.parseInt(st.nextToken());

                int min = Math.max(i-G, 0);
                int max = Math.min(i+G, N-1);

                int GCD = query(min, max, 1, 0, N-1);

                long sum = (sumArr[max+1]-sumArr[min]);

                result = result + sum/GCD;

            }

            bw.write("#"+tc+" "+result+"\n");
            bw.flush();

        }


    }

    static int GCD(int a, int b){
        if(a<b){
            int temp = b;
            b = a;
            a = temp;
        }

        if(b==0) return a;

        while(true){
            int r = a%b;
            if(r==0) return b;
            a = b;
            b = r;
        }
    }

    static int initGCD(int[] arr, int left, int right, int node){
        if(left == right){
            return segTreeGCD[node] = arr[left];
        }

        int mid = (left+right)/2;
        return segTreeGCD[node] = GCD(initGCD(arr, left, mid, node*2), initGCD(arr, mid+1, right, node*2+1));
    }

    static int query(int start, int end, int node, int left, int right){
        if(start>right || end < left){
            return 0;
        }

        if(start <= left && right <= end){
            return segTreeGCD[node];
        }

        int mid = (left+right)/2;
        return GCD(query(start, end, node*2, left, mid), query(start, end, node*2+1, mid+1, right));
    }

    static long initSum(int[] arr, int left, int right, int node){
        if(left == right){
            return segTreeSum[node] = arr[left];
        }

        int mid = (left+right)/2;
        return segTreeSum[node] = initSum(arr, left, mid, node*2)
                +initSum(arr, mid+1, right, node*2+1);
    }

    static long querySum(int start, int end, int node, int left, int right){
        if(start > right || end < left){
            return 0;
        }
        if(start<=left && right<=end){
            return segTreeSum[node];
        }

        int mid = (left+right)/2;
        return querySum(start, end, node*2, left, mid)
                +querySum(start, end, node*2+1, mid+1, right);
    }
}
