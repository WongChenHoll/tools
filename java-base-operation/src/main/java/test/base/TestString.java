package test.base;

/**
 * 测试字符串
 *
 * @author ChenHol.Wong
 * @create 2020/7/20 20:43
 */
public class TestString {
    public static void main(String[] args) {
        // 将字符串反转
        String test = "1234567890";
        StringBuilder stringBuilder = new StringBuilder(test);
        System.out.println(stringBuilder.reverse());

        System.out.println(getNumber("123456789abcdef987654321kkkk"));
        System.out.println(getNumber("+yyyyy123456789kkkk222222"));
        System.out.println(getNumber("-123456789abcdefg456778jkhgg"));
        System.out.println(getNumber("       1234567890123456789ghfhfgfhfhfhfh        "));
        System.out.println(getNumber("    -dbfrgt1234567890123456789lklkjhuhghvhg122258468135      "));
        System.out.println(getNumber("         +123456789abcdefg456778jkhgg        "));
    }

    /**
     * 提取字符串中的首次连续出现的数字
     * 首先去除字符串两边的空白字符
     * 如果第一个字符是+或者-，则保留
     *
     * @param str 需要提取的字符串
     * @return 提取后的Integer对象数字
     */
    public static Integer getNumber(String str) {

        if (null == str || str.trim() == "") {
            return null;
        }

        String trim = str.trim();
        char[] chars = trim.toCharArray();
        String symbol = "";
        String temp = "";
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (i == 0) {
                if (c == '+') symbol = "+";
                if (c == '-') symbol = "-";
            }
            if (c >= '0' && c <= '9') {
                temp += c;
            }
            if (temp != "" && (c > '9' || c < '0')) {
                break;
            }
        }
        Long aLong = Long.valueOf(symbol + temp);
        if (aLong > Integer.MAX_VALUE || aLong < Integer.MIN_VALUE) {
            return null;
        }
        return Integer.valueOf(String.valueOf(aLong));
    }
}
