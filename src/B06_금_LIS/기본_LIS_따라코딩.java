package B06_금_LIS;


import java.io.*;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

//(입력)
//1
//10
//1 1 2 2 3 3 2 2 5 5

//4
//1 2 3 5

//https://platform.samsungcic.com/#/contents/home/CONT2021101316727508
public class 기본_LIS_따라코딩 {

    static int[] arr;
    static int[] dp;
    static int[] trace;
//    0 9개
    static final int MIN = -1000000001;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("No_LIS2.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        arr = new int[N];
        dp = new int[N];
        trace = new int[N];

        Arrays.fill(dp, MIN);

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int cnt = findLIS();

        ArrayDeque<Integer> stack = new ArrayDeque<>();

        for (int i = N-1; i >= 0 ; i--) {
            if(trace[i] == cnt-1){
                stack.push(arr[i]);
                cnt--;
            }
        }

        bw.write(stack.size() + "\n");

        while(!stack.isEmpty()){
            bw.write(stack.pop() + " ");
        }

        bw.write("\n");
        bw.flush();
    }

    static int findLIS(){
        dp[0] = arr[0];
        trace[0] = 0;

        int max_idx = 0; //LIS의 마지막 위치 저장
        for (int i = 1; i <arr.length ; i++) {
            if(dp[max_idx] < arr[i]){
                dp[++max_idx] = arr[i];
                trace[i] = max_idx;
            }else{
                int target;

                int tmp = Arrays.binarySearch(dp, 0, max_idx, arr[i]);
                target = tmp < 0 ? (tmp*-1)-1 : tmp;

                dp[target] = arr[i];
                trace[i] = target;

            }

        }
        return max_idx + 1;

    }
}