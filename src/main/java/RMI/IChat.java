package RMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IChat extends Remote {
    String writeMessage(String message, String player) throws RemoteException;
}
