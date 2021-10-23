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

public class No_01_기본_강사님 {

    private static ArrayList<Integer>[] adjList;
    private static int[] order;
    private static boolean[] isCut;
    private static int number;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken()); // 정점 수
            int E = Integer.parseInt(st.nextToken()); // 간선 수

            adjList = new ArrayList[V + 1];
            order = new int[V + 1];
            isCut = new boolean[V + 1];

            for (int i = 1; i <= V; i++) {
                adjList[i] = new ArrayList();
            }

            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                adjList[from].add(to);
                adjList[to].add(from);
            }

            number = 1;

            for (int i = 1; i <= V; i++) {
                if (order[i] == 0) {
                    // root = 시작 정점인 경우
                    dfs(i, true);
                }
            }

            int cnt = 0;
            for (int i = 1; i <= V; i++) {
                if (isCut[i]) {
                    cnt++;
                    System.out.print(i + " ");
                }
            }
            System.out.println("count : " + cnt);

        }
    }

    // 단절점 탐색 : 깊이 우선 탐색
    private static int dfs(int here, boolean isRoot) {
        order[here] = number++; // DFS 탐색 순서 저장
        int low = order[here]; // low 의 초기값은 자기 자신의 order
        int child = 0; // 자식 Tree 수 count

        for (int next : adjList[here]) {
            /*
             만약 이미 DFS에서 탐색된 정점이라면
             현재 정점의 방문순서와 탐색된 정점의 방문 순서 중 min값(=low)을 찾는다.
            */
            // 이미 방문한 정점이면 Skip
            if (order[next] > 0) {
                low = Math.min(low, order[next]);
                continue;
            }

            child++;
            int min = dfs(next, false);

            /*
            정점 A가 시작 정점(root)이 아닐 때
            A번 정점에서 자식 노드들이 정점 A를 거치지 않고
            정점 A보다 빠른 방문번호를 가진 정점으로 갈 수 없다면 단절점이다.
            */
            if (!isRoot && order[here] <= min) {
                isCut[here] = true;
            }

            low = Math.min(low, min);
        }

        /*
        정점 A가 시작 정점(root)일 때
        자식 수가 2개 이상이면 단절점이다.
        */
        if (isRoot) {
            isCut[here] = (child >= 2);
        }

        return low;
    }

}