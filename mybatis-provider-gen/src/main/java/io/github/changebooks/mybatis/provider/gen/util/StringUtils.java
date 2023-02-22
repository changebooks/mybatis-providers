package io.github.changebooks.mybatis.provider.gen.util;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * String utils
 *
 * @author changebooks@qq.com
 */
public final class StringUtils {
    /**
     * 下划线
     */
    public static final String UNDERSCORE_SEPARATOR = "_";

    /**
     * 换行
     */
    public static final String NEW_LINE = "\n";

    private StringUtils() {
    }

    /**
     * join words
     *
     * @param list the word list
     * @return word\nword\nword
     */
    public static String join(List<String> list) {
        if (list != null) {
            return String.join(NEW_LINE, list);
        } else {
            return null;
        }
    }

    /**
     * converts underscore word to camel word
     *
     * @param word the underscore word
     * @return the camel word
     */
    public static String underscoreToCamel(String word) {
        if (word == null || word.isEmpty()) {
            return word;
        } else {
            String[] parts = word.split(UNDERSCORE_SEPARATOR);
            return Arrays.
                    stream(parts).
                    filter(Objects::nonNull).
                    map(StringUtils::upperFirst).
                    filter(Objects::nonNull).
                    collect(Collectors.joining(""));
        }
    }

    /**
     * converts first character to upper
     *
     * @param word the normal word
     * @return the title
     */
    public static String upperFirst(String word) {
        if (word == null || word.isEmpty()) {
            return word;
        } else {
            String initial = word.substring(0, 1).toUpperCase();
            String other = word.substring(1);
            return initial + other;
        }
    }

    /**
     * converts first character to lower
     *
     * @param word the title
     * @return the normal word
     */
    public static String lowerFirst(String word) {
        if (word == null || word.isEmpty()) {
            return word;
        } else {
            String initial = word.substring(0, 1).toLowerCase();
            String other = word.substring(1);
            return initial + other;
        }
    }

}
