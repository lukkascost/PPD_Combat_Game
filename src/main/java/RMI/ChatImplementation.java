package RMI;

import MainPackage.ApplicationRun;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import static MainPackage.ApplicationRun.gamePanel;
import static MainPackage.ApplicationRun.yourTurn;

public class ChatImplementation extends UnicastRemoteObject implements IChat {

    public ChatImplementation() throws RemoteException {
        super();
    }
    public String writeMessage(String message, String player) {
        ApplicationRun.chatPanel.getChatTextLog().append(player+": "+message);
        return player+": "+message;
    }

    public String writeLog(String message, String player) throws RemoteException {
        ApplicationRun.chatPanel.writeLog(player+": "+message);
        gamePanel.updateTable();
        return player+": "+message;
    }
    public void skipTurnChat() throws RemoteException {
        ApplicationRun.yourTurn = !ApplicationRun.yourTurn;
        if(yourTurn){
            ApplicationRun.chatPanel.writeLog("Faça Sua Jogada!");
        }
        else{
            ApplicationRun.chatPanel.writeLog("Aguarde a Jogada do Oponente!");
        }
        gamePanel.updateTable();

    }
}
