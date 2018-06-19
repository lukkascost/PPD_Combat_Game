package MainPackage;

import UI.ChatPanel;
import UI.MainFrame;
import UI.OptionsPanel;

import javax.swing.*;
import java.awt.*;


public class ApplicationRun extends Thread {
    public static String playerName;

    public static MainFrame frame;
    public static ChatPanel chatPanel;
    public static JPanel mainPannel;
    public static OptionsPanel optionsPanel;




    public static void main(String[] args)  {
        new ApplicationRun().start();
    }


    public void run(){
        setPlayerName();

        mainPannel = new JPanel();
        mainPannel.setLayout(null);

        optionsPanel = new OptionsPanel();

        chatPanel = new ChatPanel();
        mainPannel.add(chatPanel);
        mainPannel.setBackground(Color.lightGray);

        frame = new MainFrame("PPChat");
        frame.setBackground(Color.lightGray);
        frame.add(mainPannel);
        mainPannel.add(optionsPanel);

    }


    public void setPlayerName(){
        playerName = JOptionPane.showInputDialog("Digite seu nick!");
    }
}