package com.datoucai.utils;

import com.datoucai.entity.TbSensitiveWordsExample;
import com.datoucai.service.ISensitiveService;
import com.datoucai.service.dto.SensitiveWordDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@Slf4j
public class DFAService implements InitializingBean {
    @Autowired
    private ISensitiveService sensitiveService;
    @Override
    public void afterPropertiesSet() throws Exception {
        TbSensitiveWordsExample tbSensitiveWordsExample = new TbSensitiveWordsExample();
        tbSensitiveWordsExample.setLimit(1000);
        List<SensitiveWordDto> sensitiveWordDtos = sensitiveService.queryByParam(tbSensitiveWordsExample);
        List<String> words = sensitiveWordDtos.stream().map(x -> x.getWord()).collect(Collectors.toList());
        addWords(words);
    }

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

    /**
     * 添加敏感词到 DFA 中
     * @param word
     */
    public void addWord(String word) {
        log.info("添加敏感词:{}",word);
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

    /**
     *  批量添加敏感词
      */
    public void addWords(List<String> words) {
        for (String word : words) {
            addWord(word);
        }
    }

    /**
     * 过滤函数，替换敏感词为指定字符
     * @param text
     * @param replacement
     * @return
     */
    public String checkSensitiveWords(String text, char replacement) {
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
                    i = j + 1;
                    break;
                }
                j++;
            }

            if (currentState == null) {
                i++;
            }
        }

        return result.toString();
    }

    /**
     * 过滤函数，默认替换敏感词为 '*'
     * @param text
     * @return
     */
    public String checkSensitiveWords(String text) {
        return checkSensitiveWords(text, '*');
    }

    public static void main(String[] args) {
        DFAService dfaService = new DFAService();
        dfaService.addWords(Arrays.asList("bad", "badword2", "badword3"));

        String text = "This is a badword1 and another badword2 in the text.";
        String filteredText = dfaService.checkSensitiveWords(text);

        System.out.println("Original text: " + text);
        System.out.println("Filtered text: " + filteredText);
    }
}
