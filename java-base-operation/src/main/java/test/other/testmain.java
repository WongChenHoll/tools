package test.other;

/**
 * @author ChenHol.Wong
 * @create 2020/2/2 20:57
 */
public class testmain {
    public static void main(String[] args) {

        System.out.println(0x7fffffff);
        father father = new father();
        father.test();
        System.out.println(father.aa);
        child child = new child();
        child.setBb(1111);
        child.test();
        System.out.println(child.aa+"   "+child.bb);
    }
}
