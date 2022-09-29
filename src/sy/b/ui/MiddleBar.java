package sy.b.ui;

import javax.swing.*;
import java.awt.*;

public class MiddleBar extends JPanel {

    /**
     * 获取消息区域文本
     * @return 消息区域文本
     */
    public JTextArea getMessageArea() {
        return messageArea;
    }

    private final JTextArea messageArea;

    public MiddleBar() {
        super();
        messageArea = new JTextArea();
        messageArea.setEditable(false); // 不允许中间文本框编辑
        setBorder(BorderFactory.createTitledBorder("消息")); // 设置外边框
        setLayout(new BorderLayout());
        JScrollPane scrollPane = new JScrollPane(); // 允许滚动
        scrollPane.setViewportView(messageArea);
        add(scrollPane, BorderLayout.CENTER);
    }

    /**
     * 清空文本框
     */
    public void clean() {
        messageArea.setText("");
    }
}