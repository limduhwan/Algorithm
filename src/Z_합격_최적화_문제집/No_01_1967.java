package Z_합격_최적화_문제집;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

//https://www.acmicpc.net/problem/1967
//https://lmcoa15.tistory.com/56
public class No_01_1967 {

    static int N, weight_, maxNode, maxWeight;
    static boolean[] checked;
    static int[] weightNode;
    static ArrayList<Node>[] al;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("No_1967.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        System.out.println("N " +N);

        checked = new boolean[N+1];
        weightNode = new int[N+1];
        al = new ArrayList[N+1];

        for(int i = 1; i<=N; i++){
            al[i] = new ArrayList<>();
        }

        for(int i = 1; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            al[start].add(new Node(end, weight));
            al[end].add(new Node(start, weight));
        }

        dfs(1, 0);

        System.out.println(maxNode+ " "+ maxWeight);

        maxWeight = 0;
        Arrays.fill(checked, false);
        dfs(maxNode, 0);
//        System.out.println(checked[maxNode]);
        System.out.println(maxNode+ " "+ maxWeight);
    }

    static void dfs(int start, int wei) {
        checked[start] = true;

        for(int i = 0; i<al[start].size(); i++){
            int end = al[start].get(i).point;
            int weight = al[start].get(i).weight;

            if (al[start].size() == 1 && checked[end]) {
                if(weight_ > maxWeight){
                    maxWeight = weight_;
                    maxNode = start;
                }
            }

            if(!checked[end]){
                weight_ = wei + weight;
                dfs(end, wei + weight);
            }
        }
    }

    static class Node {
        int point;
        int weight;

        Node(int point, int weight){
            this.point = point;
            this.weight = weight;
        }
    }
}
