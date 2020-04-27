package com.mp.Hutool;

import cn.hutool.core.io.FileUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;

public class Test {

    public static void main(String[] args) {
//        QrConfig config = new QrConfig(300, 300);
//// 设置边距，既二维码和背景之间的边距
//        config.setMargin(3);
//// 设置前景色，既二维码颜色（青色）
//        config.setForeColor(Color.CYAN.getBlue());
//// 设置背景色（灰色）
//        config.setBackColor(Color.GRAY.getRGB());
//
//// 生成二维码到文件，也可以到流
//        QrCodeUtil.generate("http://hutool.cn/", config, FileUtil.file("c:/qrcode.jpg"));


        String decode = QrCodeUtil.decode(FileUtil.file("c:/qrcode.jpg"));
        System.out.println(decode);
    }
}
