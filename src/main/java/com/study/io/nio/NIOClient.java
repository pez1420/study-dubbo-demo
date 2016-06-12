package com.study.io.nio;

//http://leetidea.com/2016/01/20/java-NIO-Reactor%E6%A8%A1%E5%BC%8F/
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class NIOClient {
    //ͨ��������
    private Selector selector;
    public void initClient(String ip,int port) throws IOException {
        SocketChannel channel = SocketChannel.open();
        channel.configureBlocking(false);
        this.selector = Selector.open();
        // �ͻ������ӷ�����,��ʵ����ִ�в�û��ʵ�����ӣ���Ҫ��listen���������е�
        //��channel.finishConnect();�����������
        channel.connect(new InetSocketAddress(ip,port));
        channel.register(selector, SelectionKey.OP_CONNECT);
    }

    /**
     * ������ѯ�ķ�ʽ����selector���Ƿ�����Ҫ������¼�������У�����д���
     */
    public void listen() throws IOException {
        while (true) {
            selector.select();
            Iterator ite = this.selector.selectedKeys().iterator();
            while (ite.hasNext()) {
                SelectionKey key = (SelectionKey) ite.next();
                ite.remove();
                // �����¼�����
                if (key.isConnectable()) {
                    SocketChannel channel = (SocketChannel) key.channel();
                    // ����������ӣ����������
                    if(channel.isConnectionPending()){
                        channel.finishConnect();
                    }
                    channel.configureBlocking(false);
                    channel.write(ByteBuffer.wrap(new String("�����˷�����һ����Ϣ").getBytes()));
                    //ע�����Ȥ���¼�������
                    channel.register(this.selector, SelectionKey.OP_READ);
                } else if (key.isReadable()) {// ����˿ɶ����¼�
                    read(key);
                }

            }

        }
    }

    public void read(SelectionKey key) throws IOException{
        //�ͷ���˵�read����һ��
    }

    public static void main(String[] args) throws IOException {
        NIOClient client = new NIOClient();
        client.initClient("localhost",8080);
        client.listen();
    }

}
