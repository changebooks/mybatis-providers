# mybatis-provider-gen

### Description
Generate POJO

### pom.xml
```
<dependency>
  <groupId>io.github.changebooks</groupId>
  <artifactId>mybatis-provider-gen</artifactId>
  <version>1.0.1</version>
</dependency>
```

### Schema
```
CREATE TABLE city
(
    id        int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
    city_name varchar(255)     NOT NULL DEFAULT '' COMMENT 'City Name',
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
```

### DataSourceConstants
```
public final class DataSourceConstants {

    public static final String URL = "jdbc:mysql://localhost:3306/test?characterEncoding=utf-8&useInformationSchema=true";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "123456";

}
```

### Generate
```
String author = "changebooks@qq.com";
CodeGenerator generator = new CodeGenerator.Builder().setAuthor(author).build();

Connection conn = DriverManager.
                getConnection(DataSourceConstants.URL, DataSourceConstants.USERNAME, DataSourceConstants.PASSWORD);

String code = generator.genCode(conn, "city");
System.out.println(code);
```

### Generated's Code
```
/**
 * City
 *
 * @author changebooks@qq.com
 */
@MybatisResultMap(table = "city")
public final class City implements Serializable {
    /**
     * ID
     */
    @MybatisResult(column = "id", jdbcType = JdbcType.INTEGER, id = true, autoIncrement = true)
    private Integer id;

    /**
     * City Name
     */
    @MybatisResult(column = "city_name", jdbcType = JdbcType.VARCHAR, id = false, autoIncrement = false)
    private String cityName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

}
```
