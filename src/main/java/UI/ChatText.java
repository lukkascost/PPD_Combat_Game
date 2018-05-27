package UI;

import javax.swing.*;

public class ChatText extends JTextArea {
    public ChatText() {
        this.setBounds(0,0,360,400);
        this.setEditable(false);
        this.setAutoscrolls(true);
    }

    public void append(String msg ){
        this.setText(this.getText() + "\n" + msg);
    }
}
