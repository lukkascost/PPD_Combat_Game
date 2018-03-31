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
        for (JButton field: fieldView) {
            this.colors.add(ApplicationRun.colors.indexOf(field.getBackground()));
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
        for (int j = 40; j < 60; j++) {
            this.pieces.set(j, 0);
            this.colors.set(j, 0);
        }
        this.colors.set(42, 1);
        this.colors.set(43, 1);
        this.colors.set(52, 1);
        this.colors.set(53, 1);

        this.colors.set(46, 1);
        this.colors.set(47, 1);
        this.colors.set(56, 1);
        this.colors.set(57, 1);
        return true;
    }

    public boolean movementPieceFirstClick(int lin, int cols, boolean j1) {
        int position = (lin*10) + cols;
        if(j1){
            if(this.colors.get(position) != 3 && this.colors.get(position)!= 6) {
                this.forbiddenMovement();
                return false;
            }
            if(this.pieces.get(position)>10){
                this.forbiddenMovement();
                return false;
            }
            this.setPossibleMovements( lin,  cols,  2);
        }else{
            if(this.colors.get(position) != 2 && this.colors.get(position)!= 5){
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

    public String movementPieceSecondClick(int line, int col,int vertical,int horizontal, boolean j1) throws RemoteException {

        int position = (line*10) + col;

        if(!(vertical>1 || vertical<-1 || horizontal>1 || horizontal<-1)) {
            if (vertical == -1 && horizontal == 0){
                if (this.up){
                    if (this.movementPiece(position,10, j1).equals("Lose") ) return "Lose";}
                else return "ERROR";
            }else {
                if (vertical == 1 && horizontal == 0) {
                    if (this.down){
                        if (this.movementPiece(position, -10, j1).equals("Lose") ) return "Lose";}
                    else return "ERROR";
                }else{
                    if (vertical == 0 && horizontal == 1) {
                        if (this.right){
                            if (this.movementPiece(position, -1, j1).equals("Lose") ) return "Lose";}
                        else return "ERROR";
                    }else {
                        if (vertical == 0 && horizontal == -1) {
                            if (this.left){
                                if (this.movementPiece(position, 1, j1).equals("Lose") ) return "Lose";}
                            else return "ERROR";
                        }else{
                            return "ERROR";
                        }
                    }
                }
            }
        }else{ return "ERROR";}
        return "OK";

    }

    public void cleanGame() {
        for (int j = 0; j < 100; j++) {
            this.pieces.set(j,11);
            this.colors.set(j,0);
        }
    }


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
    public String swap(int position1, int position2){
        int aux = this.pieces.get(position1);
        this.pieces.set(position1,this.pieces.get(position2));
        this.pieces.set(position2,aux);

        aux = this.colors.get(position1);
        this.colors.set(position1,this.colors.get(position2));
        this.colors.set(position2,aux);
        return "";
    }

    private String combat(int attacker, int attacked){
        int value_attacker = this.pieces.get(attacker) ;
        int value_attacked = this.pieces.get(attacked);

        if(value_attacked == 12){
            return "Lose";
        }
        if(value_attacked == 11 && value_attacker == 3){
            Integer color = this.colors.get(attacker);
            if(color == 2 || color == 3) color += 3;
            this.pieces.set(attacked, value_attacker);
            this.colors.set(attacked, color );
            this.pieces.set(attacker, 0);
            this.colors.set(attacker, 0);
        }
        else {
            if (value_attacked == value_attacker) {
                this.pieces.set(attacked, 0);
                this.pieces.set(attacker, 0);
                this.colors.set(attacked, 0);
                this.colors.set(attacker, 0);
            } else {
                if (value_attacked > value_attacker) {
                    Integer color = this.colors.get(attacked);
                    if(color == 2 || color == 3) color += 3;
                    this.colors.set(attacked, color);
                    this.pieces.set(attacker, 0);
                    this.colors.set(attacker, 0);
                } else {
                    Integer color = this.colors.get(attacker);
                    if(color == 2 || color == 3) color += 3;
                    this.pieces.set(attacked, value_attacker);
                    this.colors.set(attacked, color);
                    this.pieces.set(attacker, 0);
                    this.colors.set(attacker, 0);
                }
            }
        }
        return "";
    }
    private String movementPiece(int position, int increment,boolean j1) {
        String result = "";
        if (j1) {
            if(this.colors.get(position)==2){
                result =  this.combat(position + increment , position);
            }else {
                result = this.swap(position, position + increment);

            }
        }else{
            if(this.colors.get(position)==3){
                result = this.combat(position + increment , position);
            }else{
                result = this.swap(position, position + increment);
            }
        }
        return  result;
    }
    public ArrayList<Integer> getPieces() {
        return pieces;
    }

    public ArrayList<Integer> getColors() {
        return colors;
    }
}
