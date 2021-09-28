package B01_월_LCA;

//문제
//N(2 ≤ N ≤ 100,000)개의 정점으로 이루어진 트리가 주어진다.
//트리의 각 정점은 1번부터 N번까지 번호가 매겨져 있으며, 루트는 1번이다.
//
//두 노드의 쌍 M(1 ≤ M ≤ 100,000)개가 주어졌을 때,
//두 노드의 가장 가까운 공통 조상이 몇 번인지 출력한다.
//
//입력
//첫째 줄에 노드의 개수 N이 주어지고,
//다음 N-1개 줄에는 트리 상에서 연결된 두 정점이 주어진다.
//그 다음 줄에는 가장 가까운 공통 조상을 알고싶은 쌍의 개수 M이 주어지고,
//다음 M개 줄에는 정점 쌍이 주어진다.
//
//출력
//M개의 줄에 차례대로 입력받은 두 정점의 가장 가까운 공통 조상을 출력한다.


import java.io.*;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

//15 ->노드 개수 N
//1 2
//1 3
//2 4
//3 7
//6 2
//3 8
//4 9
//2 5
//5 11
//7 13
//10 4
//11 15
//12 5
//14 7
//6 -> M 알고싶은 쌍의 개수
//6 11
//10 9
//2 6
//7 6
//8 13
//8 15

//2
//4
//2
//1
//3
//1
//https://subbak2.tistory.com/60
//https://www.acmicpc.net/problem/11438
public class 기본_No_05_11438_LCA_03번_반복중 {
    static int N,M,K;
    static int[][] arr, parent;
    static boolean[] isVisit;
    static int[] depth;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("No_11438.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new int[N+1][N+1];
        parent = new int[N+1][N+1];
        isVisit = new boolean[N+1];
        depth = new int[N+1];

//        System.out.println(N);
        for (int i = 0; i <N-1 ; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            arr[a][b] = arr[b][a] = 1;
        }

//        kdfl

        K = 0;
        int temp = 1;
        while(temp<=N){
            temp <<=1;
            K++;
        }

        bfs(1, 0);

        fillParent();

        M = Integer.parseInt(br.readLine());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            System.out.println(lca(a, b));
        }
        
    }

    static int lca(int x, int y){
        //뎁스 바꿔주기
        if(depth[x] > depth[y]){
            int temp = x;
            x = y;
            y = temp;
        }

        //y뎁스 끌올 *여기 코드 주의
        for (int i = K-1; i >= 0 ; i--) {
            if(depth[y] - depth[x] >= (1<<i)){
                y = parent[i][y];
            }
        }

        //같으면 x리턴
        if(x == y) return x;

        //위에서 내려오기 * parent 주의
        for (int i = K-1; i >= 0 ; i--) {
            if(parent[i][x] != parent[i][y]){
                x = parent[i][x];
                y = parent[i][y];
            }
        }

        return parent[0][x];
        //parent[0][x]리턴
    }

    static void fillParent(){
        for (int k = 1; k < K ; k++) {
            for (int v = 1; v <=N ; v++) {
                parent[k][v] = parent[k-1][parent[k-1][v]];
            }
        }
    }

    static void bfs(int start, int dep){

        ArrayDeque<Integer> que = new ArrayDeque<>();
        isVisit[start] = true;
        depth[start] = dep;
        que.add(start);

        while(!que.isEmpty()){
            int now = que.poll();

            for (int i = 1; i <=N ; i++) {
                if(arr[now][i] == 1 &&  isVisit[i] == false){
                    isVisit[i] = true;
                    parent[0][i] = now;
                    depth[i] = depth[now] + 1;
                    que.add(i);
                }
            }
        }
    }

}

