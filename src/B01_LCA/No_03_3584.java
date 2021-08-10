package B01_LCA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

//https://www.acmicpc.net/problem/3584
//https://velog.io/@pss407/%EB%B0%B1%EC%A4%803584-%EA%B0%80%EC%9E%A5-%EA%B0%80%EA%B9%8C%EC%9A%B4-%EA%B3%B5%ED%86%B5-%EC%A1%B0%EC%83%81
public class No_03_3584 {
    static int allTestCases, nodeCnt;
    static int[][] arr;
    static int[] depth, parent;
    static boolean[] hasParent;
    static int root;
    static boolean[] checked;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new FileReader("No_3584.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

//        st = new StringTokenizer(br.readLine());
//        System.out.println(st.nextToken());

        allTestCases = Integer.parseInt(br.readLine());
//        System.out.println(allTestCases +" " + nodeCnt);

        for(int k = 0; k<allTestCases; k++) {
            nodeCnt = Integer.parseInt(br.readLine());
            arr = new int[nodeCnt + 1][nodeCnt + 1];
            depth = new int[nodeCnt + 1];
            parent = new int[nodeCnt + 1];
            hasParent = new boolean[nodeCnt + 1];
            checked = new boolean[nodeCnt + 1];

        //        for(int j=0; j<nodeCnt; j++){
        //            System.out.println(hasParent[j]);
        //        }
        //        마지막 것은 찾을 대상이니까 -1개만 담는다
            for (int i = 0; i < nodeCnt - 1; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
        //            System.out.println(a+" "+b);

                arr[a][b] = arr[b][a] = 1;
                hasParent[b] = true;
            }

            for (int i = 0; i <= nodeCnt; i++) {
                if (i != 0 && hasParent[i] == false) {
        //                System.out.println(i);
                    root = i;
                }
            }

        //        System.out.println("root "+ root);
            dfs(root, 0);

        //        for(int j=0; j<=nodeCnt; j++){
        //           System.out.println(j+ "  "+ parent[j]);
        //        }

        //        for(int j=0; j<=nodeCnt; j++){
        //            System.out.println(j+ "  "+ depth[j]);
        //        }

        //        System.out.println(depth[6]);

            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
        //        System.out.println(a +"   "+  b);

        //        여기부터 LCA
            bw.write(lca(a, b) + "\n");
        //        System.out.println(lca(a, b));
        }
        bw.close();
        br.close();
    }

//    호출될 때 마다 깊이가 하나씩 깊어짐
    static void dfs(int start, int depthNum) {
        checked[start] = true;
        depth[start] = depthNum;

        for(int i = 0; i<=nodeCnt; i++){
            if(checked[i] ==  false && arr[start][i] == 1){
                dfs(i, depthNum+1);
                parent[i] = start;
            }
        }
    }

    static int lca(int a, int b) {
        while(depth[a] < depth[b]){
            b = parent[b]; //끌어올림
        }

        while (depth[a] > depth[b]) {
            a = parent[a]; //끌어올림
        }

        while(a!=b){
            a = parent[a];
            b = parent[b];
        }

        return a;
    }


}
