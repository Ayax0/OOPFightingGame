package com.nextlvlup.server.network;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import com.nextlvlup.network.PacketListener;
import com.nextlvlup.network.UDPSocket;
import com.nextlvlup.network.packet.TimeoutPacket;

public class UDPServer extends Thread {

	private DatagramSocket socket;
	private byte[] buf = new byte[512];
	
	private boolean running;
	
	@SuppressWarnings("rawtypes")
	private HashMap<Class<? extends Serializable>, ArrayList<PacketListener>> listeners = new HashMap<>();
	
	public HashMap<UDPSocket, Long> sockets = new HashMap<>();
	
	public UDPServer(int port) throws SocketException {
		this.socket = new DatagramSocket(port);
	}
	
	public void broadcast(Object obj) {
		for(UDPSocket receiver : sockets.keySet()) {
			sendPacket(receiver, obj);
		}
	}
	
	public void broadcast(Object obj, UDPSocket sender) {
		for(UDPSocket receiver : sockets.keySet()) {
			if(receiver.equals(sender)) continue;
			sendPacket(receiver, obj);
		}
	}
	
	public void sendPacket(UDPSocket receiver, Object object) {
		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ObjectOutput oo = new ObjectOutputStream(bos);
			oo.writeObject(object);
			oo.close();
			
			byte[] outBuf = bos.toByteArray();
			DatagramPacket packet = new DatagramPacket(outBuf, outBuf.length, receiver.getAddress(), receiver.getPort());
			
			socket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings({ "unchecked" })
	@Override
	public void run() {
		running = true;
		System.out.println("server is running on port " + socket.getPort());
		
		while(running) {
			try {
				DatagramPacket packet = new DatagramPacket(buf, buf.length);
				socket.receive(packet);
				
				ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(buf));
				Object obj = in.readObject();
				
				UDPSocket sender = new UDPSocket(packet.getAddress().getAddress(), packet.getPort());
				sockets.put(sender, System.currentTimeMillis());
				
				Iterator<Map.Entry<UDPSocket, Long>> iter = sockets.entrySet().iterator();
				while (iter.hasNext()) {
					Map.Entry<UDPSocket, Long> entry = iter.next();
					if((System.currentTimeMillis() - entry.getValue()) < 3000) continue;
					
					this.broadcast(new TimeoutPacket(entry.getKey()));
					
					System.err.println("Socket timed out!");
					iter.remove();
				}
				
				if(!this.listeners.containsKey(obj.getClass())) continue;
				for(PacketListener<Serializable> listener : this.listeners.get(obj.getClass())) {
					listener.handler((Serializable) obj, sender);
				}
			} catch(IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (ConcurrentModificationException e) {
				e.printStackTrace();
			}
		}
	}
	
	@SuppressWarnings("rawtypes")
	public void addPacketListener(Class<? extends Serializable> _class, PacketListener<? extends Serializable> listener) {
		if(!this.listeners.containsKey(_class))
			this.listeners.put(_class, new ArrayList<PacketListener>());
		this.listeners.get(_class).add(listener);
	}
	
}
