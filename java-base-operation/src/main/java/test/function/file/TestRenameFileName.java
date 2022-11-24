package test.function.file;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author ChenHol.Wong
 * @create 2020/8/14 19:26
 */
public class TestRenameFileName {
    public static void main(String[] args) throws Exception {
        ArrayList<String> list = new ArrayList<String>();

        File file = new File("E:\\迅雷下载\\download_from_xunlei_20200119_videos");
        rename(file, list);
        File write = new File(file.getPath() + "/目录.csv");

        FileOutputStream outputStream = new FileOutputStream(write);

        System.out.println(list.size());
        for (String s : list) {
            s = s + "\r\n";
            outputStream.write(s.getBytes());
        }

    }

    public static void rename(File file, List list) throws Exception {
        File[] files = file.listFiles();

        for (File f : files) {
            if (f.isDirectory()) {
                rename(f, list);
            } else {
                String parent = f.getParent();
                String name = f.getName();
                String uuid = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
                list.add(uuid + "!^" + name);
                f.renameTo(new File(parent + "/" + uuid + ".mp4"));
            }
        }

    }
}
