package test.base;

/**
 * <pre>
 *     a++和++a 都属于自增运算符，区别是对变量a的值进行自增的时机不同。a++是先进行取值，后进行自增。++a是先进行自增，后进行取值。
 *
 *      示例
 *       例如：假设 x=3，y=4;
 *      (1) (x++)+(++x)=8
 *       解释：对于第一个（x++），因为x++是先取值后自增，所以（x++）所取得值为3，然后x进行自增，此时x=4；对于第二个（++x），
 *       因为++x是先自增后取值，所以（++x）所取得值为5，此时x=5，所以结果为8。
 * </pre>
 *
 * @author WongChenHoll
 * @date 2022-5-26 星期四 17:25
 **/
public class TestVariable {
    public static void main(String[] args) {
        int a = 0;
        int b = 1;
        int c = 2;
        System.out.println(" a：" + a + " b：" + b + " c：" + c);

        c = b++;
        System.out.println(" a：" + a + " b：" + b + " c：" + c);

        b = ++c;
        System.out.println(" a：" + a + " b：" + b + " c：" + c);

        a = c + b;
        System.out.println(" a：" + a + " b：" + b + " c：" + c);
    }
}
