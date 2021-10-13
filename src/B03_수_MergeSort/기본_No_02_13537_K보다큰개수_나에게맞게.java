package B03_수_MergeSort;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

//문제
//길이가 N인 수열 A1, A2, ..., AN이 주어진다.
// 이때, 다음 쿼리를 수행하는 프로그램을 작성하시오.
//
//i j k: Ai, Ai+1, ..., Aj로 이루어진 부분 수열 중에서
// k보다 큰 원소의 개수를 출력한다.

//입력
//첫째 줄에 수열의 크기 N (1 ≤ N ≤ 100,000)이 주어진다.
//
//둘째 줄에는 A1, A2, ..., AN이 주어진다. (1 ≤ Ai ≤ 109)
//
//셋째 줄에는 쿼리의 개수 M (1 ≤ M ≤ 100,000)이 주어진다.
//
//넷째 줄부터 M개의 줄에는 쿼리 i, j, k가 한 줄에 하나씩 주어진다.
// (1 ≤ i ≤ j ≤ N, 1 ≤ k ≤ 109)
//
//출력
//각각의 쿼리마다 정답을 한 줄에 하나씩 출력한다.

//5 -> 수열 크기
//5 1 2 3 4 -> 수열
//3 -> 쿼리 개수
//2 4 1 -> 쿼리
//4 4 4
//1 5 2

//2
//0
//3

//보통 K보다 큰 개수, 작은 개수는 mergesort로 푼다
//https://viyoung.tistory.com/258
public class 기본_No_02_13537_K보다큰개수_나에게맞게 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("No_13537.txt"));
        StringTokenizer st;



    }
}
