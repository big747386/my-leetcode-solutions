// Created by Bob at 2023/06/14 09:46
// https://leetcode.cn/problems/permutations/

/*
46. 全排列 (Medium)

给定一个不含重复数字的数组 `nums` ，返回其 所有可能的全排列 。你可以 **按任意顺序** 返回答案。

**示例 1：**

```
输入：nums = [1,2,3]
输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]

```

**示例 2：**

```
输入：nums = [0,1]
输出：[[0,1],[1,0]]

```

**示例 3：**

```
输入：nums = [1]
输出：[[1]]

```

**提示：**

- `1 <= nums.length <= 6`
- `-10 <= nums[i] <= 10`
- `nums` 中的所有整数 **互不相同**
*/

// @lc code=begin

import java.util.*;
import java.util.stream.Collectors;

class Solution46 {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        Deque<Integer> path = new ArrayDeque<>();
        dfs(nums, path, 0, used, ans);
        return ans;
    }

//    private void backtrack(int[] nums, int level, List<List<Integer>> ans) {
//        if (level == nums.length-1) {
//            ans.add(Arrays.stream(nums).boxed().collect(Collectors.toList()));
//            return;
//        }
//        for (int i = level;i< nums.length; i++) {
//            swap(nums, i, level);
//            backtrack(nums, level + 1, ans);
//            swap(nums, level, i);
//        }
//    }
//
//    private void swap(int[] nums, int i, int j) {
//        int tmp = nums[i];
//        nums[i] = nums[j];
//        nums[j] = tmp;
//    }

    private void dfs(int[] nums, Deque<Integer> path, int depth, boolean[] used, List<List<Integer>> ans) {
        if (depth == nums.length) {
            ans.add(new ArrayList<>(path));
        }
        for (int i = 0; i < nums.length;i ++) {
            if (!used[i]) {
                path.addLast(nums[i]);
                used[i] = true;
                dfs(nums, path, depth+1, used, ans);
                used[i] = false;
                path.removeLast();
            }
        }
    }
}

// @lc code=end
