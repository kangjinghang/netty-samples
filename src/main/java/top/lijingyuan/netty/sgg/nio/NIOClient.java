package top.lijingyuan.netty.sgg.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * NIOClient
 *
 * @author <a href="kangjh@shukun.net">kangjinghang</a>
 * @date 2021-04-29
 * @since 1.0.0
 */
public class NIOClient {

    public static void main(String[] args) throws Exception {
        // 得到一个网络通道
        try (SocketChannel socketChannel = SocketChannel.open()) {
            // 设置非阻塞
            socketChannel.configureBlocking(false);
            // 提供服务器端的ip 和 端口
            InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1", 6666);
            // 连接服务器
            if (!socketChannel.connect(inetSocketAddress)) {
                while (!socketChannel.finishConnect()) {
                    System.out.println("因为连接需要时间，客户端不会阻塞，可以做其它工作..");
                }
            }
            // ...如果连接成功，就发送数据
            String str = "hello world";
            ByteBuffer buffer = ByteBuffer.wrap(str.getBytes());
            // 发送数据，将 buffer 数据写入 channel
            socketChannel.write(buffer);
            System.in.read();
        }
    }

}
