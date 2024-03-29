package B03_수_위상정렬;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

//문제
//N명의 학생들을 키 순서대로 줄을 세우려고 한다. 각 학생의 키를 직접 재서 정렬하면 간단하겠지만,
//마땅한 방법이 없어서 두 학생의 키를 비교하는 방법을 사용하기로 하였다.
//그나마도 모든 학생들을 다 비교해 본 것이 아니고, 일부 학생들의 키만을 비교해 보았다.
//일부 학생들의 키를 비교한 결과가 주어졌을 때, 줄을 세우는 프로그램을 작성하시오.
//
//입력
//첫째 줄에 N(1 ≤ N ≤ 32,000), M(1 ≤ M ≤ 100,000)이 주어진다. M은 키를 비교한 회수이다.
//다음 M개의 줄에는 키를 비교한 두 학생의 번호 A, B가 주어진다. 이는 학생 A가 학생 B의 앞에 서야 한다는 의미이다.
//학생들의 번호는 1번부터 N번이다.
//
//출력
//첫째 줄에 학생들을 키 순서대로 줄을 세운 결과를 출력한다. 답이 여러 가지인 경우에는 아무거나 출력한다.
//3 2
//1 3
//2 3
//
//1 2 3
//https://www.acmicpc.net/problem/2252
//https://m.blog.naver.com/ndb796/221236874984
//https://zoonvivor.tistory.com/95
public class 기본_No_01_2252_01번_반복중 {
    static int N, M;
    static ArrayList<Integer>[] ar;
    static int[] indegree;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("No_2252.txt"));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        ar = new ArrayList[N+1];

        for (int i = 0; i <=N ; i++) {
            ar[i] = new ArrayList<>();
        }
        
        indegree = new int[N+1];
        for (int i = 1; i <=M ; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            ar[a].add(b);
            indegree[b]++;
        }

        ArrayDeque<Integer> queue = new ArrayDeque<>();

        for (int i = 1; i <=N ; i++) {
            if(indegree[i] == 0){
                queue.add(i);
            }
        }

        while(!queue.isEmpty()){
            System.out.println(queue.peek());
            int start = queue.poll();

            for (int i = 0; i <ar[start].size() ; i++) {
                int end = ar[start].get(i);

                indegree[end]--;
                if(indegree[end] == 0){
                    queue.add(end);
                }

            }

        }
    }
}
