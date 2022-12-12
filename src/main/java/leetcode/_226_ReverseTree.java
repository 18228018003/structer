package leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 谷歌面试题 翻转二叉树
 *
 */
public class _226_ReverseTree {
    private static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int val){
            this.val = val;
        }
    }

    /**
     * 使用递归的方法翻转 二叉树
     * @param root 根节点
     * @return 节点
     */
    public TreeNode invertTree(TreeNode root){
        if (root == null) return null;
        TreeNode left = root.left;
        root.left = invertTree(root.right);
        root.right = invertTree(left);
        return root;
    }
    /**
     * 使用层序遍历的方法翻转 二叉树
     * @param root 根节点
     * @return 节点
     */
    public TreeNode invertTreeLevel(TreeNode root){
        if (root == null) return null;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            TreeNode node = queue.poll();
            TreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;
            if (node.left != null){
                queue.offer(node.left);
            }
            if (node.right != null){
                queue.offer(node.right);
            }
        }
        return root;
    }
    public static void main(String[] args) {
        TreeNode root = new TreeNode(7);
        root.left = new TreeNode(4);
        root.right = new TreeNode(9);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(8);
        root.right.right = new TreeNode(10);

    }
}
