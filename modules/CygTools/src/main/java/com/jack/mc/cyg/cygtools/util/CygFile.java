package com.jack.mc.cyg.cygtools.util;

import android.graphics.Bitmap;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 文件的封装
 */
public class CygFile {

    public CygFile() {

    }

    /**
     * 在SD卡上创建文件
     *
     * @param fileName 文件名称
     * @return
     */
    public static File createFileToSD(String fileName) throws IOException {
        File file = new File(CygEnvironment.getExternalStorageDir() + fileName);
        file.createNewFile();
        return file;
    }

    /**
     * 在SD卡上创建目录
     *
     * @param dirName 目录名称
     */
    public static File createSDDir(String dirName) {
        File dir = new File(CygEnvironment.getExternalStorageDir() + CygEnvironment.separator + dirName);
        dir.mkdir();
        return dir;
    }

    /**
     * 判断SD卡上的文件是否存在
     *
     * @param fileName 文件名称
     * @return
     */
    public static boolean isFileExist(String fileName) {
        File file = new File(CygEnvironment.getExternalStorageDir() + fileName);
        file.delete();
        return file.exists();
    }

    /**
     * 将一个InputStream里面的数据写入到SD卡中
     *
     * @param path     路径
     * @param fileName 文件名称
     * @param input    输入流
     * @return
     */
    public static File writeToSDFromInput(String path, String fileName, InputStream input) {
        File file = null;
        OutputStream output = null;
        try {
            createSDDir(path);
            file = createFileToSD(path + fileName);
            output = new FileOutputStream(file);
            byte buffer[] = new byte[1024];
            int len = 0;
            //如果下载成功就开往SD卡里些数据
            while ((len = input.read(buffer)) != -1) {
                output.write(buffer, 0, len);
            }
            output.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                input.close();
                output.close();
            } catch (Exception e) {

                e.printStackTrace();
            }
        }
        return file;
    }

    /**
     * 将bitmap保存在文件中
     *
     * @param bitmap   bitmap对象
     * @param filePath 文件路径
     * @return
     */
    public static File saveBitmapFile(Bitmap bitmap, String filePath) {
        File file = new File(filePath);//将要保存图片的路径
        try {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 80, bos);
            bos.flush();
            bos.close();
            return file;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取文件的大小
     *
     * @param path 文件路径
     * @return
     */
    public static long getFileSize(String path) {
        if (CygString.isEmpty(path)) {
            return -1;
        }

        File file = new File(path);
        return (file.exists() && file.isFile() ? file.length() : -1);
    }

}