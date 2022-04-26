package mobi.zishun.javabase.network;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class BioSocketServer {
    public static void main(String[] args) throws IOException {
        startServerSingleClient(9716);
//        startServerClients(9717);
    }

    // 同步时服务端的示例代码
    // 服务端同时只能服务一个客户端，其它客户端可以先接入和请求但是需要等待第一个连接的客户端关闭连接后服务端才会返回
    public static void startServerSingleClient(int port) throws IOException {
        // 创建Socket
        ServerSocket serverSocket = new ServerSocket(port);
        //2 接受客户端连(此方法是阻塞式的，如果没有连接则会一直等待)
        // 在 Socket socket = server.accept()的时候，接受一次客户端连接后，此时的代码就不会在执行了
        // 通过循环来让服务端继续执行代码，其它客户端可以等待第一个连接的客户端关闭连接后和服务端联系
        while (true) {
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

    // 支持多客户端请求服务端代码
    // 多线程 异步
    public static void startServerClients(int port) throws IOException {
        // 创建Socket
        ServerSocket serverSocket = new ServerSocket(port);
        while (true) {
            Socket clientSocket = serverSocket.accept();
            //当新的客户端到达时，开启新的线程去处理。然后主线程继续接受新的客户端
            new Thread(new HandlerSocket(clientSocket)).start();
        }
    }

    public static class HandlerSocket implements Runnable {
        private Socket socket;

        public HandlerSocket(Socket socket) {
            this.socket = socket;
        }

        @SneakyThrows
        public void run() {
            InputStream inputStream = socket.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String msg;
            while ((msg = bufferedReader.readLine()) != null) {
                System.out.println("收到客户端消息" + msg);
            }
        }
    }
}