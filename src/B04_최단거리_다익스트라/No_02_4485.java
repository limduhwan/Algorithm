package B04_최단거리_다익스트라;

import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//https://www.acmicpc.net/problem/4485
//아래 블로그 클리어하다!
//https://machine-geon.tistory.com/121
public class No_02_4485 {

    static int N; //맵 크기
    static int[][] map;
    static int[][] dijk;
    static int[] dx = {1,0,0,-1}; //우 하 상 좌
    static int[] dy = {0,1,-1,0};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("No_4485.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int cnt = 0; //반복횟수
        while(true) {
            N = Integer.parseInt(br.readLine());
            if(N == 0) break;

            map = new int[N][N];
            dijk = new int[N][N];

            for(int i = 0; i<N; i++){
                st = new StringTokenizer(br.readLine());

                for(int j=0; j<N; j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                    dijk[i][j] = Integer.MAX_VALUE;
                }
            }

            sb.append("Problem " + cnt + ":" + dijkstra() + "\n");


        }

        System.out.println(sb);
        br.close();
    }

    static int dijkstra() {
        PriorityQueue<Point> pq = new PriorityQueue<Point>();
        dijk[0][0] = map[0][0]; //초기값
        pq.offer(new Point(0,0, map[0][0])); //add도 동일결과

        while(!pq.isEmpty()){
            Point p = pq.poll();

            for(int k=0; k<4; k++){
                int nextRow = p.row + dy[k];
                int nextCol = p.col + dx[k];

                if(isValid(nextRow, nextCol)){
                    if(dijk[nextRow][nextCol] > dijk[p.row][p.col] + map[nextRow][nextCol]){
                        dijk[nextRow][nextCol] = dijk[p.row][p.col] + map[nextRow][nextCol];
                        pq.offer(new Point(nextRow, nextCol, dijk[nextRow][nextCol]));
                    }
                }
            }
        }
        return dijk[N-1][N-1];
    }

    static class Point implements Comparable<Point> {
        int row, col, cost;

        public Point (int row, int col, int cost){
            super();
            this.row = row;
            this.col = col;
            this.cost = cost;
        }

        @Override
        public int compareTo(Point o){
            return this.cost - o.cost;
        }
    }

    static boolean isValid(int x, int y){
        if(x <0 || x>=N || y <0 || y>=N){
            return false;
        }
        return true;
    }
}
