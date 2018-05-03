package me.importtao.seckillbackend.util;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;

/**
 * Package me.importtao.seckillbackend.util
 * Class ImgUpload
 * Description: TODO
 *
 * @author importtao
 * date 2018/5/3 14:18
 * @version V1.0
 */
public class ImgUpload {
    public static void uploadFile(byte[] file, String filePath,String fileName) throws Exception {
        File targetFile = new File(filePath);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        System.out.println(filePath);
        FileOutputStream out = new FileOutputStream(targetFile);
        out.write(file);
        out.flush();
        out.close();
    }
}
