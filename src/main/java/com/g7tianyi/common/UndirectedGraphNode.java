package com.g7tianyi.common;

import java.util.ArrayList;
import java.util.List;

/** Created by g7tianyi on Oct 25, 2019 */
public class UndirectedGraphNode {

  public int label;
  public List<UndirectedGraphNode> neighbors;

  public UndirectedGraphNode(int x) {
    label = x;
    neighbors = new ArrayList<>();
  }

  @Override
  public String toString() {
    return String.valueOf(label);
  }
}
