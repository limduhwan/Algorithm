package B18_단절점;

import java.io.*;
import java.util.StringTokenizer;

//트리특성 문제
//단절점과 단절선 찾기
//정점의 간선 개수가 2개 이상이면 단절점
//트리에서 모든 간선은 단절선
public class No_06_14675_단절점_단절선 {
    static int[][] tree;
    static int N, Q;
    static int[] indegree, outdegree;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("No_14675.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        tree = new int[N+1][N+1];
        indegree = new int[N+1];
        outdegree = new int[N+1];

        for(int i = 1; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            tree[a][b] = 1;
            tree[b][a] = 1;
            outdegree[a]++;
            indegree[b]++;
        }

        Q = Integer.parseInt(br.readLine());
        for(int i = 0; i<Q; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(a == 1){

                System.out.println(indegree[b]+outdegree[b] >= 2 ? true : false);
            }else if(a == 2){
                System.out.println(true);
            }

        }
    }
}
