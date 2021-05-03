package A01_Grammar;

import java.util.ArrayList;
import java.util.Iterator;

//https://coding-factory.tistory.com/551
//가변적으로 변하는 선형리스트
//데이터가 늘어날 수록 성능에 악영향
//중간에 데이터를 인서트해야 할 경우가 많다면 linkedlist를 활용
public class ArrayList_ {
    public static void main(String[] args){
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(3); //값 추가
        list.add(null); //null값도 add가능
        list.add(1,10); //index 1뒤에 10 삽입

        System.out.println(list.get(0));

        for(Integer i : list) { //for문을 통한 전체출력
            System.out.println(i);
        }

        Iterator iter = list.iterator(); //Iterator 선언
        while(iter.hasNext()){//다음값이 있는지 체크
            System.out.println(iter.next()); //값 출력
        }

        System.out.println(list.contains(1)); //list에 1이 있는지 검색 : true
        System.out.println(list.indexOf(3)); //1이 있는 index반환 없으면 -1

        list.remove(1);  //index 1 제거
        list.clear();  //모든 값 제거

//        Student student = new Student(name,age);
//        ArrayList<Student> members = new ArrayList<Student>();
//        members.add(student);
//        members.add(new Member("홍길동",15));
    }
}

