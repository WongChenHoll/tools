package com.jason.test.project.impl;

import com.jason.test.project.model.CoalaAccount;
import com.jason.test.project.service.JdbcTemplateService;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author ChenHol.Wong
 * @create 2020/9/27 9:59
 */
@Service
public class JdbcTemplateServiceImpl implements JdbcTemplateService {

    private static final Logger logger = LoggerFactory.getLogger(JdbcTemplateServiceImpl.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private Map map = new ConcurrentHashMap();

    @Override
    public Object query() {

//        String sql = "select id,customer_name,sex,bill_date from T_HAO_COALA_ACCOUNT where id='2'";
        // query(String sql, ResultSetExtractor<T> rse)
        // 查询单个结果对象，ID=2的数据
//        CoalaAccount account = jdbcTemplate.query(sql, new ResultSetExtractor<CoalaAccount>() {
//            @Override
//            public CoalaAccount extractData(ResultSet rs) throws SQLException, DataAccessException {
//                CoalaAccount account = new CoalaAccount();
//                while (rs.next()) {
//                    account.setId(rs.getString(1));
//                    account.setCustomerName(rs.getString(2));
//                    account.setSex(rs.getString(3).charAt(0));
//                    account.setBillDate(rs.getInt(4));
//                }
//                return account;
//            }
//        });
//
//        // 查询多个对象,返回该对象List。性别是1的所有数据
//        sql = "select id,customer_name,sex,bill_date from T_HAO_COALA_ACCOUNT where sex='1'";
//        List<CoalaAccount> list = jdbcTemplate.query(sql, (ResultSetExtractor<List<CoalaAccount>>) rs -> {
//            List<CoalaAccount> list1 = new ArrayList<>();
//            while (rs.next()) {
//                CoalaAccount obj = new CoalaAccount();
//                obj.setId(rs.getString(1));
//                obj.setCustomerName(rs.getString(2));
//                obj.setSex(rs.getString(3).charAt(0));
//                obj.setBillDate(rs.getInt(4));
//                list1.add(obj);
//            }
//            return list1;
//        });
//
//        // 查询多个对象,返回Map形式的List。性别是1的所有数据
//        List<Map<String, Object>> list = jdbcTemplate.query(sql, new ResultSetExtractor<List<Map<String, Object>>>() {
//            @Override
//            public List<Map<String, Object>> extractData(ResultSet rs) throws SQLException, DataAccessException {
//                List<Map<String, Object>> list2 = new ArrayList<>();
//                Map<String, Object> map;
//                while (rs.next()) {
//                    map = new HashMap<>();
//                    map.put("id", rs.getString(1));
//                    map.put("customerName", rs.getString(2));
//                    map.put("sex", rs.getString(3));
//                    map.put("billDate", rs.getString(4));
//                    list2.add(map);
//                }
//                return list2;
//            }
//        });
//
//        // 查询总数
//        sql = "select count(*) from T_HAO_COALA_ACCOUNT";
//        Integer count = jdbcTemplate.query(sql, rs -> {
//            rs.next();
//            int anInt = rs.getInt(1);
//            return anInt;
//        });


        // query(String sql, RowCallbackHandler rch)
        // 查询单条数据
//        HashMap<String, Object> map = new HashMap<>();
//        jdbcTemplate.query(sql, new RowCallbackHandler() {
//            @Override
//            public void processRow(ResultSet rs) throws SQLException {
//                map.put("id", rs.getString("id"));
//                map.put("customerName", rs.getString("customer_name"));
//                map.put("sex", rs.getString("sex"));
//                map.put("billDate", rs.getString("bill_date"));
//            }
//        });
//        // 查询多条数据
//        ArrayList<CoalaAccount> list = new ArrayList<>();
//        sql = "select id,customer_name,sex,bill_date from T_HAO_COALA_ACCOUNT where sex='3'";
//        jdbcTemplate.query(sql, rs -> {
//            CoalaAccount account = new CoalaAccount();
//            account.setId(rs.getString(1));
//            account.setCustomerName(rs.getString(2));
//            account.setSex(rs.getString(3).charAt(0));
//            account.setBillDate(rs.getInt(4));
//            list.add(account);
//        });


        // query(String sql, RowMapper<T> rowMapper)
        // 查一条数据
//        String sql = "select id,customer_name,sex,bill_date from T_HAO_COALA_ACCOUNT where id='2'";
//        List<CoalaAccount> account = jdbcTemplate.query(sql, new RowMapper<CoalaAccount>() {
//            @Override
//            public CoalaAccount mapRow(ResultSet rs, int rowNum) throws SQLException {
//                CoalaAccount account = new CoalaAccount();
//                account.setId(rs.getString(1));
//                account.setCustomerName(rs.getString(2));
//                account.setSex(rs.getString(3).charAt(0));
//                account.setBillDate(rs.getInt(4));
//                return account;
//            }
//        });
//
//        // 查多条数据
//        sql = "select id,customer_name,sex,bill_date from T_HAO_COALA_ACCOUNT where sex='2'";
//        List<CoalaAccount>  list = jdbcTemplate.query(sql, new RowMapper<CoalaAccount>() {
//            @Override
//            public CoalaAccount mapRow(ResultSet rs, int rowNum) throws SQLException {
//                CoalaAccount obj = new CoalaAccount();
//                obj.setId(rs.getString("id"));
//                obj.setCustomerName(rs.getString("customer_name"));
//                obj.setSex(rs.getString("sex").charAt(0));
//                obj.setBillDate(rs.getInt("bill_date"));
//                return obj;
//            }
//        });
//
//        // 查询总数
//        sql = "select count(*) from T_HAO_COALA_ACCOUNT";
//        List<Integer> count = jdbcTemplate.query(sql, new RowMapper<Integer>() {
//            @Override
//            public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
//                int anInt = rs.getInt(1);
//                return anInt;
//            }
//        });


        // query(PreparedStatementCreator psc, @Nullable final PreparedStatementSetter pss, final ResultSetExtractor<T> rse)
        // 查询一条记录,sql中无动态参数
//        String sql = "select id,customer_name,sex,bill_date from T_HAO_COALA_ACCOUNT where id='2'";
//        CoalaAccount account = jdbcTemplate.query(new PreparedStatementCreator() {
//            @Override
//            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
//                return con.prepareStatement(sql);
//            }
//        }, new PreparedStatementSetter() {
//            @Override
//            public void setValues(PreparedStatement ps) throws SQLException {
//                // nothing to do ....
//            }
//        }, new ResultSetExtractor<CoalaAccount>() {
//            @Override
//            public CoalaAccount extractData(ResultSet rs) throws SQLException, DataAccessException {
//                CoalaAccount account = new CoalaAccount();
//                while (rs.next()) {
//                    account.setId(rs.getString(1));
//                    account.setCustomerName(rs.getString(2));
//                    account.setSex(rs.getString(3).charAt(0));
//                    account.setBillDate(rs.getInt(4));
//                }
//                return account;
//            }
//        });
//
//        // 查询多条记录,sql中有动态参数
//        String sql2 = "select id,customer_name,sex,bill_date from T_HAO_COALA_ACCOUNT where sex = ? and bill_date > ?";
//        List<CoalaAccount> list = jdbcTemplate.query(
//                con -> con.prepareStatement(sql2),
//                ps -> {
//                    // 给sql中的占位符设置参数值
//                    ps.setString(1, "1");
//                    ps.setInt(2, 10);
//                },
//                (ResultSetExtractor<List<CoalaAccount>>) rs -> {
//                    ArrayList<CoalaAccount> list1 = new ArrayList<>();
//                    while (rs.next()) {
//                        CoalaAccount account1 = new CoalaAccount();
//                        account1.setId(rs.getString(1));
//                        account1.setCustomerName(rs.getString(2));
//                        account1.setSex(rs.getString(3).charAt(0));
//                        account1.setBillDate(rs.getInt(4));
//                        list1.add(account1);
//                    }
//                    return list1;
//                });


        // query(PreparedStatementCreator psc, ResultSetExtractor<T> rse)

//        String sql = "select id,customer_name,sex,bill_date from T_HAO_COALA_ACCOUNT where id='2'";
//        CoalaAccount account = jdbcTemplate.query(new PreparedStatementCreator() {
//            @Override
//            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
//                return con.prepareStatement(sql);
//            }
//        }, new ResultSetExtractor<CoalaAccount>() {
//            @Override
//            public CoalaAccount extractData(ResultSet rs) throws SQLException, DataAccessException {
//                CoalaAccount account = new CoalaAccount();
//                while (rs.next()) {
//                    account.setId(rs.getString(1));
//                    account.setCustomerName(rs.getString(2));
//                    account.setSex(rs.getString(3).charAt(0));
//                    account.setBillDate(rs.getInt(4));
//                }
//                return account;
//            }
//        });


        // query(String sql, @Nullable PreparedStatementSetter pss, ResultSetExtractor<T> rse)
        // 此方法和上面两个类似
//        String sql = "select id,customer_name,sex,bill_date from T_HAO_COALA_ACCOUNT where id='2'";
//        jdbcTemplate.query(sql, new PreparedStatementSetter() {
//            @Override
//            public void setValues(PreparedStatement ps) throws SQLException {
//            // 如果sql中有占位符，则处理占位符的参数值，没有不做任何处理。
//            }
//        }, new ResultSetExtractor<CoalaAccount>() {
//            @Override
//            public CoalaAccount extractData(ResultSet rs) throws SQLException, DataAccessException {
//                // 处理结果集，
//                return null;
//            }
//        });


        // query(String sql, Object[] args, int[] argTypes, ResultSetExtractor<T> rse)
//        // 根据泛型定义，可以查询多条也可以查询一条数据，如果sql有占位符则第二个和第三个参数不能是null，元素也必须要有值，且两个数组的长度要一样。
//        String sql2 = "select id,customer_name,sex,bill_date from T_HAO_COALA_ACCOUNT where sex = ? and bill_date > ?";
//        Object[] param = {"1", 10};
//        int[] index = {1, 2};
//        List<CoalaAccount> list = jdbcTemplate.query(sql2, param, index, new ResultSetExtractor<List<CoalaAccount>>() {
//            @Override
//            public List<CoalaAccount> extractData(ResultSet rs) throws SQLException, DataAccessException {
//                List<CoalaAccount> list = new ArrayList<>();
//                while (rs.next()) {
//                    CoalaAccount account = new CoalaAccount();
//                    account.setId(rs.getString(1));
//                    account.setCustomerName(rs.getString(2));
//                    account.setSex(rs.getString(3).charAt(0));
//                    account.setBillDate(rs.getInt(4));
//                    list.add(account);
//                }
//                return list;
//            }
//        });
//
//        // 也可以参数值为空，即没有任何占位符
//        String sql = "select id,customer_name,sex,bill_date from T_HAO_COALA_ACCOUNT where id='2'";
//        List<CoalaAccount> list2 = jdbcTemplate.query(sql, null, null, new ResultSetExtractor<List<CoalaAccount>>() {
//            @Override
//            public List<CoalaAccount> extractData(ResultSet rs) throws SQLException, DataAccessException {
//                List<CoalaAccount> list = new ArrayList<>();
//                while (rs.next()) {
//                    CoalaAccount account = new CoalaAccount();
//                    account.setId(rs.getString(1));
//                    account.setCustomerName(rs.getString(2));
//                    account.setSex(rs.getString(3).charAt(0));
//                    account.setBillDate(rs.getInt(4));
//                    list.add(account);
//                }
//                return list;
//            }
//        });


        // query(String sql, @Nullable Object[] args, ResultSetExtractor<T> rse)
        // 没有占位符
//        String sql = "select id,customer_name,sex,bill_date from T_HAO_COALA_ACCOUNT where id='2'";
//        CoalaAccount account = jdbcTemplate.query(sql, (Object[]) null, new ResultSetExtractor<CoalaAccount>() {
//            @Override
//            public CoalaAccount extractData(ResultSet rs) throws SQLException, DataAccessException {
//                CoalaAccount account = new CoalaAccount();
//                while (rs.next()) {
//                    account.setId(rs.getString(1));
//                    account.setCustomerName(rs.getString(2));
//                    account.setSex(rs.getString(3).charAt(0));
//                    account.setBillDate(rs.getInt(4));
//                }
//                return account;
//            }
//        });
//        // 有占位符
//        String sql2 = "select id,customer_name,sex,bill_date from T_HAO_COALA_ACCOUNT where sex = ? and bill_date > ?";
//        Object[] param = {"1", 10};
//        List<CoalaAccount> list = jdbcTemplate.query(sql2, param, new ResultSetExtractor<List<CoalaAccount>>() {
//            @Override
//            public List<CoalaAccount> extractData(ResultSet rs) throws SQLException, DataAccessException {
//                List<CoalaAccount> list = new ArrayList<>();
//                while (rs.next()) {
//                    CoalaAccount account = new CoalaAccount();
//                    account.setId(rs.getString(1));
//                    account.setCustomerName(rs.getString(2));
//                    account.setSex(rs.getString(3).charAt(0));
//                    account.setBillDate(rs.getInt(4));
//                    list.add(account);
//                }
//                return list;
//            }
//        });


        // query(String sql, ResultSetExtractor<T> rse, @Nullable Object... args)
//        String sql2 = "select id,customer_name,sex,bill_date from T_HAO_COALA_ACCOUNT where sex = ? and bill_date > ?";
//        List<CoalaAccount> list = jdbcTemplate.query(sql2, rs -> {
//            List<CoalaAccount> list1 = new ArrayList<>();
//            while (rs.next()) {
//                CoalaAccount account = new CoalaAccount();
//                account.setId(rs.getString(1));
//                account.setCustomerName(rs.getString(2));
//                account.setSex(rs.getString(3).charAt(0));
//                account.setBillDate(rs.getInt(4));
//                list1.add(account);
//            }
//            return list1;
//        }, "1", 10); // 传参的位置


        // query(PreparedStatementCreator psc, RowCallbackHandler rch)
//        ArrayList<CoalaAccount> list = new ArrayList<>();
//        String sql2 = "select id,customer_name,sex,bill_date from T_HAO_COALA_ACCOUNT where sex = ? and bill_date > ?";
//        jdbcTemplate.query(new PreparedStatementCreator() {
//            @Override
//            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
//                PreparedStatement ps = con.prepareStatement(sql2);
//                ps.setString(1, "1");
//                ps.setInt(2, 12);
//                ps.executeQuery();
//                return ps;
//            }
//        }, new RowCallbackHandler() {
//            @Override
//            public void processRow(ResultSet rs) throws SQLException {
//                CoalaAccount account = new CoalaAccount();
//                account.setId(rs.getString(1));
//                account.setCustomerName(rs.getString(2));
//                account.setSex(rs.getString(3).charAt(0));
//                account.setBillDate(rs.getInt(4));
//                list.add(account);
//            }
//        });


        // query(String sql, @Nullable PreparedStatementSetter pss, RowCallbackHandler rch)
        // 查询多条数据
//        String sql = "select id,customer_name,sex,bill_date from T_HAO_COALA_ACCOUNT where sex = ? and bill_date > ?";
//        ArrayList<CoalaAccount> list = new ArrayList<>();
//        jdbcTemplate.query(sql, new PreparedStatementSetter() {
//            @Override
//            public void setValues(PreparedStatement ps) throws SQLException {
//                ps.setString(1, "1");
//                ps.setInt(2, 10);
//            }
//        }, new RowCallbackHandler() {
//            @Override
//            public void processRow(ResultSet rs) throws SQLException {
//                CoalaAccount account = new CoalaAccount();
//                account.setId(rs.getString(1));
//                account.setCustomerName(rs.getString(2));
//                account.setSex(rs.getString(3).charAt(0));
//                account.setBillDate(rs.getInt(4));
//                list.add(account);
//            }
//        });
//        CoalaAccount account = new CoalaAccount();
//        sql = "select id,customer_name,sex,bill_date from T_HAO_COALA_ACCOUNT where id='2'";
//        jdbcTemplate.query(sql, new PreparedStatementSetter() {
//            @Override
//            public void setValues(PreparedStatement ps) throws SQLException {
//                // sql中没有参数需要处理，故不作任何处理
//            }
//        }, new RowCallbackHandler() {
//            @Override
//            public void processRow(ResultSet rs) throws SQLException {
//                account.setId(rs.getString(1));
//                account.setCustomerName(rs.getString(2));
//                account.setSex(rs.getString(3).charAt(0));
//                account.setBillDate(rs.getInt(4));
//            }
//        });

        // query(String sql, Object[] args, int[] argTypes, RowCallbackHandler rch)
        // 查询单条数据
//        String sql = "select id,customer_name,sex,bill_date from T_HAO_COALA_ACCOUNT where id='2'";
//        CoalaAccount account = new CoalaAccount();
//        jdbcTemplate.query(sql, null, null, new RowCallbackHandler() {
//            @Override
//            public void processRow(ResultSet rs) throws SQLException {
//                account.setId(rs.getString(1));
//                account.setCustomerName(rs.getString(2));
//                account.setSex(rs.getString(3).charAt(0));
//                account.setBillDate(rs.getInt(4));
//            }
//        });
//
//        // 查询多条数据
//        sql = "select id,customer_name,sex,bill_date from T_HAO_COALA_ACCOUNT where sex = ? and bill_date > ?";
//        ArrayList<CoalaAccount> list = new ArrayList<>();
//        Object[] param = {"1", 10};
//        int[] index = {1, 2};
//        jdbcTemplate.query(sql, param, index, new RowCallbackHandler() {
//            @Override
//            public void processRow(ResultSet rs) throws SQLException {
//                CoalaAccount account = new CoalaAccount();
//                account.setId(rs.getString(1));
//                account.setCustomerName(rs.getString(2));
//                account.setSex(rs.getString(3).charAt(0));
//                account.setBillDate(rs.getInt(4));
//                list.add(account);
//            }
//        });


        // query(String sql, Object[] args, RowCallbackHandler rch)

//        String sql = "select id,customer_name,sex,bill_date from T_HAO_COALA_ACCOUNT where sex = ? and bill_date > ?";
//        ArrayList<CoalaAccount> list = new ArrayList<>();
//        Object[] param = {"4", 10};
//        jdbcTemplate.query(sql, param, new RowCallbackHandler() {
//            @Override
//            public void processRow(ResultSet rs) throws SQLException {
//                CoalaAccount account = new CoalaAccount();
//                account.setId(rs.getString(1));
//                account.setCustomerName(rs.getString(2));
//                account.setSex(rs.getString(3).charAt(0));
//                account.setBillDate(rs.getInt(4));
//                list.add(account);
//            }
//        });
//        logger.info("查询结果：{}", account);


        // query(String sql, RowCallbackHandler rch, @Nullable Object... args)
//        String sql = "select id,customer_name,sex,bill_date from T_HAO_COALA_ACCOUNT where sex = ? and bill_date > ?";
//        ArrayList<CoalaAccount> list = new ArrayList<>();
//        jdbcTemplate.query(sql,new RowCallbackHandler() {
//            @Override
//            public void processRow(ResultSet rs) throws SQLException {
//                CoalaAccount account = new CoalaAccount();
//                account.setId(rs.getString(1));
//                account.setCustomerName(rs.getString(2));
//                account.setSex(rs.getString(3).charAt(0));
//                account.setBillDate(rs.getInt(4));
//                list.add(account);
//            }
//        },"2",10);


        // query(PreparedStatementCreator psc, RowMapper<T> rowMapper)
        // 查询多条数据，sql中有占位符
//        String sql = "select id,customer_name,sex,bill_date from T_HAO_COALA_ACCOUNT where sex = ? and bill_date > ?";
//        List<List<CoalaAccount>> list = jdbcTemplate.query(new PreparedStatementCreator() {
//            @Override
//            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
//                PreparedStatement ps = con.prepareStatement(sql);
//                ps.setString(1, "1");
//                ps.setInt(2, 10);
//                return ps;
//            }
//        }, new RowMapper<List<CoalaAccount>>() {
//            ArrayList<CoalaAccount> list = new ArrayList<>();
//
//            @Override
//            public List<CoalaAccount> mapRow(ResultSet rs, int rowNum) throws SQLException {
//                CoalaAccount account = new CoalaAccount();
//                account.setId(rs.getString(1));
//                account.setCustomerName(rs.getString(2));
//                account.setSex(rs.getString(3).charAt(0));
//                account.setBillDate(rs.getInt(4));
//                list.add(account);
//                return list;
//            }
//        });
//
//        // 查询单条数据 ，sql中没有占位符
//        String queryById = "select id,customer_name,sex,bill_date from T_HAO_COALA_ACCOUNT where id='2'";
//        List<CoalaAccount> accounts = jdbcTemplate.query(new PreparedStatementCreator() {
//            @Override
//            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
//                return con.prepareStatement(queryById);
//            }
//        }, new RowMapper<CoalaAccount>() {
//            @Override
//            public CoalaAccount mapRow(ResultSet rs, int rowNum) throws SQLException {
//                CoalaAccount account = new CoalaAccount();
//                account.setId(rs.getString(1));
//                account.setCustomerName(rs.getString(2));
//                account.setSex(rs.getString(3).charAt(0));
//                account.setBillDate(rs.getInt(4));
//                return account;
//            }
//        });
//        logger.info("总数：[{}],查询结果：{}", accounts.size(), accounts.get(0));

        // query(String sql, @Nullable PreparedStatementSetter pss, RowMapper<T> rowMapper)
//        String queryById = "select id,customer_name,sex,bill_date from T_HAO_COALA_ACCOUNT where id='2'";
//        List<CoalaAccount> query = jdbcTemplate.query(queryById, new PreparedStatementSetter() {
//            @Override
//            public void setValues(PreparedStatement ps) throws SQLException {
//
//            }
//        }, new RowMapper<CoalaAccount>() {
//            @Override
//            public CoalaAccount mapRow(ResultSet rs, int rowNum) throws SQLException {
//                CoalaAccount account = new CoalaAccount();
//                account.setId(rs.getString(1));
//                account.setCustomerName(rs.getString(2));
//                account.setSex(rs.getString(3).charAt(0));
//                account.setBillDate(rs.getInt(4));
//                return account;
//            }
//        });


        // query(String sql, Object[] args, int[] argTypes, RowMapper<T> rowMapper)
        // 查询多条记录
//        String sql = "select id,customer_name,sex,bill_date from T_HAO_COALA_ACCOUNT where sex = ? and bill_date > ?";
//        Object[] param = { "1",10};
//        int[] index = {1,2};
//
//        List<CoalaAccount> list = jdbcTemplate.query(sql, param, index, new RowMapper<CoalaAccount>() {
//            @Override
//            public CoalaAccount mapRow(ResultSet rs, int rowNum) throws SQLException {
//                CoalaAccount account = new CoalaAccount();
//                account.setId(rs.getString(1));
//                account.setCustomerName(rs.getString(2));
//                account.setSex(rs.getString(3).charAt(0));
//                account.setBillDate(rs.getInt(4));
//                return account;
//            }
//        });
        // query(String sql, @Nullable Object[] args, RowMapper<T> rowMapper)
//        String sql = "select id,customer_name,sex,bill_date from T_HAO_COALA_ACCOUNT where sex = ? and bill_date > ?";
//        Object[] param = { "1",10};
//
//        List<CoalaAccount> list = jdbcTemplate.query(sql, param, new RowMapper<CoalaAccount>() {
//            @Override
//            public CoalaAccount mapRow(ResultSet rs, int rowNum) throws SQLException {
//                CoalaAccount account = new CoalaAccount();
//                account.setId(rs.getString(1));
//                account.setCustomerName(rs.getString(2));
//                account.setSex(rs.getString(3).charAt(0));
//                account.setBillDate(rs.getInt(4));
//                return account;
//            }
//        });

        // query(String sql, RowMapper<T> rowMapper, @Nullable Object... args)
//        String sql = "select id,customer_name,sex,bill_date from T_HAO_COALA_ACCOUNT where sex = ? and bill_date > ?";
//
//        List<CoalaAccount> list = jdbcTemplate.query(sql,new RowMapper<CoalaAccount>() {
//            @Override
//            public CoalaAccount mapRow(ResultSet rs, int rowNum) throws SQLException {
//                CoalaAccount account = new CoalaAccount();
//                account.setId(rs.getString(1));
//                account.setCustomerName(rs.getString(2));
//                account.setSex(rs.getString(3).charAt(0));
//                account.setBillDate(rs.getInt(4));
//                return account;
//            }
//        },"1",10);


//        logger.info("查询总数：[{}]", count);
        return null;
    }

    @Override
    public Object queryForObject() {
        // queryForObject 只能查询单列

        // queryForObject(String sql, RowMapper<T> rowMapper)
        // 查询对象中的单个属性
//        String sql = "select customer_name from T_HAO_COALA_ACCOUNT where id= '2'";
//        CoalaAccount account = jdbcTemplate.queryForObject(sql, new RowMapper<CoalaAccount>() {
//            @Override
//            public CoalaAccount mapRow(ResultSet rs, int rowNum) throws SQLException {
//                CoalaAccount account = new CoalaAccount();
//                account.setCustomerName(rs.getString(1));
//                return account;
//            }
//        });
//
//        // 查询总数
//        sql = "select count(*) from T_HAO_COALA_ACCOUNT where sex = '1'";
//        Integer count = jdbcTemplate.queryForObject(sql, new RowMapper<Integer>() {
//            @Override
//            public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
//                return rs.getInt(1);
//            }
//        });
//        logger.info("总数量：[{}]", count);
//        // 查询某一列总和
//        sql = "select sum(bill_date) from T_HAO_COALA_ACCOUNT where sex = '1'";
//        Integer sum = jdbcTemplate.queryForObject(sql, new RowMapper<Integer>() {
//            @Override
//            public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
//                return rs.getInt(1);
//            }
//        });
//        logger.info("账单日总和：[{}]", sum);
//        // 查询单列
//        sql = "select customer_name from T_HAO_COALA_ACCOUNT where id= '222222'";
//        String customerName = jdbcTemplate.queryForObject(sql, new RowMapper<String>() {
//            @Override
//            public String mapRow(ResultSet rs, int rowNum) throws SQLException {
//                return rs.getString(1);
//            }
//        });
//        logger.info("客户姓名：[{}]", customerName);


        // 自动转换查询结果
        // queryForObject(String sql, Class<T> requiredType)
//        String customerName = jdbcTemplate.queryForObject("select customer_name from T_HAO_COALA_ACCOUNT where id= '2'", String.class);
//        CoalaAccount account = jdbcTemplate.queryForObject("select customer_name from T_HAO_COALA_ACCOUNT where id= '2'", CoalaAccount.class);
//        Integer sum = jdbcTemplate.queryForObject("select sum(bill_date) from T_HAO_COALA_ACCOUNT where sex = '1'", Integer.class);
//        String count = jdbcTemplate.queryForObject("select count(*) from T_HAO_COALA_ACCOUNT where sex = '1'", String.class);


        // queryForObject(String sql, Object[] args, int[] argTypes, RowMapper<T> rowMapper)
//        String sql = "select count(*) from T_HAO_COALA_ACCOUNT where sex = ? and bill_date = ? ";
//        Object[] param = {"1", 15};
//        int[] index = {1, 2};
//        Integer count = jdbcTemplate.queryForObject(sql, param, index, new RowMapper<Integer>() {
//            @Override
//            public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
//                return rs.getInt(1);
//            }
//        });
//
//        sql = "select customer_name from T_HAO_COALA_ACCOUNT where id= '2'";
//        String customerName = jdbcTemplate.queryForObject(sql, null, null, new RowMapper<String>() {
//            @Override
//            public String mapRow(ResultSet rs, int rowNum) throws SQLException {
//                return rs.getString(1);
//            }
//        });


        // queryForObject(String sql, @Nullable Object[] args, RowMapper<T> rowMapper)
//        String sql = "select count(*) from T_HAO_COALA_ACCOUNT where sex = ? and bill_date = ? ";
//        Object[] param = {"1", 10};
//        Integer count = jdbcTemplate.queryForObject(sql, param, new RowMapper<Integer>() {
//            @Override
//            public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
//                return rs.getInt(1);
//            }
//        });
//        sql = "select id,customer_name,sex,bill_date from T_HAO_COALA_ACCOUNT where sex = ? and bill_date > ?";
//        jdbcTemplate.queryForObject(sql, param, new RowMapper<String>() {
//            @Override
//            public String mapRow(ResultSet rs, int rowNum) throws SQLException {
//                return rs.getString(1);
//            }
//        });
//        logger.info("客户姓名：[{}]", customerName);
//        logger.info("账单日总和：[{}]", sum);
//        logger.info("总数量：[{}]", count);


        // queryForObject(String sql, RowMapper<T> rowMapper, @Nullable Object... args)
//        jdbcTemplate.queryForObject("", new RowMapper<Object>() {
//            @Override
//            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
//                return null;
//            }
//        },1);
        // 不举例


        // queryForObject(String sql, Object[] args, int[] argTypes, Class<T> requiredType)
//        String sql = "select count(*) from T_HAO_COALA_ACCOUNT where sex = ? and bill_date = ? ";
//        Object[] param = {"1", 10};
//        int[] index = {1, 2};
//        Integer count = jdbcTemplate.queryForObject(sql, param, index, Integer.class);

//        logger.info("客户姓名：[{}]", customerName);
//        logger.info("账单日总和：[{}]", sum);
//        logger.info("总数量：[{}]", count);

        // queryForObject(String sql, Object[] args, Class<T> requiredType)

        // queryForObject(String sql, Class<T> requiredType, @Nullable Object... args)
        return null;
    }

    @Override
    public Object queryForList(Object object) {

        // queryForList(String sql, Class<T> elementType)
        // 查询多行单列，返回指定类型的List集合
//        String sql = "select customer_name from T_HAO_COALA_ACCOUNT where sex = '3' and bill_date > 10 ";
//        List<String> names = jdbcTemplate.queryForList(sql, String.class);


        // queryForList(String sql)
        // 查询多行多列
//        String sql = "select id,customer_name,sex,bill_date from T_HAO_COALA_ACCOUNT where sex = '3' and bill_date > 12 ";
//        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);


        // queryForList(String sql, Object[] args, int[] argTypes, Class<T> elementType)
        // 查询多行单列
//        String sql = "select customer_name from T_HAO_COALA_ACCOUNT where sex = ? and bill_date > ? ";
//        Object[] param = {"1", 12};
//        int[] index = {1, 2};
//        List<String> list = jdbcTemplate.queryForList(sql, param, index, String.class);


        // queryForList(String sql, Object[] args, Class<T> elementType)
        // 省略 ...

        // queryForList(String sql, Class<T> elementType, @Nullable Object... args)
        // 多行单列
//        String sql = "select customer_name from T_HAO_COALA_ACCOUNT where sex = ? and bill_date > ? ";
//        List<String> list = jdbcTemplate.queryForList(sql, String.class, "1", 12);

        // queryForList(String sql, Object[] args, int[] argTypes)
//        String sql = "select id,customer_name,sex,bill_date from T_HAO_COALA_ACCOUNT where sex = ? and bill_date > ? ";
//        Object[] param = {"1", 12};
//        int[] index = {1, 2};
//        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql, param, index);


        // queryForList(String sql, @Nullable Object... args)
        String sql = "select id,customer_name,sex,bill_date from T_HAO_COALA_ACCOUNT where sex = ? and bill_date > ? ";
//        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql, "1", 12);
        Object[] param = {"1", 12};
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql, param);

        return maps;


    }

    @Override
    public Object execute() {
        // execute(final String sql)
//        jdbcTemplate.execute("select * from T_HAO_COALA_ACCOUNT");
//        jdbcTemplate.execute("insert into T_HAO_COALA_ACCOUNT (id,customer_name,sex,principal,bill_date) values (sys_guid(),'测试','1',222,15)");
//        jdbcTemplate.execute("update T_HAO_COALA_ACCOUNT set birthday=to_date('2000-10-07','yyyy-mm-dd') where id='834747A641D24410BF602FE448C61801'");
//        jdbcTemplate.execute("delete T_HAO_COALA_ACCOUNT where id = '1' ");
//        jdbcTemplate.execute("COMMENT ON COLUMN T_HAO_COALA_ACCOUNT.BIRTHDAY IS '客户出生日期'");
//        jdbcTemplate.execute("truncate table T_HAO_COALA_ACCOUNT"); // 清空表数据
//        jdbcTemplate.execute("drop table T_HAO_COALA_ACCOUNT"); // 删除表
//        jdbcTemplate.execute("alter table t_hao_coala_account add test_column varchar2(200)"); // 修改表结构 添加字段
//        jdbcTemplate.execute("alter table t_hao_coala_account drop column test_column"); // 修改表结构 删除字段
//        jdbcTemplate.execute("begin add_data_to_account; end;"); // 执行存储结构
//        jdbcTemplate.execute("create table test_execute_new_table(id  VARCHAR2(50),name varchar2(100) )tablespace TEST_DATA"); // 新建表

        // execute(StatementCallback<T> action)
        // 新增数据
//        jdbcTemplate.execute(new StatementCallback() {
//            @Override
//            public Object doInStatement(Statement stmt) throws SQLException, DataAccessException {
//                return stmt.execute("insert into T_HAO_COALA_ACCOUNT (id,customer_name,sex,principal,bill_date) values (sys_guid(),'测试22','1',222,15)");
//            }
//        });

        // execute(StatementCallback<T> action)
//        CoalaAccount execute = jdbcTemplate.execute((StatementCallback<CoalaAccount>) stmt -> {
//            ResultSet rs = stmt.executeQuery("select id,customer_name,sex,bill_date from T_HAO_COALA_ACCOUNT where id='2'");
//            CoalaAccount account = new CoalaAccount();
//            while (rs.next()) {
//                account.setId(rs.getString(1));
//                account.setCustomerName(rs.getString(2));
//                account.setSex(rs.getString(3).charAt(0));
//                account.setBillDate(rs.getInt(4));
//            }
//            return account;
//        });

        // execute(ConnectionCallback<T> action)
//        String sql = "select id,customer_name,sex,bill_date from T_HAO_COALA_ACCOUNT where sex='1' ";
//        List<CoalaAccount> list = new ArrayList<>();
//        List<CoalaAccount> execute = jdbcTemplate.execute(new ConnectionCallback<List<CoalaAccount>>() {
//            @Override
//            public List<CoalaAccount> doInConnection(Connection con) throws SQLException, DataAccessException {
//                try {
//                    con.setAutoCommit(false); // 设置为手工提交事务，默认是自动提交
//                    PreparedStatement ps = con.prepareStatement(sql);
//                    ps.execute(); // 执行sql
//                    ResultSet rs = ps.getResultSet();
//                    while (rs.next()) {
//                        CoalaAccount account = new CoalaAccount();
//                        account.setId(rs.getString(1));
//                        account.setCustomerName(rs.getString(2));
//                        account.setSex(rs.getString(3).charAt(0));
//                        account.setBillDate(rs.getInt(4));
//                        list.add(account);
//                    }
//                    con.commit(); // 提交事务，查询时不需要提交
//                } catch (Exception e) {
//                    logger.error("sql处理异常：", e);
//                    con.rollback(); // 事务回滚
//                }
//                return list;
//            }
//        });

        // execute(String sql, PreparedStatementCallback<T> action)
//        String sql = "select id,customer_name,sex,bill_date from T_HAO_COALA_ACCOUNT where sex='1' ";
//        CoalaAccount execute = jdbcTemplate.execute(sql, (PreparedStatementCallback<CoalaAccount>) ps -> {
//            ps.execute();
//            ResultSet rs = ps.getResultSet();
//            CoalaAccount account = new CoalaAccount();
//            while (rs.next()) {
//                account.setId(rs.getString(1));
//                account.setCustomerName(rs.getString(2));
//                account.setSex(rs.getString(3).charAt(0));
//                account.setBillDate(rs.getInt(4));
//            }
//            return account;
//        });
//        int[] result = jdbcTemplate.execute("update T_HAO_COALA_ACCOUNT set bill_date = 1 where arrearage = ? ", (PreparedStatementCallback<int[]>) ps -> {
//            ps.setBigDecimal(1, new BigDecimal("2000"));
//            ps.addBatch();
//            ps.setBigDecimal(1, new BigDecimal("28500.00"));
//            ps.addBatch();
//            int[] ints = ps.executeBatch();
//            return ints;
//        });
//        logger.info("执行结果：[{}]条数据", Arrays.toString(result));

        // execute(String callString, CallableStatementCallback<T> action)
        // 测试执行添加数据的存储过程
//        jdbcTemplate.execute("{call add_data_to_account}", new CallableStatementCallback() {
//            @Override
//            public Object doInCallableStatement(CallableStatement cs) throws SQLException, DataAccessException {
//                cs.execute();
//                return null;
//            }
//        });
//        // 测试执行添加数据的存储过程 注意sql语句的传参格式，建议使用{}形式
//        jdbcTemplate.execute("begin add_data_to_account;end;", (CallableStatementCallback) cs -> {
//            cs.execute();
//            return null;
//        });
//        // 测试执行添加数据的存储过程，此存储过程带有两个输入参数
//        jdbcTemplate.execute("{call insert_data_to_user(?,?)}", (CallableStatementCallback) cs -> {
//            cs.setString(1,"姓名：测试");
//            cs.setString(2,"地点：北京");
//            cs.execute();
//            return null;
//        });
//        // 执行取数据的存储过程，存储过程第一个是输入参数，第二个和第三个是输出参数
//        String execute = jdbcTemplate.execute("{call get_data_from_product(?,?,?)}", (CallableStatementCallback<String>) cs -> {
//            cs.setInt(1, 2);
//            cs.registerOutParameter(2, Types.VARCHAR); // 注册OUT参数
//            cs.registerOutParameter(3, Types.INTEGER); // 注册OUT参数
//            cs.executeUpdate();
//            String name = cs.getNString(2);
//            int count = cs.getInt(3);
//            return "货物名称：" + name + "   数量：" + count;
//        });

        // execute(PreparedStatementCreator psc, PreparedStatementCallback<T> action)
//        CoalaAccount coalaAccount = jdbcTemplate.execute(con -> con.prepareStatement("select id,customer_name,sex,bill_date from T_HAO_COALA_ACCOUNT where id = '2222' "), (PreparedStatementCallback<CoalaAccount>) ps -> {
//            ps.execute();
//            ResultSet resultSet = ps.getResultSet();
//            CoalaAccount account = new CoalaAccount();
//            while (resultSet.next()) {
//                account.setId(resultSet.getString(1));
//                account.setCustomerName(resultSet.getString(2));
//                account.setSex(resultSet.getString(3).charAt(0));
//                account.setBillDate(resultSet.getInt(4));
//            }
//            return account;
//        });
//
//        String sql = "insert into T_HAO_COALA_ACCOUNT (id,customer_name,sex,principal,bill_date) values (sys_guid(),?,?,?,?)";
//        Integer execute = jdbcTemplate.execute(new PreparedStatementCreator() {
//            @Override
//            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
//                PreparedStatement preparedStatement = con.prepareStatement(sql);
//                preparedStatement.setString(1, "测试姓名：哈哈哈");
//                preparedStatement.setString(2, "1");
//                preparedStatement.setBigDecimal(3, new BigDecimal("5000.00"));
//                preparedStatement.setInt(4, 3);
//                return preparedStatement;
//            }
//        }, new PreparedStatementCallback<Integer>() {
//            @Override
//            public Integer doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
//                ps.executeUpdate();
//                int updateCount = ps.getUpdateCount();
//                logger.info("是否新增成功：{}", updateCount == 1);
//                return updateCount;
//            }
//        });

        // execute(CallableStatementCreator csc, CallableStatementCallback<T> action)
        // 处理存储过程的
//        Object execute = jdbcTemplate.execute(new CallableStatementCreator() {
//            @Override
//            public CallableStatement createCallableStatement(Connection con) throws SQLException {
//                CallableStatement callableStatement = con.prepareCall("{call get_data_from_product(?,?,?)}");
//                callableStatement.setInt(1, 3); // 输入参数 主键ID的值
//                callableStatement.registerOutParameter(2, Types.VARCHAR); // 注册OUT参数
//                callableStatement.registerOutParameter(3, Types.INTEGER); // 注册OUT参数
//                return callableStatement;
//            }
//        }, new CallableStatementCallback<Object>() {
//            @Override
//            public Object doInCallableStatement(CallableStatement cs) throws SQLException, DataAccessException {
//                cs.execute();
//                ResultSet resultSet = cs.getResultSet();
//                String name = cs.getNString(2);
//                int count = cs.getInt(3);
//                return "货物名称：" + name + "   数量：" + count;
//            }
//        });
//        // 通过CallableStatementCreatorFactory工厂创建CallableStatementCreator对象
//        CallableStatementCreatorFactory factory = new CallableStatementCreatorFactory("{call get_data_from_product(?,?,?)}");
//        factory.addParameter(new SqlParameter("id", Types.INTEGER)); // 设置存储过程的输入参数
//        factory.addParameter(new SqlOutParameter("name", Types.VARCHAR)); // 设置存储过程的输出参数
//        factory.addParameter(new SqlOutParameter("count", Types.INTEGER)); // 设置存储过程的输出参数
//        HashMap<String, Object> param = new HashMap<>();
//        param.put("id", 6);
//        jdbcTemplate.execute(factory.newCallableStatementCreator(param), (CallableStatementCallback<Object>) cs -> {
//            cs.execute();
//            String name = cs.getString(2);
//            int count = cs.getInt(3);
//            return "货物名称：" + name + "   数量：" + count;
//        });

        logger.info("execute()方法执行完毕");
        return null;
    }

    @Override
    public void update() {


        // update(final String sql)
//        String insertIntoSql = "insert into T_HAO_COALA_ACCOUNT (id,customer_name,sex,birthday,balance,arrearage,bill_date) values ('1','test','2',trunc(sysdate),5500,2000,20) ";
//        String deleteSql = "delete T_HAO_COALA_ACCOUNT where id = '11' ";
//        String updateSql = "update T_HAO_COALA_ACCOUNT set bill_date = 20 where id = '2'";
//        String procedureSql = "{call insert_data_to_user('张三2','北京2')";
//        jdbcTemplate.update(insertIntoSql);
//        jdbcTemplate.update(deleteSql);
//        jdbcTemplate.update(updateSql);
//        jdbcTemplate.update(procedureSql);


        // update(PreparedStatementCreator psc)
//        String insertIntoSql = "insert into T_HAO_COALA_ACCOUNT (id,customer_name,sex,birthday,balance,arrearage,bill_date) values ('1','test','2',trunc(sysdate),5500,2000,20) ";
//        String deleteSql = "delete T_HAO_COALA_ACCOUNT where id = ? ";
//        String updateSql = "update T_HAO_COALA_ACCOUNT set bill_date = 20 where id = '2'";
//        String procedureSql = "{call insert_data_to_user('张三2','北京2')";

//        jdbcTemplate.update(new PreparedStatementCreator() {
//            @Override
//            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
//                PreparedStatement ps = con.prepareStatement(insertIntoSql);
//                return ps;
//            }
//        });
//        jdbcTemplate.update(new PreparedStatementCreator() {
//            @Override
//            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
//                PreparedStatement ps = con.prepareStatement(deleteSql);
//                ps.setString(1,"ED57637E0B7C446BA37D039E1E40AA97");
//                return ps;
//            }
//        });


        // update(final PreparedStatementCreator psc, final KeyHolder generatedKeyHolder)
//        String insertIntoSql = "insert into T_HAO_COALA_ACCOUNT (id,customer_name,sex,birthday,balance,arrearage,bill_date) values (sys_guid(),'test','2',trunc(sysdate),5500,2000,20) ";
//        String deleteSql = "delete T_HAO_COALA_ACCOUNT where id = ? ";
//        String updateSql = "update T_HAO_COALA_ACCOUNT set bill_date = 20 where id = '2'";
//        String procedureSql = "{call insert_data_to_user('张三2','北京2')";

//        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
//        jdbcTemplate.update(new PreparedStatementCreator() {
//            @Override
//            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
//                return con.prepareStatement(insertIntoSql, new String[]{"id"});
//            }
//        }, keyHolder);
//        Map<String, Object> keys = keyHolder.getKeys();
//        String id = (String) keys.get("id");
//        logger.info("添加的主键ID：[{}]", id);
        // 下面获取主键ID是再MySql数据库中操作
//        Number key = keyHolder.getKey();
//        if (key == null) return;
//        byte byteValue = key.byteValue();
//        String toString = key.toString();
//        int intValue = key.intValue();
//        long longValue = key.longValue();
//
//        logger.info("{},{},{},{},{}", byteValue, toString, intValue, longValue, id);


        // update(String sql, @Nullable PreparedStatementSetter pss)
//        String insertIntoSql = "insert into T_HAO_COALA_ACCOUNT (id,customer_name,sex,birthday,balance,arrearage,bill_date) values (sys_guid(),'testtest','2',trunc(sysdate),5500,2000,20) ";
//        String deleteSql = "delete T_HAO_COALA_ACCOUNT where id = ? ";
//        String updateSql = "update T_HAO_COALA_ACCOUNT set bill_date = 20 where id = '2'";
//        String procedureSql = "{call insert_data_to_user('张三2','北京2')";

//        jdbcTemplate.update(insertIntoSql, new PreparedStatementSetter() {
//            @Override
//            public void setValues(PreparedStatement ps) throws SQLException {
//
//            }
//        });
//        jdbcTemplate.update(deleteSql, new PreparedStatementSetter() {
//            @Override
//            public void setValues(PreparedStatement ps) throws SQLException {
//                ps.setString(1, "09CCE5E3E7D14C118B6B624AD8E7BA24");
//            }
//        });


        // update(String sql, Object[] args, int[] argTypes)
//        String insertIntoSql = "insert into T_HAO_COALA_ACCOUNT (id,customer_name,sex,birthday,balance,arrearage,bill_date) values (sys_guid(),'testtest','2',trunc(sysdate),5500,2000,20) ";
//        String deleteSql = "delete T_HAO_COALA_ACCOUNT where id = ? ";
//        String updateSql = "update T_HAO_COALA_ACCOUNT set CUSTOMER_NAME =? where id =? ";
//        String procedureSql = "{call insert_data_to_user('张三2','北京2')";

//        Object[] param = {"李四2222", "2"};
        int[] index = {Types.VARCHAR, Types.VARCHAR};
//        jdbcTemplate.update(updateSql, param, index);


        // update(String sql, @Nullable Object... args)
        String updateSql = "update T_HAO_COALA_ACCOUNT set CUSTOMER_NAME =? where id =? ";
        Object[] param = {"李四", "2"};
        jdbcTemplate.update(updateSql, param);
    }

    @Override
    public void batchUpdate() throws ParseException {

//         batchUpdate(final String... sql)
//        String updateSql = "update T_HAO_COALA_ACCOUNT set BILL_DATE = 26 where id = '2'";
//        jdbcTemplate.batchUpdate(updateSql);
//
//        // batchUpdate(String sql, final BatchPreparedStatementSetter pss)
//        String updateSql = "update T_HAO_COALA_ACCOUNT set PRINCIPAL =? where id =?";
//        List<CoalaAccount> list = new ArrayList<>();
//        list.add(new CoalaAccount("2", new BigDecimal(5000)));
//        list.add(new CoalaAccount("3", new BigDecimal(6000)));
//        list.add(new CoalaAccount("4", new BigDecimal(7000)));
//        list.add(new CoalaAccount("5", new BigDecimal(8000)));
//        jdbcTemplate.batchUpdate(updateSql, new BatchPreparedStatementSetter() {
//            @Override
//            public void setValues(PreparedStatement ps, int i) throws SQLException {
//                ps.setBigDecimal(1, list.get(i).getPrincipal());
//                ps.setString(2, list.get(i).getId());
//            }
//
//            @Override
//            public int getBatchSize() {
//                return list.size();
//            }
//        });
//
//
//        // batchUpdate(String sql, List<Object[]> batchArgs)
//
//        Object[] obj1 = new Object[3];
//        obj1[0]="batchUpdate_Test_name_1";
//        obj1[1]=10;
//        obj1[2]="2";
//
//        Object[] obj2 = new Object[3];
//        obj2[0]="batchUpdate_Test_name_2";
//        obj2[1]=20;
//        obj2[2]="3";
//
//        List<Object[]> param = new ArrayList<>();
//        param.add(obj1);
//        param.add(obj2);
//        jdbcTemplate.batchUpdate("update T_HAO_COALA_ACCOUNT set CUSTOMER_NAME = ?,PRINCIPAL = ? where id = ?",param);
//
//
//        // batchUpdate(String sql, List<Object[]> batchArgs, final int[] argTypes)
//        Object[] obj1 = new Object[3];
//        obj1[0] = "batchUpdate_Test_name_1_type1";
//        obj1[1] = 1001;
//        obj1[2] = "65536F6490C44D45B149F6C1BB54EE3F";
//
//        Object[] obj2 = new Object[3];
//        obj2[0] = "batchUpdate_Test_name_2_type2";
//        obj2[1] = 2002;
//        obj2[2] = "DC78F9F3698F4BE1AA4A3DEF39509271";
//
//        List<Object[]> param = new ArrayList<>();
//        param.add(obj1);
//        param.add(obj2);
//
//        int[] type = new int[3];
//        type[0] = Types.CHAR; // 这里使用 Types.CHAR 也行
//        type[1] = Types.NUMERIC;
//        type[2] = Types.VARCHAR; // 这里使用 Types.VARCHAR 也行
//        jdbcTemplate.batchUpdate("update T_HAO_COALA_ACCOUNT set CUSTOMER_NAME = ?,PRINCIPAL = ? where id = ?", param, type);
//
//        // batchUpdate(String sql, final Collection<T> batchArgs, final int batchSize, final ParameterizedPreparedStatementSetter<T> pss)
//        String updateSql = "update T_HAO_COALA_ACCOUNT set PRINCIPAL =? , BIRTHDAY = ? where id =?";
//        List<CoalaAccount> list = new ArrayList<>();
//        list.add(new CoalaAccount("2", new BigDecimal(5), DateUtils.parseDate("2020-01-01", "yyyy-MM-dd")));
//        list.add(new CoalaAccount("3", new BigDecimal(6), DateUtils.parseDate("2020-01-02", "yyyy-MM-dd")));
//        list.add(new CoalaAccount("4", new BigDecimal(7), DateUtils.parseDate("2020-01-03", "yyyy-MM-dd")));
//        list.add(new CoalaAccount("5", new BigDecimal(8), DateUtils.parseDate("2020-01-04", "yyyy-MM-dd")));
//        jdbcTemplate.batchUpdate(updateSql, list, list.size(), new ParameterizedPreparedStatementSetter<CoalaAccount>() {
//            @Override
//            public void setValues(PreparedStatement ps, CoalaAccount argument) throws SQLException {
//                ps.setBigDecimal(1, argument.getPrincipal());
//                ps.setDate(2, new Date(argument.getBirthday().getTime()));
//                ps.setString(3, argument.getId());
//            }
//        });

    }

    @Override
    public void call() {

    }

    private void opertion() {
        map.clear();
        map.put(1, 1);
        map.put(2, 2);
    }
}
