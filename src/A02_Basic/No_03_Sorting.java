package A02_Basic;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

//    Arrays.sort();
//    Collections.sort();
//https://m.blog.naver.com/PostView.naver?isHttpsRedirect=true&blogId=kyohack&logNo=220340125130
public class No_03_Sorting {
    static class comp implements Comparator<Integer>{
        public int compare(Integer o1, Integer o2){
            return o2 - o1; //내림차순(역순)
        }
    }

    public static void main(String[] args) {
        int myints[] = { 32, 71, 12, 45, 26, 80, 53, 33 };
        Integer[] myIntegers = new Integer[myints.length];

        for(int i = 0; i<myints.length; i++){
            myIntegers[i] = myints[i];
        }

        //0번째부터 4-1번째까지만 오름차순
        Arrays.sort(myints, 0, 4);
        System.out.println(Arrays.toString(myints));

        //오름차순
        Arrays.sort(myints);
        System.out.println(Arrays.toString(myints));

        //Integer타입 오름차순
        Arrays.sort(myIntegers);
        System.out.println(Arrays.toString(myIntegers));

        //내림차순 반드시 Integer타입
//        Arrays.sort(myIntegers, new comp());
//        System.out.println(Arrays.toString(myIntegers));

        Arrays.sort(myIntegers, Collections.reverseOrder());
        System.out.println(Arrays.toString(myIntegers));
    }

    //CompareTo에서 비교할 때
    //아래처럼 해야 안터진다.
//    Int.compare(this.weight - o.weight);
//    Long.compare(this.weight - o.weight);

}
