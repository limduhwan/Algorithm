package B03_Index_tree;

import java.io.*;
import java.util.StringTokenizer;

//https://popuur.tistory.com/42
//https://m.blog.naver.com/PostView.nhn?blogId=ndb796&logNo=221312822103&proxyReferer=https:%2F%2Fwww.google.com%2F
//https://www.acmicpc.net/problem/2042
public class No_01_2042_0 {
    static int N, M, K;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("No_2042.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); //노드개수
        M = Integer.parseInt(st.nextToken()); //수의 변경 횟수
        K = Integer.parseInt(st.nextToken()); //합을 구하는 횟수

        IndexTree it = new IndexTree(N);

        for(int i =1; i<= N; i++){
            int val = Integer.parseInt(br.readLine());
            it.set(i, val);
        }

        for(int i=1; i<=M+K; i++){
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if( a== 1){
                it.set(b, c);
            } else {
                long result = it.search(b, c);
                bw.write(result+"\n");
            }
        }

        br.close();
        bw.flush();
        bw.close();


    }

    static class IndexTree {
        int sz = 0;
        long[] tree;

        IndexTree(int n){
            for(sz=1; sz<=n; sz *=2 );
            tree = new long[sz*2];
            sz--;
        }

        void init(int idx, long val) {
            idx += sz;
            tree[idx] = val;
            while(idx >= 1) {
                idx /= 2;
                tree[idx] = tree[idx*2] + tree[idx*2+1];
            }
        }

        void set(int idx, long val){
            idx += sz;
            long gap = tree[idx] - val;
            while(idx >= 1){
                tree[idx] -= gap;
                idx /= 2;
            }
        }

        long search(int l, int r){
            l += sz;
            r += sz;

            long result = 0;
            while( l<=r) {
                if(l%2 == 1){
                    result += tree[l];
                }
                l++;
                l /= 2;

                if(r%2 == 0){
                    result += tree[r];
                }

                r--;
                r /=2;
            }
            return result;
        }
    }
}


