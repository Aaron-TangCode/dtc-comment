package com.datoucai.sensitive;

import java.util.*;

/**
 * AC算法-敏感词过滤
 */
public class ACDemoTest {
    class ACNode {
        Map<Character, ACNode> children = new HashMap<>();
        ACNode fail; // 失败指针
        //存储当前节点所匹配到的所有模式字符串
        List<String> output = new ArrayList<>();
    }
    private ACNode root = new ACNode();

    // 添加敏感词到 Trie 树中
    public void addWord(String word) {
        ACNode currentNode = root;
        for (char c : word.toCharArray()) {
            currentNode = currentNode.children.computeIfAbsent(c, k -> new ACNode());
        }
        currentNode.output.add(word);
    }

    // 批量添加敏感词
    public void addWords(List<String> words) {
        for (String word : words) {
            addWord(word);
        }
        buildFailure();
    }

    // 构建失败指针
    private void buildFailure() {
        Queue<ACNode> queue = new LinkedList<>();

        // 初始化 root 的失败指针和第一层子节点的失败指针
        for (ACNode node : root.children.values()) {
            node.fail = root;
            queue.add(node);
        }

        while (!queue.isEmpty()) {
            ACNode currentNode = queue.remove();

            for (Map.Entry<Character, ACNode> entry : currentNode.children.entrySet()) {
                char c = entry.getKey();
                ACNode childNode = entry.getValue();
                ACNode failNode = currentNode.fail;

                // 找到当前字符对应的失败指针节点
                while (failNode != null && !failNode.children.containsKey(c)) {
                    failNode = failNode.fail;
                }
                if (failNode == null) {
                    childNode.fail = root;
                } else {
                    childNode.fail = failNode.children.get(c);
                    if (childNode.fail != null) {
                        childNode.output.addAll(childNode.fail.output);
                    }
                }

                queue.add(childNode);
            }
        }
    }

    // 过滤函数，替换敏感词为指定字符
    public String filter(String text, char replacement) {
        StringBuilder result = new StringBuilder(text);
        ACNode currentNode = root;

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);

            while (currentNode != null && !currentNode.children.containsKey(c)) {
                currentNode = currentNode.fail;
            }
            if (currentNode == null) {
                currentNode = root;
                continue;
            }
            currentNode = currentNode.children.get(c);

            if (currentNode.output.size() > 0) {
                for (String word : currentNode.output) {
                    int start = i - word.length() + 1;
                    for (int j = start; j <= i; j++) {
                        result.setCharAt(j, replacement);
                    }
                }
            }
        }

        return result.toString();
    }

    // 过滤函数，默认替换敏感词为 '*'
    public String filter(String text) {
        return filter(text, '*');
    }

    public static void main(String[] args) {
        ACDemoTest acDemoTest = new ACDemoTest();
        acDemoTest.addWords(Arrays.asList("badword1", "badword2", "badword3"));

        String text = "This is a badword1 and another badword2 in the text.";
        String filteredText = acDemoTest.filter(text);

        System.out.println("Original text: " + text);
        System.out.println("Filtered text: " + filteredText);
    }
}
