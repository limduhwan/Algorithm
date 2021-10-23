package B02_화_SegmentTree;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.StringTokenizer;

/*
김희성 프로(SW역량강화TF)
알고리즘 유형 : 구간 트리 (인덱스 트리, 세그먼트 트리) + 좌표 압축

풀이
1. 입력 값의 갯수를 구하기 위한 인덱스 트리를 사용한다.
(구간의 최대(MAX) 값을 구하는 인덱스 트리 구성)
2. 입력 값의 범위가 1 ~ 10억이기 때문에 인덱스 트리 사이즈가
메모리 제한을 초과하여 좌표 압축을 사용한다. (HashMap 사용)
3. 인덱스 트리를 사용하여 갯수의 최대 값을 구한다.
그 때 원래 입력 값의 대소를 비교하는 로직을 추가한다.

죠르디는 사랑입니다♥
 */

//2
//7 5
//1 2 4 2 3 3 2
//10 3
//2 2 3 3 5 4 7 7 9 10
//
//
//
//
//(출력)
//
//#1 3 2
//#2 10 1

//21년 3분기 세그먼트(인덱스)트리 응용 강의 00:10
public class No_01_부분수열최빈값_리라벨링_세그먼트트리_강사님_재정리 {
    private static int[] indexTree;
    private static int[] orgNum;
    static int K, startIdx, treeLength;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("No_부분수열의 최빈값.txt"));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken()); // 수의 개수
            int Kx = Integer.parseInt(st.nextToken()); // 부분 수열의 길이

            //KSLT
            K = (int) Math.ceil(Math.log(N)/Math.log(2));
            startIdx = (int) Math.pow(2, K);
            treeLength = (int) Math.pow(2, K+1);

            indexTree = new int[treeLength+1]; // 입력 값의 Max Count 를 저장할 구간 최대 값 트리
            orgNum = new int[treeLength+1]; // 실제 입력 값을 저장할 트리

            int[] input = new int[N + 1];
            HashMap<Integer, Integer> map = new HashMap<>();

            int result_num = 0;
            int result_cnt = 0;

            st = new StringTokenizer(br.readLine());

            for (int i = 1; i <= N; i++) {
                input[i] = Integer.parseInt(st.nextToken());

                // 좌표 압축 Start
                Integer a = map.get(input[i]);
                if (a == null) {
                    a = map.size() + 1;
                    map.put(input[i], a);
                }

                // 좌표 압축 End

                // i 번째 입력 값이 1개 더해졌음을 트리에 저장한다.
                update(a, 1, input[i]);

                // i 가 K 보다 크거나 같을 때만 max 값을 구한다.
                if (i >= Kx) {
                    // 전체 트리 구간에서 max 값을 구한다.
//                    int max = query(startIdx + 1, startIdx + N);
                    int max = query_(1, N);
                    // 전체 구간 쿼리는 트리 배열에서 1번째 값이다.
//                    int max = orgNum[1];

                    //max 2
                    //max 3
                    //max 3
                    //#1 3 2
                    //max 3
                    //max 3
                    //max 3
                    //max 5
                    //max 7
                    //max 7
                    //max 7
                    //max 10
                    //#2 10 1

                    System.out.println("max " + max);
                    if (result_num < max) {
                        result_num = max;
                        result_cnt = 1;
                    } else if (result_num == max) {
                        result_cnt++;
                    }

                    // max 값을 구한 후에 i - K + 1 번째 입력 값이 1개 줄었음을 트리에 저장한다.
                    update(map.get(input[i - Kx + 1]), -1, input[i - Kx + 1]);
                }
            }

            System.out.println("#" + tc + " " + result_num + " " + result_cnt);
        }
    }

    // 구간의 최대 값을 update 하는 로직을 구성하면서
    // 그 최대 값의 실제 입력 값이 어떤 값인지도 알아야한다.
    static void update(int idx, int val, int num) {
        idx = idx +  startIdx -1;
        indexTree[idx] += val;
        orgNum[idx] = num;
        idx = idx/2;

        while (idx > 0) {
            if (indexTree[idx * 2] > indexTree[idx * 2 + 1]) {
                indexTree[idx] = indexTree[idx * 2];
                orgNum[idx] = orgNum[idx * 2];
            } else if (indexTree[idx * 2] < indexTree[idx * 2 + 1]) {
                indexTree[idx] = indexTree[idx * 2 + 1];
                orgNum[idx] = orgNum[idx * 2 + 1];
            } else {
                indexTree[idx] = indexTree[idx * 2];
                orgNum[idx] = Math.max(orgNum[idx * 2], orgNum[idx * 2 + 1]);
            }
            idx = idx/2;
        }
    }

    // 항상 전체 구간 쿼리만 수행하기 때문에 사실상 필요없는 코드임
    // 구간의 최대 값을 query 하는 로직을 구성하면서
    // 그 최대 값의 실제 입력 값을 구해야한다.
    // 최대 값이 같은 경우 실제 입력 값이 큰 값도 구해야함
    static int query(int start, int end) {
        int max = 0;
        int numMax = 0;

        while (start < end) {
            // 시작하는 부분이 홀수 일 때
            if (start % 2 == 1) {
                if (max < indexTree[start]) {
                    max = indexTree[start];
                    numMax = orgNum[start];
                }
                // max 값이 같다면 입력 값이 더 큰 수를 구한다.
                else if (max == indexTree[start]) {
                    numMax = Math.max(numMax, orgNum[start]);
                }
            }
            // 끝나는 부분이 짝수 일 때
            if (end % 2 == 0) {
                if (max < indexTree[end]) {
                    max = indexTree[end];
                    numMax = orgNum[end];
                }
                // max 값이 같다면 입력 값이 더 큰 수를 구한다.
                else if (max == indexTree[end]) {
                    numMax = Math.max(numMax, orgNum[end]);
                }
            }

            start = (start + 1) /2;
            end = (end - 1) /2;
        }

        if (start == end) {
            if (max < indexTree[start])
                numMax = orgNum[start];
                // max 값이 같다면 입력 값이 더 큰 수를 구한다.
            else if (max == indexTree[start])
                numMax = Math.max(numMax, orgNum[start]);
        }

        return numMax;
    }

    static int query_(int start, int end) {
        start = startIdx + start -1;
        end = startIdx + end -1;

        int max = 0;
        int numMax = 0;

        while (start <= end) {
            // 시작하는 부분이 홀수 일 때
            if (start % 2 == 1) {
                if (max < indexTree[start]) {
                    max = indexTree[start];
                    numMax = orgNum[start];
                }
                // max 값이 같다면 입력 값이 더 큰 수를 구한다.
                else if (max == indexTree[start]) {
                    numMax = Math.max(numMax, orgNum[start]);
                }
            }
            // 끝나는 부분이 짝수 일 때
            if (end % 2 == 0) {
                if (max < indexTree[end]) {
                    max = indexTree[end];
                    numMax = orgNum[end];
                }
                // max 값이 같다면 입력 값이 더 큰 수를 구한다.
                else if (max == indexTree[end]) {
                    numMax = Math.max(numMax, orgNum[end]);
                }
            }

            start = (start + 1) /2;
            end = (end - 1) /2;
        }

//        if (start == end) {
//            if (max < indexTree[start])
//                numMax = orgNum[start];
//                // max 값이 같다면 입력 값이 더 큰 수를 구한다.
//            else if (max == indexTree[start])
//                numMax = Math.max(numMax, orgNum[start]);
//        }

        return numMax;
    }
}