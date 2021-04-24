package top.lijingyuan.netty.chapter1.http;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;

import static io.netty.handler.codec.http.HttpHeaderNames.CONTENT_LENGTH;
import static io.netty.handler.codec.http.HttpHeaderNames.CONTENT_TYPE;
import static io.netty.handler.codec.http.HttpHeaderValues.TEXT_PLAIN;

/**
 * HttpHelloWorldServerHandler
 *
 * @author <a href="kangjinghang@gmail.com">kangjinghang</a>
 * @date 2021-04-24
 * @since 1.0.0
 */
@ChannelHandler.Sharable
public class HttpHelloWorldServerHandler extends SimpleChannelInboundHandler<HttpObject> {

    private static final byte[] CONTENT = "helloworld".getBytes();

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
        if(msg instanceof HttpRequest){
            HttpRequest req = (HttpRequest)msg;
            FullHttpResponse response = new DefaultFullHttpResponse(req.protocolVersion(), HttpResponseStatus.OK,
                    Unpooled.wrappedBuffer(CONTENT));
            response.headers()
                    .set(CONTENT_TYPE, TEXT_PLAIN)
                    .setInt(CONTENT_LENGTH, response.content().readableBytes());

            ChannelFuture f = ctx.write(response);
        }
    }

}
