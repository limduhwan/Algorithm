package B01_최소공통조상;

import java.io.*;
import java.util.StringTokenizer;

public class No_05_11438 {

    static int N, Q;
    static int[] parent;
    static int[][] arr;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("No_11438.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());

        System.out.println(N);

        arr = new int[N+1][N+1];
        parent = new int[N+1];

        for(int i = 1; i<=N-1; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            arr[start][end] = 1;
            parent[end] = start;
        }

        Q = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
        System.out.println(Q);

        for(int i = 1; i<=Q; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            bw.write(lca(a, b)+"\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }

    static int lca(int from, int to){
        while



        return 0;
    }
}
