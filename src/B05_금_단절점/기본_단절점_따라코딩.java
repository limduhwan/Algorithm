package B05_금_단절점;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 단절점

/*
첫 번째 줄 = 테스트 케이스의 수
두 번째 줄 = V(정점의 수), E(간선의 수)
세 번째 줄 ~ = 간선의 정보

1
10 14
1 2
1 10
1 3
2 4
2 3
3 5
4 6
5 4
6 7
6 8
6 9
7 8
7 9
8 9
 */

public class 기본_단절점_따라코딩 {

    static ArrayList<Integer>[] adjList;
    static int[] order;
    static boolean[] isCut;
    static int number;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("No_단절점.txt"));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T ; tc++) {
            st = new StringTokenizer(br.readLine());

            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            adjList = new ArrayList[V+1];
            order = new int[V+1];
            isCut = new boolean[V+1];

            for (int i = 1; i <=V ; i++) {
                adjList[i] = new ArrayList<>();
            }

            for (int i = 0; i <E ; i++) {
                st = new StringTokenizer(br.readLine());

                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());

                adjList[from].add(to);
                adjList[to].add(from);
            }

            number = 1;

            for (int i = 1; i <=V ; i++) {
                if(order[i] == 0){
                    dfs(i, true);
                }
            }

            int cnt = 0;
            for (int i = 1; i <=V ; i++) {
                if(isCut[i]){
                    cnt++;
                    System.out.println(i + " ");
                }
            }

            System.out.println(cnt);



        }


    }

    static int dfs(int here, boolean isRoot){
        order[here] = number++;
        int low = order[here];
        int child = 0;

        for (int i = 0; i < adjList[here].size(); i++) {
            int next = adjList[here].get(i);

            if(order[next] > 0){
                low = Math.min(low, order[next]);
                continue;
            }

            child++;

            int min = dfs(next, false);
            //**여기가 핵심
            //정점 A가 시작 정점이 아닐 때만
            //A번 정점에서 자식 노드들이 정점 A를 거치지 않고
            //A번 보다 빠른 방문번호를 가진 정점으로 갈 수 없다면 단절점이다.
            if(!isRoot && order[here] <= min){
                isCut[here] = true;
            }

            low = Math.min(low, min);
        }

        //정점 A가 시작 정점(Root)일 때
        //자식 수가 2개 이상이면 단절점이다.
        if(isRoot){
            isCut[here] = (child >=2);
        }

        return low;
    }

}















