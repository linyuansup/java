package sy.b.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ServerTopBar extends JPanel {
    private final JLabel portLabel;
    private final JTextArea portArea;
    private final JButton startButton;

    public ServerTopBar() {
        super();
        portLabel = new JLabel("端口号");
        portArea = new JTextArea("54531");
        startButton = new JButton("连接");
        setBorder(BorderFactory.createTitledBorder("服务端设置"));
        setLayout(new BorderLayout(5, 5));
        add(portLabel, BorderLayout.WEST);
        add(portArea, BorderLayout.CENTER);
        add(startButton, BorderLayout.EAST);
    }

    public int getPort() {
        return Integer.parseInt(portArea.getText());
    }

    public void setText(String s) {
        portArea.append(s + "\n");
    }

    public void setClick(ActionListener onClick) {
        startButton.addActionListener(onClick);
    }

}