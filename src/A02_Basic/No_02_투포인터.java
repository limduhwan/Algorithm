package A02_Basic;

import java.util.Arrays;

public class No_02_투포인터 {
    public static void main(String[] args) {
        int[] input = new int[]{1, 2, 3, 4, 2, 5, 3, 1, 1, 2};
        int targetSum = 5;

//        Arrays.sort(input);
        int start = 0;
        int end = 0;
        int tempSum = 0;
        int result = 0;

        while(true){
//            구간합이 목표값보다 크거나 같으면 현재값을 빼고 start pointer++
            if(tempSum >= targetSum){
                tempSum = tempSum - input[start++];
            }
//            끝까지 갔으면 브레이크
            else if(end == input.length){
                break;
            }
//            구간합이 목표값보다 작으면 현재값을 더하고 end pointer ++
            else{
                tempSum = tempSum + input[end++];
            }

            if(tempSum == targetSum){
                result++;
            }
        }

//        [2,3] [5], [3, 1, 1] -> 3개
        System.out.println("result : "+ result);
    }
}
