package Game;

import MainPackage.ApplicationRun;

import javax.swing.*;
import java.util.ArrayList;

import static MainPackage.ApplicationRun.j1;

public class GameBackEnd {
    private ArrayList<Integer> peaces;
    private ArrayList<Integer> colors;

    public GameBackEnd(ArrayList<JTextField> fieldView){
        this.peaces = new ArrayList<>();
        this.colors = new ArrayList<>();
        for (JTextField field: fieldView) {
            this.colors.add(ApplicationRun.colors.indexOf(field.getBackground()));
            if (field.getText().equals("--")) {
                this.peaces.add(-1);
            } else {
                if (field.getText().equals("B0")) {
                    this.peaces.add(11);
                } else {
                    if (field.getText().equals("FL")) {
                        this.peaces.add(12);
                    }else{
                        if(field.getText().isEmpty()) this.peaces.add(0); else
                            this.peaces.add(Integer.parseInt(field.getText()));
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
                this.peaces.set(i+60, new Integer(st));
                this.colors.set(i+60, new Integer(3));
                this.colors.set(i,    new Integer(2));

            }
            else{
                this.peaces.set(i,    new Integer(st));
                this.colors.set(i,    new Integer(2));
                this.colors.set(i+60, new Integer(3));
            }
           i++;
        }
    }

    public String messageToSend(){
        String result = "02 ";
        for (int i = 0; i < this.peaces.size(); i++) {
            result+= this.colors.get(i).intValue() + "" ;
        }
        return  result
    }
    public void receivedString(String[] received){
        for (int i = 0; i < received.length-1; i++) {

        }

    }


}
