package mobi.zishun.javabase.network;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/*
 * 典型的BIO服务端:
1. 一个主线程在某个port监听，等待客户端连接。
2. 当接收到客户端发起的连接时，创建一个新的线程去处理客户端请求。
3. 主线程重新回到port监听，等待下一个客户端连接。
缺点:
1. 每个新的客户端Socket都需要创建一个新的Thread处理，将会导致大量的线程处于休眠状态。
2. 每个线程都有调用栈的内存分配，连接数非常多时，耗费较多内存。
3. 连接数比较多时，创建大量线程，上下文切换所带来的开销较大。
* https://blog.csdn.net/thinking_fioa/article/details/80398579
 */
public class BioSocketServer {
    public static void main(String[] args) throws IOException {
//        startServerSingleClient(9716);
//        startServerClients(9716);
        startServerClientsByThreadPools(9718);
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
            // 当新的客户端到达时，开启新的线程去处理。然后主线程继续接受新的客户端
            // 这种情况，线程可以无限增加，最终导致OOM，可使用线程池控制线程数量（可同时连接的客户端数量）
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

    public static void startServerClientsByThreadPools(int port) throws IOException {
        // 创建Socket
        ServerSocket serverSocket = new ServerSocket(port);
        // 当新的客户端到达时，从线程池中拿到空闲的线程去处理。然后主线程继续接受新的客户端
        // 这种情况，线程可以无限增加，最终导致OOM，可使用线程池控制线程数量（可同时连接的客户端数量）
        HandlerSocketThreadPools pools = new HandlerSocketThreadPools(2, 3);
        while (true) {
            Socket clientSocket = serverSocket.accept();
            pools.execute(new HandlerSocket(clientSocket));
        }
    }

    public static class HandlerSocketThreadPools {
        private final ThreadPoolExecutor threadPoolExecutor;

        public HandlerSocketThreadPools(int maxSize, int size) {
            threadPoolExecutor = new ThreadPoolExecutor(maxSize, maxSize, 120, TimeUnit.MICROSECONDS, new ArrayBlockingQueue<>(size));
        }

        public void execute(Runnable runnable) {
            threadPoolExecutor.execute(runnable);
        }
    }
}