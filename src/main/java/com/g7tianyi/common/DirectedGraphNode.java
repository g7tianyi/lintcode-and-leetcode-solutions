package com.g7tianyi.common;

import java.util.ArrayList;

/** Created by g7tianyi on Oct 25, 2019 */
public class DirectedGraphNode {

  public int label;
  public ArrayList<DirectedGraphNode> neighbors;

  public DirectedGraphNode(int x) {
    label = x;
    neighbors = new ArrayList<>();
  }
}
