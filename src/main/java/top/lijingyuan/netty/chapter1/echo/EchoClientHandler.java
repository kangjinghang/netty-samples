package top.lijingyuan.netty.chapter1.echo;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.TimeUnit;

/**
 * EchoClientHandler
 *
 * @author <a href="kangjinghang@gmail.com">kangjinghang</a>
 * @date 2021-04-24
 * @since 1.0.0
 */
public class EchoClientHandler extends ChannelInboundHandlerAdapter {

    private final ByteBuf firstMessage;

    public EchoClientHandler() {
        this.firstMessage = Unpooled.wrappedBuffer("I am echo message".getBytes());
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(firstMessage);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ctx.write(firstMessage);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
       try {
           TimeUnit.SECONDS.sleep(3);
       } catch (InterruptedException e) {
           e.printStackTrace();
       }
       ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

}
