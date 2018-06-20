package MainPackage;

import UI.ChatPanel;
import UI.MainFrame;
import UI.OnlineCheckBox;
import UI.OptionsPanel;
import WebService.IChatService;
import WebService.IChatServiceImpl;
import WebService.IChatServiceImplService;

import javax.swing.*;
import java.awt.*;
import java.util.Hashtable;


public class ApplicationRun extends Thread {
    public static String playerName;

    public static MainFrame frame;
    public static ChatPanel chatPanel;
    public static JPanel mainPannel;
    public static OptionsPanel optionsPanel;
    public static JCheckBox isOnline;
    public static JLabel activeChat;
    public static JCheckBox activeChatStatus;
    public static String ip = "localhost";
    public static int port = 4040;
    public static Hashtable friendChatContent = new Hashtable();
    public static IChatService iChatService;

    public static void main(String[] args)  {
        new ApplicationRun().start();
    }

    public void run(){

        IChatServiceImplService iChatServiceImplService = new IChatServiceImplService();
        iChatService = iChatServiceImplService.getIChatServiceImplPort();

        setPlayerName();

        iChatService.createQueue(playerName);
        activeChat = new JLabel("Nome: --");
        activeChat.setBounds(10,10,200,30);

        activeChatStatus = new JCheckBox("Online");
        activeChatStatus.setBounds(200,10,200,30);
        activeChatStatus.setEnabled(false);
        activeChatStatus.setSelected(true);

        isOnline = new OnlineCheckBox("Online");

        mainPannel = new JPanel();
        mainPannel.setLayout(null);

        optionsPanel = new OptionsPanel();
        optionsPanel.add(isOnline);

        chatPanel = new ChatPanel();
        chatPanel.add(activeChat);
        chatPanel.add(activeChatStatus);

        mainPannel.add(chatPanel);
        mainPannel.setBackground(Color.lightGray);

        frame = new MainFrame("PPChat: "+ playerName);
        frame.setBackground(Color.lightGray);
        frame.add(mainPannel);
        mainPannel.add(optionsPanel);

    }


    public void setPlayerName(){
        playerName = JOptionPane.showInputDialog("Digite seu nick!");
    }
}