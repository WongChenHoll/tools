package annotation.param;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 书。测试前段传值
 *
 * @author WangChenHol
 * @date 2021-9-28 14:54
 **/
@Setter
@Getter
@ToString
public class Book {
    private String name; // 书名
    private double price; // 价格
    private String author; // 作者
    private String publishingHouse; // 出版社

}
