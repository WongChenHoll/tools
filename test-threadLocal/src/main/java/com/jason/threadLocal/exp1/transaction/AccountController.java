package com.jason.threadLocal.exp1.transaction;

/**转账功能
 * @author WangChenHol
 * @date 2021-12-12 16:27
 **/
public class AccountController {
    AccountService service = new AccountService();

    public void transfer() {
        boolean transfer = service.transfer("Jack", "Rose", 100);
        System.out.println(transfer ? "转账成功" : "转账失败");
    }

    public static void main(String[] args) {
        new AccountController().transfer();
    }
}
