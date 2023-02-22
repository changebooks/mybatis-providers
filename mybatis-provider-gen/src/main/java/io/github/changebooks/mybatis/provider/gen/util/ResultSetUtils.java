package io.github.changebooks.mybatis.provider.gen.util;

import io.github.changebooks.mybatis.provider.gen.schema.Column;
import io.github.changebooks.mybatis.provider.gen.schema.Table;
import io.github.changebooks.mybatis.provider.gen.type.JavaType;
import io.github.changebooks.mybatis.provider.gen.type.JdbcType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

/**
 * Database ResultSet utils
 *
 * @author changebooks@qq.com
 */
public final class ResultSetUtils {

    private ResultSetUtils() {
    }

    /**
     * ResultSet to Table
     * ignore: columns
     *
     * @param rs the {@link ResultSet} instance
     * @return 表描述
     * @throws SQLException if the columnLabel is not valid;
     *                      if a database access error occurs
     *                      or
     *                      this method is called on a closed result set
     */
    public static Table getTable(ResultSet rs) throws SQLException {
        Objects.requireNonNull(rs, "rs can't be null");

        String name = rs.getString("TABLE_NAME");
        String remark = rs.getString("REMARKS");

        Table result = new Table();

        result.setName(name);
        result.setRemark(remark);

        return result;
    }

    /**
     * ResultSet to Column
     * ignore: primaryKeys
     *
     * @param rs the {@link ResultSet} instance
     * @return 字段描述
     * @throws SQLException if the columnLabel is not valid;
     *                      if a database access error occurs
     *                      or
     *                      this method is called on a closed result set
     */
    public static Column getColumn(ResultSet rs) throws SQLException {
        Objects.requireNonNull(rs, "rs can't be null");

        String name = rs.getString("COLUMN_NAME");
        String remark = rs.getString("REMARKS");
        int type = rs.getInt("DATA_TYPE");
        String typeName = rs.getString("TYPE_NAME");
        int size = rs.getInt("COLUMN_SIZE");
        int scale = rs.getInt("DECIMAL_DIGITS");
        Object defaultValue = rs.getObject("COLUMN_DEF");
        String allowNull = rs.getString("IS_NULLABLE");
        String autoIncrement = rs.getString("IS_AUTOINCREMENT");

        Column result = new Column();

        result.setName(name);
        result.setRemark(remark);
        result.setType(type);
        result.setTypeName(typeName);
        result.setJdbcType(JdbcType.lookup(type));
        result.setJavaType(JavaType.lookup(type));
        result.setSize(size);
        result.setScale(scale);
        result.setDefaultValue(defaultValue);
        result.setAllowNull("YES".equals(allowNull));
        result.setAutoIncrement("YES".equals(autoIncrement));

        return result;
    }

}
