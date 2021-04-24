package Fluid_Washal;

import java.io.*;
import java.util.StringTokenizer;

//https://pangsblog.tistory.com/90
public class No_11404 {
    static int cityCount;
    static int[][] distance;
    static final int INF = 1000000000;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("No_11404.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = null;

        cityCount = Integer.parseInt(br.readLine());

        int busCount = Integer.parseInt(br.readLine());

        distance = new int[cityCount+1][cityCount+1];

        for(int i=1; i<= cityCount; i ++){
            for(int j=1; j <= cityCount; j ++) {
                if(i == j) continue;
                distance[i][j] = INF;
            }
        }

        for(int i = 0; i<busCount; i++){
            st = new StringTokenizer((br.readLine()));
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int val = Integer.parseInt(st.nextToken());
            distance[u][v] = Math.min(distance[u][v], val);
        }

        floydMarshall();
        print();
    }

    public static void floydMarshall() {
        // 기준이 되는 거쳐가는 노드
        for(int k =1; k <= cityCount; k++){
            // 출발하는 노드 i
            for(int i=1; i <= cityCount; i++) {
                // 도착하는 노드 j
                for(int j=1; j<= cityCount; j++){
                    //i에서 k를 거쳤다가 k에서 j 까지 가는 거리와 i에서 j 까지 가는 거리를 비교해서 작은 값이 최소거리이다.
                    distance[i][j] = Math.min(distance[i][k] + distance[k][j], distance[i][j]);
                }
            }
        }
    }

    public static void print() {
        StringBuffer sb = new StringBuffer();
        for(int i = 1; i <=cityCount; i++){
            for(int j = 1; j <= cityCount; j++) {
                if(distance[i][j] >= INF) sb.append("0 ");
                else sb.append(distance[i][j] + " ");
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }
}
