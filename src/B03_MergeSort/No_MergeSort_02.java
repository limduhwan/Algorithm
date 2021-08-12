package B03_MergeSort;

//https://www.youtube.com/watch?v=ctkuGoJPmAE
//https://yunmap.tistory.com/entry/%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98-Java%EB%A1%9C-%EA%B5%AC%ED%98%84%ED%95%98%EB%8A%94-%EC%89%AC%EC%9A%B4-Merge-Sort-%EB%B3%91%ED%95%A9-%EC%A0%95%EB%A0%AC-%ED%95%A9%EB%B3%91-%EC%A0%95%EB%A0%AC
public class No_MergeSort_02 {
    static int[] src;
    static int[] tmp;

    public static void main(String[] args) {
        src = new int[]{1, 9, 8, 5, 4, 2, 3, 7, 6};
        tmp = new int[src.length];
        printArray(src);
        mergeSort(0, src.length-1);
        printArray(src);
    }

    public static void mergeSort(int start, int end){
        if(start<end){
            int mid = (start+end) /2;
            mergeSort(start, mid);
            mergeSort(mid+1, end);

            int p = start;
            int q = mid+1;
            int idx = p;

            while(p<=mid || q <=end){
                if(q>end || (p<=mid && src[p]<src[q])) {
                    tmp[idx++] = src[p++];
                } else {
                    tmp[idx++] = src[q++];
                }
            }

            for(int i=start; i<=end; i++){
                src[i] = tmp[i];
            }
        }

    }

    static void printArray(int[] a){
        for(int i=0; i<a.length; i++){
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }
}
