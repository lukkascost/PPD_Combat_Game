package RMI;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import static MainPackage.ApplicationRun.*;

public class ChatImplementation extends UnicastRemoteObject implements IChat {

    public ChatImplementation() throws RemoteException {
    }

    @Override
    public String writeMessage(String message, String player) throws RemoteException {
        if(friendChatContent.containsKey(player)){
            friendChatContent.put(player,"");
        }
        String chatContent = (String) friendChatContent.get(player);
        chatContent += player+ ": " +message+"\n";
        friendChatContent.replace(player,chatContent);
        return "OK";
    }

    @Override
    public boolean isOnlineChecked() throws RemoteException {
        return isOnline.isSelected();
    }
}
