package sy.b.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ClientTopBar extends JPanel {
    private final JTextArea IPArea; // IP 文本框
    private final JTextArea portArea; // 端口号文本框
    private final JButton connectButton; // 连接按钮

    public ClientTopBar() {
        super();
        // 初始化空间
        JLabel IPLabel = new JLabel("IP 地址：");
        IPArea = new JTextArea("127.0.0.1");
        JLabel portLabel = new JLabel("端口号：");
        portArea = new JTextArea("54531", 1, 5);
        connectButton = new JButton("连接");
        JPanel rightLayout = new JPanel(new BorderLayout());
        // 添加控件
        rightLayout.add(portLabel, BorderLayout.WEST);
        rightLayout.add(portArea, BorderLayout.CENTER);
        rightLayout.add(connectButton, BorderLayout.EAST);
        // 添加外边框
        setBorder(BorderFactory.createTitledBorder("客户端设置"));
        setLayout(new BorderLayout(5, 5));
        // 添加控件
        add(IPLabel, BorderLayout.WEST);
        add(IPArea, BorderLayout.CENTER);
        add(rightLayout, BorderLayout.EAST);
    }

    /**
     * 获取端口号
     * @return 端口号
     */
    public int getPort() {
        return Integer.parseInt(portArea.getText());
    }

    /**
     * 获取 IP 地址
     * @return IP 地址
     */
    public String getIP() {
        return IPArea.getText();
    }

    /**
     * 设置点击方法
     * @param onClick 点击事件回调
     */
    public void setClick(ActionListener onClick) {
        connectButton.addActionListener(onClick);
    }
}