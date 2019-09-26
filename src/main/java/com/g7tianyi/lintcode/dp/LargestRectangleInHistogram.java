package com.g7tianyi.lintcode.dp;

import com.g7tianyi.common.Arrays;
import com.g7tianyi.util.Logger;
import org.junit.Test;

import java.util.Stack;

/**
 * Created by g7tianyi on Sep 26, 2019
 *
 * @link https://www.lintcode.com/problem/largest-rectangle-in-histogram/description
 */
public class LargestRectangleInHistogram {

  private static final Logger log = Logger.getInstance();

  public class Solution {

    public int largestRectangleArea(int[] values) {

      if (values == null || values.length == 0) {
        return 0;
      }

      // 计算以当前值为高的矩形面积时，需要找到它的左右边界
      // 建立栈，存储当前元素在数组中的位置，将数组元素依次推入栈中
      // 如果它比栈顶元素小，则当前栈顶元素P的右边界出现了 => 我们算的就是当前栈顶元素P的最大矩形面积
      // 对于左边界，将栈顶元素P出栈，直到新的栈顶元素P'对应的高度比P对应的高度还小，这个就是左边界了
      //
      // 总结：一般在找左边和右边第一个比该元素大或小的问题的时候会用到单调栈
      Stack<Integer> stack = new Stack<>();
      stack.push(0);
      int result = 0;
      for (int i = 0; i <= values.length; ++i) {
        int myHeight = (i == values.length) ? -1 : values[i];
        while (!stack.isEmpty() && myHeight <= values[stack.peek()]) {
          int h = values[stack.pop()];
          int w = (stack.isEmpty()) ? i : i - stack.peek() - 1;
          result = Math.max(result, h * w);
        }
        // 前面比第i个高的都清空了才能把第i个放进去；
        stack.push(i);
      }
      return result;
    }

    // O(n^2), TLE，也在设想中
    public int TLE_largestRectangleArea(int[] heights) {
      if (heights == null || heights.length == 0) {
        return 0;
      }

      int result = 0, min;
      for (int i = 0; i < heights.length; ++i) {
        min = heights[i];
        result = Math.max(result, min);
        for (int j = i + 1; j < heights.length; ++j) {
          min = Math.min(min, heights[j]);
          result = Math.max(result, (j - i + 1) * min);
        }
      }
      return result;
    }
  }

  private final Solution s = new Solution();

  @Test
  public void test() {
    log.info(s.largestRectangleArea(Arrays.from(2, 1, 5, 6, 2, 3)));
    log.info(s.largestRectangleArea(Arrays.from(1, 2, 3, 4, 5)));
    log.info(s.largestRectangleArea(Arrays.from(1, 1, 1, 1, 1, 1)));
    log.info(s.largestRectangleArea(Arrays.from(1, 1)));
  }
}
