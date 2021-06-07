package B04_최소공통조상;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//https://www.acmicpc.net/problem/3584
//https://velog.io/@pss407/%EB%B0%B1%EC%A4%803584-%EA%B0%80%EC%9E%A5-%EA%B0%80%EA%B9%8C%EC%9A%B4-%EA%B3%B5%ED%86%B5-%EC%A1%B0%EC%83%81
public class No_04_3584 {
    static ArrayList<Integer>[] tree;
    static int[] depth;
    static int[] parent;
    static int N;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new FileReader("No_3584.txt"));
        int T = Integer.parseInt(br.readLine());

        for(int t=0; t<T; t++) {
            N = Integer.parseInt(br.readLine());
            depth = new int[N+1];
            parent = new int[N+1];
            int[] cnt = new int[N+1];
            tree = new ArrayList[N+1];
            for(int i=1; i<=N; i++)
                tree[i] = new ArrayList<>();

            String[] input;
            for(int i=0; i<N-1; i++) {
                input = br.readLine().split(" ");
                int a = Integer.parseInt(input[0]);
                int b = Integer.parseInt(input[1]);

                tree[a].add(b);
                cnt[b]++;         //부모 있는 지 체크
            }

            int root = 0;
            for(int i=1; i<=N; i++) {
                if(cnt[i]==0) {
                    root = i;     //부모가 없는 노드가 루트
                    break;
                }
            }

            make_tree(root);      //루트부터 트리 만들기

            input = br.readLine().split(" ");

            System.out.println(lca(Integer.parseInt(input[0]), Integer.parseInt(input[1])));
        }
    }

    public static int lca(int a, int b) {   //최단거리 공통 조상 찾는 알고리즘

        while(true) {
            if(a==b)
                return a;

            if(depth[a]==depth[b]) {
                while(a!=b) {
                    a = parent[a];
                    b = parent[b];
                }
            }

            else if(depth[a]<depth[b]) {
                while(depth[a]<depth[b]) {
                    b = parent[b];
                }
            }

            else {
                while(depth[a]>depth[b]) {
                    a = parent[a];
                }
            }
        }
    }

    public static void make_tree(int root) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(root);

        while(!queue.isEmpty()) {
            int temp = queue.poll();

            for(int child : tree[temp]) {
                if(parent[child]!=0) continue;

                parent[child] = temp;
                depth[child] = depth[temp]+1;   //부모로부터 깊이 1씩 증가
                queue.add(child);
            }
        }
    }
}