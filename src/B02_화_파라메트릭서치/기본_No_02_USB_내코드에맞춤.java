package B02_화_파라메트릭서치;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.StringTokenizer;

class 기본_No_02_USB_내코드에맞춤 {
    static int N;
    static int M;
    static int[] video;
    static final int MAX_SIZE = 1000000000;

    public static void main(String arg[]) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("No_USB.txt"));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            video = new int[N+1];

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <=N; i++) {
                video[i] = Integer.parseInt(st.nextToken());
            }

            //여기부터 진짜
            int lb = -1, ub = MAX_SIZE, m;

            while(lb+1 < ub){
                m = lb + (ub-lb)/2;
                if(fmin(m)){
                    ub = m;
                }else{
                    lb = m;
                }
            }

            System.out.println("#"+tc+" "+ub);
        }

        br.close();
    }

    static boolean fmin(int mid_size){
        int usbCount = 1;
        int usbSize = 0;

        for (int i = 1; i <= N; i++) {
            if(usbSize + video[i] > mid_size){
                usbCount++;
                usbSize = video[i];

                if(usbSize > mid_size || usbCount > M){
                    return false;
                }
            }else{
                usbSize = usbSize + video[i];
            }
        }
        return true;
    }
}