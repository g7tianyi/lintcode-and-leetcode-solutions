# 高级数据结构

## 堆

| 问题 | 简单说明 | 推荐指数 |
|:--------|:------------|:---------------|
| [UglyNumber2](https://www.lintcode.com/problem/ugly-number-ii/description) | 一开始没想到可以用堆余数TLE了，然后因为忘了需要去重，所以WA了，😶😶😶 |  ⭐️️️⭐️️⭐️️️⭐️⭐️ |
| [MergeKSortedLists](https://www.lintcode.com/problem/merge-k-sorted-lists/description) | 堆与列表联合使用的好题 |  ⭐️️️⭐️️⭐️️️⭐️⭐️ |
| [FindMedianFromDataStream](https://www.lintcode.com/problem/find-median-from-data-stream/description) | 开始用的二分查找，写到差不多要结束想起以前在PKU做过一道类似的题，用两个堆来模拟，超赞呢 |  ⭐️️️⭐️️⭐️️️⭐️⭐️ |
| [Heapify](https://www.lintcode.com/problem/heapify/description) | 建堆，基础知识，值得一试 |  ⭐️️️⭐️️⭐️️️⭐️⭐️ |

## 单调栈

| 问题 | 简单说明 | 推荐指数 |
|:--------|:------------|:---------------|
| [LargestRectangleInHistogram](https://www.lintcode.com/problem/largest-rectangle-in-histogram/description) | **一般在找左边和右边第一个比该元素大或小的问题的时候会用到单调栈**，本题是单调栈问题最好的开始，十分推荐 | ⭐️️️⭐️️⭐️️⭐️️️⭐️️️️ |
| [SumOfSubarrayMinimums](https://www.lintcode.com/problem/sum-of-subarray-minimums/description) | 十分推荐，就理解单调栈这一概念而言，可能比上一题还好呢~ | ⭐️️️⭐️️⭐️️⭐️️️⭐️️️️ |

## 线段树

| 问题 | 简单说明 | 推荐指数 |
|:--------|:------------|:---------------|
| [SegmentTreeBuild](https://www.lintcode.com/problem/segment-tree-build/description), [SegmentTreeBuild2](https://www.lintcode.com/problem/segment-tree-build-ii/description) | 只是让你构造一棵线段树，不涉及查询等问题，所以基本算是入门题，可以尝试一下递归与非递归的模式 | ⭐️️️⭐️️️⭐️️️️ |
| [SegmentTreeQuery](https://www.lintcode.com/problem/segment-tree-query/description), [SegmentTreeQuery2](https://www.lintcode.com/problem/segment-tree-query-ii/description), 以及比较实际的[IntervalMinimumNumber](https://www.lintcode.com/problem/interval-minimum-number/description) | 接上题，开始查询了 | ⭐️️️⭐️️️⭐️️️️ |
| [SegmentTreeModify](https://www.lintcode.com/problem/segment-tree-modify/description) | 接上题，单点修改 | ⭐️️️⭐️️️⭐️️️️ |
| [RangeAddition](https://www.lintcode.com/problem/range-addition/description) | 这道题的标准解法就是线段树，线段树的经典问题也就是这道题了，不过lintcode上的数据比较弱，按理说这样的问题，直接使用区间染色的套路是不应该AC的，Anyway，灰常推荐 |  ⭐️️️⭐️️⭐️️️⭐️⭐️ |
| [IntervalSum](https://www.lintcode.com/problem/interval-sum/description) | 同上 |  ⭐️️️⭐️️⭐️️️⭐️⭐️ |
