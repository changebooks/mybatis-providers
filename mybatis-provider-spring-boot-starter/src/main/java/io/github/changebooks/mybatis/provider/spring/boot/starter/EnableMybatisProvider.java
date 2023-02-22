package io.github.changebooks.mybatis.provider.spring.boot.starter;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * Enable mybatis-provider-spring-boot-starter
 *
 * @author changebooks@qq.com
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import(MybatisProviderConfiguration.class)
public @interface EnableMybatisProvider {
}
