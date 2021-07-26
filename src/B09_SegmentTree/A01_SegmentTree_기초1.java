package B09_SegmentTree;

import java.util.Arrays;

//https://stack07142.tistory.com/216
//https://www.youtube.com/watch?v=075fcq7oCC8
//https://www.youtube.com/watch?v=ahFB9eCnI6c
public class A01_SegmentTree_기초1 {

    public static void main(String[] args) throws Exception {
        int[] arr = {5, 3, 7, 9, 6, 4, 1, 2, 1};

        SegmentTree segmentTree = new SegmentTree(arr, 9);

        System.out.println(Arrays.toString(segmentTree.segmentArr));
    }
}

class SegmentTree {
    int[] segmentArr;

    SegmentTree(int[] arr, int n) {
        segmentArr = new int[n*4];

        init(arr, 0, n-1, 1);
    }

    int init(int[] arr, int left, int right, int node){

        if(left == right) {
            return segmentArr[node] = arr[left];
        }

        int mid = (left+right) / 2;

        segmentArr[node] = segmentArr[node] + init(arr, left, mid, node*2);
        segmentArr[node] = segmentArr[node] + init(arr, mid+1, right, node*2+1);

        return segmentArr[node];
    }
}
