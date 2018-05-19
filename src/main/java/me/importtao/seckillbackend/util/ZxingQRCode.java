package me.importtao.seckillbackend.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Hashtable;

/**
 * Package me.importtao.seckillbackend.util
 * Class ZxingQRCode
 * Description: TODO
 *
 * @author importtao
 * date 2018/5/17 16:08
 * @version V1.0
 */
@Service
@Scope("singleton")
public class ZxingQRCode {
    private static final Logger logger = LoggerFactory.getLogger(ZxingQRCode.class);
    @Value("${file.imgPath}")
    private String imgPath;
    @Value("${file.logoPath}")
    private String logoPath;
    @Resource
    private LogoConfig logoConfig;
    private static final int BLACK = 0xFF000000;
    private static final int WHITE = 0xFFFFFFFF;

    /**
     *  最终调用该方法生成二维码图片
     * @param url 要生成二维码的url
     * @throws Exception Exception
     */
    public String get2CodeImage(String url) throws Exception{
        String format = "png";
        String fileName = GeneratorTimeRandomString.getTimeRandomString()+".png";
        String img = imgPath+fileName ;
        String logo = logoPath;
        Hashtable<EncodeHintType, String> hints = new Hashtable<>(16);
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        BitMatrix bitMatrix = new MultiFormatWriter().encode(url, BarcodeFormat.QR_CODE, 300, 300, hints);
        File qrcodeFile = new File(img);
        writeToFile(bitMatrix, format, qrcodeFile, logo);
        return fileName;
    }

    /**
     *
     * @param matrix 二维码矩阵相关
     * @param format 二维码图片格式
     * @param file 二维码图片文件
     * @param logo logo路径
     * @throws IOException
     */
    public void writeToFile(BitMatrix matrix,String format,File file,String logo) throws IOException {
        BufferedImage image = toBufferedImage(matrix);
        Graphics2D gs = image.createGraphics();

        //载入logo
        BufferedImage img = ImageIO.read(new File(logo));
        img = setRadius(img,10,3,5);
        int widthLogo = img.getWidth(), heightLogo = img.getHeight();

        // 计算图片放置位置
        int x = (image.getWidth() - widthLogo) / 2;
        int y = (image.getHeight() - heightLogo) / 2;
        gs.drawImage(img, x, y, null);
        gs.dispose();
        img.flush();
        if(!ImageIO.write(image, format, file)){
            throw new IOException("Could not write an image of format " + format + " to " + file);
        }
    }

    public  BufferedImage toBufferedImage(BitMatrix matrix){
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for(int x=0;x<width;x++){
            for(int y=0;y<height;y++){
                image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
            }
        }
        return image;
    }
    public String createZxing(String content) throws WriterException, IOException {
        int width=300;
        int hight=300;
        String format="png";
        HashMap hints=new HashMap(16);
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        //纠错等级L,M,Q,H
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        //边距
        hints.put(EncodeHintType.MARGIN, 2);
        BitMatrix bitMatrix=new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, hight, hints);
        String fileName = GeneratorTimeRandomString.getTimeRandomString()+".png";
        Path file=new File(imgPath+fileName).toPath();
        MatrixToImageWriter.writeToPath(bitMatrix, format, file);
        return fileName;
    }

    /**
     * 图片设置圆角
     * @param srcImage
     * @param radius
     * @param border
     * @param padding
     * @return
     * @throws IOException
     */
    public  BufferedImage setRadius(BufferedImage srcImage, int radius, int border, int padding) throws IOException{
        int width = srcImage.getWidth();
        int height = srcImage.getHeight();
        int canvasWidth = width + padding * 2;
        int canvasHeight = height + padding * 2;

        BufferedImage image = new BufferedImage(canvasWidth, canvasHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D gs = image.createGraphics();
        gs.setComposite(AlphaComposite.Src);
        gs.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        gs.setColor(Color.WHITE);
        gs.fill(new RoundRectangle2D.Float(0, 0, canvasWidth, canvasHeight, radius, radius));
        gs.setComposite(AlphaComposite.SrcAtop);
        gs.drawImage(setClip(srcImage, radius), padding, padding, null);
        if(border !=0){
            gs.setColor(Color.GRAY);
            gs.setStroke(new BasicStroke(border));
            gs.drawRoundRect(padding, padding, canvasWidth - 2 * padding, canvasHeight - 2 * padding, radius, radius);
        }
        gs.dispose();
        return image;
    }

    /**
     * 图片切圆角
     * @param srcImage
     * @param radius
     * @return
     */
    public static BufferedImage setClip(BufferedImage srcImage, int radius){
        int width = srcImage.getWidth();
        int height = srcImage.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D gs = image.createGraphics();

        gs.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        gs.setClip(new RoundRectangle2D.Double(0, 0, width, height, radius, radius));
        gs.drawImage(srcImage, 0, 0, null);
        gs.dispose();
        return image;
    }
}
