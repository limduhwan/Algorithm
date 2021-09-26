package B07_수_BinarySearch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class 기본_No_02_Collections_Binarysearch {
    public static void main(String[] args)
    {
        List al = new ArrayList();
        al.add(1);
        al.add(2);
        al.add(3);
        al.add(10);
        al.add(20);

        // 10 은 현재 3번째 위치에 있습니다.
        int index = Collections.binarySearch(al, 10);
        System.out.println(index);

        // 13 은 현재 없습니다. 13 은 4번째 위치에 추가 되었을 것 입니다.
        // 그래서 이 함수는 (-4-1)
        // 즉, -5를리턴하게 됩니다.
        index = Collections.binarySearch(al, 13);
        System.out.println(index);
    }

//    이걸 외워야 함
//    Math.abs(Collections.binarySearch(task[a], b) + 1);
}
