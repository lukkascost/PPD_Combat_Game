package Communication.Sockets;

import Communication.ICommunication;
import com.sun.xml.internal.ws.api.streaming.XMLStreamReaderFactory;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


public class SocketCommunication implements Communication.ICommunication {
    private String host;
    private int port;
    private Socket socket;

    private SocketSend socketSend;
    private SocketReceive socketReceive;

    static boolean changedString = false;
    static boolean receivedString = false;

    static String protocolMsg2Send = "";
    static String protocolMsgReceived = "";

    public SocketCommunication(String host, int port) throws IOException {
        this.host = host;
        this.port = port;
        socket = new Socket(host, port);
        socket.close();
    }

    public void send(String msg) {
        protocolMsg2Send = msg;
        changedString = true;
    }

    public String receivedMsg() {
        return protocolMsgReceived;
    }

    public void connect() throws IOException {
        socket = new Socket(host, port);
        socketSend = new SocketSend(new DataOutputStream(socket.getOutputStream()));
        socketReceive = new SocketReceive(new DataInputStream(socket.getInputStream()));
    }
}
