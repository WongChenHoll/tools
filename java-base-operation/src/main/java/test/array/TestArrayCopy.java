package test.array;

/**
 * 测试 System.arraycopy() 方法
 *
 * @author ChenHol.Wong
 * @create 2020/8/10 20:49
 */
public class TestArrayCopy {
    public static void main(String[] args) {

        int[] src = new int[]{1, 2, 3, 4, 5};
        int[] dest = new int[10];

        System.arraycopy(src, 0, dest, 3, 5);

        for (int a : src) {
            System.out.print(a + " ");
        }
        System.out.println("");
        for (int a : dest) {
            System.out.print(a + " ");
        }
    }
}
