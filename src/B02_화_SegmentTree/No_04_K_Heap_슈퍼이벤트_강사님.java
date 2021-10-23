package B02_화_SegmentTree;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
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

public class No_04_K_Heap_슈퍼이벤트_강사님 {

    private static int[] tree;
    private static int leaf;
    private static int T, Q, Action;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("No_K힙슈퍼이벤트.txt"));
        int get_number;
        StringTokenizer st = new StringTokenizer(br.readLine());

        T = Integer.parseInt(st.nextToken());

        leaf = 2;
        while (leaf <= 100000) {
            leaf *= 2;            //숫자의 값이 변하지 않는 100000의 값이기에 산술적으로 표현함.   depth = (int) Math.ceil(Math.log10(N) / Math.log10(2)); // 1. depth
        }

        tree = new int[leaf * 2];

        for (int tc = 1; tc <= T; tc++) {
            Arrays.fill(tree, 0);
            StringBuilder ans = new StringBuilder();
            ans.append("#").append(tc);
            Q = Integer.parseInt(br.readLine());

            for (int i = 1; i <= Q; i++) {
                st = new StringTokenizer(br.readLine());
                Action = Integer.parseInt(st.nextToken());     //1 수 뽑기 , 2 발표 (1≤X≤100,000)
                get_number = Integer.parseInt(st.nextToken()); // 뽑은 혹은 발표 번호 K (1≤K≤참가자수(X(1)))

                if (Action == 1) {
                    tree_add(get_number);
                } else if (Action == 2) {
                    ans.append(" ").append(tree_search(get_number));
                } else {
                }
            }
            System.out.println(ans.toString());
        }
    }

    // 이진 트리 생성
    // index-카드번호, data-뽑힌 수 count++ 해준다.
    static void tree_add(int card_num) {
        int index;
        index = card_num + (leaf - 1); // 트리의 실제 저장 시작 부분으로 치환
        tree[index]++;
        index >>= 1;
        // 부모 노드도 업데이트
        while (index > 0) {
            tree[index] = tree[2 * index] + tree[2 * index + 1];
            index >>= 1;
        }
    }

    // 이진 트리 탐색 부분
    // 구간합이 tree[index]가 되는 index가 해당 카드의 번호가 된다.

    static int tree_search(int kth) {
        int index = 1;
        while (index < leaf) {
            // 당첨 카드 삭제 > 부모노드도 탐색하면서 -- 한다.
            tree[index]--;
            // 왼쪽 노드의 구간합이 kth 보다 크면 왼쪽 구간에서 K번째 카드가 있다.
            // index를 왼쪽 노드로 이동해서 왼쪽만 더 탐색한다.
            if (tree[index * 2] >= kth) index = index * 2;
                // 아니면 오른쪽 노드의 구간에 K번째 카드가 있다.
                // K번째 카드는 오른쪽에 있으므로 왼쪽 구간합(1~5번째)를 K(8)에서 뺀다.
                // index는 오른쪽 노드로 이동해서 오늘쪽만 탐색한다.
            else {
                kth = kth - tree[index * 2];
                index = index * 2 + 1;
            }
        }

        // 부른 카드에 대해서도 -- 해줘야 함
        tree[index]--;
        return index - leaf + 1;
    }

//    static int search(int k) {
//        int index = 1;
//
//        while (index < startIdx) {
//            if (k <= tree[index * 2]) {
//                index = index * 2;
//            } else {
//                k = k - tree[index * 2];
//                index = index * 2 + 1;
//            }
//        }
//
//        return index - startIdx + 1;
//    }
}