// Created by Bob at 2023/05/19 11:43
// https://leetcode.cn/problems/non-overlapping-intervals/

/*
435. 无重叠区间 (Medium)

给定一个区间的集合 `intervals` ，其中 `intervals[i] = [startᵢ, endᵢ]`
。返回 需要移除区间的最小数量，使剩余区间互不重叠 。

**示例 1:**

```
输入: intervals = [[1,2],[2,3],[3,4],[1,3]]
输出: 1
解释: 移除 [1,3] 后，剩下的区间没有重叠。

```

**示例 2:**

```
输入: intervals = [ [1,2], [1,2], [1,2] ]
输出: 2
解释: 你需要移除两个 [1,2] 来使剩下的区间没有重叠。

```

**示例 3:**

```
输入: intervals = [ [1,2], [2,3] ]
输出: 0
解释: 你不需要移除任何区间，因为它们已经是无重叠的了。

```

**提示:**

- `1 <= intervals.length <= 10⁵`
- `intervals[i].length == 2`
- `-5 * 10⁴ <= startᵢ < endᵢ <= 5 * 10⁴`
*/

// @lc code=begin

import java.util.Arrays;
import java.util.Comparator;

class Solution435 {
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length == 0) {
            return 0;
        }
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                    return o1[0] - o2[0];
            }
        });
        int ans = 1;
        int n = intervals.length;
        int left = intervals[n-1][0];
        for (int i = n-2; i >= 0; i--) {
            if (intervals[i][1] <= left) {
                ans++;
                left =  intervals[i][0];
            }
        }
        return intervals.length - ans;
    }

}

// @lc code=end
