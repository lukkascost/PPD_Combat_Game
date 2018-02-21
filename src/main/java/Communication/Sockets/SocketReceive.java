package Communication.Sockets;

import java.io.DataInputStream;
import java.io.IOException;

public class SocketReceive extends  Thread{

    private DataInputStream istream;
    private boolean run = true;


    public SocketReceive(DataInputStream dataInputStream) {
        this.istream = dataInputStream;
    }


    public void run(){
        while (run) {
            try {
                SocketCommunication.protocolMsgReceived = istream.readUTF();
            } catch (IOException e) {
                e.printStackTrace();
            }
            SocketCommunication.receivedString = true;
            while(SocketCommunication.receivedString){}
        }
    }


}
