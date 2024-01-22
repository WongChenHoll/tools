package test.systems;

import java.util.Map;

/**
 * @author WongChenHoll
 * @description
 * @date 2023-3-16 星期四 10:16
 **/
public class TestSystem {
    public static void main(String[] args) {
        System.out.println("Runtime.getRuntime().availableProcessors() 机器的CPU核心数:" + Runtime.getRuntime().availableProcessors());
        System.out.println("Runtime.getRuntime().freeMemory() 虚拟机的空闲内存量:" + Runtime.getRuntime().freeMemory() + " bytes");
        System.out.println("Runtime.getRuntime().maxMemory() 虚拟机最大内存量:" + Runtime.getRuntime().maxMemory() + " bytes");
        System.out.println("Runtime.getRuntime().totalMemory() 虚拟机中的内存总量:" + Runtime.getRuntime().totalMemory() + " bytes");
        System.out.println("Runtime.getRuntime().totalMemory() 虚拟机中的内存总量:" + Runtime.getRuntime().totalMemory()/1024/1024 + " M");

        Map<String, String> map = System.getenv();
        String userName = map.get("USERNAME");// 获取用户名
        String computerName = map.get("COMPUTERNAME");// 获取计算机名
        String userDomain = map.get("USERDOMAIN");// 获取计算机域名
        System.out.println("userName:"+userName);
        System.out.println("computerName:"+computerName);
        System.out.println("userDomain:"+userDomain);
    }
}
