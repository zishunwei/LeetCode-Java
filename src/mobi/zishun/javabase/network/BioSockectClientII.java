package mobi.zishun.javabase.network;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class BioSockectClientII {
    public static void main(String[] args) throws IOException {
        startClient(9718);
    }

    public static void startClient(int port) throws IOException {
        //创建客户端套接字
        Socket socket = new Socket();
        //连接本地端口9999
        socket.connect(new InetSocketAddress(port), 1000);
        //得到字节输出流
        OutputStream outputStream = socket.getOutputStream();
        //将字节输出流转成打印流
        PrintStream printStream = new PrintStream(outputStream);
        //获取键盘输入
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String next = scanner.next();
            //将数据输出
            printStream.println(next);
        }
    }
}
