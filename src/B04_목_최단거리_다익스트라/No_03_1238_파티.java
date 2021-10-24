package B04_목_최단거리_다익스트라;

import java.io.*;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//문제
//N개의 숫자로 구분된 각각의 마을에 한 명의 학생이 살고 있다.
//
//어느 날 이 N명의 학생이 X (1 ≤ X ≤ N)번 마을에 모여서 파티를 벌이기로 했다.
// 이 마을 사이에는 총 M개의 단방향 도로들이 있고 i번째 길을 지나는데 Ti(1 ≤ Ti ≤ 100)의 시간을 소비한다.
//
//각각의 학생들은 파티에 참석하기 위해 걸어가서 다시 그들의 마을로 돌아와야 한다.
// 하지만 이 학생들은 워낙 게을러서 최단 시간에 오고 가기를 원한다.
//
//이 도로들은 단방향이기 때문에 아마 그들이 오고 가는 길이 다를지도 모른다.
// N명의 학생들 중 오고 가는데 가장 많은 시간을 소비하는 학생은 누구일지 구하여라.
//
//입력
//첫째 줄에 N(1 ≤ N ≤ 1,000), M(1 ≤ M ≤ 10,000), X가 공백으로 구분되어 입력된다.
// 두 번째 줄부터 M+1번째 줄까지 i번째 도로의 시작점, 끝점, 그리고 이 도로를 지나는데 필요한 소요시간 Ti가 들어온다.
// 시작점과 끝점이 같은 도로는 없으며,
// 시작점과 한 도시 A에서 다른 도시 B로 가는 도로의 개수는 최대 1개이다.
//
//모든 학생들은 집에서 X에 갈수 있고,
// X에서 집으로 돌아올 수 있는 데이터만 입력으로 주어진다.
//
//출력
//첫 번째 줄에 N명의 학생들 중 오고 가는데 가장 오래 걸리는 학생의 소요시간을 출력한다.

//다익스트라를 2번 돌리는 유형.
//머리를 살짝 굴리면 목적지를 기준으로 2번 돌린다.
//https://pangtrue.tistory.com/272
public class No_03_1238_파티 {
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
