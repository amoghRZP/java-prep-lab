package trie;

/*
Find shortest uniquely identifying prefixes on a set of words.

E.g. "pineapple" "pinetree" "pill" => "pinea" "pinet" and "pil"
eg - "dog", "dogs", "cat"
       No solution because dog is prefix of both dog and dogs
*/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
c  a t

p  i  n e a p p l e d
   l    t
   l    r
        e
        e

pineapple


Tree making: O(N*k)

hashmap :O(26)

Getting prefix: O(N*k)

              str     div
p -> i         p
i -> l, n      pi       1
n -  t a       pin.     3
e - a
a - p
*/
public class UniquePrefix {
    static HashMap<Character, Tree> map;

    // Trie
    public static class Tree {
        char val;
        List<Tree> next;

        public Tree(char c) {
            this.val = c;
        }

    }
    public static List<String> Foo(List<String> words) {
        List<String> result = new ArrayList<>();

        //Create Trie by traversing each word and each char
        for(String word: words) {
            //process each word
            Tree prevNode = map.computeIfAbsent(word.charAt(0), k -> new Tree(word.charAt(0)));

            // traverse each char of word
            for (int i=1; i<word.length(); i++)
            {
                char c = word.charAt(i);
                boolean flagNextNodeFound = false;
                List<Tree> nextNodes = prevNode.next;
                if(nextNodes != null) {
                    for (Tree nextNode : nextNodes) {
                        if (nextNode.val == c) {
                            prevNode = nextNode;
                            flagNextNodeFound = true;
                            break;
                        }
                    }
                }

                if(!flagNextNodeFound) {
                    Tree node = new Tree(word.charAt(i));
                    if(prevNode.next == null) {
                        prevNode.next = new ArrayList<>();
                    }
                    prevNode.next.add(node);
                    prevNode = node;
                }
            }
        }
        //System.out.println(map);

        // Search Prefix in the trie
        for(String word: words) {
            int i = 0;
            char begin = word.charAt(i);
            Tree root = map.get(begin);
            int div = 0;
            System.out.println(word);
            while(i<word.length() && root.next != null) {
                if(root.next.size() > 1) {
                    for(Tree nextNode : root.next) {
                        System.out.print(nextNode.val +" ");
                    }
                    div = i;
                    System.out.print(div+" ");
                }

                for(Tree nextNode : root.next) {
                    if(i+1 < word.length() && nextNode.val == word.charAt(i+1)) {
                        root = nextNode;
                        break;
                    }
                }
                i++;
            }
            System.out.println();
            if(div == 0) {
                result.add(word.substring(0, 1));
            } else {
                result.add(word.substring(0, div + 2));
            }
        }
        return result;
    }

    public static void main (String[] args) {
        map = new HashMap<>();
        List<String> list = new ArrayList<>();
        list.add("pineapple");
        list.add("pinetree");
        list.add("pill");
        list.add("cat");
        list.add("dog");

        System.out.println(Foo(list));
    }
}
