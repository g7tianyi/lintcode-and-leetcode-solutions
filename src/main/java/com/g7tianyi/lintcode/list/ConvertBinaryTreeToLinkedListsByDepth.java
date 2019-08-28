package com.g7tianyi.lintcode.list;

import com.g7tianyi.common.ListNode;
import com.g7tianyi.common.Strings;
import com.g7tianyi.common.TreeNode;
import com.g7tianyi.util.Logger;
import lombok.AllArgsConstructor;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.function.Consumer;

/**
 * Created by g7tianyi on Aug 24, 2019
 *
 * @link https://www.lintcode.com/problem/convert-binary-tree-to-linked-lists-by-depth/description
 */
public class ConvertBinaryTreeToLinkedListsByDepth {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public List<ListNode> binaryTreeToLists(TreeNode root) {
      List<ListNode> result = new ArrayList<>();
      if (root == null) {
        return result;
      }

      // 利用队列进行二叉树的层序遍历

      Queue<TreeNode> queue = new LinkedList<>();
      queue.offer(root);
      queue.offer(null); // null节点作为哨兵节点，标志着一层的结束

      ListNode list = null, curr = null;
      while (!queue.isEmpty()) {

        TreeNode node = queue.poll();
        if (node == null) { // 一层结束了
          result.add(list);
          list = null;
          if (!queue.isEmpty()) {
            queue.offer(null);
          }
        } else {
          if (list == null) {
            list = new ListNode(node.val);
            curr = list;
          } else {
            curr.next = new ListNode(node.val);
            curr = curr.next;
          }
          if (node.left != null) {
            queue.offer(node.left);
          }
          if (node.right != null) {
            queue.offer(node.right);
          }
        }
      }

      return result;
    }
  }

  @AllArgsConstructor
  private static final class Input {

    private TreeNode root;
  }

  @Test
  public void test() {

    Solution s = new Solution();

    Consumer<Input> runner =
        input -> {
          List<ListNode> listNodes = s.binaryTreeToLists(input.root);
          listNodes.forEach(listNode -> log.info(Strings.format(listNode)));
          log.info("");
        };

    TreeNode root = new TreeNode(1);
    TreeNode node1 = new TreeNode(2);
    TreeNode node2 = new TreeNode(3);
    TreeNode node3 = new TreeNode(4);
    root.left = node1;
    root.right = node2;
    node1.left = node3;

    runner.accept(new Input(root));

    root.left = null;
    root.right = node1;
    node1.left = node2;
    runner.accept(new Input(root));
  }
}
