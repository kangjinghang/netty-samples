package top.lijingyuan.netty.sgg.nio;

import java.nio.IntBuffer;

/**
 * BasicBuffer
 *
 * @author <a href="kangjh@shukun.net">kangjinghang</a>
 * @date 2021-04-29
 * @since 1.0.0
 */
public class BasicBuffer {

    public static void main(String[] args) {
        IntBuffer buffer = IntBuffer.allocate(5);
        for (int i = 0; i < buffer.capacity(); i++) {
            buffer.put(i * 2);
        }

        buffer.flip();

        while (buffer.hasRemaining()) {
            System.out.println(buffer.get());
        }
    }

}
