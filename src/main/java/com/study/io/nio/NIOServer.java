package com.study.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class NIOServer {
    //ͨ��������
    private Selector selector;
    public void initServer(int port) throws IOException {
        // ���һ��ServerSocketͨ��
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        // ����ͨ��Ϊ������
        serverChannel.configureBlocking(false);
        // ����ͨ����Ӧ��ServerSocket�󶨵�port�˿�
        serverChannel.socket().bind(new InetSocketAddress(port));
        // ���һ��ͨ��������
        this.selector = Selector.open();
        //��ͨ���������͸�ͨ���󶨣���Ϊ��ͨ��ע��SelectionKey.OP_ACCEPT�¼�,ע����¼���
        //�����¼�����ʱ��selector.select()�᷵�أ�������¼�û����selector.select()��һֱ������
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);
    }

    public void listen() throws IOException {
        System.out.println("����������ɹ���");
        // ��ѯ����selector
        while (true) {
            //��ע����¼�����ʱ���������أ�����,�÷�����һֱ����
            selector.select();
            // ���selector��ѡ�е���ĵ�������ѡ�е���Ϊע����¼�
            Iterator ite = this.selector.selectedKeys().iterator();
            while (ite.hasNext()) {
                SelectionKey key = (SelectionKey) ite.next();
                // ɾ����ѡ��key,�Է��ظ�����(������ѭ����
                ite.remove();
                // �ͻ������������¼�
                if (key.isAcceptable()) {
                    ServerSocketChannel server = (ServerSocketChannel) key.channel();
                    // ��úͿͻ������ӵ�ͨ��
                    SocketChannel channel = server.accept();
                    channel.configureBlocking(false);
                    //��������Ը��ͻ��˷�����ϢŶ
                    channel.write(ByteBuffer.wrap(new String("��ͻ��˷�����һ����Ϣ").getBytes()));
                    //ע�����Ȥ���¼�����
                    channel.register(this.selector, SelectionKey.OP_READ);
                } else if (key.isReadable()) {// ����˿ɶ����¼�
                    read(key);
                }
            }
        }
    }

    public void read(SelectionKey key) throws IOException{
        // �������ɶ�ȡ��Ϣ:�õ��¼�������Socketͨ��
        SocketChannel channel = (SocketChannel) key.channel();
        // ������ȡ�Ļ�����
        ByteBuffer buffer = ByteBuffer.allocate(10);
        channel.read(buffer);
        byte[] data = buffer.array();
        String msg = new String(data).trim();
        System.out.println("������յ���Ϣ��"+msg);
        ByteBuffer outBuffer = ByteBuffer.wrap(msg.getBytes());
        channel.write(outBuffer);// ����Ϣ���͸��ͻ���
    }

    public static void main(String[] args) throws IOException {
        NIOServer server = new NIOServer();
        server.initServer(8080);
        server.listen();
    }

}
