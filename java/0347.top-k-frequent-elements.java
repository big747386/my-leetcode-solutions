// Created by Bob at 2023/06/09 09:35
// https://leetcode.cn/problems/top-k-frequent-elements/

/*
347. 前 K 个高频元素 (Medium)

给你一个整数数组 `nums` 和一个整数 `k` ，请你返回其中出现频率前 `k` 高的元素。你可以按
**任意顺序** 返回答案。

**示例 1:**

```
输入: nums = [1,1,1,2,2,3], k = 2
输出: [1,2]

```

**示例 2:**

```
输入: nums = [1], k = 1
输出: [1]
```

**提示：**

- `1 <= nums.length <= 10⁵`
- `k` 的取值范围是 `[1, 数组中不相同的元素的个数]`
- 题目数据保证答案唯一，换句话说，数组中前 `k` 个高频元素的集合是唯一的

**进阶：** 你所设计算法的时间复杂度 **必须** 优于 `O(n log n)` ，其中 `n` 是数组大小。
*/

// @lc code=begin

import java.util.*;

class Solution347 {
    public int[] topKFrequent(int[] nums, int k) {
        // 桶排序
//        Map<Integer, Integer> counter = new HashMap<>();
//        int max = 0;
//        for (int num: nums) {
//            counter.merge(num, 1, Integer::sum);
//            max = Math.max(max, counter.get(num));
//        }
//        List<List<Integer>> bucket = new ArrayList<>();
//        for (int i = 0; i <= max; i++) {
//            bucket.add(new ArrayList<>());
//        }
//        for (Map.Entry<Integer, Integer> entry: counter.entrySet()) {
//            bucket.get(entry.getValue()).add(entry.getKey());
//        }
//        int index = 0;
//        int[] ans = new int[k];
//        for (int i = max; i > 0; i--) {
//            if (!bucket.get(i).isEmpty()) {
//                for (int val : bucket.get(i)) {
//                    if (index == k) {
//                        return ans;
//                    }
//                    ans[index++] = val;
//                }
//            }
//        }
//        return ans;
        Map<Integer, Integer> occurrence = new HashMap<>();
        int max = 0;
        for (int num: nums) {
            occurrence.merge(num, 1, Integer::sum);
        }

        PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        for (Map.Entry<Integer, Integer> entry: occurrence.entrySet()) {
            int num = entry.getKey(), cnt = entry.getValue();
            if (queue.size() == k) {
                if (queue.peek()[1] < cnt) {
                    queue.poll();
                    queue.offer(new int[]{num, cnt});
                }
            } else {
                queue.offer(new int[]{num, cnt});
            }
        }
        int[] ans = new int[k];
        for (int i = 0; i < k; i++) {
            ans[i] = queue.poll()[0];
        }
        return ans;

        // 还有一种快速排序的方法 todo
    }
}

// @lc code=end
