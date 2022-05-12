package B02_화_SegmentTree;
import java.io.*;
import java.util.StringTokenizer;

//2
//5
//7
//1 2 4
//0 3 9
//0 4 10
//1 2 5
//0 1 -5
//0 3 5
//1 1 3
//10
//20
//1 1 1
//0 7 -35961
//1 3 7
//0 7 -24162
//0 6 -67310
//1 3 6
//1 4 7
//0 7 64951
//0 4 13544
//1 5 10
//0 5 -84661
//0 8 -41782
//0 5 28959
//0 3 11759
//0 1 43796
//1 4 8
//1 1 10
//1 7 9
//0 10 -55949
//1 8 8
public class A0_2022_필수_02_BottomUp_완전기본_구간합_기본원리_내거_01번_반복중 {
    static int T, N, Q;
    static int K, startIdx, treeLength;
    static long[] tree;
    static long result;
    static long MOD = 1000000007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("No_완전기본_구간합_기본원리.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());

        for(int t=1; t<=T; t++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            Q = Integer.parseInt(st.nextToken());

            //KSLT를 구하자
            K = (int)Math.ceil(Math.log(N) / Math.log(2));
            startIdx = (int)Math.pow(2, K);
            treeLength = (int)Math.pow(2, K+1)-1;

            tree = new long[treeLength+1];

            for(int i=1; i<=N; i++){
                updateTree(i, i);
            }

            result = 0;

            for (int i = 1; i<=Q; i++){
                st = new StringTokenizer(br.readLine());
                int type = Integer.parseInt(st.nextToken());
                int from = Integer.parseInt(st.nextToken());
                int to   = Integer.parseInt(st.nextToken());

                if(type == 0){
                    updateTree(from, to);
                }else{
                    result = result + sumIndexTree(from, to);
                }
            }

            if(result < 0){
                result = result + MOD;
            }

            System.out.println("#" + t + "  " + result%MOD);

        }

    }

    public static long sumIndexTree(int start, int end){
        long sum = 0;
        start = startIdx + start -1;
        end = startIdx + end -1;

        while(start <= end){
            if(start % 2 !=0){
                sum = sum + tree[start];
            }

            if(end % 2 ==0){
                sum = sum + tree[end];
            }

            start = (start +1)/2;
            end = (end-1)/2;
        }

        return sum;
    }

    public static void updateTree(int index, int num){
        index = startIdx + index -1;
        tree[index] = num;
        index = index/2;

        while(index > 0){
            tree[index] = tree[index*2] + tree[index*2+1];
            index = index/2;
        }
    }



}