package annotation.param;

import annotation.bean.User;
import org.springframework.web.bind.annotation.*;

/**
 * 前端传递参数值.
 *
 * @author WangChenHol
 * @date 2021-9-28 14:58
 **/
@RestController
@RequestMapping("/param")
public class RequestBodyController {

    /**
     * RequestBody注解的普通用法。
     * 注意：请求实体类中一定要有getter方法。
     *
     * @param book 实体类
     */
    @PostMapping("normal")
    public Book normal(@RequestBody Book book) {
        return book;
    }

    // @RequestBody注解加减属性
    @PostMapping("addOrDeleteProperty")
    public Book addOrDeleteProperty(@RequestBody Book book) {
        return book;
    }

    /**
     * 使用@RequestBody注解接收json字符串参数。
     * 此情况请求的Context-type可以是：
     * text/plain
     * application/json
     * application/javascript
     * application/xml
     * text/xml
     * text/html
     * 等。
     *
     * @param param json字符串参数
     */
    @PostMapping("jsonString")
    public String addOrDeleteProperty(@RequestBody String param) {
        return param;
    }

    // 多个@RequestBody注解一起使用，会报异常
    @PostMapping("more")
    public String more(@RequestBody Book book, @RequestBody User user) {
        return book.toString() + "\r\n" + user.toString();
    }

    // 使用多个对象接收参数
    @PostMapping("moreObject")
    public String moreObject(@RequestBody Book book1, Book book2) {
        return "带注解接收的数据：\r\n" + book1.toString()
                + "\r\n\r\n不带注解接收的数据：\r\n" + book2.toString();
    }


    // GET请求中通过@RequestBody注解获取请求体中的参数
    @GetMapping("toGet")
    public Book toGet(@RequestBody Book book) {
        return book;
    }

    @PostMapping("requestBodyAndRequestParam")
    public String requestBodyAndRequestParam(@RequestBody Book book,
                                             @RequestParam String name,
                                             @RequestParam double price,
                                             @RequestParam String author,
                                             @RequestParam String publishingHouse) {
        return book.toString() + "\r\n\r\n"
                + name + "\r\n"
                + price + "\r\n"
                + author + "\r\n"
                + publishingHouse;
    }

    @PostMapping("requestBodyAndPathVariable/{bookType}/{bookNo}")
    public String requestBodyAndPathVariable(@RequestBody Book book,
                                             @PathVariable String bookType,
                                             @PathVariable String bookNo) {
        return "图书类别：" + bookType + "\t图书编号：" + bookNo + "\r\n" + book.toString();
    }

    @PostMapping("String")
    public String String(@RequestBody String name) {
        return name;
    }

    @PostMapping("integer")
    public int integer(@RequestBody int name) {
        return name;
    }

    @PostMapping("array")
    public String array(@RequestBody String[] names) {
        if (names == null || names.length == 0) {
            return "空";
        }
        StringBuilder content = new StringBuilder();
        for (String name : names) {
            content.append(name).append(" ");
        }
        return content.toString();
    }
}
