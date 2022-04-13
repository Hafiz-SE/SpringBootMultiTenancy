package com.multitenancy.test;

import com.multitenancy.test.multitenancy.routing.DynamicTenantAwareRoutingSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@SpringBootApplication
@EnableScheduling
@EnableTransactionManagement
public class TestApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		new TestApplication()
				.configure(new SpringApplicationBuilder(TestApplication.class))
				.properties(getDefaultProperties())
				.run(args);
	}

	@Bean
	public DataSource dataSource() {
		return new DynamicTenantAwareRoutingSource("static/tenants.json");
	}

	private static Properties getDefaultProperties() {

		Properties defaultProperties = new Properties();

		// Set sane Spring Hibernate properties:
		defaultProperties.put("spring.jpa.show-sql", "true");
		defaultProperties.put("spring.jpa.hibernate.naming.physical-strategy", "org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl");
		defaultProperties.put("spring.datasource.initialize", "false");

		// Prevent JPA from trying to Auto Detect the Database:
		defaultProperties.put("spring.jpa.database", "postgresql");

		// Prevent Hibernate from Automatic Changes to the DDL Schema:
		defaultProperties.put("spring.jpa.hibernate.ddl-auto", "none");

		return defaultProperties;
	}
}
