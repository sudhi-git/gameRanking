package com.sudhi.gameranking.config;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@Profile({ "default" })
@EnableTransactionManagement
@EnableJpaRepositories(
        basePackages = "com.sudhi.gameranking.model",
        entityManagerFactoryRef = "emf",
        transactionManagerRef = "transactionManager"
)
public class DBConfig {
	@Bean
	public JpaVendorAdapter eclipseLink() {
		return new EclipseLinkJpaVendorAdapter();
	}

	@Primary
	@Bean
	public DataSource ds() {
		DriverManagerDataSource gameRankingDs = new DriverManagerDataSource();
		gameRankingDs.setDriverClassName("org.h2.Driver");
		gameRankingDs.setUrl("jdbc:h2:~/gameRanking1;DB_CLOSE_DELAY=-1");
		gameRankingDs.setUsername("sa");
		gameRankingDs.setPassword("");
		return gameRankingDs;
	}

	/**
	 * Set the PersistenceUnitManager to use for obtaining the JPA persistence unit
	 * that this FactoryBean is supposed to build an EntityManagerFactory
	 * 
	 * @param adapter             plug in vendor-specific behavior into Spring's
	 *                            EntityManagerFactory creators
	 * @param ds                  factory for connections to the physical data
	 *                            source that this DataSource object represents
	 * @param persistenceUnitName logical grouping of user defined persistable
	 *                            classes (entity classes, embeddable classes and
	 *                            mapped superclasses) with related settings,
	 *                            required by JPA.
	 * @return Entity Manager Factory Instance
	 */

	@Primary
	@Bean
	public LocalContainerEntityManagerFactoryBean emf(JpaVendorAdapter adapter, DataSource ds,
			@Value("${puName}") String persistenceUnitName) {
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setJpaVendorAdapter(adapter);
		factory.setDataSource(ds);
		factory.setPersistenceUnitName(persistenceUnitName);
		factory.setPackagesToScan("com.sap.lbn.retention.model.tenantaware");
		factory.setJpaPropertyMap(getJpaProperties());
		return factory;
	}

    @Bean
    public EntityManager entityManager(@Qualifier("emf") EntityManagerFactory emf) {
        return emf.createEntityManager();
    }
    
	/**
	 * This is the central interface in Spring's transaction infrastructure. We can
	 * use this directly, but it is not primarily meant as API: Typically,
	 * applications will work with either TransactionTemplate or declarative
	 * transaction demarcation through AOP.
	 * 
	 * @param emf used to interact with the entity manager factory for the
	 *            persistence unit.
	 * @return JPA Transaction Manager instance
	 */

	@Primary
	@Bean
	public PlatformTransactionManager transactionManager(@Qualifier("emf") EntityManagerFactory emf) {
		return new JpaTransactionManager(emf);
	}

	private Map<String, String> getJpaProperties() {
		Map<String, String> map = new HashMap<>();
		map.put("eclipselink.ddl-generation", "create-or-extend-tables");
		map.put("eclipselink.ddl-generation.output-mode", "both");
		map.put("eclipselink.cache.shared.default", "false");
		return map;
	}
}
