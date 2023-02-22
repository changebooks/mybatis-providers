# mybatis-provider

### Description
Object Relational Mapping

### pom.xml
```
<dependency>
  <groupId>io.github.changebooks</groupId>
  <artifactId>mybatis-provider</artifactId>
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

### POJO
```
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

    @Override
    public String toString() {
        return "{" +
                "\"id:\": " + getId() + ", " +
                "\"cityName\": \"" + getCityName() + "\"" +
                "}";
    }

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

### Provider
```
public class CityProvider extends BaseProvider<City> {
}
```

### Mapper
```
public interface CityMapper {

    @SelectProvider(type = CityProvider.class, method = "selectList")
    @ResultMap("BaseResultMap")
    List<City> selectList(String[] conditions, String[] orders, Long startRow, Integer pageSize);

    @SelectProvider(type = CityProvider.class, method = "selectCount")
    long selectCount(String[] conditions);

    @SelectProvider(type = CityProvider.class, method = "selectOne")
    @ResultMap("BaseResultMap")
    City selectOne(@Param("id") int id);

    @InsertProvider(type = CityProvider.class, method = "insertOne")
    int insertOne(City record);

    @UpdateProvider(type = CityProvider.class, method = "updateOne")
    int updateOne(City record);

    @DeleteProvider(type = CityProvider.class, method = "deleteOne")
    int deleteOne(@Param("id") int id);

}
```

### DataSourceConstants
```
public final class DataSourceConstants {

    public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String URL = "jdbc:mysql://localhost:3306/test?characterEncoding=utf-8&useInformationSchema=true";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "123456";

    public static final DataSource INSTANCE = new UnpooledDataSource(DRIVER, URL, USERNAME, PASSWORD);

    public static final TransactionFactory TRANSACTION_FACTORY = new JdbcTransactionFactory();

    public static final Environment ENVIRONMENT = new Environment.Builder("").
            dataSource(INSTANCE).
            transactionFactory(TRANSACTION_FACTORY).
            build();

    public static final Configuration CONFIGURATION = new Configuration(ENVIRONMENT);

    public static final SqlSessionFactory SESSION_FACTORY = new SqlSessionFactoryBuilder().build(CONFIGURATION);

    static {
        CONFIGURATION.setLogImpl(StdOutImpl.class);
        CONFIGURATION.addMapper(CityMapper.class);

        ResultMapUtils.addResultMaps(CONFIGURATION);
    }

}
```

### Query
```
SqlSession sqlSession = DataSourceConstants.SESSION_FACTORY.openSession(true);
CityMapper cityMapper = sqlSession.getMapper(CityMapper.class);
City record = cityMapper.selectOne(1);
System.out.println(record);
```
