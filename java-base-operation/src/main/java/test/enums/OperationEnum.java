package test.enums;

/**
 * 加减乘除操作枚举。
 * <pre>枚举的最基本用法,在枚举中定义一个方法</pre>
 *
 * @author WongChenHoll
 * @date 2022-11-10 10:57
 **/
public enum OperationEnum {
    PLUS, MINUS, TIMES, DIVIDE;


    /**
     * 对参数x和y进行加减乘除运算
     */
    public double apply(double x, double y) {
        switch (this) {
            case PLUS:
                return x + y;
            case MINUS:
                return x - y;
            case TIMES:
                return x * y;
            case DIVIDE:
                return x / y;
        }
        throw new AssertionError("Unknown Operation" + this);
    }
}
