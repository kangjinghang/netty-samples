package top.lijingyuan.netty.sgg.bio;

import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * BIOServer
 *
 * @author <a href="kangjh@shukun.net">kangjinghang</a>
 * @date 2021-04-28
 * @since 1.0.0
 */
public class BIOServer {

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();

        ServerSocket serverSocket = new ServerSocket(6666);

        System.out.println("服务器启动了");

        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("连接到一个客户端了");

            executorService.execute(() -> {
                handler(socket);
            });
        }
    }


    public static void handler(Socket socket) {
        try (InputStream inputStream = socket.getInputStream()) {
            byte[] bytes = new byte[1024];
            while (true) {
                int read = inputStream.read(bytes);
                if (read != -1) {
                    System.out.println(new String(bytes, 0, read));
                } else {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("关闭和socket的连接");
    }
}
