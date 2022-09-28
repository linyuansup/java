package sy.b.ui;

import javax.swing.*;
import java.awt.*;

public class MiddleBar extends JPanel {
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

    public void setText(String s) {
        messageArea.append(s + "\n");
    }

    public void clean() {
        messageArea.setText("");
    }
}