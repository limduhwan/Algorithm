package B02_SegmentTree;
//https://hyeonseong.tistory.com/3
//1  2  3  4  5  6
import java.util.*;
public class No_00_원리{
    static int[] arr = {1, 2, 3, 4, 5, 6};
    static int[] tree;
    public static void main(String[] args) {

        tree = new int[arr.length*4];
        Tree st = new Tree();
        st.init(0, st.arr.length-1,1);

        Scanner scan = new Scanner(System.in);

        System.out.print("n번 부터 m번 까지의 구간합 : n,m 을 입력하시오 : ");
        System.out.println(st.sum(0, st.arr.length-1, 1, scan.nextInt(), scan.nextInt()));

        System.out.print("n번째 인덱스의 값을 m 으로 변경 : n,m 을 입력하시오 : ");
        int n = scan.nextInt();
        int m = scan.nextInt();
        st.update(0, st.arr.length-1, 1, 0, m-st.arr[n]);
        System.out.println("변경된 세그먼트트리의 전체합 : " + st.sum(0, st.arr.length-1, 1, 0, 5));
    }
}
class Tree {
    int arr[]; // 구간합을 만들 요소들
    int tree[]; // 구간합 트리
    public Tree() {
        Scanner scan = new Scanner(System.in);
        System.out.print("배열 크기 입력 : ");
        arr = new int[scan.nextInt()];
        tree = new int[arr.length*4];
        System.out.print("배열 요소 입력 : ");
        for(int i=0;i<arr.length;i++) {
            arr[i] = scan.nextInt();
        }
    }
    public int init(int start, int end, int node) {
        if(start == end) { /* 리프노드이거나 자식노드들이 구간합이 모두구해졌을 경우 */
            return tree[node] = arr[start]; /* 구간합 트리에 넣어준다 */
        }
        /* 반씩 나눠서  재귀적으로 자식노드들의 구간합을 구해준다 */
        int mid = (start+end)/2;
        return tree[node] = init(start, mid, node*2) + init(mid+1, end, node*2+1);
    }

    public void update(int start, int end, int node, int index, int dif) {
        if(index < start || index > end) {
            return;
        }
        tree[node] += dif; /* 변경된 값만큼 더해주고 */
        if(start == end) {
            return;
        }
        /* 변경된 값이 속해있는 구간의 구간합을 모두 바꿔준다 */
        int mid = (start + end)/2;
        update(start, mid, node*2, index, dif);
        update(mid+1, end, node*2+1, index, dif);
    }

    public int sum(int start, int end, int node, int left, int right) {
        if(right < start || left > end) {
            return 0;
        }
        if(left <=start && end <=right) {
            return tree[node];
        }
        /* 필요한 구간마다 밑에서부터 구간합을 가지고 올라온다 */
        int mid = (start+end)/2;
        return sum(start, mid, node*2, left, right) + sum(mid+1, end, node*2+1, left, right);
    }

    public void print() {
        for(int i=1;i<this.tree.length;i++) {
            System.out.print(i + "번째 인덱스 : " + this.tree[i]);
            System.out.println();
        }
    }
}
