package B09_금_MST;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//#1 38
//#2 0
//#3 342

// MST (Kruskal or Prim)
//왜 cost가 long인가
//Class를 두개 만든다.
//거리 제곱 연산에 대해서
public class 문제풀이_Day03_02번_도시계획_00_MST_나에게맞게 {

    static Point[] points;
    static int[] parent;
    static PriorityQueue<Road> pq;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("No_도시계획.txt"));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken()); // 도시의 수
            int M = Integer.parseInt(st.nextToken()); // 수원지의 수

            points = new Point[N+1];
            parent = new int[N+1];

            for (int i = 1; i <= N ; i++) {
                parent[i] = i;
            }

            for (int i = 1; i <=N ; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                points[i] = new Point(x, y);
            }

            st = new StringTokenizer(br.readLine());

            int a = 0;
            for (int i = 0; i < M; i++) {
                int b = Integer.parseInt(st.nextToken());
                union(a, b);
            }

            pq = new PriorityQueue<>();

            for (int i = 1; i < N; i++) {
                for (int j = i+1; j <=N ; j++) {
                    pq.offer(new Road(i, j,
                            (long) (points[i].x - points[j].x)*(points[i].x - points[j].x)
                    +(long)(points[i].y - points[j].y)*(points[i].y - points[j].y)));
                }
            }

            System.out.println(kruskal(N-M));
        }

    }

    static long kruskal(int V){
        long mst_cost = 0;
        int selected = 0;

        while(!pq.isEmpty()){
            Road road = pq.poll();

            if(!equals(road.start, road.end)){
                mst_cost = mst_cost + road.cost;
                union(road.start, road.end);
                selected++;

                if(selected == V){
                    return mst_cost;
                }
            }
        }

        return  mst_cost;
    }

    static class Road implements Comparable<Road>{
        int start;
        int end;
        long cost;

        Road(int start, int end, long cost){
            this.start = start;
            this.end = end;
            this.cost = cost;
        }

        public int compareTo(Road o){
            return Long.compare(this.cost, o.cost);
        }
    }

    static class Point{
        int x;
        int y;

        Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    static void union(int a, int b){
        int x = find(a);
        int y = find(b);

        if(x !=y){
            if(x < y){
                parent[y] = x;
            }else{
                parent[x] = y;
            }
        }
    }

    static int find(int a){
        if(parent[a] == a){
            return a;
        } else {
            return parent[a] = find(parent[a]);
        }
    }

    static boolean equals(int a, int b){
        int x = find(a);
        int y = find(b);

        if(x ==y){
            return true;
        }else{
            return false;
        }
    }
}