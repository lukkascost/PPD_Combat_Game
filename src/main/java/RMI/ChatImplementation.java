package RMI;

import Game.IGame;
import MainPackage.ApplicationRun;

import javax.swing.*;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

import static MainPackage.ApplicationRun.*;
import static MainPackage.ApplicationRun.ip;

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
    public int restartMatch(){
            return JOptionPane.showConfirmDialog(null,"Deseja reiniciar?","REINICIAR?", JOptionPane.YES_NO_OPTION);
    }
    public void atualizaGlobal() throws RemoteException, NotBoundException {
        if (j1) {
            gamePanel.globalGame.setInitialValues(ApplicationRun.peaces, ApplicationRun.j1);
            LocateRegistry.getRegistry(ip).rebind(ApplicationRun.player + "-game", gamePanel.globalGame);
        }else {
            gamePanel.globalGame = (IGame) LocateRegistry.getRegistry(ip).lookup(ApplicationRun.enemy + "-game");
            gamePanel.globalGame.setInitialValues(ApplicationRun.peaces, ApplicationRun.j1);
        }
    }
}
