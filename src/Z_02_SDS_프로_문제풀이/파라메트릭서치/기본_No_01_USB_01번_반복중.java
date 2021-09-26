package Z_02_SDS_프로_문제풀이.파라메트릭서치;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.StringTokenizer;

class 기본_No_01_USB_01번_반복중 {

    static int N, M;
    static int[] video;
    static int MAX_SIZE = 1000000000;

    public static void main(String arg[]) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("No_USB.txt"));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T ; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            video = new int[N+1];

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <=N ; i++) {
                video[i] = Integer.parseInt(st.nextToken());
            }

            int lb = -1, ub = MAX_SIZE, m;

            while(lb+1 < ub){
                m = lb+(ub-lb)/2;

                if(fmin(m)){
//                    System.out.println("fmin true");
                    ub = m;
                }else{
//                    System.out.println("fmin false");
                    lb = m;
                }

            }

            System.out.println(ub);
        }

    }

    static boolean fmin(int mid_size){
        System.out.println(mid_size);
        int usbCount = 1;
        int usbSize = 0;

        for (int i = 1; i <=N ; i++) {
            if(usbSize + video[i] > mid_size){
                System.out.println("1");
                usbCount++;
                usbSize = video[i];

                if(usbSize > mid_size || usbCount > M){
                    return false;
                }

            }else{
                System.out.println("2");
                usbSize = usbSize + video[i];
            }
        }
        System.out.println("usbSize   "+usbSize);
        return true;
    }

}