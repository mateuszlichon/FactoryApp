package pl.lichon.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.validation.Validator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;


@Configuration
@ComponentScan(basePackages = { "com.lichon"})
@EnableWebMvc
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"com.lichon.repository"})
public class AppConfig extends WebMvcConfigurerAdapter {
		
	@Bean(name = "dataSource")
	public DriverManagerDataSource dataSource() {
	    DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
	    driverManagerDataSource.setDriverClassName("com.mysql.jdbc.Driver");
	    driverManagerDataSource.setUrl("jdbc:mysql://localhost:3306/factoryapp");
	    driverManagerDataSource.setUsername("root");
	    driverManagerDataSource.setPassword("coderslab");
		
	    String dbUrl = System.getenv("JDBC_DATABASE_URL");
		if(dbUrl!=null) {
			driverManagerDataSource.setDriverClassName("org.postgresql.Driver");
			driverManagerDataSource.setUrl(dbUrl);
			driverManagerDataSource.setUsername(System.getenv("JDBC_DATABASE_USERNAME"));
			driverManagerDataSource.setPassword(System.getenv("JDBC_DATABASE_PASSWORD"));
		}
	    return driverManagerDataSource;
	}
	
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		super.addResourceHandlers(registry);
		registry.addResourceHandler("/resources/**").addResourceLocations("/WEB-INF/resources/");
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Bean
	public LocalEntityManagerFactoryBean entityManagerFactory() {
		LocalEntityManagerFactoryBean emfb = new LocalEntityManagerFactoryBean();
		emfb.setPersistenceUnitName("factoryapp");
		String dbUrl = System.getenv("JDBC_DATABASE_URL");
		if(dbUrl!=null) {
			Properties jpaProperties = new Properties();
			jpaProperties.put("javax.persistence.jdbc.url", System.getenv("JDBC_DATABASE_URL"));
			jpaProperties.put("javax.persistence.jdbc.user", System.getenv("JDBC_DATABASE_USERNAME"));
			jpaProperties.put("javax.persistence.jdbc.password", System.getenv("JDBC_DATABASE_PASSWORD"));
			jpaProperties.put("javax.persistence.jdbc.driver", "org.postgresql.Driver");
			jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
			emfb.setJpaProperties(jpaProperties);
			emfb.afterPropertiesSet();
		}
		return emfb;
	}

	@Bean
	public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
		JpaTransactionManager tm = new JpaTransactionManager(emf);
		return tm;
	}
	
	@Bean
	public Validator validator() {
		return new LocalValidatorFactoryBean();
	}

}
