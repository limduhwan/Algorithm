package B02_화_SegmentTree;

import java.io.*;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

// 중앙값 찾기
//https://velog.io/@imfksh/%EB%B0%B1%EC%A4%80-9426-Java
//https://www.acmicpc.net/problem/9426
public class 기본_No_04_9426_중앙값찾기 {

    static int[] segTree;
    static int MAX = 65535;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("No_9426.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        segTree = new int[MAX*4];
        ArrayDeque<Integer> q = new ArrayDeque<>();

        long result = 0;

        for(int i = 1; i<=N; i++){
            int now = Integer.parseInt(br.readLine());
            update(1, 0, MAX, now, 1);
            q.offer(now);
            if(i >= K){
                result = result + search(1, 0, MAX, (K+1)/2);
                int poll = q.poll();
                update(1, 0, MAX, poll, -1);
            }
        }

        bw.write(result+"\n");
        bw.flush();
    }

    //세그먼트 트리 완성
    static int init(int[] arr, int node, int start, int end){
        if(start == end) {
            return segTree[node] = arr[start];
        }

        int mid = (start+end) / 2;

        return segTree[node] = init(arr, node*2, start, mid) + init(arr, node*2+1, mid+1, end);
    }

    static int update(int node, int start, int end, int index, int diff){
        if(index < start || index > end){
            return segTree[node];
        }

        if(start == end){
            return segTree[node] = segTree[node] + diff;
        }

        int mid = (start+end) / 2;

        return segTree[node] = update(node*2, start, mid, index, diff) + update(node*2+1, mid+1, end, index, diff);
    }

    static int search(int node, int start, int end, long k){
        if(start == end){
            return start;
        }

        int mid = (start+end) /2;

        if(k<=segTree[node*2]){
            return search(node*2, start, mid, k);
        } else {
            return search(node*2+1, mid+1, end, k-segTree[node*2]);
        }
    }
}

