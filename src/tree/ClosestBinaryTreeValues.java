package tree;

import java.util.ArrayList;
import java.util.List;
import java.lang.Math;
import java.util.PriorityQueue;

// Problem: https://leetcode.ca/2016-08-28-272-Closest-Binary-Search-Tree-Value-II/
public class ClosestBinaryTreeValues {

    public static List<Integer> result = new ArrayList<>();

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args){
        TreeNode root = new TreeNode(4);
        TreeNode node = new TreeNode(2);
        root.left = node;
        node = new TreeNode(5);
        root.right = node;
        node = new TreeNode(1);
        root.left.left = node;
        node = new TreeNode(3);
        root.left.right = node;
        double target = 3.5;
        closestKNodesUsingListForBST(root, target, 2);
        for (Integer val: result) {
            //System.out.println(val);
        }

        PriorityQueue<TreeNode> pq = new PriorityQueue<>((o1, o2) -> {
            if (Math.abs(target - o1.val) < Math.abs(target - o2.val)) {
                return 1; // return 1 if you want o1 to be selected
            } else if (Math.abs(target - o1.val) > Math.abs(target - o2.val)) {
                return -1; // return -1 if you want o2 to be selected
            } else {
                return 0; // return 0 for equal
            }
        });

        closestKNodesUsingHeap(root, pq,2);
        for(TreeNode tn: pq) {
            System.out.println(tn.val);
        }

    }


    public static void closestKNodesUsingListForBST(TreeNode root, double target, int k) {
        if(root == null) {
            return;
        }

        closestKNodesUsingListForBST(root.left, target, k);
        if(result.size() >= k) {
            if(Math.abs(result.get(0) - target) > Math.abs(root.val - target) ) {
                result.remove(0);
                result.add(root.val);
            }
        } else {
            result.add(root.val);
        }
        closestKNodesUsingListForBST(root.right, target, k);
    }

    public static void closestKNodesUsingHeap(TreeNode root, PriorityQueue<TreeNode> pq, int k) {
        if(root == null) {
            return;
        }
        if(pq.size() > k) {
            TreeNode tn = pq.poll();
            System.out.println("DEBUG:"+ tn.val);
        }
        pq.add(root);
        closestKNodesUsingHeap(root.left, pq, k);
        closestKNodesUsingHeap(root.right, pq, k);
    }

}

