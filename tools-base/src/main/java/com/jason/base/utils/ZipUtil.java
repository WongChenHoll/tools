package com.jason.base.utils;

import cn.hutool.core.collection.CollectionUtil;
import com.jason.base.exception.ServiceException;
import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.FileHeader;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.model.enums.AesKeyStrength;
import net.lingala.zip4j.model.enums.CompressionLevel;
import net.lingala.zip4j.model.enums.CompressionMethod;
import net.lingala.zip4j.model.enums.EncryptionMethod;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author WongChenHoll
 * @description 解压、压缩
 * @date 2023-2-21 星期二 13:31
 **/
public class ZipUtil {

    public static final long FILE_SIZE = 1024 * 1024 * 5; // 5 M
    public static final Charset UTF_8 = StandardCharsets.UTF_8;

    private ZipUtil() {
    }

    /**
     * 分割归档压缩整个文件夹（目录）。
     * 默认允许重命名，不要加密。
     *
     * @param source 压缩文件的存放地址（包括压缩文件名，并且是.zip结尾）。<br/>比如：D:\data\test-data\zip\test.zip
     * @param folder 需要压缩的目录
     * @return source
     */
    public static String zipFolderSplit(String source, File folder) throws IOException, ServiceException {
        return zipFolder(source, true, null, folder, true);
    }


    /**
     * 压缩整个文件夹（目录）。
     * 默认允许重命名，不要加密。
     *
     * @param source 压缩文件的存放地址（包括压缩文件名，并且是.zip结尾）。<br/>比如：D:\data\test-data\zip\test.zip
     * @param folder 目标目录
     * @return source
     */
    public static String zipFolder(String source, File folder) throws IOException, ServiceException {
        return zipFolder(source, true, null, folder, false);
    }

    /**
     * 压缩整个文件夹（目录）。
     * 默认支持重命名
     *
     * @param source   压缩文件的存放地址（包括压缩文件名，并且是.zip结尾）。<br/>比如：D:\data\test-data\zip\test.zip
     * @param password 压缩密码
     * @param folder   目标目录
     * @return 压缩文件的完整路径
     */
    public static String zipFolder(String source, String password, File folder) throws IOException, ServiceException {
        return zipFolder(source, true, password, folder, false);
    }

    /**
     * 压缩整个文件夹（目录）。
     *
     * @param source   压缩文件的存放地址（包括压缩文件名，并且是.zip结尾）。<br/>比如：D:\data\test-data\zip\test.zip
     * @param rename   是否需要重命名
     * @param password 压缩密码
     * @param folder   目标目录
     * @return 压缩文件的完整路径
     */
    public static String zipFolder(String source, boolean rename, String password, File folder, boolean splitFile) throws IOException, ServiceException {
        if (!folder.isDirectory()) {
            throw ServiceException.fileException("请选择目录");
        }
        File file = renameFileName(source, rename);
        boolean pwdFlag = StringUtils.isNotBlank(password);
        try (ZipFile zipFile = pwdFlag ? new ZipFile(file, password.toCharArray()) : new ZipFile(file)) {
            zipFile.setCharset(UTF_8);
            if (splitFile) {
                zipFile.createSplitZipFileFromFolder(folder, pwdFlag ? passwordParameters() : parameters(), true, FILE_SIZE);
            } else {
                if (pwdFlag) {
                    // 压缩文件需要加密
                    zipFile.addFolder(folder, passwordParameters());
                } else {
                    // 压缩文件不需要加密
                    zipFile.addFolder(folder);
                }
            }
        } catch (ZipException e) {
            throw ServiceException.serverException("压缩文件异常", e);
        }
        return file.getPath();
    }

    /**
     * 压缩指定的文件集合，并且分割压缩文件
     * 默认支持重命名
     *
     * @param source 压缩文件的存放地址（包括压缩文件名，并且是.zip结尾）。 <br/>比如：D:\data\test-data\zip\test.zip
     * @param files  需要压缩的文件集合
     */
    public static String zipFilesSplit(String source, List<File> files) throws IOException, ServiceException {
        return zipFiles(source, true, null, files, true);
    }

    /**
     * 压缩指定的文件集合.支持重命名
     *
     * @param source 压缩文件的存放地址（包括压缩文件名，并且是.zip结尾）。 <br/>比如：D:\data\test-data\zip\test.zip
     * @param files  需要压缩的文件集合
     */
    public static String zipFiles(String source, List<File> files) throws IOException, ServiceException {
        return zipFiles(source, true, null, files, false);
    }

    /**
     * 压缩指定的文件集合.支持重命名.
     * 默认支持重命名
     *
     * @param source   压缩文件的存放地址（包括压缩文件名，并且是.zip结尾）。 <br/>比如：D:\data\test-data\zip\test.zip
     * @param password 压缩密码
     * @param files    需要压缩的文件集合
     */
    public static String zipFiles(String source, String password, List<File> files) throws IOException, ServiceException {
        return zipFiles(source, true, password, files, false);
    }

    /**
     * 压缩指定的文件集合.
     *
     * @param source    压缩文件的存放地址（包括压缩文件名，并且是.zip结尾）。<br/>比如：D:\data\test-data\zip\test.zip
     * @param rename    是否需要重命名
     * @param password  压缩密码
     * @param files     需要压缩的文件集合
     * @param splitFile 是否要分割压缩文件
     */
    public static String zipFiles(String source, boolean rename, String password, List<File> files, boolean splitFile) throws ServiceException, IOException {
        if (CollectionUtil.isEmpty(files)) {
            throw ServiceException.fileException("请选择要压缩的文件");
        }
        File file = renameFileName(source, rename);
        boolean pwdFlag = StringUtils.isNotBlank(password);
        try (ZipFile zipFile = pwdFlag ? new ZipFile(file, password.toCharArray()) : new ZipFile(file)) {
            zipFile.setCharset(UTF_8);
            if (splitFile) {
                // 分割文件
                zipFile.createSplitZipFile(files, pwdFlag ? passwordParameters() : parameters(), true, FILE_SIZE);
            } else {
                if (pwdFlag) {
                    // 压缩文件需要加密
                    zipFile.addFiles(files, passwordParameters());
                } else {
                    // 压缩文件不需要加密
                    zipFile.addFiles(files);
                }
            }
        } catch (ZipException e) {
            throw ServiceException.serverException("压缩文件异常", e);
        }
        return file.getPath();
    }

    private static File renameFileName(String source, boolean rename) throws ServiceException, IOException {
        if (StringUtils.isBlank(source)) {
            throw ServiceException.fileNotExistException();
        }
        File file = new File(source);
        if (!file.getParentFile().exists()) {
            if (!file.getParentFile().mkdirs()) {
                throw ServiceException.fileNotExistException("文件路径创建失败");
            }
        }
        while (file.exists()) {
            if (!rename) {
                throw ServiceException.fileException(String.format("文件[%s]已存在", source));
            }
            source = file.getPath();
            String name = file.getName();
            String path = source.substring(0, source.length() - name.length());
            String pathname = path + name.substring(0, name.length() - 4) + "_copy.zip";
            file = new File(pathname);
        }
        return file;
    }

    /**
     * 设置加密的压缩参数.
     * <pre>
     *     如果{@link ZipParameters#setEncryptFiles(boolean)}参数为 true,
     *     那么{@link ZipParameters#setEncryptionMethod(EncryptionMethod)}参数只能是：{@link EncryptionMethod#ZIP_STANDARD}和{@link EncryptionMethod#AES}
     * </pre>
     *
     * @return 压缩参数对象
     */
    private static ZipParameters passwordParameters() {
        ZipParameters parameters = new ZipParameters();
        parameters.setEncryptFiles(true); // 是否设置添加密码
        parameters.setEncryptionMethod(EncryptionMethod.ZIP_STANDARD);// 加密方式
        return parameters;
    }

    /**
     * 压缩参数
     *
     * @return 压缩参数对象
     */
    private static ZipParameters parameters() {
        ZipParameters parameters = new ZipParameters();
        parameters.setCompressionLevel(CompressionLevel.MEDIUM_FAST);// 压缩级别
        parameters.setCompressionMethod(CompressionMethod.DEFLATE);// 压缩方式
        parameters.setEncryptFiles(false); // 是否设置添加密码
        parameters.setEncryptionMethod(EncryptionMethod.ZIP_STANDARD_VARIANT_STRONG);// 加密方式
        parameters.setAesKeyStrength(AesKeyStrength.KEY_STRENGTH_256);
        return parameters;
    }

    /**
     * 解压zip压缩包
     *
     * @param targetFolderPath 解压后存放文件的目录。比如：D:/data/test-data/zip/videos_copy
     * @param sourceZipFile    需要解压的zip压缩包，比如：new File("D:/data/test-data/zip/videos_20230221.zip")
     * @param password         解压需要的密码
     */
    public static void unZipFile(String targetFolderPath, File sourceZipFile, String password) throws ServiceException {
        // 创建目标文件夹
        File targetFolder = new File(targetFolderPath);
        if (!targetFolder.exists()) {
            if (!targetFolder.mkdirs()) {
                throw ServiceException.fileNotExistException("文件路径创建失败");
            }
        } else {
            targetFolderPath = sourceZipFile.getParentFile().getPath();
        }
        try (ZipFile zipFile = new ZipFile(sourceZipFile)) {
            boolean splitArchive = zipFile.isSplitArchive();
            // 判断是不是 zip文件
            if (!zipFile.isValidZipFile()) {
                throw ServiceException.fileException("带解压的文件不是zip文件。");
            }
            if (zipFile.isEncrypted()) {
                zipFile.setPassword(password.toCharArray());
            }
            zipFile.setCharset(UTF_8);
            zipFile.extractAll(targetFolderPath);
        } catch (Exception e) {
            e.printStackTrace();
            throw ServiceException.serverException("解压文件异常", e);
        }
    }

    /**
     * 解压文件
     */
    public static void unSplitZipFile(String targetFolderPath, File splitZipFiles) throws ServiceException, IOException, RuntimeException {
        unSplitZipFile(targetFolderPath, splitZipFiles, null);
    }

    /**
     * 解压分割归档的压缩包（不需要处理分割存档的文件，只需要分割后的zip压缩包）。
     *
     * @param targetFolderPath 解压后的存放文件夹，比如：D:/data/test-data/zip/merge/zipSingle
     * @param splitZipFiles    需要解压的zip、包，比如：new File("D:/data/test-data/zip/split/zipSingle/video_20230222.zip")
     * @param password         密码
     */
    public static void unSplitZipFile(String targetFolderPath, File splitZipFiles, String password) throws ServiceException, IOException, RuntimeException {
        if (splitZipFiles == null || splitZipFiles.isDirectory() || !splitZipFiles.exists() || !splitZipFiles.getName().endsWith(".zip")) {
            throw ServiceException.fileNotExistException();
        }
        // 创建目标文件夹
        File targetFolder = new File(targetFolderPath);
        if (!targetFolder.exists()) {
            if (!targetFolder.mkdirs()) {
                throw ServiceException.fileNotExistException("文件路径创建失败");
            }
        }
        String mergeZipName = targetFolderPath + "/merge_zip_" + splitZipFiles.getName().substring(0, splitZipFiles.getName().lastIndexOf(".")) + ".zip";
        ZipFile toMergeZipFile = null;
        ZipFile mergeZipFile = null;
        try {
            toMergeZipFile = new ZipFile(splitZipFiles);
            File mergeFile = new File(mergeZipName);
            if (toMergeZipFile.isEncrypted()) {
                if (StringUtils.isBlank(password)) {
                    throw ServiceException.paramsException("请输入解压密码");
                }
                toMergeZipFile.setPassword(password.toCharArray());
            }
            toMergeZipFile.setCharset(UTF_8);
            // 将分割的zip文件合并为单个zip文件，而不需要提取归档中的文件
            toMergeZipFile.mergeSplitFiles(mergeFile);
            mergeZipFile = new ZipFile(mergeFile);
            for (FileHeader fileHeader : toMergeZipFile.getFileHeaders()) {
                mergeZipFile.extractFile(fileHeader, targetFolderPath);
            }
        } catch (Exception e) {
            throw ServiceException.serverException("解压文件异常", e);
        } finally {
            Objects.requireNonNull(toMergeZipFile).close();
            Objects.requireNonNull(mergeZipFile).close();
        }
    }


    public static void main(String[] args) throws ServiceException, IOException {
        File file1 = new File("D:/data/test-data/zip/1.txt");
        File file2 = new File("D:/data/test-data/zip/2.txt");
        File file3 = new File("D:/data/test-data/zip/3.txt");
        ArrayList<File> fileList = new ArrayList<File>();
        fileList.add(file1);
        fileList.add(file2);
        fileList.add(file3);

        File toZipFile = new File("D:/data/test-data/zip/videos/02-HashMap集合的数据结构_存储过程讲解.mp4");
//        System.out.println(zipFolder("D:/data/test-data/zip/output_test.zip", new File("D:/data/test-data/zip/output")));
//        System.out.println(zipFolder("D:/data/test-data/zip/videos_20230221.zip", new File("D:/data/test-data/zip/videos")));
//        System.out.println(zipFolderSplit("D:/data/test-data/zip/merge/videos_20230222.zip", new File("D:/data/test-data/zip/videos")));
//        System.out.println(zipFilesSplit("D:/data/test-data/zip/split/zipSingle/video_20230222.zip",new ArrayList<>(Collections.singletonList(toZipFile))));

//        unZipFile("D:/data/test-data/zip/videos_copy", new File("D:/data/test-data/zip/videos_20230221.zip"));
//        unZipFile("D:/data/test-data/zip/output_copy", new File("D:/data/test-data/zip/output_test.zip"), null);
//        unZipFile("D:/data/test-data/zip/merge_copy", new File("D:/data/test-data/zip/aaaaaa"), null);
//        unSplitZipFile("D:/data/test-data/zip/merge/zipSingle", new File("D:/data/test-data/zip/split/zipSingle/video_20230222.zip"), null);
    }
}
