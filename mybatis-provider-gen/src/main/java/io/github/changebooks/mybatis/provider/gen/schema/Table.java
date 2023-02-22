package io.github.changebooks.mybatis.provider.gen.schema;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 表描述
 * 包含：表名、备注、字段列表等
 *
 * @author changebooks@qq.com
 */
public final class Table implements Serializable {
    /**
     * 表名
     */
    private String name;

    /**
     * 备注
     */
    private String remark;

    /**
     * 字段列表
     */
    private List<Column> columns;

    @Override
    public String toString() {
        String columns = Optional.ofNullable(getColumns()).
                orElse(Collections.emptyList()).
                stream().
                map(String::valueOf).
                collect(Collectors.joining(", ", "[", "]"));

        return "{" +
                "\"name\": \"" + getName() + "\", " +
                "\"remark\": \"" + getRemark() + "\", " +
                "\"columns\": " + columns + "" +
                "}";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }

}
