package Communication.Sockets;

import Communication.CommonStatic;

import java.io.*;

public class SocketSend extends Thread {

    private DataOutputStream ostream;
    private boolean run = true;

    public SocketSend(DataOutputStream outputStream) {
        this.ostream = outputStream;
    }

    public void run() {
        while (run) {
            if (CommonStatic.changedString) {
                try {
                    this.ostream.writeUTF(CommonStatic.protocolMsg2Send);
                    this.ostream.flush();
                    CommonStatic.changedString = false;
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}

