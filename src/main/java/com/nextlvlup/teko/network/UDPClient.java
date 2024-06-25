package com.nextlvlup.teko.network;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class UDPClient extends Thread {
	
	private DatagramSocket socket;
	private InetAddress address;
	
	private ArrayList<PacketListener> listeners = new ArrayList<PacketListener>();
	
	public UDPClient() throws SocketException, UnknownHostException {
		socket = new DatagramSocket();
		address = InetAddress.getByName("localhost");
	}
	
	public void addPacketListener(PacketListener listener) {
		this.listeners.add(listener);
	}
	
	public void sendPacket(Object object) {
		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ObjectOutput oo = new ObjectOutputStream(bos);
			oo.writeObject(object);
			oo.close();
			
			byte[] outBuf = bos.toByteArray();
			DatagramPacket packet = new DatagramPacket(outBuf, outBuf.length, address, 5000);
			
			socket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		while(true) {
			byte[] inBuf = new byte[512];
			
			try {
				DatagramPacket packet = new DatagramPacket(inBuf, inBuf.length);
				socket.receive(packet);
				
				ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(inBuf));
				Object obj = in.readObject();
				
				for(PacketListener listener : listeners) {
					listener.handler(obj, packet.getAddress(), packet.getPort());
				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

}
