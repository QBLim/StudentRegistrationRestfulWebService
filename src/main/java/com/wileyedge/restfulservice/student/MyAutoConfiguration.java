package com.wileyedge.restfulservice.student;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnResource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
@PropertySource("classpath:database.properties")
public class MyAutoConfiguration {

	@Autowired 
    private Environment env;
		
	@Bean
	@ConditionalOnMissingBean
	public DataSource dataSource() {
		System.out.println("inside dataSource() of MyAutoConfiguration");
	    DriverManagerDataSource dataSource = new DriverManagerDataSource();
	  
	    dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
	    dataSource.setUrl("jdbc:mysql://localhost:3306/std?createDatabaseIfNotExist=true");
	    dataSource.setUsername("root");
	    dataSource.setPassword("root1234");
	 
	    return dataSource;
	}
	
	@Bean(name = "entityManagerFactory")
	@ConditionalOnBean(name = "dataSource")  
	//include entityManagerFactory bean only if a dataSource bean is present. sequence matters.define dataSource bean prior. 
	@ConditionalOnMissingBean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		System.out.println("inside entityManagerFactory() of MyAutoConfiguration");
	    LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
	    em.setDataSource(dataSource());
	    em.setPackagesToScan("com.wileyedge.*");
	    em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
	    //if (additionalProperties() != null) {
	    System.out.println("going to call man...................");
	    em.setJpaProperties(additionalProperties());
	    //}
	    return em;
	}
	
	@Bean
	@ConditionalOnResource(resources = "classpath:database.properties") //include additionalProperties bean only if mysql.properties is present in classpath
	//@Conditional(HibernateCondition.class)
	public Properties additionalProperties() {
		System.out.println("inside additionalProperties() of MyAutoConfiguration" + env);
		Properties hibernateProperties = new Properties();
		hibernateProperties.setProperty("hibernate.hbm2ddl.auto",env.getProperty("mysql-hibernate.hbm2ddl.auto"));
		hibernateProperties.setProperty("hibernate.dialect", env.getProperty("mysql-hibernate.dialect"));
		hibernateProperties.setProperty("hibernate.show_sql", env.getProperty("mysql-hibernate.show_sql"));
		return hibernateProperties;
	}
	
}
