package B02_위상정렬;

import java.io.*;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

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
