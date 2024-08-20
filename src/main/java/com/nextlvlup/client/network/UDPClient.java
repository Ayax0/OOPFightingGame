package com.nextlvlup.client.network;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import com.nextlvlup.network.PacketListener;
import com.nextlvlup.network.packet.KeepAlivePacket;

public class UDPClient extends Thread {
	
	private DatagramSocket socket;
	private InetAddress address;
	
	@SuppressWarnings("rawtypes")
	private HashMap<Class<? extends Serializable>, ArrayList<PacketListener>> listeners = new HashMap<>();
	
	public UDPClient() throws SocketException, UnknownHostException {
		socket = new DatagramSocket();
		address = InetAddress.getByName("localhost");
	}
	
	@SuppressWarnings("rawtypes")
	public void addPacketListener(Class<? extends Serializable> _class, PacketListener<? extends Serializable> listener) {
		if(!this.listeners.containsKey(_class))
			this.listeners.put(_class, new ArrayList<PacketListener>());
		this.listeners.get(_class).add(listener);
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
	
	@SuppressWarnings("unchecked")
	@Override
	public void run() {
		new Timer().scheduleAtFixedRate(new TimerTask() {
			
			@Override
			public void run() {
				sendPacket(new KeepAlivePacket());
			}
		}, 0, 1000);
		
		while(true) {
			byte[] inBuf = new byte[512];
			
			try {
				DatagramPacket packet = new DatagramPacket(inBuf, inBuf.length);
				socket.receive(packet);
				
				ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(inBuf));
				Object obj = in.readObject();
				
				for(PacketListener<Serializable> listener : this.listeners.get(obj.getClass())) {
					listener.handler((Serializable) obj, packet.getAddress(), packet.getPort());
				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

}