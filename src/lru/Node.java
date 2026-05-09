package lru;

public class Node {
    public String val;
    Node next;
    Node prev;

    public Node(String str) {
        val = str;
        next = null;
        prev = null;
    }
}
