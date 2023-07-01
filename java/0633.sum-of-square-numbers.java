// Created by Bob at 2023/06/05 09:44
// https://leetcode.cn/problems/sum-of-square-numbers/

/*
633. 平方数之和 (Medium)

给定一个非负整数 `c` ，你要判断是否存在两个整数 `a` 和 `b`，使得 `a² + b² = c` 。

**示例 1：**

```
输入：c = 5
输出：true
解释：1 * 1 + 2 * 2 = 5

```

**示例 2：**

```
输入：c = 3
输出：false

```

**提示：**

- `0 <= c <= 2³¹ - 1`
*/

// @lc code=begin

class Solution633 {
    public boolean judgeSquareSum(int c) {
        long left = 0;
        long right = (long) Math.sqrt(c);
        while (left <= right) {
            long sum = left* left + right * right;
            if (sum < c) {
                left++;
            } else if (sum > c) {
                right--;
            } else {
                return true;
            }
        }
        return false;
    }
}

// @lc code=end
