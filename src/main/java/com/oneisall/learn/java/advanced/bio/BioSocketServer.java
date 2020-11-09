package com.oneisall.learn.java.advanced.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * BIO 服务端
 *
 * @author : oneisall
 * @version : v1 2020/5/2 15:46
 */
public class BioSocketServer {
    public static void main(String[] args) throws IOException {
        byte[] bytes = new byte[1024];
        ServerSocket serverSocket = new ServerSocket(9527);
        while (true) {
            System.out.println("accept before,wait connect ...");
            Socket socket;
            try {
                // 这里会阻塞，等待客户端的连接
                socket = serverSocket.accept();
            } catch (IOException e) {
                break;
            }

            // 一旦连接成功，则往下走↓↓↓↓↓，第二个客户端连接不上来

            System.out.println("accept after,read before,wait data");
            // 这里会阻塞，等待客户端的数据
            int read = socket.getInputStream().read(bytes);
            System.out.println("read = " + read + ",data complete!");
            System.out.println("read after,bytes to string:" + new String(bytes));
        }

    }
}
