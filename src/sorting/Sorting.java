package sorting;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class Sorting {
    public static void main(String[] args) {
        int[] arr = new int[10];
        for(int i=0; i < 10; i++) {
            arr[i] = 10 - i;
        }
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));

        Integer[] arr2 = new Integer[]{23, 45, 68};
        // Arrays.sort(arr2, Collections.reverseOrder());
        Arrays.sort(arr2, (x, y) -> x>y? -1 : 1);
        System.out.println("Reverser sort " + Arrays.toString(arr2));

        String[] strings1 = new String[]{"Gauri", "Amogh", "Sunidhi"};
        Arrays.sort(strings1, Collections.reverseOrder());
        System.out.println(Arrays.toString(strings1));

        String[] strings2 = new String[]{"Gauri", "Amogh", "Sunidhi"};
        Arrays.sort(strings2, (o1, o2) -> -o1.compareTo(o2));
        System.out.println(Arrays.toString(strings2));

        int[][] arr3 = new int[][]{{5,10}, {2,5}, {4,7}, {3,9}};
        Arrays.sort(arr3, (a, b) -> -Integer.compare(a[1], b[1]));
        System.out.println("Sorted 2d array");
        for (int i = 0; i < 4; i++) {
            System.out.println(arr3[i][0] + " " + arr3[i][1]);
        }

        String a = "Amogh";
        String b = "Sunidhi";
        System.out.println("Comparison: " + a.compareTo(b));
    }
}
