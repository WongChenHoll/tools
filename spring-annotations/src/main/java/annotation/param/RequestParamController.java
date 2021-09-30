package annotation.param;

import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * RequestParam注解的使用
 *
 * @author WangChenHol
 * @date 2021-9-29 10:03
 **/
@RestController
@RequestMapping("param")
public class RequestParamController {

    @PostMapping("normalParam")
    public String RequestParamNormal(@RequestParam String name,
                                     @RequestParam double price,
                                     @RequestParam String author,
                                     @RequestParam String publishingHouse) {
        return "图书名：" + name + "\r\n价格：" + price + "\r\n作者：" + author + "\r\n出版社：" + publishingHouse;
    }

    @GetMapping("toGetParam")
    public String RequestParamNormalToGet(@RequestParam String name,
                                          @RequestParam double price,
                                          @RequestParam String author,
                                          @RequestParam String publishingHouse) {
        return "图书名：" + name + "\r\n价格：" + price + "\r\n作者：" + author + "\r\n出版社：" + publishingHouse;
    }

    @GetMapping("differentParamName")
    public String differentParamName(@RequestParam(name = "name", defaultValue = "发家致富（默认名）") String bookName,
                                     @RequestParam(name = "price") double price,
                                     @RequestParam(name = "author", required = false) String author,
                                     @RequestParam(name = "house", required = false, defaultValue = "江南出版社") String publishingHouse) {
        return "图书名：" + bookName + "\r\n价格：" + price + "\r\n作者：" + author + "\r\n出版社：" + publishingHouse;
    }

    @GetMapping("arrayParam")
    public String array(@RequestParam String[] books) {
        return Arrays.toString(books);
    }

}
