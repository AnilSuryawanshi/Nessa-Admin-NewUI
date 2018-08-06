package com.connecticus.admin.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
public static void main(String[] args) throws UnknownHostException, IOException {
	Socket socket = new Socket("127.0.0.1",9999);
	System.out.println("connected to server");
	InputStream in = socket.getInputStream();
	OutputStream out = socket.getOutputStream();
	out.write("hello from client".getBytes());
	byte[] response = new byte[1024];
	in.read(response);
	System.out.println("Recieved from server"+new String(response).trim());
	socket.close();
}
}
