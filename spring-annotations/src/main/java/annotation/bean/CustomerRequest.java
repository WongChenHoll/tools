package annotation.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;

/**
 * 客户请求类。
 * 验证、分组校验
 *
 * @author WangChenHol
 * @date 2021-9-26 12:01
 **/
@Getter
@Setter
@ToString
public class CustomerRequest {

    @NotBlank(groups = {Apply.class, Update.class}, message = "客户姓名不能为空")
    private String custName; // 客户名

    @NotBlank(groups = {Update.class, Query.class}, message = "客户编号不能为空")
    private String custNo; // 客户编号

    @NotNull(message = "请选择客户出生日期", groups = {Apply.class, Update.class})
    @Past(message = "出生日期不正确", groups = {Apply.class, Update.class})
    private Date birthday; // 客户生日

    @Email(regexp = "[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?", message = "邮箱格式不正确", groups = {Apply.class, Update.class})
    private String email; // 邮箱

    @Valid
    private List<BankCard> cardList;

    @Getter
    @Setter
    @ToString
    public static class BankCard {
        @NotBlank(message = "银行卡号不能为空", groups = {Apply.class, Update.class})
        private String cardNo; // 银行卡号

        @NotNull(message = "请确定有限期")
        @Future(message = "有效期必须大于当前日期", groups = {Apply.class})
        private Date periodOfValidity; // 有效期

    }
}
