package annotation.param;

import org.springframework.web.bind.annotation.*;

/**
 * PathVariable注解的使用
 *
 * @author WangChenHol
 * @date 2021-9-30 10:20
 **/
@RestController
@RequestMapping("/param")
public class PathVariableController {

    @GetMapping("/pathNormal/{bookType}/find/{bookNo}")
    public String getBook(@PathVariable String bookType,
                          @PathVariable String bookNo) {
        return "图书类别：" + bookType + "\t图书编号：" + bookNo;
    }

    @PostMapping("/postPath/{bookType}/find/{bookNo}")
    public String getBookByPost(@PathVariable(name = "bookType", required = false) String type,
                                @PathVariable(name = "bookNo", required = false) String number) {
        return "图书类别：" + type + "\t图书编号：" + number;
    }
}
