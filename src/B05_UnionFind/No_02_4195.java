package B05_UnionFind;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

//https://www.acmicpc.net/problem/4195
//https://steady-coding.tistory.com/111
public class No_02_4195 {
    static int relation_cnt, friend_cnt;
    static String[] parent;
    static ArrayList<String> groups;
    static HashMap hm;
    static int member_cnt;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("No_4195.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        relation_cnt = Integer.parseInt(br.readLine());
        friend_cnt = Integer.parseInt(br.readLine());

        System.out.println(relation_cnt + " " + friend_cnt);

        parent = new String[friend_cnt];
        groups = new ArrayList<String>();
        hm = new HashMap();

        st = new StringTokenizer(br.readLine());

        for(int i=0; i<friend_cnt; i++){
            String aaa = st.nextToken();
            String bbb = st.nextToken();
            hm.put(aaa, aaa);
            hm.put(bbb, bbb);

            union(aaa, bbb);

        }
    }

    static void union(String A, String B){


    }

    static String find(String XXX){
        return "";
    }
}
