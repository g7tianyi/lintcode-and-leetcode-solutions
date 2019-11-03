# 算法设计

## 贪心算法

| 问题 | 简单说明 | 推荐指数 |
|:--------|:------------|:---------------|
| [CanPlaceFlowers](https://www.lintcode.com/problem/can-place-flowers/description) | - | ⭐️️️⭐️️️⭐️️️️ |
| [LemonadeChange](https://www.lintcode.com/problem/lemonade-change/description) | - | ⭐️️️⭐️️️⭐️️️️ |
| [JumpGame](https://www.lintcode.com/problem/jump-game/description) | 很好的贪心问题，比前面两个都好，贪心算法要写出来一般都非常简单，问题在于找到贪心的结构，因为一般情况下，我们总是倾向于认为贪心策略不容易遇到，于是就滥用DP了 | ⭐️️️⭐️️️⭐️️️⭐️️️⭐️️️ |
| [Candy](https://www.lintcode.com/problem/candy/description) | 这道题的贪心结构比较明显 | ⭐️️️⭐️️️⭐️️️️ |
| [BattleshipsInBoard](https://www.lintcode.com/problem/battleships-in-a-board/description) | 其实也不能说是贪心问题，但的确值得试试，要求仅扫描一次，不能改变数组，O(1)的空间，😆 | ⭐️️️⭐️️️⭐️️️️ |

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
| [DigitalFlip](https://www.lintcode.com/problem/digital-flip/description) | 还有点意思 | ⭐️️️⭐️️️⭐️️️️ |
| [CoinsInLine](https://www.lintcode.com/problem/coins-in-a-line/description) | 斐波那契型的DP问题 | ⭐️️️⭐️️️⭐️️️️ |
| [CoinsInLine2](https://www.lintcode.com/problem/coins-in-a-line/description) | 比上一题难一点但也更有趣一点的DP问题 | ⭐️️️⭐️️️⭐️️️️ |
| [MaximumSubArray2](https://www.lintcode.com/problem/maximum-subarray-ii/description) | 最大子数组问题的升级版 | ⭐️️️⭐️️️⭐️️️️ |
| [PerfectSquare](https://www.lintcode.com/problem/perfect-squares/description) | 简单而值得熟练的一种DP问题 | ⭐️️️⭐️️️⭐️️️️ |

### 背包问题

> 以下内容来自对dd_engi的论文的重点摘录

- **0-1背包问题**. 有N件物品和一个容量为V的背包，第i件物品的费用是c[i]，价值是w[i]，求将哪些物品装入背包可使价值总和最大。0-1背包的特点是每种物品仅有一件，可以选择放或不放。用F[i][j]表示前i件物品恰放入一个容量为j的背包可以获得的最大价值，则其状态转移方程便是：`F[i][j] = MAX(F[i-1][j], F[i-1][j-c[i]]+w[i])`，其意思是如果不放第i件物品，那么问题就转化为`前i-1件物品放入容量为j的背包中`，价值为F[i-1][j]；如果放第i件物品，那么问题就转化为`前i-1件物品放入剩下的容量为j-c[i]的背包中`，此时能获得的最大价值就是`F[i-1][j-c[i]]再加上通过放入第i件物品获得的价值w[i]`。0-1背包问题存在一个很关键的空间复杂度优化，可以直接建下面[Backpack](https://github.com/g7tianyi/lintcode-and-leetcode-solutions/blob/master/src/main/java/com/g7tianyi/lintcode/dp/backpack/Backpack.java#L17)的代码。0-1背包问题的几个小变化是：
    - **恰好填满背包与不需要恰好填满背包时求最大价值**：
    - **求方案总数**：对于这类改变问法的问题，一般只需将状态转移方程中的max改成sum即可。例如若每件物品均是完全背包中的物品，转移方程即为`F[i][j] = SUM { F[i-1][j], F[i][j-c[i]]}`，初始条件`F[0][0] = 1`，这样做可行的原因在于状态转移方程已经考察了所有可能的背包组成方案
- **完全背包问题**. 有N种物品和一个容量为V的背包，**每种物品都有无限件可用**，第i种物品的费用是c[i]，价值是w[i]，求将哪些物品装入背包可使这些物品的费用总和不超过背包容量，且价值总和最大。从每种物品的角度考虑，与它相关的策略已并非取或不取两种，而是有取0件、取1件、取2件...等很多种，如果仍然按照解01背包时的思路，令F[i][j]表示前i种物品恰放入一个容量为j的背包的最大值，仍然可以按照每种物品不同的策略写出状态转移方程：`F[i][j] = MAX( F[i-1][j-k*c[i]]+k*w[i] | 0 <= k*c[i] <= j )`

| 问题 | 简单说明 | 推荐指数 |
|:--------|:------------|:---------------|
| [Backpack](https://www.lintcode.com/problem/backpack/description)，[Backpack2](https://www.lintcode.com/problem/backpack-ii/description) | 0-1背包问题，必须掌握，尤其是那个有趣的空间优化技巧 | ⭐️️️⭐️️️⭐️️️⭐️️️⭐️️️ |
| [Backpack5](https://www.lintcode.com/problem/backpack-v/description)，[CoinChange2](https://www.lintcode.com/problem/coin-change-2/description) | 还是0-1背包问题，相当于背包需要恰好被填满，也一样用到了和上面一样的空间优化技巧，不过因为需要完全填满整个背包，有些许变化，值得多试几次，掌握0-1背包问题的精髓 | ⭐️️️⭐️️️⭐️️️⭐️️️⭐️️️ |
| [Backpack9](https://www.lintcode.com/problem/backpack-ix/description) | 价值函数变了的01背包问题 | ⭐️️️⭐️️️⭐️️️️ |
| [CoinChange](https://www.lintcode.com/problem/coin-change/description) | 完全背包问题的最基本型，而且没有O(N^3)到O(N^2)的优化还过不了，所以这题是五星推荐了 | ⭐️️️⭐️️️⭐️️️⭐️️️⭐️️️ |
| [CuttingRod](https://www.lintcode.com/problem/cutting-a-rod/description) | 又一个完全背包问题 | ⭐️️️⭐️️️⭐️️️⭐️️️⭐️️️ |
| [CombinationSum4](https://www.lintcode.com/problem/combination-sum-iv/description) | [爬梯子问题](https://github.com/g7tianyi/lintcode-and-leetcode-solutions/blob/master/src/main/java/com/g7tianyi/lintcode/dp/ClimbingStairs.java#L17)，但与完全背包十分相似，完全背包问题与爬梯子问题的差距在于：前者取背包的次序不重要，后者次序重要，不同的次序意味着不同的解决方案，更进一步地：1) 完全背包问题：F[i,j]表示前i件物品构成价值j的方案总数，`F[i,j] = F(i-1, j-Vk) + F(i-1,j)`，循环第一层是物品；2) 爬梯子问题：F[i]表示爬到第i级的总方案数，`F[i] = Sum(F[i- Vk])`，循环第一层是梯级 (相当于背包大小) | ⭐️️️⭐️️️⭐️️️⭐️️️⭐️️️ |
| [OnesAndZeroes](https://www.lintcode.com/problem/ones-and-zeroes/description) | 二维费用的背包问题，一定要掌握 | ⭐️️️⭐️️️⭐️️️⭐️️️⭐️️️ |

### LIS与LCS问题

| 问题 | 简单说明 | 推荐指数 |
|:--------|:------------|:---------------|
| [LongestContinuousIncreasingSubSequence](https://www.lintcode.com/problem/longest-continuous-increasing-subsequence/description) | LIS问题：子序列必须连续，掌握好这两者的差别——其实这差别也很简单吧，哈哈 | ⭐️️️⭐️️️⭐️️️⭐️️️⭐️️️ |
| [LongestIncreasingSubsequence](https://www.lintcode.com/problem/longest-increasing-subsequence/description) | LIS问题：子序列可以不连续 | ⭐️️️⭐️️️⭐️️️⭐️️️⭐️️️ |
| [LongestCommonSubsequence](https://www.lintcode.com/problem/longest-common-subsequence/description) | LCA问题：最基本的LCA问题 | ⭐️️️⭐️️️⭐️️️⭐️️️⭐️️️ |

### 区间动态规划

| 问题 | 简单说明 | 推荐指数 |
|:--------|:------------|:---------------|
| [StoneGame](https://www.lintcode.com/problem/stone-game/description) | 区间动态规划的入门问题 | ⭐️️️⭐️️⭐️️️⭐️️️️ |
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
| [FindTheDuplicateNumber](https://www.lintcode.com/problem/find-the-duplicate-number/description) | 这道题赞死了，一开始我想歪了，以为自己找到了O(n)的办法，然后残酷打脸，转念一想，原来可以.... 总之是活学活用超级赞的题，完整的思路在[这里](https://github.com/g7tianyi/lintcode-and-leetcode-solutions/blob/master/src/main/java/com/g7tianyi/lintcode/array/FindTheDuplicateNumber.java) | ⭐️️️⭐️️️⭐️️️⭐️️️⭐️️️ |
| [FindPeekElement](https://www.lintcode.com/problem/find-peak-element/description) | 是一道需要仔细分析、寻找规律的好题，初始条件限制和递推规律决定了居然可以使用二分查找的思想解决问题，这道题可以看出，二分思维的应用，并不一定要求序列是有序的，六星推荐 | ⭐️️️⭐️️️⭐️️️⭐️️️⭐️️️ |
