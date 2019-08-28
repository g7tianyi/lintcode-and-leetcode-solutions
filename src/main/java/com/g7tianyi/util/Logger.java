package com.g7tianyi.util;

import com.g7tianyi.common.Strings;

import java.util.List;

/** Created by g7tianyi on Aug 24, 2019 */
public final class Logger {

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

  public void info(int[] elems) {
    info(Strings.format(elems));
  }

  public void info(char[] elems) {
    info(Strings.format(elems));
  }

  public void info() {
    System.out.println();
  }
}
