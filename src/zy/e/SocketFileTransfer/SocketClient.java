package zy.e.SocketFileTransfer;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketClient extends Thread {
    private String ip = null; // ip 地址
    private int port; // 端口号
    private Socket socket; // Socket 对象
    private String fileSavePath; // 文件保存路径
    private ServerSocket serverSocket = null; // 服务器套接字
    private InputStream inputStream = null; // 字节输入流
    private OutputStream outputStream = null; // 字节输出流
    private FileOutputStream fileOutputStream = null; // 文件输出流
    private FileInputStream fileInputStream = null; // 文件输入流

    /**
     * 作为服务器构建 Socket
     *
     * @param ip           ip 地址
     * @param port         端口号
     * @param fileSavePath 文件保存路径
     */
    public SocketClient(String ip, int port, String fileSavePath) {
        this.ip = ip;
        this.port = port;
        this.fileSavePath = fileSavePath;
    }

    /**
     * 作为客户端构建 Socket
     *
     * @param port         端口号
     * @param fileSavePath 文件保存路径
     */
    public SocketClient(int port, String fileSavePath) {
        this.port = port;
        this.fileSavePath = fileSavePath;
    }

    /**
     * 启动程序并开始等待文件写入
     */
    @Override
    public void run() {
        super.run();
        try {
            // 如果 IP 地址不存在，则为服务端，否则为客户端
            if (ip == null) {
                serverSocket = new ServerSocket(port);
                socket = serverSocket.accept();
            } else {
                socket = new Socket(ip, port);
            }
            System.out.println("连接成功");
            // 初始化输入流
            inputStream = socket.getInputStream();
            // 初始化输出流
            outputStream = socket.getOutputStream();
            while (true) {
                // 读取文件名信息
                byte[] fileNameBytes = new byte[1024]; // 文件名缓冲
                int fileNameLength; // 文件名长度
                String fileName = null; // 文件名
                while (true) {
                    if ((fileNameLength = inputStream.read(fileNameBytes)) != -1) {
                        fileName = new String(fileNameBytes, 0, fileNameLength);
                        break;
                    }
                }
                // 读取文件大小
                byte[] fileSizeBytes = new byte[1024]; // 文件大小缓冲
                int fileSizeLength; // 文件大小长度
                int fileSize; // 文件大小
                while (true) {
                    if ((fileSizeLength = inputStream.read(fileSizeBytes)) != -1) {
                        fileSize = Integer.parseInt(new String(fileSizeBytes, 0, fileSizeLength));
                        break;
                    }
                }
                // 初始化文件输出流
                fileOutputStream = new FileOutputStream(fileSavePath + fileName);
                // 初始化缓冲区
                byte[] bytes = new byte[1024];
                int len; // 读取文件的长度
                while (fileSize > 0 && (len = inputStream.read(bytes)) != -1) {
                    fileOutputStream.write(bytes, 0, len); // 写入文件
                    fileOutputStream.flush(); // 刷新缓冲区
                    fileSize -= len; // 减少文件大小
                }
                fileOutputStream.close();
                System.out.println("文件接收成功");
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close(); // 关闭流
        }
    }

    /**
     * 关闭所有资源
     */
    public void close() {
        try {
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
            if (socket != null) {
                socket.close();
            }
            if (serverSocket != null) {
                serverSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送文件
     *
     * @return 文件路径
     */
    public void sendFile(String filePath) {
        File file = new File(filePath);
        // 判断文件是否存在
        if (file.exists()) {
            // 如果文件存在，则发送文件
            try {
                // 初始化文件输入流
                fileInputStream = new FileInputStream(file);
                // 发送文件名
                outputStream.write(file.getName().getBytes());
                // 发送文件大小
                outputStream.write(String.valueOf(file.length()).getBytes());
                // 发送文件
                // 初始化缓冲区
                byte[] bytes = new byte[1024];
                int len; // 读取文件的长度
                while ((len = fileInputStream.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, len); // 写入文件
                    outputStream.flush();
                }
                fileInputStream.close();
                System.out.println("文件发送成功");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("文件不存在");
        }
    }
}
