package MainPackage;

import Chat.ChatPanel;
import Game.GamePanel;
import UI.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class ApplicationRun extends Thread {
    public static final String[] peaces = new String[]{"01","02","02","02","02","02","02","02","02","03","03","03","03","03","04","04","04","04",
            "05","05","05","05","06","06","06","06","07","07","07","08","08","09","10","FL","BO","BO","BO","BO","BO","BO"};
    public static ArrayList<Color> colors = new ArrayList<>();
    public static String player;
    public static String enemy;
    public static String ip = "localhost";
    public static boolean run = true;
    public static boolean yourTurn = false;
    public static boolean j1 = false;
    public static GamePanel gamePanel;
    public static ChatPanel chatPanel;


    public static void main(String[] args)  {
        new ApplicationRun().start();
    }

    @Override
    public void run() {
        colors.add(new Color(33,120,57));
        colors.add(new Color(61 ,165 ,184));
        colors.add(new Color(255,228,225));
        colors.add(new Color(175,238,238));
        colors.add(new Color(255,255,179));
        try {
            LocateRegistry.createRegistry(1099);
            j1 = true;
            yourTurn = true;
        } catch (RemoteException ignored) {
        }
        //ip = JOptionPane.showInputDialog("Digite o Caminho do servidor");
        player = JOptionPane.showInputDialog("Digite seu nick");
        enemy = JOptionPane.showInputDialog("Digite o nick do oponente");

        Collections.shuffle(Arrays.asList(peaces));

        JPanel mainPannel = new JPanel();
        mainPannel.setLayout(null);

        int widthGame = 480;
        int heightGame = 560;
        try {
            gamePanel = new GamePanel(widthGame, heightGame);
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }

        try {
            chatPanel = new ChatPanel();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        mainPannel.add(gamePanel);
        mainPannel.add(chatPanel);
        mainPannel.setBackground(Color.lightGray);

        MainFrame frame = new MainFrame("Combat Game: "+player);
        frame.setBackground(Color.lightGray);
        frame.add(mainPannel);

        gamePanel.surrender.setEnabled(true);
    }

}
