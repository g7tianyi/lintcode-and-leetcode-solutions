package com.g7tianyi.common;

import java.util.ArrayList;
import java.util.List;

/** Created by g7tianyi on Aug 24, 2019 */
public class TreeNode {

  public int val;

  public TreeNode left;
  public TreeNode right;

  public TreeNode(int val) {
    this.val = val;
    this.left = null;
    this.right = null;
  }

  public static TreeNode createTree(String s) {
    if (s == null) {
      return null;
    }
    s = s.trim();
    if (s.length() == 0) {
      return null;
    }

    String[] elems = s.split(",");
    List<TreeNode> nodes = new ArrayList<>(elems.length);
    for (int i = 0, value; i < elems.length; i++) {
      if (elems[i].equals("#")) {
        nodes.add(null);
        continue;
      }

      value = Integer.valueOf(elems[i]);
      TreeNode node = new TreeNode(value);
      nodes.add(node);

      if (i == 0) {
        continue;
      }

      int parentIndex = ((i + 1) >> 1) - 1;
      TreeNode parentNode = nodes.get(parentIndex);
      if (parentNode == null) {
        continue;
      }

      if (i % 2 == 1) {
        parentNode.left = node;
      } else {
        parentNode.right = node;
      }
    }

    return nodes.get(0);
  }
}
