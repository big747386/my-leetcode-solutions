// Created by Bob at 2023/07/04 19:22
// https://leetcode.cn/problems/binary-tree-paths/

/*
257. 二叉树的所有路径 (Easy)

给你一个二叉树的根节点 `root` ，按 **任意顺序** ，返回所有从根节点到叶子节点的路径。

**叶子节点** 是指没有子节点的节点。

**示例 1：**

![](https://assets.leetcode.com/uploads/2021/03/12/paths-tree.jpg)

```
输入：root = [1,2,3,null,5]
输出：["1->2->5","1->3"]

```

**示例 2：**

```
输入：root = [1]
输出：["1"]

```

**提示：**

- 树中节点的数目在范围 `[1, 100]` 内
- `-100 <= Node.val <= 100`
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
*/

// @lc code=begin

import java.util.ArrayList;
import java.util.List;

class Solution257 {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        dfs(root, new StringBuilder(), res);
        return res;
    }

    private void dfs(TreeNode root, StringBuilder path, List<String> res) {
        if (root.left == null && root.right == null) {
            res.add(path.toString() + root.val);
            return;
        }
        int dd = path.length();
        path.append(root.val + "->");
        if (root.left!= null) {
            dfs(root.left, path, res);
        }
        if (root.right!= null) {
            dfs(root.right, path, res);
        }
        path.delete(dd, path.length());
    }
}

class TreeNode {
     int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
}

// @lc code=end
