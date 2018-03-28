package RMI;

import Chat.ChatText;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IChat extends Remote {

    String writeMessage(String message, String player) throws RemoteException;
    void setChatText(String chatText)throws RemoteException;
    String getChatText()throws RemoteException;
}
