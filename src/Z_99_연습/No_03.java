package Z_99_연습;

import java.util.ArrayList;

public class No_03 {
    static int function(int num) {
        if(num == 1){
            return 1;
        }
        int aaa = function(num-1);
        int total = num + aaa;
        System.out.println(aaa);
        return total;
    }

    public static void main(String[] args) {
        System.out.println(function(5));
    }
}
