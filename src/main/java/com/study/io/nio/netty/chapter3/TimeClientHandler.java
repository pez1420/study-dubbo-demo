package com.study.io.nio.netty.chapter3;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class TimeClientHandler extends ChannelInboundHandlerAdapter{
	
	private final ByteBuf firstMessage;

	public TimeClientHandler() {
		byte[] req = "QUERY TIME ORDER".getBytes();
		firstMessage = Unpooled.buffer(req.length);
		firstMessage.writeBytes(req);
	}

	/**
	 * �ͻ��˽���TCP����֮��,��server����ָ��
	 */
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		ctx.writeAndFlush(firstMessage);
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		ByteBuf byteBuf = (ByteBuf)msg;
		byte req[] = new byte[byteBuf.readableBytes()];
		byteBuf.readBytes(req);
		
		String body = new String(req, "UTF-8");
		System.out.println("Now is :" + body);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		System.out.println("Unexpected exception from downstream : " + cause.getMessage());
		super.exceptionCaught(ctx, cause);
	}
	
}
