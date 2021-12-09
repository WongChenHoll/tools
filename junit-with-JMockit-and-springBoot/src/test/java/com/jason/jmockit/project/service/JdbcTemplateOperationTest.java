package com.jason.jmockit.project.service;

import com.jason.jmockit.JunitWithJmockitApplication;
import com.jason.jmockit.project.service.impl.JdbcTemplateOperationImpl;
import mockit.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

/**
 * 针对Spring中JdbcTemplate操作的单元测试
 *
 * @author WangChenHol
 * @date 2021-12-8 22:01
 **/
@SuppressWarnings("ALL")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JunitWithJmockitApplication.class)
public class JdbcTemplateOperationTest {

    @Tested
    private JdbcTemplateOperationImpl jdbcTemplateOperation;
    // 下面只能使用Injectable，不能用Mocked注解
    @Injectable
    JdbcTemplate jdbcTemplate;

    // 基本测试
    @Test
    public void test() {
        new Expectations() {
            {
                jdbcTemplate.queryForObject("aaa", String.class);
                result = "sss";
                jdbcTemplate.queryForObject("sss", Integer.class);
                result = 111;
                jdbcTemplate.queryForObject("ddd", Double.class);
                result = 22.00;
            }
        };
        assertEquals("sss", jdbcTemplate.queryForObject("aaa", String.class));
        assertEquals(111, (int) jdbcTemplate.queryForObject("sss", Integer.class));
        assertEquals(22.00, (double) jdbcTemplate.queryForObject("ddd", Double.class), 0);
    }

    // queryForObject   2个参数
    @Test
    public void test1() {
        new Expectations() {
            {
                jdbcTemplate.queryForObject(anyString, String.class);
                result = "哈哈哈";
            }
        };
        String string = jdbcTemplateOperation.getString();
        assertEquals("哈哈哈", string);
//        assertEquals("哈哈哈", jdbcTemplate.queryForObject("asas", String.class));
//        assertEquals("1999", jdbcTemplate.queryForObject("333333ddddd", String.class));
    }

    // queryForObject   2个参数
    @Test
    public void test2() {
        new Expectations() {
            {
                jdbcTemplate.queryForObject("aaa", String.class);
                result = "哈哈哈";
                jdbcTemplate.queryForObject("sss", Integer.class);
                result = 100;
            }
        };
        String string = jdbcTemplateOperation.getString("aaa", "sss");
        assertEquals("哈哈哈", string);
    }

    // queryForObject   3个参数
    @Test
    public void test3() {
        Object[] param = new Object[2];
        new Expectations() {
            {
                jdbcTemplate.queryForObject("select * from dual", param, String.class);
                result = "查询用户名";
            }
        };
        new MockUp<JdbcTemplate>(JdbcTemplate.class){
            @Mock
            public int[] batchUpdate(String sql, final BatchPreparedStatementSetter pss) throws DataAccessException {
                return new int[]{1,2};
            }
        };
        String string = jdbcTemplateOperation.getString(param);
        assertEquals("查询用户名", string);
    }

}
