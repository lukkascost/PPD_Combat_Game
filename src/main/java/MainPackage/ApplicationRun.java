package MainPackage;

import Chat.*;
import Communication.*;
import Communication.Sockets.SocketCommunication;
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

    /*****************************
     * VARIAVEIS DA CLASSE MAIN.
     *****************************/
    private MainFrame frame;
    private JPanel mainPannel;
    private GamePanel gamePannel;
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