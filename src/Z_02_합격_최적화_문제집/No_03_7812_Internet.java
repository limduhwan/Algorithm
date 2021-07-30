package Z_02_합격_최적화_문제집;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

//https://coder-in-war.tistory.com/entry/Baekjoon-JAVA7812-%EC%A4%91%EC%95%99-%ED%8A%B8%EB%A6%AC
public class No_03_7812_Internet {

    static class Node {
        int next, cost;
        Node(int next, int cost){
            this.next = next;
            this.cost = cost;
        }
    }

    static int N;
    static ArrayList<Node>[] tree;
    static long[] size, myFamily, stranger;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("No_7812.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st;
        N = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());

//        System.out.println(N);

        tree = new ArrayList[N];
        size = new long[N];
        myFamily = new long[N];
        stranger = new long[N];

        for(int i = 0; i<N; i++){
            tree[i] = new ArrayList<>();
        }

        for(int i = 1; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            tree[a].add(new Node(b, cost));
            tree[b].add(new Node(a, cost));
        }
        dfs1(0, -1);
//        dfs2(0, -1);

//        bw.write(findResult()+"\n");
        bw.flush();
        bw.close();
    }

    static void dfs1(int here, int dad){
        System.out.println("A "+ here + " " +dad);
        for(Node n : tree[here]){
            System.out.println("B " + n.next +"  "+ dad);
            if(n.next != dad){
                System.out.println("C 스택대상 "+n.next+" "+here);
                dfs1(n.next, here);
                System.out.println("E 스택에 쌓였던 거 방출 " + here+" "+ dad +" "+size[here] +"   " + size[n.next]);
                size[here] = size[here] + size[n.next];
//                myFamily[here] = myFamily[here] + myFamily[n.next] + size[n.next] * n.cost;
            }
        }
        System.out.println("D-1 for 끝나고 "+ here + " " +dad +" " +size[here]);
        size[here] ++;
        System.out.println("D-2 for 끝나고 "+ here + " " +dad +" " +size[here]);
    }

    static void dfs2(int here, int dad){
        for(Node n: tree[here]){
            if(n.next != dad){
                stranger[n.next] = stranger[n.next] + stranger[here] + (size[0]-size[n.next]) * n.cost + myFamily[here] - (myFamily[n.next] + size[n.next]*n.cost);
                dfs2(n.next, here);

            }
        }
    }

    static long findResult(){
        long min = Long.MAX_VALUE;
        for(int i=0; i<N; i++){
            min = Math.min(min, myFamily[i] + stranger[i]);
        }
        return min;
    }

}
