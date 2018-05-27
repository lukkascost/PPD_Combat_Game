import UI.ChatPannel;
import net.jini.space.JavaSpace;

import javax.swing.*;
import java.awt.*;

public class ChatUserUI {

    static JavaSpace space;
    static JFrame frame;
    static JPanel mainPannel;
    static ChatPannel chatPannel;

    public static void main(String[] args) {
        try {
            System.out.println("Procurando pelo servico JavaSpace...");
            Lookup finder = new Lookup(JavaSpace.class);
            space = (JavaSpace) finder.getService();
            if (space == null) {
                System.out.println("O servico JavaSpace nao foi encontrado. Encerrando...");
                System.exit(-1);
            }
            System.out.println("O servico JavaSpace foi encontrado.");
        }catch (Exception e){
            e.printStackTrace();
        }
        showScreen();
    }


    public static void  showScreen(){
        mainPannel =  new JPanel();
        mainPannel.setLayout(null);

        chatPannel = new ChatPannel();
        mainPannel.add(chatPannel);

        frame = new JFrame("User Enviroment Chat");
        frame.add(mainPannel);

        frame.setLayout(new GridLayout(1, 1));
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(380, 560);


    }
}
