package com.datoucai.sensitive;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * DFA算法-敏感词过滤
 */
public class DFADemoTest {
    private static class State {
        private final Map<Character, State> transitions = new HashMap<>();
        private boolean isTerminal = false;

        public State nextState(char c) {
            return transitions.getOrDefault(c, null);
        }

        public void addTransition(char c, State nextState) {
            transitions.put(c, nextState);
        }

        public boolean isTerminal() {
            return isTerminal;
        }

        public void setTerminal(boolean isTerminal) {
            this.isTerminal = isTerminal;
        }
    }

    private final State startState = new State();

    // 添加敏感词到 DFA 中
    public void addWord(String word) {
        State currentState = startState;
        for (char c : word.toCharArray()) {
            State nextState = currentState.nextState(c);
            if (nextState == null) {
                nextState = new State();
                currentState.addTransition(c, nextState);
            }
            currentState = nextState;
        }
        currentState.setTerminal(true);
    }

    // 批量添加敏感词
    public void addWords(List<String> words) {
        for (String word : words) {
            addWord(word);
        }
    }

    // 过滤函数，替换敏感词为指定字符
    public String filter(String text, char replacement) {
        StringBuilder result = new StringBuilder(text);
        int n = text.length();

        for (int i = 0; i < n; ) {
            State currentState = startState;
            int j = i;

            while (j < n && currentState != null) {
                currentState = currentState.nextState(text.charAt(j));

                if (currentState != null && currentState.isTerminal()) {
                    // 替换敏感词
                    for (int k = i; k <= j; k++) {
                        result.setCharAt(k, replacement);
                    }
                    i = j + 1;  // Jump to the next character after the sensitive word
                    break;
                }
                j++;
            }

            if (currentState == null) {
                i++;  // Move to the next character if no match was found
            }
        }

        return result.toString();
    }

    // 过滤函数，默认替换敏感词为 '*'
    public String filter(String text) {
        return filter(text, '*');
    }

    public static void main(String[] args) {
        DFADemoTest dfaDemoTest = new DFADemoTest();
        dfaDemoTest.addWords(Arrays.asList("bad", "badword2", "badword3"));

        String text = "This is a badword1 and another badword2 in the text.";
        String filteredText = dfaDemoTest.filter(text);

        System.out.println("Original text: " + text);
        System.out.println("Filtered text: " + filteredText);
    }
}
