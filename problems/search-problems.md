# 搜索问题

## 深度优先搜索

| 问题 | 简单说明 | 推荐指数 |
|:--------|:------------|:---------------|
| [CanPlaceFlowers](https://www.lintcode.com/problem/can-place-flowers/description) | 深搜好题 | ⭐️️️⭐️️⭐️️️⭐️️️️ |
| [SumOfAllSubsets](https://www.lintcode.com/problem/sum-of-all-subsets/description) | 值得重试的深搜好体 | ⭐️️️⭐️️⭐️️️⭐️️️️ |
| [Permutations2](https://www.lintcode.com/problem/permutations-ii/description) | 主要问题是如何避免重复 | ⭐️️️⭐️️⭐️️️⭐️️️️ |
| [ExpressionAddOperators](https://www.lintcode.com/problem/expression-add-operators/description) | 原本很简单的DFS的题，新鲜点的内容大概就是用个栈来模拟一下表达式求值，结果一直MLE... 所以不要真用栈来处理表达式求值，维护好prev和sum变量就可以了，另外用StringBuilder也要注意内存问题，主要是它的预分配和字符数组拷贝很捉急；考虑到MLE一直逼着我不停地优化代码，我还是很推荐[这题](https://github.com/g7tianyi/lintcode-and-leetcode-solutions/blob/master/src/main/java/com/g7tianyi/lintcode/search/ExpressionAddOperators.java)的 | ⭐️️️⭐️️⭐️️️⭐️️️️ |
| [TwentyFourGame](https://www.lintcode.com/problem/24-game/description) | 比较Tricky的好题，关键可能还不在深搜和剪枝本身，毕竟就四个数字四种运算，但是怎么处理好除法问题比较有趣，所以这题其实还考察了OO呢 | ⭐️️️⭐️️⭐️️️⭐️️️️ |

## 广度优先搜索

| 问题 | 简单说明 | 推荐指数 |
|:--------|:------------|:---------------|
| [WordLadder](https://www.lintcode.com/problem/word-ladder/description) | 很好的广搜问题，这题在一开始建立好搜索的图比较重要；另外可以尝试双向广搜，应该会很快 | ⭐️️️⭐️️⭐️️️⭐️️️️️️⭐️️️️ |
| [WordLadder2](https://www.lintcode.com/problem/word-ladder-ii/description) | 在上一题的基础上麻烦点，因为存在大量复制，所以需要剪枝了，比如不需要重复进去广搜队列 | ⭐️️️⭐️️⭐️️️⭐️️️️️️⭐️️️️ |

## 快速减枝 + 解题空间构造

| 问题 | 简单说明 | 推荐指数 |
|:--------|:------------|:---------------|
| [LargestPalindromeProduct](https://www.lintcode.com/problem/largest-palindrome-product/description) | Selectively constructing the answer and use the rules to verify it, rather than looping each and evert possible solution | ⭐️️️⭐️️⭐️️️⭐️️️️️️⭐️️️️ |
