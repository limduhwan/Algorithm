package A01_Grammar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//https://www.nextree.co.kr/p11101/
//다익스트라에서 쓰인다.
public class Comparable_ {
    public static void main(String[] args) {
        List<Player> players = new ArrayList<>();
        players.add(new Player(100, 1));
        players.add(new Player(200, 2));
        players.add(new Player(300, 3));
        players.add(new Player(400, 3));
        players.add(new Player(500, 5));

        Collections.sort(players);
        for (int i = 0; i <players.size() ; i++) {
            System.out.println(players.get(i).name+" / "+players.get(i).score);
        }
    }

    static class Player implements Comparable<Player> {
        private int name;
        private int score;

        Player(int name, int score) {
            this.name = name;
            this.score = score;
        }

        @Override
        public int compareTo(Player o) {
//            System.out.println(this.score +"  /  "+o.score);
            if(this.score == o.score){
                return Integer.compare(this.name, o.name);
            }else{
                return o.score - this.score;
            }
        }
    }
}
