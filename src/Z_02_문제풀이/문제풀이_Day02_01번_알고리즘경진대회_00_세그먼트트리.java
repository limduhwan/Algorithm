package Z_02_문제풀이;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//https://platform.samsungcic.com/#/connect/LCB20210329100061572
//2일차 (2/7) 11:11분
//3/7 11:43

public class 문제풀이_Day02_01번_알고리즘경진대회_00_세그먼트트리 {
    static int TC, CheoungSu;
    static int[] CheoungByulZicwonSu;
    static int[] ChodaeGaneungSu;
    static int[] segTree;
    static int[] CheoungByulTeamSu;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("문제풀이_Day02_01번_알고리즘경진대회_00_세그먼트트리.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        TC = Integer.parseInt(br.readLine());

        for (int i = 1; i <=TC ; i++) {
            CheoungSu = Integer.parseInt(br.readLine());
            CheoungByulZicwonSu = new int[CheoungSu+1];
            ChodaeGaneungSu = new int[CheoungSu+1];
            CheoungByulTeamSu = new int[CheoungSu+1];
            segTree = new int[CheoungSu*4];

            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <=CheoungSu ; j++) {
                CheoungByulZicwonSu[j] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for (int k = 1; k <=CheoungSu ; k++) {
                ChodaeGaneungSu[k] = Integer.parseInt(st.nextToken());
            }

            //CheoungByulTeamSu[]을 구성하자.

            for (int m = 1; m <=CheoungSu ; m++) {
                int cgchsu = ChodaeGaneungSu[m];

                int start = m - cgchsu;
                int end = m +  cgchsu;

                if(start <0){
                    start = 1;
                }

                if(end >CheoungSu){
                    end = CheoungSu;
                }



            }

            initSegTree(1, 1, CheoungSu);

            query(1, 1, CheoungSu, 1, CheoungSu);

        }
    }

    static int initSegTree(int node, int start, int end){
        if(start == end){
            return segTree[node] = CheoungByulTeamSu[start];
        }

        int mid = (start+end)/2;
        return segTree[node] = initSegTree(node*2, start, mid) +
                               initSegTree(node*2+1, mid+1, end);
    }

    static int query(int node, int start, int end, int left, int right){
        if(right < start || end < left){
            return 0;
        }

        if( left <=start && end<=right){
            return segTree[node];
        }

        int mid = (start + end)/2;

        return query(node*2, start, mid, left, right)
                + query(node*2+1, mid+1, end, left, right);
    }
}
