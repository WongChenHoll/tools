package test.other;

/**
 * @author WongChenHoll
 * @description
 * @date 2023-3-29 星期三 9:44
 **/
public class TestFinally {
    public static void main(String[] args) {
        System.out.println("结果：" + test(20));
        System.out.println("结果：" + test(0));
        System.out.println();
        System.out.println("结果：" + testParam(30));
        System.out.println("结果：" + testParam(0));
    }

    public static int test(int arg) {
        try {
            if (10 / arg > 1) {
                System.out.println("=== 1 ===");
                return 1;
            } else {
                System.out.println("=== 2 ===");
                return 2;
            }
        } catch (Exception e) {
            System.out.println("异常：" + e.getMessage());
            return -1;
        } finally {
            System.out.println("=======");
        }
    }

    public static int testParam(int arg) {
        int rs;
        try {
            if (10 / arg > 1) {
                System.out.println("=== 1 ===");
                rs = 1;
            } else {
                System.out.println("=== 2 ===");
                rs = 2;
            }
        } catch (Exception e) {
            System.out.println("异常：" + e.getMessage());
            rs = -1;
        } finally {
            System.out.println("=======");
        }
        return rs;
    }
}
