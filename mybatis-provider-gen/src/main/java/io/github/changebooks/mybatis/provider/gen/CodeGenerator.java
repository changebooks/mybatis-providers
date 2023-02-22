package io.github.changebooks.mybatis.provider.gen;

import io.github.changebooks.mybatis.provider.gen.formatter.CodeFormatter;
import io.github.changebooks.mybatis.provider.gen.schema.Column;
import io.github.changebooks.mybatis.provider.gen.schema.Table;
import io.github.changebooks.mybatis.provider.gen.util.MetaDataUtils;
import io.github.changebooks.mybatis.provider.gen.util.StringUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Generate code
 *
 * @author changebooks@qq.com
 */
public class CodeGenerator {
    /**
     * 作者
     */
    private String author;

    /**
     * 生成代码
     *
     * @param conn      the {@link Connection} instance
     * @param tableName 表名
     * @return pojo code
     */
    public String genCode(Connection conn, String tableName) {
        Objects.requireNonNull(conn, "conn can't be null");
        Objects.requireNonNull(tableName, "tableName can't be null");

        try {
            Table table = MetaDataUtils.getTable(conn, tableName);
            return genCode(table);
        } catch (SQLException tr) {
            throw new RuntimeException(tr);
        }
    }

    /**
     * 生成代码
     *
     * @param table 表描述
     * @return pojo code
     */
    public String genCode(Table table) {
        Objects.requireNonNull(table, "table can't be null");

        List<Column> columns = table.getColumns();
        Objects.requireNonNull(columns, "columns can't be null");

        String code = formatField(columns) +
                StringUtils.NEW_LINE +
                formatMethod(columns);

        return formatImport(columns) +
                StringUtils.NEW_LINE +
                formatClass(table, code);
    }

    /**
     * 格式化导包代码
     *
     * @param columns 字段描述列表
     * @return import package.name
     */
    public String formatImport(List<Column> columns) {
        return StringUtils.join(CodeFormatter.formatImport(columns)) + StringUtils.NEW_LINE;
    }

    /**
     * 格式化类注释
     *
     * @param table 表描述
     * @param code  代码
     * @return ** class description * ** author name * @MybatisResultMap(table) public final class Name implements Serializable { ... }
     */
    public String formatClass(Table table, String code) {
        return CodeFormatter.formatClass(table, author, code);
    }

    /**
     * 格式化属性代码
     *
     * @param columns 字段描述列表
     * @return private Type name
     */
    public String formatField(List<Column> columns) {
        return StringUtils.join(CodeFormatter.formatField(columns));
    }

    /**
     * 格式化方法代码
     *
     * @param columns 字段描述列表
     * @return private Type getName() private Type setName(Type name)
     */
    public String formatMethod(List<Column> columns) {
        return StringUtils.join(CodeFormatter.formatMethod(columns));
    }

    public String getAuthor() {
        return author;
    }

    /**
     * Build a new {@link CodeGenerator}.
     */
    public static final class Builder {

        private static final String AUTHOR = "CodeGenerator";

        /**
         * @see CodeGenerator#author
         */
        private String author;

        /**
         * Create the {@link CodeGenerator} instance
         *
         * @return CodeGenerator
         */
        public CodeGenerator build() {
            CodeGenerator result = new CodeGenerator();

            result.author = Optional.ofNullable(author).orElse(AUTHOR);

            return result;
        }

        public Builder setAuthor(String author) {
            this.author = author;
            return this;
        }

    }

}
