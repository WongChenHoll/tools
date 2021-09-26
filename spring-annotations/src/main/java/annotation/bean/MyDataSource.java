package annotation.bean;

/**
 * 自定义数据源类
 *
 * @author WangChenHol
 * @date 2021-9-17 15:10
 **/
public class MyDataSource extends TestDataSource{

    public String dataSourceName;
    public String url;

    public MyDataSource() {
    }

    public MyDataSource(String dataSourceName, String url) {
        this.dataSourceName = dataSourceName;
        this.url = url;
    }

    @Override
    public String toString() {
        return "MyDataSource{" +
                "dataSourceName='" + dataSourceName + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
