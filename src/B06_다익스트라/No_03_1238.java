package B06_다익스트라;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class No_03_1238 {

    static int N, M, X;
    static ArrayList<ArrayList<Node>> al;
    static int[] di;

    static class Node implements Comparable<Node>{
        int point;
        int weight;

        public Node(String point, String weight){
            this.point = point;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return weight - o.weight;
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

        System.out.println(N+" "+M+" "+X);

        al = new ArrayList<ArrayList<Node>>();

        for(int i = 0; i<N; i++){
            al.add(i, new ArrayList<Node>());
        }

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

al.add(st.nextToken(), new ArrayList<new Node(st.nextToken(),       st.nextToken())>());
        }

    }
}
