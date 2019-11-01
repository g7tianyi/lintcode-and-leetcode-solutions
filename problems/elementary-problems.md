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
| [TheSmallestDifference](https://www.lintcode.com/problem/the-smallest-difference/description) | 也可以尝试下二分查找之外的思路，很推荐~ | ⭐️️️⭐️️⭐️️️⭐️️⭐️️️ |
| [NumberOfA](https://www.lintcode.com/problem/number-of-a/description) | 挺不错的，如果数据强一点，可以至少是中级难度吧 | ⭐️️️⭐️️⭐️️️ |
| [LegalNumberStatistics2](https://www.lintcode.com/problem/legal-number-statistics-ii/description) | 同上，如果数据强一点，可以至少是中级难度吧 | ⭐️️️⭐️️⭐️️️ |

## 2 数组问题

| 问题 | 简单说明 | 推荐指数 |
|:--------|:------------|:---------------|
| [MultiKeywordSort](https://www.lintcode.com/problem/multi-keyword-sort/description) | 相当于实现快速排序的泛型版 | ⭐️️️⭐️️️️ |
| [MaximumProductOfThreeNumbers](https://www.lintcode.com/problem/maximum-product-of-three-numbers/description) | 主要是将各种测试用例考虑全面，十分推荐的好题 | ⭐️️️⭐️️⭐️️️⭐️️️⭐️️️ |
| [SetMismatch](https://www.lintcode.com/problem/set-mismatch/description) | 可以通过这个题了解[RadixSort](https://en.wikipedia.org/wiki/Radix_sort) | ⭐️️️⭐️️⭐️️️⭐️️️ |
| [FindAllNumbersDisappearedInAnArray](https://www.lintcode.com/problem/find-all-numbers-disappeared-in-an-array/description) | 比较Tricky | ⭐️️️⭐️️⭐️️️⭐️️️⭐️️️ |
| [IntersectionOfTwoArrays](https://www.lintcode.com/problem/intersection-of-two-arrays/description) | - | ⭐️️️⭐️️⭐️️️⭐️️️ |
| [ShortestSubarrayWithSumAtLeastK](https://www.lintcode.com/problem/shortest-subarray-with-sum-at-least-k/description) | 数组的前缀和，不过这道题怎么是困难级呢，不至于吧 | ⭐️️️⭐️️️️ |
| [NumberOfSubArraysWithBoundedMaximum](https://www.lintcode.com/problem/number-of-subarrays-with-bounded-maximum/description) | 这个题主要是比较有趣，因为只需要知道有多少个方案，所以不需要真正的队列，但队列的也可以想见，AC需要对情况清晰的考虑 | ⭐️️️⭐️️⭐️️️⭐️️️ |
| [ShortestWordDistance](https://www.lintcode.com/problem/shortest-word-distance/description) | 虽然是简单级... 当得到了两个word的下标列表后，要loop得到最小值，还蛮值得重试一次的 | ⭐️️️⭐️️⭐️️️⭐️️️ |
| [ProductOfArrayExceptSelf](https://www.lintcode.com/problem/product-of-array-except-self/description) | 主要是挑战一下常数空间复杂度~ | ⭐️️️⭐️️⭐️️️⭐️️️ |

### 2.0 矩阵问题

| 问题 | 简单说明 | 推荐指数 |
|:--------|:------------|:---------------|
| [MatrixZigZagTraversal](https://www.lintcode.com/problem/matrix-zigzag-traversal/description) | 蛮有意思的，主要是找到遍历的模式，否则if-else会写得想吐 | ⭐️️️⭐️️⭐️️️⭐️️️️⭐️️️️ |
| [SearchA2DMatrix2](https://www.lintcode.com/problem/search-a-2d-matrix-ii/description) | 好题啊，并不仅仅只是二分问题 | ⭐️️️⭐️️⭐️️️⭐️️️ |
| [RotateImage](https://www.lintcode.com/problem/rotate-image/description) | 就算法而言其实比较无聊，这样的题做多了会比较善于发现规律，而且coding能力有提示，所以绝对值得多做几次 | ⭐️️️⭐️️️⭐️️️️ |
| [GameOfLife](https://www.lintcode.com/problem/game-of-life/description) | 其实挺无聊的，不知道很多大公司为何就喜欢考，另外要求原地修改还是比较有意思的 | ⭐️️️⭐️️️⭐️️️️ |

### 2.1 桶排序思想

| 问题 | 简单说明 | 推荐指数 |
|:--------|:------------|:---------------|
| [ContainsDuplicate3](https://www.lintcode.com/problem/contains-duplicate-iii/description), [MissingNumber](https://www.lintcode.com/problem/missing-number/description) | 可以通过这个题了解[BucketSort](http://personal.kent.edu/~rmuhamma/Algorithms/MyAlgorithms/Sorting/bucketSort.htm)，MissingNumber说是桶排序弱了点，但有桶排序的思维痕迹 | ⭐️️️⭐️️⭐️️️⭐️️️⭐️️️ |
| [FirstMissingPositive](https://www.lintcode.com/problem/first-missing-positive/description) | 也算是桶排序的思路，标记落入桶中的逻辑其实比较Tricky，也许嵌入式设备中可以这样写程序，应用层代码因为快速变化的可能性，对清晰性的要求更高，一定还是用更清晰简洁的方式来处理吧 | ⭐️️️⭐️️️️ |

## 3 双指针技巧

处理数组问题常用技巧，简单而强大，**通常来说，双指针处理的都是连续区间的问题**：

- 一个从0开始后移动，一个从尾部开始往前移动，**表现为夹逼**，如下面的[ContainerWithMostWater](https://www.lintcode.com/problem/container-with-most-water/description)
- 确定前指针，后指针从前指针后面一个位置开始往前走，可以经常看得见类似于`i = 0; j = i + 1;`的代码，表现为**子区间扫描**，如下面的[MinimumSizeSubarraySum](https://www.lintcode.com/problem/minimum-size-subarray-sum/description)，[ValidTriangleNumber](https://www.lintcode.com/problem/valid-triangle-number/description)和[MinimumWindowSubstring](https://www.lintcode.com/problem/minimum-window-substring/description)

| 问题 | 简单说明 | 推荐指数 |
|:--------|:------------|:---------------|
| [MinimumSizeSubarraySum](https://www.lintcode.com/problem/minimum-size-subarray-sum/description) | 双指针问题的入门佳作，也是大成之作了 | ⭐️️️⭐️️️⭐️️️⭐️️️⭐️️️ |
| [Closest3Sum](https://www.lintcode.com/problem/3sum-closest/description) | 也是超级好的双指针求解的问题，极度推荐 | ⭐️️️⭐️️️⭐️️️⭐️️️⭐️️️ |
| [PartitionArray](https://www.lintcode.com/problem/partition-array/description) | - | ⭐️️️⭐️️️⭐️️️️ |
| [SortLettersByCase](https://www.lintcode.com/problem/sort-letters-by-case/description) | - | ⭐️️️⭐️️️⭐️️️️ |
| [ContainerWithMostWater](https://www.lintcode.com/problem/container-with-most-water/description) | 双指针夹逼还可以有这么多内容，哈哈 | ⭐️️️⭐️️️⭐️️️️ |
| [ValidTriangleNumber](https://www.lintcode.com/problem/valid-triangle-number/description) | 双指针可以优化到O(N^2)，二分查找可以优化到O(N^2*logN)，暴力解法是O(N^3)，很优秀啦~ | ⭐️️️⭐️️️⭐️️️️ |
| [MinimumWindowSubstring](https://www.lintcode.com/problem/minimum-window-substring/description) | 子区间扫描性的双指针问题 | ⭐️️️⭐️️️⭐️️️️ |

## 4 排序问题

| 问题 | 简单说明 | 推荐指数 |
|:--------|:------------|:---------------|
| ReorderArrayToConstructTheMinimumNumber | 快速排序 | ⭐️️️⭐️️️⭐️️️️ |
