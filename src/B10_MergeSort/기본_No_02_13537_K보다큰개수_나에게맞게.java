package B10_MergeSort;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

//문제
//길이가 N인 수열 A1, A2, ..., AN이 주어진다.
// 이때, 다음 쿼리를 수행하는 프로그램을 작성하시오.
//
//i j k: Ai, Ai+1, ..., Aj로 이루어진 부분 수열 중에서
// k보다 큰 원소의 개수를 출력한다.

//입력
//첫째 줄에 수열의 크기 N (1 ≤ N ≤ 100,000)이 주어진다.
//
//둘째 줄에는 A1, A2, ..., AN이 주어진다. (1 ≤ Ai ≤ 109)
//
//셋째 줄에는 쿼리의 개수 M (1 ≤ M ≤ 100,000)이 주어진다.
//
//넷째 줄부터 M개의 줄에는 쿼리 i, j, k가 한 줄에 하나씩 주어진다.
// (1 ≤ i ≤ j ≤ N, 1 ≤ k ≤ 109)
//
//출력
//각각의 쿼리마다 정답을 한 줄에 하나씩 출력한다.

//5 -> 수열 크기
//5 1 2 3 4 -> 수열
//3 -> 쿼리 개수
//2 4 1 -> 쿼리
//4 4 4
//1 5 2

//2
//0
//3

//보통 K보다 큰 개수, 작은 개수는 mergesort로 푼다
//https://viyoung.tistory.com/258
public class 기본_No_02_13537_K보다큰개수_나에게맞게 {

    static int n;
    static int[] arr;
    static int[][] tree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("No_13537.txt"));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        arr = new int[n+1];

        st = new StringTokenizer(br.readLine());

        for (int i = 1; i <=n ; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int h = (int) Math.ceil(Math.log(n)/Math.log(2))+1;

        tree = new int[1<<h][];
        merge(1, 1, n);

        int m = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i <m ; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            sb.append(query(1, 1, n, a, b, k)).append("\n");
        }

        System.out.println(sb);

    }

    static int upperBound(int node, int k){
        int s=0;
        int e=tree[node].length;

        while (s<e){
            int m = (s+e)>>1;
            if(tree[node][m]<=k) s = m+1;
            else e = m;
        }

        return e;
    }

    static int query(int node, int st, int en, int l, int r, int k){
        if(st>r || en<l) return 0;
        if(l<=st && en<=r){
            int idx = upperBound(node, k);
            return tree[node].length-idx;
        }

        int m = (st+en)>>1;
        return query(node*2, st, m, l, r, k) + query(node*2+1, m+1, en, l, r, k);
    }

    static void merge(int node, int st, int en){
        if(st!=en){
            int m = (st+en)>>1;
            merge(node*2, st, m);
            merge(node*2+1, m+1, en);
            mergeSortTree(node, st, en);
        }else{
            tree[node] = new int[]{arr[st]};
        }
    }

    static void mergeSortTree(int node, int st, int en){
        tree[node] = new int[en-st+1];

        int s = st;
        int m = (st+en)>>1;
        int e = m+1;
        int idx = 0;

        while(s<=m&&e<=en){
            if(arr[s]<arr[e]) tree[node][idx++] = arr[s++];
            else tree[node][idx++] = arr[e++];
        }

        while(s<=m) tree[node][idx++] = arr[s++];
        while(e<=en) tree[node][idx++] = arr[e++];

        for (int i = st; i <=en ; i++) {
            arr[i] = tree[node][i-st];
        }
    }
}
