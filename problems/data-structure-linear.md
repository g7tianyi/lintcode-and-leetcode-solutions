# 线性数据结构

## 栈

| 问题 | 简单说明 | 推荐指数 |
|:--------|:------------|:---------------|
| [BaseballGame](https://www.lintcode.com/problem/baseball-game/description) | 不错的栈的问题 | ⭐️️️⭐️️⭐️️️⭐️️️️ |
| [NextGreaterElement1](https://www.lintcode.com/problem/next-greater-element-i/description) | 同上 | ⭐️️️⭐️️⭐️️⭐️️️⭐️️️️ |
| [BackspaceStringCompare](https://www.lintcode.com/problem/backspace-string-compare/description) | 不错的栈的问题 | ⭐️️️⭐️️⭐️️⭐️️️⭐️️️️ |
| [MaxStack](https://www.lintcode.com/problem/max-stack/description) | 虽然是栈的好题，但不明白这个题为什么是困难级 | ⭐️️️⭐️️⭐️️️ |
| [SimplifyPath](https://www.lintcode.com/problem/simplify-path/description) | 简化Unix路径 | ⭐️️️⭐️️⭐️️️ |

## 队列

| 问题 | 简单说明 | 推荐指数 |
|:--------|:------------|:---------------|
| [KillProcess](https://www.lintcode.com/problem/kill-process/description) | 需要同时使用队列与HashMap | ⭐️️️⭐️️⭐️️⭐️️️⭐️️️️ |
| [ImplementQueueByTwoStacks](https://www.lintcode.com/problem/implement-queue-by-two-stacks/description) | 这题的关键在于，并不需要每次Push和Pop的时候都翻转两个栈，而应该推迟到需要的时候，这种基础问题，必须十分地熟练才可以 | ⭐️️️⭐️️⭐️️⭐️️️⭐️️️️ |
| [ImplementQueueByCircularArray](https://www.lintcode.com/problem/implement-queue-by-circular-array/description) | 和上面一题很像，基本功问题 | ⭐️️️⭐️️⭐️️️ |
| [FirstUniqueNumberInDataStream2](https://www.lintcode.com/problem/first-unique-number-in-data-stream-ii/description) | 优先级队列 + HashMap，考虑优先级队列的基本初衷在于次序是被关心的，相应地，队列的优先级规则显然也基于出现某种被关心的次序 | ⭐️️️⭐️️⭐️️️⭐️️️️ |

## 链表

链表问题的常见处理：

1. 管理好prev, curr和next三个指针的关系
2. 双指针模式：一个快指针和一个慢指针
3. 链表相交问题：是否相交并寻找焦点
4. next指针的巧用问题
5. 哨兵节点模式
6. 环的问题

| 问题 | 简单说明 | 推荐指数 |
|:--------|:------------|:---------------|
| [SortList](https://www.lintcode.com/problem/sort-list/description) | 单链表排序，非常重要 | ⭐️️️⭐️️⭐️️⭐️️️⭐️️️️ |
| [MergeKSortedLists](https://www.lintcode.com/problem/merge-k-sorted-lists/description) | 用堆来维护K大数，然后就是链表的合并操作，对编程要求还比较高 |  ⭐️️️⭐️️⭐️️️⭐️⭐️ |
| [LinkedListCycle](https://www.lintcode.com/problem/linked-list-cycle/description) | 链表带环的问题，必须掌握的经典 | ⭐️️️⭐️️⭐️️️⭐️⭐️ |
| [LinkedListCycle2](https://www.lintcode.com/problem/linked-list-cycle-ii/description) | 判断单链表有没有环，很无聊，但确是必须熟悉的 |  ⭐️️️⭐️️⭐️️️⭐️⭐️ |
| [RotateList](https://www.lintcode.com/problem/rotate-list/description) | 和上题一样，无聊但是必须掌握，这类题的本质都是过程式思维的考察，所以思路清晰是很关键 | ⭐️️️⭐️️⭐️️️⭐️⭐️ |
| [ReverseNodesInKGroup](https://www.lintcode.com/problem/reverse-nodes-in-k-group/description) | 超级好题啊，如果你的代码里有太多if-else，考虑怎么简化，本题其实可以给六个星星了 | ⭐️️️⭐️️⭐️️️⭐️⭐️ |
| [CopyListWithRandomPointer](https://www.lintcode.com/problem/copy-list-with-random-pointer/description) | 超级好题啊，巧用next指针，话说单链表的next指针真是充满了各种神奇的用法，另外这道题应该是困难级了 | ⭐️️️⭐️️⭐️️️⭐️⭐️ |
| [RemoveDuplicatesFromSortedList2](https://www.lintcode.com/problem/remove-duplicates-from-sorted-list-ii/description) | 好题，说真的单链表的题都很能考察编程能力 | ⭐️️️⭐️️⭐️️️⭐️⭐️ |
| [SwapTwoNodesInLinkedList](https://www.lintcode.com/problem/swap-two-nodes-in-linked-list/description) | 需要考虑一些特殊情况，值得重做一次 | ⭐️️️⭐️️⭐️️️⭐️⭐️ |
| [MiddleOfTheLinkedList](https://www.lintcode.com/problem/middle-of-the-linked-list/description) | 双指针问题再现，主要是需要熟悉 | ⭐️️️⭐️️⭐️️️⭐️⭐️ |
| [ConnectedComponentsInList](https://www.lintcode.com/problem/connected-components-in-list/description) | 有点新鲜感 | ⭐️️️⭐️️⭐️️️ |
| [InsertIntoACyclicSortedList](https://www.lintcode.com/problem/insert-into-a-cyclic-sorted-list/description) | 虽然很无趣，但看到LintCode上说Google、Amazon和Facebook这样的大腕都在考这道题，好吧... | ⭐️️️⭐️️⭐️️️ |
| [PalindromeLinkedList](https://www.lintcode.com/problem/palindrome-linked-list/description) | 结合了**双指针寻找链表中点**和**链表翻转**这两个重要的链表操作的好题 | ⭐️️️⭐️️⭐️️️⭐️⭐️ |
| [IntersectionOfTwoLinkedLists](https://www.lintcode.com/problem/intersection-of-two-linked-lists/description) | 也是必须熟悉的链表操作 | ⭐️️️⭐️️⭐️️️⭐️⭐️ |
