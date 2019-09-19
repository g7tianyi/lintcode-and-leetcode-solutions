package com.g7tianyi.lintcode.tree;

import com.g7tianyi.common.TreeNode;
import com.g7tianyi.util.Logger;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by g7tianyi on Sep 13, 2019
 *
 * @link https://www.lintcode.com/problem/serialize-and-deserialize-binary-tree/description
 */
public class SerializeAndDeserializeBinaryTree {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public String serialize(TreeNode root) {

      if (root == null) {
        return "";
      }

      Queue<TreeNode> myQueue = new LinkedList<>();
      myQueue.offer(root);

      StringBuilder sb = new StringBuilder();
      while (!myQueue.isEmpty()) {
        TreeNode node = myQueue.poll();
        if (node != null) {
          sb.append(node.val).append(',');
          myQueue.offer(node.left);
          myQueue.offer(node.right);
        } else {
          sb.append("#,");
        }
      }

      while (true) {
        int index = sb.length() - 1;
        char ch = sb.charAt(index);
        if (ch == '#' || ch == ',') {
          sb.deleteCharAt(index);
        } else {
          break;
        }
      }

      return sb.toString();
    }

    public TreeNode deserialize(String data) {
      if (data == null) {
        return null;
      }
      data = data.trim();
      if (data.length() == 0) {
        return null;
      }

      String[] elems = data.split(",");
      List<TreeNode> nodes = new ArrayList<>();

      TreeNode root = new TreeNode(Integer.parseInt(elems[0]));
      nodes.add(root);

      TreeNode parent = root, curr;
      for (int i = 1, j = 1, k = 0; i < elems.length; ++i) {
        if (elems[i].equals("#")) {
          curr = null;
        } else {
          curr = new TreeNode(Integer.parseInt(elems[i]));
          nodes.add(curr);
        }

        if (k == 0) {
          parent.left = curr;
        } else {
          parent.right = curr;
          parent = nodes.get(j);
          ++j;
        }
        k ^= 1;
      }

      return root;
    }
  }

  @Test
  public void test() {

    Solution s = new Solution();

    log.info(
        s.serialize(s.deserialize("1,2,3,11,#,4,5,#,#,6,7,#,10,#,#,8,9,#,#,12,13,#,#,#,#,#,14")));
    log.info(s.serialize(TreeNode.createTree("2,2,5,#,#,5,7")));
    log.info(s.serialize(TreeNode.createTree("3,9,20,#,#,15")));
    log.info(s.serialize(TreeNode.createTree("2")));

    TreeNode tree;
    String data;

    tree = TreeNode.createRandomTree(13);
    data = s.serialize(tree);
    log.info(data);

    tree = TreeNode.createRandomTree(1);
    data = s.serialize(tree);
    log.info(data);
  }
}
