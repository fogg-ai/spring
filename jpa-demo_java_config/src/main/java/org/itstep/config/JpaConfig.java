package org.itstep.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = "org.itstep")
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "org.itstep")
public class JpaConfig {
    @Bean
    public DataSource getDataSourceDev(){
        return new EmbeddedDatabaseBuilder()
                .addScript("data.sql")
                .setType(EmbeddedDatabaseType.H2)
                .build();
    }
    @Bean("entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean getEntityManagerFactory(DataSource dataSource){
        LocalContainerEntityManagerFactoryBean localContainer = new LocalContainerEntityManagerFactoryBean();
        localContainer.setDataSource(dataSource);
        localContainer.setPackagesToScan("org.itstep");
        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setGenerateDdl(true);
        jpaVendorAdapter.setShowSql(true);
        localContainer.setJpaVendorAdapter(jpaVendorAdapter);
        Properties props = new Properties();
        props.setProperty("format_sql","true");
        localContainer.setJpaProperties(props);

        return localContainer;
    }

    @Bean("transactionManager")
    public JpaTransactionManager getJpaTransactManager(EntityManagerFactory entityManagerFactory){
        return new JpaTransactionManager(entityManagerFactory);

    }

}
