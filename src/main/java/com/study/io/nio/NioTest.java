package com.study.io.nio;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;


public class NioTest {

	public static void main(String[] args) {
		ByteBuffer byteBuffer = ByteBuffer.allocate(100);

		byteBuffer.put((byte)1);
		byteBuffer.put((byte)2);
		
		byteBuffer.flip();
		while (byteBuffer.hasRemaining()) {
			byte c = byteBuffer.get();
			System.out.println(c);
		}

		//position置为0，limit保持不变，可以用于重复读取一段数据
		byteBuffer.rewind();
		while (byteBuffer.hasRemaining()) {
			byte c = byteBuffer.get();
			System.out.println(c);
		}
		//The position is set to zero, the limit is set to the capacity, and the mark is discarded. 
		byteBuffer.clear();
		
		String response = "you are my friends!";
		byte [] bytes = response.getBytes();
		byteBuffer.put(bytes);
		while (byteBuffer.hasRemaining()) {
			byteBuffer.flip();
			byte []dst = new byte[byteBuffer.remaining()]; 
			byteBuffer.get(dst);
			try {
				String result = new String(dst, "utf-8");
				System.out.println(result);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		
		   String sendString = "dddd";
		   try {
				ByteBuffer sendBuffer = ByteBuffer.wrap(sendString.getBytes());
				sendBuffer.flip();
				byte[] sendMsg = new byte[sendBuffer.limit()];
				sendBuffer.get(sendMsg);
				System.out.println(new String(sendMsg, "utf-8"));
		   } catch (UnsupportedEncodingException e) {
				e.printStackTrace();
		   }
		    
	}

}
