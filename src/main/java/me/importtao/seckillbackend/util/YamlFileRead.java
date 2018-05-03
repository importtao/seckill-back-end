package me.importtao.seckillbackend.util;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

/**
 * Package me.importtao.file.util
 * Class YamlFileRead
 * Description: TODO
 *
 * @author importtao
 * date 2018/2/5 20:54
 * @version V1.0
 */
@Component
public class YamlFileRead {
    public static final Logger logger = LoggerFactory.getLogger(YamlFileRead.class);
    public static Map read(String path){
        Map map = new HashMap();
        File file = new File(path);
        if(file.exists()){
            if(file.isFile()){
                FileInputStream fileInputStream;
                try {
                    fileInputStream = new FileInputStream(file);
                    Yaml yaml = new Yaml();
                    Object object = yaml.load(fileInputStream);
                    HashMap<String,String> result = yaml.loadAs(fileInputStream,HashMap.class);
                    logger.info(JSON.toJSONString(yaml.loadAs(fileInputStream,HashMap.class)));
                    logger.info("????????"+result.toString());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            }else{
                logger.error(path+"??????งน???");
            }
        }else{
            logger.error(path+"??งน??????????????");
        }
        return map;
    }

    public static void main(String[] args) {
        YamlFileRead.read("src/main/resources/regularExpress.yml");
    }
}
