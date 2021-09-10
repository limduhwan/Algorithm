package B04_목_최단거리_다익스트라;

import java.io.*;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//다익스트라를 2번 돌리는 유형.
//머리를 살짝 굴리면 목적지를 기준으로 2번 돌린다.
//https://pangtrue.tistory.com/272
public class No_25_1238 {
    static int N, M, K;
    static int[][] arrBack, arr;
    static int[] dijBack, dij;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("No_1238.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

//        System.out.println(N+" "+ M+" "+ K);

        arr = new int[N+1][N+1];
        arrBack = new int[N+1][N+1];

        dij = new int[N+1];
        dijBack = new int[N+1];

        Arrays.fill(dijBack, Integer.MAX_VALUE);
        Arrays.fill(dij, Integer.MAX_VALUE);

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            arrBack[start][end] = weight;
            arr[end][start] = weight;
        }

        dij[K] = 0;
        dij(K);

        dijBack[K] = 0;
        dijBack(K);

        for(int i=1; i<=N; i++){
            System.out.println(dij[i]);
        }

        System.out.println();

        for(int i=1; i<=N; i++){
            System.out.println(dijBack[i]);
        }
    }

    static void dij(int point){
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        pq.offer(point);

        while(!pq.isEmpty()){
            int nowPoint = pq.poll();
//            System.out.println("nowPoint: " + nowPoint);
            for(int i=1; i<=N; i++){
//                System.out.println("nowPoint: " + nowPoint + " i " + i +" "+ arr[nowPoint][i] +" " +dij[i] +" "+dij[nowPoint]  );
                if(arr[nowPoint][i] != 0 && dij[i]> dij[nowPoint] + arr[nowPoint][i]) {
                    dij[i] = dij[nowPoint] + arr[nowPoint][i];
//                    System.out.println("update: "+i+"  "+ dij[i]);
                    pq.offer(i);
                }
            }

        }

    }

    static void dijBack(int point){
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        pq.offer(point);

        while(!pq.isEmpty()){
            int nowPoint = pq.poll();
//            System.out.println("nowPoint: " + nowPoint);
            for(int i=1; i<=N; i++){
//                System.out.println("nowPoint: " + nowPoint + " i " + i +" "+ arr[nowPoint][i] +" " +dij[i] +" "+dij[nowPoint]  );
                if(arrBack[nowPoint][i] != 0 && dijBack[i]> dijBack[nowPoint] + arrBack[nowPoint][i]) {
                    dijBack[i] = dijBack[nowPoint] + arrBack[nowPoint][i];
//                    System.out.println("update: "+i+"  "+ dij[i]);
                    pq.offer(i);
                }
            }

        }

    }

    static class Node implements Comparable<Node>{
        int point;
        int weight;

        Node(int point, int weight){
            this.point = point;
            this.weight = weight;
        }

        public int compareTo(Node o){
            return this.weight - o.weight;
        }
    }
}
