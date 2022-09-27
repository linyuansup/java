package zy.c;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ClickAndDraw {
    public static void main(String[] args) {
        // 新建一个窗体
        JFrame frame = new JFrame("ClickAndDraw");
        // 添加一个画布
        frame.setSize(500, 500);
        // 设置默认关闭方法
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 添加点击事件
        frame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                // 获取画布
                Graphics graphics = frame.getGraphics();
                // 转型以便调粗细
                Graphics2D graphics2D = (Graphics2D) graphics;
                // 调整画笔粗细
                graphics2D.setStroke(new BasicStroke(3.0f));
                switch (e.getButton()) {
                    // 左键
                    case 1:
                        if (e.getClickCount() == 2) {
                            // 双击清除画布
                            frame.repaint();
                        } else {
                            // 单击画圆
                            graphics2D.setColor(Color.GREEN);
                            graphics2D.drawOval(e.getX(), e.getY(), 30, 30);
                        }
                        break;
                    // 右键画方
                    case 3:
                        graphics2D.setColor(Color.RED);
                        graphics2D.drawRect(e.getX(), e.getY(), 30, 30);
                        break;
                }
            }
        });
        frame.setVisible(true);
    }
}
