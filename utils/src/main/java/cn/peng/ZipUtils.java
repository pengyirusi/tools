package cn.peng;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;
import org.apache.commons.lang3.StringUtils;
import java.io.File;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * @author weiyupeng
 * @create 2021/11/23 16:56
 * zip4j 实现文件和文件夹压缩
 */
public class ZipUtils {

    private static final Logger LOGGER = Logger.getLogger(ZipUtils.class);

    /**
     * 压缩文件夹
     * @param folderPath 文件夹路径
     * @param zipFilePath 打包后保存的文件路径
     * @param password 解压密码
     * @return zipFilePath
     */
    public static String zipFolder(String folderPath, String zipFilePath, String password) throws ZipException {
        if (!new File(folderPath).exists()) {
            LOGGER.error(folderPath + "not exists");
            return null;
        }
        if (!new File(folderPath).isDirectory()) {
            LOGGER.error(folderPath + "is not directory");
            return null;
        }
        if (new File(zipFilePath).exists()) {
            LOGGER.error(zipFilePath + "exists");
            return null;
        }

        List<String> fileNames = new LinkedList<>();
        getFiles(fileNames, folderPath);
        for (String file : fileNames) {
            if (StringUtils.isBlank(zip(file, zipFilePath, password, true))) {
                LOGGER.error("add file:" + file + " to zipFile:" + zipFilePath + " failed");
            }
        }
        return zipFilePath;
    }

    /**
     * 递归获取文件夹下的文件
     * @param fileNames 用于存放文件名的list
     * @param folderPath 文件夹
     */
    private static void getFiles(List<String> fileNames, String folderPath) {
        File folder = new File(folderPath);
        File[] files = folder.listFiles();
        if (files == null || files.length == 0) {
            return;
        }
        for (File file : files) {
            String fileAbsolutePath = file.getAbsolutePath();
            if (file.isDirectory()) {
                getFiles(fileNames, fileAbsolutePath);
            } else {
                fileNames.add(fileAbsolutePath);
            }
        }
    }

    /**
     * 压缩文件
     * @param filePath 要打包的文件路径
     * @param zipFilePath 打包后保存的文件路径
     * @param password 解压密码
     * @return 调用zip函数，如果zipFilePath存在默认执行失败
     */
    public static String zip(String filePath, String zipFilePath, String password) throws ZipException {
        return zip(filePath, zipFilePath, password, false);
    }

    /**
     * 压缩文件
     * @param filePath 要打包的文件路径
     * @param zipFilePath 打包后保存的文件路径
     * @param password 解压密码
     * @param add 如果zipFilePath存在是否叠加
     * @return zipFilePath
     */
    private static String zip(String filePath, String zipFilePath, String password, boolean add) throws ZipException {
        if (new File(zipFilePath).exists() && !add) {
            LOGGER.error("zipFilePath exists, exit zip!");
            return null;
        }
        File file = new File(filePath);
        if (!file.exists()) {
            LOGGER.error("file not exists!");
            return null;
        }
        ZipParameters parameters = new ZipParameters();
        parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
        parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
        if (StringUtils.isNotEmpty(password)) {
            parameters.setEncryptFiles(true);
            parameters.setEncryptionMethod(Zip4jConstants.ENC_METHOD_STANDARD);
            parameters.setPassword(password.toCharArray());
        }
        ZipFile zipFile = new ZipFile(zipFilePath);
        zipFile.addFile(file, parameters);
        return zipFilePath;
    }

    public static void main(String[] args) throws ZipException {
        String folder = "Z:\\IDEA Project\\tools\\utils\\src\\main\\java\\cn\\peng\\files";
        String zipFile = "Z:\\IDEA Project\\tools\\utils\\src\\main\\java\\cn\\peng\\weiyupeng.zip";
        zipFolder(folder, zipFile, "123");
    }
}
