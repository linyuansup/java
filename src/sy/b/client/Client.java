package sy.b.client;

import sy.b.ui.BottomBar;
import sy.b.ui.ClientTopBar;
import sy.b.ui.MiddleBar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        JFrame mainUI = new JFrame();
        ClientTopBar clientTopBar = new ClientTopBar();
        MiddleBar middleBar = new MiddleBar();
        BottomBar bottomBar = new BottomBar();
        final BufferedWriter[] br = {null};
        clientTopBar.setClick(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try (Socket socket = new Socket(clientTopBar.getIP(), clientTopBar.getPort())) {
                            middleBar.setText("连接成功");
                            br[0] = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                        } catch (Exception ex) {
                            middleBar.setText("发生错误：" + ex);
                        }
                    }
                }).start();
            }
        });
        bottomBar.setClick(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    br[0].write(bottomBar.getText() + "\n");
                    br[0].flush();
                } catch (Exception ex) {
                    middleBar.setText("发生错误：" + ex);
                }
            }
        });
        mainUI.setLayout(new BorderLayout());
        mainUI.setTitle("客户端");
        mainUI.setSize(500, 500);
        mainUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainUI.add(clientTopBar, BorderLayout.NORTH);
        mainUI.add(middleBar, BorderLayout.CENTER);
        mainUI.add(bottomBar, BorderLayout.SOUTH);
        mainUI.setVisible(true);
    }
}
