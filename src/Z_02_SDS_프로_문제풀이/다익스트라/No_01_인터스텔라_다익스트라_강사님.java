package Z_02_SDS_프로_문제풀이.다익스트라;

import A01_Grammar.Comparable_;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//3
//6 6 0
//1 2 3
//5 1 2
//5 6 5
//3 4 3
//6 4 6
//2 3 3
//1 4
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


public class No_01_인터스텔라_다익스트라_강사님 {
    static int T, N, M, K;
    static Vertex adj[];
    static long price[][] = new long[100001][3];        // 비용 배열
    static boolean[][] visited = new boolean[100001][3];// 방문 배열

    public static void main(String agrs[]) throws Exception {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("No_인터스텔라.txt"));
        T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken()); // 행성의 갯수 = 노드의 갯수
            M = Integer.parseInt(st.nextToken()); // 여행가능한 행성경로 = 간선의 갯수
            K = Integer.parseInt(st.nextToken()); // 워프패킷 갯수  0~2개

            adj = new Vertex[100001]; //행성 갯수만큼 자신이 갈 수 있는 경로 저장 배열

            for (int m = 0; m < M; m++) {
                st = new StringTokenizer(br.readLine());

                int x = Integer.parseInt(st.nextToken()); //행성 A
                int y = Integer.parseInt(st.nextToken()); //행성 B
                int z = Integer.parseInt(st.nextToken()); //행성간 거리

                //양방향 이므로 각자의 갈수 있는 경로로 저장
                Vertex v1 = new Vertex(y, z, adj[x]);
                adj[x] = v1;

                Vertex v2 = new Vertex(x, z, adj[y]);
                adj[y] = v2;
            }

            st = new StringTokenizer(br.readLine());

            //경로의 시작과 끝을 알려줌
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            //PQ로 정렬순서 설정 비용이 작은 것을 우선적으로 탐색
//            PriorityQueue<Node> pq = new PriorityQueue<Node>(new Comparator<Node>() {
//                @Override
//                public int compare(Node o1, Node o2) {
//                    // TODO Auto-generated method stub
//
//                    if (o1.dis > o2.dis) {
//                        return 1;
//                    } else if (o1.dis == o2.dis) {
//                        return 0;
//                    } else {
//                        return -1;
//                    }
//                }
//            });

            PriorityQueue<Node> pq = new PriorityQueue<Node>();


            // 시작 노드를 기준으로 값을 초기화함 (시작, 거리 0, 패킷 갯수)

            pq.add(new Node(start, 0, K));

            //각 도시마다 3개의 값을 두었다. 이유는 최대 패킷에 맞는 값을 저장해두기 위하여
            //다익스트라의 기본 원리 = Node 값을 무한대로 설정함.
            for (int i = 1; i <= N; i++) {
                price[i][0] = Long.MAX_VALUE;
                price[i][1] = Long.MAX_VALUE;
                price[i][2] = Long.MAX_VALUE;

                visited[i][0] = false;
                visited[i][1] = false;
                visited[i][2] = false;
            }

            //다익스트라의 기본 원리 대로 탐색시작
            while (!pq.isEmpty()) {
                Node n = pq.poll();
                Vertex v = adj[n.end]; //자식의 Vertex를 가져온다.

                if (n.end == end) break; //내 자식이 끝점이다 그럴 경우 break
                if (visited[n.end][n.ticket]) continue;//ticket과 자식 기준으로 방문 여부 결정
                visited[n.end][n.ticket] = true; //방문하지 않았다면 방문 표시후 수행

                while (v != null) {
                    if (n.dis + v.dis < price[v.end][n.ticket]) { //현재의 거리 + 자식의 거리 < 자식이 도달할 수 있는지점
                        price[v.end][n.ticket] = n.dis + v.dis;
                        pq.add(new Node(v.end, n.dis + v.dis, n.ticket));
                    }

                    if (n.ticket > 0 && n.dis + 1 < price[v.end][n.ticket - 1]) { //패킷하나를 소요한 경우도 저장
                        price[v.end][n.ticket - 1] = n.dis + 1;
                        pq.add(new Node(v.end, n.dis + 1, n.ticket - 1));
                    }
                    v = v.next;
                }
            }

            long ret = Math.min(price[end][0], price[end][1]);
            ret = Math.min(ret, price[end][2]);

            System.out.println("#" + t + " " + ret);
        }
    }

    static class Vertex {
        int end;
        long dis;
        Vertex next;

        public Vertex(int e, long d, Vertex v) {
            this.end = e;
            this.dis = d;
            this.next = v;
        }
    }

    static class Node implements Comparable<Node>{
        int end;
        long dis;
        int ticket;

        public Node(int e, long d, int t) {
            this.end = e;
            this.dis = d;
            this.ticket = t;
        }

        @Override
        public int compareTo(Node o) {
            if (this.dis > o.dis) {
                return 1;
            } else if (this.dis == o.dis) {
                return 0;
            } else {
                return -1;
            }

//            return Long.compare(this.dis, o.dis);
        }
    }
}