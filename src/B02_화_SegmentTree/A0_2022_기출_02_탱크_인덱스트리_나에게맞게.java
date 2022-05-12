package B02_화_SegmentTree;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

//(입력)
//2
//5
//1 1 5
//2 2 4
//3 3 3
//4 4 2
//5 5 1
//9
//3 1 7
//4 4 6
//1 3 9
//7 2 3
//8 5 2
//2 6 8
//9 7 1
//5 8 5
//6 9 4
//
//
//(출력)
//
//#1 20
//#2 77


// 구간트리 (인덱스 트리)
public class A0_2022_기출_02_탱크_인덱스트리_나에게맞게 {
    static int T, N, depth, treeN, startIdx;
    static int K, StartIdx, TreeLenghth;
    static long[] tree;
    static ArrayList<Tank> tank;

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("No_탱크.txt"));

        StringTokenizer st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());

        for (int t = 1; t <= 1; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());

            //KSLT

            K = (int) Math.ceil(Math.log(N)/Math.log(2));
            StartIdx = (int) Math.pow(2, K);
            TreeLenghth = (int) Math.pow(2, K+1)-1;
            tree = new long[TreeLenghth + 1];

            tank = new ArrayList<>();

            for (int i = 1; i <= N; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());

                tank.add(new Tank(x, y, v, 0));
            }

            // X축 기준 정렬 Compartor
            Comparator<Tank> orderX = new Comparator<Tank>() {
                @Override
                public int compare(Tank o1, Tank o2) {
                    return o2.x - o1.x;
                }
            };

            // Y축 기준 정렬 Compartor
            Comparator<Tank> orderY = new Comparator<Tank>() {
                @Override
                public int compare(Tank o1, Tank o2) {
                    return o2.y - o1.y;
                }
            };

            // y축으로 내림차순 정렬
            Collections.sort(tank, orderY);

            // 리라벨링 (Y축 순서대로 넣어주기)
            for (int i = 0; i < tank.size(); i++) {
                tank.get(i).orderY = i + 1;
            }

            // 다시 X축으로 내림차순 정렬
            Collections.sort(tank, orderX);

            long totalSum = 0;

            for (int i = 0; i < N; i++) {
                Tank X = tank.get(i);
//                System.out.println(X.orderY +"  "+ X.v);
                update(X.orderY, X.v);
                System.out.println(totalSum);
                totalSum += sum(X.orderY);

            }
            System.out.println("#" + t + " " + totalSum);
        }

    }

    public static void update(int idx, int num) {

        idx = StartIdx + idx - 1;

        tree[idx] = num;
        idx = idx/2; // start=start/2 와 동일;

        while (idx > 0) {

            tree[idx] = tree[2 * idx] + tree[2 * idx + 1];
            idx = idx/2; // start=start/2 와 동일;
        }

    }

    public static long sum(int idx) {

        long sum = 0;
        int start = StartIdx;
        int end = StartIdx + idx - 2; //Leaf노드 시작~ 내 앞 노드까지의 구간합

        while (start <= end) {

            if(start % 2 == 1){
                sum = sum + tree[start];
            }
            if (end % 2 == 0) {
                sum = sum + tree[end];
            }

            start = (start + 1) / 2;
            end = (end - 1) / 2;
        }
        return sum;
    }

    public static class Tank {

        int x;
        int y;
        int v;
        int orderY;

        public Tank(int x, int y, int v, int orderY) {
            this.x = x;
            this.y = y;
            this.v = v;
            this.orderY = orderY;
        }

    }

}