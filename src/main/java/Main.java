import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Main {
    /****************************
     * VARIAVEIS DO SISTEMA.
     ****************************/
    public final int widthGame = 480;
    public final int heighGame = 560;


    /*****************************
     * VARIAVEIS DA CLASSE MAIN.
     *****************************/
    private MainFrame frame;
    private JPanel mainPannel;
    private GamePanel gamePannel;
    private ChatPanel chatPannel;
    private ChatText chatTextLog;


    public static void main(String[] args) throws InterruptedException {
        new Main().startApp();
    }

    private void startApp() {
        chatTextLog = new ChatText();

        mainPannel = new JPanel();
        mainPannel.setLayout(null);

        gamePannel = new GamePanel(widthGame,heighGame);

        chatPannel = new ChatPanel();
        chatPannel.add(chatTextLog);

        mainPannel.add(gamePannel);
        mainPannel.add(chatPannel);

        frame = new MainFrame("Combate Game");
        frame.add(mainPannel);
    }

}
