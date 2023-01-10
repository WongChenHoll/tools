package annotation.service;

import annotation.bean.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author WongChenHoll
 * @description
 * @date 2023-1-9 星期一 17:08
 **/
@Service
public class JSONServiceImpl implements JSONService {
    @Override
    public List<Product> list() {
        ArrayList<Product> list = new ArrayList<>();
        list.add(Product.getInstance(10, "书本", "aaa", 12.34, new Date()));
        list.add(Product.getInstance(10, null, null, 12.34, new Date()));
        return list;
    }
}
