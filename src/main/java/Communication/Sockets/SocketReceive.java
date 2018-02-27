package Communication.Sockets;

import Communication.CommonStatic;

import java.io.DataInputStream;
import java.io.IOException;

public class SocketReceive extends  Thread{

    private DataInputStream istream;
    private boolean run = true;


    public SocketReceive(DataInputStream dataInputStream) {
        this.istream = dataInputStream;
        this.start();
    }

    @Override
    public void run(){
        while (run) {
            try {
                CommonStatic.protocolMsgReceived = istream.readUTF();
                CommonStatic.onDataReceive.release();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
