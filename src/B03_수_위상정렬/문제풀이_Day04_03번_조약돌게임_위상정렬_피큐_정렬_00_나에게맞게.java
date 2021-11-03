package B03_수_위상정렬;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

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
public class 문제풀이_Day04_03번_조약돌게임_위상정렬_피큐_정렬_00_나에게맞게 {
    static int T, Answer, Knum, visitCnt, N, M, K;
    static ArrayList<Integer>[] edgeList;
    static int edgeCnt[];
    static PriorityQueue<Integer> pq;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("No_조약돌게임.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T ; t++) {
            st = new StringTokenizer(br.readLine());
            Answer = 0;
            Knum = 0;
            visitCnt = 0;

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            edgeList = new ArrayList[N+1];
            edgeCnt = new int[N+1];

            pq = new PriorityQueue<>();

            for (int i = 0; i <= N ; i++) {
                edgeList[i] = new ArrayList<>();
            }

            for (int i = 1; i <= M ; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int p = Integer.parseInt(st.nextToken());
                int q = Integer.parseInt(st.nextToken());

                if(p > q){
                    edgeList[a].add(b);
                    Answer += p;
                    edgeCnt[b]++;
                }else if(p<q){
                    edgeList[b].add(a);
                    Answer += q;
                    edgeCnt[a]++;
                }else{
                    Answer += p;
                }
            }

            for (int i = 1; i <= N ; i++) {
                if(edgeCnt[i] == 0){
                    pq.add(i);
                }
            }

            while(!pq.isEmpty()){
                int start = pq.poll();
                visitCnt++;

                if(visitCnt == K){
                    Knum = start;
                    break;
                }

                for(int end : edgeList[start]){
                    edgeCnt[end]--;
                    if(edgeCnt[end] == 0){
                        pq.add(end);
                    }
                }
            }

            System.out.println(Answer +" "+ Knum);
        }

    }
}
