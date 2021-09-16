package Z_02_SDS_프로_문제풀이;

import javax.swing.plaf.synth.SynthTableHeaderUI;
import java.io.*;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

//[2일차] [SW검정] Professional 실전 문제풀이 과정(7/7)
//12:20
//17:00 원리설명
//구간합
//트리에 넣었던 걸 다시 뺀다
public class 문제풀이_Day02_04번_부품공장_00_세그먼트트리_나에게맞게 {

    static long[] segTree;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("No_부품공장.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringTokenizer st1;

        int T = Integer.parseInt(st.nextToken());

        for (int tc = 1; tc <=T ; tc++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int X = Integer.parseInt(st.nextToken());
            int Y = Integer.parseInt(st.nextToken());

            segTree = new long[100000*4]; // 왜 long으로 100000인가
            Comp[] comps = new Comp[N];

            st = new StringTokenizer(br.readLine());
            st1 = new StringTokenizer(br.readLine());

            for (int i = 0; i < N; i++) {
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st1.nextToken());
                comps[i] = new Comp(a, b);
            }

            //a 값을 가지고 오름차순 정렬
            Arrays.sort(comps, (o1, o2) -> o1.a - o2.a);

            ArrayDeque<Comp> q = new ArrayDeque<>();

            long result = 0;

            for (int i = 0; i < N ; i++) {
                Comp now = comps[i];
                if(!q.isEmpty()){
                    while(now.a - q.peek().a > X){
                        update(1, 1, 100000, q.poll().b, 0);
                    }
                }

                result = result + query(1, 1, 100000, 1, now.b - Y);

                result = result + query(1, 1, 100000, now.b + Y, 100000);

                q.offer(now);
                update(1, 1, 100000, now.b, 1);
            }

            bw.write("#"+tc+ " " + result + "\n");
            bw.flush();
        }
    }

    static class Comp{
        int a;
        int b;
        public Comp(int a, int b){
            this.a = a;
            this.b = b;
        }
    }

    static long update(int node, int start, int end, int index, long val){
        if(index < start || end < index){
            return segTree[node];
        }

        if(start == end){
            return segTree[node] = val;
        }

        int mid = (start+end) /2;

        return segTree[node] = update(node*2, start, mid, index, val)
                + update(node*2+1, mid+1, end, index, val);
    }

    static long query(int node, int start, int end, int left, int right){
        if(right < start || end < left){
            return 0;
        }

        if(left <= start && end <= right){
            return segTree[node];
        }

        int mid = (start+end) / 2;

        return query(node*2, start, mid, left, right)
                +query(node*2+1, mid+1, end, left, right);
    }
}
