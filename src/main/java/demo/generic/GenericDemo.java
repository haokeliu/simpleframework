package demo.generic;

import java.util.LinkedList;
import java.util.List;

public class GenericDemo {
    public static void main(String[] args) {
        List<String> linkedList = new LinkedList();
        linkedList.add("words");
//        linkedList.add(1);
        for (int i=0;i<linkedList.size();i++){
            String item = (String)linkedList.get(i);
            System.out.println(item);
        }

        GenericClassExample<String> generic = new GenericClassExample<>("abc");
        System.out.println(generic.getMember().getClass());
    }
}
