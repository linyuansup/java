package zy.c;

import javax.swing.*;
import java.awt.*;

public class DrawPic {
    public static void main(String[] args) {
        // 新建一个窗体
        Frame frame = new Frame("DrawPic");
        // 添加一个画布
        frame.add(new JPanel() {
            @Override
            public void paint(Graphics g) {
                super.paint(g);
                // 转型以便调粗细
                Graphics2D graphics2D = (Graphics2D) g;
                // 调整画笔粗细
                graphics2D.setStroke(new BasicStroke(3.0f));
                // 循环画外面的方框
                for (int i = 100; i <= 300; i += 100) {
                    for (int j = 100; j <= 300; j += 100) {
                        graphics2D.drawRect(i, j, 100, 100);
                    }
                }
                // 上面的圆
                graphics2D.drawOval(130, 130, 40, 40);
                // 中间的实心圆
                graphics2D.fillOval(230, 230, 40, 40);
                // 右下的斜杠
                graphics2D.drawLine(320, 320, 380, 380);
            }
        });
        frame.setVisible(true);
    }
}

class Frame extends JFrame {
    public Frame(String s) {
        super(s);
        // 设置窗体大小
        this.setSize(500, 500);
        // 设置窗体位置
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}