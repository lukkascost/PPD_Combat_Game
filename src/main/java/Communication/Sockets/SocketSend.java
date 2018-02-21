package Communication.Sockets;

import java.io.*;

public class SocketSend extends Thread {

    private DataOutputStream ostream;
    private boolean run = true;

    public SocketSend(DataOutputStream outputStream) {
        this.ostream = outputStream;
    }

    public void run() {
        while (run) {
            if (SocketCommunication.changedString) {
                try {
                    this.ostream.writeUTF(SocketCommunication.protocolMsg2Send);
                    this.ostream.flush();
                    SocketCommunication.changedString = false;
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}

