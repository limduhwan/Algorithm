package B02_화_SegmentTree;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

//3                                               ← 3 test cases in total
//10 3                                            ← 1st case
//175 182 178 179 170 179 171 185 185 181
//3 7 175
//1 10 180
//1 10 179
//7 5                                            ← 2nd case
//183 176 175 183 174 182 186
//1 4 176
//2 6 177
//1 7 180
//1 7 160
//5 7 180
//2 2                                            ← 3rd case
//161 168
//1 2 175
//1 2 188
//
//
//(출력)
//
//#1 3 4 4
//#2 2 2 4 7 2
//#3 0 0

// 구간트리 (인덱스 트리, 세그먼트 트리)
public class A0_2022_기출_01_키컸으면_인덱스트리_강사님 {
    static int tree[];
    static int leaf;
    static int T,N,Q;
    static int a,b,x;
    static int result[];
    static int flag[];

    static ArrayList<Edge> edgeList;
    static ArrayList<Comp> compList;

    static class Edge{

        int idx;

        int tall;



        Edge(int idx,int tall){

            this.idx = idx;

            this.tall = tall;



        }

    }



    static class Comp{

        int l;

        int r;

        int tall;

        int idx;

        Comp(int idx,int l, int r, int tall){

            this.idx = idx;

            this.l = l;

            this.r = r;

            this.tall = tall;

        }

    }



    public static void main(String[] args) throws IOException {

//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new FileReader("No_키컸으면.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));



        T = Integer.parseInt(br.readLine());

        StringTokenizer st;

        for (int i = 1; i <= T ; i++) {

            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());

            Q = Integer.parseInt(st.nextToken());



            leaf = 1 << (int)Math.ceil(Math.log10(N)/Math.log10(2)); //leaf 기준이 되는 가장 작은 leaf Node 주소값

            tree = new int[leaf << 1]; //leaf의 값이 8이면 Index Tree의 사이즈는 전체가 16이다.

            result = new int[Q + 1];



            edgeList = new ArrayList<>();

            compList = new ArrayList<>();





            st = new StringTokenizer(br.readLine());

            for (int j = 1; j <= N ; j++) {

                int n = Integer.parseInt(st.nextToken());

                edgeList.add(new Edge(j,n)); //전체키의 요소마다 순번과 키값을 저장한다.

            }





            Collections.sort(edgeList, (Edge a, Edge b)-> b.tall - a.tall); // 키 순서대로 내림차순 정렬한다.



            for (int j = 1; j <= Q ; j++) {

                st = new StringTokenizer(br.readLine());

                a = Integer.parseInt(st.nextToken());

                b = Integer.parseInt(st.nextToken());

                x = Integer.parseInt(st.nextToken());



                compList.add(new Comp(j,a,b,x)); // 질의는 범위와 순번 그리고 키값을 저장한다.

            }



            Collections.sort(compList, (Comp a, Comp b)-> b.tall - a.tall); // 카 순서대로 내림차순 정렬한다.



            int k = 0;

            for (Comp c:compList) { // 질의의 순번에 따라 진행한다.

                for (int j = k; j < N; j++) {

                    Edge temp = edgeList.get(j); //키 순서대로 값을 가져온다/

                    if(c.tall < temp.tall){ //질의 키보다 정렬된 키가 클때만 아래를 수행한다.

//                        System.out.println("E.n : " + temp.n + " e.num : " + temp.num + "("+c.a+","+c.b+")" + " j : " + j);

                        update(temp.idx, 1); // 해당 하는 순번에 값에 1을 추가한다.

                        k++;// K는 정렬된 키의 진행된 값을 저장하기 위한 공간으로, 이미 인덱스 트리에 업데이트 된 값이 반복되지 않도록 기준을 잡아준다.

                    }else{ // 질의 키보다 작은 경우 지금까지 구간에 누적된 값들의 합을 구한다.

                        result[c.idx] += sum(c.l, c.r);

//                        System.out.println("sum(1, N, 1, c.a, c.b) : " + sum(1, N, 1, c.a, c.b));

                        break;

                    }

                }

                if(k == N)// 더 이상 Update 할 값이 없는 경우에는 계속 구간합만 수행한다.

                    result[c.idx] += sum(c.l, c.r);

            }

            bw.write("#"+i+" ");

            for (int j = 1 ; j < result.length; j++) { // 하나씩 값을 붙여 나갈때는 BufferedWriter 를 사용하는 것을 추천한다.

                bw.write(result[j]+" ");

            }

            bw.write("\n");

            bw.flush();

        }







    }

    private static void update(int idx, int dif) { //Bottom Up Update 방식

// TODO Auto-generated method stub

        idx += leaf  - 1;

        tree[idx] = dif;

        while((idx >>= 1) > 0){ //최상위 부모 1번 idx 를 만날때까지 업데이트함.

            tree[idx] = tree[idx<<1] + tree[(idx<<1) + 1];

        }

    }



    private static long sum(int l, int r) { //구간합 구하는 방식

// TODO Auto-generated method stub

        long ans = 0;

        l += leaf - 1;

        r += leaf - 1;



        while(l < r){

            if((l & 1) == 1) //홀수이면

                ans += tree[l++];

            if((r & 1) != 1) //짝수이면

                ans += tree[r--];

            l >>= 1;

            r >>= 1;

        }

        if(l == r)

            ans += tree[l];

        return ans;

    }

}