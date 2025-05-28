package com.bfs.crud.rest.dao.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.config.Profiles;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(entityManagerFactoryRef = "secondaryEntityManagerFactory",
						transactionManagerRef = "secondaryTransactionManager", 
						basePackages = "com.bfs.crud.rest.dao")

public class DbConfig {
	
	private static Logger LOGGER = LoggerFactory.getLogger(DbConfig.class);

	@Bean
	@Primary
	@ConfigurationProperties(prefix = "spring.datasource.mysql")
	public DataSource secondaryDataSource() {
		LOGGER.info("profile dev with h2 database is running");
		return DataSourceBuilder.create().build();
	}
	
	@Bean
	@Profile("test")
	public DataSource dataSource() {
		try {
			LOGGER.info("profile test with h2 database is running");
			var dbBuilder = new EmbeddedDatabaseBuilder();
			return dbBuilder.setType(EmbeddedDatabaseType.H2)
					.addScripts("classpath:h2/schema.sql", "classpath:h2/test-data.sql").build();
		} catch (Exception e) {
			LOGGER.error("Embedded DataSource bean cannot be created!", e);
			return null;
		}
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean secondaryEntityManagerFactory(EntityManagerFactoryBuilder builder) {
		return builder.dataSource(secondaryDataSource())
				      .packages("com.bfs.crud.rest.model")
				      .build();
	}

	@Bean
	public PlatformTransactionManager secondaryTransactionManager(
			@Qualifier("secondaryEntityManagerFactory") LocalContainerEntityManagerFactoryBean entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory.getObject());
	}
}