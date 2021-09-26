package annotation.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author WangChenHol
 * @date 2021-9-23 11:09
 **/
@PropertySource("classpath:application-test.properties")
@Component
public class TestPropertySource {

    @Value("${project-name}")
    private String projectName;
    @Value("${test.url}")
    private String url;
    @Value("${test.response.time}")
    private int time;

    @Override
    public String toString() {
        return "TestPropertySource{" +
                "projectName='" + projectName + '\'' +
                ", url='" + url + '\'' +
                ", time=" + time +
                '}';
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
