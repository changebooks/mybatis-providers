package io.github.changebooks.mybatis.provider.gen.formatter;

import io.github.changebooks.mybatis.provider.gen.schema.Column;
import io.github.changebooks.mybatis.provider.gen.schema.Table;

import java.util.Optional;

/**
 * Format annotation
 *
 * @author changebooks@qq.com
 */
public final class AnnotationFormatter {
    /**
     * 类注释
     */
    public static final String CLASS = "" +
            "%s/**\n" +
            "%s * %s\n" +
            "%s *\n" +
            "%s * @author %s\n" +
            "%s */\n";

    /**
     * 属性注释
     */
    public static final String FIELD = "" +
            "%s/**\n" +
            "%s * %s\n" +
            "%s */\n";

    private AnnotationFormatter() {
    }

    /**
     * 格式化类注释
     *
     * @param table   表描述
     * @param author  作者
     * @param leftTab 左制表
     * @return ** class description * ** author name *
     */
    public static String formatClass(Table table, String author, String leftTab) {
        if (table != null) {
            String description = table.getRemark();
            return formatClass(description, author, leftTab);
        } else {
            return "";
        }
    }

    /**
     * 格式化类注释
     *
     * @param description 类描述
     * @param author      作者
     * @param leftTab     左制表
     * @return ** class description * ** author name *
     */
    public static String formatClass(String description, String author, String leftTab) {
        description = Optional.ofNullable(description).orElse("").trim();
        author = Optional.ofNullable(author).orElse("").trim();
        leftTab = Optional.ofNullable(leftTab).orElse("");

        return String.format(CLASS,
                // %s/**\n
                leftTab,
                // %s * %s\n
                leftTab, description,
                // %s *\n
                leftTab,
                // %s * @author %s\n
                leftTab, author,
                // %s */\n
                leftTab
        );
    }

    /**
     * 格式化属性注释
     *
     * @param column  字段描述
     * @param leftTab 左制表
     * @return ** field description *
     */
    public static String formatField(Column column, String leftTab) {
        if (column != null) {
            String description = column.getRemark();
            return formatField(description, leftTab);
        } else {
            return "";
        }
    }

    /**
     * 格式化属性注释
     *
     * @param description 属性描述
     * @param leftTab     左制表
     * @return ** field description *
     */
    public static String formatField(String description, String leftTab) {
        description = Optional.ofNullable(description).orElse("").trim();
        leftTab = Optional.ofNullable(leftTab).orElse("");

        return String.format(FIELD,
                // %s/**\n
                leftTab,
                // %s * %s\n
                leftTab, description,
                // %s */\n
                leftTab
        );
    }

}
