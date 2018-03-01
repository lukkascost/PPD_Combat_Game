package Game;

import Communication.CommonStatic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import static MainPackage.ApplicationRun.colors;
import static MainPackage.ApplicationRun.gameBackEnd;
import static MainPackage.ApplicationRun.j1;

public class GamePanel extends JPanel{

    private JPanel tablePannel = new JPanel();
    private ArrayList<JButton> table = new ArrayList<JButton>();
    private AbstractAction abstractAction;



    public GamePanel(int widthGame,int heightGame) {

        this.setLayout(null);
        this.setBounds(0,0,widthGame,heightGame);
        int widthOfSquare = (int) (widthGame*0.08f);

        this.tablePannel.setLayout(new GridLayout(10,10));
        this.tablePannel.setBounds(widthOfSquare,widthOfSquare,widthOfSquare*10,widthOfSquare*10);
        this.abstractAction = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                onClick(e);
            }
        };

        for (int j=0;j<10;j++) {
            for (int i = 0; i < 10; i++) {
                JButton field = new JButton();
                field.setBackground(colors.get(0));
                field.setBorder(BorderFactory.createLineBorder(Color.lightGray));
                field.setHorizontalAlignment(JTextField.CENTER);
                field.addActionListener(abstractAction);
                this.table.add(field);
                this.tablePannel.add(field);
            }
        }
        this.add(tablePannel);

        this.setCollor(5,3, colors.get(1));
        this.setCollor(5,4, colors.get(1));
        this.setCollor(6,3, colors.get(1));
        this.setCollor(6,4, colors.get(1));

        this.setCollor(5,7, colors.get(1));
        this.setCollor(5,8, colors.get(1));
        this.setCollor(6,7, colors.get(1));
        this.setCollor(6,8, colors.get(1));
    }

    public void sendMoviment() {
        CommonStatic.protocolMsg2Send = gameBackEnd.messageToSend();
        CommonStatic.onDataSend.release();
    }

    public void setCollor(int line,int col, Color color){
        this.table.get((line-1)*10 + (col-1)).setBackground(color);
    }
    public ArrayList<JButton> getTable() {
        return table;
    }

    public void setTable(ArrayList<JButton> table) {
        this.table = table;
    }

    public void updateTable(GameBackEnd game){
        for (int i = 0; i < this.table.size(); i++) {
            int color = game.getColors().get(i);
            int text = game.getPeaces().get(i).intValue();

            this.table.get(i).setBackground(colors.get(color));
            if(color>1) {
                if(j1){
                    if(color == 2 && text == 0){
                        this.table.get(i).setText("--");
                    }else{
                        if(text<11) this.table.get(i).setText(text+ "");
                        if(text==11) this.table.get(i).setText("BO");
                        if(text==12) this.table.get(i).setText("FL");
                    }
                }else{
                    if(color == 3 && text == 0){
                        this.table.get(i).setText("--");
                    }else{
                        if(text<11) this.table.get(i).setText(text+ "");
                        if(text==11) this.table.get(i).setText("BO");
                        if(text==12) this.table.get(i).setText("FL");
                    }
                }
            }else{
                this.table.get(i).setText("");
            }
        }
    }

    public void setCollor(int i, Color collor) {
        this.table.get(i).setBackground(collor);
    }

    private void onClick(ActionEvent e){
        JButton button = (JButton) e.getSource();
        int line = button.getY()/38;
        int col = button.getX()/38;
        System.out.println("Line: "+line+" Col: "+col+ " Valor: "+button.getText());
        //TODO chama jogo
        this.updateTable(gameBackEnd);
        this.sendMoviment();
    }


}
