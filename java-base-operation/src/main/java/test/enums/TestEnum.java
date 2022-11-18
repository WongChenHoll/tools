package test.enums;

/**
 * @author WongChenHoll
 * @description
 * @date 2022-11-10 星期四 10:58
 **/
public class TestEnum {
    public static void main(String[] args) {

        System.out.println(OperationEnum.PLUS.apply(1.0, 3.4));
        System.out.println(OperationEnum.TIMES.apply(3.0, 1.3));

        System.out.println(OperationFunctionEnum.MINUS.apply(10, 3));
        System.out.println(OperationFunctionEnum.DIVIDE.apply(9.9, 3.3));


        System.out.println(OperationAbstractMethodEnum.MINUS.apply(40, 8));

        System.out.println(SqlFunctionEnum.SELECT.buildSQL("C_1,C_2", "T_USER", null, null));
    }
}
