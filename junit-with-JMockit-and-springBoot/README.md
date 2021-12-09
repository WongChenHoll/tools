## Q1: 执行时 jacoco 覆盖率文件没有生成。

Skipping JaCoCo execution due to missing execution data file. 原因是 jacoco 插件适用了参数<argline>和 maven-surefire-plugin
argLine冲突。 解决1： 删除maven-surefire-plugin插件下的 argLine配置。 解决2: maven-surefire-plugin插件下的 argLine配置.放在全局位置。

## 使用JMockit中的Expectations进行单元测试报错

是因为

```xml

<dependency>
    <groupId>org.jmockit</groupId>
    <artifactId>jmockit</artifactId>
    <version>1.49</version>
    <scope>test</scope>
</dependency>
```

此版本中不允许使用Expectations，推荐使用MockUp。建议更换低版本，比如：1.36。

## @Mocked和@Injectable

@Mocked是针对其修饰类的所有实例。@Injectable只是针对其修饰的实例。@Injectable对类的静态方法，构造函数没有影响。因为它只影响某一个实例嘛！  
可以使用@Injectable修饰一个类，测试其中的静态方法。

## Expectations和MockUp

用Expectations无法mock native方法；  
用Expectations无法mock private方法；  
MockUp可以mock所有类型方法。  

```java
        import org.junit.Test;
        import mockit.Expectations;
        import static org.junit.Assert.assertNull;
        
        // Expectations的使用方法：
        @Test
        public void test(){
            new Expectations() {
                {
                    mailService.sendMail(testUserId, anyString); // 被mock的方法
                    result = true; // 设置方法的返回值，可以是任何类型的返回值
            
                    userCheckService.check(testUserId); // 被mock的方法
                    result = true; // 设置方法的返回值
                }
            };
            Assert.assertTrue(orderService.submitOrder(testUserId, testItemId)); // 注意参数的传递，需要和Expectations中的参数一致。
        }
        
        // MockUp的使用：
        @Test
        public void test(){
            new MockUp<JdbcTemplate>(JdbcTemplate.class){
                @Mock
                public int[] batchUpdate(String sql, final BatchPreparedStatementSetter pss) throws DataAccessException {
                     return new int[]{1,2}; // 可以自己设置任意返回的数据，不过类型要一致
                }
            };
        Assert.assertTrue(1==jdbcTemplate.batchUpdate(anyString,null)[0]);
        }
        
```

## 异常抛出ExpectedException的使用:  

```java
import mockit.Injectable;
import mockit.Tested;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

public class ServiceTest{
    @Tested
    private UserServiceImpl userService; // 这里必须是类，不能是接口

    @Injectable
    private UserDao userDao;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void test() throws Exception {
        // 测试抛出异常，下面这两行一定要有的，并且异常信息要和被抛出的异常信息一致。
        expectedException.expect(Exception.class);
        expectedException.expectMessage("用户不存在");
        
        new Expectations() {
            {
                userDao.getUsers();
                result = null;
            }
        };
        assertNull(userService.getUsers());
    }
    
}

```

