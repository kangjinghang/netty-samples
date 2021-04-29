package top.lijingyuan.netty.sgg.nio;

import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * NIOFileChannel
 *
 * @author <a href="kangjh@shukun.net">kangjinghang</a>
 * @date 2021-04-29
 * @since 1.0.0
 */
public class NIOFileChannel {

    public static void main(String[] args) throws Exception {
        String str = "hello wanderer";

        FileOutputStream fileOutputStream = new FileOutputStream("/Users/wanderer/temp/file01.txt");
        FileChannel channel = fileOutputStream.getChannel();
        FileDescriptor fd = fileOutputStream.getFD();
        System.out.println("fd: " + fd);

        ByteBuffer buffer = ByteBuffer.allocate(1024);

        buffer.put(str.getBytes());
        // 开始读，反转
        buffer.flip();

        channel.write(buffer);
        fileOutputStream.close();
    }

}
