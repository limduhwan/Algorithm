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
        System.out.println("최대공약수: " + GCD(a, b));
    }

    static int GCD(int a, int b){
        if(a<b){
            int temp = b;
            b = a;
            a = temp;
        }

        if(b==0) return a;

        while(true){
            int r = a%b;
            if(r==0) return b;
            a = b;
            b = r;
        }
    }

    //이 코드를 외우자
    static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
}
