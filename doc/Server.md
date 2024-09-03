# Server

## UDPServer
In der UDPServer Klasse wir eine neue DatagramSocket instanz für den übergebenen Port erzeugt.

### Helper Funktionen
Nachfolgend sind die enthaltenen Helper Funktionen beschrieben.

**broadcast(obj):**
Sendet das übergebene Packet (obj) an alle in der `sockets` enthaltenen UDPClients.

**broadcast(obj, sender):**
Sendet das übergebene Packet (obj) am alle in der `sockets` enthaltenen UDPClients, ausser an den Absender (sender) selbst.

**sendPacket(receiver, obj):**
Sendet das übergebene Packet (obj) an den spezifizierten Empfänger (receiver)

**addPacketListener(_class, listener):**
Registriert einen PacketListener welcher durch das definierte Packet (_class) getriggert wird.

```java
server.addPacketListener(PlayerMovePacket.class, new PacketListener<PlayerMovePacket>() {

  @Override
  public void handler(PlayerMovePacket obj, InetAddress address, int port) {
    System.out.println("player moved!");
  }
  
});
```

### Server Thread
Da die UDPServer Klasse von den Thread Klasse erbt kann diese mit der `server.start()` Methode gestartet werden. Dieses Starten des Servers hat keinen einfluss auf die Verbindungen zu den Clients da UDP über keine Verbindung verfügt. Der gestartete Thread ist einzig dafür da die empfangenen Packete zu verarbeiten bzw an die registrierten PacketListener weiterzuleiten.

Hierbei wird bei jedem Empfangenen Packet überprüft ob für diesen PacketType ein Listener registriert ist. Falls dies der Fall ist wird das Packet an diesen weitergeleitet.