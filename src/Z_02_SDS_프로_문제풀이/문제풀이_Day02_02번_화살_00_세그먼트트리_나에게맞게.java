package Z_02_SDS_프로_문제풀이;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
김희성 프로(SW역량강화TF)
알고리즘 유형 : 세그먼트 트리

풀이
1. 높이 순으로 내림 차순 정렬 (높이가 같으면 index 순)
2. 높이가 높은 순서부터
    2-1 Tree 에서 K 번째 수 찾기 수행
    2-2 수행 후 해당 건물 Tree 에 업데이트
3. 2번 과정 반복
죠르디는 사랑입니다♥
 */

//출력
//#1 28
//#2 0
//#3 12

//알아야 할 것
//세그먼트트리에서 K번째 수 찾기
//Arrays.sort ( , ,) from index, to Index
//세그먼트트리 연산없이 노드만 업데이트하는 방법
public class 문제풀이_Day02_02번_화살_00_세그먼트트리_나에게맞게 {

    static class Point implements Comparable<Point>{
        int idx;
        int power;
        int height;

        public Point(int idx, int power, int height) {
            this.idx = idx;
            this.power = power;
            this.height = height;
        }

        public int compareTo(Point o){
//            return o.height - this.height;
            return Integer.compare(o.height, this.height);
        }
    }
    static final int[] segTree = new int[131072*2];
    static final Point[] points = new Point[100000];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("No_화살.txt"));
        StringTokenizer st, st1;

        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i <100000 ; i++) {
            points[i] = new Point(0, 0, 0);
        }

        for (int tc = 1; tc <= T ; tc++) {
            int N = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            st1 = new StringTokenizer(br.readLine());

            for (int i = 0; i < N; i++) {
                points[i].idx = i + 1;
                points[i].power = Integer.parseInt(st1.nextToken());
                points[i].height = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(points, 0, N);

            long result = 0;
            for (int i = 0; i < N; i++) {
                int idx = points[i].idx;
                int P = points[i].power + 1;

                int now = query(1, idx+1, N, 1, N);

                if(now >= P){
                    result = result + search(1, 1, N,P+i-now);
                }

                update(1, 1, N, idx, 1);
            }
            Arrays.fill(segTree, 0);
            System.out.println("#"+tc+" "+result);
        }
    }

//    static void update(int node, int start, int end, int index, int dif){
//        if(index < start || end < index) {
//            return;
//        }
//
//        segTree[node] = segTree[node] + dif;
//
//        if(start == end){
//            return;
//        }
//
//        int mid = (start+end)/2;
//
//        update(node*2, start, mid, index, dif);
//        update(node*2+1,mid+1, end,  index, dif);
//    }

    static int update(int node, int start, int end, int index, int c){
        if(index < start || end < index){
            return segTree[node];
        }

        if(start == end){
            return segTree[node] = c;
        }

        int mid = (start+end) /2;
        return segTree[node] = update(node*2, start, mid, index, c)
                +update(node*2+1, mid+1, end, index, c);

    }

    static int query(int node, int start, int end, int left, int right){
        if(start > right || end < left){
            return 0;
        }

        if(start <= left && right <= end){
            return segTree[node];
        }
//        ??
        int mid = (left+right) / 2;

        return query(node*2, start, end, left, mid)
                + query(node*2+1, start, end,mid+1, right);
    }

    static int search(int node, int start, int end, int k){
        if(start == end){
            return  start;
        }

        int mid = (start+end)/2;

        if(k <= segTree[node*2]){
            return search(node*2, start, mid,  k);
        } else {
            return search(node*2+1, mid+1, end,  k-segTree[node*2]);
        }
    }

}