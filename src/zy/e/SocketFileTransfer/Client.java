package zy.e.SocketFileTransfer;

import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            String ip = scanner.next();
            int port = scanner.nextInt();
            String fileSavePath = scanner.next();
            SocketClient socket = new SocketClient(ip, port, fileSavePath);
            socket.start();
            while (true)
            {
                String fileNeedSendPath = scanner.next();
                socket.sendFile(fileNeedSendPath);
            }
        }
    }
}
