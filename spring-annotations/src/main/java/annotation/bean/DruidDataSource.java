package annotation.bean;

/**
 * @author WangChenHol
 * @date 2021-9-22 17:39
 **/
public class DruidDataSource {

    private String url;
    private String username;
    private String password;
    private String driverClassName; // 自动将driver-class-name按照驼峰命名的规则转换

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

    @Override
    public String toString() {
        return "DruidDataSource{" +
                "\r\nurl='" + url + '\'' +
                ", \nusername='" + username + '\'' +
                ", \npassword='" + password + '\'' +
                ", \ndriverClassName='" + driverClassName + '\'' +
                '}';
    }
}
