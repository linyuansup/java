package sy.b.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Client {
    public static void main(String[] args) {
        MainUI mainUI = new MainUI();
        mainUI.setVisible(true);
    }
}

class MainUI extends JFrame {
    public MainUI() {
        super();
        setTitle("Server");
        setSize(500, 500);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(BorderLayout.NORTH, new TopBar());
        add(BorderLayout.CENTER, new MiddleBar());
        add(BorderLayout.SOUTH, new BottomBar());
    }
}

class TopBar extends JPanel {
    public TopBar() {
        super();
        setLayout(new GridLayout());
        setBorder(BorderFactory.createTitledBorder("客户端设置"));
        add(new JLabel("服务器IP："));
        JTextArea IPArea = new JTextArea();
        add(IPArea);
        add(new JLabel("端口号："));
        JTextArea portArea = new JTextArea();
        add(portArea);
        ActionListener connectClickFunc = e -> new Thread(() -> {

        }).start();
        JButton connectButton = new JButton("连接");
        connectButton.addActionListener(connectClickFunc);
        add(connectButton);
    }
}

class MiddleBar extends JPanel {
    public MiddleBar() {
        super();
        setBorder(BorderFactory.createTitledBorder("消息"));
        setLayout(new BorderLayout());
        JTextArea messageArea = new JTextArea();
        messageArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(messageArea);
        add(BorderLayout.CENTER, scrollPane);
    }
}

class BottomBar extends JPanel {
    public BottomBar() {
        super();
        setBorder(BorderFactory.createTitledBorder("发送"));
        setLayout(new BorderLayout());
        JTextArea sendArea = new JTextArea();
        add(BorderLayout.CENTER, sendArea);
        JButton sendButton = new JButton("发送");
        ActionListener sendClickFunc = e -> System.out.println("发送");
        sendButton.addActionListener(sendClickFunc);
        add(BorderLayout.EAST, sendButton);
    }
}