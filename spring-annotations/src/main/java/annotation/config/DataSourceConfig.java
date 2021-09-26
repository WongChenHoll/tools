package annotation.config;

import annotation.bean.MyDataSource;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;


/**
 * @author WangChenHol
 * @date 2021-9-17 15:08
 **/
@SpringBootConfiguration
public class DataSourceConfig {

    @Bean
    public MyDataSource mysqlDataSource(){
        return new MyDataSource("Mysql","jdbc:mysql://localhost:3306/testDatabase");
    }

    @Bean
    public MyDataSource oracleDataSource(){
        return new MyDataSource("Oracle","jdbc:oracle:thin:@localhost:1521:orcl");
    }
}
