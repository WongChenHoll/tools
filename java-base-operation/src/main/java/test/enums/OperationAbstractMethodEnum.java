package test.enums;

/**
 * 在枚举中定义一个抽象方法。
 * 每个枚举中的常量都要实现重写这个抽象方法，称之为“特定于常量的方法实现”
 * 注意写法：每个常量的方法实现在大括号中
 *
 * @author WongChenHoll
 * @date 2022-11-18 17:02
 **/
public enum OperationAbstractMethodEnum {
    PLUS {
        @Override
        public double apply(double x, double y) {
            return x + y;
        }
    },
    MINUS {
        @Override
        public double apply(double x, double y) {
            return x - y;
        }
    },
    TIMES {
        @Override
        public double apply(double x, double y) {
            return x * y;
        }
    },
    DIVIDE {
        @Override
        public double apply(double x, double y) {
            return x / y;
        }
    };

    public abstract double apply(double x, double y);
}
