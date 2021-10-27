package B02_화_SegmentTree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

//[2일차] [SW검정] Professional 실전 문제풀이 과정(7/7)
//12:20
//17:00 원리설명
//구간합
//트리에 넣었던 걸 다시 뺀다

/*

김희성 프로(SW역량강화TF)

알고리즘 유형 : 구간 트리(인덱스 트리, 세그먼트 트리 등)



풀이

1. A를 기준으로 정렬한다.

2. A 값이 차가 X 이하인 값들만 트리에 들어가 있도록

 X 이하면 트리에 1 을 더하고,

 X 이상 차이났던 기존에 들어간 값은 -1 을 더한다.

 여기서 기존에 들어갔던 값들을 저장해두기 위해 Queue 를 사용한다.



죠르디는 사랑입니다♥

 */

//4
//5 2 3
//3 5 4 1 2
//1 3 4 5 2
//10 10 1
//8 2 4 5 3 6 7 9 10 1
//8 2 4 5 3 6 7 9 10 1
//10 1 1
//2 3 1 4 6 5 7 8 10 9
//9 8 10 7 5 6 4 3 1 2
//10 1 8
//4 9 1 6 3 2 10 8 7 5
//8 10 5 1 4 6 3 9 2 7

public class A0_필수_05_부품공장_01번_반복중 {

    static class Comp{
        int a;
        int b;

        public Comp(int a, int b){
            this.a = a;
            this.b = b;
        }
    }

    static int K, StartIdx, TreeLength;
    static int[] tree;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("No_부품공장.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        StringTokenizer st1;

        int T = Integer.parseInt(st.nextToken());

        for (int tc = 1; tc <= T ; tc++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int X = Integer.parseInt(st.nextToken());
            int Y = Integer.parseInt(st.nextToken());

            K = (int) Math.ceil(Math.log(N)/Math.log(2));
            StartIdx = (int) Math.pow(2, K);
            TreeLength = (int) Math.pow(2, K+1);
            tree = new int[TreeLength+1];

            Comp[] comps = new Comp[N];

            st = new StringTokenizer(br.readLine());
            st1 = new StringTokenizer(br.readLine());

            for (int i = 0; i <N ; i++) {
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st1.nextToken());

                comps[i] = new Comp(a, b);
            }

//            Arrays.sort(comps, (o1, o2) -> o1.a - o2.a);

            Arrays.sort(comps, (o1, o2) -> o1.a - o2.a);
            ArrayDeque<Comp> q = new ArrayDeque<>();

            long result = 0;

            for (int i = 0; i <N ; i++) {
                Comp now = comps[i];
                if(!q.isEmpty()){
                    while(now.a - q.peek().a > X){
                        update(q.poll().b, -1);
                    }
                }

                result = result + query(1, now.b - Y);

                result = result + query(now.b+Y, N);

                q.offer(now);
                update(now.b, 1);
            }
            System.out.println(result);
        }

    }

    static int query(int start, int end){
        start = start + StartIdx -1;
        end = end + StartIdx -1;

        int sum = 0;
        while(start <= end){
            if(start%2==1){
                sum = sum + tree[start];
            }

            if(end%2==0){
                sum = sum + tree[end];
            }

            start = (start+1)/2;
            end = (end-1)/2;
        }

        return sum;
    }

    static void update(int idx, int diff){
        int index = StartIdx + idx -1;
        tree[index] = tree[index] + diff;

        index = index/2;
        while(index > 0){
            tree[index] = tree[index]+diff;
            index = index/2;
        }
    }
}