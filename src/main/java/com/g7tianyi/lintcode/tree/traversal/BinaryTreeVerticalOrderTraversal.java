package com.g7tianyi.lintcode.tree.traversal;

import com.g7tianyi.common.TreeNode;
import com.g7tianyi.util.Logger;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by g7tianyi on Oct 25, 2019
 *
 * @link https://www.lintcode.com/problem/binary-tree-vertical-order-traversal/description
 */
public class BinaryTreeVerticalOrderTraversal {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    class Element {
      private int value;
      private int depth;
      private int skew;

      public Element(int value, int depth, int skew) {
        this.value = value;
        this.depth = depth;
        this.skew = skew;
      }
    }

    public List<List<Integer>> verticalOrder(TreeNode root) {

      List<List<Integer>> result = new ArrayList<>();
      if (root == null) {
        return result;
      }

      List<Element> elements = new ArrayList<>();
      traverse(root, 0, 0, elements);

      elements.sort(
          new Comparator<Element>() {
            @Override
            public int compare(Element o1, Element o2) {
              if (o1.skew != o2.skew) {
                return Integer.compare(o1.skew, o2.skew);
              }
              return Integer.compare(o1.depth, o2.depth);
            }
          });

      int currSkew = Integer.MIN_VALUE;
      List<Integer> column = new ArrayList<>();
      for (Element element : elements) {
        if (currSkew == Integer.MIN_VALUE) {
          currSkew = element.skew;
        }
        if (currSkew != element.skew) {
          result.add(new ArrayList<>(column));

          column = new ArrayList<>();
          currSkew = element.skew;
        }
        column.add(element.value);
      }
      result.add(new ArrayList<>(column));

      return result;
    }

    private void traverse(TreeNode root, int skew, int depth, List<Element> elements) {

      elements.add(new Element(root.val, depth, skew));

      if (root.left != null) {
        traverse(root.left, skew - 1, depth + 1, elements);
      }
      if (root.right != null) {
        traverse(root.right, skew + 1, depth + 1, elements);
      }
    }
  }

  private final Solution s = new Solution();

  private final Consumer<TreeNode> c =
      root -> {
        s.verticalOrder(root).forEach(log::info);
        log.info();
      };

  @Test
  public void test() {
    c.accept(TreeNode.createTree("3,9,20,#,#,15,7"));
    c.accept(TreeNode.createTree("3,9,8,4,0,1,7"));
  }
}
