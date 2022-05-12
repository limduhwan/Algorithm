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

// 구간트리 (인덱스 트리)
public class A0_2022_기출_01_키컸으면_인덱스트리_나에게맞게_01번_반복중 {

    static StringTokenizer st;
    static int T, N, Q;
    static int K, StartIdx, TreeLength;
    static int[] tree, result;
    static int a, b, x;

    static ArrayList<Edge> edgeList;
    static ArrayList<Comp> compList;

    static class Edge{

        int idx;
        int tall;

        Edge(int idx, int tall){
            this.idx = idx;
            this.tall = tall;
        }
    }

    static class Comp{
        int idx;
        int l;
        int r;

        int tall;

        Comp(int idx, int l, int r, int tall){
            this.idx = idx;
            this.l = l;
            this.r = r;
            this.tall = tall;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("No_키컸으면.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        T = Integer.parseInt(br.readLine());

        for(int i = 1; i<= T; i++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            Q = Integer.parseInt(st.nextToken());

//            System.out.println("N " + N + " Q " + Q);

            //KSLT

            K = (int) Math.ceil(Math.log(N) / Math.log(2));
            StartIdx = (int) Math.pow(2, K);
            TreeLength = (int) Math.pow(2, K+1)-1;
            tree = new int[TreeLength+1];
            result = new int[Q+1];

            edgeList = new ArrayList<>();
            compList = new ArrayList<>();

            st = new StringTokenizer(br.readLine());

            for(int j=1; j<=N; j++){
                int n = Integer.parseInt(st.nextToken());
                edgeList.add(new Edge(j, n));
            }

            Collections.sort(edgeList, (Edge a, Edge b) -> b.tall - a.tall);

            for(int j=1; j<=Q; j++){
                st = new StringTokenizer(br.readLine());
                a = Integer.parseInt(st.nextToken());
                b = Integer.parseInt(st.nextToken());
                x = Integer.parseInt(st.nextToken());

                compList.add(new Comp(j, a, b, x));
            }

            Collections.sort(compList, (Comp a, Comp b) -> b.tall - a.tall);

            int k = 0;

            for (Comp c : compList){
                for(int j=k; j<N; j++){
                    Edge temp = edgeList.get(j);

                    if(c.tall < temp.tall){
                        update(temp.idx, 1);
                        k++;
                    }else{
                        result[c.idx] += sum(c.l, c.r); // 이건 됨
//                        result[c.idx] = result[c.idx] + sum(c.l, c.r); //이건 안됨
                        break;
                    }
                }

                if(k == N){
                    result[c.idx] += sum(c.l, c.r);
                }
            }

            bw.write("#"+i+" ");

            for(int j = 1; j< result.length; j++){
                bw.write(result[j]+ " ");
            }

            bw.write("\n");
            bw.flush();
        }
    }

    static void update(int index, int dif){
        index = StartIdx + index -1;
        tree[index] = dif;

        index = index / 2;

        while(index >0){
            tree[index] = tree[index*2] + tree[index*2+1];
            index = index/2;
        }

    }

    static long sum(int start, int end){
        start = StartIdx + start -1;
        end = StartIdx + end -1;

        long ans = 0;

        while(start <=end){
            if( (start % 2) == 1){
                ans = ans + tree[start];
            }

            if( (end % 2) == 0){
                ans = ans + tree[end];
            }

            start = (start+1)/2;
            end = (end-1)/2;
        }

        return ans;
    }
}