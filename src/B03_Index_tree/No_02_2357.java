package B03_Index_tree;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

//https://www.acmicpc.net/problem/2357
//https://lucysworld.tistory.com/5?category=889472
public class No_02_2357 {
    private static int N, M, s_idx;
    private static long[] minTree, maxTree;
    public static void main(String[] args) throws Exception{
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("No_2357.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        s_idx = 1;
        while (s_idx < N) {
            s_idx *= 2;
        }
        minTree = new long[s_idx*2];
        Arrays.fill(minTree, Long.MAX_VALUE);
        maxTree = new long[s_idx*2];
        for (int i = 1; i <= N ; i++) {
            minTree[s_idx+i] = maxTree[s_idx+i] = Long.parseLong(br.readLine());
        }
        init(s_idx+1, N);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) + s_idx;
            int b = Integer.parseInt(st.nextToken()) + s_idx;
            long minAns = minQuery(a, b);
            long maxAns = maxQuery(a, b);
            bw.write(minAns + " " + maxAns + "\n");
            bw.flush();
        }
        bw.close();
        br.close();

    }

    private static void init(int start, int end) {
        for (int i = start; i < start + end; i++) {
            int idx = i / 2;
            while (idx != 0) {
                maxTree[idx] = Math.max(maxTree[i], maxTree[idx]);
                minTree[idx] = Math.min(minTree[i], minTree[idx]);
                idx /= 2;
            }
        }
    }

    private static long maxQuery(int start, int end) {
        long result = 0;
        while (start < end) {
            if(start % 2 == 1) result = Math.max(result, maxTree[start]);
            if(end % 2 == 0) result = Math.max(result, maxTree[end]);
            start = (start+1)/2;
            end = (end-1)/2;
        }
        if(start == end) result = Math.max(result, maxTree[start]);
        return result;
    }

    private static long minQuery(int start, int end) {
        long result = Long.MAX_VALUE;
        while (start < end) {
            if(start % 2 == 1) result = Math.min(result, minTree[start]);
            if(end % 2 == 0) result = Math.min(result, minTree[end]);
            start = (start+1)/2;
            end = (end-1)/2;
        }
        if(start == end) result = Math.min(result, minTree[start]);
        return result;
    }
}
