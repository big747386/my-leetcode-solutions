// Created by Bob at 2023/06/15 11:16
// https://leetcode.cn/problems/combinations/

/*
77. 组合 (Medium)

给定两个整数 `n` 和 `k`，返回范围 `[1, n]` 中所有可能的 `k` 个数的组合。

你可以按 **任何顺序** 返回答案。

**示例 1：**

```
输入：n = 4, k = 2
输出：
[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]
```

**示例 2：**

```
输入：n = 1, k = 1
输出：[[1]]
```

**提示：**

- `1 <= n <= 20`
- `1 <= k <= n`
*/

// @lc code=begin

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

class Solution77 {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        Deque<Integer> path = new ArrayDeque<>();
        boolean[] used = new boolean[n+1];
        int depth = 0;

        dfs(n, k,1, path, depth, used, ans);
        return ans;
    }

    private void dfs(int n, int k, int cur, Deque<Integer> path, int depth, boolean[] used,  List<List<Integer>> ans) {
        if (depth == k) {
            ans.add(new ArrayList<>(path));
            return;
        }
        for (int i = cur; i <= n; i++) {
            if (!used[i]) {
                path.addLast(i);
                used[i] = true;
                dfs(n,k,i+1,path, depth+1, used, ans);
                used[i] = false;
                path.removeLast();
            }
        }
    }


}

// @lc code=end
