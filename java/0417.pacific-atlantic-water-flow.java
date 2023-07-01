// Created by Bob at 2023/06/13 19:35
// https://leetcode.cn/problems/pacific-atlantic-water-flow/

/*
417. 太平洋大西洋水流问题 (Medium)

有一个 `m × n` 的矩形岛屿，与 **太平洋** 和 **大西洋** 相邻。 **“太平洋”**
处于大陆的左边界和上边界，而 **“大西洋”** 处于大陆的右边界和下边界。

这个岛被分割成一个由若干方形单元格组成的网格。给定一个 `m x n` 的整数矩阵 `heights` ，
`heights[r][c]` 表示坐标 `(r, c)` 上单元格 **高于海平面的高度** 。

岛上雨水较多，如果相邻单元格的高度 **小于或等于**
当前单元格的高度，雨水可以直接向北、南、东、西流向相邻单元格。水可以从海洋附近的任何单元格流入海洋。

返回网格坐标 `result` 的 **2D 列表** ，其中 `result[i] = [rᵢ, cᵢ]`
表示雨水从单元格 `(ri, ci)` 流动 **既可流向太平洋也可流向大西洋** 。

**示例 1：**

![](https://assets.leetcode.com/uploads/2021/06/08/waterflow-grid.jpg)

```
输入: heights =
[[1,2,2,3,5],[3,2,3,4,4],[2,4,5,3,1],[6,7,1,4,5],[5,1,1,2,4]]
输出: [[0,4],[1,3],[1,4],[2,2],[3,0],[3,1],[4,0]]

```

**示例 2：**

```
输入: heights = [[2,1],[1,2]]
输出: [[0,0],[0,1],[1,0],[1,1]]

```

**提示：**

- `m == heights.length`
- `n == heights[r].length`
- `1 <= m, n <= 200`
- `0 <= heights[r][c] <= 10⁵`
*/

// @lc code=begin

import java.util.ArrayList;
import java.util.List;

class Solution417 {
    int[] direction = new int[]{-1, 0, 1, 0, -1};
    int m, n;
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        m = heights.length;
        n = heights[0].length;
        boolean[][] canPacific = new boolean[m][n];
        boolean[][] canAtlantic = new boolean[m][n];
        for (int i = 0;i < m; i++) {
            dfs(heights, i, 0, canPacific);
            dfs(heights, i, n-1, canAtlantic);
        }
        for (int j = 0; j < n; j++) {
            dfs(heights,0, j, canPacific);
            dfs(heights, m-1, j, canAtlantic);
        }

        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (canAtlantic[i][j] && canPacific[i][j]) {
                    List<Integer> single = new ArrayList<>();
                    single.add(i);
                    single.add(j);
                    ans.add(single);
                }
            }
        }
        return ans;
    }

    private void dfs(int[][] heights, int x, int y, boolean[][] canReach) {
        if (canReach[x][y]) {
            return;
        }
        canReach[x][y] = true;
        for (int i =0; i< direction.length-1; i++) {
            int p = x + direction[i];
            int q = y + direction[i+1];

            if (p < m && p >= 0 && q < n && q >= 0
            && heights[p][q] >= heights[x][y]) {
                dfs(heights, p, q, canReach);
            }
        }
    }
}

// @lc code=end
