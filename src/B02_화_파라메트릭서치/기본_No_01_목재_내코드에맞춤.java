package B02_화_파라메트릭서치;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.StringTokenizer;

class 기본_No_01_목재_내코드에맞춤 {
    static int N;
    static int H;
    static int[] wood;
    static int MAX_SIZE = 1000000000;

    public static void main(String arg[]) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("No_목재.txt"));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int tc=1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());

            wood = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                wood[i] = Integer.parseInt(st.nextToken());
            }

            int lb = -1;
            int ub = MAX_SIZE;
            int m = 0;

            while(lb+1 < ub){
                m = lb+(ub-lb)/2;
                if(fmax(m)){
                    lb = m;
                }else{
                    ub = m;
                }
            }

            System.out.println("#"+tc+" "+lb);
        }
        br.close();
    }

    static boolean fmax(int size){
        int h = 0;
        for (int i = 0; i < N; i++) {
            h = h+( 0 > (wood[i]-size) ? 0 : (wood[i]-size) );
        }

        if(h>=H){
            return true;
        }

        return false;
    }
}