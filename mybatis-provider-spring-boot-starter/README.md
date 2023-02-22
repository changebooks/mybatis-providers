# mybatis-provider-spring-boot-starter

### Description
Add ResultMap to Mybatis Configuration.  
&#064;EnableMybatisProvider

### pom.xml
```
<dependency>
  <groupId>io.github.changebooks</groupId>
  <artifactId>mybatis-provider-spring-boot-starter</artifactId>
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

### Application
```
@SpringBootApplication
@EnableMybatisProvider
@MapperScan(basePackages = {"***.sample.repository.mapper"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
```

### Controller
```
@RequestMapping("city")
@RestController
public class CityController {

    @Resource
    private CityMapper cityMapper;

    @GetMapping(value = "/select-list")
    public List<City> selectList(@RequestParam("city_name") String cityName) {
        String[] conditions = {"city_name LIKE '%" + cityName + "%'"};
        String[] orders = {"city_name ASC", "id DESC"};
        Long startRow = 0L;
        Integer pageSize = 2;

        return cityMapper.selectList(conditions, orders, startRow, pageSize);
    }

    @GetMapping(value = "/select-count")
    public long selectCount(@RequestParam("city_name") String cityName) {
        String[] conditions = {"city_name LIKE '%" + cityName + "%'"};

        return cityMapper.selectCount(conditions);
    }

    @GetMapping(value = "/select-one")
    public City selectOne(@RequestParam("id") Integer id) {
        return cityMapper.selectOne(id);
    }

    @PostMapping(value = "/insert-one")
    public int insertOne(@RequestParam("city_name") String cityName) {
        City record = new City();
        record.setCityName(cityName);

        return cityMapper.insertOne(record);
    }

    @PostMapping(value = "/update-one")
    public int updateOne(@RequestParam("city_name") String cityName) {
        City record = new City();
        record.setId(1);
        record.setCityName(cityName);

        return cityMapper.updateOne(record);
    }

    @DeleteMapping(value = "/delete-one")
    public int deleteOne(@RequestParam("id") Integer id) {
        return cityMapper.deleteOne(id);
    }

}
```
