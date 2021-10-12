package B04_목_최단거리_다익스트라;
//문제
//요즘 많은 자동차에서는 GPS 네비게이션 장비가 설치되어 있다.
// 네비게이션은 사용자가 입력한 출발점과 도착점 사이의 최단 경로를 검색해 준다.
// 하지만, 교통 상황을 고려하지 않고 최단 경로를 검색하는 경우에는 극심한 교통 정체를 경험할 수 있다.
//
//상근이는 오직 자기 자신만 사용 가능한 네비게이션을 만들고 있다.
// 이 네비게이션은 절대로 최단 경로를 찾아주지 않는다.
// 항상 거의 최단 경로를 찾아준다.
//
//거의 최단 경로란 최단 경로에 포함되지 않는
// 도로로만 이루어진 경로 중 가장 짧은 것을 말한다.
//
//예를 들어, 도로 지도가 아래와 같을 때를 생각해보자.
// 원은 장소를 의미하고, 선은 단방향 도로를 나타낸다.
// 시작점은 S, 도착점은 D로 표시되어 있다. 굵은 선은 최단 경로를 나타낸다.
// (아래 그림에 최단 경로는 두 개가 있다)거의 최단 경로는 점선으로 표시된 경로이다.
// 이 경로는 최단 경로에 포함되지 않은 도로로 이루어진 경로 중 가장 짧은 경로이다.
// 거의 최단 경로는 여러 개 존재할 수도 있다.
// 예를 들어, 아래 그림의 길이가 3인 도로의 길이가 1이라면, 거의 최단 경로는 두 개가 된다.
// 또, 거의 최단 경로가 없는 경우도 있다.
//
//입력
//입력은 여러 개의 테스트 케이스로 이루어져 있다.
// 각 테스트 케이스의 첫째 줄에는 장소의 수 N (2 ≤ N ≤ 500)과
// 도로의 수 M (1 ≤ M ≤ 104)가 주어진다. 장소는 0부터 N-1번까지 번호가 매겨져 있다.
// 둘째 줄에는 시작점 S와 도착점 D가 주어진다.
// (S ≠ D; 0 ≤ S, D < N)
// 다음 M개 줄에는 도로의 정보 U, V, P가 주어진다.
// (U ≠ V ; 0 ≤ U, V < N; 1 ≤ P ≤ 103)
// 이 뜻은 U에서 V로 가는 도로의 길이가 P라는 뜻이다.
// U에서 V로 가는 도로는 최대 한 개이다.
// 또, U에서 V로 가는 도로와 V에서 U로 가는 도로는 다른 도로이다.
//
//입력의 마지막 줄에는 0이 두 개 주어진다.
//
//출력
//각 테스트 케이스에 대해서, 거의 최단 경로의 길이를 출력한다.
// 만약, 거의 최단 경로가 없는 경우에는 -1을 출력한다.

import java.io.*;
import java.util.*;

//7 9 -> 장소수, 도로
//0 6 -> 시작점, 도착점
//0 1 1
//0 2 1
//0 3 2
//0 4 3
//1 5 2
//2 6 4
//3 6 2
//4 6 4
//5 6 1
//4 6
//0 2
//0 1 1
//1 2 1
//1 3 1
//3 2 1
//2 0 3
//3 0 2
//6 8
//0 1
//0 1 1
//0 2 2
//0 3 3
//2 5 3
//3 4 2
//4 1 1
//5 1 1
//3 0 1
//0 0

//5
//-1
//6
//https://rlaguswhd-organize.tistory.com/15
//다익스트라해주고 최단경로를 지워주고 다시 다익스트라
public class No_26_5719_거의최단경로_나에게맞게 {
    static int N, M, S,D;
    static int[] distance;
    static int[][] list;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("No_5719.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        while(true){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            if(N==0 && M==0){
                break;
            }

            distance = new int[N+1];
            list = new int[N+1][N+1];

            st = new StringTokenizer(br.readLine());
            S = Integer.parseInt(st.nextToken());
            D = Integer.parseInt(st.nextToken());

            int now, next, cost;
            for (int i = 1; i <=M ; i++) {
                st = new StringTokenizer(br.readLine());
                now = Integer.parseInt(st.nextToken());
                next = Integer.parseInt(st.nextToken());
                cost = Integer.parseInt(st.nextToken());

                list[now][next] = cost;
            }

            Arrays.fill(distance, Integer.MAX_VALUE);
            distance[S] = 0;
            dijkstra();

            deleteNode();

            Arrays.fill(distance, Integer.MAX_VALUE);
            distance[S] = 0;
            dijkstra();
            if(distance[D] == Integer.MAX_VALUE){
                System.out.println("-1");
            }else {
                System.out.println(distance[D]);
            }
        }
    }

    static void deleteNode(){
        ArrayDeque<Integer> que = new ArrayDeque<>();
        que.add(D);

        while(!que.isEmpty()){
            int now = que.poll();

            for (int i = 0; i < N; i++) {
                if(list[i][now] == 0){
                    continue;
                }

                if(distance[now] == distance[i] + list[i][now]){
                    list[i][now] = 0;
                    que.add(i);
                }
            }
        }
    }

    static void dijkstra(){
        PriorityQueue<Point> que = new PriorityQueue<Point>();
        que.add(new Point(S, 0));

        while(!que.isEmpty()){
            Point now = que.poll();
            //이 코드를 위해서
            //que.add(new Point(i, distance[i]));
//          //이걸하는 거다
            if(now.cost > distance[now.node]){
                continue;
            }

            for (int i = 0; i <N ; i++) {
                if(list[now.node][i] == 0){
                    continue;
                }

                if(distance[i] > distance[now.node] + list[now.node][i]){
                    distance[i] = distance[now.node] + list[now.node][i];

                    que.add(new Point(i, distance[i]));
                }
            }
        }
    }


    static class Point implements Comparable<Point>{
        int node;
        int cost;

        Point(int node, int cost){
            this.node = node;
            this.cost = cost;
        }

        public int compareTo(Point o){
            return Integer.compare(this.cost, o.cost);
        }
    }

}
