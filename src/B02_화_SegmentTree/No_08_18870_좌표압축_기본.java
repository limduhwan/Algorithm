package B02_화_SegmentTree;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

//수직선 위에 N개의 좌표 X1, X2, ..., XN이 있다.
// 이 좌표에 좌표 압축을 적용하려고 한다.
//
//Xi를 좌표 압축한 결과 X'i의 값은 Xi > Xj를 만족하는
// 서로 다른 좌표의 개수와 같아야 한다.
//
//X1, X2, ..., XN에 좌표 압축을 적용한 결과 X'1, X'2, ..., X'N를 출력해보자.

//5
//2 4 -10 4 -9

//2 3 0 3 1
//https://infodon.tistory.com/53
public class No_08_18870_좌표압축_기본 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("No_18870.txt"));
        StringBuilder sb = new StringBuilder();

        int num = Integer.parseInt(br.readLine());

        String[] arr = br.readLine().split(" ");
        int[] an = new int[num];
        int cnt = 0;

        for (int i = 0; i < arr.length; i++) {
            an[i] = Integer.parseInt(arr[i]);
        }

        int[] temp = an.clone();

        Arrays.sort(an);

        for (int i = 0; i < num; i++) {
            System.out.print(an[i] + " ");
        }
        System.out.println("");

        HashMap<Integer, Integer> hmap = new HashMap<>();

        for (int i = 0; i < an.length; i++) {
            if (!hmap.containsKey(an[i]))
                hmap.put(an[i], cnt++);
        }

        for (int i = 0; i < num; i++) {
            sb.append(hmap.get(temp[i])).append(" ");
        }
        System.out.println(sb.toString());
    }
}
