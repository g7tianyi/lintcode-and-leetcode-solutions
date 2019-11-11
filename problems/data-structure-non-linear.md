# 非线性数据结构

## 基本二叉树问题

| 问题 | 简单说明 | 推荐指数 |
|:--------|:------------|:---------------|
| [CloneBinaryTree](https://www.lintcode.com/problem/clone-binary-tree/description) | 主要是尝试下递归与非递归的方案 | ⭐️️️⭐️️⭐️️️⭐️️️️ |
| [SameTree](https://www.lintcode.com/problem/same-tree/description) | 主要是尝试下递归与非递归的方案 | ⭐️️️⭐️️⭐️️️⭐️️️️ |
| [MergeTwoBinaryTrees](https://www.lintcode.com/problem/merge-two-binary-trees/description) | - | ⭐️️️⭐️️ |
| [DiameterOfBinaryTree](https://www.lintcode.com/problem/diameter-of-binary-tree/description) | 还比较有趣 | ⭐️️️⭐️️ |
| [SymmetricTree](https://www.lintcode.com/problem/symmetric-tree/description) | 主要是尝试下递归与非递归的方案 | ⭐️️️⭐️️⭐️️⭐️️️⭐️️️ |
| [SumOfLeftLeaves](https://www.lintcode.com/problem/sum-of-left-leaves/description) | 还比较有趣 | ⭐️️️⭐️️⭐️️️ |
| [IncreasingOrderSearchTree](https://www.lintcode.com/problem/increasing-order-search-tree/description) | 在原始的树中进行操作，不要生成一棵新的树 | ⭐️️️⭐️️⭐️️️⭐️ |
| [BinaryTreePruning](https://www.lintcode.com/problem/binary-tree-pruning/description) | 好题 | ⭐️️️⭐️️⭐️️️⭐️ |
| [MinimumAbsoluteDifferenceInBST](https://www.lintcode.com/problem/minimum-absolute-difference-in-bst/description) | DITTO | ⭐️️️⭐️️⭐️️️⭐️ |
| [InvertBinaryTree](https://www.lintcode.com/problem/invert-binary-tree/description) | 主要是尝试下递归与非递归的方案 | ⭐️️️⭐️️⭐️️️⭐️⭐️ |
| [SubTreeOfAnotherTree](https://www.lintcode.com/problem/subtree-of-another-tree/description) | 乍一看完全无解 | ⭐️️️⭐️️⭐️️️⭐️⭐️ |
| [BinaryTreeLongestConsecutiveSequence](https://www.lintcode.com/problem/binary-tree-longest-consecutive-sequence/description) | - | ⭐️️️⭐️️⭐️️️⭐️️ |
| [LongestUnivaluePath](https://www.lintcode.com/problem/longest-univalue-path/description) | 还比较难的趣题 | ⭐️️️⭐️️⭐️️️⭐️️ |
| [ConstructStringFromBinaryTree](https://www.lintcode.com/problem/construct-string-from-binary-tree/description) | 比较有意思 | ⭐️️️⭐️️ |
| [SerializeAndDeserializeBinaryTree](https://www.lintcode.com/problem/serialize-and-deserialize-binary-tree/description) | 二叉树的序列化，有了这道题的基础，可以自己写二叉树问题的测试用例了 | ⭐️️️⭐️️⭐️️️⭐️⭐️️ |
| [LowestCommonAncestorOfABinaryTree](https://www.lintcode.com/problem/lowest-common-ancestor-of-a-binary-tree/description) | LCA问题，必须掌握，递归时似乎可以有记忆化搜索的优化点，优化判断一个节点是否属于自己的左右子树 | ⭐️️️⭐️️⭐️️️⭐️⭐️️ |

## 树的遍历问题

树的遍历问题主要有两类问题：

- 前序/中序/后序/层序遍历整个树
- 根据前序/中序/后序/层序遍历的结果，构造树

遍历一棵树的基本规律如下：

| 遍历方式 | 递归 | 非递归 |
|:--------|:------------|:---------------|
| 前序遍历 | 先输出自己，再递归进入左子树，最后递归进入右子树 | 使用栈模拟，结果用ArrayList保存，然后append自身到List尾部，最后**子节点逆序入栈** | 
| 中序遍历 | 先递归进入左子树，再输出自己，最后递归进入右子树 | 使用栈模拟，结果用ArrayList保存，引入一个TreeNodeWrapper，引入需要将自己也放入栈中， 可以参考[这里](https://github.com/g7tianyi/lintcode-and-leetcode-solutions/blob/master/src/main/java/com/g7tianyi/lintcode/tree/traversal/BinaryTreeInorderTraversal.java#L40) | 
| 后序遍历 | 先递归进入左子树，再递归进入右子树，最后输出自己 | 使用栈模拟，结果用LinkedList保存，然后prepend自身到List头部，最后**子节点顺序入栈** | 
| 层序遍历 | - | 都是使用队列来模拟了 | 

| 问题 | 简单说明 | 推荐指数 |
|:--------|:------------|:---------------|
| [NaryTreePreorderTraversal](https://www.lintcode.com/problem/n-ary-tree-preorder-traversal/description) | N叉树的前序遍历，尝试下递归与非递归写法 | ⭐️️️⭐️️⭐️️⭐️️️⭐️️️️ |
| [NaryTreePostorderTraversal](https://www.lintcode.com/problem/n-ary-tree-postorder-traversal/description) | N叉树的后序遍历，尝试下递归与非递归写法 | ⭐️️️⭐️️⭐️️⭐️️️⭐️️️️ |
| [NaryTreeLevelOrderTraversal](https://www.lintcode.com/problem/n-ary-tree-level-order-traversal/description) | 层序遍历 | ⭐️️️⭐️️ |
| [AverageOfLevelsInBinaryTree](https://www.lintcode.com/problem/average-of-levels-in-binary-tree/description) | 队列，层序遍历问题 | ⭐️️️⭐️️⭐️️️⭐️ |
| [BinaryTreeTilt](https://www.lintcode.com/problem/binary-tree-tilt/description) | 也是遍历问题，加点花 | ⭐️️️⭐️️⭐️️⭐️️️⭐️️️️ |
| [BinaryTreeVerticalOrderTraversal](https://www.lintcode.com/problem/binary-tree-vertical-order-traversal/description) | 这么多树有关的问题，这是让我觉得最有新鲜感的一道题~ | ⭐️️️⭐️️⭐️️⭐️️️⭐️️️️ |
| [ConstructBinaryTreeFromInorderAndPostorderTraversal](https://www.lintcode.com/problem/construct-binary-tree-from-inorder-and-postorder-traversal/description) | 借助中序遍历与后序遍历还原二叉树，必须掌握的基础知识 | ⭐️️️⭐️️⭐️️⭐️️️⭐️️️️ |
| [ConstructBinaryTreeFromPreorderAndInorderTraversal](https://www.lintcode.com/problem/construct-binary-tree-from-preorder-and-inorder-traversal/description) | 借助中序遍历与前序遍历还原二叉树，必须掌握的基础知识 | ⭐️️️⭐️️⭐️️⭐️️️⭐️️️️ |
| [ConstructBinaryTreeFromPreorderAndPostorderTraversal](https://www.lintcode.com/problem/construct-binary-tree-from-preorder-and-postorder-traversal/description) | 借助前序遍历与后序遍历还原二叉树，必须掌握的基础知识 | ⭐️️️⭐️️⭐️️⭐️️️⭐️️️️ |
| [ConstructBinaryTreeFromString](https://www.lintcode.com/problem/construct-binary-tree-from-string/description) | 值得重试 | ⭐️️️⭐️️ |
| [BinaryTreeMaximumPathSum](https://www.lintcode.com/problem/binary-tree-maximum-path-sum/description) | 高频好题 | ⭐️️️⭐️️ |

## 二叉搜索树

| 问题 | 简单说明 | 推荐指数 |
|:--------|:------------|:---------------|
| [MinimumDifferenceBetweenBSTNodes](https://www.lintcode.com/problem/minimum-difference-between-bst-nodes/description) | 本质上是二叉树的中序遍历 | ⭐️️️⭐️️⭐️️⭐️️️⭐️️️️ |
| [LowestCommonAncestorOfBinarySearchTree](https://www.lintcode.com/problem/lowest-common-ancestor-of-a-binary-search-tree/description) | LCA问题，非常经典且重要 | ⭐️️️⭐️️⭐️️️⭐️⭐️ |
| [InsertNodeInBinarySearchTree](https://www.lintcode.com/problem/insert-node-in-a-binary-search-tree/description) | 向二叉搜索树中插入节点，这个应该是BST最重要的操作 | ⭐️️️⭐️️⭐️️️⭐️⭐️ |
| [ConvertSortedArrayToBinarySearchTree](https://www.lintcode.com/problem/convert-sorted-array-to-binary-search-tree-with-minimal-height/description) | 将排序数组转化为二叉搜索树 | ⭐️️️⭐️️⭐️️️⭐️⭐️ |
| [ConvertBSTToGreaterTree](https://www.lintcode.com/problem/convert-bst-to-greater-tree/description) | 优先遍历右子树还还说，怎么处理返回值还比较Tricky | ⭐️️️⭐️️⭐️️️⭐️⭐️ |
| [SearchRangeInBinarySearchTree](https://www.lintcode.com/problem/search-range-in-binary-search-tree/description) | - | ⭐️️️⭐️️⭐️️️⭐️⭐️ |
| [ValidateBinarySearchTree](https://www.lintcode.com/problem/validate-binary-search-tree/description) | - | ⭐️️️⭐️️⭐️️️⭐️⭐️ |

## 哈希

| 问题 | 简单说明 | 推荐指数 |
|:--------|:------------|:---------------|
| [LFUCache](https://www.lintcode.com/problem/lfu-cache/description) | 如果理解了LFU的概念，其实这题更像是个设计的问题，不过我的代码也没有好好优化.. | ⭐️️️⭐️️⭐️️⭐️️️⭐️️️️ |
| [LRUCache](https://www.lintcode.com/problem/lru-cache/description) | 同上 | ⭐️️️⭐️️⭐️️️ |
| [FirstUniqueNumberInDataStream](https://www.lintcode.com/problem/first-unique-number-in-data-stream/description) | 归类到队列也行 | ⭐️️️⭐️️ |
| [InsertDeleteGetRandomO1](https://www.lintcode.com/problem/insert-delete-getrandom-o1/description) | 还蛮有趣的问题，插入好说，主要是如何处理好删除与getRandom的关系 | ⭐️️️⭐️️⭐️️⭐️️️⭐️️️️ |
