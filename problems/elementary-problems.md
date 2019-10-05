# 基本问题

## 0 位运算问题

| 问题 | 简单说明 | 推荐指数 |
|:--------|:------------|:---------------|
| [NumberOf1Bits](https://www.lintcode.com/problem/number-of-1-bits/description) | 最简单的、二进制数1的情况 | ⭐️️️⭐️️️⭐️️️⭐️️️⭐️️️ |
| [Count1InBinary](https://www.lintcode.com/problem/count-1-in-binary/description) | 现在考虑下负数问题 | ⭐️️️⭐️️️⭐️️️⭐️️️⭐️️️ |
| [PrimeNumberOfSetBitsInBinaryRepresentation](https://www.lintcode.com/problem/prime-number-of-set-bits-in-binary-representation/description) | 将[Count1InBinary](https://www.lintcode.com/problem/count-1-in-binary/description)与[CountPrimes](https://www.lintcode.com/problem/count-primes/description)结合起来 | ⭐️️️⭐️️️⭐️️️⭐️️️⭐️️️ |
| [SingleNumber3](https://www.lintcode.com/problem/single-number-iii/description) | 真没想到，位运算应用得也太酷炫了 | ⭐️️️⭐️️️⭐️️️⭐️️️⭐️️️ |

## 1 二分查找

二分查找问题但不一定就能很快写对，在[这里](https://www.g7tianyi.com/blog/binary-search)有个简单的总结。 

| 问题 | 简单说明 | 推荐指数 |
|:--------|:------------|:---------------|
| [ClassicalBinarySearch](https://www.lintcode.com/problem/classical-binary-search/description) | 最基本的二分查找问题| ⭐️️️⭐️️⭐️️️⭐️️️️⭐️️️️ |
| [FirstPositionOfTarget](https://www.lintcode.com/problem/first-position-of-target/description) | 寻找指定元素最先出现的位置，最简单的二分查找的变体 | ⭐️️️⭐️️️️️⭐️️️️️⭐️️️️️ |
| [SearchInsertPosition](https://www.lintcode.com/problem/search-insert-position/description) | 寻找插入位置，也是最简单的二分查找的变体 | ⭐️️️⭐️️⭐️️️⭐️️️⭐️️️ |
| [GuessNumberHigherOrLower](https://www.lintcode.com/problem/guess-number-higher-or-lower/description) | 有点无聊 | ⭐️️️ |
| [Heaters](https://www.lintcode.com/problem/heaters/description) | 这个题，二分查找只是优化项，本质还是怎么样转化为算法问题 | ⭐️️️⭐️️⭐️️️ |
| [FindSmallestLetterGreaterThanTarget](https://www.lintcode.com/problem/find-smallest-letter-greater-than-target/description) | 本质上还是寻找插入位置 | ⭐️️️⭐️️⭐️️️⭐️️⭐️️️ |
| [MinimumIndexSumOfTwoLists](https://www.lintcode.com/problem/minimum-index-sum-of-two-lists/description) | - | ⭐️️️⭐️️⭐️️️ |
| [SearchForARange](https://www.lintcode.com/problem/search-for-a-range/description) | 查找指定元素的第一个和最后一个下标 | ⭐️️️⭐️️⭐️️️⭐️️⭐️️️ |
| [SearchInRotatedSortedArray](https://www.lintcode.com/problem/search-in-rotated-sorted-array/description) | 二分查找比较难的变种了 | ⭐️️️⭐️️⭐️️️⭐️️⭐️️️ |

## 2 数组问题

| 问题 | 简单说明 | 推荐指数 |
|:--------|:------------|:---------------|
| [MatrixZigZagTraversal](https://www.lintcode.com/problem/matrix-zigzag-traversal/description) | 蛮有意思的，主要是找到遍历的模式，否则if-else会写得想吐 | ⭐️️️⭐️️⭐️️️⭐️️️️⭐️️️️ |
| [MultiKeywordSort](https://www.lintcode.com/problem/multi-keyword-sort/description) | 相当于实现快速排序的泛型版 | ⭐️️️⭐️️️️ |
| [MaximumProductOfThreeNumbers](https://www.lintcode.com/problem/maximum-product-of-three-numbers/description) | 主要是将各种测试用例考虑全面，十分推荐的好题 | ⭐️️️⭐️️⭐️️️⭐️️️⭐️️️ |
| [ContainsDuplicate3](https://www.lintcode.com/problem/contains-duplicate-iii/description) | 可以通过这个题了解[BucketSort](http://personal.kent.edu/~rmuhamma/Algorithms/MyAlgorithms/Sorting/bucketSort.htm) | ⭐️️️⭐️️⭐️️️⭐️️️⭐️️️ |
| [SetMismatch](https://www.lintcode.com/problem/set-mismatch/description) | 可以通过这个题了解[RadixSort](https://en.wikipedia.org/wiki/Radix_sort) | ⭐️️️⭐️️⭐️️️⭐️️️ |
| [FindAllNumbersDisappearedInAnArray](https://www.lintcode.com/problem/find-all-numbers-disappeared-in-an-array/description) | 比较Tricky | ⭐️️️⭐️️⭐️️️⭐️️️⭐️️️ |
| [IntersectionOfTwoArrays](https://www.lintcode.com/problem/intersection-of-two-arrays/description) | - | ⭐️️️⭐️️⭐️️️⭐️️️ |
| [ShortestSubarrayWithSumAtLeastK](https://www.lintcode.com/problem/shortest-subarray-with-sum-at-least-k/description) | 数组的前缀和，不过这道题怎么是困难级呢，不至于吧 | ⭐️️️⭐️️️️ |
| [NumberOfSubArraysWithBoundedMaximum](https://www.lintcode.com/problem/number-of-subarrays-with-bounded-maximum/description) | 这个题主要是比较有趣，因为只需要知道有多少个方案，所以不需要真正的队列，但队列的也可以想见，AC需要对情况清晰的考虑 | ⭐️️️⭐️️⭐️️️⭐️️️ |

## 3 双指针技巧

| 问题 | 简单说明 | 推荐指数 |
|:--------|:------------|:---------------|
| [MinimumSizeSubarraySum](https://www.lintcode.com/problem/minimum-size-subarray-sum/description) | 双指针问题的入门佳作，也是大成之作了 | ⭐️️️⭐️️️⭐️️️⭐️️️⭐️️️ |
| [PartitionArray](https://www.lintcode.com/problem/partition-array/description) | - | ⭐️️️⭐️️️⭐️️️️ |
| [SortLettersByCase](https://www.lintcode.com/problem/sort-letters-by-case/description) | - | ⭐️️️⭐️️️⭐️️️️ |
