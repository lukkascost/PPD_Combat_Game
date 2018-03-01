package Chat;

import Communication.CommonStatic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ChatPanel extends JPanel{

    private JTextField writeToSend;
    private JButton send;
    private ChatText chatTextLog;
    private JTextArea log = new JTextArea();
    private JScrollPane logSP = new JScrollPane(log);
    private AbstractAction abstractAction;


    public ChatPanel() {
        this.writeToSend = new JTextField();
        this.chatTextLog = new ChatText();
        this.send = new JButton("->",new ImageIcon("src/java/Chat/send.png"));
        this.abstractAction = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                onClick();
            }
        };

        send.setBounds(335,415,40,30);
        send.addActionListener(this.abstractAction);

        writeToSend.setBounds(10,415,320,30);
        writeToSend.addActionListener(this.abstractAction);


        JScrollPane sp = new JScrollPane(chatTextLog);   // JTextArea is placed in a JScrollPane.
        sp.setBounds(10,10,360,400);
        sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        logSP.setBounds(10,450,360,100);
        logSP.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        log.setBackground(Color.lightGray);
        log.setEditable(false);


        this.add(logSP);
        this.add(sp);
        this.add(writeToSend);
        this.add(send);
        this.setBackground(Color.lightGray);
        this.setLayout(null);
        this.setBounds(500,0,380,560);
        this.setVisible(true);
    }

    private void onClick(){
        if(CommonStatic.isConnected){
            //TODO no log informar que o outro jogador nao foi encontrado.
        }
        if (!this.writeToSend.getText().equals("") && CommonStatic.isConnected) {
            this.chatTextLog.append("Você: " + this.writeToSend.getText());;
            CommonStatic.protocolMsg2Send = "01 "+this.writeToSend.getText();
            CommonStatic.onDataSend.release();
            this.writeToSend.setText("");
        }
    }
    public void writeLog(String msg){
        this.log.append(msg+"\n");
    }
    public ChatText getChatTextLog() {
        return chatTextLog;
    }

    public void setChatTextLog(ChatText chatTextLog) {
        this.chatTextLog = chatTextLog;
    }
}
