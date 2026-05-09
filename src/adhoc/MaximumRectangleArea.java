package adhoc;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class MaximumRectangleArea {
    public static int largestArea(int[] arr) {
        int maxArea = 0;
        for (int i = 0; i < arr.length; i++) {
            int minHeight = Integer.MAX_VALUE;
            for (int j = i; j < arr.length; j++) {
                minHeight = min(minHeight, arr[j]);
                maxArea = max(maxArea, minHeight * (j - i + 1));
            }
        }
        return maxArea;
    }
    public static void main(String[] args) {
        int[] arr = new int[]{6, 2, 5, 4, 5, 1, 6};
        System.out.println(largestArea(arr));
    }
}
