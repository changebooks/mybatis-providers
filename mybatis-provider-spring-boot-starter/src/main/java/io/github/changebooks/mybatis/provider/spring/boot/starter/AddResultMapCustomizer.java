package io.github.changebooks.mybatis.provider.spring.boot.starter;

import io.github.changebooks.mybatis.provider.ResultMapUtils;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.mybatis.spring.boot.autoconfigure.SqlSessionFactoryBeanCustomizer;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Configuration;

/**
 * add ResultMap to Configuration
 *
 * @author changebooks@qq.com
 */
@Configuration
@AutoConfigureAfter(MybatisAutoConfiguration.class)
public class AddResultMapCustomizer implements SqlSessionFactoryBeanCustomizer {

    @Override
    public void customize(SqlSessionFactoryBean factoryBean) {
        ResultMapUtils.addResultMaps(ConfigurationUtils.getConfiguration(factoryBean));
    }

}
