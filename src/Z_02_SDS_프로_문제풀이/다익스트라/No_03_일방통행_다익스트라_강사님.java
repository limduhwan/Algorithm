package Z_02_SDS_프로_문제풀이.다익스트라;

import java.io.*;
import java.util.*;

//(입력)
//3
//6 9
//RBWBGB
//1 2 3
//2 5 1
//3 1 2
//2 3 7
//2 4 5
//5 4 1
//4 3 1
//4 6 10
//3 4 4
//3 2
//RBB
//1 2 10
//2 3 10
//5 5
//BRWBG
//4 5 3
//1 2 1
//3 2 6
//2 3 5
//2 4 2
//
//
//
//
//(출력)
//
//#1 24
//#2 20
//#3 17

public class No_03_일방통행_다익스트라_강사님 {
    static int N, M;
    static char color[];
    static long dist[][];
    static ArrayList<NODE> al[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("No_일방통행.txt"));
        int test_case = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= test_case; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            char[] color = st.nextToken().toCharArray();
            dist = new long[4][N];
            // 1번행 : 적색일때 :
            // 2번행 : 청색일때
            // 3번행 : 백색일때
            // 4번행 : 녹색일때
            al = new ArrayList[N];
            for (int i = 0; i < N; i++) {
                al[i] = new ArrayList<>();
            }

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken()) - 1;
                int e = Integer.parseInt(st.nextToken()) - 1;
                int c = Integer.parseInt(st.nextToken());

                al[s].add(new NODE(e, c, s));
            }

            PriorityQueue<NODE> pq = new PriorityQueue<>();

            pq.add(new NODE(0, 0, 0));

            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < N; j++) {
                    dist[i][j] = Long.MAX_VALUE;
                }
            }

            dist[0][0] = 0;
            while (!pq.isEmpty()) {
                // 0 : 빨강 + 파랑
                // 1 : 파랑 통행증
                // 2 : 빨강 통행증
                // 3 : 통행증 없음

                NODE now = pq.poll();

                int now_e = now.end;
                long now_c = now.cost;
                int now_col = now.card;
                int next_col = now_col;

                if (color[now_e] == 'R') { // R일때 1혹은 3은 더이상 진행 못함.
                    if (now_col == 1 || now_col == 3) {
                        continue;
                    } else if (now_col == 2) {
                        next_col = 3;
                    } else if (now_col == 0) {
                        next_col = 1;
                    }
                } else if (color[now_e] == 'B') {// B일때 2혹은 3은 더이상 진행 못함.
                    if (now_col == 2 || now_col == 3) {
                        continue;
                    } else if (now_col == 1) {
                        next_col = 3;
                    } else if (now_col == 0) {
                        next_col = 2;
                    }
                } else if (color[now_e] == 'W') {// W일때 0의 상태가됨
                    next_col = 0;
                } else if (color[now_e] == 'G') {// G일떈 그냥 통과
                    next_col = now_col;
                }

                for (int i = 0; i < al[now_e].size(); i++) {
                    int next_e = al[now_e].get(i).end;
                    long next_c = al[now_e].get(i).cost;
                    if (dist[next_col][next_e] > now_c + next_c) {
                        dist[next_col][next_e] = now_c + next_c;
                        pq.add(new NODE(next_e, now_c + next_c, next_col));
                    }
                }
            }

            long ans = Long.MAX_VALUE;
            for (int i = 0; i < 4; i++) {
                if (ans > dist[i][N - 1]) {
                    ans = dist[i][N - 1];
                }
            }
            if (ans == Long.MAX_VALUE) {
                ans = -1;
            }

            System.out.println("#" + tc + " " + ans);
        }
    }

    static class NODE implements Comparable<NODE> {
        int end;
        long cost;
        int card;

        public NODE(int end, long cost, int card) {
            this.end = end;
            this.cost = cost;
            this.card = card;
        }

        public int compareTo(NODE o) {
            if (this.cost > o.cost) {
                return 1;
            } else {
                return -1;
            }
        }
    }
}