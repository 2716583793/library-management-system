package com.wang.utils;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

/**
 * 获取图片信息的实用类
 */
public class ImageData {
    public static final Image bi = new ImageIcon(Objects.requireNonNull(ImageData.class.getResource("/com/wang/resource/bi.png"))).getImage();
    public static final ImageIcon un = new ImageIcon(Objects.requireNonNull(ImageData.class.getResource("/com/wang/resource/un.png")));
    public static final ImageIcon pw = new ImageIcon(Objects.requireNonNull(ImageData.class.getResource("/com/wang/resource/pw.png")));
    public static final ImageIcon bg = new ImageIcon(Objects.requireNonNull(ImageData.class.getResource("/com/wang/resource/bg.png")));
    public static final ImageIcon info = new ImageIcon(Objects.requireNonNull(ImageData.class.getResource("/com/wang/resource/info.png")));
    public static final ImageIcon main = new ImageIcon(Objects.requireNonNull(ImageData.class.getResource("/com/wang/resource/main.png")));
}
