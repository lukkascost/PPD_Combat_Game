package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ChatPannel extends JPanel{
    private JTextField writedText;
    private JButton send;
    private ChatText chatTextLog;
    private AbstractAction abstractAction;


    public ChatPannel() {
        this.writedText = new JTextField();
        this.chatTextLog = new ChatText();
        this.send = new JButton("->",new ImageIcon("src/java/Chat/send.png"));
        this.abstractAction = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                    onClick();
            }
        };
        send.setBounds(335,415,40,30);
        send.addActionListener(this.abstractAction);

        writedText.setBounds(10,415,320,30);
        writedText.addActionListener(this.abstractAction);


        JScrollPane sp = new JScrollPane(chatTextLog);   // JTextArea is placed in a JScrollPane.
        sp.setBounds(10,10,360,400);
        sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);


        this.add(sp);
        this.add(writedText);
        this.add(send);
        this.setBackground(Color.lightGray);
        this.setLayout(null);
        this.setBounds(0,0,380,560);
        this.setVisible(true);
    }
    private void onClick() {

    }

}

