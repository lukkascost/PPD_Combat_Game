package Game;

import javax.swing.*;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface IGame  extends Remote {
     boolean setInitialValues(String[] pieces, boolean j1) throws RemoteException;
     boolean movementPieceFirstClick(int lin, int cols, boolean j1) throws RemoteException;
     boolean movementPieceSecondClick(int line, int col, int vertical,int horizontal, boolean j1) throws RemoteException;
     void cleanGame()throws RemoteException;
     void swap(int position1, int position2) throws RemoteException;
     boolean isUp() throws RemoteException;
     boolean isLeft() throws RemoteException;
     boolean isRight() throws RemoteException;
     boolean isDown() throws RemoteException;
     ArrayList<Integer> getPieces() throws RemoteException;
     ArrayList<Integer> getColors() throws RemoteException;

}
