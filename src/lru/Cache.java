package lru;

import java.util.HashMap;
import java.util.Map;

public class Cache {
    Node head;
    Node tail;
    Integer capacity;
    Integer count;
    Map<String, Node> map;

    public Cache(Integer capacity) {
        this.capacity = capacity;
        head = null;
        tail = null;
        count = 0;
        map = new HashMap<>();
    }

    public void processStream(String str) {
        if (map.containsKey(str)) {
            Node currentNode = map.get(str);

            if (tail == currentNode) {
                tail = tail.prev;
            }

            if (head != currentNode) {

                // remove current node from its position safely
                if (currentNode.prev != null) {
                    currentNode.prev.next = currentNode.next;
                }
                if (currentNode.next != null) {
                    currentNode.next.prev = currentNode.prev;
                }


                //put current node in the front
                head.prev = currentNode;
                currentNode.next = head;
                currentNode.prev = null;
                head = currentNode;
            }
        } else {

            // Create new node and assign it as head, handle condition when empty queue
            Node newNode = new Node(str);
            map.put(str, newNode);
            if (head != null) {
                head.prev = newNode;
            }
            newNode.next = head;
            newNode.prev = null;
            head = newNode;

            // When empty queue head is tail as well
            if (count == 0) {
                tail = head;
            }
            count++;

            if (count > capacity) {
                map.remove(tail.val);
                tail = tail.prev;
                tail.next = null;
            }
        }

        Node node = head;
        while (node != null) {
            System.out.print(node.val +" ");
            node = node.next;
        }

        assert head != null;
        System.out.print("head: "+head.val+" tail: "+tail.val);
        System.out.println();
    }

}
