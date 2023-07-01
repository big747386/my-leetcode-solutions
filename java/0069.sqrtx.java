// Created by Bob at 2023/06/05 17:41
// https://leetcode.cn/problems/sqrtx/

/*
69. x 的平方根  (Easy)

给你一个非负整数 `x` ，计算并返回 `x` 的 **算术平方根** 。

由于返回类型是整数，结果只保留 **整数部分**，小数部分将被 **舍去 。**

**注意：** 不允许使用任何内置指数函数和算符，例如 `pow(x, 0.5)` 或者 `x ** 0.5` 。

**示例 1：**

```
输入：x = 4
输出：2

```

**示例 2：**

```
输入：x = 8
输出：2
解释：8 的算术平方根是 2.82842..., 由于返回类型是整数，小数部分将被舍去。

```

**提示：**

- `0 <= x <= 2³¹ - 1`
*/

// @lc code=begin

class Solution69 {
    public int mySqrt(int x) {
        int l = 1, r = x, mid, sqrt;
        while (l <= r) {
            mid = l + (r-l) / 2;
            sqrt = x/mid;
            if (sqrt == mid) {
                return mid;
            } else if (sqrt > mid) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return r;
    }
}

// @lc code=end