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
    public ChatPanel chatPannel;


    public static void main(String[] args)  {
        new ApplicationRun().startApp();
    }

    private void startApp() {

        mainPannel = new JPanel();
        mainPannel.setLayout(null);

        gamePannel = new GamePanel(widthGame,heightGame);

        chatPannel = new ChatPanel();

        mainPannel.add(gamePannel);
        mainPannel.add(chatPannel);

        frame = new MainFrame("Combate Game");
        frame.add(mainPannel);
    }

}
