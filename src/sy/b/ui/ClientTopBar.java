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
    private final JPanel IPLayout;
    private final JPanel portLayout;

    public ClientTopBar(ActionListener onClick) {
        super();
        IPLabel = new JLabel("IP 地址：");
        IPArea = new JTextArea("127.0.0.1");
        portLabel = new JLabel("端口号：");
        portArea = new JTextArea("54531", 1, 5);
        connectButton = new JButton("连接");
        IPLayout = new JPanel(new BorderLayout(5,5));
        portLayout = new JPanel(new BorderLayout(5,5));
        connectButton.addActionListener(onClick);
        setBorder(BorderFactory.createTitledBorder("客户端设置"));
        setLayout(new BorderLayout(5,5));
        portLayout.add(portLabel, BorderLayout.WEST);
        portLayout.add(portLabel, BorderLayout.EAST);
        IPLayout.add(IPLabel, BorderLayout.WEST);
        IPLayout.add(IPArea, BorderLayout.EAST);
        add(IPLayout, BorderLayout.WEST);
        add(portLayout, BorderLayout.CENTER);
        add(connectButton, BorderLayout.EAST);
    }

    public int getPort() {
        return Integer.parseInt(portArea.getText());
    }

    public String getIP() {
        return IPArea.getText();
    }
}