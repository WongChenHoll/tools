package test.function.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;

/**
 * @author ChenHol.Wong
 * @create 2020/8/14 20:04
 */
public class test {
    public static void main(String[] args) throws Exception {
        File read = new File("E:\\迅雷下载\\download_from_xunlei_20200119_videos\\目录.csv");
        File write = new File("E:\\迅雷下载\\download_from_xunlei_20200119_videos\\目录2.csv");
        FileOutputStream outputStream = new FileOutputStream(write);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(read));
        String line = bufferedReader.readLine();

        String splitstr = ".mp4";
        boolean contains = line.contains(splitstr);

        String[] split = line.split(splitstr);

        for (String s : split) {
            int length = s.length();
            String uuid = s.substring(0, 32);
            String name = s.substring(32);

            System.out.println(uuid);
            System.out.println(name);
            String fileName = uuid + "####" + name + ".mp4\r\n";
            outputStream.write(fileName.getBytes());

        }

    }
}
