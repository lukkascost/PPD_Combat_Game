package UI;

import MainPackage.ApplicationRun;
import RMI.ChatImplementation;
import RMI.IChat;
import RMI.RMIMethods;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import static MainPackage.ApplicationRun.*;
import static RMI.RMIMethods.setRMIChatObject;

public class ChatPanel extends JPanel {

    public JTextField writedText;
    private JButton send;
    public ChatText chatTextLog;
    private JTextArea log = new JTextArea();
    private JScrollPane logSP = new JScrollPane(log);
    private AbstractAction abstractAction;
    public IChat chat ;



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
        writedText.setEditable(false);


        JScrollPane sp = new JScrollPane(chatTextLog);   // JTextArea is placed in a JScrollPane.
        sp.setBounds(10,40,460,360);
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


        try {
            chat = new ChatImplementation();
            setRMIChatObject(chat,playerName);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void onClick()  {
        if (this.writedText.getText().equals("")) return;
        if (optionsPanel.friendList.getSelectedValue() == null) {
            this.writedText.setText("");
            return;
        }

        String friendName = (String) optionsPanel.friendList.getSelectedValue();

        if(!RMIMethods.existFriendObject(friendName)){
            this.writedText.setText("");
            return;
        }

        try {
            IChat friend = RMIMethods.getRMIChatObject(friendName);
            if (friend.isOnlineChecked()) {
                chat.writeMessage(this.writedText.getText(), playerName, friendName);
                friend.writeMessage(this.writedText.getText(), playerName, playerName);
            }
            else{
                iChatService.writeMessage(playerName+": "+this.writedText.getText(),friendName);
                chat.writeMessage(this.writedText.getText(), playerName, friendName);
            }
        }
        catch (Exception e){
            log.append(e.getMessage());
        }
        this.writedText.setText("");
    }


    public void writeLog(String s) {
        this.log.append(s + "\n");
        this.log.setCaretPosition(this.log.getDocument().getLength());

    }

    public void updateActualChat(){
        String selectedValue = (String) optionsPanel.friendList.getSelectedValue();
        this.chatTextLog.setText((String) ApplicationRun.friendChatContent.get(selectedValue));
        activeChatStatus.setSelected(RMIMethods.isFriendChatOnline(selectedValue));

    }
}
