package annotation.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import java.util.Date;

/**
 * 客户请求类。
 * 验证Hiberate Validator中的参数校验注解
 *
 * @author WangChenHol
 * @date 2021-9-26 12:01
 **/
@Getter
@Setter
@ToString
public class CustomerRequest {

    @Email
    private String custName;

    private String custNo;

    private Date birthday;
}
