package com.g7tianyi.lintcode.util;

/** Created by g7tianyi on Aug 26, 2019 */
public final class StringUtils {

  private StringUtils() {}

  public static String prePad(String s, int length, char ch) {
    StringBuilder sb = new StringBuilder(s);
    sb.reverse();

    while (sb.length() < length) {
      sb.append(ch);
    }
    sb.reverse();
    return sb.toString();
  }

  public static String makeStringFrom(char ch, int length) {
    StringBuilder sb = new StringBuilder();
    while (sb.length() < length) {
      sb.append(ch);
    }
    return sb.toString();
  }
}
