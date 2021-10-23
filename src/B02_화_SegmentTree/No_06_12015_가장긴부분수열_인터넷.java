package B02_화_SegmentTree;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.io.IOException;

//가장 긴 증가하는 부분 수열 2
//시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
//1 초	512 MB	19371	7960	5530	43.390%
//문제
//수열 A가 주어졌을 때, 가장 긴 증가하는 부분 수열을 구하는 프로그램을 작성하시오.
//
//예를 들어, 수열 A = {10, 20, 10, 30, 20, 50} 인 경우에
// 가장 긴 증가하는 부분 수열은 A = {10, 20, 10, 30, 20, 50} 이고, 길이는 4이다.
//
//입력
//첫째 줄에 수열 A의 크기 N (1 ≤ N ≤ 1,000,000)이 주어진다.
//
//둘째 줄에는 수열 A를 이루고 있는 Ai가 주어진다. (1 ≤ Ai ≤ 1,000,000)
//
//출력
//첫째 줄에 수열 A의 가장 긴 증가하는 부분 수열의 길이를 출력한다.

//6 => 수열 크기
//10 20 10 30 20 50 => 수열값
//
//4


//https://www.acmicpc.net/problem/12015
//https://velog.io/@cchloe2311/%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98-%EB%B0%B1%EC%A4%80-12015.-%EA%B0%80%EC%9E%A5-%EA%B8%B4-%EC%A6%9D%EA%B0%80%ED%95%98%EB%8A%94-%EB%B6%80%EB%B6%84-%EC%88%98%EC%97%B4-2
public class No_06_12015_가장긴부분수열_인터넷 {

    static private int[] a;
    static int startIndex;
    static int[] tree;

    static private ElementInfo[] elementInfos;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("No_12015.txt"));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        a = new int[n];
        elementInfos = new ElementInfo[n];
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
            elementInfos[i] = new ElementInfo(a[i], i);
        }
        Arrays.sort(elementInfos);

        for (startIndex = 1; startIndex < n; startIndex *= 2) ;
        tree = new int[startIndex * 2 - 1 + 1];

        for (int i = 0; i < n; i++) {
            int index = elementInfos[i].index;
            updateTree(startIndex + index, findMaxLISLength(0, index, 1, 0, startIndex - 1) + 1);
        }

        System.out.print(tree[1]);
    }

    static void updateTree(int index, int value) {
        while (index != 0) {
            tree[index] = value;

            int parentIndex = index / 2;
            if (tree[parentIndex] < value) index = parentIndex;
            else index = 0;
        }
    }

    static int findMaxLISLength(int l, int r, int nodeIndex, int nodeL, int nodeR) {
        if ((l > nodeR) || (r < nodeL)) return 0;
        else if ((l <= nodeL) && (r >= nodeR)) return tree[nodeIndex];

        int mid = (nodeL + nodeR) / 2;
        int leftChildValue = findMaxLISLength(l, r, nodeIndex * 2, nodeL, mid);
        int rightChildValue = findMaxLISLength(l, r, nodeIndex * 2 + 1, mid + 1, nodeR);
        return leftChildValue > rightChildValue ? leftChildValue : rightChildValue;
    }
}

class ElementInfo implements Comparable<ElementInfo> {
    public int value;
    public int index;

    public ElementInfo(int value, int index) {
        this.value = value;
        this.index = index;
    }

    @Override
    public int compareTo(ElementInfo o) {
        if (this.value != o.value) return this.value - o.value;
        else return o.index - this.index;
    }
}