package sy.b.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ServerTopBar extends JPanel {
    private final JTextArea portArea; // 端口文本框
    private final JButton startButton; // 启动服务按钮

    public ServerTopBar() {
        super();
        JLabel portLabel = new JLabel("端口号");
        portArea = new JTextArea("54531");
        startButton = new JButton("连接");
        setBorder(BorderFactory.createTitledBorder("服务端设置")); // 设置外边框
        setLayout(new BorderLayout(5, 5)); // 设置边距
        add(portLabel, BorderLayout.WEST);
        add(portArea, BorderLayout.CENTER);
        add(startButton, BorderLayout.EAST);
    }

    /**
     * 获取端口号
     * @return 端口号
     */
    public int getPort() {
        return Integer.parseInt(portArea.getText());
    }

    /**
     * 设置点击方法
     * @param onClick 点击事件回调
     */
    public void setClick(ActionListener onClick) {
        startButton.addActionListener(onClick);
    }

}