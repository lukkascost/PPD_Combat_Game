package RMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IChat extends Remote {
    String writeMessage(String message, String player, String saveIn) throws RemoteException;
    boolean isOnlineChecked() throws RemoteException;
    String getChatWith(String name) throws RemoteException;
}
