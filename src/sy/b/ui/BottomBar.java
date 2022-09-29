package sy.b.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class BottomBar extends JPanel {
    private final JTextArea sendArea;
    private final JButton sendButton;

    public BottomBar() {
        super();
        sendArea = new JTextArea();
        sendButton = new JButton("发送");
        setBorder(BorderFactory.createTitledBorder("发送"));
        setLayout(new BorderLayout(5, 5));
        add(sendArea, BorderLayout.CENTER);
        add(sendButton, BorderLayout.EAST);
    }

    public String getText() {
        return sendArea.getText();
    }

    public void setClick(ActionListener onClick) {
        sendButton.addActionListener(onClick);
    }
}