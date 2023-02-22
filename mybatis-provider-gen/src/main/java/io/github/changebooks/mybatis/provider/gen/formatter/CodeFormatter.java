package io.github.changebooks.mybatis.provider.gen.formatter;

import io.github.changebooks.mybatis.provider.gen.schema.Column;
import io.github.changebooks.mybatis.provider.gen.schema.Table;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Format code
 *
 * @author changebooks@qq.com
 */
public final class CodeFormatter {
    /**
     * 制表
     */
    public static final String TAB_KEY = "\t";

    /**
     * 常用包
     */
    public static final Set<String> IMPORT_PACKAGES = new HashSet<>();

    static {
        IMPORT_PACKAGES.add("import io.github.changebooks.mybatis.provider.annotation.MybatisResult;");
        IMPORT_PACKAGES.add("import io.github.changebooks.mybatis.provider.annotation.MybatisResultMap;");
        IMPORT_PACKAGES.add("import org.apache.ibatis.type.JdbcType;");
        IMPORT_PACKAGES.add("import java.io.Serializable;");
    }

    private CodeFormatter() {
    }

    /**
     * 格式化导包代码
     *
     * @param columns 字段描述列表
     * @return import package.name
     */
    public static List<String> formatImport(List<Column> columns) {
        Set<String> result = PojoFormatter.formatImport(columns);
        result.addAll(IMPORT_PACKAGES);
        return result.
                stream().
                sorted().
                collect(Collectors.toList());
    }

    /**
     * 格式化类注释
     *
     * @param table  表描述
     * @param author 作者
     * @param code   代码
     * @return ** class description * ** author name * @MybatisResultMap(table) public final class Name implements Serializable { ... }
     */
    public static String formatClass(Table table, String author, String code) {
        return AnnotationFormatter.formatClass(table, author, "") +
                MybatisResultFormatter.formatMybatisResultMap(table, "") +
                PojoFormatter.formatClass(table, code, "");
    }

    /**
     * 格式化属性代码
     *
     * @param columns 字段描述列表
     * @return private Type name
     */
    public static List<String> formatField(List<Column> columns) {
        return Optional.ofNullable(columns).
                orElse(Collections.emptyList()).
                stream().
                filter(Objects::nonNull).
                map(CodeFormatter::formatField).
                filter(x -> !x.isEmpty()).
                collect(Collectors.toList());
    }

    /**
     * 格式化属性代码
     *
     * @param column 字段描述
     * @return private Type name
     */
    public static String formatField(Column column) {
        return AnnotationFormatter.formatField(column, TAB_KEY) +
                MybatisResultFormatter.formatMybatisResult(column, TAB_KEY) +
                PojoFormatter.formatField(column, TAB_KEY);
    }

    /**
     * 格式化方法代码
     *
     * @param columns 字段描述列表
     * @return private Type getName() private Type setName(Type name)
     */
    public static List<String> formatMethod(List<Column> columns) {
        return PojoFormatter.formatMethod(columns, TAB_KEY);
    }

}
