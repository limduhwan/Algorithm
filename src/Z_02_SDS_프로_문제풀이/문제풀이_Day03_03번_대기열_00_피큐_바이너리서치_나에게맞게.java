package Z_02_SDS_프로_문제풀이;

import java.io.*;
import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
중요!!! : 주석 삭제 금지 / 동일 코드 SW검정 사이트 제출 금지
김희성 프로(SW역량강화TF)
알고리즘 유형 : 우선순위 큐 + 바이너리 서치 (???)

풀이
1. 우선순위 큐를 사용해서 작업을 처리할 서버를 구할 수 있다.
2. 작업을 처리할 서버를 구하면서, 각 서버별 배열에 각 작업이 끝나는 시점을 순서대로 저장한다.
3. 쿼리가 들어오면 서버 배열에서 Lower Bound 를 구해서 모니터링 시간을 뺀다.

죠르디는 사랑입니다♥
 */

//https://platform.samsungcic.com/#/connect/LCB20210329100061572
//3일차 (5/6) 25:45
//코드 30:13
//Collection.binarysearch와 오브젝트의 compare로 오름, 내림 하는 거를 잘 알아야 한다.

//#1 6
//#2 4

//이거 알아야 함
//Collections.binarySearch

//compareTo 정렬 다시 익혀두자
//public int compareTo(Server o){
//        if(this.idx == o.idx){
//        return this.num - o.num;
//        }else{
//        return this.idx - o.idx;
//        }
//        }
public class 문제풀이_Day03_03번_대기열_00_피큐_바이너리서치_나에게맞게 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("No_대기열.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken()); // 요청의 개수
            int K = Integer.parseInt(st.nextToken()); // 서버의 개수
            int Q = Integer.parseInt(st.nextToken()); // 모니터링을 하는 시각의 수

            PriorityQueue<Server> pq = new PriorityQueue<>();

            ArrayList<Integer>[] task = new ArrayList[K+1];

            for (int i = 1; i < K+1 ; i++) {
                task[i] = new ArrayList<>();
                pq.add(new Server(i, 0));
            }

            st = new StringTokenizer(br.readLine());

            for (int i = 0; i < N; i++) {
                Server now = pq.poll();
                now.idx = now.idx + Integer.parseInt(st.nextToken());
                task[now.num].add(now.idx);
                pq.add(now);
            }

            int result = 0;

            for (int i = 0; i < Q; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                //여기 중요
                int idx = Math.abs(Collections.binarySearch(task[a], b) + 1);

                if(task[a].size() > idx){
                    result = result + task[a].get(idx) - b;
                }
            }

            System.out.println(result);

        }
    }

    static class Server implements Comparable<Server>{
        int num;
        int idx;
        Server(int num, int idx){
            this.num = num;
            this.idx = idx;
        }

        public int compareTo(Server o){
            if(this.idx == o.idx){
                return this.num - o.num;
            }else{
                return this.idx - o.idx;
            }
        }
    }
}