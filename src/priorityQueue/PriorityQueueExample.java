package priorityQueue;

import java.util.*;

public class PriorityQueueExample {
    public static void main(String[] args) {
        // MinHeap implemented with comparator
        PriorityQueue<Integer> minHeapInt = new PriorityQueue<>((o1, o2) -> {
            if (o1<o2) {
                return -1;
            } else if (o1>o2) {
                return 1;
            } else {
                return 0;
            }
        });
        minHeapInt.add(5);
        minHeapInt.add(1);
        minHeapInt.add(2);

        System.out.println("Min Heap Int Start:");
        System.out.println(minHeapInt.poll());
        System.out.println(minHeapInt.poll());
        System.out.println(minHeapInt.poll());
        System.out.println(minHeapInt.peek() + " " + minHeapInt.size()+ " " + minHeapInt.isEmpty()+"\n\n");

        // MinHeap implemented without comparator
        PriorityQueue<Integer> minHeapIntWithoutComparator = new PriorityQueue<>();
        minHeapIntWithoutComparator.add(5);
        minHeapIntWithoutComparator.add(1);
        minHeapIntWithoutComparator.add(2);

        System.out.println("Min Heap Int Without Comparator Start:");
        System.out.println(minHeapIntWithoutComparator.poll());
        System.out.println(minHeapIntWithoutComparator.poll());
        System.out.println(minHeapIntWithoutComparator.poll());
        System.out.println(minHeapIntWithoutComparator.peek() + " " + minHeapIntWithoutComparator.size() + " " + minHeapIntWithoutComparator.isEmpty()+"\n\n");

        PriorityQueue<Integer> maxHeapInt = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (o1<o2) {
                    return 1;
                } else if (o1>o2) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });

        maxHeapInt.add(5);
        maxHeapInt.add(1);
        maxHeapInt.add(2);

        System.out.println("Max Heap Int Start:");
        System.out.println(maxHeapInt.poll());
        System.out.println(maxHeapInt.poll());
        System.out.println(maxHeapInt.poll());
        System.out.println(maxHeapInt.peek() + " " + maxHeapInt.size()+ " " + maxHeapInt.isEmpty()+"\n\n");

        // made max Heap by adding minus to default compare function
        PriorityQueue<String> maxHeapString = new PriorityQueue<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return -o1.compareTo(o2);
            }
        });
        // PriorityQueue<String> maxHeapString = new PriorityQueue<>((o1, o2) -> -o1.compareTo(o2));
        Collections.reverseOrder();
        maxHeapString.add("Amogh");
        maxHeapString.add("Zscaler");
        maxHeapString.add("Sunidhi");

        System.out.println("Max Heap String Start:");
        System.out.println(maxHeapString.poll());
        System.out.println(maxHeapString.poll());
        System.out.println(maxHeapString.poll());
        System.out.println(maxHeapString.peek() +" " + maxHeapString.size()+ " "+ maxHeapString.isEmpty()+"\n\n");

        PriorityQueue<String> minHeapString = new PriorityQueue<>();
        minHeapString.add("Amogh");
        minHeapString.add("Zscaler");
        minHeapString.add("Sunidhi");
        //Iterate Priority Queue
        System.out.println("Iterating Priority Queue : Min Heap String");
        for(String element: minHeapString) {
            System.out.println(element);
        }
    }
}
