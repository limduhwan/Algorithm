package B10_화_파라메트릭서치;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.StringTokenizer;

//절단기의 최대 사이즈
//2
//4 7 -> 목재 개수, 남은 목재길이의 합
//11 14 10 17 -> 목재 사이즈
//5 20
//4 42 40 26 46

//12
//36

class 기본_No_01_목재_02번_반복중 {
    static int mokjaeCnt, hap;
    static int[] arr;
    static int MAX_SIZE = 1000000000;
    public static void main(String arg[]) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("No_목재.txt"));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            mokjaeCnt = Integer.parseInt(st.nextToken());
            hap = Integer.parseInt(st.nextToken());

            arr = new int[mokjaeCnt+1];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i <mokjaeCnt ; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            int lb = -1;
            int ub = MAX_SIZE;
            int m = 0;

            while(lb+1 < ub){
                m = lb+(ub-lb)/2;

                if(exam(m)){
                    ub = m;
                }else{
                    lb = m;
                }
            }

            System.out.println(ub + " "+lb);
        }

    }

    static boolean exam(int mid_size){
        int sum = 0;

        for (int i = 0; i <mokjaeCnt ; i++) {
            if(arr[i] - mid_size > 0){
                sum = sum + arr[i] - mid_size;
            }
        }

        if(sum < hap){
            return true;
        }

        return false;
    }

}