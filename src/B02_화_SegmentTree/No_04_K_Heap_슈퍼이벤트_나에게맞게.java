package B02_화_SegmentTree;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.StringTokenizer;

//3
//10
//1 2
//1 4
//1 5
//1 8
//1 9
//1 8
//1 1
//2 6
//2 5
//2 1
//5
//1 6
//1 7
//1 2
//2 2
//1 100
//7
//1 1
//1 1
//2 1
//1 1
//2 1
//1 1
//1 1
//
//
//(출력)
//#1 8 8 1
//#2 6
//#3 1 1

//왜 서치할 때 index--해 주는가
public class No_04_K_Heap_슈퍼이벤트_나에게맞게 {

    static int[] tree;
    static int leaf;
    static int T, Q, Action;
    static int K, StartIdx, TreeLength;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("No_K힙슈퍼이벤트.txt"));
        int get_number;
        StringTokenizer st = new StringTokenizer(br.readLine());

        T = Integer.parseInt(st.nextToken());

        for (int tc = 1; tc <= T ; tc++) {
//            KSLT
            K = (int) Math.ceil(Math.log(100000)/Math.log(2));
            StartIdx = (int) Math.pow(2, K);
            TreeLength = (int) Math.pow(2, K+1);
            tree = new int[TreeLength+1];

            StringBuilder ans = new StringBuilder();
            ans.append("#").append(tc);
            Q = Integer.parseInt(br.readLine());

            for (int i = 1; i <= Q ; i++) {
                st = new StringTokenizer(br.readLine());
                Action = Integer.parseInt(st.nextToken());
                get_number = Integer.parseInt(st.nextToken());

                if(Action ==1){
                    tree_add(get_number);
                }else if(Action ==2){
                    ans.append(" ").append(tree_search(get_number));
                }
            }
            System.out.println(ans.toString());
        }
    }

    static void tree_add(int card_num){
        int index = card_num + StartIdx-1;

        tree[index] = tree[index] + 1;

        index = index /2;

        while (index > 0){
            tree[index] = tree[index*2] + tree[index*2+1];
            index = index / 2;
        }
    }

    static int tree_search(int kth){
        int index = 1;
        while(index < StartIdx){
            tree[index]--; // 이해안됨

            if(kth <= tree[index*2]){
                index = index*2;
            }else{
                kth = kth - tree[index*2];
                index = index*2+1;
            }
        }

        tree[index]--; // 이해안됨
        return index - StartIdx + 1;
    }
}