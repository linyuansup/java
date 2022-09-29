package sy.b.client;

import sy.b.SocketClient;
import sy.b.ui.BottomBar;
import sy.b.ui.ClientTopBar;
import sy.b.ui.MiddleBar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Client {

    SocketClient socketClient = null;

    public static void main(String[] args) {
        JFrame mainUI = new JFrame();
        ClientTopBar clientTopBar = new ClientTopBar();
        MiddleBar middleBar = new MiddleBar();
        BottomBar bottomBar = new BottomBar();
        SocketClient socketClient = new SocketClient();
        bottomBar.setClick(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                socketClient.setAsClient(clientTopBar.getIP(), clientTopBar.getPort(), middleBar.getMessageArea());
                socketClient.start();
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
