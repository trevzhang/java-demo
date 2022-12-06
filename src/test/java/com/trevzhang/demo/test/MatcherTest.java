package com.trevzhang.demo.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.junit.Test;

/**
 * @author zhangchunguang.zcg
 * @since 2022/12/1 8:37 PM
 */
public class MatcherTest {
    private static final String FEATURE_PATTERN_REGEX = "data:image/png;base64,.*?(?=\"/>)";
    private static final Pattern PATTERN = Pattern.compile(FEATURE_PATTERN_REGEX);
private static final String content = "<p>线路特色</p><p></p><p></p><p></p><p></p><div class=\\\"media-wrap image-wrap\\\"><img src=\\\"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAZAAAAGQCAYAAACAvzbMAAAAAXNSR0IArs4c6QAAIABJREFUeF6kvQe0ZGd1JbxvqqqXO6m71cpSK+eAAkFk7LHHYIIRJhlsD3hgGOYfYzxj/x5sg7Fhhh8ZDAYTJEACY0yyAQOWSUYSUguBMq2c1UEdXq6qm/61T7j3q+qWmGXXWlK/V6/q3vulE/bZ55zoL2/bVQ+qIaI6xrCOgaJEiRpRPIV46QuIlj+GBGuBuEaNISJkqGugRXxQdyjRv8HYoHUnBtk7dTpYa654Qs6W01W9X6/3rER3V8xXwVpt327OOTxX/yD5XUukb6asH4TsPY41fs+0f19vzZwTZ48+dd+v0i+Q3XJDVUefZ8XyUfUH1F7RO8XPV9ve+t9n2iLHWr7+z5frzxF7RP1t/b3K7OAerevWpZQ+aYl2iATF1ylDPu1aOrtrvD+D3u/qN6AgfWvumyEXSq7QaRAROMjLbqrtwVS7VTWf4MG0oGBDED/i6z2ZIsqYiAin2Dv3wfS/t69r0/ji/teOw2vsn/drUn6UXtxMc5JmelbfTKI23j4FH5Ptg6/s4oLqfe3Kvs3OPkPbH71lm09y7D/e4ULtKqLqnxMaikgwfgPoAu1NwjR+qCAeYJwW/2qvT7p29Werbbmq35DOH8GIIA+t1TOr4Gcr/sfo2p9HHD/Dq2RelzkovnWVyIiBSGS4P8CjXExrlcOKJ8AAAAASUVORK5CYII=\\\"/></div><p></p>\"";
    @Test
    public void testMatcher() {

        Matcher matcher = PATTERN.matcher(content);
        matcher.replaceAll(" ");
        System.out.println(content);
    }

}
