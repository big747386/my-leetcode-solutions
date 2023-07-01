// Created by Bob at 2023/06/05 10:33
// https://leetcode.cn/problems/longest-word-in-dictionary-through-deleting/

/*
524. 通过删除字母匹配到字典里最长单词 (Medium)

给你一个字符串 `s` 和一个字符串数组 `dictionary` ，找出并返回 `dictionary`
中最长的字符串，该字符串可以通过删除 `s` 中的某些字符得到。

如果答案不止一个，返回长度最长且字母序最小的字符串。如果答案不存在，则返回空字符串。

**示例 1：**

```
输入：s = "abpcplea", dictionary =
["ale","apple","monkey","plea"]
输出："apple"

```

**示例 2：**

```
输入：s = "abpcplea", dictionary = ["a","b","c"]
输出："a"

```

**提示：**

- `1 <= s.length <= 1000`
- `1 <= dictionary.length <= 1000`
- `1 <= dictionary[i].length <= 1000`
- `s` 和 `dictionary[i]` 仅由小写英文字母组成
*/

// @lc code=begin

import java.util.Arrays;
import java.util.List;

class Solution524 {
    public String findLongestWord(String s, List<String> dictionary) {
        // 双指针
//        dictionary.sort((o1, o2) -> {
//            if (o1.length() == o2.length()) {
//                return o1.compareTo(o2);
//            } else {
//                return o2.length() - o1.length();
//            }
//        });
//        for (String word : dictionary) {
//            int index = 0;
//            for (int i = 0; i < s.length(); i++) {
//                if (s.charAt(i) == word.charAt(index)) {
//                    index++;
//                }
//                if (index == word.length()) {
//                    return word;
//                }
//            }
//        }
//        return "";


        // 动态规划
        int m = s.length();
        int[][] f = new int[m+1][26];
        Arrays.fill(f[m], m);

        for (int i = m-1; i>=0; i--) {
            for (int j = 0; j < 26; j++) {
                if (s.charAt(i) == 'a' + j) {
                    f[i][j] = i;
                } else {
                    f[i][j] = f[i+1][j];
                }
            }
        }

        String res = "";
        for (String word: dictionary) {
            int j = 0;
            boolean match = true;
            for (int i = 0; i < word.length(); i++) {
                if (f[j][word.charAt(i) - 'a'] == m) {
                    match = false;
                    break;
                }
                j = f[j][word.charAt(i)-'a'] + 1;
            }
            if (match) {
                if (word.length() > res.length() || (word.length() == res.length() && word.compareTo(res) < 0)) {
                    res = word;
                }
            }
        }
        return res;
    }
}

// @lc code=end
