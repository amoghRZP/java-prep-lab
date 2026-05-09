package tree;

import java.util.LinkedList;
import java.util.Queue;

public class TreeTraversal {
    public static void main(String[] args) {
        /*
                 5
                / \
               3   6
              / \
             1   4
              \
               2

             level order traversal
             5 3 6 1 4 2
             In order traversal
             1 2 3 4 5 6
             left view
             5 3 1 2
         */
        ClosestBinaryTreeValues.TreeNode root = new ClosestBinaryTreeValues.TreeNode(5);
        ClosestBinaryTreeValues.TreeNode node = new ClosestBinaryTreeValues.TreeNode(3);
        root.left = node;
        node = new ClosestBinaryTreeValues.TreeNode(6);
        root.right = node;
        node = new ClosestBinaryTreeValues.TreeNode(1);
        node.right = new ClosestBinaryTreeValues.TreeNode(2);
        root.left.left = node;
        node = new ClosestBinaryTreeValues.TreeNode(4);
        root.left.right = node;

        System.out.println("Level Order Traversal:");
        levelOrderTraversal(root);

        System.out.println("\nIn Order Traversal:");
        inOrderTraversal(root);

        System.out.println("\nPost Order Traversal:");
        postOrderTraversal(root);

        System.out.println("\nPre Order Traversal:");
        preOrderTraversal(root);

        System.out.println("\nLeft View of Binary Tree:");
        leftViewOfBinaryTree(root);
    }


    public static void levelOrderTraversal(ClosestBinaryTreeValues.TreeNode root) {
        Queue<ClosestBinaryTreeValues.TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()) {
            ClosestBinaryTreeValues.TreeNode node = queue.poll();
            System.out.print(node.val + " ");

            if(node.left != null) {
                queue.add(node.left);
            }
            if(node.right != null) {
                queue.add(node.right);
            }
        }
    }

    public static class TreeNodeWithLevel {
        ClosestBinaryTreeValues.TreeNode node;
        int level;
        public TreeNodeWithLevel(ClosestBinaryTreeValues.TreeNode node, int level) {
            this.node = node;
            this.level = level;
        }
    }
    
    public static void leftViewOfBinaryTree(ClosestBinaryTreeValues.TreeNode root) {
        Queue<TreeNodeWithLevel> queue = new LinkedList<>();
        queue.add(new TreeNodeWithLevel(root, 0));
        int currLevel = -1;
        while(!queue.isEmpty()) {
            TreeNodeWithLevel node = queue.poll();
            if (currLevel != node.level) {
                System.out.print(node.node.val + " ");
                currLevel = node.level;
            }
            if(node.node.left != null) {
                queue.add(new TreeNodeWithLevel(node.node.left, node.level + 1));
            }
            if(node.node.right != null) {
                queue.add(new TreeNodeWithLevel(node.node.right, node.level + 1));
            }
        }
    }

    public static void inOrderTraversal(ClosestBinaryTreeValues.TreeNode root) {
        if(root == null) {
            return;
        }

        inOrderTraversal(root.left);
        System.out.print(root.val+" ");
        inOrderTraversal(root.right);
    }

    public static void postOrderTraversal(ClosestBinaryTreeValues.TreeNode root) {
        if(root == null) {
            return;
        }

        postOrderTraversal(root.left);
        postOrderTraversal(root.right);
        System.out.print(root.val+" ");
    }

    public static void preOrderTraversal(ClosestBinaryTreeValues.TreeNode root) {
        if(root == null) {
            return;
        }

        System.out.print(root.val+" ");
        preOrderTraversal(root.left);
        preOrderTraversal(root.right);
    }


}
