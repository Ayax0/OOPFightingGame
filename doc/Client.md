# Client

## UDPClient
In der UDPClient Klasse wir eine neue DatagramSocket instanz erzeugt.

### Helper Funktionen
Nachfolgend sind die enthaltenen Helper Funktionen beschrieben.

**sendPacket(obj):**
Sendet das übergebene Packet (obj) an den Server

**addPacketListener(_class, listener):**
Registriert einen PacketListener welcher durch das definierte Packet (_class) getriggert wird.

```java
client.addPacketListener(PlayerJoinPacket.class, new PacketListener<PlayerJoinPacket>() {

  @Override
  public void handler(PlayerJoinPacket obj, InetAddress address, int port) {
    System.out.println("player moved!");
  }
  
});
```

### Client Thread
Da die UDPClient Klasse von den Thread Klasse erbt kann diese mit der `client.start()` Methode gestartet werden. Dieses Starten des Clients hat keinen einfluss auf die Verbindungen zum Server da UDP über keine Verbindung verfügt. Der gestartete Thread ist einzig dafür da die empfangenen Packete zu verarbeiten bzw an die registrierten PacketListener weiterzuleiten und allgemeine Aufgaben zu erledigen wie z.B. das KeepAlive Packet zu versenden.

Hierbei wird bei jedem Empfangenen Packet überprüft ob für diesen PacketType ein Listener registriert ist. Falls dies der Fall ist wird das Packet an diesen weitergeleitet.