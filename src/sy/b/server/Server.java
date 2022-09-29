package sy.b.server;

import sy.b.SocketClient;
import sy.b.ui.BottomBar;
import sy.b.ui.MiddleBar;
import sy.b.ui.ServerTopBar;

import javax.swing.*;
import java.awt.*;

public class Server {


    public static void main(String[] args) {
        // 建立新界面
        JFrame mainUI = new JFrame();
        // 服务端顶部区域
        ServerTopBar serverTopBar = new ServerTopBar();
        // 中间区域
        MiddleBar middleBar = new MiddleBar();
        // 底部区域
        BottomBar bottomBar = new BottomBar();
        // Socket 客户端
        SocketClient socketClient = new SocketClient();
        // 设置顶栏按钮点击事件
        serverTopBar.setClick(e -> {
            // 将 Socket 客户端设置为服务端
            socketClient.setAsServer(serverTopBar.getPort(), middleBar.getMessageArea());
            // 清空文本框
            middleBar.clean();
            // 启动 Socket
            socketClient.startServer();
        });
        // 设置底栏按钮点击事件
        bottomBar.setClick(e -> {
            // 将要发送的文本发送给 Socket 客户端
            socketClient.sendText(bottomBar.getText());
            // 清空底栏文本框
            bottomBar.clean();
        });
        // 设置布局
        mainUI.setLayout(new BorderLayout());
        // 设置标题
        mainUI.setTitle("服务端");
        // 设置大小
        mainUI.setSize(500, 500);
        // 设置关闭方法
        mainUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 添加顶栏
        mainUI.add(serverTopBar, BorderLayout.NORTH);
        // 添加中间区域
        mainUI.add(middleBar, BorderLayout.CENTER);
        // 添加底部区域
        mainUI.add(bottomBar, BorderLayout.SOUTH);
        // 显示窗体
        mainUI.setVisible(true);
    }
}
