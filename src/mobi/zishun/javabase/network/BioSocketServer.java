package mobi.zishun.javabase.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class BioSocketServer {
    public static void main(String[] args) throws IOException {
        serve(9716);
    }

    public static void serve(int port) throws IOException {
        // 创建Socket
        ServerSocket serverSocket = new ServerSocket(port);
        //2 接受客户端连(此方法是阻塞式的，如果没有连接则会一直等待)
        Socket clientSocket = serverSocket.accept();
        //3获取客户端的字节输入流
        InputStream inputStream = clientSocket.getInputStream();
        //将字节流转字符输入流
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String msg;
        //4按行接受客户端的信息（此方法是阻塞式的，会一直读取客户端的信息）
        while ((msg = bufferedReader.readLine()) != null) {
            System.out.println("收到客户端的信息" + msg);
        }
    }
}