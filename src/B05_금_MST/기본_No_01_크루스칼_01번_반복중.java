package B05_금_MST;


import java.util.ArrayList;
import java.util.Collections;
//최소신장트리란
//https://blog.naver.com/ssarang8649/220992988177
//최소비용으로 모든 정점을 연결하는 트리
//https://brenden.tistory.com/36

public class 기본_No_01_크루스칼_01번_반복중 {
    static ArrayList<Edge> edgeList;
    static int[] parent;
    static int sum;
    public static void main(String[] args) {
        edgeList = new ArrayList<Edge>();
        edgeList.add(new Edge(1,4,4));
        edgeList.add(new Edge(1,2,6));
        edgeList.add(new Edge(2,3,5));
        edgeList.add(new Edge(2,4,3));
        edgeList.add(new Edge(2,5,7));
        edgeList.add(new Edge(2,6,8));
        edgeList.add(new Edge(3,6,8));
        edgeList.add(new Edge(4,5,9));
        edgeList.add(new Edge(5,6,11));

        parent = new int[7];

        for (int i = 1; i <=6 ; i++) {
            parent[i] = i;
        }

        Collections.sort(edgeList);

        for (int i = 0; i <edgeList.size() ; i++) {
            Edge edge = edgeList.get(i);

            if(!isSameParent(edge.start, edge.end)){
                sum = sum + edge.weight;
                union(edge.start, edge.end);
            }

        }

        System.out.println(sum);

    }

    static class Edge implements Comparable<Edge>{
        int start;
        int end;
        int weight;

        Edge(int start, int end, int weight){
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        public int compareTo(Edge o){
            return Integer.compare(this.weight, o.weight);
        }
    }

    static void union(int a, int b){
        int x = find(a);
        int y = find(b);

        if(x!=y){
            if(x<y){
                parent[y] = x;
            }else{
                parent[x] = y;
            }
        }
    }

    static int find(int x){
        if(x == parent[x]){
            return x;
        }else{
            return parent[x] = find(parent[x]);
        }
    }

    static boolean isSameParent(int a, int b){
        int x = find(a);
        int y = find(b);

        if(x == y){
            return true;
        }
        return false;
    }
}
