package Game;

import MainPackage.ApplicationRun;

import javax.swing.*;
import javax.swing.plaf.metal.MetalButtonUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.ArrayList;

import static MainPackage.ApplicationRun.*;

public class GamePanel extends JPanel{

    private ArrayList<JButton> table = new ArrayList<>();
    public static boolean firstClick = true;
    public static int positionFirst[] = new int[]{12,12};
    public JButton surrender = new JButton();
    public JButton restart = new JButton();
    public IGame globalGame;

    public GamePanel(int widthGame,int heightGame) throws RemoteException, NotBoundException {

        this.setLayout(null);
        this.setBounds(0,0,widthGame,heightGame);
        int widthOfSquare = (int) (widthGame*0.08f);

        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new GridLayout(10,10));
        tablePanel.setBounds(widthOfSquare, widthOfSquare,widthOfSquare*10,widthOfSquare*10);
        AbstractAction abstractAction = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                try {
                    onClick(e);
                } catch (RemoteException e1) {
                    e1.printStackTrace();
                }
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
                try {
                    globalGame.cleanGame();
                    chatPanel.iLose();

                } catch (RemoteException e1) {
                    e1.printStackTrace();
                }
            }
        });
        this.surrender.setEnabled(false);

        this.restart.setText("Reiniciar Partida");
        this.restart.setBounds(200,500,100,30);
        this.restart.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO acao
                try {
                    chatPanel.restart();
                } catch (RemoteException e1) {
                    e1.printStackTrace();
                } catch (NotBoundException e1) {
                    e1.printStackTrace();
                }
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

        globalGame = new GameImplementation(this.table);
        if (j1) {
            globalGame.setInitialValues(ApplicationRun.peaces, ApplicationRun.j1);
            LocateRegistry.getRegistry(ip).rebind(ApplicationRun.player + "-game", globalGame);
        }else {
            globalGame = (IGame) LocateRegistry.getRegistry(ip).lookup(ApplicationRun.enemy + "-game");
            globalGame.setInitialValues(ApplicationRun.peaces, ApplicationRun.j1);

        }
        this.updateTable();
    }


    public void setColor(int line, int col, Color color){
        this.table.get((line-1)*10 + (col-1)).setBackground(color);
    }
    public ArrayList<JButton> getTable() {
        return table;
    }

    public void updateTable() throws RemoteException {
        for (int i = 0; i < 100; i++) {
            int color = globalGame.getColors().get(i);
            int text = globalGame.getPieces().get(i);
            this.table.get(i).setBackground(colors.get(color));
            if(color>1) {
                if(text<11) this.table.get(i).setText(text+ "");
                if(text==11) this.table.get(i).setText("BO");
                if(text==12) this.table.get(i).setText("FL");
                if(text>12) this.table.get(i).setText(text+ "");

                if (color == 3)
                    this.table.get(i).setText(!j1?"--":this.table.get(i).getText());
                if (color == 2)
                    this.table.get(i).setText(j1?"--":this.table.get(i).getText());

            }
            else{
                this.table.get(i).setText("");
            }
        }
    }

    private void onClick(ActionEvent e) throws RemoteException {
        if(chatPanel.hasEnemy()) {
            if (ApplicationRun.yourTurn) {
                JButton button = (JButton) e.getSource();
                int line = button.getY() / 38;
                int col = button.getX() / 38;
                if (firstClick) {
                    int position = (line*10) + col;
                    if (globalGame.movementPieceFirstClick(line, col, ApplicationRun.j1)){
                        if(globalGame.isUp()) {
                            gamePanel.getTable().get(position-10).setBackground(ApplicationRun.colors.get(4));
                            gamePanel.getTable().get(position-10).setText("^"); }
                        if(globalGame.isDown()) {
                            gamePanel.getTable().get(position+10).setBackground(ApplicationRun.colors.get(4));
                            gamePanel.getTable().get(position+10).setText("\\/");}
                        if(globalGame.isLeft()) {
                            gamePanel.getTable().get(position-1).setBackground(ApplicationRun.colors.get(4));
                            gamePanel.getTable().get(position-1).setText("<");}
                        if(globalGame.isRight()){
                            gamePanel.getTable().get(position+1).setBackground(ApplicationRun.colors.get(4));
                            gamePanel.getTable().get(position+1).setText(">");}
                        firstClick = false;
                        positionFirst = new int[]{line,col};
                    }
                } else {
                    int vertical = line - positionFirst[0];
                    int horizontal = col - positionFirst[1];
                    firstClick = true;
                    if (globalGame.movementPieceSecondClick(line, col, vertical, horizontal, ApplicationRun.j1)) {
                        gamePanel.updateTable();
                        chatPanel.chat.skipTurnChat();
                        chatPanel.chatEnemy.skipTurnChat();
                    }
                }
            }
        }
        else{
            chatPanel.writeLog("Inimigo ainda nao conectado! Aguarde sua conexão...");
        }

    }

    public void loseMessage() {

    }
}
