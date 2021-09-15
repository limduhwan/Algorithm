package B17_BFS;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//https://www.acmicpc.net/problem/1260
//https://m.blog.naver.com/lm040466/221787478911
public class 기본_No_01_1260_01번_반복중 {

    static int N,M,V;
    static int[][] graph;
    static boolean[] checked;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("No_1260.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());

//        System.out.println(N+"   "+ M+"   "+ V);

        graph = new int[N+1][N+1];
        checked = new boolean[N+1];

        for( int i =0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            graph[x][y] = graph[y][x] = 1;
        }

        bfs(V);

//        checked = new boolean[N+1];
//        System.out.println();
//
//        dfs(V);
    }

    public static void bfs(int V){
        ArrayDeque<Integer> que = new ArrayDeque<>();

        checked[V] = true;
        que.offer(V);

        System.out.println(V);

        while(!que.isEmpty()){
            int start = que.poll();

            for (int i = 1; i <=N ; i++) {
                if(graph[start][i] == 1 && checked[i] == false){
                    checked[i] = true;
                    que.offer(i);
                    System.out.println(i);
                }

            }

        }


    }

    public static void dfs(int i){
        checked[i] = true;
        System.out.print(i+ " ");

        for(int j = 1; j<=N; j++){
            if(graph[i][j] == 1 && checked[j] == false){
                dfs(j);
            }
        }

    }

}
