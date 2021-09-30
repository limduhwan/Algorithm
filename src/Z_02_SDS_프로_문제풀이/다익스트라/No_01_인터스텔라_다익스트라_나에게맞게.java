package Z_02_SDS_프로_문제풀이.다익스트라;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//3 => T
//6 6 0 => N 행성의 개수, M 여행가능한 행성 쌍의 개수, K 워프패킷 개수
//1 2 3 => 행성 쌍, 걸리는 시간
//5 1 2
//5 6 5
//3 4 3
//6 4 6
//2 3 3
//1 4 => 경로의 시작점, 끝점
//6 6 1
//1 2 3
//5 1 2
//5 6 5
//3 4 3
//6 4 6
//2 3 3
//4 1
//6 6 2
//1 2 3
//5 1 2
//5 6 5
//3 4 3
//6 4 6
//2 3 3
//1 4
//
//
//
//
//(출력)
//#1 9
//#2 7
//#3 4


public class No_01_인터스텔라_다익스트라_나에게맞게 {

    static int T, N, M, K;
    static Vertex adj[];
    static long[][] price = new long[100001][3];
    static boolean[][] visited = new boolean[100001][3];

    public static void main(String agrs[]) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("No_인터스텔라.txt"));
        T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            adj = new Vertex[100001];

            for (int m = 0; m < M; m++) {
                st = new StringTokenizer(br.readLine());

                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int z = Integer.parseInt(st.nextToken());

                Vertex v1 = new Vertex(y, z, adj[x]);
                adj[x] = v1;

                Vertex v2 = new Vertex(x, z, adj[y]);
                adj[y] = v2;
            }

            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            PriorityQueue<Node> pq = new PriorityQueue<Node>();

            pq.add(new Node(start, 0, K));

            for (int i = 1; i <= N ; i++) {
                price[i][0] = Long.MAX_VALUE;
                price[i][1] = Long.MAX_VALUE;
                price[i][2] = Long.MAX_VALUE;

                visited[i][0] = false;
                visited[i][1] = false;
                visited[i][2] = false;
            }

            while(!pq.isEmpty()){
                Node n = pq.poll();
                Vertex v = adj[n.end];

                if(n.end == end) break;
                if(visited[n.end][n.ticket]) continue;
                visited[n.end][n.ticket] = true;

                while(v != null){
                    if(n.dis + v.dis < price[v.end][n.ticket]){
                        price[v.end][n.ticket] = n.dis + v.dis;
                        pq.add(new Node(v.end, n.dis + v.dis, n.ticket));
                    }

                    if(n.ticket > 0 && n.dis +1 < price[v.end][n.ticket-1]){
                        price[v.end][n.ticket-1] = n.dis +1;
                        pq.add(new Node(v.end, n.dis +1, n.ticket-1));
                    }

                    v = v.next;
                }
            }

            long ret = Math.min(price[end][0], price[end][1]);
            ret = Math.min(ret, price[end][2]);

            System.out.println("#" + t + " " + ret);
        }
    }

    static class Vertex{
        int end;
        long dis;
        Vertex next;

        public Vertex(int e, long d, Vertex v){
            this.end = e;
            this.dis = d;
            this.next = v;
        }
    }

    static class Node implements Comparable<Node>{
        int end;
        long dis;
        int ticket;

        public Node(int e, long d, int t){
            this.end = e;
            this.dis = d;
            this.ticket = t;
        }

        public int compareTo(Node o){
           return Long.compare(this.dis, o.dis);
        }
    }
}