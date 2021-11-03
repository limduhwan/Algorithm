package B03_수_위상정렬;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
//2
//4 4
//10 1 100 10
//1 2
//1 3
//2 4
//3 4
//4
//8 8
//10 20 1 5 8 7 1 43
//1 2
//1 3
//2 4
//2 5
//3 6
//5 7
//6 7
//7 8
//7
//
//120
//39
//https://www.acmicpc.net/problem/1005
//https://moonsbeen.tistory.com/134
public class No_05_1005_ACM크래프트_인터넷 {
    static int n, w;
    static ArrayList<Integer>[] list; //연결 간선 정보
    static int[] building; //빌딩 짓는 비용 정보
    static int[] indegree;
    static int[] buildCost; //각 위치까지 빌딩을 짓는 비용의 최대값을 저장한다.

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("No_1005.txt"));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());
        for(int i = 0; i < t; i++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            building = new int[n + 1];
            list = new ArrayList[n + 1];
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= n; j++) {
                building[j] = Integer.parseInt(st.nextToken());
                list[j] = new ArrayList<>();
            }

            indegree = new int[n + 1];

            for(int j = 0; j < k; j++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                list[s].add(e);
                indegree[e]++;
            }
            w = Integer.parseInt(br.readLine());; //건설해야 할 건물의 번호

            buildCost = new int[n + 1];
            topologySort();
            System.out.println(buildCost[w]);
        }
    }

    public static void topologySort() {
        Queue<Integer> q = new LinkedList<>();
        for(int i = 1; i < indegree.length; i++) {
            if(indegree[i] == 0) {
                buildCost[i] = building[i];
                q.offer(i);
            }
        }

        while(!q.isEmpty()) {
            int current = q.poll();

            for(int i = 0; i < list[current].size(); i++) {
                int next = list[current].get(i);
                buildCost[next] = Math.max(buildCost[current] + building[next], buildCost[next]);
                indegree[next]--;
                if(indegree[next] == 0) q.offer(next);
            }
        }
    }
}
