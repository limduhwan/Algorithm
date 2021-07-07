package B09_SegmentTree;
//https://hyeonseong.tistory.com/3
//1  2  3  4  5  6

import java.util.Scanner;

public class No_00_원리_1{
    static int[] arr = {1,2,3};
    static int[] tree;
    public static void main(String[] args) {

        System.out.println((1+2)/2);
        tree = new int[arr.length*4];
        init(1, 0, arr.length-1);
//
//        for(int i=0;i<tree.length;i++) {
//            System.out.println(tree[i]);
//        }

        Scanner scan = new Scanner(System.in);
//
        System.out.print("n번 부터 m번 까지의 구간합 : n,m 을 입력하시오 : ");
        System.out.println(sum(1, 0, arr.length-1,  scan.nextInt(), scan.nextInt()));

//        System.out.print("n번째 인덱스의 값을 m 으로 변경 : n,m 을 입력하시오 : ");
//        int n = scan.nextInt();
//        int m = scan.nextInt();
//        st.update(0, st.arr.length-1, 1, 0, m-st.arr[n]);
//        System.out.println("변경된 세그먼트트리의 전체합 : " + st.sum(0, st.arr.length-1, 1, 0, 5));
    }

    static int init(int node, int start, int end) {
        if(start == end) { /* 리프노드이거나 자식노드들이 구간합이 모두구해졌을 경우 */
            return tree[node] = arr[start]; /* 구간합 트리에 넣어준다 */
        }
        /* 반씩 나눠서  재귀적으로 자식노드들의 구간합을 구해준다 */
        int mid = (start+end)/2;
        return tree[node] = init(node*2, start, mid) + init(node*2+1, mid+1, end);
    }

    static int sum(int node, int start, int end, int left, int right) {
        if(right < start || left > end) {
            return 0;
        }
        if(left <=start && end <=right) {
            return tree[node];
        }
        /* 필요한 구간마다 밑에서부터 구간합을 가지고 올라온다 */
        int mid = (start+end)/2;
        return sum(node*2, start, mid, left, right) + sum(node*2+1,mid+1, end, left, right);
    }

    static void update(int node, int start, int end, int index, int dif) {
        if(index < start || index > end) {
            return;
        }
        tree[node] += dif; /* 변경된 값만큼 더해주고 */
        if(start == end) {
            return;
        }
        /* 변경된 값이 속해있는 구간의 구간합을 모두 바꿔준다 */
        int mid = (start + end)/2;
        update(node*2, start, mid, index, dif);
        update(node*2+1,mid+1, end, index, dif);
    }
}