package Z_02_합격_최적화_문제집;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//K번째 수 찾기 어렵다...
//https://blog.udanax.org/2016/04/7469-k.html
public class No_11_7469_MergeSortTree_Internet {
    static int[][] data;
    static int[][] temp;
    static int N, K;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("No_7469.txt"));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        data = new int[N][2];
        temp = new int[N][2];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            data[i - 1][0] = Integer.parseInt(st.nextToken());
            data[i - 1][1] = i;
        }

        mergeSort(0, N - 1);

        while (K > 0) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            System.out.println(q(x, y, z));
            K--;
        }
        br.close();
    }

    private static int q(int i, int j, int k) {
        int c = 0;
        for (int x = 0; x < data.length; x++) {
            if (data[x][1] >= i && data[x][1] <= j) {
                c++;
                if (c == k) {
                    return (data[x][0]);
                }
            }
        }
        return 0;
    }

    private static void mergeSort(int start, int end) {
        if (start < end) {
//            **여기 괄호 주의
            int mid = start + (end - start) / 2;
            mergeSort(start, mid);
            mergeSort(mid + 1, end);
            merge(start, mid, end);
        }
    }

    private static void merge(int start, int mid, int end) {
        for (int i = start; i <= end; i++) {
            temp[i][0] = data[i][0];
            temp[i][1] = data[i][1];
        }

        int l = start;
        int r = mid + 1;
        int k = start;

//        while (l <= mid && r <= end) {
//            if (temp[l][0] < temp[r][0]) {
//                data[k][0] = temp[l][0];
//                data[k][1] = temp[l][1];
//                l++;
//            } else {
//                data[k][0] = temp[r][0];
//                data[k][1] = temp[r][1];
//                r++;
//            }
//            k++;
//        }

        while(l <= mid || r <= end) {    // 쪼개진 두 배열의 원소를 비교해가면서 새로운 배열에다가 삽입한다.
            if(l<=mid && r<=end) {    // 두 배열 모두 비교가 필요한 원소가 남아있다면
                if(temp[l][0] < temp[r][0]){
                    data[k][0] = temp[l][0];
                    data[k][1] = temp[l][1];
                    l++;
                } else {
                    data[k][0] = temp[r][0];
                    data[k][1] = temp[r][1];
                    r++;
                }
            } else if(l > mid && r <= end){    // 첫 번째 배열은 비교할 원소가 남아 있지 않은 경우
                data[k][0] = temp[r][0];
                data[k][1] = temp[r][1];
                r++;
            } else if(l <= mid && r > end) {     // 두 번째 배열은 비교할 원소가 남아 있지 않은경우
                data[k][0] = temp[l][0];
                data[k][1] = temp[l][1];
                l++;
            }

            k++;
        }

        while (l <= mid) {
            data[k][0] = temp[l][0];
            data[k][1] = temp[l][1];
            l++;
            k++;
        }
    }

}
