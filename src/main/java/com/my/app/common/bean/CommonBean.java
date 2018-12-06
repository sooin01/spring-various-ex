package com.my.app.common.bean;

import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.stereotype.Component;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Component
public class CommonBean implements BeanDefinitionRegistryPostProcessor {

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		System.out.println("bean factory!");
	}

	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
		System.out.println("bean definition registry!");

		Properties properties = new Properties();
		properties.put("driverClassName", "org.mariadb.jdbc.Driver");
		properties.put("jdbcUrl", "jdbc:mariadb://localhost:3306/test");
		properties.put("username", "test");
		properties.put("password", "admin123");
		HikariConfig config = new HikariConfig(properties);
		config.setPoolName("testPool");

		BeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition(HikariDataSource.class)
				.addConstructorArgValue(config).getBeanDefinition();
		registry.registerBeanDefinition("dataSource2", beanDefinition);
	}

}
