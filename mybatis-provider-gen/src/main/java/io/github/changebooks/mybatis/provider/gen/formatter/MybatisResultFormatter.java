package io.github.changebooks.mybatis.provider.gen.formatter;

import io.github.changebooks.mybatis.provider.gen.schema.Column;
import io.github.changebooks.mybatis.provider.gen.schema.Table;

import java.util.Optional;

/**
 * Format mybatis result
 *
 * @author changebooks@qq.com
 */
public final class MybatisResultFormatter {
    /**
     * &#064;MybatisResultMap
     */
    public static final String MYBATIS_RESULT_MAP = "%s@MybatisResultMap(table = \"%s\")\n";

    /**
     * &#064;MybatisResult
     */
    public static final String MYBATIS_RESULT = "%s@MybatisResult(column = \"%s\", jdbcType = JdbcType.%s, id = %b, autoIncrement = %b)\n";

    private MybatisResultFormatter() {
    }

    /**
     * 格式化&#064;MybatisResultMap
     *
     * @param table   表描述
     * @param leftTab 左制表
     * @return &#064;MybatisResultMap(table, id)
     */
    public static String formatMybatisResultMap(Table table, String leftTab) {
        if (table != null) {
            String tableName = table.getName();
            return formatMybatisResultMap(tableName, leftTab);
        } else {
            return "";
        }
    }

    /**
     * 格式化&#064;MybatisResultMap
     *
     * @param table   表名
     * @param leftTab 左制表
     * @return &#064;MybatisResultMap(table)
     */
    public static String formatMybatisResultMap(String table, String leftTab) {
        table = Optional.ofNullable(table).orElse("").trim();
        leftTab = Optional.ofNullable(leftTab).orElse("");

        return String.format(MYBATIS_RESULT_MAP,
                // %s@MybatisResultMap(table = "%s")\n
                leftTab, table
        );
    }

    /**
     * 格式化&#064;MybatisResult
     *
     * @param column  字段描述
     * @param leftTab 左制表
     * @return &#064;MybatisResult(column, jdbcType, id, autoIncrement)
     */
    public static String formatMybatisResult(Column column, String leftTab) {
        if (column != null) {
            String columnName = column.getName();
            String jdbcType = column.getJdbcType();
            boolean id = column.isPrimaryKey();
            boolean autoIncrement = column.isAutoIncrement();
            return formatMybatisResult(columnName, jdbcType, id, autoIncrement, leftTab);
        } else {
            return "";
        }
    }

    /**
     * 格式化&#064;MybatisResult
     *
     * @param column        属性名
     * @param jdbcType      Jdbc Type
     * @param id            Primary Key ?
     * @param autoIncrement Auto Increment ?
     * @param leftTab       左制表
     * @return &#064;MybatisResult(column, jdbcType, id, autoIncrement)
     */
    public static String formatMybatisResult(String column, String jdbcType, boolean id, boolean autoIncrement, String leftTab) {
        column = Optional.ofNullable(column).orElse("").trim();
        jdbcType = Optional.ofNullable(jdbcType).orElse("").trim();
        leftTab = Optional.ofNullable(leftTab).orElse("");

        return String.format(MYBATIS_RESULT,
                // %s@MybatisResult(column = "%s", jdbcType = JdbcType.%s, id = %b, autoIncrement = %b)\n
                leftTab, column, jdbcType, id, autoIncrement
        );
    }

}
