package com.jason.test.project.service.impl;

import com.jason.test.TestApplication;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author WangChenHol
 * @date 2021-12-7 21:39
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplication.class)
public class JdbcTemplateServiceImplTest {

    @InjectMocks
    private JdbcTemplateServiceImpl jdbcTemplateService;

    @Mock
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testupdate() {
        String updateSql = "update T_HAO_COALA_ACCOUNT set CUSTOMER_NAME =? where id =? ";
        Object[] param = {"李四", "2"};

        Mockito.when(jdbcTemplate.update(updateSql, param)).thenReturn(22);
        jdbcTemplateService.update();

    }

    @Test
    public void testqueryForList() {
        Object[] param = {"1", 12};
        List<Map<String, Object>> maps = new ArrayList<>();
        HashMap<String, Object> map = new HashMap<>();
        map.put("aaa", 33);
        maps.add(map);
        Mockito.when(jdbcTemplate.queryForList("select", param)).thenReturn(maps);
        List<Map<String, Object>> query = (List<Map<String, Object>>) jdbcTemplateService.queryForList(1);
//        Assert.assertEquals(33, query.get(0).get("aaa"));
    }

    @Test
    public void testopertion() {
        jdbcTemplateService.opertion();
    }
}
