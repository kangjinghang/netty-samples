package top.lijingyuan.netty.sgg.nio.groupchat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;

/**
 * GroupChatClient
 *
 * @author <a href="kangjh@shukun.net">kangjinghang</a>
 * @date 2021-04-30
 * @since 1.0.0
 */
public class GroupChatClient {

    // 定义相关的属性
    // 服务器的ip
    private final String HOST = "127.0.0.1";
    // 服务器端口
    private final int PORT = 6667;
    private Selector selector;
    private SocketChannel socketChannel;
    private String username;

    //构造器,完成初始化工作
    public GroupChatClient() {
        try {
            selector = Selector.open();
            socketChannel = SocketChannel.open(new InetSocketAddress(HOST, PORT));
            socketChannel.configureBlocking(false);
            // 将 channel 注册到selector
            socketChannel.register(selector, SelectionKey.OP_READ);
            //得到 username
            username = socketChannel.getLocalAddress().toString().substring(1);
            System.out.println(username + " is ok...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 向服务器发送消息
    public void sendInfo(String info) throws IOException {
        info = username + " 说：" + info;
        socketChannel.write(ByteBuffer.wrap(info.getBytes()));
    }

    // 读取从服务器端回复的消息
    public void readInfo() throws IOException {
        int count = selector.select();
        if (count > 0) {
            // 有可以用的通道
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                if (key.isReadable()) {
                    // 得到相关的通道
                    SocketChannel sc = (SocketChannel) key.channel();
                    //得到一个 Buffer
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    // 读取
                    sc.read(buffer);
                    // 把读到的缓冲区的数据转成字符串
                    String msg = new String(buffer.array());
                    System.out.println(msg.trim());
                }
            }
            // 删除当前的 selectionKey,防止重复操作
            iterator.remove();
        }
    }

    public static void main(String[] args) throws IOException {
        //启动我们客户端
        GroupChatClient chatClient = new GroupChatClient();
        // 启动一个线程,每个 3 秒，读取从服务器发送数据
        new Thread(() -> {
            while (true) {
                try {
                    chatClient.readInfo();
                    Thread.sleep(3000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

        // 发送数据给服务器端
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String s = scanner.nextLine();
            chatClient.sendInfo(s);
        }
    }

}
