package A02_Basic;

//https://lktprogrammer.tistory.com/106
public class 재귀_기초1 {
    public static int Function(int num){
        if(num == 1){
            return 1;
        } else {
            return num + Function(num -1);
        }
    }

    public static void main(String[] args)  {
        System.out.println("1부터 5까지의 합은 : " + Function(5));
    }
}
