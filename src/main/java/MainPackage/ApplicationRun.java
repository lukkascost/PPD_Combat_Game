package MainPackage;

import Chat.*;
import Communication.*;
import Communication.Sockets.SocketCommunication;
import Game.Table;
import UI.*;
import Threads.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class ApplicationRun extends Thread {
    /****************************
     * VARIAVEIS DO SISTEMA.
     ****************************/
    private final int widthGame = 480;
    private final int heightGame = 560;
    public static final String[] peaces = new String[]{"01","02","02","02","02","02","02","02","02","03","03","03","03","03","04","04","04","04",
            "05","05","05","05","06","06","06","06","07","07","07","08","08","09","10","FL","BO","BO","BO","BO","BO","BO"};

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

        Table table = new Table();
        table.setOnes();
        System.out.println(table);

        ICommunication socketCommunication;
        try {
            chatPannel.writeLog("Aguardando conexao...");
            socketCommunication = new SocketCommunication("127.0.0.1",4055);
            socketCommunication.connect();
            chatPannel.writeLog("Conectado!!");
            frame.setTitle("CLIENT");
        } catch (IOException e) {
            try {
                socketCommunication = new SocketCommunication(4055);
                socketCommunication.connect();
                frame.setTitle("SERVER");
                chatPannel.writeLog("Conectado!!");

            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        new ThreadChatReceive().start();
        CommonStatic.isConnected = true;
    }

}
