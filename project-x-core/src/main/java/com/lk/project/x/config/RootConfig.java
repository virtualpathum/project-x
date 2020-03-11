/**
 * Created On : 16 Aug 2017
 */
package com.lk.project.x.config;

import java.util.TimeZone;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author virtualpathum
 * The Class RootConfig.
 */
@Configuration
@Import({ SysConfig.class})
@ComponentScan(basePackages = { 
	    "com.lk.project.x.mapper",
	    "com.lk.project.x.service" })
public class RootConfig {
	
	/**
	 * Jackson object mapper.
			 *
			 * @return the object mapper
			 */
@Bean
public ObjectMapper jacksonObjectMapper() {
		Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();

		builder.timeZone(TimeZone.getDefault());
		builder.serializationInclusion(Include.NON_NULL);
		builder.featuresToDisable(
		DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		builder.featuresToEnable(
		DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
		builder.defaultViewInclusion(Boolean.TRUE);

		return builder.build();
		}

		}
