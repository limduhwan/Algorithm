package Z_02_합격_최적화_문제집;

import java.io.*;
import java.util.StringTokenizer;

//DFS로만 접근하면 시간초과가 난다. DP 융합문제
//https://loosie.tistory.com/250
public class No_21_1103_DFS_DP {
    static int seroM, garoN;
    static int[][] map, dp;
    static boolean[][] visited;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int hole = -99;
    static int max = -1;
    static boolean flag = false;

    public static void main(String[] agrs) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("No_1103.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        seroM = Integer.parseInt(st.nextToken());
        garoN = Integer.parseInt(st.nextToken());

        map = new int[seroM][garoN];
        dp = new int[seroM][garoN];
        visited = new boolean[seroM][garoN];

        for(int i=0; i<seroM; i++){
            String[] line = br.readLine().split("");
//            System.out.println(line.length);
            for(int j=0; j< line.length; j++){
                if(line[j].equals("H")){
                    map[i][j] = hole;
                }else{
                    map[i][j] = Integer.parseInt(line[j]);
                }
            }
        }

        visited[0][0] = true;
        dfs(0,0,1);

        if(flag){
            System.out.println(-1);
        }else{
            System.out.println(max);
        }
    }

    static void dfs(int x, int y, int cnt){
        if(cnt > max){
            max = cnt;
        }

        dp[x][y] = cnt;

        visited[x][y] = true;

        int move = map[x][y];

        for(int i=0; i<4; i++){
            int nx = x + (move*dx[i]);
            int ny = y + (move*dy[i]);

//            System.out.println(nx+" "+ny);
//            System.out.println(map[nx][ny]);
            if(nx < 0 || ny <0 || nx >= seroM || ny >= garoN ||map[nx][ny] == hole){
                continue;
            }

            if(visited[nx][ny]){
                flag = true;
                return;
            }

            if(dp[nx][ny] > cnt) continue;

            visited[nx][ny] = true;
            dfs(nx, ny, cnt+1);
            visited[nx][ny] = false;
        }


    }

}
