# 算法设计

## 贪心算法

| 问题 | 简单说明 | 推荐指数 |
|:--------|:------------|:---------------|
| [CanPlaceFlowers](https://www.lintcode.com/problem/can-place-flowers/description) | - | ⭐️️️⭐️️️⭐️️️️ |
| [LemonadeChange](https://www.lintcode.com/problem/lemonade-change/description) | - | ⭐️️️⭐️️️⭐️️️️ |
| [JumpGame](https://www.lintcode.com/problem/jump-game/description) | 很好的贪心问题，比前面两个都好，贪心算法要写出来一般都非常简单，问题在于找到贪心的结构，因为一般情况下，我们总是倾向于认为贪心策略不容易遇到，于是就滥用DP了 | ⭐️️️⭐️️️⭐️️️⭐️️️⭐️️️ |

## 动态规划

动态规划博大精神啊，内容可以非常多：

- 背包问题
- 线性动态规划
- 区间动态规划
- 坐标动态规划
- 树型动态规划
- ...

### 基本动态规划问题

| 问题 | 简单说明 | 推荐指数 |
|:--------|:------------|:---------------|
| [PascalsTriangle](https://www.lintcode.com/problem/pascals-triangle/description), [Triangle](https://www.lintcode.com/problem/triangle/description) | 基本算是入门的前几道题，[Triangle](https://github.com/g7tianyi/lintcode-and-leetcode-solutions/blob/master/src/main/java/com/g7tianyi/lintcode/dp/Triangle.java)顺便使用了轮转数组 | ⭐️️️⭐️️️⭐️️️️ |
| [MinCostClimbingStairs](https://www.lintcode.com/problem/min-cost-climbing-stairs/description) | - | ⭐️️️⭐️️️ |
| [InterleavingString](https://www.lintcode.com/problem/interleaving-string/description) | 字符串的交叉编织，很不错的DP问题 | ⭐️️️⭐️️️⭐️️️⭐️️️⭐️️️ |
| [DicesSum](https://www.lintcode.com/problem/dices-sum/description) | 不错的DP问题 | ⭐️️️⭐️️️⭐️️️️ |
| [CopyBooks](https://www.lintcode.com/problem/copy-books/description) | 值得一试 | ⭐️️️⭐️️️ |
| [KSum](https://www.lintcode.com/problem/k-sum/description) | 三维DP问题 | ⭐️️️⭐️️️⭐️️️⭐️️️⭐️️️ |
| [EditDistance](https://www.lintcode.com/problem/edit-distance/description) | 编辑距离，CLRS上的经典习题，最终简化到了一维的问题 | ⭐️️️⭐️️️⭐️️️⭐️️️⭐️️️ |

### 背包问题

| 问题 | 简单说明 | 推荐指数 |
|:--------|:------------|:---------------|
| [Backpack](https://www.lintcode.com/problem/backpack/description)，[Backpack2](https://www.lintcode.com/problem/backpack-ii/description) | 0-1背包问题，必须掌握，尤其是那个有趣的空间优化技巧 | ⭐️️️⭐️️️⭐️️️⭐️️️⭐️️️ |
| [Backpack5](https://www.lintcode.com/problem/backpack-v/description)| 还是0-1背包问题，也一样用到了和上面一样的空间优化技巧，不过因为需要完全填满整个背包，有些许变化，值得多试几次，掌握背包问题的精髓 | ⭐️️️⭐️️️⭐️️️⭐️️️⭐️️️ |

### 区间动态规划

| 问题 | 简单说明 | 推荐指数 |
|:--------|:------------|:---------------|
| [BurstBalloons](https://www.lintcode.com/problem/burst-balloons/description) | 比较隐蔽的DP问题，乍一看只能想到DFS，TLE后才挖掘出DP... | ⭐️️️⭐️️️⭐️️️⭐️️️⭐️️️ |
| [BooleanParenthesization](https://www.lintcode.com/problem/boolean-parenthesization/description) | 有了上一道题和这一道题的练习——至少2-3次独立思考并编码，区间动态规划应该就掌握得比较好了；另外，这两题都可以得出一个启示：当我们发现一个问题可以用深搜或广搜来解决时，就考虑下是否有记忆化搜索的可能性，这样就可能导出DP了 | ⭐️️️⭐️️️⭐️️️⭐️️️⭐️️️ |
| [FrogJump](https://www.lintcode.com/problem/frog-jump/description) | 应该也可以归纳为区间动态规划，比较有趣，很少看到需要使用HashMap和HashSet来处理的DP问题，不难 | ⭐️️️⭐️️⭐️️️⭐️️️️ |

### 坐标动态规划

| 问题 | 简单说明 | 推荐指数 |
|:--------|:------------|:---------------|
| [BombEnemy](https://www.lintcode.com/problem/bomb-enemy/description) | 这题在lintcode上只是中等难度是不客观的，怎么都是困难了，而且写代码也比较繁琐 | ⭐️️️⭐️️️⭐️️️️ |

## 二分思维

> 二分思维比二分查找更高级一些

| 问题 | 简单说明 | 推荐指数 |
|:--------|:------------|:---------------|
| [FindTheDuplicateNumber](https://www.lintcode.com/problem/find-the-duplicate-number/description) | 这道题赞死了，一开始我想歪了，以为自己找到了O(n)的办法，然后残酷打脸，转念一想，原来可以.... 总之是活学活用超级赞的题，完整的思路在[这里](https://github.com/g7tianyi/lintcode-and-leetcode-solutions/blob/master/src/main/java/com/g7tianyi/lintcode/array/FindTheDuplicateNumber.java) | ⭐️️️⭐⭐️️️⭐️️️⭐️️️️️️️ |
