package B09_BFS;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//https://www.acmicpc.net/problem/10451
public class No_02_10451 {
    static int testcase;
    static int sunyulSize;
    static int[] arr;
    static boolean[] checked;
    static int cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("No_10451.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = null;

        testcase = Integer.parseInt(br.readLine());
        for(int k = 0; k<testcase; k++) {
            cnt = 0;
            sunyulSize = Integer.parseInt(br.readLine());

            arr = new int[sunyulSize + 1];
            checked = new boolean[sunyulSize + 1];

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= sunyulSize; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 1; i <= sunyulSize; i++) {
                if (checked[i] == false) {
                    bfs(i);
                    cnt++;
                }
            }
//            System.out.println(cnt);
            bw.write(cnt+"\n");
        }

        bw.close();
        br.close();
    }

    static void bfs(int i){
//        checked[i] = true;
        Queue<Integer> queue = new LinkedList();
        queue.offer(arr[i]);

        while(!queue.isEmpty()){
            int num = queue.poll();
//            System.out.println(num);
            for(int j = 1; j<=sunyulSize; j++){
//                System.out.println(j +" "+ checked[j] +" "+ num);
                if(checked[j]==false && j == num){
                    checked[j] = true;
                    queue.offer(arr[j]);
//                    System.out.print(j);
                }
            }
        }
    }
}
