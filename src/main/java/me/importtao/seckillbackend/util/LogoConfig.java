package me.importtao.seckillbackend.util;

import org.springframework.stereotype.Component;

import java.awt.*;

/**
 * Package me.importtao.seckillbackend.util
 * Class LogoConfig
 * Description: TODO
 *
 * @author importtao
 * date 2018/5/17 16:54
 * @version V1.0
 */
@Component
public class LogoConfig
{
    // logo默认边框颜色
    public static final Color DEFAULT_BORDERCOLOR = Color.BLACK;
    // logo默认边框宽度
    public static final int DEFAULT_BORDER = 2;
    // logo大小默认为照片的1/5
    public static final int DEFAULT_LOGOPART = 5;

    private final int border = DEFAULT_BORDER;
    private final Color borderColor;
    private final int logoPart;

    /**
     * Creates a default config with on color  and off color
     * , generating normal black-on-white barcodes.
     */
    public LogoConfig()
    {
        this(DEFAULT_BORDERCOLOR, DEFAULT_LOGOPART);
    }

    public LogoConfig(Color borderColor, int logoPart)
    {
        this.borderColor = borderColor;
        this.logoPart = logoPart;
    }

    public Color getBorderColor()
    {
        return borderColor;
    }

    public int getBorder()
    {
        return border;
    }

    public int getLogoPart()
    {
        return logoPart;
    }
}