package annotation.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 请求基类
 *
 * @author WangChenHol
 * @date 2021-9-23 14:01
 **/
public class RequestBean implements Serializable {
    private static final long serialVersionUID = -399332463744053005L;
    private final Date requestTime = new Date();
}
