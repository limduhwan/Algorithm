package B07_위상정렬;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class No_02_1766 {

    static int muncnt, firstmun;
    static int[] indegree;
    static ArrayList<Integer>[] al;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("No_1766.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());

        muncnt = Integer.parseInt(st.nextToken());
        firstmun = Integer.parseInt(st.nextToken());

        System.out.println(muncnt +" "+ firstmun);

        indegree = new int[muncnt+1];
        al = new ArrayList[muncnt+1];

        for(int i=0; i<=muncnt; i++){
            al[i] = new ArrayList<Integer>();
        }

        for(int i=0; i<firstmun; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            al[a].add(b);
            indegree[b]++;
        }




    }
}
