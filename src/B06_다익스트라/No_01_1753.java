package B06_다익스트라;

import java.awt.image.ImagingOpException;
import java.io.*;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class No_01_1753 {
    static int V, E, startPoint;
    static int[] dij;
    static int[][] arr;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("No_1753.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

//        System.out.println(V+" "+E);

        dij = new int[V+1];
        arr = new int[V+1][V+1];
        Arrays.fill(dij, Integer.MAX_VALUE);

        st = new StringTokenizer(br.readLine());
        startPoint = Integer.parseInt(st.nextToken());

//        System.out.println(startPoint);

        dij[startPoint] = 0;

        for(int i = 1; i<=E; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

//            System.out.println(start+" "+ end + " " + weight);

            arr[start][end] = weight;
        }

        dij(startPoint);

        for(int i = 1; i<=V; i++){
            if(dij[i] == Integer.MAX_VALUE){
                System.out.println("INF");
            } else {
                System.out.println(dij[i]);
            }
        }
    }

    static void dij(int start) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        queue.offer(start);

        while(!queue.isEmpty()){
            int a = queue.poll();

            for(int i = 1; i<=V; i++){
                if(arr[a][i] !=0 && dij[i] > dij[a] + arr[a][i]) {
                    dij[i] = dij[a] + arr[a][i];
                    queue.offer(i);
                }
            }
        }
    }
}
