package Z_02_SDS_프로_문제풀이.세그먼트트리;

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
public class No_01_부분수열최빈값_리라벨링_세그먼트트리_강사님_재정리_03번_반복중 {
    private static int[] indexTree;
    private static int[] orgNumTree;
    static int K, startIdx, treeLength;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("No_부분수열의 최빈값.txt"));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= 1 ; tc++) {
            st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int Kx = Integer.parseInt(st.nextToken());

            //KSLT

            K = (int) Math.ceil(Math.log(N)/Math.log(2));
            startIdx = (int) Math.pow(2, K);
            treeLength = (int) Math.pow(2, K+1);
            indexTree = new int[treeLength+1];
            orgNumTree = new int[treeLength+1];

            st = new StringTokenizer(br.readLine());
            int[] input = new int[N+1];
            HashMap<Integer, Integer> map = new HashMap<>();
            int result_num = 0;
            int result_cnt = 0;

            for (int i = 1; i <= N ; i++) {
                input[i] = Integer.parseInt(st.nextToken());

                Integer a = map.get(input[i]);

                if(a == null){
                    a = map.size()+1;
                    map.put(input[i], a);
                }

                update(a,  1, input[i]);

                if(i>=Kx){
                    int maxNum = query(1, N);
                    System.out.println(maxNum);

                    if(result_num < maxNum){
                        result_num = maxNum;
                        result_cnt = 1;
                    }else{
                        result_cnt++;
                    }

                    update(map.get(input[i-Kx+1]), -1, input[i]);
                }
            }

            System.out.println(result_num +" " + result_cnt);
        }
    }

    static int query(int start, int end){
        start = start + startIdx - 1;
        end = start + startIdx - 1;

        int maxNum = 0;
        int maxBindo = 0;

        while(start <= end){

            if(start%2 == 1){
                if(maxBindo < indexTree[start]){
                    maxBindo = indexTree[start];
                    maxNum = orgNumTree[start];
                } else if(maxBindo == indexTree[start]){
                    maxNum = Math.max(maxNum, orgNumTree[start]);
                }
            }

            if(end%2 ==0){
                if(maxBindo < indexTree[end]){
                    maxBindo = indexTree[end];
                    maxNum = orgNumTree[end];
                } else if(maxBindo == indexTree[end]){
                    maxNum = Math.max(maxNum, orgNumTree[end]);
                }
            }

            start = (start+1)/2;
            end = (end-1)/2;
        }


        return maxNum;
    }

    static void update(int index, int value, int originNum){
        index = index + startIdx - 1;
        indexTree[index] = indexTree[index] + value;
        orgNumTree[index] = originNum;

        index = index / 2;

        while(index > 0){
            if(indexTree[index*2] < indexTree[index*2+1]){
                indexTree[index] = indexTree[index*2+1];
                orgNumTree[index] = orgNumTree[index*2+1];
            }else if(indexTree[index*2] > indexTree[index*2+1]){
                indexTree[index] = indexTree[index*2];
                orgNumTree[index] = orgNumTree[index*2];
            }else if(indexTree[index*2] == indexTree[index*2+1]){
                indexTree[index] = indexTree[index*2+1];
                orgNumTree[index] = Math.max(orgNumTree[index*2], orgNumTree[index*2+1]);
            }

            index = index/2;
        }


    }
}