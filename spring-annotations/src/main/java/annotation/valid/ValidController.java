package annotation.valid;

import com.jason.base.response.BaseResponse;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;

/**
 * 参数校验相关
 *
 * @author WangChenHol
 * @date 2021-9-23 13:59
 **/
@RequestMapping("/valid")
@RestController
public class ValidController {

    // 新加客户信息
    @PostMapping("add")
    public BaseResponse<String> add(@RequestBody @Valid UserRequest request) {
        return BaseResponse.success("成功");
    }

    // 申请银行卡
    @PostMapping("applyCard")
    public BaseResponse<String> applyCard(@RequestBody @Validated(Apply.class) CustomerRequest request) {
        request.setCustNo("88888888");
        return BaseResponse.success("申请成功");
    }

    // 修改银行卡信息
    @PostMapping("updateCard")
    public BaseResponse<String> updateCard(@RequestBody @Validated(Update.class) CustomerRequest request) {
        return BaseResponse.success("修改成功");
    }

    // 查询客户信息
    @PostMapping("queryCard")
    public BaseResponse<CustomerRequest> queryCard(@RequestBody @Validated({Query.class}) CustomerRequest request) {
        CustomerRequest cust = new CustomerRequest();
        cust.setCustName("zhangsan");
        cust.setCustNo(request.getCustNo());
        cust.setEmail("123@qq.com");
        cust.setBirthday(new Date());

        CustomerRequest.BankCard card1 = new CustomerRequest.BankCard();
        card1.setCardNo("123456");
        card1.setPeriodOfValidity(new Date());
        CustomerRequest.BankCard card2 = new CustomerRequest.BankCard();
        card2.setCardNo("123456");
        card2.setPeriodOfValidity(new Date());
        ArrayList<CustomerRequest.BankCard> cards = new ArrayList<>();
        cards.add(card1);
        cards.add(card2);
        cust.setCardList(cards);
        return BaseResponse.success(cust);
    }
}
