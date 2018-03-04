package Communication.Sockets;

import Communication.CommonStatic;
import MainPackage.ApplicationRun;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.SocketException;

public class SocketReceive extends  Thread {

    private DataInputStream istream;
    private boolean run = true;


    public SocketReceive(DataInputStream dataInputStream) {
        this.istream = dataInputStream;
        this.start();
    }

    @Override
    public void run() {
        while (run) {
            try {
                CommonStatic.protocolMsgReceived = istream.readUTF();
                CommonStatic.onDataReceive.release();
            } catch (SocketException e) {
                ApplicationRun.chatPanel.writeLog("Desconectado... ");
                ApplicationRun.chatPanel.writeLog("Encerrando... ");
                CommonStatic.isConnected=false;
                try {
                    this.sleep(10000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }

                System.exit(0);
                this.run = false;
            } catch (IOException e) {
                e.printStackTrace();

            }
        }
    }
}