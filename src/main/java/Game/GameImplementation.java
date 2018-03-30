package Game;

import MainPackage.ApplicationRun;

import javax.swing.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import static MainPackage.ApplicationRun.colors;
public class GameImplementation extends UnicastRemoteObject implements IGame {
    private ArrayList<Integer> pieces;
    private ArrayList<Integer> colors;
    private ArrayList<Boolean> show;

    public boolean isUp() {
        return up;
    }

    public boolean isLeft() {
        return left;
    }

    public boolean isRight() {
        return right;
    }

    public boolean isDown() {
        return down;
    }

    private boolean up,left,right, down;


    protected GameImplementation() throws RemoteException {
        super();
    }

    public GameImplementation(ArrayList<JButton> fieldView) throws RemoteException {
        super();
        this.pieces = new ArrayList<>();
        this.colors = new ArrayList<>();
        this.show = new ArrayList<>();
        for (JButton field: fieldView) {
            this.colors.add(ApplicationRun.colors.indexOf(field.getBackground()));
            this.show.add(true);
            if (field.getText().equals("--")) {
                this.pieces.add(-1);
            } else {
                if (field.getText().equals("BO")) {
                    this.pieces.add(11);
                } else {
                    if (field.getText().equals("FL")) {
                        this.pieces.add(12);
                    }else{
                        if(field.getText().isEmpty()) this.pieces.add(0); else
                            this.pieces.add(Integer.parseInt(field.getText()));
                    }
                }
            }
        }
    }

    public void setNewValues( ArrayList<Integer> pieces,
                              ArrayList<Integer> colors, boolean j1) {
        for (int i = 0; i < 100; i++) {
            if (this.colors.get(i) == 3)
                this.show.set(i ,j1);
            if (this.colors.get(i) == 2)
                this.show.set(i , !j1);
        }
    }
    public boolean setInitialValues(String[] pieces, boolean j1) {
        int i = 0;
        for (String st: pieces) {
            if (st.equals("BO")) st = st.replace("BO","11");
            if (st.equals("FL")) st = st.replace("FL","12");
            if(j1){
                this.pieces.set(i+60, Integer.valueOf(st));
                this.colors.set(i+60, 3);
                this.colors.set(i, 2);
            }
            else{
                this.pieces.set(i, Integer.valueOf(st));
                this.colors.set(i, 2);
                this.colors.set(i+60, 3);
            }
            i++;
        }
        return true;
    }

    public boolean movementPieceFirstClick(int lin, int cols, boolean j1) {
        int position = (lin*10) + cols;
        if(j1){
            if(this.colors.get(position) != 3) {
                this.forbiddenMovement();
                return false;
            }
            if(this.pieces.get(position)>10){
                this.forbiddenMovement();
                return false;
            }
            this.setPossibleMovements( lin,  cols,  2);
        }else{
            if(this.colors.get(position) != 2){
                this.forbiddenMovement();
                return false;
            }
            if(this.pieces.get(position)>10){
                this.forbiddenMovement();
                return false ;
            }
            this.setPossibleMovements( lin,  cols,  3);
        }
        return true;
    }

    public boolean movementPieceSecondClick(int line, int col,int vertical,int horizontal, boolean j1) throws RemoteException {

        int position = (line*10) + col;

        if(!(vertical>1 || vertical<-1 || horizontal>1 || horizontal<-1)) {
            if (vertical == -1 && horizontal == 0){
                if (this.up)
                    this.movementPiece(position,10, j1);
                else return false;
            }else {
                if (vertical == 1 && horizontal == 0) {
                    if (this.down)
                        this.movementPiece(position, -10, j1);
                    else return false;
                }else{
                    if (vertical == 0 && horizontal == 1) {
                        if (this.right) this.movementPiece(position, -1, j1);
                        else return false;
                    }else {
                        if (vertical == 0 && horizontal == -1) {
                            if (this.left) this.movementPiece(position, 1, j1);
                            else return false;
                        }else{
                            return false;
                        }
                    }
                }
            }
        }else{ return false;}
        return true;

    }
    //
//    public void setWinMessage() {
//        for (int j = 0; j < 100; j++) {
//            this.pieces.set(j,11);
//            this.colors.set(j,0);
//        }
//    }
//
//    public void setLoseMessage() {
//        for (int j = 0; j < 100; j++) {
//            this.pieces.set(j,11);
//            this.colors.set(j,0);
//        }
//    }
//
//    public void skipPlay() {
//
//    }
//
    private void setPossibleMovements(int lin, int cols, int color){
        int position =  (lin*10) + cols;
        this.up = lin != 0 && (this.colors.get(position - 10) == 0 || this.colors.get(position - 10) == color);
        this.down = lin != 9 && (this.colors.get(position + 10) == 0 || this.colors.get(position + 10) == color);
        this.left = cols != 0 && (this.colors.get(position - 1) == 0 || this.colors.get(position - 1) == color);
        this.right = cols != 9 && (this.colors.get(position + 1) == 0 || this.colors.get(position + 1) == color);
    }
    private void forbiddenMovement(){
        ApplicationRun.chatPanel.writeLog("Você não pode movimentar essa peça!!!");
        GamePanel.positionFirst = new int[]{12,12};
        GamePanel.firstClick = true;
    }
    public void swap(int position1, int position2){
        int aux = this.pieces.get(position1);
        this.pieces.set(position1,this.pieces.get(position2));
        this.pieces.set(position2,aux);

        aux = this.colors.get(position1);
        this.colors.set(position1,this.colors.get(position2));
        this.colors.set(position2,aux);
    }
    //
//    public void attack(int attacker, int to) {
//        this.show.set(attacker, true);
//        this.pieces.set(attacker, this.pieces.get(attacker) * 10 + to);
//        this.pieces.set(attacker, this.pieces.get(attacker) / 10);
//        this.show.set(attacker, false);
//    }
//
//    private void combat(int attacker, int attacked){
//        int value_attacker = this.pieces.get(attacker)/10 ;
//        this.pieces.set(attacker,value_attacker);
//        int value_attacked = this.pieces.get(attacked);
//
//        if(value_attacked == 12){
//            this.setLoseMessage();
//            return;
//        }
//        if(value_attacked == 11 && value_attacker == 3){
//            this.pieces.set(attacked, value_attacker);
//            this.colors.set(attacked, this.colors.get(attacker));
//            this.pieces.set(attacker, 0);
//            this.colors.set(attacker, 0);
//        }
//        else {
//            if (value_attacked == value_attacker) {
//                this.pieces.set(attacked, 0);
//                this.pieces.set(attacker, 0);
//                this.colors.set(attacked, 0);
//                this.colors.set(attacker, 0);
//            } else {
//                if (value_attacked > value_attacker) {
//                    this.show.set(attacked, true);
//                    this.pieces.set(attacker, 0);
//                    this.colors.set(attacker, 0);
//                } else {
//                    this.pieces.set(attacked, value_attacker);
//                    this.colors.set(attacked, this.colors.get(attacker));
//                    this.pieces.set(attacker, 0);
//                    this.colors.set(attacker, 0);
//                }
//            }
//        }
//    }
    private void movementPiece(int position, int increment,boolean j1) {
        if (j1) {
            if(this.colors.get(position)==2){
//                this.attack(position + increment ,flagMovement);
            }else {
                this.swap(position, position + increment);

            }
        }else{
            if(this.colors.get(position)==3){
//                this.attack(position+ increment , flagMovement);
            }else{
                this.swap(position, position + increment);
            }
        }
    }
    public ArrayList<Integer> getPieces() {
        return pieces;
    }

    public ArrayList<Integer> getColors() {
        return colors;
    }
    public ArrayList<Boolean> getShow() {
        return show;
    }
}
