package B02_화_파라메트릭서치;

//https://coding-food-court.tistory.com/107

//문제
//도현이의 집 N개가 수직선 위에 있다.
// 각각의 집의 좌표는 x1, ..., xN이고, 집 여러개가 같은 좌표를 가지는 일은 없다.
//
//도현이는 언제 어디서나 와이파이를 즐기기 위해서 집에 공유기 C개를 설치하려고 한다.
// 최대한 많은 곳에서 와이파이를 사용하려고 하기 때문에,
// 한 집에는 공유기를 하나만 설치할 수 있고,
// 가장 인접한 두 공유기 사이의 거리를 가능한 크게 하여 설치하려고 한다.
//
//C개의 공유기를 N개의 집에 적당히 설치해서,
// 가장 인접한 두 공유기 사이의 거리를 최대로 하는 프로그램을 작성하시오.
//
//입력
//첫째 줄에 집의 개수 N (2 ≤ N ≤ 200,000)과
// 공유기의 개수 C (2 ≤ C ≤ N)이 하나 이상의 빈 칸을 사이에 두고 주어진다.
// 둘째 줄부터 N개의 줄에는 집의 좌표를 나타내는 xi (0 ≤ xi ≤ 1,000,000,000)가
// 한 줄에 하나씩 주어진다.
//
//출력
//첫째 줄에 가장 인접한 두 공유기 사이의 최대 거리를 출력한다.

//5 3 => 집의 개수, 공유기의 개수
//1 => 집의 좌표
//2
//8
//4
//9

//3 => 두 공유기 사이의 최대 거리
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

//https://youngest-programming.tistory.com/426
public class 기본_No_02_2110_공유기최대거리_01번_반복중 {
    static int N, C;
    static int[] position;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("No_2110.txt"));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        position = new int[N+1];

        for (int i = 1; i <=N ; i++) {
            st = new StringTokenizer(br.readLine());

            position[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(position);

        int lb = -1;
        int ub = 1000000000;
        int mid = 0;

        while(lb+1<ub){
            mid = lb + (ub-lb)/2;

            if(exam(mid)){
                lb = mid;
            }else{
                ub = mid;
            }
        }
        System.out.println(lb);
    }

    static boolean exam(int mid_size){
        //일단 먼저 구현하고 lb를 끌어올릴지 말지 고민해 보자

        int count = 1;
        int prevHome = 1;
        for (int i = 2; i < position.length ; i++) {
            if(position[i] - position[prevHome] >= mid_size){
                count++;
                prevHome = i;
            }
        }

        if(count >= C){
            return true;
        }else{
            return false;
        }
    }

}
