package Game;

import Communication.CommonStatic;
import MainPackage.ApplicationRun;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static MainPackage.ApplicationRun.chatPanel;
import static MainPackage.ApplicationRun.gamePanel;
import static MainPackage.ApplicationRun.j1;

public class GameBackEnd {
    private ArrayList<Integer> pieces;
    private ArrayList<Integer> colors;
    private ArrayList<Boolean> send;
    private boolean up,left,right, down;

    public GameBackEnd(ArrayList<JButton> fieldView){
        this.pieces = new ArrayList<>();
        this.colors = new ArrayList<>();
        this.send = new ArrayList<>();
        for (JButton field: fieldView) {
            this.colors.add(ApplicationRun.colors.indexOf(field.getBackground()));
            this.send.add(false);
            if (field.getText().equals("--")) {
                this.pieces.add(-1);
            } else {
                if (field.getText().equals("B0")) {
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

    public void setInitialValues(String[] pieces){
        int i = 0;
        for (String st: pieces) {
            if (st.equals("BO")) st = st.replace("BO","11");
            if (st.equals("FL")) st = st.replace("FL","12");
            if(j1){
                this.pieces.set(i+60, new Integer(st));
                this.colors.set(i+60, 3);
                this.colors.set(i, 2);
            }
            else{
                this.pieces.set(i,    new Integer(st));
                this.colors.set(i, 2);
                this.colors.set(i+60, 3);
            }
            i++;
        }
    }

    public String messageToSend(){
        StringBuilder result = new StringBuilder("02 ");
        for (int i = 0; i < this.pieces.size(); i++) {
            int color = this.colors.get(i);
            int value = this.pieces.get(i);
            result.append(color);
            if(color>1){
                if(j1){
                    if(color == 3 ) {
                        result.append(!this.send.get(i) ? "0 " : value + " ");
                    }
                    else
                        result.append(value).append(" ");
                }else{
                    if(color == 2) {
                        result.append(!this.send.get(i) ? "0 " : value + " ");
                    }
                    else
                        result.append(value).append(" ");
                }
            }else{
                result.append("0 ");
            }

        }
        System.out.println("Sending: "+result);
        return result.toString();
    }

    public void receivedString(String[] received){
        System.out.print("\nReceived: " + received[0] + " ");
        int envio = -1;
        int increment = 0;
        for (int i = 0; i < received.length-1; i++) {
            int color = Integer.valueOf(received[i+1].substring(0,1));
            int value = Integer.valueOf(received[i+1].substring(1,received[i+1].length()));
            System.out.print(received[i+1]+" ");
            this.colors.set(i,color);
            if(color>1){
                if(j1){
                    if(color != 3) {
                        this.pieces.set(i, value);
                    }
                    if(color == 3) {
                        if (value > 0){
                            this.send.set(i, true);
                            this.pieces.set(i, value);
                        }
                    }
                }else{
                    if(color != 2)
                        this.pieces.set(i, value);
                    if(color == 2) {
                        if (value > 0){
                            this.send.set(i, true);
                            this.pieces.set(i, value);
                        }
                    }
                }
                if(value>12){
                    envio = i;
                    switch (value%10){
                        case 5:
                            increment = -10;
                            break;
                        case 6:
                            increment = 10;
                            break;
                        case 7:
                            increment = 1;
                            break;
                        case 8:
                            increment = -1;
                            break;
                        default:break;
                    }
                }

            }else{
                this.pieces.set(i, 0);
            }
        }
        if(envio>=0){
            combat(envio,envio+increment);
        }
    }

    public void movementPieceFirstClick(int lin, int cols){
        gamePanel.updateTable(this);
        int position = (lin*10) + cols;
        if(j1){
            if(this.colors.get(position) != 3) {
                this.forbiddenMovement();
                return;
            }
            if(this.pieces.get(position)>10){
                this.forbiddenMovement();
                return;
            }
            this.setPossibleMovements( lin,  cols,  2);
        }else{
            if(this.colors.get(position) != 2){
                this.forbiddenMovement();
                return;
            }
            if(this.pieces.get(position)>10){
                this.forbiddenMovement();
                return;
            }
            this.setPossibleMovements( lin,  cols,  3);
        }

        if(this.up) {
            gamePanel.getTable().get(position-10).setBackground(ApplicationRun.colors.get(4));
            gamePanel.getTable().get(position-10).setText("^");
        }
        if(this.down) {
            gamePanel.getTable().get(position+10).setBackground(ApplicationRun.colors.get(4));
            gamePanel.getTable().get(position+10).setText("\\/");}
        if(this.left) {
            gamePanel.getTable().get(position-1).setBackground(ApplicationRun.colors.get(4));
            gamePanel.getTable().get(position-1).setText("<");}
        if(this.right){
            gamePanel.getTable().get(position+1).setBackground(ApplicationRun.colors.get(4));
            gamePanel.getTable().get(position+1).setText(">");}

        GamePanel.firstClick = false;
        GamePanel.positionFirst = new int[]{lin,cols};
    }

    public ArrayList<Integer> getPieces() {
        return pieces;
    }

    public ArrayList<Integer> getColors() {
        return colors;
    }

    private void movementPiece(int position, int increment, int flagMovement){
        if (j1) {
            if(this.colors.get(position)==2){
                this.attack(position + increment ,flagMovement);
            }else {
                this.swap(position, position + increment);
            }
        }else{
            if(this.colors.get(position)==3){
                this.attack(position+ increment , flagMovement);
            }else{
                this.swap(position, position + increment);}
        }
    }

    public void movementPieceSecondClick(int line, int col) {
        int vertical = line - GamePanel.positionFirst[0];
        int horizontal = col - GamePanel.positionFirst[1];
        int position = (line*10) + col;

        GamePanel.positionFirst = new int[]{12,12};
        GamePanel.firstClick = true;
        if(j1) {
            if (this.colors.get(position) == 3) {
                this.movementPieceFirstClick(line, col);
                return;
            }
        }else {
            if (this.colors.get(position) == 2) {
                this.movementPieceFirstClick(line, col);
                return;
            }
        }
        if(!(vertical>1 || vertical<-1 || horizontal>1 || horizontal<-1)) {
            if (vertical == -1 && horizontal == 0){
                if (this.up)
                    this.movementPiece(position,10,5);
                else return;
            }else {
                if (vertical == 1 && horizontal == 0) {
                    if (this.down)
                        this.movementPiece(position, -10, 6);
                    else return;
                }else{
                    if (vertical == 0 && horizontal == 1) {
                        if (this.right) this.movementPiece(position, -1, 7);
                        else return;
                    }else {
                        if (vertical == 0 && horizontal == -1) {
                            if (this.left) this.movementPiece(position, 1, 8);
                            else return;
                        }else{
                            return;
                        }
                    }
                }
            }
        }else{
            return;}
        gamePanel.updateTable(this);
        this.skipPlay();
        CommonStatic.protocolMsg2Send = "05 ";
        CommonStatic.onDataSend.release();
    }

    private void swap(int position1, int position2){
        int aux = this.pieces.get(position1);
        this.pieces.set(position1,this.pieces.get(position2));
        this.pieces.set(position2,aux);

        aux = this.colors.get(position1);
        this.colors.set(position1,this.colors.get(position2));
        this.colors.set(position2,aux);

        gamePanel.sendMovement();
    }

    private void attack(int attacker, int to){
        this.send.set(attacker, true);
        this.pieces.set(attacker, this.pieces.get(attacker) * 10 + to);
        gamePanel.sendMovement();
        this.pieces.set(attacker,this.pieces.get(attacker)/10);
        this.send.set(attacker,false);
    }

    private void combat(int attacker, int attacked){
        int value_attacker = this.pieces.get(attacker)/10 ;
        this.pieces.set(attacker,value_attacker);
        int value_attacked = this.pieces.get(attacked);

        if(value_attacked == 12){
            CommonStatic.protocolMsg2Send = "03 ";
            CommonStatic.onDataSend.release();
            this.setLoseMessage(0);
            return;
        }
        if(value_attacked == 11 && value_attacker == 3){
            this.pieces.set(attacked, value_attacker);
            this.colors.set(attacked, this.colors.get(attacker));
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
                    this.send.set(attacked, true);
                    this.pieces.set(attacker, 0);
                    this.colors.set(attacker, 0);
                } else {
                    this.pieces.set(attacked, value_attacker);
                    this.colors.set(attacked, this.colors.get(attacker));
                    this.pieces.set(attacker, 0);
                    this.colors.set(attacker, 0);
                }
            }
        }
        gamePanel.sendMovement();
    }

    private void forbiddenMovement(){
        ApplicationRun.chatPanel.writeLog("Você não pode movimentar essa peça!!!");
        GamePanel.positionFirst = new int[]{12,12};
        GamePanel.firstClick = true;
    }

    private void setPossibleMovements(int lin, int cols, int color){
        int position =  (lin*10) + cols;
        this.up = lin != 0 && (this.colors.get(position - 10) == 0 || this.colors.get(position - 10) == color);
        this.down = lin != 9 && (this.colors.get(position + 10) == 0 || this.colors.get(position + 10) == color);
        this.left = cols != 0 && (this.colors.get(position - 1) == 0 || this.colors.get(position - 1) == color);
        this.right = cols != 9 && (this.colors.get(position + 1) == 0 || this.colors.get(position + 1) == color);
    }

    public void setWinMessage(int i) {
        for (int j = 0; j < 100; j++) {
            this.pieces.set(j,11);
            this.colors.set(j,0);
        }
        chatPanel.writeLog("Você Ganhou !!");
        gamePanel.surrender.setEnabled(false);
        ApplicationRun.run = false;
    }

    public void setLoseMessage(int i) {
        for (int j = 0; j < 100; j++) {
            this.pieces.set(j,11);
            this.colors.set(j,0);
        }
        chatPanel.writeLog("Você Perdeu !!");
        gamePanel.surrender.setEnabled(false);
        ApplicationRun.run = false;
    }
    public synchronized void skipPlay(){
        try {
            Thread.currentThread().sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ApplicationRun.yourTurn = !ApplicationRun.yourTurn;
        if(ApplicationRun.yourTurn){
            chatPanel.writeLog("Sua vez!");
        }else{
            chatPanel.writeLog("Aguarde sua Jogada...");
        }
    }
}
