    package top.lijingyuan.netty.sgg.nio;

    import java.io.FileInputStream;
    import java.io.FileOutputStream;
    import java.nio.channels.FileChannel;

    /**
     * NIOFileChannel
     *
     * @author <a href="kangjh@shukun.net">kangjinghang</a>
     * @date 2021-04-29
     * @since 1.0.0
     */
    public class NIOFileChannel04 {

        public static void main(String[] args) throws Exception {

            try (FileInputStream fileInputStream = new FileInputStream("/Users/wanderer/temp/file01.txt");
                 FileOutputStream fileOutputStream = new FileOutputStream("/Users/wanderer/temp/file02.txt")) {
                FileChannel sourceChannel = fileInputStream.getChannel();
                FileChannel destChannel = fileOutputStream.getChannel();

                destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());

            }
        }

    }
