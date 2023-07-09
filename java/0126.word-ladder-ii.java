// Created by Bob at 2023/07/03 11:44
// https://leetcode.cn/problems/word-ladder-ii/

/*
126. 单词接龙 II (Hard)

按字典 `wordList` 完成从单词 `beginWord` 到单词 `endWord` 转化，一个表示此过程的
**转换序列** 是形式上像 `beginWord -> s₁ -> s₂ -> ... -> sₖ`
这样的单词序列，并满足：

- 每对相邻的单词之间仅有单个字母不同。
- 转换过程中的每个单词 `sᵢ`（ `1 <= i <= k`）必须是字典 `wordList` 中的单词。注意，
`beginWord` 不必是字典 `wordList` 中的单词。
- `sₖ == endWord`

给你两个单词 `beginWord` 和 `endWord` ，以及一个字典 `wordList`
。请你找出并返回所有从 `beginWord` 到 `endWord` 的 **最短转换序列**
，如果不存在这样的转换序列，返回一个空列表。每个序列都应该以单词列表 `[beginWord, s₁, s₂, ...,
sₖ]` 的形式返回。

**示例 1：**

```
输入：beginWord = "hit", endWord = "cog", wordList =
["hot","dot","dog","lot","log","cog"]
输出：[["hit","hot","dot","dog","cog"],["hit","hot","lot","log","cog"]]
解释：存在 2 种最短的转换序列：
"hit" -> "hot" -> "dot" -> "dog" -> "cog"
"hit" -> "hot" -> "lot" -> "log" -> "cog"

```

**示例 2：**

```
输入：beginWord = "hit", endWord = "cog", wordList =
["hot","dot","dog","lot","log"]
输出：[]
解释：endWord "cog" 不在字典 wordList 中，所以不存在符合要求的转换序列。

```

**提示：**

- `1 <= beginWord.length <= 5`
- `endWord.length == beginWord.length`
- `1 <= wordList.length <= 500`
- `wordList[i].length == beginWord.length`
- `beginWord`、 `endWord` 和 `wordList[i]` 由小写英文字母组成
- `beginWord != endWord`
- `wordList` 中的所有单词 **互不相同**
*/

// @lc code=begin

import java.util.*;

class Solution126 {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> ans = new ArrayList<>();
        if (!wordList.contains(endWord)) {
            return ans;
        }
        Set<String> dictionary = new HashSet<>(wordList);
        Map<String, List<String>> from = new HashMap<>();
        Queue<String> queue = new ArrayDeque<>();
        queue.offer(beginWord);
        boolean find = false;
        int step = 1;
        Map<String, Integer> steps = new HashMap<>();
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String curWord = queue.poll();
                char[] chars = curWord.toCharArray();
                for (int j = 0; j < chars.length; j++) {
                    char origin = chars[j];
                    for (char k = 'a'; k <= 'z'; k++) {
                        if (k == origin) {
                            continue;
                        }
                        chars[j] = k;
                        String nextWord = new String(chars);
                        if (steps.containsKey(nextWord) && step == steps.get(nextWord)) {
                            from.get(nextWord).add(curWord);
                        }
                        if (!dictionary.contains(nextWord)) {
                            continue;
                        }
                        dictionary.remove(nextWord);
                        if (nextWord.equals(endWord)) {
                            find = true;
                        }
                        queue.offer(nextWord);
                        from.putIfAbsent(nextWord, new ArrayList<>());
                        from.get(nextWord).add(curWord);
                        steps.put(nextWord, step);
                    }
                    chars[j] = origin;
                }
            }
            step++;
            if (find) {
                break;
            }
        }

        if (find) {
            Deque<String> path = new ArrayDeque<>();
            path.add(endWord);
            backtrack(from, path, ans, beginWord, endWord);
        }
        return ans;
    }


    private void backtrack(Map<String, List<String>> from, Deque<String> path, List<List<String>> ans, String beginWord, String curWord) {
        if (curWord.equals(beginWord)) {
            ans.add(new ArrayList<>(path));
            return;
        }
        for (String prev: from.get(curWord)) {
            path.addFirst(prev);
            backtrack(from, path, ans, beginWord, prev);
            path.removeFirst();
        }
    }

//    public static void main(String[] args) {
//        Solution solution = new Solution();
//
//        List<String> wordList = Arrays.asList("hot", "dot", "dog", "lot", "log", "cog");
//        solution.findLadders("hit", "cog", wordList);
//    }
}

// @lc code=end
