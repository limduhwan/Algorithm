package B01_최소공통조상;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

//https://www.acmicpc.net/problem/11437
//https://iamheesoo.github.io/blog/algo-boj11437
//LCA
public class No_02_11437 {
    static int n;
    static ArrayList<ArrayList<Integer>> list;
    static int[] depth, parent;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("No_11437.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());

        list = new ArrayList<>();

        for(int i=0; i<=n; i++){
            list.add(new ArrayList<Integer>());
        }

        //그래프 만들기
        for(int i=0; i<n-1; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            list.get(a).add(b);
            list.get(b).add(a);
        }

        depth = new int[n+1]; // 왜 n+1이지????
        parent = new int[n+1]; // 왜 n+1이지????로
        dfs(1,1);

        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            System.out.println(solve(a, b));
        }

    }

    public static void dfs(int node, int cnt) { //방문노드, 현재깊이
        depth[node] = cnt;

        for(int child: list.get(node)){ // 노드와 연결된 것들 중에
            if(depth[child]==0) { // 깊이 계산이 안된 곳은 자식 노드이므
                dfs(child, cnt+1);
                parent[child]=node;
            }
        }
    }

    public static int solve(int a, int b) {
        while (depth[a]>depth[b]){ //a가 더 밑에 있다면
            a = parent[a];
        }

        while (depth[a]<depth[b]){//b가 더 밑에 있다면
            b=parent[b];
        }

        while(a!=b){ // 같은 층인 같지 않다면(부모가 다르다면) 같은 부모를 찾을 때까지 반복
            a=parent[a];
            b=parent[b];
        }

        return a;
    }
}

