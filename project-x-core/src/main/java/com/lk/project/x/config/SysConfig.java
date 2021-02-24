/**
 * Created On : 16 Aug 2017
 */
package com.lk.project.x.config;

import java.io.IOException;
import java.util.Properties;

import javax.inject.Inject;
import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/**
 * @author virtualpathum
 * The Class SysConfig.
 */
@Configuration
//TODO:Need to fix
//@Import({ DatabaseProfile.class})
@EnableJpaRepositories(basePackages = {"com.lk.project.x.repo" })
@PropertySource({ "classpath:persistence.properties" })
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

	//@Value("${spring.datasource.driverClassName}")
	//String driverClass;

	@Bean
	@Primary
	public DataSource dataSource() {
	    DriverManagerDataSource dataSource = new DriverManagerDataSource();
       // System.out.println("///// Driver : " + driverClass);
		//dataSource.setDriverClassName(driverClass);
		//dataSource.setUsername(env.getProperty("spring.datasource.username"));
		//dataSource.setUsername(env.getProperty("spring.datasource.password"));
	    //dataSource.setUrl(env.getProperty("spring.datasource.url"));


		dataSource.setDriverClassName("org.h2.Driver");
		dataSource.setUrl("jdbc:h2:mem:projectx;DB_CLOSE_DELAY=-1");
		dataSource.setUsername("sa");
		dataSource.setPassword("sa");



		//dataSource.setDriverClassName("org.postgresql.Driver");
		//dataSource.setUrl("jdbc:postgresql://ec2-13-229-66-227.ap-southeast-1.compute.amazonaws.com:5432/projectx");
		//dataSource.setUrl("jdbc:postgresql://172.17.0.2:5432/projectx");

	    //dataSource.setPassword("post-gres");

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
	public JavaMailSender getJavaMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(587);

		mailSender.setUsername("virtualpathum@gmail.com");
		mailSender.setPassword("cjnvzfnbyuioakte");

		Properties props = mailSender.getJavaMailProperties();
		props.put("mail.transport.protocol", "smtp");
		//props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.debug", "true");

		return mailSender;
	}

	/*@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource
				= new ReloadableResourceBundleMessageSource();

		messageSource.setBasename("classpath:messages");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}

	@Bean
	public LocalValidatorFactoryBean getValidator() {
		LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
		bean.setValidationMessageSource(messageSource());
		return bean;
	}*/

	
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
