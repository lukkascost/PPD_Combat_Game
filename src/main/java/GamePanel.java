import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GamePanel extends JPanel  implements  IGamePanel {
    private ArrayList<JTextField> table = new ArrayList<JTextField>();

    public GamePanel(int widthGame,int heightGame) {
        this.setLayout(null);
        this.setBounds(0,0,widthGame,heightGame);
//        this.setBorder(BorderFactory.createLineBorder(Color.red));

        int widthOfSquare = (int) (widthGame*0.09f);

        for (int j=0;j<10;j++) {
            for (int i = 0; i < 10; i++) {
                JTextField field = new JTextField();
                field.setEditable(false);
                field.setBackground(Color.blue);
                field.setBorder(BorderFactory.createLineBorder(Color.lightGray));
                field.setBounds((int) (widthGame*0.05f) + (i * widthOfSquare), (int) (widthGame*0.05f)+ ( j*widthOfSquare), widthOfSquare, widthOfSquare);
                this.table.add(field);
                this.add(field);
            }
        }
    }

    public void moveObject() {

    }

    public void sendMoviment() {

    }
}
