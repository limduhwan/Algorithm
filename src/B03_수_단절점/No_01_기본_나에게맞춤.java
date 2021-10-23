package B03_수_단절점;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

public class No_01_기본_나에게맞춤 {

    static ArrayList<Integer>[] adjList;
    static int[] order;
    static boolean[] isCut;
    static int number;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken()); // 정점 수
            int E = Integer.parseInt(st.nextToken()); // 간선 수

            adjList = new ArrayList[V+1];
            order = new int[V+1];
            isCut = new boolean[V+1];

            for (int i = 1; i <=V ; i++) {
                adjList[i] = new ArrayList<>();
            }

            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());

                adjList[from].add(to);
                adjList[to].add(from);
            }

            number = 1;

            for (int i = 1; i <=V; i++) {
                if(order[i] == 0){
                    dfs(i, true);
                }
            }

            int cnt = 0;
            for (int i = 1; i <=V ; i++) {
                if(isCut[i]){
                    cnt++;
                    System.out.println(i+" ");
                }
            }
            System.out.println("count : "+cnt);
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

            if(!isRoot && order[here] <= min){
                isCut[here] = true;
            }

            low = Math.min(low, min);

        }

        if(isRoot){
            isCut[here] = (child >=2);
        }

        return low;
    }

}