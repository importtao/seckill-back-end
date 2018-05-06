package me.importtao.seckillbackend.controller;

import me.importtao.seckillbackend.util.GeneratorTimeRandomString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Paths;
import java.util.HashMap;


/**
 * Package me.importtao.seckillbackend.controller
 * Class FileController
 * Description: TODO
 *
 * @author importtao
 * date 2018/5/3 14:26
 * @version V1.0
 */
@RestController
@CrossOrigin
public class FileController {
    @Value("${file.imgPath}")
    private String imgPath;

    @Value("${file.imgPath}")
    private String filePath;
    private final ResourceLoader resourceLoader;

    @Autowired
    public FileController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
    private static final Logger logger = LoggerFactory.getLogger(FileController.class);
    @PostMapping("/file")
    public HashMap uploadFile(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        HashMap map = new HashMap(6);
        if (file.isEmpty()) {
            map.put("status",1);
            map.put("msg","文件为空");
            return map;
        }
        // 获取文件名
        String fileName = file.getOriginalFilename();
        logger.info("上传的文件名为：" + fileName);
        // 获取文件的后缀名
        //文件名转换为系统文件名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        logger.info("上传的后缀名为：" + suffixName);
        fileName = GeneratorTimeRandomString.getTimeRandomString()+suffixName;
        logger.info("修改文件名为："+fileName);
        // 文件上传后的路径
        // 解决中文问题，liunx下中文路径，图片显示问题
        // fileName = UUID.randomUUID() + suffixName;
        File dest = new File(filePath + fileName);
        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
            map.put("status",0);
            map.put("msg","上传成功");
            map.put("file",fileName);
            return map;
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        map.put("status",1);
        map.put("msg","上传失败");
        return map;
    }

    @GetMapping("/file/{filename:.+}")
    public String downLoad(HttpServletRequest request,HttpServletResponse response,@PathVariable("filename")String fileName){
        logger.info("文件名："+fileName);
        response.setHeader("content-type", "application/octet-stream");
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            os = response.getOutputStream();
            bis = new BufferedInputStream(new FileInputStream(new File(filePath
                    + fileName)));
            int i = bis.read(buff);
            while (i != -1) {
                os.write(buff, 0, buff.length);
                os.flush();
                i = bis.read(buff);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "文件不存在";
    }

    /*显示图片的方法关键 匹配路径像 localhost:8080/b7c76eb3-5a67-4d41-ae5c-1642af3f8746.png*/
    @RequestMapping(method = RequestMethod.GET, value = "/img/{filename:.+}")
    @ResponseBody
    public ResponseEntity<?> getFile(@PathVariable String filename) {

        try {
            return ResponseEntity.ok(resourceLoader.getResource("file:" + Paths.get(imgPath, filename).toString()));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/img")
    public HashMap uploadImg(@RequestParam("file") MultipartFile file,HttpServletRequest request) {
        HashMap map = new HashMap(6);
        if (file.isEmpty()) {
            map.put("status",1);
            map.put("msg","文件为空");
            return map;
        }
        // 获取文件名
        String fileName = file.getOriginalFilename();
        logger.info("上传的图片名为：" + fileName);
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        logger.info("上传的后缀名为：" + suffixName);
        fileName = GeneratorTimeRandomString.getTimeRandomString()+suffixName;
        logger.info("修改文件名为："+fileName);
        File dest = new File(imgPath + fileName);
        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
            map.put("status",0);
            map.put("msg","图片上传成功");
            map.put("file",fileName);
            return map;
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        map.put("status",1);
        map.put("msg","上传失败");
        return map;
    }
}