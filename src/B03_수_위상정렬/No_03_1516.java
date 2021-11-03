package B03_수_위상정렬;

import java.io.*;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//게임개발
//문제
//숌 회사에서 이번에 새로운 전략 시뮬레이션 게임 세준 크래프트를 개발하기로 하였다.
//핵심적인 부분은 개발이 끝난 상태고,
//종족별 균형과 전체 게임 시간 등을 조절하는 부분만 남아 있었다.
//
//게임 플레이에 들어가는 시간은 상황에 따라 다를 수 있기 때문에,
//모든 건물을 짓는데 걸리는 최소의 시간을 이용하여 근사하기로 하였다.
//물론, 어떤 건물을 짓기 위해서 다른 건물을 먼저 지어야 할 수도 있기 때문에 문제가 단순하지만은 않을 수도 있다.
//예를 들면 스타크래프트에서 벙커를 짓기 위해서는 배럭을 먼저 지어야 하기 때문에,
//배럭을 먼저 지은 뒤 벙커를 지어야 한다.
//여러 개의 건물을 동시에 지을 수 있다.
//
//편의상 자원은 무한히 많이 가지고 있고,
//건물을 짓는 명령을 내리기까지는 시간이 걸리지 않는다고 가정하자.
//
//입력
//첫째 줄에 건물의 종류 수 N(1 ≤ N ≤ 500)이 주어진다.
//다음 N개의 줄에는 각 건물을 짓는데 걸리는 시간과
//그 건물을 짓기 위해 먼저 지어져야 하는 건물들의 번호가 주어진다.
//건물의 번호는 1부터 N까지로 하고, 각 줄은 -1로 끝난다고 하자.
//각 건물을 짓는데 걸리는 시간은 100,000보다 작거나 같은 자연수이다.
//모든 건물을 짓는 것이 가능한 입력만 주어진다.
//
//출력
//N개의 각 건물이 완성되기까지 걸리는 최소 시간을 출력한다.

public class No_03_1516 {
    static int N;
    static ArrayList[] al;
    static int[] indgree, cost;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("No_1516.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());

        //1, 2, 3, 4, 5 순차적 실행이라 배열을 사용했다.
        al = new ArrayList[N+1];
        //진입차수를 저장할 배열
        indgree = new int[N+1];
        //각 건물의 걸리는 비용
        cost = new int[N+1];

        //Array배열 초기화
        for(int i=1; i<=N; i++){
            al[i] = new ArrayList();
        }

        for(int i = 1; i<=N; i++){
            st = new StringTokenizer(br.readLine());
            int cost_ = Integer.parseInt(st.nextToken());

            cost[i] = cost_;

            //이 부분이 약간 애매. 맥스가 3개가 3개를 돌려줬다.
            for(int j = 0; j<3; j++){
                int a = Integer.parseInt(st.nextToken());
                //여기서 고민
                //4는 이미 3과 연결돼 있기 때문에... 빼고 저장
                if(a != -1 && j!=1){
                    //여기서 주의.
                    al[a].add(i);
                    indgree[i]++;
                }else{
                    break;
                }
            }
        }

//        System.out.println(al[5]);
        //가장 작은 진입차수를 가진 녀석을 골라준다.
        int start = Integer.MAX_VALUE;
        for(int i=1; i<=N; i++){
            if(indgree[i]<start){
                start = i;
            }
        }
//        System.out.println(start);

        PriorityQueue<Integer> queue = new PriorityQueue<>();

        queue.add(start);

        while(!queue.isEmpty()){
            int point = queue.poll();

            //해당 정점과 연결된 정점을 하나씩 수행
            for(int i = 0; i<al[point].size(); i++){
                //해당 정점과 연결된 정점을 가져와서
                int a = (int)al[point].get(i);
                queue.add(a);
                //해당 정점의 진입차수를 하나씩 제거해 주고
                indgree[a]--;
                //비용 정보도 업데이트 해 준다.
                cost[a] = cost[point] + cost[a];
            }
        }

        for(int i = 1; i< cost.length; i++){
//            System.out.println(cost[i]);
            bw.write(cost[i] + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
