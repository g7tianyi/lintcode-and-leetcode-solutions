package com.g7tianyi.util;

import com.g7tianyi.common.ListNode;
import com.g7tianyi.common.Strings;

import java.util.List;

/** Created by g7tianyi on Aug 24, 2019 */
public final class Logger {

  private Logger() {}

  private enum Holder {
    INSTANCE;

    private Logger instance;

    Holder() {
      this.instance = new Logger();
    }

    private Logger getSingleton() {
      return this.instance;
    }
  }

  public static Logger getInstance() {
    return Holder.INSTANCE.getSingleton();
  }

  public void info(String format, Object... args) {
    System.out.printf(format + "\n", args);
  }

  public void info(Object arg) {
    System.out.println(arg);
  }

  public <T> void format(List<T> list) {
    info(Strings.format(list));
  }

  public <T> void info(List<T> list) {
    info(Strings.format(list));
  }

  public void info(String[] elems) {
    info(Strings.format(elems));
  }

  public void info(long[] elems) {
    info(Strings.format(elems));
  }

  public void info(int[] elems) {
    info(Strings.format(elems));
  }

  public void printMatrix(int[][] elems) {
    for (int[] line : elems) {
      info(line);
    }
  }

  public void info(int[] elems, int except) {
    info(Strings.format(elems, elems.length, except));
  }

  public void info(char[] elems) {
    info(Strings.format(elems));
  }

  public void info(boolean[] elems) {
    info(Strings.format(elems));
  }

  public void info(ListNode node) {
    info(Strings.format(node));
  }

  public void info() {
    System.out.println();
  }
}
