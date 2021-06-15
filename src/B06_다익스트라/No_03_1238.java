package B06_다익스트라;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class No_03_1238 {

    static int N, M, X;
    static ArrayList<ArrayList<Node>> al;
    static int[] di, reverse;

    static class Node implements Comparable<Node>{
        int point;
        int weight;

        public Node(int point, int weight){
            this.point = point;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("No_1238.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

//        System.out.println(N+" "+M+" "+X);

        al = new ArrayList<>();

        for(int i = 0; i<=N; i++){
            al.add(new ArrayList<>());
        }

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
//            System.out.println(start+" "+end+" "+weight);

            al.get(start).add(new Node(end, weight));
        }

        di = new int[N+1];
        reverse = new int[N+1];

        Arrays.fill(di, Integer.MAX_VALUE);
        Arrays.fill(reverse, Integer.MAX_VALUE);

        dij(X);

        for(int i = 0; i<di.length-1; i++){
            System.out.println(di[i]);
        }
    }

    static void dij(int x){
        PriorityQueue<Node> queue= new PriorityQueue<Node>();
        boolean[] visited = new boolean[N+1];
        visited[x] = true;
        queue.add(new Node(x, 0));

        di[x] = 0;

        while(!queue.isEmpty()){
            int index = queue.poll().point;

            if(visited[index]) continue;
            visited[index] = true;

            for(Node node: al.get(index)){
                if(di[node.point] > di[index] + node.weight ){
                    di[node.point] = di[index] + node.weight;
                    queue.add(new Node(node.point, di[node.point]));
                }
            }
        }
    }
}
