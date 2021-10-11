package B09_금_MST;

import java.util.*;

//문제
//때는 2040년, 이민혁은 우주에 자신만의 왕국을 만들었다.
// 왕국은 N개의 행성으로 이루어져 있다.
// 민혁이는 이 행성을 효율적으로 지배하기 위해서 행성을 연결하는 터널을 만들려고 한다.
//
//행성은 3차원 좌표위의 한 점으로 생각하면 된다.
// 두 행성 A(xA, yA, zA)와 B(xB, yB, zB)를 터널로 연결할 때 드는 비용은
// min(|xA-xB|, |yA-yB|, |zA-zB|)이다.
//
//민혁이는 터널을 총 N-1개 건설해서 모든 행성이 서로 연결되게 하려고 한다.
// 이때, 모든 행성을 터널로 연결하는데 필요한 최소 비용을 구하는 프로그램을 작성하시오.
//
//입력
//첫째 줄에 행성의 개수 N이 주어진다. (1 ≤ N ≤ 100,000)
// 다음 N개 줄에는 각 행성의 x, y, z좌표가 주어진다.
// 좌표는 -109보다 크거나 같고, 109보다 작거나 같은 정수이다.
// 한 위치에 행성이 두 개 이상 있는 경우는 없다.
//
//출력
//첫째 줄에 모든 행성을 터널로 연결하는데 필요한 최소 비용을 출력한다.

//5 -> 항성개수
//11 -15 -15 -> x, y, z 좌표
//14 -5 -15
//-1 -1 -5
//10 -4 -1
//19 -4 19
//
//4

//https://www.acmicpc.net/problem/2887
//https://moonsbeen.tistory.com/m/47

public class 기본_No_02_2887_인터넷 {
    static int n;
    static PriorityQueue<Edge> q;
    static int[] parent;
    static int result;
    static Planet[] planet;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        n = scan.nextInt();

        planet = new Planet[n];
        for(int i = 0; i < n; i++) {
            int x = scan.nextInt();
            int y = scan.nextInt();
            int z = scan.nextInt();
            planet[i] = new Planet(i, x, y, z);
        }
        q = new PriorityQueue<>();

        //x좌표 순서대로 오름차순 정렬 후 작은 값부터 q에 edge정보 저장.
        Arrays.sort(planet, (o1, o2) -> o1.x - o2.x);
        for(int i = 0; i < n - 1; i++) {
            q.offer(new Edge(planet[i].num, planet[i + 1].num, Math.abs(planet[i].x - planet[i + 1].x)));
        }
        //y좌표 순서대로 오름차순 정렬 후 작은 값부터 q에 edge정보 저장.
        Arrays.sort(planet, (o1, o2) -> o1.y - o2.y);
        for(int i = 0; i < n - 1; i++) {
            q.offer(new Edge(planet[i].num, planet[i + 1].num, Math.abs(planet[i].y - planet[i + 1].y)));
        }
        //z좌표 순서대로 오름차순 정렬 후 작은 값부터 q에 edge정보 저장.
        Arrays.sort(planet, (o1, o2) -> o1.z - o2.z);
        for(int i = 0; i < n - 1; i++) {
            q.offer(new Edge(planet[i].num, planet[i + 1].num, Math.abs(planet[i].z - planet[i + 1].z)));
        }

        parent = new int[n];
        kruskal();
        System.out.println(result);
    }

    public static void kruskal() {
        for(int i = 0; i < n; i++) {
            parent[i] = i;
        }

        while(!q.isEmpty()) {
            Edge edge = q.poll();
            int p1 = find(edge.s);
            int p2 = find(edge.e);

            if(p1 != p2) {
                union(p1, p2);
                result += edge.cost;
            }
        }
    }

    public static void union(int a, int b) {
        parent[a] = b;
    }

    public static int find(int a) {
        if(parent[a] == a) return a;
        else return parent[a] = find(parent[a]);
    }

    public static class Planet {
        int num;
        int x;
        int y;
        int z;

        public Planet(int num, int x, int y, int z) {
            this.num = num;
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    public static class Edge implements Comparable<Edge>{
        int s;
        int e;
        int cost;

        public Edge(int s, int e, int cost) {
            this.s = s;
            this.e = e;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge e) {
            return this.cost - e.cost;
        }
    }
}

