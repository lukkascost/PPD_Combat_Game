package RMI;

import MainPackage.ApplicationRun;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ChatImplementation extends UnicastRemoteObject implements IChat {

    public ChatImplementation() throws RemoteException {
        super();
    }
    public String writeMessage(String message, String player) {
        ApplicationRun.chatPanel.getChatTextLog().append(player+": "+message);
        return player+": "+message;
    }
}
