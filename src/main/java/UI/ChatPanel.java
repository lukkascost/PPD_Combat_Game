package UI;

import MainPackage.ApplicationRun;


import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;

import static MainPackage.ApplicationRun.*;

public class ChatPanel extends JPanel {

    private JTextField writedText;
    private JButton send;
    private ChatText chatTextLog;
    private JTextArea log = new JTextArea();
    private JScrollPane logSP = new JScrollPane(log);
    private AbstractAction abstractAction;



    public ChatPanel()  {
        this.writedText = new JTextField();
        this.chatTextLog = new ChatText();

        this.send = new JButton("->",new ImageIcon("src/java/Chat/send.png"));
        this.abstractAction = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                    onClick();

            }
        };

        send.setBounds(435,415,40,30);
        send.addActionListener(this.abstractAction);

        writedText.setBounds(10,415,420,30);
        writedText.addActionListener(this.abstractAction);


        JScrollPane sp = new JScrollPane(chatTextLog);   // JTextArea is placed in a JScrollPane.
        sp.setBounds(10,10,460,400);
        sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        logSP.setBounds(10,450,460,100);
        logSP.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        log.setBackground(Color.lightGray);
        log.setEditable(false);


        this.add(logSP);
        this.add(sp);
        this.add(writedText);
        this.add(send);
        this.setBackground(Color.lightGray);
        this.setLayout(null);
        this.setBounds(400,0,480,560);
        this.setVisible(true);

    }

    private void onClick()  {
        chatTextLog.append(playerName+": "+ this.writedText.getText());
        this.writedText.setText("");
    }
     public void writeLog(String s) {
         this.log.append(s + "\n");
         this.log.setCaretPosition(this.log.getDocument().getLength());
     }
}
