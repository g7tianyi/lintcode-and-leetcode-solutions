package com.g7tianyi.common;

import java.util.*;

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

  public String serialize() {
    return serialize(this, 1, getHeight());
  }

  private String serialize(TreeNode root, int currentHeight, int totalHeight) {

    StringBuilder sb = new StringBuilder();
    int spaces = getSpaceCount(totalHeight - currentHeight + 1);
    if (root == null) {
      // create a 'spatial' block and return it
      String row = String.format("%" + (2 * spaces + 1) + "s%n", "");
      // now repeat this row space+1 times
      return new String(new char[spaces + 1]).replace("\0", row);
    }

    if (currentHeight == totalHeight) {
      return String.valueOf(root.val);
    }

    int slashes = getSlashCount(totalHeight - currentHeight + 1);
    sb.append(String.format("%" + (spaces + 1) + "s%" + spaces + "s", root.val + "", ""));
    sb.append("\n");
    // now print / and \
    // but make sure that left and right exists
    char leftSlash = root.left == null ? ' ' : '/';
    char rightSlash = root.right == null ? ' ' : '\\';
    int spaceInBetween = 1;
    for (int i = 0, space = spaces - 1; i < slashes; i++, space--, spaceInBetween += 2) {
      for (int j = 0; j < space; j++) sb.append(" ");
      sb.append(leftSlash);
      for (int j = 0; j < spaceInBetween; j++) sb.append(" ");
      sb.append(rightSlash);
      for (int j = 0; j < space; j++) sb.append(" ");
      sb.append("\n");
    }
    // sb.append("\n");

    // now get string representations of left and right subtrees
    String leftTree = serialize(root.left, currentHeight + 1, totalHeight);
    String rightTree = serialize(root.right, currentHeight + 1, totalHeight);
    // now line by line print the trees side by side
    Scanner leftScanner = new Scanner(leftTree);
    Scanner rightScanner = new Scanner(rightTree);
    while (leftScanner.hasNextLine()) {
      if (currentHeight == totalHeight - 1) {
        sb.append(String.format("%-2s %2s", leftScanner.nextLine(), rightScanner.nextLine()));
        sb.append("\n");
        spaceInBetween -= 2;
      } else {
        sb.append(leftScanner.nextLine());
        sb.append(" ");
        sb.append(rightScanner.nextLine()).append('\n');
      }
    }

    return sb.toString();
  }

  public int getHeight() {
    return getHeight(this);
  }

  private int getHeight(TreeNode root) {
    if (root == null) return 0;
    return Math.max(getHeight(root.left), getHeight(root.right)) + 1;
  }

  private int getSlashCount(int height) {
    if (height <= 3) return height - 1;
    return (int) (3 * Math.pow(2, height - 3) - 1);
  }

  private int getSpaceCount(int height) {
    return (int) (3 * Math.pow(2, height - 2) - 1);
  }

  @Override
  public String toString() {
    return this.val + "";
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

  public static TreeNode createRandomTree() {
    return createRandomTree(1 + (int) (Math.random() * 6));
  }

  public static TreeNode createRandomTree(int maxNode) {

    TreeNode root = new TreeNode(1);
    Queue<TreeNode> myQueue = new LinkedList<>();
    myQueue.offer(root);
    int count = 1;
    while (!myQueue.isEmpty()) {
      TreeNode node = myQueue.poll();
      if (node == null) {
        continue;
      }
      if (Math.random() < 0.85) {
        node.left = new TreeNode(node.val << 1);
        myQueue.offer(node.left);
        if (++count >= maxNode) {
          break;
        }
      }
      if (Math.random() < 0.85) {
        node.right = new TreeNode((node.val << 1) + 1);
        myQueue.offer(node.right);
        if (++count >= maxNode) {
          break;
        }
      }
    }

    return root;
  }
}
