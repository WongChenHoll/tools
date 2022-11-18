package test.enums;

import java.util.function.DoubleBinaryOperator;

/**
 * @author WongChenHoll
 * @date 2022-11-10 11:07
 **/
public enum OperationFunctionEnum {
    PLUS(Double::sum),
    MINUS((x, y) -> x - y),
    TIMES((x, y) -> x * y),
    DIVIDE((x, y) -> x / y);

    private final DoubleBinaryOperator operator;

    OperationFunctionEnum(DoubleBinaryOperator operator) {
        this.operator = operator;
    }

    public double apply(double x, double y) {
        return operator.applyAsDouble(x, y);
    }
}
