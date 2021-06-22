package B07_DFS;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.StringTokenizer;

public class No_01_11724_DFS {
    static int N, M;
    static int graph[][];
    static int visited[];

    public static void main(String args[]) throws Exception{
        BufferedReader br = new BufferedReader(new FileReader("No_11724_DFS.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new int[N+1][N+1];

        visited = new int[N+1];

        for(int i = 0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
           graph[x][y] = graph[y][x] = 1;
        }

        int cnt = 1;
        for(int i = 1; i < N; i++){
            if(visited[i]==0){
                dfs(i, cnt);
                cnt++;
            }
        }

        System.out.println(cnt-1);
    }

    public static void dfs(int x, int cnt){
        visited[x] = cnt;

        for(int i=1; i<N+1; i++){
           if(graph[x][i] == 1 && visited[i]==0){
               dfs(i, cnt);
           }
        }
    }
}
