package com.g7tianyi.common;

import org.junit.Test;

import java.util.function.Consumer;

/** Created by g7tianyi on Aug 24, 2019 */
public class TreeNodeTest {

  @Test
  public void test_createTree() {

    Consumer<String> c =
        str -> {
          System.out.println(str);
          String s = TreeNode.createTree(str).serialize();
          System.out.println(s);
          System.out.println();
        };

    c.accept("1,2,3,#,4,5");
    c.accept("1,2,3,4,5");
    c.accept("1,2,3,4,#,5");
    c.accept("1,2,3,4,#,#,5");
    c.accept("1,#,3,4,5");
  }

  @Test
  public void test_createRandomTree() {
    String s = TreeNode.createRandomTree().serialize();
    System.out.println(s);
  }
}
