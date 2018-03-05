package MainPackage;

import Chat.ChatPanel;
import Communication.CommonStatic;
import Communication.ICommunication;
import Communication.Sockets.SocketCommunication;
import Game.GameBackEnd;
import Game.GamePanel;
import Threads.ThreadChatReceive;
import UI.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class ApplicationRun extends Thread {
    private static final String[] peaces = new String[]{"01","02","02","02","02","02","02","02","02","03","03","03","03","03","04","04","04","04",
            "05","05","05","05","06","06","06","06","07","07","07","08","08","09","10","FL","BO","BO","BO","BO","BO","BO"};
    public static ArrayList<Color> colors = new ArrayList<>();
    public static boolean j1;
    public static GameBackEnd gameBackEnd;
    public static boolean run = true;
    public static boolean yourTurn = false;

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

        String ip = JOptionPane.showInputDialog("Digite o ip para se conectar");
        int port = Integer.parseInt(JOptionPane.showInputDialog("Digite a porta para se conectar"));

        Collections.shuffle(Arrays.asList(peaces));

        JPanel mainPannel = new JPanel();
        mainPannel.setLayout(null);

        int widthGame = 480;
        int heightGame = 560;
        gamePanel = new GamePanel(widthGame, heightGame);

        chatPanel = new ChatPanel();

        mainPannel.add(gamePanel);
        mainPannel.add(chatPanel);
        mainPannel.setBackground(Color.lightGray);

        MainFrame frame = new MainFrame("Combat Game");
        frame.setBackground(Color.lightGray);
        frame.add(mainPannel);
        ICommunication socketCommunication;

        try {
            chatPanel.writeLog("waiting connection...");
            socketCommunication = new SocketCommunication(ip,port);
            socketCommunication.connect();
            j1=false;
            chatPanel.writeLog("Connected!!");
            frame.setTitle("Combat Game - J2");
            yourTurn = false;
            chatPanel.writeLog("Aguarde sua Jogada...");
        } catch (IOException e) {
            try {
                chatPanel.writeLog("Um servidor foi iniciado no seu ip, porta: "+port);
                socketCommunication = new SocketCommunication(port);
                socketCommunication.connect();
                j1 = true;
                frame.setTitle("Combat Game - J1");
                chatPanel.writeLog("Connected!!");
                yourTurn = true;
                chatPanel.writeLog("Sua vez!");

            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        gameBackEnd=  new GameBackEnd(gamePanel.getTable());
        gameBackEnd.setInitialValues(peaces);
        gamePanel.updateTable(gameBackEnd);
        gamePanel.surrender.setEnabled(true);
        new ThreadChatReceive().start();
        CommonStatic.isConnected = true;
    }

}
