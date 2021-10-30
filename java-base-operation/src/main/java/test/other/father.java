package test.other;

/**
 * @author ChenHol.Wong
 * @create 2020/2/2 20:56
 */
public class father {
    private test test;
    public String aa = "123";
    protected int bb;

    public int getBb() {
        return bb;
    }

    public void setBb(int bb) {
        this.bb = bb;
    }

    public void test() {
        System.out.println("aaa");
    }
}
