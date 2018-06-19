package UI;

import MainPackage.ApplicationRun;
import RMI.ChatImplementation;
import RMI.IChat;


import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import static MainPackage.ApplicationRun.*;

public class ChatPanel extends JPanel {

    private JTextField writedText;
    private JButton send;
    public ChatText chatTextLog;
    private JTextArea log = new JTextArea();
    private JScrollPane logSP = new JScrollPane(log);
    private AbstractAction abstractAction;
    public IChat chat ;
    public IChat friendChat ;



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
            LocateRegistry.getRegistry(ip).rebind(playerName+"-chat",chat);
        } catch (RemoteException e) {
            e.printStackTrace();
        }


    }

    private void onClick()  {
        if (!this.writedText.getText().equals("")){
            if (optionsPanel.getSelectedFriendName() != null){
                //TODO fazer enviar para os dois chats.

            }else{
                //TODO erro, amigo nao selecionado.
            }
        }
        this.writedText.setText("");
    }


     public void writeLog(String s) {
         this.log.append(s + "\n");
         this.log.setCaretPosition(this.log.getDocument().getLength());

     }

     public boolean isFriendChatOnline(String player_name){
         try {
             return friendChat.isOnlineChecked();
         } catch (RemoteException e) {
             e.printStackTrace();
         }
         return false;
     }

    public boolean hasFriend() throws RemoteException {
        if (this.friendChat !=  null) return true;
        try {
            friendChat = (IChat) LocateRegistry.getRegistry(ip).lookup(optionsPanel.getSelectedFriendName()+"-chat");
            return true;
        }
        catch (NotBoundException ignored){
        }
        catch (AccessException e) {
            e.printStackTrace();
        }
        return false;
    }

}
