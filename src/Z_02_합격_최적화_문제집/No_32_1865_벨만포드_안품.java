package Z_02_합격_최적화_문제집;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

//https://steady-coding.tistory.com/91
public class No_32_1865_벨만포드_안품 {
    static int TC, N, M, W;
    static int[] dist;
    static ArrayList<ArrayList<Road>> a;
    static final int INF = 987654321;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("No_1865.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        TC = Integer.parseInt(br.readLine());

        while(TC-- >0){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());

            dist = new int[N+1];

            a = new ArrayList<>();
            for(int i=0; i<=N; i++){
                a.add(new ArrayList<>());
            }

            for(int i=0; i<M+W; i++){
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());

                if(i < M) {
                    a.get(start).add(new Road(end, weight));
                    a.get(end).add(new Road(start, weight));
                } else {
                    a.get(start).add(new Road(end, -weight));
                }
            }

            boolean isMinusCycle = false;
            for(int i = 1; i<= N; i++){


            }

        }
    }

    public static boolean bellmanFort(int start){
        Arrays.fill(dist, INF);
        dist[start] = 0;
        boolean update = false;





        return false;
    }

    static class Road {
        int end;
        int weight;

        Road(int end, int weight){
            this.end = end;
            this.weight = weight;
        }
    }
}
