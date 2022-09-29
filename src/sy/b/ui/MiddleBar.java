package sy.b.ui;

import javax.swing.*;
import java.awt.*;

public class MiddleBar extends JPanel {
    public JTextArea getMessageArea() {
        return messageArea;
    }

    private final JTextArea messageArea;

    public MiddleBar() {
        super();
        messageArea = new JTextArea();
        messageArea.setEditable(false);
        setBorder(BorderFactory.createTitledBorder("消息"));
        setLayout(new BorderLayout());
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(messageArea);
        add(scrollPane, BorderLayout.CENTER);
    }

    public void clean() {
        messageArea.setText("");
    }
}