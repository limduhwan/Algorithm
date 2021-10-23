package B08_목_위상정렬;

import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
김희성 프로(SW역량강화TF)
알고리즘 유형 : DFS + 위상 정렬

풀이
1. DFS 로 메인로드의 말단 지점 2개를 구한다.
2. 메인로드의 말단 지점 2개를 제외하여 위상 정렬의 시작 지점으로 두고 답을 구한다.

죠르디는 사랑입니다♥
 */

//(입력)
//2
//11
//1 2 15 3
//1 3 20 10
//1 4 10 5
//4 5 5 2
//5 6 3 1
//6 7 30 6
//5 8 15 15
//8 9 15 5
//8 10 3 1
//10 11 20 15
//3
//1 2 87 162
//3 1 40 115
//
//
//
//
//(출력)
//
//#1 16
//#2 0

public class No_01_메인로드_위상정렬_강사님 {

    private static class Load {
        int dest;
        int pop; // (population) 유동인구 수
        int cost; // 도로의 길이

        public Load(int dest, int pop, int cost) {
            this.dest = dest;
            this.pop = pop;
            this.cost = cost;
        }
    }

    private static ArrayList<Load>[] adjList;
    private static boolean[] visited;
    private static int[] sum_cnt, inputEdgeCount;
    private static long[] sum_pop;
    private static long main_cost, result;
    private static int main_a, main_b;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("No_메인로드.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken()); // 상점의 수

            adjList = new ArrayList[N + 1];

            for (int i = 0; i <= N; i++) {
                adjList[i] = new ArrayList<>();
            }

            visited = new boolean[N + 1];
            sum_cnt = new int[N + 1];
            sum_pop = new long[N + 1];
            inputEdgeCount = new int[N + 1];

            for (int i = 1; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int A = Integer.parseInt(st.nextToken());
                int B = Integer.parseInt(st.nextToken());
                int pop = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());
                adjList[A].add(new Load(B, pop, cost));
                adjList[B].add(new Load(A, pop, cost));
                inputEdgeCount[A]++;
                inputEdgeCount[B]++;
            }

            // 메인로드에 해당하는 말단 정점 2개를 구한다.
            main_cost = 0;
            dfs(1);

            // 위상 정렬로 답 구하기
            result = 0;
            topologicalSort(N);

            bw.write("#" + tc + " " + result + "\n");
            bw.flush();
        }
    }

    // 위상 정렬
    private static void topologicalSort(int N) {
        // BFS 탐색을 위한 큐
        ArrayDeque<Integer> queue = new ArrayDeque<>();

        // 정점의 개수를 1개로 초기화
        Arrays.fill(sum_cnt, 1);
        // visited 배열 재사용
        Arrays.fill(visited, false);

        // 자신으로 들어오는 간선이 1개만 있는 경우 정렬 시작점이다. -> indgree == 1
        for (int i = 1; i <= N; i++) {
            // 메인로드의 말단은 제외
            if (i == main_a || i == main_b)
                continue;
            if (inputEdgeCount[i] == 1)
                queue.add(i);
        }

        while (!queue.isEmpty()) {
            int now = queue.poll();

            visited[now] = true; // 방문 체크

            for (Load loads : adjList[now]) {
                if (visited[loads.dest]) // 방문한 정점은 pass
                    continue;

                result += (long) sum_cnt[now] * loads.cost;
                sum_cnt[loads.dest] += sum_cnt[now];

                if (--inputEdgeCount[loads.dest] == 1)
                    queue.add(loads.dest);
            }
        }
    }

    // 메인로드에 해당하는 말단 정점 2개를 구한다.
    private static int dfs(int now) {
        int first_leaf = now, second_leaf = 0;
        long first = 0, second = 0;
        visited[now] = true;
        for (Load load : adjList[now]) {
            if (!visited[load.dest]) {
                int x = dfs(load.dest);
                // 자식 트리 중 1, 2 번째 MAX 유동인구를 구함
                long child_pop = sum_pop[load.dest] + load.pop;

                if (first <= child_pop) {
                    // 첫 번째 MAX 값 보다 더 큰 값이 있으면,
                    // 기존 첫 번째 MAX 값은 두 번째 MAX 값이 된다.
                    second = first;
                    second_leaf = first_leaf;
                    first = child_pop;
                    first_leaf = x;
                } else if (second < child_pop) {
                    second = child_pop;
                    second_leaf = x;
                }
            }
        }

        sum_pop[now] = first;

        // cost 가 MAX 가 되는 leaf 정점 두개를 찾아야함
        if (main_cost < first + second) {
            main_cost = first + second;
            main_a = first_leaf;
            main_b = second_leaf;
        }

        return first_leaf;
    }
}