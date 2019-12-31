/**
 * Created On : 16 Aug 2017
 */
package com.lk.project.x.config;

import java.io.IOException;
import java.util.Properties;

import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author virtualpathum
 * The Class SysConfig.
 */
@Configuration
//TODO:Need to fix
//@Import({ DatabaseProfile.class})
@EnableJpaRepositories(basePackages = {"com.lk.project.x.repo" })
@EnableTransactionManagement
public class SysConfig {
	
	/** The Constant PERSISTENCE_UNIT_NAME. */
	public static final String PERSISTENCE_UNIT_NAME = "PU_PROJECT-X";
	
	/** The Constant JPA_ENTITY_PACKAGES. */
	private static final String[] JPA_ENTITY_PACKAGES = { "com.lk.project.x.entity" };
	
	/** The Constant PERSISTENCE_PROPERTIES. */
	public static final String PERSISTENCE_PROPERTIES = "persistence.properties";
	
	/** The Constant jndiNamespace. */
	public static final String jndiNamespace = "java:comp/env/jdbc/project-x";

	

	@Bean
	@Primary
	public DataSource dataSource() {
	    DriverManagerDataSource dataSource = new DriverManagerDataSource();
	    dataSource.setDriverClassName("org.postgresql.Driver");

	    dataSource.setUrl("jdbc:postgresql://project-x.c8d8t9ylweam.ap-southeast-1.rds.amazonaws.com:5455/project-x");
	    dataSource.setUsername("postgres");
	    dataSource.setPassword("post-gres");

	    return dataSource;
	}
	
	@Bean
	public EntityManagerFactory entityManagerFactory() throws IOException, NamingException{
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();

		LocalContainerEntityManagerFactoryBean containerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		containerEntityManagerFactoryBean.setJpaVendorAdapter(vendorAdapter);
		containerEntityManagerFactoryBean.setPackagesToScan(SysConfig.JPA_ENTITY_PACKAGES);
		containerEntityManagerFactoryBean.setDataSource(dataSource());
		containerEntityManagerFactoryBean.setJpaProperties(getJpaProperties());
		containerEntityManagerFactoryBean.setPersistenceUnitName(SysConfig.PERSISTENCE_UNIT_NAME);
		containerEntityManagerFactoryBean.afterPropertiesSet();
		return containerEntityManagerFactoryBean.getObject();

	}


	
	@Bean
	public Properties getJpaProperties() throws IOException {
		ClassPathResource resource = new ClassPathResource(PERSISTENCE_PROPERTIES);
		return PropertiesLoaderUtils.loadProperties(resource);
	}
	
	@Bean
    public PlatformTransactionManager transactionManager() throws IOException, NamingException {
        JpaTransactionManager jpaTransactionManager  = new JpaTransactionManager();
        jpaTransactionManager.setDataSource(dataSource());
        jpaTransactionManager.setEntityManagerFactory(entityManagerFactory());
        
        return jpaTransactionManager;
    }
	
}
