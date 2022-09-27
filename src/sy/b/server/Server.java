package sy.b.server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Server {
    public static void main(String[] args) {
        JFrame jFrame = new MainUI();
        jFrame.setVisible(true);
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
        setBorder(BorderFactory.createTitledBorder("服务器设置"));
        setLayout(new BorderLayout());
        add(BorderLayout.WEST, new JLabel("端口号："));
        add(BorderLayout.CENTER, new JTextArea());
        ActionListener connectClickFunc = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        };
        JButton connectButton = new JButton("连接");
        connectButton.addActionListener(connectClickFunc);
        add(BorderLayout.EAST, connectButton);
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
        ActionListener sendClickFunc = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("发送");
            }
        };
        sendButton.addActionListener(sendClickFunc);
        add(BorderLayout.EAST, sendButton);
    }
}