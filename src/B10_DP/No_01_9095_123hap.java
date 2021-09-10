package B10_DP;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class No_01_9095_123hap {
    public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("No_9095_123hap.txt"));
//        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in)); //콘솔에서 입력받을 경우
//        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        for(int i=0; i<M; i++){
            int number = Integer.parseInt(br.readLine());

            int d[] = new int[number+2];
            d[0] = 1;
            d[1] = 1;
            d[2] = 2;
            for( int j=3; j<= number; j++){
                d[j] = d[j-1] + d[j-2] + d[j-3];
            }
            System.out.println(d[number]);
        }
    }

}
