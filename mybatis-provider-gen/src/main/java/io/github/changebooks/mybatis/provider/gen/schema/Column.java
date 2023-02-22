package io.github.changebooks.mybatis.provider.gen.schema;

import java.io.Serializable;

/**
 * 字段描述
 * 包含：字段名、备注、类型、长度等
 *
 * @author changebooks@qq.com
 */
public final class Column implements Serializable {
    /**
     * 字段名
     */
    private String name;

    /**
     * 备注
     */
    private String remark;

    /**
     * 类型，java.sql.Types
     */
    private int type;

    /**
     * 类型名，java.sql.Types
     */
    private String typeName;

    /**
     * 类型，jdbc type
     */
    private String jdbcType;

    /**
     * 类型，java type
     */
    private Class<?> javaType;

    /**
     * 长度
     */
    private int size;

    /**
     * 精度
     */
    private int scale;

    /**
     * 默认值
     */
    private Object defaultValue;

    /**
     * 主键
     */
    private boolean primaryKey;

    /**
     * 允许空？
     */
    private boolean allowNull;

    /**
     * 自增？
     */
    private boolean autoIncrement;

    @Override
    public String toString() {
        Object defaultValue = getDefaultValue();
        String quoteDefaultValue = (defaultValue instanceof String) ? "\"" + defaultValue + "\"" : String.valueOf(defaultValue);

        return "{" +
                "\"name\": \"" + getName() + "\", " +
                "\"remark\": \"" + getRemark() + "\", " +
                "\"type\": " + getType() + ", " +
                "\"typeName\": \"" + getTypeName() + "\", " +
                "\"jdbcType\": \"" + getJdbcType() + "\", " +
                "\"javaType\": \"" + getJavaType() + "\", " +
                "\"size\": " + getSize() + ", " +
                "\"scale\": " + getScale() + ", " +
                "\"defaultValue\": " + quoteDefaultValue + ", " +
                "\"primaryKey\": " + isPrimaryKey() + ", " +
                "\"allowNull\": " + isAllowNull() + ", " +
                "\"autoIncrement\": " + isAutoIncrement() + "" +
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getJdbcType() {
        return jdbcType;
    }

    public void setJdbcType(String jdbcType) {
        this.jdbcType = jdbcType;
    }

    public Class<?> getJavaType() {
        return javaType;
    }

    public void setJavaType(Class<?> javaType) {
        this.javaType = javaType;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getScale() {
        return scale;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    public Object getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(Object defaultValue) {
        this.defaultValue = defaultValue;
    }

    public boolean isPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(boolean primaryKey) {
        this.primaryKey = primaryKey;
    }

    public boolean isAllowNull() {
        return allowNull;
    }

    public void setAllowNull(boolean allowNull) {
        this.allowNull = allowNull;
    }

    public boolean isAutoIncrement() {
        return autoIncrement;
    }

    public void setAutoIncrement(boolean autoIncrement) {
        this.autoIncrement = autoIncrement;
    }

}
