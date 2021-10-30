package test.other;

/**
 * 测试一些运算
 * 参考博客：  https://blog.csdn.net/whxjason/article/details/111194318
 *
 * @author ChenHol.Wong
 * @create 2020/12/15 10:14
 */
public class TestYunSuan {
    public static void main(String[] args) {
        // |
        // |是针对二进制的二目运算符。运算规则：两个二进制数值如果在同一位上至少有一个1，则结果中该位为1，否则为0，比如1011 | 0010 = 1011。
//        System.out.println(1 | 2 ); // 1 | 10 = 11 -> 3
//        System.out.println(2 | 1 ); // 10 | 1 = 11 -> 3
//        System.out.println();
//        System.out.println(1 | 3 ); // 1 | 11 = 11 -> 3
//        System.out.println(2 | 3 ); // 10 | 11 = 11 -> 3
//        System.out.println();
//        System.out.println(1 | 4); // 1| 100 = 101 -> 5
//        System.out.println(2 | 4); // 10 | 100 = 110 -> 6
//        System.out.println(3 | 4); // 11 | 100 = 111 -> 7
//        System.out.println();
//        System.out.println(1 | 5); // 1 | 101 = 101 -> 5
//        System.out.println(2 | 5); // 10 | 101 = 111 -> 7
//        System.out.println(3 | 5); // 11 | 101 = 111 -> 7
//        System.out.println(4 | 5); // 100 | 101 = 101 ->5
//        System.out.println(true | false);
//        System.out.println(true | true);
//        System.out.println(false | true);
//        System.out.println(false | false);


        // & &是是针对二进制的二目运算符。两个二进制数值如果在同一位上都是1，则结果中该位为1，否则为0 。
//        System.out.println(1 & 2 ); // 1 & 10 = 00 -> 0
//        System.out.println(2 & 1 ); // 10 & 1 = 00 -> 0
//        System.out.println();
//        System.out.println(1 & 3 ); // 1 & 11 = 01 -> 1
//        System.out.println(2 & 3 ); // 10 & 11 = 10 -> 2
//        System.out.println();
//        System.out.println(1 & 4); // 1 & 100 = 000 -> 0
//        System.out.println(2 & 4); // 10 & 100 = 000 -> 0
//        System.out.println(3 & 4); // 11 & 100 = 000 -> 0
//        System.out.println();
//        System.out.println(1 & 5); // 1 & 101 = 001 > 1
//        System.out.println(2 & 5); // 10 & 101 = 000 -> 0
//        System.out.println(3 & 5); // 11 & 101 = 001 -> 1
//        System.out.println(4 & 5); // 100 & 101 = 100 -> 4
//
//        System.out.println(true & false);
//        System.out.println(true & true);
//        System.out.println(false & true);
//        System.out.println(false & false);

        // ^ ^是针对二进制的二目运算符。运算规则：两个二进制数值如果在同一位上相同，则该位结果为0，否则为1。
//        System.out.println(1 ^ 2 ); // 1 & 10 = 11 -> 3
//        System.out.println(2 ^ 1 ); // 10 & 1 = 11 -> 3
//        System.out.println();
//        System.out.println(1 ^ 3 ); // 1 & 11 = 10 -> 2
//        System.out.println(2 ^ 3 ); // 10 & 11 = 01 -> 1
//        System.out.println();
//        System.out.println(1 ^ 4); // 1 & 100 = 101 -> 5
//        System.out.println(2 ^ 4); // 10 & 100 = 110 -> 6
//        System.out.println(3 ^ 4); // 11 & 100 = 111 -> 7
//        System.out.println();
//        System.out.println(1 ^ 5); // 1 & 101 = 100 > 4
//        System.out.println(2 ^ 5); // 10 & 101 = 111 -> 7
//        System.out.println(3 ^ 5); // 11 & 101 = 110 -> 6
//        System.out.println(4 ^ 5); // 100 & 101 = 001 -> 1
//
//
//        System.out.println(20 ^ 5);
//        System.out.println(true ^ false);
//        System.out.println(true ^ true);
//        System.out.println(false ^ true);
//        System.out.println(false ^ false);

        // || 逻辑或运算
//        System.out.println(true || false);
//        System.out.println(false || true);
//        System.out.println(true || true);
//        System.out.println(false || false);

        // >> 位运算
        // >>x（常数）: 向右移动x位（顶点在哪个方向就往哪个方向移动），如果该数是正数，则高位（最左边）补x个0，如果是负数，则最高位补x个1。
//        -38原码：10100110
//        反码：11011001
//        补码：11011010
//        右移两位：_ _ 110110
//        补码左边添两个1，得到：11110110
//        反码：11110101
//        原码：10001010
//        结果为：-10（十进制）
        System.out.println(-38 >> 2);


//        38原码：00100110（首位0代表符号＋）
//        右移两位：_ _ 001001
//        左边补两个0，得到：00001001
//        结果为：9（十进制）
        System.out.println(38 >> 2);

        System.out.println(-2 >>> 2);
        System.out.println(-38 >>> 2);
        System.out.println(38 >>> 2);

        System.out.println(38 << 3);
        System.out.println(-38 << 3);


    }
}
