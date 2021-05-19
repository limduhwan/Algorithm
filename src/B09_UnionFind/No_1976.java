package B09_UnionFind;

import java.io.*;
import java.util.StringTokenizer;

//https://www.acmicpc.net/problem/1976
//https://steady-coding.tistory.com/109
public class No_1976 {

    static int N, M;
    static int[] parent;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("No_1976.txt"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); //도시의 수
        M = Integer.parseInt(br.readLine()); //여행계획의 수

//        System.out.println(N +"    "+ M);

        parent = new int[N+1];

        for(int i=0; i<N; i++){
            parent[i] = i;
        }

        for(int i=1; i<=N; i++){
            st = new StringTokenizer(br.readLine());

            for (int j = 1; j <= N; j++) {
                int temp = Integer.parseInt(st.nextToken());
                if(temp == 1){
                    union(i, j);
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        int start = find(Integer.parseInt(st.nextToken()));

        for(int i = 1; i<M; i++){
            int now = Integer.parseInt(st.nextToken());

            if(start != find(now)){
                bw.write("NO\n");
                bw.flush();
                bw.close();
                br.close();
                return;
            }
        }

        bw.write("YES\n");
        bw.flush();
        bw.close();
        br.close();
    }

    static void union(int x, int y){
        x = find(x);
        y = find(y);

        if(x != y){
            if(x<y) {
                parent[y] = x;
            } else {
                parent[x] = y;
            }
        }
    }

    static int find(int x){
        if(parent[x] == x){
            return x;
        }else{
            return parent[x] = find(parent[x]);
        }
    }

    static boolean isSameParent(int x, int y){
        x = find(x);
        y = find(y);
        return x == y;
    }
}
