package Communication.Sockets;

import Communication.CommonStatic;

import java.io.*;

public class SocketSend extends Thread {

    private DataOutputStream ostream;
    private boolean run = true;

    public SocketSend(DataOutputStream outputStream) {
        this.ostream = outputStream;
        this.start();
    }
    @Override
    public void run() {
        while (run) {
            try {
                CommonStatic.onDataSend.acquire();
                this.ostream.writeUTF(CommonStatic.protocolMsg2Send);
                this.ostream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
