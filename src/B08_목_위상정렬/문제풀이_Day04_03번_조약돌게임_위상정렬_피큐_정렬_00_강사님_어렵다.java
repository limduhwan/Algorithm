package B08_목_위상정렬;

import java.io.IOException;
import java.io.*;
import java.util.*;

//2 - tc
//4 3 2 - N지점수  M 간선정보  K K번째 방문 지점
//1 2 2 1
//2 3 3 4
//3 4 5 4
//2 1 2
//1 2 5 7

//#1 11 3
//#2 7 1

//4일차 6/7
//18:18
public class 문제풀이_Day04_03번_조약돌게임_위상정렬_피큐_정렬_00_강사님_어렵다 {

    static BufferedWriter bw;
    static StringTokenizer st;
    static int T, N, M, K, visitCnt, Knum;
    static int edgeCnt[];  //들어오는 간선 수
    static ArrayList<Integer>[] edgeList;
    static PriorityQueue<Integer> pq;
    static long Answer;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("No_조약돌게임.txt"));
//        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            Answer = 0;
            Knum = 0;
            visitCnt = 0;//선택한 지점 수
            N = Integer.parseInt(st.nextToken()); // 지점수
            M = Integer.parseInt(st.nextToken()); // 간선정보
            K = Integer.parseInt(st.nextToken()); //K번째 방문 지점

            edgeList = new ArrayList[N + 1];
            edgeCnt = new int[N + 1];//들어오는 간선수

            pq = new PriorityQueue<Integer>();//들어오는 간선의 수가 0인 대상으로 낮은 숫자부터 저장

            for (int i = 0; i <= N; i++) {
                edgeList[i] = new ArrayList<Integer>();
            }

            // 1. 간선정보 저장하면서 최종점수 미리 저장
            for (int i = 1; i <= M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken()); // 시작
                int b = Integer.parseInt(st.nextToken()); // 끝
                int p = Integer.parseInt(st.nextToken()); // a->b 값
                int q = Integer.parseInt(st.nextToken()); // b->a 값

                // p, q 두점수중에 큰 점수 선택

                if (p > q) {  //a->b 쓰는 경우
                    edgeList[a].add(b);
                    Answer += p;
                    edgeCnt[b]++;
                } else if (p < q) { //b->a 쓰는 경우
                    edgeList[b].add(a);
                    Answer += q;
                    edgeCnt[a]++;
                } else { //같으면 그냥 더한다. (둘중 아무거나)
                    Answer += p;
                }
            }

            //2. 들어오는 간선이 0인 지점 찾아서, pq에 넣기
            for (int i = 1; i <= N; i++) {
                if (edgeCnt[i] == 0) {
                    pq.add(i);//구슬번호가 작은순으로 자동정렬 삽입 - 사전순으로 빠른것 출력
                }
            }

            // 3. pq에 구슬번호 순으로 넣었다 뽑으면서 , 방문 순서 찾기
            while (!pq.isEmpty()) {
                int start = pq.poll();
                visitCnt++;

                if (visitCnt == K) {
                    Knum = start;
                    break;
                }

                for (int end : edgeList[start]) {  //해당 지점으로 시작하는 모든 값 합산
                    edgeCnt[end]--; //사용한 횟수만큼 제거
                    if (edgeCnt[end] == 0) {  //새로운 시작점 후보
                        pq.add(end);
                    }
                }
            }

            bw.write("#" + t + " " + Answer + " " + Knum + "\n");
        }

        bw.flush();
        br.close();
    }
}
