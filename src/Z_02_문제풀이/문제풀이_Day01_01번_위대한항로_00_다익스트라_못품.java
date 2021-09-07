package Z_02_문제풀이;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//https://platform.samsungcic.com/#/connect/LCB20210329100061572
//(1/6) 48분
public class 문제풀이_Day01_01번_위대한항로_00_다익스트라_못품 {
    static int TC, N, M, K;
    static ArrayList<Node>[] al;
    static int[] friend, dist;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("문제풀이_Day01_01번_89_위대한항로.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        TC = Integer.parseInt(br.readLine());

        for(int tc = 1; tc<=1; tc++){
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            al = new ArrayList[N+1];
            friend = new int[N+1];
            dist = new int[N+1];

            for(int i=0; i<=N; i++){
                al[i] = new ArrayList<Node>();
            }

            Arrays.fill(dist, Integer.MAX_VALUE);

            for(int i=0; i<M; i++){
                st = new StringTokenizer(br.readLine());

                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());

//                System.out.println("M   "+a +" "+ b +" "+ c);
//                al[a].add(new Node(b, c));
                al[b].add(new Node(a, c));
            }

            for(int i=0; i<K; i++){
                st = new StringTokenizer(br.readLine());

                int c = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());

//                System.out.println("K   "+c +" "+ d);

                friend[c] = d;
            }

            dist[N] = 0;

            dij(N, 1);

            System.out.println(dist[1]);

            for(int i = 1; i<=N; i++){
                System.out.println(i +"  "+ dist[i]);
            }
        }
    }

    static void dij(int start, int end) {
        //**Node가 들어감
        PriorityQueue<Node> pq = new PriorityQueue<>();

        //**Node가 들어감
        pq.offer(new Node(start, 0));

        while(!pq.isEmpty()){
            Node nowNode = pq.poll();

            //*더 큰 가중치로 도착한 경우 패스 => 이 부분 이해 안됨!!!!
            if(nowNode.weight > dist[nowNode.point]){
                continue;
            }

            for(int i =0; i<al[nowNode.point].size(); i++){
                Node next = al[nowNode.point].get(i);

//                if( dist[endNode.point] > dist[nowNode.point] + endNode.weight){
////                    System.out.println("1 "+ dist[nowNode] +" "+ endNode.weight);
//                    dist[endNode.point] = dist[nowNode.point] + endNode.weight;
//                    pq.offer(new Node(endNode.point, dist[endNode.point]));
//                }

//                if(dist[next.point] > Math.max(nowNode.weight))



            }

        }

    }

    static class Node implements Comparable<Node>{
        int point;
        int weight;

        Node(int end, int weight){
            this.point = end;
            this.weight = weight;
        }

        public int compareTo(Node o){
            return this.weight - o.weight;
        }
    }



}
