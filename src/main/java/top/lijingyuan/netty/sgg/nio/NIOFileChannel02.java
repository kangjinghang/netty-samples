package top.lijingyuan.netty.sgg.nio;

import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * NIOFileChannel
 *
 * @author <a href="kangjh@shukun.net">kangjinghang</a>
 * @date 2021-04-29
 * @since 1.0.0
 */
public class NIOFileChannel02 {

    public static void main(String[] args) throws Exception {
        FileInputStream fileInputStream = new FileInputStream("/Users/wanderer/temp/file01.txt");
        FileChannel channel = fileInputStream.getChannel();
        FileDescriptor fd = fileInputStream.getFD();
        System.out.println("fd: " + fd);

        ByteBuffer buffer = ByteBuffer.allocate(1024);

        channel.read(buffer);
        System.out.println(new String(buffer.array(), 0, buffer.limit()));
        fileInputStream.close();
    }

}
