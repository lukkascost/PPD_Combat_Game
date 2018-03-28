package RMI;

import Chat.ChatText;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ChatImplementation extends UnicastRemoteObject implements IChat {

    private String chatText;

    public ChatImplementation() throws RemoteException {
        super();
    }

    public void setChatText(String chatText) {
        this.chatText = chatText;
    }

    public String getChatText() {
        return chatText;
    }

    public String writeMessage(String message, String player) throws RemoteException {
            this.chatText += (player+": "+message+"\n");
        return this.chatText;
    }
}
