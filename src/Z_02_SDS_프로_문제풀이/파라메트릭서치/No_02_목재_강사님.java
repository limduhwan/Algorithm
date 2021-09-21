package Z_02_SDS_프로_문제풀이.파라메트릭서치;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;
import java.util.StringTokenizer;

class No_02_목재_강사님 {
    static int N;
    static int H;
    static int MAXSIZE = 100000000;
    static int Wood[];

    static boolean fmax(int size) {
        int h = 0;
        for(int i = 0; i < N; i++)
            h += (0 > (Wood[i] - size)? 0 : (Wood[i] - size));
        if(h >= H) return true;
        return false;
    }

    public static void main(String arg[]) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("No_목재.txt"));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());

            Wood = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                Wood[i] = Integer.parseInt(st.nextToken());
            }
            int lb=-1, ub = MAXSIZE, m;
            while(lb+1 < ub) {
                m = lb+(ub-lb)/2;
                if(fmax(m)) lb = m;
                else ub = m;
            }

            System.out.println("#" + test_case + " "+ lb);
        }
        br.close();
    }
}