package me.importtao.seckillbackend.util;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Package me.importtao.usersystem.util
 * Class FileRessive
 * Description: TODO
 *
 * @author importtao
 * date 2018/2/4 19:03
 * @version V1.0
 */
public class FileRessive {
    //private String directory = "/home/fileressive/";
    private String directory = "C:\\1001010\\";
    private int port;
    ServerSocket serverSocket = null;
    Socket socket = null;
    DataInputStream dataInputStream = null;
    FileOutputStream  fileOutputStream = null;
    public synchronized void ressieve(int port){
        System.out.println("开始监听》》》》》");
        String fileName;
        long size;
        this.port = port;
        try {
            serverSocket = new ServerSocket(port);
            socket = serverSocket.accept();
            dataInputStream = new DataInputStream(socket.getInputStream());
            fileName = dataInputStream.readUTF();
            size = dataInputStream.readLong();
            File file = new File(directory+fileName);
            file.createNewFile();
            fileOutputStream = new FileOutputStream(file);
            byte [] bytes = new byte[1024];
            long length = 0;
            long progress = 0;
            while((length = dataInputStream.read(bytes,0,bytes.length))!=-1){
                progress += length;
                System.out.print("|"+progress*100/size+"%");
                fileOutputStream.write(bytes);
            }
            System.out.println("|");
            System.out.println("++++++++++接受完成++++++++文件："+fileName+"+++[size:"+size+"]");
            if(fileOutputStream != null){
                fileOutputStream.close();
            }
            if(dataInputStream!=null){
                dataInputStream.close();
            }
            if(serverSocket!=null){
                serverSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {

        }
    }

    public static void main(String[] args) {
        while (true){
            FileRessive fileRessive = new FileRessive();
            fileRessive.ressieve(8899);
        }
    }
}
