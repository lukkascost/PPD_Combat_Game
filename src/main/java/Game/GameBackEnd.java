package Game;

import MainPackage.ApplicationRun;

import javax.swing.*;
import java.util.ArrayList;

import static MainPackage.ApplicationRun.chatPannel;
import static MainPackage.ApplicationRun.gamePannel;
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
                    if(color == 3 )
                        result += !this.send.get(i) ?"0 ":value+" ";
                    else
                        result += value+" ";
                }else{
                    if(color == 2)
                        result += !this.send.get(i) ?"0 ":value+" ";
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
        System.out.printf("Recebido: "+received[0]+" ");
        for (int i = 0; i < received.length-1; i++) {
            int color = Integer.valueOf(received[i+1].substring(0,1));
            int value = Integer.valueOf(received[i+1].substring(1,received[i+1].length()));
            System.out.printf(received[i+1]+" ");
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
                if(value>12){
                    if(value%10 == 5)
                        combat(i,i-10);
                }

            }else{
                this.pieces.set(i, new Integer(0));
            }
        }
        System.out.println();
    }

    public void movimentPieceFirstClick(int lin, int cols){
        gamePannel.updateTable(this);
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
            this.up = lin==0 ?false: (this.colors.get(position-10)!=0 && this.colors.get(position-10)!=2 ? false:true);
            this.down = lin==9 ?false: (this.colors.get(position+10)!=0 && this.colors.get(position+10)!=2 ? false:true);
            this.left = cols==0 ? false: (this.colors.get(position-1)!=0 && this.colors.get(position-1)!=2 ? false :true);
            this.right = cols==9 ? false: (this.colors.get(position+1)!=0 && this.colors.get(position+1)!=2 ? false :true);
        }else{
            if(this.colors.get(position) != 2){
                this.forbiddenMovement();
                return;
            }
            if(this.pieces.get(position)>10){
                this.forbiddenMovement();
                return;
            }
            this.up = lin==0 ?false: (this.colors.get(position-10)!=0 && this.colors.get(position-10)!=3 ? false:true);
            this.down = lin==9 ?false: (this.colors.get(position+10)!=0 && this.colors.get(position+10)!=3 ? false:true);
            this.left = cols==0 ? false: (this.colors.get(position-1)!=0 && this.colors.get(position-1)!=3 ? false :true);
            this.right = cols==9 ? false: (this.colors.get(position+1)!=0 && this.colors.get(position+1)!=3 ? false :true);
        }
        if(this.up) {
            gamePannel.getTable().get(position-10).setBackground(ApplicationRun.colors.get(4)); gamePannel.getTable().get(position-10).setText("^");
        }
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
                if (this.up) {
                    if (j1) {
                        if(this.colors.get(position)==2){
                            this.attack(position+10,position ,5);
                        }else this.swap(position, position + 10);
                    }else{
                        if(this.colors.get(position)==3){

                        }
                    }
                }
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

    public void attack(int attacker , int attacked , int to){
        if(!this.send.get(attacker)) {
            this.send.set(attacker, true);
            this.pieces.set(attacker, this.pieces.get(attacker) * 10 + to);
        }
    }

    public void combat(int attacker, int attacked){
        int value_attacker = this.pieces.get(attacker)/10;
        int value_attacked = this.pieces.get(attacked);

        if(value_attacked == value_attacker){
            this.pieces.set(attacked,0);
            this.pieces.set(attacker,0);

            this.colors.set(attacked,0);
            this.colors.set(attacker,0);

        }else{
            if(value_attacked>value_attacker){
                this.send.set(attacked,true);
                this.pieces.set(attacker,0);
                this.colors.set(attacker,0);
            }
            else{
                this.send.set(attacker,true);
                this.pieces.set(attacker,value_attacker);
                this.pieces.set(attacked,0);
                this.colors.set(attacked,0);
            }
        }
        gamePannel.sendMoviment();
    }

    public void forbiddenMovement(){
        chatPannel.writeLog("Você não pode movimentar essa peça!!!");
        gamePannel.positionFirst = new int[]{12,12};
        gamePannel.firstClick = true;
    }
}
