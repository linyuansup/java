package zy.e.SocketFileTransfer;

import java.util.Scanner;

public class Server {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int port = scanner.nextInt(); // 端口号
            String fileSavePath = scanner.next(); // 文件保存路径
            SocketClient socket = new SocketClient(port, fileSavePath); // 构建 Socket
            socket.start();
            while (true)
            {
                String fileNeedSendPath = scanner.next();
                socket.sendFile(fileNeedSendPath); // 发送文件
            }
        }
    }
}