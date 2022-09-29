package sy.b.client;

import sy.b.SocketClient;
import sy.b.ui.BottomBar;
import sy.b.ui.ClientTopBar;
import sy.b.ui.MiddleBar;

import javax.swing.*;
import java.awt.*;

public class Client {

    public static void main(String[] args) {
        // 建立新界面
        JFrame mainUI = new JFrame();
        // 客户端顶部区域
        ClientTopBar clientTopBar = new ClientTopBar();
        // 中间区域
        MiddleBar middleBar = new MiddleBar();
        // 底部区域
        BottomBar bottomBar = new BottomBar();
        // 新建 Socket 客户端
        SocketClient socketClient = new SocketClient();
        // 设置顶栏按钮点击事件
        clientTopBar.setClick(e -> {
            // 将 Socket 客户端设置为客户端
            socketClient.setAsClient(clientTopBar.getIP(), clientTopBar.getPort(), middleBar.getMessageArea());
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
        mainUI.setTitle("客户端");
        // 设置大小
        mainUI.setSize(500, 500);
        // 设置关闭方法
        mainUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 添加顶栏
        mainUI.add(clientTopBar, BorderLayout.NORTH);
        // 添加中间区域
        mainUI.add(middleBar, BorderLayout.CENTER);
        // 添加底部区域
        mainUI.add(bottomBar, BorderLayout.SOUTH);
        // 显示窗体
        mainUI.setVisible(true);
    }
}
