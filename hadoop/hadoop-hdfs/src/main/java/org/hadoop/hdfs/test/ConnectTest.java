package org.hadoop.hdfs.test;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Objects;

public class ConnectTest {
	
	
	public static void main(String[] args) {
		if (Objects.isNull(args) || args.length != 2) {
			throw new IllegalArgumentException("ip port");
		}
		String host = args[0];
		int port = Integer.parseInt(args[1]);

		try (Socket socket = new Socket(host, port);) {
			if (socket.isConnected()) {
				System.out.println("测试成功");
			} else {
				System.out.println("测试失败");
			}

		} catch (UnknownHostException e) {
			System.out.println("测试失败");
		} catch (IOException e) {
			System.out.println("测试失败");
		}

	}

}
