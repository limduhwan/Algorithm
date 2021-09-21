package Z_02_SDS_프로_문제풀이.파라메트릭서치;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;
import java.util.StringTokenizer;

class No_01_USB_강사님_이해안됨 {
    static int N;
    static int M;
    static int MAXSIZE = 100000000;
    static int Video[];

    static boolean fmin(int size) {
        int usbCount = 1;
        int usbSize = 0;
        for(int i = 0; i < N; i++) {
            if(usbSize + Video[i] > size) {
                usbCount++;
                usbSize = Video[i];
                if(usbSize > size || usbCount > M) return false;
            }
            else usbSize += Video[i];
        }
        return true;
    }

    public static void main(String arg[]) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("No_USB.txt"));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++)
        {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            Video = new int[N];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                Video[i] = Integer.parseInt(st.nextToken());
            }
            int lb=-1, ub = MAXSIZE, m;

            while(lb+1 < ub) {
                m = lb+(ub-lb)/2;
                if(fmin(m)) ub = m;
                else lb = m;
            }

            System.out.println("#" + test_case + " "+ ub);
        }

        br.close();
    }
}