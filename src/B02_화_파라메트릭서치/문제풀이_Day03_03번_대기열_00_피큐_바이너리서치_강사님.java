package B02_화_파라메트릭서치;

import java.io.IOException;
import java.io.*;
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
public class 문제풀이_Day03_03번_대기열_00_피큐_바이너리서치_강사님 {
    private static class Server implements Comparable<Server> {
        int num; // 서버 번호
        int idx; // 작업이 끝나는 시간
        public Server(int num, int idx) {
            this.num = num;
            this.idx = idx;
        }

        @Override
        public int compareTo(Server o) {
            // 작업이 끝나는 시간 기준 오름차순
            // 작업이 끝나는 시간이 같으면, 서버 번호가 빠른 순으로 꺼낸다.
            if (this.idx == o.idx)
                return this.num - o.num;
            else
                return this.idx - o.idx;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("No_대기열.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken()); // 요청의 개수
            int K = Integer.parseInt(st.nextToken()); // 서버의 개수
            int Q = Integer.parseInt(st.nextToken()); // 모니터링을 하는 시각의 수

            // 일 시킬 서버를 정하게 위한 우선순위 큐
            PriorityQueue<Server> pq = new PriorityQueue<>();
            // 각 서버별 작업이 끝나는 시간을 순서대로 저장할 배열
            ArrayList<Integer>[] task = new ArrayList[K + 1];

            // ArrayList 초기화 및 서버 전체를 pq 에 넣기
            for (int i = 1; i < K + 1; i++) {
                task[i] = new ArrayList<>();
                pq.add(new Server(i, 0));
            }

            st = new StringTokenizer(br.readLine());

            for (int i = 0; i < N; i++) {
                Server now = pq.poll(); // 다음 작업을 수행할 서버 pq 에서 꺼냄
                now.idx += Integer.parseInt(st.nextToken()); // 작업이 끝나는 시간 업데이트
                task[now.num].add(now.idx); // 서버 별 ArrayList에 작업이 끝나는 시간 추가
                pq.add(now); // 다시 pq 에 넣음
            }

            int result = 0;

            for (int i = 0; i < Q; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                // Lower Bound 를 찾음
                // binarySearch API 는 값을 못 찾을 경우 음수를 붙여서 Lower Bound 를 반환함
                int idx = Math.abs(Collections.binarySearch(task[a], b) + 1);

                // 작업을 수행 중인 범위 내에 있을 때만 계산하면 됨
                if (task[a].size() > idx)
                    result += task[a].get(idx) - b;
            }

            bw.write("#" + tc + " " + result + "\n");
            bw.flush();
        }
    }
}