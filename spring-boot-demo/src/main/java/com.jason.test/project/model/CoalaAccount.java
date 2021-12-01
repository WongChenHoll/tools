package com.jason.test.project.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 客户账户金额表
 *
 * @author ChenHol.Wong
 * @create 2020/10/7 12:03
 */
public class CoalaAccount {
    private String id;
    private String customerName;
    private char sex;
    private Date birthday;
    private BigDecimal principal;
    private BigDecimal balance;
    private BigDecimal arrearage;
    private Integer billDate;

    public CoalaAccount() {
    }

    public CoalaAccount(String id, BigDecimal principal) {
        this.id = id;
        this.principal = principal;
    }

    public CoalaAccount(String id, BigDecimal principal, Date birthday) {
        this.id = id;
        this.principal = principal;
        this.birthday = birthday;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public BigDecimal getPrincipal() {
        return principal;
    }

    public void setPrincipal(BigDecimal principal) {
        this.principal = principal;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getArrearage() {
        return arrearage;
    }

    public void setArrearage(BigDecimal arrearage) {
        this.arrearage = arrearage;
    }

    public Integer getBillDate() {
        return billDate;
    }

    public void setBillDate(Integer billDate) {
        this.billDate = billDate;
    }

    @Override
    public String toString() {
        return "CoalaAccount{" +
                "id='" + id + '\'' +
                ", customerName='" + customerName + '\'' +
                ", sex=" + sex +
                ", birthday=" + birthday +
                ", principal=" + principal +
                ", balance=" + balance +
                ", arrearage=" + arrearage +
                ", billDate=" + billDate +
                '}';
    }
}
