package RMI;


import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import static MainPackage.ApplicationRun.*;

public class ChatImplementation extends UnicastRemoteObject implements IChat {

    public ChatImplementation() throws RemoteException {
        super();
    }

    @Override
    public String writeMessage(String message, String player, String saveIn) {
        if(!friendChatContent.containsKey(saveIn)){
            friendChatContent.put(saveIn,"");
            optionsPanel.friendList.addInList(saveIn);
        }
        String chatContent = (String) friendChatContent.get(saveIn);
        chatContent += player+ ": " +message+"\n";
        friendChatContent.replace(saveIn,chatContent);
        chatPanel.updateActualChat();
        return "OK";
    }

    @Override
    public boolean isOnlineChecked() {
        return isOnline.isSelected();
    }


    public String getChatWith(String name) {
        if(friendChatContent.containsKey(name)) {
            return (String) friendChatContent.get(name);
        } else {
            return "";
        }
    }
}
