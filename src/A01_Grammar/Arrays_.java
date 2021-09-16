package A01_Grammar;

import java.util.Arrays;

public class Arrays_ {
    public static void main(String[] args) {

        Integer array[] = {3,2,1,6,5,7,4,8,10,9};

        //오름차순 정렬
//        Arrays.sort(array);
//        System.out.println(Arrays.toString(array));
        //결과 : [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]

//        1을 리턴시키면 오름차순 정렬이 되며, -1을 리턴시키면 내림차순 정렬이 된다.
        //내림차순 정렬
        Arrays.sort(array, (o1, o2) -> {
            System.out.println(o1 +" "+ o2);
            if(o1 < o2) {
                System.out.println("오름차순");
                return 1; //오름차순
            } else {
                System.out.println("내림차순");
                return -1; //내림차순
            }
        });
        System.out.println(Arrays.toString(array));
        //결과 : [10, 9, 8, 7, 6, 5, 4, 3, 2, 1]

        //오름차순
        Arrays.sort(array, (o1, o2) -> o1 - o2);
        System.out.println(Arrays.toString(array));

        //내림차순
        Arrays.sort(array, (o1, o2) -> o2 - o1);
        System.out.println(Arrays.toString(array));


    }
}
