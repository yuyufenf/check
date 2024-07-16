package com.muxingzhe.check.utils;

import com.muxingzhe.check.utils.exception.FileException;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedWriter;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 文件储存工具类
 * @author kampf
 * @date 2019/8/24 11:20
 */
@Slf4j
public class FilePathUtils {

    //TODO 所有的路径要做成 自定义配置文件存储地址 + 项目名称 + 日期文件夹 + 文件

    /** 自定义配置文件存储地址 **/
    private static String staticDir;

    /** 项目名称 **/
    private static String fileDir = "unicorn";

    /**
     * 储存文件工具类(字节文件)
     * @param fileName 文件名
     * @param fileType 文件类型,如果类型不是自定义请写空
     * @param file 二进制文件
     * @return 文件储存
     */
    public static Map<String, String> saveFile(String fileName, String fileType, byte file){
        try {

            Map<String, String> resultPath = new HashMap<>();

            if(!StrUtils.isBlank(fileType)){
                fileName = fileName + "." + fileType;
            }

            fileName = makeFileName(fileName);

            Map<String, String> paths = makePath();

            //相对路径
            String relPath = paths.get("relPath") + fileName;
            //绝对路径
            String absPath = paths.get("absPath") + fileName;

            BufferedWriter writer = Files.newBufferedWriter(Paths.get(absPath));
            writer.write(file);
            writer.flush();
            writer.close();

            resultPath.put("relPath", relPath);
            resultPath.put("absPath", absPath);

            return resultPath;
        } catch (Exception e){
            log.error("文件存储失败:[{}]", e.getMessage(), e);
            throw new FileException("创建文件时失败,请检查存储路径是否存在权限问题");
        }
    }

    /**
     * 储存文件工具类(流文件)
     * @param fileName 文件名
     * @param fileType 文件类型
     * @param is 流文件
     * @return
     */
    public static Map<String, String> saveFile(String fileName, String fileType, InputStream is){
        try {

            Map<String, String> resultPath = new HashMap<>();

            if(StrUtils.isBlank(fileType)){
                fileName = fileName + "." + fileType;
            }

            fileName = makeFileName(fileName);

            Map<String, String> paths = makePath();

            //相对路径
            String relPath = paths.get("relPath") + fileName;
            //绝对路径
            String absPath = paths.get("absPath") + fileName;

            Files.createFile(Paths.get(absPath));

            resultPath.put("relPath", relPath);
            resultPath.put("absPath", absPath);

            return resultPath;
        } catch (Exception e){
            log.error("文件存储失败:[{}]", e.getMessage(), e);
            throw new FileException("创建文件时失败,请检查存储路径是否存在权限问题");
        }
    }

    /**
     * 将文件名添加4位纯数字uuid以及日期以防止文件名重复
     * @param fileName 原始文件名
     * @return
     */
    private static String makeFileName(String fileName){

        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("HHmm");

        int hashCode = UUID.randomUUID().hashCode();
        if(hashCode < 0){
            hashCode = -hashCode;
        }

        String uuid = String.format("%04d", hashCode);
        fileName = dateFormat.format(date) + uuid + fileName;

        return fileName;
    }

    /**
     * 通过规则创建文件储存路径
     * @return 返回绝对路径(absPath)和相对路径(relPath)的map对象
     */
    private static Map<String, String> makePath(){

        Map<String, String> map = new HashMap<>();

        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyyMM");

        String relPath = "/" + fileDir + "/" + dateFormat.format(date) + "/";
        String absPath = staticDir + relPath;

        //TODO 根据年月日创建目录并分割,不过一般用不到这么细分,常规储存一般细分到月即可.之前有做过监控项目,图片的归类细分到秒,所以文件夹都是细分到天故保留此分割方法
//        Calendar date = Calendar.getInstance();
//        根据年月日创建目录并分割
//        String path = date.get(Calendar.YEAR) + File.separator
//                + (date.get(Calendar.MONTH) + 1) + File.separator
//                + date.get(Calendar.DAY_OF_MONTH);
        Path up = Paths.get(absPath);

        try {
            if(!Files.exists(up)){
                Files.createDirectories(up);
            }
        } catch (Exception e){
            log.error("路径创建失败:[{}]", e.getMessage(), e);
            throw new FileException("创建文件夹路径时失败,请检查存储路径是否存在权限问题");
        }

        map.put("relPath",relPath);
        map.put("absPath",absPath);

        return map;
    }
}
