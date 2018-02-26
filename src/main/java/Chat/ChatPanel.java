package Chat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChatPanel extends JPanel{

    private JTextField writeToSend;
    private JButton send;
    private ChatText chatTextLog;
    private AbstractAction abstractAction;


    public ChatPanel() {
        this.writeToSend = new JTextField();
        this.chatTextLog = new ChatText();
        this.send = new JButton();
        this.abstractAction = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                onClick();
            }
        };


        send.setBounds(335,415,40,30);
        send.setBorder(BorderFactory.createLineBorder(Color.red));

        send.addActionListener(this.abstractAction);



        writeToSend.setBounds(10,415,320,30);
        writeToSend.setBorder(BorderFactory.createLineBorder(Color.green));
        writeToSend.addActionListener(this.abstractAction);

        this.add(chatTextLog);
        this.add(writeToSend);
        this.add(send);

        this.setLayout(null);
        this.setBounds(500,0,380,560);
        this.setBorder(BorderFactory.createLineBorder(Color.blue));
        this.setVisible(true);
    }


    private void onClick(){
        this.chatTextLog.setText(this.chatTextLog.getText() +"\nVocê: "+  this.writeToSend.getText());
        this.writeToSend.setText("");

        //TODO implementar envio na communicacao.
    }



}
