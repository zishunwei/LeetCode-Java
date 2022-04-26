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
//        startServerSingleClient(9716);
        startServerClients(9717);
    }

    public static void startServerSingleClient(int port) throws IOException {
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

    public static void startServerClients(int port) throws IOException {
        // 创建Socket
        ServerSocket serverSocket = new ServerSocket(port);
        while (true) {
            Socket socket = serverSocket.accept();
            //当新的客户端到达时，开启新的线程去处理。然后主线程继续接受新的客户端
            new Thread(new HandlerSocket(socket)).start();
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
            String msg = null;
            while ((msg = bufferedReader.readLine()) != null) {
                System.out.println("收到客户端消息" + msg);
            }
        }
    }
}