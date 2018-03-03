package Game;

import MainPackage.ApplicationRun;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

import static MainPackage.ApplicationRun.chatPannel;
import static MainPackage.ApplicationRun.gamePannel;
import static MainPackage.ApplicationRun.j1;

public class GameBackEnd {
    private ArrayList<Integer> pieces;
    private ArrayList<Integer> colors;
    private boolean up,left,right, down;


    public GameBackEnd(ArrayList<JButton> fieldView){
        this.pieces = new ArrayList<>();
        this.colors = new ArrayList<>();
        for (JButton field: fieldView) {
            this.colors.add(ApplicationRun.colors.indexOf(field.getBackground()));
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
                this.colors.set(i+60, new Integer(3));
                this.colors.set(i,    new Integer(2));
            }
            else{
                this.pieces.set(i,    new Integer(st));
                this.colors.set(i,    new Integer(2));
                this.colors.set(i+60, new Integer(3));
            }
            i++;
        }
    }

    public String messageToSend(){
        String result = "02 ";
        for (int i = 0; i < this.pieces.size(); i++) {
            int color =  this.colors.get(i).intValue();
            int value = this.pieces.get(i).intValue();
            result+= color+"";
            if(color>1){
                if(j1){
                    if(color == 3)
                        result += "0 ";
                    else
                        result += value+" ";
                }else{
                    if(color == 2)
                        result += "0 ";
                    else
                        result += value+" ";
                }
            }else{
                result+= "0 ";
            }

        }
        System.out.println("enviando: "+result);
        return  result;
    }

    public void receivedString(String[] received){
        System.out.println("Recebido: "+received.length);
        for (int i = 0; i < received.length-1; i++) {
            int color = Integer.valueOf(received[i+1].substring(0,1));
            int value = Integer.valueOf(received[i+1].substring(1,received[i+1].length()));
            this.colors.set(i, color);
            if(color>1){
                if(j1){
                    if(color == 3);
                    else
                        this.pieces.set(i,value);
                }else{
                    if(color == 2);
                    else
                        this.pieces.set(i,value);
                }
            }else{
                this.pieces.set(i, new Integer(0));
            }
        }
    }

    public void movimentPieceFirstClick(int lin, int cols){
        gamePannel.updateTable(this);
        int position = (lin*10) + cols;
        if(j1){
            if(this.colors.get(position) != 3) {
                chatPannel.writeLog("Você não pode movimentar essa peça!!!");
                gamePannel.positionFirst = new int[]{12,12};
                gamePannel.firstClick = true;
                return;
            }
            this.up = lin==0 ?false: (this.colors.get(position-10)!=0 && this.colors.get(position-10)!=2 ? false:true);
            this.down = lin==9 ?false: (this.colors.get(position+10)!=0 && this.colors.get(position+10)!=2 ? false:true);
            this.left = cols==0 ? false: (this.colors.get(position-1)!=0 && this.colors.get(position-1)!=2 ? false :true);
            this.right = cols==9 ? false: (this.colors.get(position+1)!=0 && this.colors.get(position+1)!=2 ? false :true);
        }else{
            if(this.colors.get(position) != 2){
                chatPannel.writeLog("Você não pode movimentar essa peça!!!");
                gamePannel.positionFirst = new int[]{12,12};
                gamePannel.firstClick = true;
                return;
            }
            this.up = lin==0 ?false: (this.colors.get(position-10)!=0 && this.colors.get(position-10)!=3 ? false:true);
            this.down = lin==9 ?false: (this.colors.get(position+10)!=0 && this.colors.get(position+10)!=3 ? false:true);
            this.left = cols==0 ? false: (this.colors.get(position-1)!=0 && this.colors.get(position-1)!=3 ? false :true);
            this.right = cols==9 ? false: (this.colors.get(position+1)!=0 && this.colors.get(position+1)!=3 ? false :true);
        }
        if(this.up) {gamePannel.getTable().get(position-10).setBackground(ApplicationRun.colors.get(4)); gamePannel.getTable().get(position-10).setText("^");}
        if(this.down) {gamePannel.getTable().get(position+10).setBackground(ApplicationRun.colors.get(4));gamePannel.getTable().get(position+10).setText("\\/");}
        if(this.left) {gamePannel.getTable().get(position-1).setBackground(ApplicationRun.colors.get(4));gamePannel.getTable().get(position-1).setText("<");}
        if(this.right){ gamePannel.getTable().get(position+1).setBackground(ApplicationRun.colors.get(4));gamePannel.getTable().get(position+1).setText(">");}

        gamePannel.firstClick = false;
        gamePannel.positionFirst = new int[]{lin,cols};
    }
    public ArrayList<Integer> getPieces() {
        return pieces;
    }


    public ArrayList<Integer> getColors() {
        return colors;
    }

    public void movimentPieceSecondClick(int line, int col) {
        int vertical = line - gamePannel.positionFirst[0];
        int horizontal = col - gamePannel.positionFirst[1];
        int position = (line*10) + col;

        gamePannel.positionFirst = new int[]{12,12};
        gamePannel.firstClick = true;
        System.out.println(vertical + " "+horizontal);
        if(j1) {
            if (this.colors.get(position) == 3) {
                this.movimentPieceFirstClick(line, col);
                return;
            }
        }else {
            if (this.colors.get(position) == 2) {
                this.movimentPieceFirstClick(line, col);
                return;
            }
        }
        if(vertical>1 || vertical<-1 || horizontal>1 || horizontal<-1);
        else {
            if (vertical == -1 && horizontal == 0){
                if (this.up) this.swap(position, position + 10);
            }
            if (vertical == 1 && horizontal == 0) {
                if (this.down) this.swap(position, position - 10);
            }
            if (vertical == 0 && horizontal == 1){
                if (this.right) this.swap(position,position-1);
            }
            if (vertical == 0 && horizontal == -1){
                if (this.left) this.swap(position,position+1);
            }
        }
        gamePannel.updateTable(this);
    }


    public void swap(int position1, int position2){
        int aux = this.pieces.get(position1);
        this.pieces.set(position1,this.pieces.get(position2));
        this.pieces.set(position2,aux);

        aux = this.colors.get(position1);
        this.colors.set(position1,this.colors.get(position2));
        this.colors.set(position2,aux);
    }
}
