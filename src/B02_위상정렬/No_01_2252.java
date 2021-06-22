package B02_위상정렬;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//https://www.acmicpc.net/problem/2252
//https://m.blog.naver.com/ndb796/221236874984
//https://zoonvivor.tistory.com/95
public class No_01_2252 {
    static int N, M;
    static int[] indegree;
    static ArrayList<Integer>[] list;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new FileReader("No_2252.txt"));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        indegree = new int[N+1];
        list = new ArrayList[N+1];

        for(int i=1; i<=N; i++){
            list[i] = new ArrayList<Integer>();
        }

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            list[x].add(y);
            indegree[y]++;
        }

        Queue<Integer> queue = new LinkedList<Integer>();

        for(int i=1; i<=N; i++){
            if(indegree[i] == 0){
                queue.add(i);
            }
        }

        while(!queue.isEmpty()){
            System.out.print(queue.peek());
            int current = queue.poll();

            for(int i=0; i<list[current].size(); i++){
                int next = list[current].get(i);
                indegree[next]--;
                if(indegree[next]==0){
                    queue.add(next);
                }
            }
        }
    }
}
