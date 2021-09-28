package B01_월_LCA;

import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

//정점들의 거리
//시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
//2 초	128 MB	8105	3306	2069	39.016%

//문제
//N(2 ≤ N ≤ 40,000)개의 정점으로 이루어진 트리가 주어지고
// M(1 ≤ M ≤ 10,000)개의 두 노드 쌍을 입력받을 때 두 노드 사이의 거리를 출력하라.
//
//입력
//첫째 줄에 노드의 개수 N이 입력되고 다음 N-1개의 줄에 트리 상에 연결된 두 점과 거리를 입력받는다.
// 그 다음 줄에 M이 주어지고, 다음 M개의 줄에 거리를 알고 싶은 노드 쌍이 한 줄에 한 쌍씩 입력된다.
// 두 점 사이의 거리는 10,000보다 작거나 같은 자연수이다.
//
//정점은 1번부터 N번까지 번호가 매겨져 있다.
//
//출력
//M개의 줄에 차례대로 입력받은 두 노드 사이의 거리를 출력한다.

//7 -> N 노드개수
//1 6 13 -> 두점, 거리
//6 3 9
//3 5 7
//4 1 3
//2 4 20
//4 7 2
//3 -> M 쿼리 개수
//1 6 -> 알고 싶은 두 점 사이 거리
//1 4
//2 6

//13
//3
//36

//https://www.acmicpc.net/problem/1761
//https://velog.io/@imfksh/%EB%B0%B1%EC%A4%80-1470-Java

public class 기본_No_06_1761_02번_반복중 {
    static int N, M, K;
    static int[][] arr, parent;
    static int[] depth, distance;
    static boolean[] isVisit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("No_1761.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new int[N+1][N+1];
        parent = new int[N+1][N+1];
        depth = new int[N+1];
        isVisit = new boolean[N+1];
        distance = new int[N+1];

        for (int i = 0; i <N-1 ; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            arr[a][b] = arr[b][a] = c;
        }

//        kbfl

        K = 0;
        int temp = 1;
        while(temp <= N){
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

            int lcaDistance = lca(a, b);

            int result = distance[a] +  distance[b] - (distance[lcaDistance]*2);
            System.out.println(result);
        }
    }

    static int lca(int x, int y){
        //뎁스 바꾸기
        if(depth[x] > depth[y]){
            int temp = x;
            x = y;
            y = temp;
        }
        
        //y끌올
        for (int i = K-1; i >=0 ; i--) {
            if(depth[y] - depth[x] >= (1<<i)){
                y = parent[i][y];
            }
        }

        //x=y x리턴
        if(x==y) return x;

        //위에서부터 내려오기
        for (int i = K-1; i >= 0 ; i--) {
            if(parent[i][x] != parent[i][y]){
                x = parent[i][x];
                y = parent[i][y];
            }
        }

        return parent[0][x];

    }

    static void fillParent(){
        for (int k = 1; k <K ; k++) {
            for (int v = 1; v <= N ; v++) {
                parent[k][v] = parent[k-1][parent[k-1][v]];
            }
        }
    }

    static void bfs(int start, int dep){
        ArrayDeque<Integer> que = new ArrayDeque<>();

        isVisit[start] = true;
        depth[start] = dep;
        distance[start] = 0;
        que.add(start);

        while(!que.isEmpty()){
            int now = que.poll();

            for (int i = 1; i <= N ; i++) {
                if( arr[now][i] != 0 && isVisit[i] == false){
                    isVisit[i] = true;
                    depth[i] = depth[now] + 1;
                    parent[0][i] = now;
                    distance[i] = distance[now] + arr[now][i];
                    que.add(i);
                }
            }

        }
    }

}
