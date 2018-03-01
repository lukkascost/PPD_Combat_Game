package MainPackage;

import Communication.*;
import Communication.Sockets.SocketCommunication;
import Game.*;
import UI.*;
import Chat.*;
import Protocol.*;

import Threads.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class ApplicationRun extends Thread {
    /****************************
     * VARIAVEIS DO SISTEMA.
     ****************************/
    private final int widthGame = 480;
    private final int heightGame = 560;
    public static final String[] peaces = new String[]{"01","02","02","02","02","02","02","02","02","03","03","03","03","03","04","04","04","04",
            "05","05","05","05","06","06","06","06","07","07","07","08","08","09","10","FL","BO","BO","BO","BO","BO","BO"};
    public static ArrayList<Color> colors = new ArrayList<>();
    public static boolean j1;
    public static GameBackEnd gameBackEnd;

    /*****************************
     * VARIAVEIS DA CLASSE MAIN.
     *****************************/
    private MainFrame frame;
    private JPanel mainPannel;
    public static GamePanel gamePannel;
    public static ChatPanel chatPannel;

    public static void main(String[] args)  {
        new ApplicationRun().start();
    }

    @Override
    public void run() {
        colors.add(new Color(33,120,57));
        colors.add(new Color(61 ,165 ,184));
        colors.add(new Color(255,228,225));
        colors.add(new Color(175,238,238));
        Collections.shuffle(Arrays.asList(peaces));

        mainPannel = new JPanel();
        mainPannel.setLayout(null);

        gamePannel = new GamePanel(widthGame,heightGame);

        chatPannel = new ChatPanel();

        mainPannel.add(gamePannel);
        mainPannel.add(chatPannel);
        mainPannel.setBackground(Color.lightGray);

        frame = new MainFrame("Combate Game");
        frame.setBackground(Color.lightGray);
        frame.add(mainPannel);

        ICommunication socketCommunication;
        try {
            chatPannel.writeLog("Aguardando conexao...");
            socketCommunication = new SocketCommunication("127.0.0.1",4055);
            socketCommunication.connect();
            j1=true;
            chatPannel.writeLog("Conectado!!");
            frame.setTitle("J1");
        } catch (IOException e) {
            try {
                socketCommunication = new SocketCommunication(4055);
                socketCommunication.connect();
                j1 = false;
                frame.setTitle("J2");
                chatPannel.writeLog("Conectado!!");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        gameBackEnd=  new GameBackEnd(gamePannel.getTable());
        gameBackEnd.setInitialValues(peaces);
        gamePannel.updateTable(gameBackEnd);
        new ThreadChatReceive().start();
        CommonStatic.isConnected = true;



    }

}
