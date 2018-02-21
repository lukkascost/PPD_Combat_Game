package Chat;

import javax.swing.*;
import java.awt.*;

public class ChatText extends JTextArea{


    public ChatText() {
        this.setBounds(10,10,360,400);
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        this.setEditable(false);
    }
}
