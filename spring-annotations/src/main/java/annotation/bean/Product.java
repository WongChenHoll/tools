package annotation.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

/**
 * @author WongChenHoll
 * @description 产品实体类，测试用
 * @date 2023-1-9 星期一 17:04
 **/
@JsonInclude(JsonInclude.Include.NON_NULL) // 序列化的时候忽略值为null的属性，不进行展示
@Data
public class Product {

    private int productId; //产品ID
    @JsonInclude(JsonInclude.Include.ALWAYS) // 无论值是什么都会展示
    private String productName;
    private String numbers;
    private double price;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    private Product(int productId, String productName, String numbers, double price, Date createTime) {
        this.productId = productId;
        this.productName = productName;
        this.numbers = numbers;
        this.price = price;
        this.createTime = createTime;
    }

    public static Product getInstance(int productId, String productName, String numbers, double price, Date createTime) {
        return new Product(productId, productName, numbers, price, createTime);
    }
}
