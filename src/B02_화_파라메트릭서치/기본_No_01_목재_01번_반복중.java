package B02_화_파라메트릭서치;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.StringTokenizer;

//2
//4 7
//11 14 10 17
//5 20
//4 42 40 26 46

class 기본_No_01_목재_01번_반복중 {

    static int mokjaeCnt, nopi;
    static int[] mokjarArr;
    static int MAX_SIZE = 1000000000;

    public static void main(String arg[]) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("No_목재.txt"));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int tc=1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());

            mokjaeCnt = Integer.parseInt(st.nextToken());
            nopi = Integer.parseInt(st.nextToken());

            mokjarArr = new int[mokjaeCnt];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i <mokjaeCnt ; i++) {
                mokjarArr[i] = Integer.parseInt(st.nextToken());
            }

            //*여기부터가 핵심
            int lb = -1;
            int ub = MAX_SIZE;
            int m = 0;

            //최대 사이즈를 구하는 거니까 ub는 냅두고 lb를 끌어 올린다.
            //lb를 끌어올려서 lb+1 = ub일 때까지 반복한다.
            while(lb+1 < ub){
                m = lb+(ub-lb)/2;
                if(fexam(m)){
                    lb = m;
                }else{
                    ub = m;
                }
            }

            System.out.println(lb);
        }
    }

    static boolean fexam(int mid_size){
        int sum = 0;

        for (int i = 0; i <mokjaeCnt ; i++) {
            if(mokjarArr[i] - mid_size < 0){
                sum = sum + 0;
            }else{
                sum = sum + mokjarArr[i] - mid_size;
            }
        }

//        for (int i = 0; i < mokjaeCnt; i++) {
//            h = h+( 0 > (mokjarArr[i]-size) ? 0 : (wood[i]-size) );
//        }

//        System.out.println("sum  " + sum);
        if(sum >= nopi){
            return true;
        }

        return false;
    }
}