package com.aldrich.portal.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.orm.hibernate5.HibernateTransactionManager;

import com.mongodb.Mongo;

@Configuration
@ComponentScan(basePackages={"com.aldrich.portal.dao"})
public class PersistanceConfigurator
{
	@Bean
	public DataSource dataSource()
	{
		DriverManagerDataSource ds = new DriverManagerDataSource();        
		ds.setDriverClassName("com.mysql.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3306/pase");
		ds.setUsername("root");
		ds.setPassword("sa123");        
		return ds;
	}

	@Bean(name="txManager")	
	public HibernateTransactionManager transactionManager(SessionFactory sf)
	{
		HibernateTransactionManager htm = new HibernateTransactionManager();
		htm.setSessionFactory(sf);
		return htm;
	}

	@Bean(name="sessionFactory")
	public LocalSessionFactoryBean getSessionFactory()
	{
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setPackagesToScan(new String[] { "com.aldrich.portal.entity"});
		sessionFactory.setHibernateProperties(getHibernateProperties());
		return sessionFactory;
	}

	@Bean
	public Properties getHibernateProperties()
	{
		Properties properties = new Properties();
		properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		properties.put("hibernate.show_sql", "false");
		properties.put("hibernate.connection.release_mode", "after_statement");
		properties.put("hibernate.connection.autocommit", "true");
		
		//properties.put("", "");
		return properties;
	}	


	/*@Override
	protected String getDatabaseName() 
	{
		return "pase";
	}

	@Override
	public Mongo mongo() throws Exception 
	{
		return new MongoClient("127.0.0.1", 27017);
	}*/
	
	
	
	@SuppressWarnings("deprecation")
	public @Bean
	  MongoDbFactory mongoDbFactory() throws Exception {
		return new SimpleMongoDbFactory(new Mongo(),"pase");
	  }

	  @SuppressWarnings("deprecation")
	public @Bean
	  MongoTemplate mongoTemplate() throws Exception {
			
		//remove _class
		MappingMongoConverter converter = 
			new MappingMongoConverter(mongoDbFactory(), new MongoMappingContext());
		converter.setTypeMapper(new DefaultMongoTypeMapper(null));
			
		MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory(), converter);
					
		return mongoTemplate;
	
	  }
}
