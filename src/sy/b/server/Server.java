package sy.b.server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {
        JFrame jFrame = new MainUI();
        jFrame.setVisible(true);
    }

    static class MainUI extends JFrame {
        public static final MiddleBar middleBar = new MiddleBar();
        public static final TopBar topBar = new TopBar();
        public static final BottomBar bottomBar = new BottomBar();

        public static void addText(String s) {
            middleBar.messageArea.append(s + "\n");
        }

        public MainUI() {
            super();
            setTitle("Server");
            setSize(500, 500);
            setLayout(new BorderLayout());
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            add(BorderLayout.CENTER, middleBar);
            add(BorderLayout.NORTH, topBar);
            add(BorderLayout.SOUTH, bottomBar);
        }
    }

    static class TopBar extends JPanel {
        public TopBar() {
            super();
            setBorder(BorderFactory.createTitledBorder("服务器设置"));
            setLayout(new BorderLayout());
            add(BorderLayout.WEST, new JLabel("端口号："));
            JTextArea PortArea = new JTextArea();
            add(BorderLayout.CENTER, PortArea);
            ActionListener connectClickFunc = e -> {
                new Thread(() -> {
                    try (ServerSocket serverSocket = new ServerSocket(Integer.parseInt(PortArea.getText()))) {
                        MainUI.middleBar.messageArea.setText("");
                        MainUI.addText("等待连接......");
                        Socket accept = serverSocket.accept();
                        MainUI.addText("连接成功");
                        try {
                            while (true) {
                                InputStream is = accept.getInputStream();
                                byte[] b = new byte[1024];
                                String data = new String(b, "GBK");
                                MainUI.addText(data);
                            }
                        } catch (StringIndexOutOfBoundsException ex) {
                            MainUI.addText("连接断开");
                        } catch (Exception ex) {
                            MainUI.addText("发生错误:" + ex);
                        }
                    } catch (Exception ex) {
                        MainUI.addText("连接失败:" + ex);
                    }
                }).start();
            };
            JButton connectButton = new JButton("连接");
            connectButton.addActionListener(connectClickFunc);
            add(BorderLayout.EAST, connectButton);
        }
    }

    static class MiddleBar extends JPanel {

        public final JTextArea messageArea;

        public MiddleBar() {
            super();
            setBorder(BorderFactory.createTitledBorder("消息"));
            setLayout(new BorderLayout());
            messageArea = new JTextArea();
            messageArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane();
            scrollPane.setViewportView(messageArea);
            add(BorderLayout.CENTER, scrollPane);
        }
    }

    static class BottomBar extends JPanel {
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
}