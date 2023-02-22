package io.github.changebooks.mybatis.provider.tag;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Filter TagResult list
 *
 * @author changebooks@qq.com
 */
public final class TagResultFilter {

    private static final List<TagResult> EMPTY_LIST = Collections.emptyList();

    private TagResultFilter() {
    }

    /**
     * filter Id from {@link TagResult} List
     *
     * @param columns the {@link TagResult} List
     * @return TagResult List With Id
     */
    public static List<TagResult> filterId(List<TagResult> columns) {
        return Optional.ofNullable(columns).
                orElse(EMPTY_LIST).
                stream().
                filter(Objects::nonNull).
                filter(TagResult::isId).
                collect(Collectors.toList());
    }

    /**
     * remove Id from {@link TagResult} List
     *
     * @param columns the {@link TagResult} List
     * @return TagResult List Without Id
     */
    public static List<TagResult> removeId(List<TagResult> columns) {
        return Optional.ofNullable(columns).
                orElse(EMPTY_LIST).
                stream().
                filter(Objects::nonNull).
                filter(x -> !x.isId()).
                collect(Collectors.toList());
    }

    /**
     * filter Auto Increment from {@link TagResult} List
     *
     * @param columns the {@link TagResult} List
     * @return TagResult List With Auto Increment
     */
    public static List<TagResult> filterAutoIncrement(List<TagResult> columns) {
        return Optional.ofNullable(columns).
                orElse(EMPTY_LIST).
                stream().
                filter(Objects::nonNull).
                filter(TagResult::isAutoIncrement).
                collect(Collectors.toList());
    }

    /**
     * remove Auto Increment from {@link TagResult} List
     *
     * @param columns the {@link TagResult} List
     * @return TagResult List Without Auto Increment
     */
    public static List<TagResult> removeAutoIncrement(List<TagResult> columns) {
        return Optional.ofNullable(columns).
                orElse(EMPTY_LIST).
                stream().
                filter(Objects::nonNull).
                filter(x -> !x.isAutoIncrement()).
                collect(Collectors.toList());
    }

    /**
     * remove Null from {@link TagResult} List
     *
     * @param columns the {@link TagResult} List
     * @param record  the POJO
     * @param <T>     the type of the POJO
     * @return TagResult List Without Null
     * @throws IllegalAccessException if this {@code Field} object is enforcing Java language access control
     *                                and
     *                                the underlying field is inaccessible.
     */
    public static <T> List<TagResult> removeNull(List<TagResult> columns, T record) throws IllegalAccessException {
        Objects.requireNonNull(record, "record can't be null");

        if (columns == null) {
            return null;
        }

        List<TagResult> result = new ArrayList<>();

        for (TagResult column : columns) {
            if (column == null) {
                continue;
            }

            Field field = column.getField();
            Objects.requireNonNull(field, "field can't be null");

            Object value = field.get(record);
            if (value != null) {
                result.add(column);
            }
        }

        return result;
    }

}
