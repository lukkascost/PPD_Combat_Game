package UI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GamePanel extends JPanel  implements IGamePanel {


    private ArrayList<JTextField> table = new ArrayList<JTextField>();
    private Color[] collors = new Color[]{new Color(33,120,57),new Color(61 ,165 ,184)};
    public GamePanel(int widthGame,int heightGame) {
        this.setLayout(null);
        this.setBounds(0,0,widthGame,heightGame);
//        this.setBorder(BorderFactory.createLineBorder(Color.red));

        int widthOfSquare = (int) (widthGame*0.08f);

        for (int j=0;j<10;j++) {
            for (int i = 0; i < 10; i++) {
                JTextField field = new JTextField();
                field.setEditable(false);
                field.setBackground(this.collors[0]);
                field.setBorder(BorderFactory.createLineBorder(Color.lightGray));
                field.setBounds((int) (widthGame * 0.1f) + (i * widthOfSquare), (int) (widthGame * 0.1f) + (j * widthOfSquare), widthOfSquare, widthOfSquare);
                field.setHorizontalAlignment(JTextField.CENTER);
                this.table.add(field);
                this.add(field);
            }
        }

        this.setCollor(5,3,this.collors[1]);
        this.setCollor(5,4,this.collors[1]);
        this.setCollor(6,3,this.collors[1]);
        this.setCollor(6,4,this.collors[1]);

        this.setCollor(5,7,this.collors[1]);
        this.setCollor(5,8,this.collors[1]);
        this.setCollor(6,7,this.collors[1]);
        this.setCollor(6,8,this.collors[1]);
    }

    public void moveObject() {

    }

    public void sendMoviment() {

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


}
