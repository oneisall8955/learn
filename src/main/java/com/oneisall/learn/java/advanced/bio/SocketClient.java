package com.oneisall.learn.java.advanced.bio;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author : oneisall
 * @version : v1 2020/5/2 16:23
 */
public class SocketClient {
    public static void main(String[] args) throws IOException {
        Socket client = new Socket("127.0.0.1", 9527);
        System.out.println("connect server success,wait input ...");
        Scanner scanner = new Scanner(System.in);
        String msg = scanner.next();
        System.out.println("input success,msg:" + msg);
        byte[] output = msg.getBytes();
        client.getOutputStream().write(output);
        System.out.println("send success,msg:" + msg);
        client.close();
    }
}
