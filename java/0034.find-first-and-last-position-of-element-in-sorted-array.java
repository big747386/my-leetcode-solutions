// Created by Bob at 2023/06/06 13:45
// https://leetcode.cn/problems/find-first-and-last-position-of-element-in-sorted-array/

/*
34. 在排序数组中查找元素的第一个和最后一个位置 (Medium)

给你一个按照非递减顺序排列的整数数组 `nums`，和一个目标值
`target`。请你找出给定目标值在数组中的开始位置和结束位置。

如果数组中不存在目标值 `target`，返回 `[-1, -1]`。

你必须设计并实现时间复杂度为 `O(log n)` 的算法解决此问题。

**示例 1：**

```
输入：nums = [5,7,7,8,8,10], target = 8
输出：[3,4]
```

**示例 2：**

```
输入：nums = [5,7,7,8,8,10], target = 6
输出：[-1,-1]
```

**示例 3：**

```
输入：nums = [], target = 0
输出：[-1,-1]
```

**提示：**

- `0 <= nums.length <= 10⁵`
- `-10⁹ <= nums[i] <= 10⁹`
- `nums` 是一个非递减数组
- `-10⁹ <= target <= 10⁹`
*/

// @lc code=begin

class Solution34 {
    public int[] searchRange(int[] nums, int target) {
        if (nums.length == 0) {
            return new int[]{-1, -1};
        }
        int left = 0, right = nums.length-1, mid;
        while (left <= right) {
            mid = left + (right-left) / 2;
            if (nums[mid] == target) {
                int start = mid;
                while (start >= 0 && nums[start] == target) {
                    start--;
                }
                int end = mid;
                while ( end < nums.length && nums[end] == target) {
                    end++;
                }
                return new int[]{start+1, end-1};
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid -1;
            }
        }
        return new int[]{-1, -1};
    }
}

// @lc code=end
