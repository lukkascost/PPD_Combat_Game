package Game;

import MainPackage.ApplicationRun;

import javax.swing.*;
import java.util.ArrayList;

public class Table {
    @Override
    public String toString() {
        ArrayList<JTextField> table = ApplicationRun.gamePannel.getTable();
        String retorno = "";
        for (int i = 0; i < table.size()-1; i++) {
                retorno += table.get(i).getText()+" ";
        }
        retorno += table.get(table.size()-1).getText();
        return retorno;
    }

    public void setOnes() {
        ArrayList<JTextField> table = ApplicationRun.gamePannel.getTable();
        for (int i = 0; i < table.size(); i++) {
            table.get(i).setText(String.valueOf(i%10));
        }
    }
    public void randomSet(){
        ArrayList<JTextField> table = ApplicationRun.gamePannel.getTable();
        String[] fields = ApplicationRun.peaces;
        for (int i = 0; i < table.size(); i++) {
            table.get(i).setText(String.valueOf(i%10));
        }
    }
}
