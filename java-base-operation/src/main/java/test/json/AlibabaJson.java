package test.json;

import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson2.JSON;

import java.util.HashMap;

/**
 * @author WongChenHoll
 * @description
 * @date 2023-2-9 星期四 11:19
 **/
public class AlibabaJson {
    public static void main(String[] args) {
        HashMap<String, Object> c = new HashMap<>();
        c.put("name", "zhangSan");
        c.put("age", 33);

        HashMap<String, Object> p = new HashMap<>();
        p.put("className", "一班");
        p.put("man", "人员信息:${info}".replace("${info}", JSON.toJSONString(c)));

        System.out.println(JSON.toJSONString(p, String.valueOf(Feature.DisableCircularReferenceDetect)));
        System.out.println(JSON.parseObject(JSON.toJSONString(p)));
    }
}
