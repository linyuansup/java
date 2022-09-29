package sy.b.server;

import sy.b.SocketClient;
import sy.b.ui.BottomBar;
import sy.b.ui.MiddleBar;
import sy.b.ui.ServerTopBar;

import javax.swing.*;
import java.awt.*;

public class Server {


    public static void main(String[] args) {
        JFrame mainUI = new JFrame();
        ServerTopBar serverTopBar = new ServerTopBar();
        MiddleBar middleBar = new MiddleBar();
        BottomBar bottomBar = new BottomBar();
        SocketClient socketClient = new SocketClient();
        serverTopBar.setClick(e -> {
            socketClient.setAsServer(serverTopBar.getPort(), middleBar.getMessageArea());
            socketClient.start();
        });
        bottomBar.setClick(e -> {
            socketClient.sendText(bottomBar.getText());
            bottomBar.clean();
        });
        mainUI.setLayout(new BorderLayout());
        mainUI.setTitle("服务端");
        mainUI.setSize(500, 500);
        mainUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainUI.add(serverTopBar, BorderLayout.NORTH);
        mainUI.add(middleBar, BorderLayout.CENTER);
        mainUI.add(bottomBar, BorderLayout.SOUTH);
        mainUI.setVisible(true);
    }
}
