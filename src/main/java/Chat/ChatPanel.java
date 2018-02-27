package Chat;

import Communication.CommonStatic;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ChatPanel extends JPanel{

    private JTextField writeToSend;
    private JButton send;
    private ChatText chatTextLog;
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

        this.add(sp);
        this.add(writeToSend);
        this.add(send);

        this.setLayout(null);
        this.setBounds(500,0,380,560);
//        this.setBorder(BorderFactory.createLineBorder(Color.blue));
        this.setVisible(true);
    }

    private void onClick(){
        if (!this.writeToSend.getText().equals("")) {
            this.chatTextLog.append("Você: " + this.writeToSend.getText());;
            CommonStatic.protocolMsg2Send = this.writeToSend.getText();
            CommonStatic.onDataSend.release();
            this.writeToSend.setText("");
        }
    }

    public ChatText getChatTextLog() {
        return chatTextLog;
    }

    public void setChatTextLog(ChatText chatTextLog) {
        this.chatTextLog = chatTextLog;
    }
}
