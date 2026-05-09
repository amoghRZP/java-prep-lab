package binarySearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int[] arr = new int[]{1,3,5,8,11};
        int index = Arrays.binarySearch(arr, 8);
        System.out.println("index is:" + index);

        List<Integer> al = new ArrayList<Integer>();
        al.add(100);
        al.add(50);
        al.add(30);
        al.add(10);
        al.add(2);

        index = Collections.binarySearch(al, 50);
        System.out.println("index is:" + index);
    }
}
