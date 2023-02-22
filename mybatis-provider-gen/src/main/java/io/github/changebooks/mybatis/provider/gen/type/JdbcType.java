package io.github.changebooks.mybatis.provider.gen.type;

import java.sql.Types;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Sql Type mapping Jdbc Type
 *
 * @author changebooks@qq.com
 */
public final class JdbcType {

    public static final Map<Integer, String> STANDARD_MAPPING = new ConcurrentHashMap<>(64);

    static {
        STANDARD_MAPPING.put(Types.ARRAY, "ARRAY");
        STANDARD_MAPPING.put(Types.BIT, "BIT");
        STANDARD_MAPPING.put(Types.TINYINT, "TINYINT");
        STANDARD_MAPPING.put(Types.SMALLINT, "SMALLINT");
        STANDARD_MAPPING.put(Types.INTEGER, "INTEGER");
        STANDARD_MAPPING.put(Types.BIGINT, "BIGINT");
        STANDARD_MAPPING.put(Types.FLOAT, "FLOAT");
        STANDARD_MAPPING.put(Types.REAL, "REAL");
        STANDARD_MAPPING.put(Types.DOUBLE, "DOUBLE");
        STANDARD_MAPPING.put(Types.NUMERIC, "NUMERIC");
        STANDARD_MAPPING.put(Types.DECIMAL, "DECIMAL");
        STANDARD_MAPPING.put(Types.CHAR, "CHAR");
        STANDARD_MAPPING.put(Types.VARCHAR, "VARCHAR");
        STANDARD_MAPPING.put(Types.LONGVARCHAR, "LONGVARCHAR");
        STANDARD_MAPPING.put(Types.DATE, "DATE");
        STANDARD_MAPPING.put(Types.TIME, "TIME");
        STANDARD_MAPPING.put(Types.TIMESTAMP, "TIMESTAMP");
        STANDARD_MAPPING.put(Types.BINARY, "BINARY");
        STANDARD_MAPPING.put(Types.VARBINARY, "VARBINARY");
        STANDARD_MAPPING.put(Types.LONGVARBINARY, "LONGVARBINARY");
        STANDARD_MAPPING.put(Types.NULL, "NULL");
        STANDARD_MAPPING.put(Types.OTHER, "OTHER");
        STANDARD_MAPPING.put(Types.BLOB, "BLOB");
        STANDARD_MAPPING.put(Types.CLOB, "CLOB");
        STANDARD_MAPPING.put(Types.BOOLEAN, "BOOLEAN");
        // Oracle
        STANDARD_MAPPING.put(-10, "CURSOR");
        STANDARD_MAPPING.put(Integer.MIN_VALUE + 1000, "UNDEFINED");
        // JDK6
        STANDARD_MAPPING.put(Types.NVARCHAR, "NVARCHAR");
        // JDK6
        STANDARD_MAPPING.put(Types.NCHAR, "NCHAR");
        // JDK6
        STANDARD_MAPPING.put(Types.NCLOB, "NCLOB");
        STANDARD_MAPPING.put(Types.STRUCT, "STRUCT");
        STANDARD_MAPPING.put(Types.JAVA_OBJECT, "JAVA_OBJECT");
        STANDARD_MAPPING.put(Types.DISTINCT, "DISTINCT");
        STANDARD_MAPPING.put(Types.REF, "REF");
        STANDARD_MAPPING.put(Types.DATALINK, "DATALINK");
        // JDK6
        STANDARD_MAPPING.put(Types.ROWID, "ROWID");
        // JDK6
        STANDARD_MAPPING.put(Types.LONGNVARCHAR, "LONGNVARCHAR");
        // JDK6
        STANDARD_MAPPING.put(Types.SQLXML, "SQLXML");
        // SQL Server 2008
        STANDARD_MAPPING.put(-155, "DATETIMEOFFSET");
        // JDBC 4.2 JDK8
        STANDARD_MAPPING.put(Types.TIME_WITH_TIMEZONE, "TIME_WITH_TIMEZONE");
        // JDBC 4.2 JDK8
        STANDARD_MAPPING.put(Types.TIMESTAMP_WITH_TIMEZONE, "TIMESTAMP_WITH_TIMEZONE");
    }

    private JdbcType() {
    }

    public static String lookup(Integer type) {
        return STANDARD_MAPPING.getOrDefault(type, "UNDEFINED");
    }

}
