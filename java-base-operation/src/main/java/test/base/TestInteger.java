package test.base;

/**
 * 测试Integer对象
 * <p/>
 * Integer 的缓存范围-128到127。
 *
 * @author ChenHol.Wong
 * @create 2020/8/30 14:17
 */
public class TestInteger {
    public static void main(String[] args) {
        System.out.println(0x80000000); // -2147483648
        System.out.println(0x7fffffff); // 2147483647
    }
}
