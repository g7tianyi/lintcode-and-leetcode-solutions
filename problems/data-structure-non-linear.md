# Data Structure - non-linear

## 基本二叉树问题

| 问题 | 简单说明 | 推荐指数 |
|:--------|:------------|:---------------|
| [CloneBinaryTree](https://www.lintcode.com/problem/clone-binary-tree/description) | 主要是尝试下递归与非递归的方案 | ⭐️️️⭐️️⭐️️️⭐️️️️ |
| [SameTree](https://www.lintcode.com/problem/same-tree/description) | 主要是尝试下递归与非递归的方案 | ⭐️️️⭐️️⭐️️️⭐️️️️ |
| [BinaryTreeTilt](https://www.lintcode.com/problem/binary-tree-tilt/description) | 也是遍历问题，加点花 | ⭐️️️⭐️️⭐️️⭐️️️⭐️️️️ |
| [MergeTwoBinaryTrees](https://www.lintcode.com/problem/merge-two-binary-trees/description) | - | ⭐️️️⭐️️ |
| [DiameterOfBinaryTree](https://www.lintcode.com/problem/diameter-of-binary-tree/description) | 还比较有趣 | ⭐️️️⭐️️ |
| [SymmetricTree](https://www.lintcode.com/problem/symmetric-tree/description) | 主要是尝试下递归与非递归的方案 | ⭐️️️⭐️️⭐️️⭐️️️⭐️️️ |
| [SumOfLeftLeaves](https://www.lintcode.com/problem/sum-of-left-leaves/description) | 还比较有趣 | ⭐️️️⭐️️⭐️️️ |
| [IncreasingOrderSearchTree](https://www.lintcode.com/problem/increasing-order-search-tree/description) | 在原始的树中进行操作，不要生成一棵新的树 | ⭐️️️⭐️️⭐️️️⭐️ |
| [AverageOfLevelsInBinaryTree](https://www.lintcode.com/problem/average-of-levels-in-binary-tree/description) | 队列，层序遍历问题 | ⭐️️️⭐️️⭐️️️⭐️ |
| [BinaryTreePruning](https://www.lintcode.com/problem/binary-tree-pruning/description) | 好题 | ⭐️️️⭐️️⭐️️️⭐️ |
| [MinimumAbsoluteDifferenceInBST](https://www.lintcode.com/problem/minimum-absolute-difference-in-bst/description) | DITTO | ⭐️️️⭐️️⭐️️️⭐️ |
| [InvertBinaryTree](https://www.lintcode.com/problem/invert-binary-tree/description) | 主要是尝试下递归与非递归的方案 | ⭐️️️⭐️️⭐️️️⭐️⭐️ |
| [SubTreeOfAnotherTree](https://www.lintcode.com/problem/subtree-of-another-tree/description) | 乍一看完全无解 | ⭐️️️⭐️️⭐️️️⭐️⭐️ |
| [BinaryTreeLongestConsecutiveSequence](https://www.lintcode.com/problem/binary-tree-longest-consecutive-sequence/description) | - | ⭐️️️⭐️️⭐️️️⭐️️ |
| [LongestUnivaluePath](https://www.lintcode.com/problem/longest-univalue-path/description) | 还比较难的趣题 | ⭐️️️⭐️️⭐️️️⭐️️ |
| [ConstructStringFromBinaryTree](https://www.lintcode.com/problem/construct-string-from-binary-tree/description) | 比较有意思 | ⭐️️️⭐️️ |
| [SerializeAndDeserializeBinaryTree](https://www.lintcode.com/problem/serialize-and-deserialize-binary-tree/description) | 二叉树的序列化，有了这道题的基础，可以自己写二叉树问题的测试用例了 | ⭐️️️⭐️️⭐️️️⭐️⭐️️ |
| [LowestCommonAncestorOfABinaryTree](https://www.lintcode.com/problem/lowest-common-ancestor-of-a-binary-tree/description) | LCA问题，必须掌握，递归时似乎可以有记忆化搜索的优化点，优化判断一个节点是否属于自己的左右子树 | ⭐️️️⭐️️⭐️️️⭐️⭐️️ |

## 二叉搜索树

| 问题 | 简单说明 | 推荐指数 |
|:--------|:------------|:---------------|
| [MinimumDifferenceBetweenBSTNodes](https://www.lintcode.com/problem/minimum-difference-between-bst-nodes/description) | 本质上是二叉树的中序遍历 | ⭐️️️⭐️️⭐️️⭐️️️⭐️️️️ |
| [LowestCommonAncestorOfBinarySearchTree](https://www.lintcode.com/problem/lowest-common-ancestor-of-a-binary-search-tree/description) | LCA问题，非常经典且重要 | ⭐️️️⭐️️⭐️️️⭐️⭐️ |
| [InsertNodeInBinarySearchTree](https://www.lintcode.com/problem/insert-node-in-a-binary-search-tree/description) | 向二叉搜索树中插入节点，这个应该是BST最重要的操作 | ⭐️️️⭐️️⭐️️️⭐️⭐️ |
| [ConvertSortedArrayToBinarySearchTree](https://www.lintcode.com/problem/convert-sorted-array-to-binary-search-tree-with-minimal-height/description) | 将排序数组转化为二叉搜索树 | ⭐️️️⭐️️⭐️️️⭐️⭐️ |
| [ConvertBSTToGreaterTree](https://www.lintcode.com/problem/convert-bst-to-greater-tree/description) | 优先遍历右子树还还说，怎么处理返回值还比较Tricky | ⭐️️️⭐️️⭐️️️⭐️⭐️ |
| [SearchRangeInBinarySearchTree](https://www.lintcode.com/problem/search-range-in-binary-search-tree/description) | - | ⭐️️️⭐️️⭐️️️⭐️⭐️ |

## 哈希

| 问题 | 简单说明 | 推荐指数 |
|:--------|:------------|:---------------|
| [LFUCache](https://www.lintcode.com/problem/lfu-cache/description) | 如果理解了LFU的概念，其实这题更像是个设计的问题，不过我的代码也没有好好优化.. | ⭐️️️⭐️️⭐️️⭐️️️⭐️️️️ |
| [LRUCache](https://www.lintcode.com/problem/lru-cache/description) | 同上 | ⭐️️️⭐️️⭐️️️ |
| [FirstUniqueNumberInDataStream](https://www.lintcode.com/problem/first-unique-number-in-data-stream/description) | 归类到队列也行 | ⭐️️️⭐️️ |
| [InsertDeleteGetRandomO1](https://www.lintcode.com/problem/insert-delete-getrandom-o1/description) | 还蛮有趣的问题，插入好说，主要是如何处理好删除与getRandom的关系 | ⭐️️️⭐️️⭐️️⭐️️️⭐️️️️ |
