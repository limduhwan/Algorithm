package B11_UnionFind;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

//https://www.acmicpc.net/problem/4195
//https://steady-coding.tistory.com/111
//https://youngest-programming.tistory.com/458
public class No_02_4195 {
    static int T;
    static int F;
    static int[] parent;
    static int[] count;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("No_4195.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());

//        System.out.println(T);
        for(int i=0; i<T; i++){
            F = Integer.parseInt(br.readLine());
            parent = new int[F*2];
            count = new int[F*2];

            for(int j=0; j<F*2; j++){
                parent[j] = j;
            }

            Arrays.fill(count, 1);
            HashMap<String, Integer> map = new HashMap<>();

            int index=0;

            for(int f = 0; f<F; f++){
                st = new StringTokenizer(br.readLine());
                String friend1 = st.nextToken();
                String friend2 = st.nextToken();

//                System.out.println(friend1+" "+friend2);

                if(!map.containsKey(friend1)){
                    map.put(friend1, index++);
                }
                if(!map.containsKey(friend2)){
                    map.put(friend2, index++);
                }

                System.out.println(union(map.get(friend1), map.get(friend2)));
            }
        }
    }

    static int union(int a, int b){
        a = find(a);
        b = find(b);

        if(a!=b){
            if(a<b){
                parent[b] = a;
                count[a] = count[a]+ count[b];
                return count[a];
            } else {
                parent[a] = b;
                count[b] = count[b] + count[a];
                return count[b];
            }
        }
        return count[a];
    }

    static int find(int a){
        if(parent[a] == a) return a;
        else return parent[a] = find(parent[a]);
    }
}
