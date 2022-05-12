package B02_화_SegmentTree;
import java.io.*;
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

public class A0_2022_기출_02_탱크_인덱스트리_나에게맞게_01번_반복중 {
    static int T, N;
    static int K, StartIdx, TreeLength;
    static int[] tree;
    static StringTokenizer st;
    static ArrayList<Tank> tank;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("No_탱크.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());

        for(int t = 1; t <=1; t++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());

            //KSLT
            K = (int) Math.ceil(Math.log(N) / Math.log(2));
            StartIdx = (int) Math.pow(2, K);
            TreeLength = (int) Math.pow(2, K+1)-1;
            tree = new int[TreeLength + 1];

            tank = new ArrayList<>();

            for(int i =1; i<=N; i++){
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());

                tank.add(new Tank(x, y, v, 0));
            }

//            for(Tank a : tank){
//                System.out.println(a.y);
//            }

            Comparator<Tank> orderX = new Comparator<Tank>(){
                @Override
                public int compare(Tank o1, Tank o2){
                    return o2.x - o1.x;
                }
            };

            Comparator<Tank> orderY = new Comparator<Tank>(){
                @Override
                public int compare(Tank o1, Tank o2){
                    return o2.y - o1.y;
                }
            };

            Collections.sort(tank, orderY);

//            for(Tank a : tank){
//                System.out.println(a.y);
//            }

            for(int i = 0; i <tank.size(); i++){
                tank.get(i).orderY = i + 1;
            }

            Collections.sort(tank, orderX);

            long totalSum = 0;

            for (int i = 0; i <N; i++){
                Tank X = tank.get(i);
//                System.out.println(X.orderY +"  "+ X.v);
                update(X.orderY, X.v);
                System.out.println(totalSum);
                totalSum = totalSum + sum(X.orderY);
            }
            System.out.println("#" +  t + " " + totalSum);
        }

    }

    static long sum(int idx){
        int start = StartIdx;
        int end = StartIdx + idx -2;
        long sum = 0;

        while(start <= end){

            if(start % 2 == 1){
                sum = sum + tree[start];
            }

            if(end % 2 == 0){
                sum = sum + tree[end];
            }

            start = (start + 1) / 2;
            end = (end - 1) / 2;
        }

        return sum;
    }

    static void update(int idx, int num){
        idx = StartIdx +  idx -1;

        tree[idx] = num;
        idx = idx/2;

        while(idx > 0){
            tree[idx] = tree[idx*2] + tree[idx*2+1];
            idx = idx/2;
        }

    }

    static class Tank {
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