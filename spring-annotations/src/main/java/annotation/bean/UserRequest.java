package annotation.bean;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;

/**
 * 用户请求类。
 * 验证javax validation中的参数校验注解
 *
 * @author WangChenHol
 * @date 2021-9-23 14:07
 **/
@Getter
@Setter
@ToString
public class UserRequest extends RequestBean {
    private static final long serialVersionUID = -8960619820103069532L;

    @NotNull(message = "用户名不能为空")
    private String userName;

    @NotBlank(message = "密码不能为空")
    private String password;

    @NotEmpty(message = "验证码不能为空")
    private String verificationCode;

    @AssertTrue(message = "未成年人禁止注册")
    private boolean whetherAdult;

    @AssertFalse(message = "你已注册过")
    private boolean unregistered;

    @Min(value = 18, message = "年龄不能小于18岁")
    @Max(value = 100, message = "年龄不能超过100岁")
    private Integer age;

    @Size(min = 2, max = 10, message = "数量不对")
    private List<Integer> number;

    @Digits(integer = 4, fraction = 2, message = "价格不合法")
    private double price;

    @Past(message = "生日不能大于当前日期")
    private Date birthday;

    @Future(message = "合同到期日必须大于当天")
    private Date contractDeadline;

}
