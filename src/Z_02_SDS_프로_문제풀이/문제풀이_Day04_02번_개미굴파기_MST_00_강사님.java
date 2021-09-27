package Z_02_SDS_프로_문제풀이;

import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//2
//6
//2 1 3
//1 5 5
//3 6 2
//6 5 4
//2 6 5
//1 4 1
//3
//1 2 1
//3 2 1
//1 3 1

//#1 2
//#2 3

public class 문제풀이_Day04_02번_개미굴파기_MST_00_강사님 {

    static ArrayList<point> map[];
    static int N;

    static class point implements Comparable<point> {
        int a, b, c;

        public point(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        @Override
        public int compareTo(point arg0) {
            if (this.c > arg0.c) {
                return 1;
            } else if (this.c < arg0.c) {
                return -1;
            } else {
                return 0;
            }
        }
    }


    public static void main(String[] args) throws Exception {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("No_개미굴파기.txt"));
        int testcase = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= testcase; tc++) {
            N = Integer.parseInt(br.readLine());
            map = new ArrayList[N + 1];

            for (int i = 0; i <= N; i++) {
                map[i] = new ArrayList<point>();
            }

            for (int i = 1; i <= N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());

                map[a].add(new point(a, b, c));
                map[b].add(new point(b, a, c));
            }

            PriorityQueue<point> que = new PriorityQueue<point>();

            que.add(new point(0, 1, 0));

            int E[] = new int[N + 1];
            int L[] = new int[N + 1];
            int dep[] = new int[N + 1];
            boolean visit[] = new boolean[N + 1];

            point unused = null;

            int C = 0;
            while (!que.isEmpty()) {
                point here = que.poll();

                if (visit[here.b] == true) {
                    unused = here;
                    continue;
                }

                C++;
                visit[here.b] = true;

                E[here.b] = here.a;
                L[here.b] = here.c;
                dep[here.b] = dep[here.a] + 1;

                for (point next : map[here.b]) {
                    if (visit[next.b] == true) {
                        continue;
                    }
                    que.add(next);
                }
            }

            int result = 0;
            if (C == N) {
                result++;

                int v1 = unused.a;
                int v2 = unused.b;
                int val = unused.c;

                if (dep[v1] > dep[v2]) {
                    v1 = unused.b;
                    v2 = unused.a;
                }

                while (v1 != v2) {
                    while (dep[v1] < dep[v2]) {
                        if (unused.c == L[v2]) {
                            result++;
                        }
                        v2 = E[v2];
                    }

                    if (v1 == v2) {
                        break;
                    } else {
                        if (val == L[v1]) {
                            result++;
                        }
                        if (val == L[v2]) {
                            result++;
                        }

                        v1 = E[v1];
                        v2 = E[v2];
                    }
                }
            } else {
                result = 0;
            }

            System.out.println("#" + tc + " " + result);
        }
    }
}
