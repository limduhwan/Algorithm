package Z_합격_최적화_문제집;

import java.io.*;
import java.util.StringTokenizer;

//트리 전위, 후위 트리 속성
//트리를 구성할 수 있는가?
//이중 재귀를 구현할 수 있는가?
//https://girawhale.tistory.com/59
public class No_04_5639_재귀교본 {

    static class Node{
        int num;
        Node left, right;

        Node(int num){
            this.num = num;
        }

        Node(int num, Node left, Node right) {
            this.num = num;
            this.left = left;
            this.right = right;
        }

        void insert(int n){
            if(n < this.num){
                if(this.left == null){
                    this.left = new Node(n);
                }else{
                    this.left.insert(n);
                }
            }else{
                if(this.right == null){
                    this.right = new Node(n);
                }else{
                    this.right.insert(n);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("No_5639.txt"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st;

        Node root = new Node(Integer.parseInt(br.readLine()));

        String input;
        while(true){
            input = br.readLine();
            if(input == null || input.equals("")){
                break;
            }
            root.insert(Integer.parseInt(input));
        }

        preOrder(root);
//        postOrder(root);

    }

    static void preOrder(Node node){
        if(node == null) {
            return;
        }

        System.out.println(node.num);

        preOrder(node.left);
        preOrder(node.right);
    }

    static void postOrder(Node node){
        if(node == null){
            return;
        }

        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.num);
    }
}
