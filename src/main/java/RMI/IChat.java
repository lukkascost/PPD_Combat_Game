package RMI;

import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IChat extends Remote {
    String writeMessage(String message, String player) throws RemoteException;
    String writeLog(String message, String player) throws RemoteException;
    void skipTurnChat()throws RemoteException;
    int restartMatch() throws  RemoteException;
    void atualizaGlobal()throws  RemoteException, NotBoundException;
}
