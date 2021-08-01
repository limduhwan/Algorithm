package Z_02_합격_최적화_문제집;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class No_22_1600_BFS_안품 {
    static int K, W, H;
    static int[][] arr;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("No_1600.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        K = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        arr = new int[W][H];

        for(int i=0; i<W; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<H; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        bfs(0,0);

    }

    static void bfs(int x, int y){
        Queue<Integer> queue = new LinkedList<>();


    }
}
