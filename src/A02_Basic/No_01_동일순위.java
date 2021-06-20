package A02_Basic;

public class No_01_동일순위 {
    public static void main(String[] args) {

//        int[] scores = {90, 80, 90, 100}; //이렇게 주어지면 아래처럼 오름차순으로 먼저 정렬하고 시작한다.
        int[] scores = {80, 90, 90, 100};

        int[] ranks = new int[scores.length];
        ranks[0] = 1;
        for (int i = 1, r = 1; i < ranks.length; i++) {
            ranks[i] = (scores[i] == scores[i - 1]) ? r : ++r;
        }

        for(int rank : ranks) {
            System.out.print(rank + " ");
        }
    }
}
