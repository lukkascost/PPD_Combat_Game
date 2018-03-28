package Game;

import MainPackage.ApplicationRun;

import javax.swing.*;
import javax.swing.plaf.metal.MetalButtonUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import static MainPackage.ApplicationRun.*;

public class GamePanel extends JPanel{

    private ArrayList<JButton> table = new ArrayList<>();
    public static boolean firstClick = true;
    public static int positionFirst[] = new int[]{12,12};
    public JButton surrender = new JButton();
    public JButton restart = new JButton();

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

        this.surrender.setText("Render-se");
        this.surrender.setBounds(10,500,100,30);
        this.surrender.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO acao
            }
        });
        this.surrender.setEnabled(false);

        this.restart.setText("Reiniciar Partida");
        this.restart.setBounds(200,500,100,30);
        this.restart.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
              //TODO acao
            }
        });
        this.restart.setEnabled(false);

        this.add(surrender);
        this.add(restart);

        this.setColor(5,3, colors.get(1));
        this.setColor(5,4, colors.get(1));
        this.setColor(6,3, colors.get(1));
        this.setColor(6,4, colors.get(1));

        this.setColor(5,7, colors.get(1));
        this.setColor(5,8, colors.get(1));
        this.setColor(6,7, colors.get(1));
        this.setColor(6,8, colors.get(1));
    }


    public void setColor(int line, int col, Color color){
        this.table.get((line-1)*10 + (col-1)).setBackground(color);
    }
    public ArrayList<JButton> getTable() {
        return table;
    }

    public void updateTable(){

    }

    public void winMessage(){

    }

    private void onClick(ActionEvent e){

    }


    public void loseMessage() {

    }
}
