package io.github.changebooks.mybatis.provider.gen.formatter;

import io.github.changebooks.mybatis.provider.gen.schema.Column;
import io.github.changebooks.mybatis.provider.gen.schema.Table;
import io.github.changebooks.mybatis.provider.gen.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Format pojo
 *
 * @author changebooks@qq.com
 */
public final class PojoFormatter {
    /**
     * 不必导包
     */
    public static final String IGNORE = "java.lang.";

    /**
     * 导包代码
     */
    public static final String IMPORT = "import %s;\n";

    /**
     * 类代码
     */
    public static final String CLASS = "" +
            "%spublic final class %s implements Serializable {\n" +
            "%s\n" +
            "%s}\n";

    /**
     * 属性代码
     */
    public static final String FIELD = "%sprivate %s %s;\n";

    /**
     * 方法代码
     */
    public static final String METHOD = "" +
            "%spublic %s get%s() {\n" +
            "%s\treturn %s;\n" +
            "%s}\n" +
            "\n" +
            "%spublic void set%s(%s %s) {\n" +
            "%s\tthis.%s = %s;\n" +
            "%s}\n";

    private PojoFormatter() {
    }

    /**
     * 格式化导包代码
     *
     * @param columns 字段描述列表
     * @return import package.name
     */
    public static Set<String> formatImport(List<Column> columns) {
        return Optional.ofNullable(columns).
                orElse(Collections.emptyList()).
                stream().
                filter(Objects::nonNull).
                map(PojoFormatter::formatImport).
                filter(x -> !x.isEmpty()).
                collect(Collectors.toSet());
    }

    /**
     * 格式化导包代码
     *
     * @param column 字段描述
     * @return import package.name
     */
    public static String formatImport(Column column) {
        if (column != null) {
            String packageName = Optional.ofNullable(column.getJavaType()).map(Class::getName).orElse("");
            return formatImport(packageName);
        } else {
            return "";
        }
    }

    /**
     * 格式化导包代码
     *
     * @param packageName 包名
     * @return import package.name
     */
    public static String formatImport(String packageName) {
        packageName = Optional.ofNullable(packageName).orElse("").trim();
        if (packageName.isEmpty() || packageName.contains(IGNORE)) {
            return "";
        } else {
            return String.format(IMPORT,
                    // import %s;\n
                    packageName
            );
        }
    }

    /**
     * 格式化类代码
     *
     * @param table   表描述
     * @param code    代码
     * @param leftTab 左制表
     * @return public final class Name implements Serializable { ... }
     */
    public static String formatClass(Table table, String code, String leftTab) {
        if (table != null) {
            String tableName = table.getName();
            String className = StringUtils.underscoreToCamel(tableName);
            return formatClass(className, code, leftTab);
        } else {
            return "";
        }
    }

    /**
     * 格式化类代码
     *
     * @param className 类名
     * @param code      代码
     * @param leftTab   左制表
     * @return public final class Name implements Serializable { ... }
     */
    public static String formatClass(String className, String code, String leftTab) {
        className = Optional.ofNullable(className).orElse("").trim();
        code = Optional.ofNullable(code).orElse("");
        leftTab = Optional.ofNullable(leftTab).orElse("");

        return String.format(CLASS,
                // %spublic final class %s implements Serializable {\n
                leftTab, className,
                // %s\n
                code,
                // %s}\n
                leftTab
        );
    }

    /**
     * 格式化属性代码
     *
     * @param column  字段描述
     * @param leftTab 左制表
     * @return private Type name
     */
    public static String formatField(Column column, String leftTab) {
        if (column != null) {
            String javaType = Optional.ofNullable(column.getJavaType()).map(Class::getSimpleName).orElse("");
            String columnName = column.getName();
            String methodName = StringUtils.underscoreToCamel(columnName);
            String fieldName = StringUtils.lowerFirst(methodName);
            return formatField(javaType, fieldName, leftTab);
        } else {
            return "";
        }
    }

    /**
     * 格式化属性代码
     *
     * @param javaType  属性类型
     * @param fieldName 属性名
     * @param leftTab   左制表
     * @return private Type name
     */
    public static String formatField(String javaType, String fieldName, String leftTab) {
        javaType = Optional.ofNullable(javaType).orElse("").trim();
        fieldName = Optional.ofNullable(fieldName).orElse("").trim();
        leftTab = Optional.ofNullable(leftTab).orElse("");

        return String.format(FIELD,
                // %sprivate %s %s;\n
                leftTab, javaType, fieldName
        );
    }

    /**
     * 格式化方法代码
     *
     * @param columns 字段描述列表
     * @param leftTab 左制表
     * @return private Type getName() private Type setName(Type name)
     */
    public static List<String> formatMethod(List<Column> columns, String leftTab) {
        return Optional.ofNullable(columns).
                orElse(Collections.emptyList()).
                stream().
                filter(Objects::nonNull).
                map(x -> PojoFormatter.formatMethod(x, leftTab)).
                filter(x -> !x.isEmpty()).
                collect(Collectors.toList());
    }

    /**
     * 格式化方法代码
     *
     * @param column  字段描述
     * @param leftTab 左制表
     * @return private Type getName() private Type setName(Type name)
     */
    public static String formatMethod(Column column, String leftTab) {
        if (column != null) {
            String javaType = Optional.ofNullable(column.getJavaType()).map(Class::getSimpleName).orElse("");
            String columnName = column.getName();
            String methodName = StringUtils.underscoreToCamel(columnName);
            String fieldName = StringUtils.lowerFirst(methodName);
            return formatMethod(javaType, fieldName, methodName, leftTab);
        } else {
            return "";
        }
    }

    /**
     * 格式化方法代码
     *
     * @param javaType   属性类型
     * @param fieldName  属性名
     * @param methodName 方法名
     * @param leftTab    左制表
     * @return private Type getName() private Type setName(Type name)
     */
    public static String formatMethod(String javaType, String fieldName, String methodName, String leftTab) {
        javaType = Optional.ofNullable(javaType).orElse("").trim();
        fieldName = Optional.ofNullable(fieldName).orElse("").trim();
        methodName = Optional.ofNullable(methodName).orElse("").trim();
        leftTab = Optional.ofNullable(leftTab).orElse("");

        return String.format(METHOD,
                // %spublic %s get%s() {\n
                leftTab, javaType, methodName,
                // %s\treturn %s;\n
                leftTab, fieldName,
                // %s}\n
                leftTab,
                // %spublic void set%s(%s %s) {\n
                leftTab, methodName, javaType, fieldName,
                // %s\tthis.%s = %s;\n
                leftTab, fieldName, fieldName,
                // %s}\n
                leftTab
        );
    }

}
