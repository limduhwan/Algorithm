package B05_월_최단거리_Fluid_Washal;

import java.io.*;
import java.util.StringTokenizer;

//문제
//n(2 ≤ n ≤ 100)개의 도시가 있다. 그리고 한 도시에서 출발하여 다른 도시에 도착하는 m(1 ≤ m ≤ 100,000)개의 버스가 있다.
//각 버스는 한 번 사용할 때 필요한 비용이 있다.
//
//모든 도시의 쌍 (A, B)에 대해서 도시 A에서 B로 가는데 필요한 비용의 최솟값을 구하는 프로그램을 작성하시오.
//
//입력
//첫째 줄에 도시의 개수 n이 주어지고 둘째 줄에는 버스의 개수 m이 주어진다.
//그리고 셋째 줄부터 m+2줄까지 다음과 같은 버스의 정보가 주어진다.
//먼저 처음에는 그 버스의 출발 도시의 번호가 주어진다.
//버스의 정보는 버스의 시작 도시 a, 도착 도시 b, 한 번 타는데 필요한 비용 c로 이루어져 있다.
//시작 도시와 도착 도시가 같은 경우는 없다. 비용은 100,000보다 작거나 같은 자연수이다.
//시작 도시와 도착 도시를 연결하는 노선은 하나가 아닐 수 있다.
//
//출력
//n개의 줄을 출력해야 한다. i번째 줄에 출력하는 j번째 숫자는 도시 i에서 j로 가는데 필요한 최소 비용이다.
//만약, i에서 j로 갈 수 없는 경우에는 그 자리에 0을 출력한다.

//5
//14
//1 2 2
//1 3 3
//1 4 1
//1 5 10
//2 4 2
//3 4 1
//3 5 1
//4 5 3
//3 5 10
//3 1 8
//1 4 2
//5 1 7
//3 4 2
//5 2 4

//0 2 3 1 4
//12 0 15 2 5
//8 5 0 1 1
//10 7 13 0 3
//7 4 10 6 0
//https://pangsblog.tistory.com/90
//https://www.acmicpc.net/problem/11404
public class 기본_No_01_11404_02번_반복중 {

    static int[][] resultArr, Arr;
    static int cityCount;
    static int busCount;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("No_11404.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = null;

        cityCount = Integer.parseInt(br.readLine());
        busCount = Integer.parseInt(br.readLine());

        resultArr = new int[cityCount+1][cityCount+1];
        Arr = new int[cityCount+1][cityCount+1];

        for (int i = 1; i <= cityCount; i++) {
            for (int j = 1; j <= cityCount ; j++) {
                if( i == j) {
                    continue;
                }
                //*** Integer.MAX_VALUE는 안됨!!!
                resultArr[i][j] = 1000000000;
//                resultArr[i][j] = Integer.MAX_VALUE-1;
            }
        }

//        System.out.println(Integer.MAX_VALUE);
        for (int i = 1; i <= busCount ; i++) {
            st = new StringTokenizer(br.readLine());

            int from  = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            resultArr[from][to] = Math.min(resultArr[from][to], cost);
        }

        fluid();

//        for (int i = 1; i < cityCount; i++) {
//            for (int j = 1; j < cityCount; j++) {
//                System.out.println(resultArr[i][j]);
//            }
//        }

        StringBuffer sb = new StringBuffer();
        for(int i = 1; i <=cityCount; i++){
            for(int j = 1; j <= cityCount; j++) {
                if(resultArr[i][j] >= 1000000000) sb.append("0 ");
                else sb.append(resultArr[i][j] + " ");
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }

    static void fluid(){
        for (int k = 1; k <= cityCount; k++) {
            for (int i = 1; i <= cityCount; i++) {
                for (int j = 1; j <= cityCount; j++) {
                    if( resultArr[i][j] > resultArr[i][k]+resultArr[k][j] ){
                        resultArr[i][j] = resultArr[i][k]+resultArr[k][j];
                    }

//                    resultArr[i][j] = Math.min(resultArr[i][k] + resultArr[k][j], resultArr[i][j]);
                }
            }
        }


    }
}
