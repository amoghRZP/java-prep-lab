package stack;

import java.util.Stack;

public class StackExample {
    public static void main(String[] args) {
        Stack<Integer> st = new Stack<>();
        boolean result = st.add(2);
        System.out.println(result);
        int result2 = st.push(1);
        System.out.println(result2);
        st.push(3);
        int result3 = st.pop();
        System.out.println(result3);
        result3 = st.peek();
        System.out.println(result3);
        System.out.println(st.pop());
        System.out.println(st.pop());
        System.out.println(st.empty()+" "+st.size());

        st.isEmpty();

        st.push(11);
        st.push(12);
        System.out.println("\nIterate Stack");
        for(Integer element: st) {
            System.out.println(element);
        }
    }
}
