import Chat.ChatPanel;
import Chat.ChatText;

import javax.swing.*;

public class ApplicationRun {
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
    private ChatPanel chatPannel;
    private ChatText chatTextLog;


    public static void main(String[] args)  {
        new ApplicationRun().startApp();
    }

    private void startApp() {
        chatTextLog = new ChatText();

        mainPannel = new JPanel();
        mainPannel.setLayout(null);

        gamePannel = new GamePanel(widthGame,heightGame);

        chatPannel = new ChatPanel();
        chatPannel.add(chatTextLog);

        mainPannel.add(gamePannel);
        mainPannel.add(chatPannel);

        frame = new MainFrame("Combate Game");
        frame.add(mainPannel);
    }

}
