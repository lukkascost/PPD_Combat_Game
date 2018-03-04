package Game;

import Communication.CommonStatic;

import javax.swing.*;
import javax.swing.plaf.metal.MetalButtonUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import static MainPackage.ApplicationRun.colors;
import static MainPackage.ApplicationRun.gameBackEnd;
import static MainPackage.ApplicationRun.j1;

public class GamePanel extends JPanel{

    private ArrayList<JButton> table = new ArrayList<>();
    public static boolean firstClick = true;
    public static int positionFirst[] = new int[]{12,12};


    public GamePanel(int widthGame,int heightGame) {

        this.setLayout(null);
        this.setBounds(0,0,widthGame,heightGame);
        int widthOfSquare = (int) (widthGame*0.08f);


        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new GridLayout(10,10));
        tablePanel.setBounds(widthOfSquare, widthOfSquare,widthOfSquare*10,widthOfSquare*10);
        AbstractAction abstractAction = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                onClick(e);
            }
        };

        for (int j=0;j<10;j++) {
            for (int i = 0; i < 10; i++) {
                JButton field = new JButton();
                field.setUI(new MetalButtonUI());
                field.setBackground(colors.get(0));
                field.setBorder(BorderFactory.createLineBorder(Color.lightGray));
                field.setHorizontalAlignment(JTextField.CENTER);
                field.addActionListener(abstractAction);
                this.table.add(field);
                tablePanel.add(field);
            }
        }
        this.add(tablePanel);

        this.setColor(5,3, colors.get(1));
        this.setColor(5,4, colors.get(1));
        this.setColor(6,3, colors.get(1));
        this.setColor(6,4, colors.get(1));

        this.setColor(5,7, colors.get(1));
        this.setColor(5,8, colors.get(1));
        this.setColor(6,7, colors.get(1));
        this.setColor(6,8, colors.get(1));
    }

    public void sendMovement() {
        CommonStatic.protocolMsg2Send = gameBackEnd.messageToSend();
        CommonStatic.onDataSend.release();
    }

    public void setColor(int line, int col, Color color){
        this.table.get((line-1)*10 + (col-1)).setBackground(color);
    }
    public ArrayList<JButton> getTable() {
        return table;
    }

    public void updateTable(GameBackEnd game){
        for (int i = 0; i < this.table.size(); i++) {
            int color = game.getColors().get(i);
            int text = game.getPieces().get(i).intValue();

            this.table.get(i).setBackground(colors.get(color));

            if(color>1) {
                if(j1){
                    if(color == 2 && text == 0){
                        this.table.get(i).setText("--");
                    }else{
                        if(text<11) this.table.get(i).setText(text+ "");
                        if(text==11) this.table.get(i).setText("BO");
                        if(text==12) this.table.get(i).setText("FL");
                        if(text>12) this.table.get(i).setText(text+ "");
                    }
                }else{
                    if(color == 3 && text == 0){
                        this.table.get(i).setText("--");
                    }else{
                        if(text<11) this.table.get(i).setText(text+ "");
                        if(text==11) this.table.get(i).setText("BO");
                        if(text==12) this.table.get(i).setText("FL");
                        if(text>12) this.table.get(i).setText(text+ "");

                    }
                }
            }else{
                this.table.get(i).setText("");
            }
        }
    }

    private void onClick(ActionEvent e){
        JButton button = (JButton) e.getSource();
        int line = button.getY()/38;
        int col = button.getX()/38;

        if(firstClick) {
            gameBackEnd.movementPieceFirstClick(line, col);
            //TODO chama jogo
        }else{
            gameBackEnd.movementPieceSecondClick(line,col);
        }


    }


}
