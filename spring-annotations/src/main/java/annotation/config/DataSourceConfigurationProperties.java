package annotation.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 使用ConfigurationProperties注解，类中的成员变量一定要有setter方法
 *
 * @author WangChenHol
 * @date 2021-9-22 16:27
 **/
@ConfigurationProperties(prefix = "spring.mydatasource")
@Component
public class DataSourceConfigurationProperties {

    private String url;
    private String username;
    private String password;
    private String driverClassName; // 自动将driver-class-name按照驼峰命名的规则转换
    private String type;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "DataSourceConfigurationProperties{" +
                "\nurl='" + url + '\'' +
                ", \nusername='" + username + '\'' +
                ", \npassword='" + password + '\'' +
                ", \ndriverClassName='" + driverClassName + '\'' +
                ", \ntype='" + type + '\'' +
                '}';
    }
}
