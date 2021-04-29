package top.lijingyuan.netty.sgg.nio;

import java.io.FileInputStream;
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
public class NIOFileChannel03 {

    public static void main(String[] args) throws Exception {

        try (FileInputStream fileInputStream = new FileInputStream("/Users/wanderer/temp/file01.txt");
             FileOutputStream fileOutputStream = new FileOutputStream("/Users/wanderer/temp/file02.txt")) {
            FileChannel fileChannel01 = fileInputStream.getChannel();
            FileChannel fileChannel02 = fileOutputStream.getChannel();

            ByteBuffer buffer = ByteBuffer.allocate(1024);

            while (true) {
                // 清空buffer
                buffer.clear();
                int read = fileChannel01.read(buffer);
                if (read == -1) {
                    break;
                }
                buffer.flip();
                fileChannel02.write(buffer);
            }
        }
    }

}
