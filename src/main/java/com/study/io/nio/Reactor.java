package com.study.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

public class Reactor implements Runnable {

	private final Selector selector;
	private final ServerSocketChannel serverSocketChannel;
	
	public Reactor(int port) throws IOException {
		this.selector = Selector.open();
		this.serverSocketChannel = ServerSocketChannel.open();
		this.serverSocketChannel.configureBlocking(false);
		/**
		 * The backlog argument is the requested maximum number of pending connections 
		 * on the socket. Its exact semantics are implementation specific. 
		 * In particular, an implementation may impose a maximum length or may choose 
		 * to ignore the parameter altogther. The value provided should be greater than 0.
		 *  If it is less than or equal to 0, then an implementation specific default
		 */
		this.serverSocketChannel.socket().bind(new InetSocketAddress(port), 1024);
		SelectionKey sk = this.serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
		//���Խ�һ��������߸�����Ϣ���ŵ�SelectionKey�ϣ��������ܷ����ʶ��ĳ��������ͨ����
		//���磬���Ը��� ��ͨ��һ��ʹ�õ�Buffer�����ǰ����ۼ����ݵ�ĳ������
		//sk.attach(null);
	}


	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

}
