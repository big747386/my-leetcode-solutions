// Created by Bob at 2023/06/08 15:59
// https://leetcode.cn/problems/kth-largest-element-in-an-array/

/*
215. 数组中的第K个最大元素 (Medium)

给定整数数组 `nums` 和整数 `k`，请返回数组中第 `k` 个最大的元素。

请注意，你需要找的是数组排序后的第 `k` 个最大的元素，而不是第 `k` 个不同的元素。

你必须设计并实现时间复杂度为 `O(n)` 的算法解决此问题。

**示例 1:**

```
输入: [3,2,1,5,6,4], k = 2
输出: 5

```

**示例 2:**

```
输入: [3,2,3,1,2,4,5,5,6], k = 4
输出: 4
```

**提示：**

- `1 <= k <= nums.length <= 10⁵`
- `-10⁴ <= nums[i] <= 10⁴`
*/

// @lc code=begin
// impor†ant
class Solution215 {
    public int findKthLargest(int[] nums, int k) {
        int l = 0, r = nums.length - 1;
        k = nums.length - k;
        while (l < r) {
            int mid = quickSelect(nums, l, r);
            if (mid == k) {
                return nums[mid];
            } else if (mid < k) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return nums[r];
    }

    private int quickSelect(int[] nums, int start, int end) {
        int i = start + 1, j = end;
        while (true) {
            while (i < end && nums[i] <= nums[start]) {
                i++;
            }
            while (start < j && nums[j] >= nums[start]) {
                j--;
            }
            if (i>=j) {
                break;
            }
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }
        int tmp = nums[start];
        nums[start] = nums[j];
        nums[j] = tmp;
        return j;
    }

}

// @lc code=end
