package B15_DP;

import java.io.*;

public class No_06_2579 {
    static int[] dp, arr;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("No_2579.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        dp = new int[N+1];
        arr = new int[N+1];

        for(int i=1; i<=N; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        dp[1] = arr[1];

        if(N>=2){
            dp[2] = arr[1] + arr[2];
        }

        for(int i=3; i<=N; i++){
            dp[i] = Math.max(dp[i-2], dp[i-3] + arr[i-1]) + arr[i];
        }

        System.out.println(dp[N]);

    }
}
