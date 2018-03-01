package Game;

import Communication.CommonStatic;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static MainPackage.ApplicationRun.colors;

public class GamePanel extends JPanel{


    private ArrayList<JTextField> table = new ArrayList<JTextField>();
    public GamePanel(int widthGame,int heightGame) {
        this.setLayout(null);
        this.setBounds(0,0,widthGame,heightGame);

        int widthOfSquare = (int) (widthGame*0.08f);

        for (int j=0;j<10;j++) {
            for (int i = 0; i < 10; i++) {
                JTextField field = new JTextField();
                field.setEditable(false);
                field.setBackground(colors.get(0));
                field.setBorder(BorderFactory.createLineBorder(Color.lightGray));
                field.setBounds((int) (widthGame * 0.1f) + (i * widthOfSquare), (int) (widthGame * 0.1f) + (j * widthOfSquare), widthOfSquare, widthOfSquare);
                field.setHorizontalAlignment(JTextField.CENTER);
                this.table.add(field);
                this.add(field);
            }
        }

        this.setCollor(5,3, colors.get(1));
        this.setCollor(5,4, colors.get(1));
        this.setCollor(6,3, colors.get(1));
        this.setCollor(6,4, colors.get(1));

        this.setCollor(5,7, colors.get(1));
        this.setCollor(5,8, colors.get(1));
        this.setCollor(6,7, colors.get(1));
        this.setCollor(6,8, colors.get(1));
    }

    public void moveObject(String message) {
        String[] splited = message.split(" ");
        for (int i = 0; i < this.table.size(); i++) {
                this.table.get(i).setBackground(colors.get(Integer.parseInt(splited[i + 1].substring(0, 1))));
                this.table.get(i).setText(splited[i + 1].substring(1, splited[i + 1].length()));
        }
    }

    public void sendMoviment() {
        CommonStatic.protocolMsg2Send = table.toString();
        System.out.println(CommonStatic.protocolMsg2Send);
        CommonStatic.onDataSend.release();
    }

    public void setCollor(int line,int col, Color color){
        this.table.get((line-1)*10 + (col-1)).setBackground(color);
    }
    public ArrayList<JTextField> getTable() {
        return table;
    }

    public void setTable(ArrayList<JTextField> table) {
        this.table = table;
    }


    public void setCollor(int i, Color collor) {
        this.table.get(i).setBackground(collor);
    }
}
