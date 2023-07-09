// Created by Bob at 2023/06/27 16:18
// https://leetcode.cn/problems/shortest-bridge/

/*
934. 最短的桥 (Medium)

给你一个大小为 `n x n` 的二元矩阵 `grid` ，其中 `1` 表示陆地， `0` 表示水域。

**岛** 是由四面相连的 `1` 形成的一个最大组，即不会与非组内的任何其他 `1` 相连。 `grid` 中
**恰好存在两座岛** 。

你可以将任意数量的 `0` 变为 `1` ，以使两座岛连接起来，变成 **一座岛** 。

返回必须翻转的 `0` 的最小数目。

**示例 1：**

```
输入：grid = [[0,1],[1,0]]
输出：1

```

**示例 2：**

```
输入：grid = [[0,1,0],[0,0,0],[0,0,1]]
输出：2

```

**示例 3：**

```
输入：grid =
[[1,1,1,1,1],[1,0,0,0,1],[1,0,1,0,1],[1,0,0,0,1],[1,1,1,1,1]]
输出：1

```

**提示：**

- `n == grid.length == grid[i].length`
- `2 <= n <= 100`
- `grid[i][j]` 为 `0` 或 `1`
- `grid` 中恰有两个岛
*/

// @lc code=begin

import java.util.ArrayDeque;
import java.util.Queue;

class Solution934 {
    int[] directions = new int[]{-1,0,1,0,-1};
    public int shortestBridge(int[][] grid) {
        int n = grid.length;
        Queue<int[]> queue = new ArrayDeque<>();
        boolean flipped = false;
        for (int i = 0 ; i < n; i++) {
            if (flipped) {
                break;
            }
            for (int j = 0 ; j < n; j++) {
                if (grid[i][j] == 1) {
                    dfs(grid, i, j, queue);
                    flipped = true;
                    break;
                }
            }
        }

        int level = 0;
        while (!queue.isEmpty()) {
            int nSize = queue.size();
            level++;
            while (nSize-- > 0) {
                int[] cur = queue.poll();
                int x = cur[0], y = cur[1];

                for (int i = 0; i < 4; i++) {
                    int nx = x + directions[i];
                    int ny = y + directions[i + 1];
                    if (nx < 0 || nx >= n || ny < 0 || ny >= n || grid[nx][ny] == 2) {
                        continue;
                    }
                    if (grid[nx][ny] == 1) {
                        return level;
                    }
                    if (grid[nx][ny] == 0) {
                        queue.offer(new int[] {nx,ny});
                        grid[nx][ny] = 2;
                    }
                }
            }
        }
        return 0;
    }

    private void dfs(int[][] grid, int i, int j, Queue<int[]> queue) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == 2) {
            return;
        }
        if (grid[i][j] == 0) {
            queue.offer(new int[]{i, j});
            grid[i][j] = 2;
            return;
        }
        grid[i][j] = 2;
        dfs(grid, i - 1, j, queue);
        dfs(grid, i + 1, j, queue);
        dfs(grid, i, j - 1, queue);
        dfs(grid, i, j + 1, queue);
    }

//    public static void main(String[] args) {
//        Solution934 solution = new Solution934();
//        int[][] array = {
//                {0, 1, 0},
//                {0, 0, 0},
//                {0, 0, 1}
//        };
//        solution.shortestBridge(array);
//    }
}

// @lc code=end
