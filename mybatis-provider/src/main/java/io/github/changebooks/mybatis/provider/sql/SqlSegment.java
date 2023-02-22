package io.github.changebooks.mybatis.provider.sql;

import io.github.changebooks.mybatis.provider.tag.TagResult;
import org.apache.ibatis.type.JdbcType;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Build SQL segment
 *
 * @author changebooks@qq.com
 */
public final class SqlSegment {

    private static final List<TagResult> EMPTY_LIST = Collections.emptyList();

    private SqlSegment() {
    }

    /**
     * COLUMN NAMES
     *
     * @param columns the {@link TagResult} List
     * @return [ column, column ]
     */
    public static String[] getColumns(List<TagResult> columns) {
        return Optional.ofNullable(columns).
                orElse(EMPTY_LIST).
                stream().
                filter(Objects::nonNull).
                map(TagResult::getColumn).
                filter(Objects::nonNull).
                toArray(String[]::new);
    }

    /**
     * WHERE CONDITIONS
     *
     * @param columns the {@link TagResult} List
     * @return [ column = #{property,jdbcType=JDBC_TYPE}, column = #{property} ]
     */
    public static String[] getWhere(List<TagResult> columns) {
        return Optional.ofNullable(columns).
                orElse(EMPTY_LIST).
                stream().
                filter(Objects::nonNull).
                map(SqlSegment::joinKeyValue).
                filter(Objects::nonNull).
                toArray(String[]::new);
    }

    /**
     * WHERE CONDITIONS
     *
     * @param columns     the {@link TagResult} List
     * @param placeHolder {@code #} if true; {@code $} if false
     * @return [ column = #{property,jdbcType=JDBC_TYPE}, column = #{property}, column = ${property,jdbcType=JDBC_TYPE}, column = ${property} ]
     */
    public static String[] getWhere(List<TagResult> columns, boolean placeHolder) {
        return Optional.ofNullable(columns).
                orElse(EMPTY_LIST).
                stream().
                filter(Objects::nonNull).
                map(x -> joinKeyValue(x, placeHolder)).
                filter(Objects::nonNull).
                toArray(String[]::new);
    }

    /**
     * INSERT INTO COLUMNS
     *
     * @param columns the {@link TagResult} List
     * @return [ column, column ]
     */
    public static String[] getIntoColumns(List<TagResult> columns) {
        return Optional.ofNullable(columns).
                orElse(EMPTY_LIST).
                stream().
                filter(Objects::nonNull).
                map(TagResult::getColumn).
                filter(Objects::nonNull).
                toArray(String[]::new);
    }

    /**
     * INSERT INTO VALUES
     *
     * @param columns the {@link TagResult} List
     * @return [ #{property,jdbcType=JDBC_TYPE}, #{property} ]
     */
    public static String[] getIntoValues(List<TagResult> columns) {
        return Optional.ofNullable(columns).
                orElse(EMPTY_LIST).
                stream().
                filter(Objects::nonNull).
                map(SqlSegment::joinValue).
                filter(Objects::nonNull).
                toArray(String[]::new);
    }

    /**
     * UPDATE SETS
     *
     * @param columns the {@link TagResult} List
     * @return [ column = #{property,jdbcType=JDBC_TYPE}, column = #{property} ]
     */
    public static String[] getSets(List<TagResult> columns) {
        return Optional.ofNullable(columns).
                orElse(EMPTY_LIST).
                stream().
                filter(Objects::nonNull).
                map(SqlSegment::joinKeyValue).
                filter(Objects::nonNull).
                toArray(String[]::new);
    }

    /**
     * join key = value
     *
     * <pre>
     * column = #{property,jdbcType=JDBC_TYPE}
     * column = #{property}
     * </pre>
     *
     * @param tagResult the {@link TagResult} instance
     * @return key = #{}
     */
    public static String joinKeyValue(TagResult tagResult) {
        if (tagResult == null) {
            return null;
        }

        String column = tagResult.getColumn();
        if (column == null) {
            return null;
        }

        String value = joinValue(tagResult);
        if (value != null) {
            return String.format("%s = %s", column, value);
        } else {
            return null;
        }
    }

    /**
     * join key = value
     *
     * <pre>
     * column = #{property,jdbcType=JDBC_TYPE}
     * column = #{property}
     * column = ${property,jdbcType=JDBC_TYPE}
     * column = ${property}
     * </pre>
     *
     * @param tagResult   the {@link TagResult} instance
     * @param placeHolder {@code #} if true; {@code $} if false
     * @return key = #{}; key = ${}
     */
    public static String joinKeyValue(TagResult tagResult, boolean placeHolder) {
        if (tagResult == null) {
            return null;
        }

        String column = tagResult.getColumn();
        if (column == null) {
            return null;
        }

        String value = joinValue(tagResult, placeHolder);
        if (value != null) {
            return String.format("%s = %s", column, value);
        } else {
            return null;
        }
    }

    /**
     * join value
     *
     * <pre>
     * #{property,jdbcType=JDBC_TYPE}
     * #{property}
     * </pre>
     *
     * @param tagResult the {@link TagResult} instance
     * @return #{}
     */
    public static String joinValue(TagResult tagResult) {
        return joinValue(tagResult, true);
    }

    /**
     * join value
     *
     * <pre>
     * #{property,jdbcType=JDBC_TYPE}
     * #{property}
     * ${property,jdbcType=JDBC_TYPE}
     * ${property}
     * </pre>
     *
     * @param tagResult   the {@link TagResult} instance
     * @param placeHolder {@code #} if true; {@code $} if false
     * @return #{}; ${}
     */
    public static String joinValue(TagResult tagResult, boolean placeHolder) {
        if (tagResult == null) {
            return null;
        }

        String property = tagResult.getProperty();
        if (property == null) {
            return null;
        }

        JdbcType jdbcType = tagResult.getJdbcType();
        if (jdbcType != null) {
            return String.format("%s{%s,jdbcType=%s}", (placeHolder ? "#" : "$"), property, jdbcType.name());
        } else {
            return String.format("%s{%s}", (placeHolder ? "#" : "$"), property);
        }
    }

}
