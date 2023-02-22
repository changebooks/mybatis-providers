package io.github.changebooks.mybatis.provider.gen.type;

import java.math.BigDecimal;
import java.net.URL;
import java.sql.Types;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Sql Type mapping Java Type
 *
 * @author changebooks@qq.com
 */
public final class JavaType {

    public static final Map<Integer, Class<?>> STANDARD_MAPPING = new ConcurrentHashMap<>(64);

    static {
        STANDARD_MAPPING.put(Types.TINYINT, Byte.class);
        STANDARD_MAPPING.put(Types.SMALLINT, Short.class);
        STANDARD_MAPPING.put(Types.INTEGER, Integer.class);
        STANDARD_MAPPING.put(Types.BIGINT, Long.class);
        STANDARD_MAPPING.put(Types.FLOAT, Float.class);
        STANDARD_MAPPING.put(Types.REAL, Float.class);
        STANDARD_MAPPING.put(Types.DOUBLE, Double.class);
        STANDARD_MAPPING.put(Types.NUMERIC, BigDecimal.class);
        STANDARD_MAPPING.put(Types.DECIMAL, BigDecimal.class);
        STANDARD_MAPPING.put(Types.CHAR, String.class);
        STANDARD_MAPPING.put(Types.VARCHAR, String.class);
        STANDARD_MAPPING.put(Types.LONGVARCHAR, String.class);
        STANDARD_MAPPING.put(Types.DATE, Date.class);
        STANDARD_MAPPING.put(Types.TIME, Date.class);
        STANDARD_MAPPING.put(Types.TIMESTAMP, Date.class);
        STANDARD_MAPPING.put(Types.VARBINARY, Byte[].class);
        STANDARD_MAPPING.put(Types.LONGVARBINARY, Byte[].class);
        STANDARD_MAPPING.put(Types.NULL, Object.class);
        STANDARD_MAPPING.put(Types.BOOLEAN, Boolean.class);
        STANDARD_MAPPING.put(Types.NVARCHAR, String.class);
        STANDARD_MAPPING.put(Types.NCHAR, String.class);
        STANDARD_MAPPING.put(Types.JAVA_OBJECT, Object.class);
        STANDARD_MAPPING.put(Types.DATALINK, URL.class);
        STANDARD_MAPPING.put(Types.LONGNVARCHAR, String.class);
        STANDARD_MAPPING.put(Types.TIME_WITH_TIMEZONE, OffsetTime.class);
        STANDARD_MAPPING.put(Types.TIMESTAMP_WITH_TIMEZONE, OffsetDateTime.class);
    }

    private JavaType() {
    }

    public static Class<?> lookup(Integer type) {
        return STANDARD_MAPPING.getOrDefault(type, Object.class);
    }

}
