package sy.b.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ClientTopBar extends JPanel {
    private final JLabel IPLabel;
    private final JTextArea IPArea;
    private final JLabel portLabel;
    private final JTextArea portArea;
    private final JButton connectButton;
    private final JPanel RightLayout;

    public ClientTopBar() {
        super();
        IPLabel = new JLabel("IP 地址：");
        IPArea = new JTextArea("127.0.0.1");
        portLabel = new JLabel("端口号：");
        portArea = new JTextArea("54531", 1, 5);
        connectButton = new JButton("连接");
        RightLayout = new JPanel(new BorderLayout());
        RightLayout.add(portLabel, BorderLayout.WEST);
        RightLayout.add(portArea, BorderLayout.CENTER);
        RightLayout.add(connectButton, BorderLayout.EAST);
        setBorder(BorderFactory.createTitledBorder("客户端设置"));
        setLayout(new BorderLayout(5, 5));
        add(IPLabel, BorderLayout.WEST);
        add(IPArea, BorderLayout.CENTER);
        add(RightLayout, BorderLayout.EAST);
    }

    public int getPort() {
        return Integer.parseInt(portArea.getText());
    }

    public String getIP() {
        return IPArea.getText();
    }

    public void setClick(ActionListener onClick) {
        connectButton.addActionListener(onClick);
    }
}