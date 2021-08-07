package Z_02_합격_최적화_문제집;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;
//문제
//트리는 사이클을 갖지 않는 연결된 그래프이다.
//중앙 정점은 모든 정점으로 이르는 비용의 합이 가장 작은 정점이다. 트리의 정점 개수가 작은 경우에는 모든 경우의 수를 다 계산해보는 프로그램을 이용해 쉽게 구할 수 있다.
//위의 그림은 가중치가 있는 트리로, 정점의 개수는 5개이다. 이 트리의 중앙 정점은 B이다.
//B-A = 2, B-D = 7, B-C = 1, B-E = 7+5=12, 총: 2+1+7+12 = 22
//N이 큰 경우에 문제를 풀어보자.
//트리를 입력 받아, 모든 정점과 중앙 정점까지 비용의 합을 구하는 프로그램을 작성하시오.
//
//입력
//입력은 여러 개의 테스트 케이스로 이루어져 있다. 각 테스트 케이스의 첫 줄에는 트리의 정점의 수 n이 주어진다. (1 ≤ n ≤ 10,000) 각 정점은 0번부터 n-1번까지 번호가 붙여져 있다. 다음 n-1개 줄에는 세 정수 a, b, w가 주어진다. (1 ≤ w ≤ 100) a와 b는 간선을 나타내고, w는 그 간선의 가중치이다.
//입력의 마지막 줄에는 0이 하나 주어진다.
//
//출력
//각 테스트 케이스마다 모든 정점과 중앙 정점 사이의 비용의 합을 출력한다.

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
