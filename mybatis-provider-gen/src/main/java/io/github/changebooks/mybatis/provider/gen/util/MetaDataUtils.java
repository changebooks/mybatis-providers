package io.github.changebooks.mybatis.provider.gen.util;

import io.github.changebooks.mybatis.provider.gen.schema.Column;
import io.github.changebooks.mybatis.provider.gen.schema.Table;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Database MetaData utils
 *
 * @author changebooks@qq.com
 */
public final class MetaDataUtils {

    private MetaDataUtils() {
    }

    /**
     * 获取表描述
     *
     * @param conn      the {@link Connection} instance
     * @param tableName 表名
     * @return 表描述
     * @throws SQLException if a database access error occurs
     *                      or
     *                      this method is called on a closed connection
     */
    public static Table getTable(Connection conn, String tableName) throws SQLException {
        Objects.requireNonNull(conn, "conn can't be null");

        String dbName = conn.getCatalog();
        DatabaseMetaData metaData = conn.getMetaData();

        return getTable(metaData, dbName, tableName);
    }

    /**
     * 获取表描述
     *
     * @param metaData  the {@link DatabaseMetaData} instance
     * @param dbName    库名
     * @param tableName 表名
     * @return 表描述
     * @throws SQLException if the columnLabel is not valid;
     *                      if a database access error occurs
     *                      or
     *                      this method is called on a closed result set
     */
    public static Table getTable(DatabaseMetaData metaData, String dbName, String tableName) throws SQLException {
        Objects.requireNonNull(metaData, "metaData can't be null");

        Table result = null;

        ResultSet rs = metaData.getTables(dbName, null, tableName, new String[]{"TABLE"});
        if (rs == null) {
            return null;
        }

        try {
            if (rs.next()) {
                result = ResultSetUtils.getTable(rs);

                Set<String> primaryKeys = getPrimaryKeys(metaData, dbName, tableName);
                List<Column> columns = getColumns(metaData, dbName, tableName);

                if (primaryKeys != null && columns != null) {
                    columns = columns.stream().
                            peek(c -> c.setPrimaryKey(primaryKeys.contains(c.getName()))).
                            collect(Collectors.toList());
                }

                result.setColumns(columns);
            }
        } finally {
            rs.close();
        }

        return result;
    }

    /**
     * 获取字段列表
     *
     * @param metaData  the {@link DatabaseMetaData} instance
     * @param dbName    库名
     * @param tableName 表名
     * @return 字段列表
     * @throws SQLException if the columnLabel is not valid;
     *                      if a database access error occurs
     *                      or
     *                      this method is called on a closed result set
     */
    public static List<Column> getColumns(DatabaseMetaData metaData, String dbName, String tableName) throws SQLException {
        Objects.requireNonNull(metaData, "metaData can't be null");

        List<Column> result = new ArrayList<>();

        ResultSet rs = metaData.getColumns(dbName, null, tableName, null);
        if (rs == null) {
            return null;
        }

        try {
            while (rs.next()) {
                Column column = ResultSetUtils.getColumn(rs);
                result.add(column);
            }
        } finally {
            rs.close();
        }

        return result;
    }

    /**
     * 获取主键列表
     *
     * @param metaData  the {@link DatabaseMetaData} instance
     * @param dbName    库名
     * @param tableName 表名
     * @return 主键列表
     * @throws SQLException if the columnLabel is not valid;
     *                      if a database access error occurs
     *                      or
     *                      this method is called on a closed result set
     */
    public static Set<String> getPrimaryKeys(DatabaseMetaData metaData, String dbName, String tableName) throws SQLException {
        Objects.requireNonNull(metaData, "metaData can't be null");

        Set<String> result = new HashSet<>();

        ResultSet rs = metaData.getPrimaryKeys(dbName, null, tableName);
        if (rs == null) {
            return null;
        }

        try {
            while (rs.next()) {
                String columnName = rs.getString("COLUMN_NAME");
                result.add(columnName);
            }
        } finally {
            rs.close();
        }

        return result;
    }

}
