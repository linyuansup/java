package sy.b.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class BottomBar extends JPanel {
    private final JTextArea sendArea; // 发送文本框
    private final JButton sendButton; // 发送按钮

    public BottomBar() {
        super();
        sendArea = new JTextArea();
        sendButton = new JButton("发送");
        // 设置外边框
        setBorder(BorderFactory.createTitledBorder("发送"));
        // 设置边距
        setLayout(new BorderLayout(5, 5));
        add(sendArea, BorderLayout.CENTER);
        add(sendButton, BorderLayout.EAST);
    }

    /**
     * 获取文本区域对应的字符串
     * @return 文本区域对应的字符串
     */
    public String getText() {
        return sendArea.getText();
    }

    /**
     * 设置点击方法
     * @param onClick 点击事件回调
     */
    public void setClick(ActionListener onClick) {
        sendButton.addActionListener(onClick);
    }

    /**
     * 清空文本区域
     */
    public void clean() {
        sendArea.setText("");
    }
}