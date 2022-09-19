package book.manager.config;

import com.zaxxer.hikari.HikariDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.transaction.TransactionManager;

import javax.sql.DataSource;

@ComponentScans({
        @ComponentScan("book.manager.service")
})
@MapperScan("book.manager.mapper")
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true) //开启用注解管理security
public class RootConfiguration {

//    1.提供数据源
    @Bean
    public DataSource dataSource(){
//        PooledDataSource dataSource = new PooledDataSource();//使用spring提供的数据源  setUrl  setDriver ...
        HikariDataSource dataSource = new HikariDataSource(); //使用第三方HikariCP数据源 setJdbcUrl setDriverClassName
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/book_manager");
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUsername("root");
        dataSource.setPassword("1234");
        return dataSource;
    }
    //  2.增加事务  默认事务  ->可重复读
    @Bean
    public TransactionManager transactionManager(@Autowired DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }

    //   3. 注册 SqlSessionFactoryBean 为bean
    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean(@Autowired DataSource source){
        SqlSessionFactoryBean bean =  new SqlSessionFactoryBean();
        bean.setDataSource(source);
        return bean;
    }
}
