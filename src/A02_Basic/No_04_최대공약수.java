package A02_Basic;

public class No_04_최대공약수 {
    public static void main(String[] args) {
        int temp = 0;
        int a = 6;
        int b = 2;

        while(a!=0){
            if(a<b){
                temp = a;
                a = b;
                b = temp;
            }
            a = a - b;
        }

        System.out.println("최대공약수: " + b);
    }
}
