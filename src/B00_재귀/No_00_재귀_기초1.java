package B00_재귀;

//https://lktprogrammer.tistory.com/106
public class No_00_재귀_기초1 {

    public static int Function(int num){
        if(num == 1){
            return 1;
        } else {
//            return num + Function(num -1);

            int total = num + Function(num -1);
            System.out.println(total);
            return total;

//            int aaa = function(num-1);
//            int total = num + aaa;
//            System.out.println(aaa);
//            return total;
        }
    }

    public static void main(String[] args)  {

        System.out.println("1부터 5까지의 합은 : " + Function(5));
    }
}
