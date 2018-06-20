package RMI;

import javax.swing.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import static MainPackage.ApplicationRun.*;

public class ChatImplementation extends UnicastRemoteObject implements IChat {

    public ChatImplementation() throws RemoteException {
        super();
    }

    @Override
    public String writeMessage(String message, String player, String saveIn) throws RemoteException {
        if(!friendChatContent.containsKey(saveIn)){
            friendChatContent.put(saveIn,"");
            optionsPanel.addInList(saveIn);
        }
        String chatContent = (String) friendChatContent.get(saveIn);
        chatContent += player+ ": " +message+"\n";
        friendChatContent.replace(saveIn,chatContent);
        chatPanel.updateActualChat();
        return "OK";
    }

    @Override
    public boolean isOnlineChecked() throws RemoteException {
        return isOnline.isSelected();
    }


    public String getChatWith(String name) throws RemoteException {
        if(friendChatContent.containsKey(name)) {
            return (String) friendChatContent.get(name);
        } else {
            return "";
        }
    }
}
