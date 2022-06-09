package com.disaster.datastructure.tree.v1;


public class Client {
    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(3); // 根节点 root
        TreeNode n2 = new TreeNode(4);
        TreeNode n3 = new TreeNode(5);
        TreeNode n4 = new TreeNode(1);
        TreeNode n5 = new TreeNode(2);
        // 构建引用指向
        n1.left = n2;
        n1.right = n3;
        n2.left = n4;
        n2.right = n5;
        System.out.println("n1 = " + n1);
    }
    public static class TreeNode{
        int val;        // 节点值
        TreeNode left;  // 左子节点
        TreeNode right; // 右子节点
        TreeNode(int x) { val = x; }

        @Override
        public String toString() {
            return "TreeNode{" +
                    "val=" + val +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }
}
