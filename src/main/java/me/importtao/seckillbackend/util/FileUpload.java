package me.importtao.seckillbackend.util;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Package me.importtao.usersystem.util
 * Class FileUpload
 * Description: TODO
 *
 * @author importtao
 * date 2018/2/4 16:08
 * @version V1.0
 */
public class FileUpload {
    private static final String host = "172.16.47.135";//127.0.0.1
    private static final int port = 8899;
    FileInputStream fileInputStream = null;
    DataOutputStream dataOutputStream = null;
    Socket socket = null;
    public void send(String filePath) throws IOException{
        try {

            File file = new File(filePath);
            if(file.exists()){
                socket = new Socket(host,port);
                fileInputStream = new FileInputStream(file);
                dataOutputStream = new DataOutputStream(socket.getOutputStream());
                dataOutputStream.writeUTF(file.getName());
                dataOutputStream.flush();
                dataOutputStream.writeLong(file.length());
                byte [] bytes = new byte[1024];
                int length = 0;
                long progress = 0;
                System.out.println("===============开始传输文件====================");
                while((length=fileInputStream.read(bytes,0,bytes.length))!=-1){
                    dataOutputStream.write(bytes,0,length);
                    progress += length;
                    System.out.print("|"+progress*100/file.length()+"%");
                }
                System.out.println("|");
                System.out.println("===============文件传输成功=====================");
            }else{
                System.out.println("================文件不存在=====================");
            }

        } catch (IOException e) {
            throw e;
        }finally {
            if(fileInputStream!=null){
                fileInputStream.close();
            }
            if(dataOutputStream != null){
                dataOutputStream.close();
            }
            if(socket != null){
               socket.close();
            }

        }
    }

    public static void main(String[] args) {
        FileUpload fileUpload = new FileUpload();
        try {
            fileUpload.send("C:\\Users\\importtao\\Desktop\\file-0.0.1-SNAPSHOT.jar.zip");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
